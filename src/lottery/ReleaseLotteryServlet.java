package lottery;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ConnectionAndResultSet;
import tools.DataBaseUtil;

/**
 * @Title: ReleaseLotteryServlet
 * @Description: 直接发布抽奖活动 
 * @Company: ZhongHe
 * @author ben
 * @date 2013-11-12
 */
@WebServlet("/ReleaseLotteryServlet")
public class ReleaseLotteryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReleaseLotteryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		/**
		 * 需要接收参数：LotteryId
		 */
	//	int lotteryId = request.getParameter("LotteryId");
		int lotteryId = 1;

		/**update the database*/
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT EndDate, "
				+ "LotteryStatus FROM lottery_activity WHERE LotteryId = " + lotteryId);
		ResultSet result = con.getResultSet();
		try {
			if(result.last() && result.getRow() == 1){
				Timestamp endDate = result.getTimestamp("EndDate");
				int lotteryStatus = result.getInt("LotteryStatus");
				if(endDate.after(new Timestamp(System.currentTimeMillis())) && lotteryStatus != 1){
					dataBaseUtil.SqlExec("UPDATE lottery_activity SET LotteryStatus = 1 WHERE LotteryId = " + lotteryId);
				}
				else {
					System.out.println("error in data!");
				}
			}
			else {
				System.out.println("error in records!");
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
