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
 * @Title: GetLuckyRecordServlet
 * @Description: 商家查看活动情况时获取当前剩余奖品信息  
 * @Company: ZhongHe
 * @author ben
 * @date 2013-11-12
 */
@WebServlet("/GetLuckyRecordServlet")
public class GetLuckyRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLuckyRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		/**
		 * 需要接收参数：LotteryId
		 */
		int lotteryId = Integer.parseInt(request.getParameter("LotteryId"));
		
		/**query the database*/
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT * FROM lottery_prize WHERE LotteryId = " + lotteryId);
		ResultSet result = con.getResultSet();
		List<PrizeInfo> lpList = new ArrayList<PrizeInfo>();
		try {
			while(result.next()){
				PrizeInfo tp = new PrizeInfo(result.getInt("PrizeId"), result.getString("PrizeName"), 
						result.getString("PrizeContent"), result.getInt("LuckyNum"),
						result.getDouble("LuckyPercent"));
				lpList.add(tp);
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**construct result model*/
		List<LotteryStatus> lsList = new ArrayList<LotteryStatus>();
		for (int i = 0; i < lpList.size(); i++) {
			PrizeInfo temp = lpList.get(i);
			int currentLuckyNum = 0;
			con = dataBaseUtil.SqlQuery("SELECT * FROM lottery_lucky_record WHERE PrizeId = " + temp.getPrizeId());
			result = con.getResultSet();
			try {
				if(result.last()){
					currentLuckyNum = result.getRow();   //计算一项奖品的剩余个数
				}
				result.close();
				con.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lsList.add(new LotteryStatus(temp.getPrizeName(), currentLuckyNum));
		}
		
		/**object to json*/
		Gson gson = new Gson();
		String json = gson.toJson(lsList);
		
		/**response json*/
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
