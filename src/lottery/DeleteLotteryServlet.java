package lottery;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ConnectionAndResultSet;
import tools.DataBaseUtil;

/**
 * @Title: DeleteLotteryServlet
 * @Description: 删除抽奖活动记录 
 * @Company: ZhongHe
 * @author ben
 * @date 2013-11-12
 */
@WebServlet("/DeleteLotteryServlet")
public class DeleteLotteryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLotteryServlet() {
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
		int lotteryId = Integer.parseInt(request.getParameter("LotteryId"));

		/**delete from the database*/
		int lotteryStatus = 1;
		String picturePath = null;
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT LotteryPicture, "
				+ "LotteryStatus FROM lottery_activity WHERE LotteryId = " + lotteryId);
		ResultSet result = con.getResultSet();
		try {
			if(result.last()){
				lotteryStatus = result.getInt("LotteryStatus");
				picturePath = result.getString("LotteryPicture");
				if (picturePath.equalsIgnoreCase("null")) {  //translate
					picturePath = null;
				}
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**process*/
		if (picturePath != null && lotteryStatus != 1) {    //删除图片
			picturePath = request.getSession().getServletContext().getRealPath("") + picturePath;
			boolean flag = (new File(picturePath)).delete();
			if (flag) {
				System.out.println("picture deleted!");
			}
			else {
				System.out.println("delete the picture failed!");
			}
		}			
		if((lotteryStatus == -1) || (lotteryStatus == 0)){
			dataBaseUtil.SqlExec("DELETE FROM lottery_activity WHERE LotteryId = " + lotteryId);
			dataBaseUtil.SqlExec("DELETE FROM lottery_prize WHERE LotteryId = " + lotteryId);
		}else {
			if (lotteryStatus == 2) {
				 List<Integer> pidList = new ArrayList<Integer>();
				 con = dataBaseUtil.SqlQuery("SELECT PrizeId FROM lottery_prize WHERE LotteryId = " + lotteryId);
				 result = con.getResultSet();
				 try {
					while (result.next()) {
						pidList.add(result.getInt("PrizeId"));
					}
					result.close();
					con.getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 for (int i = 0; i < pidList.size(); i++) {
					dataBaseUtil.SqlExec("DELETE FROM lottery_lucky_record WHERE PrizeId = " + pidList.get(i));
				}
				 dataBaseUtil.SqlExec("DELETE FROM lottery_activity WHERE LotteryId = " + lotteryId);
				 dataBaseUtil.SqlExec("DELETE FROM lottery_prize WHERE LotteryId = " + lotteryId);
				 dataBaseUtil.SqlExec("DELETE FROM lottery_record WHERE LotteryId = " + lotteryId);			 
			}
			else {
				System.out.println("can not delete!");
			}
		}
		
	}

}
