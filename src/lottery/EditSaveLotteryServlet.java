package lottery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tools.DataBaseUtil;
import tools.ImageUtil;

/**
 * @Title: EditSaveLotteryServlet
 * @Description: 商家编辑抽奖活动并选择保存  
 * @Company: ZhongHe
 * @author ben
 * @date 2013-11-12
 */
@WebServlet("/EditSaveLotteryServlet")
@MultipartConfig
public class EditSaveLotteryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSaveLotteryServlet() {
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
		 * 需要接受LotteryForm表单 + json奖项信息 + LotteryId
		 */
//		int lotteryId = request.getParameter("LotteryId");
		int lotteryId = 1;
		
		String lotteryName = request.getParameter("LotteryName");
		String lotterySummary = request.getParameter("LotterySummary");
		String startDate = request.getParameter("StartDate") + " " + request.getParameter("StartTime");
		String endDate = request.getParameter("EndDate") + " " + request.getParameter("EndTime");
		int chanceNum = Integer.parseInt(request.getParameter("ChanceNum"));
		
		Part lotteryPicture = request.getPart("LotteryPicture");
		String picturePath = null;
		if(lotteryPicture.getContentType().startsWith("image/")){
			picturePath = request.getSession().getServletContext().getRealPath("");
		    picturePath = ImageUtil.scaleFill(lotteryPicture.getInputStream(), picturePath, 400, 400);
		}	
		
		/**json示范*/
		//simulate json input and will be replaced
		LotteryPrize lp = new LotteryPrize("特等奖", "iphone5s 一部", 3, 0.0001);
		LotteryPrize lp2 = new LotteryPrize("一等奖", "欧洲七日游旅游券1张", 20, 0.005);
		List<LotteryPrize> list = new ArrayList<LotteryPrize>();
		list.add(lp);
		list.add(lp2);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		//json styles 
/*		[{"prizeName":"特等奖","prizeContent":"iphone5s 一部","luckyNum":3,
		  "luckyPercent":1.0E-4},{"prizeName":"一等奖","prizeContent":"香港三日游旅游券1张",
		  "luckyNum":30,"luckyPercent":0.05}] */
		List<LotteryPrize> lpList = gson.fromJson(json, new TypeToken<ArrayList
				<LotteryPrize>>() {}.getType());
		
		/**update records in the database
		 * 更新activity表中数据，重写prize表中数据 
		 **/
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		dataBaseUtil.SqlExec("UPDATE lottery_activity SET LotteryName = '" + 
		lotteryName + "', LotterySummary = '" + lotterySummary + "', LotteryPicture = '" + 
		picturePath + "', StartDate = '" + startDate + "', EndDate = '" + endDate + 
		"', ChanceNum = " + chanceNum + ", LotteryStatus = 0 WHERE LotteryId = " + lotteryId);
		
		dataBaseUtil.SqlExec("DELETE FROM lottery_prize WHERE LotteryId = " + lotteryId);
		for(int i = 0; i < lpList.size(); i ++){
			LotteryPrize temp = lpList.get(i);
			dataBaseUtil.SqlExec("INSERT INTO lottery_prize VALUES (default,'" 
		+ lotteryId + "','" + temp.getPrizeName() + "','" + temp.getPrizeContent()
		+ "','" + temp.getLuckyNum() + "','" + temp.getLuckyPercent() + "')");
		}
	}

}
