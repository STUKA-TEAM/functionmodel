package lottery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	//	int lotteryId = request.getParameter("LotteryId");
		int lotteryId = 3;

		/**delete from the database*/
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
	    dataBaseUtil.SqlExec("DELETE FROM lottery_activity WHERE LotteryId = " + lotteryId);
		dataBaseUtil.SqlExec("DELETE FROM lottery_prize WHERE LotteryId = " + lotteryId);
		dataBaseUtil.SqlExec("DELETE FROM lottery_record WHERE LotteryId = " + lotteryId);
	}

}
