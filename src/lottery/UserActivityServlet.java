package lottery;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tools.ConnectionAndResultSet;
import tools.DataBaseUtil;

/**
 * @Title: UserActivityServlet
 * @Description: 响应用户活动界面 
 * @Company: ZhongHe
 * @author ben
 * @date 2013-11-12
 */
@WebServlet("/UserActivityServlet")
public class UserActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserActivityServlet() {
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
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT * FROM lottery_activity WHERE LotteryId = " + lotteryId);
		ResultSet result = con.getResultSet();
		LotteryInfo current = new LotteryInfo();
		try {
			if(result.last()){
				current.setLotteryId(lotteryId);
				current.setLotteryName(result.getString("LotteryName"));
				current.setLotterySummary(result.getString("LotterySummary"));
				current.setLotteryPicture(result.getString("LotteryPicture"));
				current.setStartDate(result.getTimestamp("StartDate"));
				current.setEndDate(result.getTimestamp("EndDate"));
				current.setChanceNum(result.getInt("ChanceNum"));
				current.setLotteryStatus(result.getInt("LotteryStatus"));
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
		con = dataBaseUtil.SqlQuery("SELECT * FROM lottery_prize WHERE LotteryId = " + lotteryId);
		result = con.getResultSet();
		List<LotteryPrize> lpList = new ArrayList<LotteryPrize>();
		try {
			while(result.next()){
				LotteryPrize tp = new LotteryPrize(result.getString("PrizeName"), 
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
		current.setLpList(lpList);
		
		/**object to json*/
		Gson gson = new Gson();
		String json = gson.toJson(current);
		
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
		request.setCharacterEncoding("UTF-8");
		
		/**
		 * 需要接收参数：LotteryId + OpenId
		 */
		int lotteryId = Integer.parseInt(request.getParameter("LotteryId"));
		int openId = Integer.parseInt(request.getParameter("OpenId"));
		
		/**query the database for the number of chances left*/
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT ChanceNum FROM lottery_activity WHERE LotteryId = " + lotteryId);
		ResultSet result = con.getResultSet();
		int chanceNum = 0;
		try {
			if(result.last()){
				chanceNum = result.getInt("ChanceNum");
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
		con = dataBaseUtil.SqlQuery("SELECT * FROM lottery_record WHERE OpenId = " + openId +" AND LotteryId = " + lotteryId);
		result = con.getResultSet();
		try {
			if(result.last()){
				chanceNum = chanceNum - result.getRow();   //计算一个用户的剩余抽奖次数
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/**process*/
		if(chanceNum >= 1){
			con = dataBaseUtil.SqlQuery("SELECT * FROM lottery_prize WHERE LotteryId = " + lotteryId);
			result = con.getResultSet();
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
			
			/**按中奖率由小到大排序*/
			Collections.sort(lpList, new Comparator<PrizeInfo>() {
				@Override
				public int compare(PrizeInfo o1, PrizeInfo o2) {
					// TODO Auto-generated method stub
					return Double.valueOf(o1.getLuckyPercent()).compareTo
							(Double.valueOf(o2.getLuckyPercent()));
				}
			});
			
			/**抽奖过程*/
			LotteryResult lr = null;
			Random rand = new Random();
			double lotteryResult = rand.nextDouble();   System.out.println(lotteryResult);
			double sumPercent = 0;
			for(int i = 0; i < lpList.size(); i ++){
				PrizeInfo temp = lpList.get(i);
				if((lotteryResult >= sumPercent) && (lotteryResult < sumPercent + temp.getLuckyPercent())){
					int restLuckyNum = temp.getLuckyNum();
					con = dataBaseUtil.SqlQuery("SELECT * FROM lottery_lucky_record WHERE PrizeId = " + temp.getPrizeId());
					result = con.getResultSet();
					try {
						if(result.last()){
							restLuckyNum = restLuckyNum - result.getRow();   //计算一项奖品的剩余个数
						}
						result.close();
						con.getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(restLuckyNum > 0){
						dataBaseUtil.SqlExec("INSERT INTO lottery_record VALUES(default, " + 
								lotteryId + ", " + openId + ", 1)");
						dataBaseUtil.SqlExec("INSERT INTO lottery_lucky_record VALUES(default, " 
								+ temp.getPrizeId() + ", " + openId + ")");
						lr = new LotteryResult(temp.getPrizeName(), temp.getPrizeContent(), chanceNum - 1);
						break;
					}
					else { 
						System.out.println("no more prizes, poor guy!");
					}
				}
				sumPercent += temp.getLuckyPercent();
			}
			if (lr == null) {    //没有中奖    
				dataBaseUtil.SqlExec("INSERT INTO lottery_record VALUES(default, "
			+ lotteryId + ", " + openId + ", 0)");
				lr = new LotteryResult("", "", chanceNum - 1);
			}
			
			/**object to json*/
			Gson gson = new Gson();
			String json = gson.toJson(lr);
			
			/**response json*/
			response.setContentType("application/json; charset=utf-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(json);
		}
		else {
			System.out.println("chance is zero!");
		}
	}
}
