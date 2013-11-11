package lottery;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tools.ConnectionAndResultSet;
import tools.DataBaseUtil;

/**
 * @Title: CheckLotteryStatusServlet
 * @Description: 商家查看数据库中所有抽奖活动的状态  
 * @Company: ZhongHe
 * @author ben
 * @date 2013-11-11
 */
@WebServlet("/CheckLotteryStatusServlet")
public class CheckLotteryStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLotteryStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**query from the database*/
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT LotteryId, "
				+ "LotteryName, LotteryStatus FROM lottery_activity");
		ResultSet result = con.getResultSet();
		List<LotteryStatus> lsList = new ArrayList<LotteryStatus>();
		try {
			while (result.next()) {
				LotteryStatus temp = new LotteryStatus();
				temp.setLotteryId(result.getInt("LotteryId"));
				temp.setLotteryName(result.getString("LotteryName"));
				temp.setLotteryStatus(result.getInt("LotteryStatus"));
				lsList.add(temp);
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**list to json*/
		Gson gson = new Gson();
		String json = gson.toJson(lsList);
		
		/**response*/
		response.setContentType("application/json; charset=utf-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(json);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
