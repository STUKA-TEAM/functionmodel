package vote;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import tools.ConnectionAndResultSet;
import tools.Constant;
import tools.DataBaseUtil;

/**
 * @Title: Vendor
 * @Description: 提供商家所有可操作行为
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月14日
 */
public class Vendor {
	/**
	 * @category constructor()
	 */
	public Vendor(){
		
	}
	
	/**
	 * @Title: insertActivity
	 * @Description: 向数据库中插入活动记录，数据库需要提前初始化
	 * @param vActivity
	 * @param vStatus
	 * @param dataBaseUtil
	 * @return int voteId 
	 */
	public int insertActivity(VoteActivity vActivity, int vStatus, DataBaseUtil dataBaseUtil){
		int voteId = -1;
		vActivity.setVoteStatus(vStatus);
		
		voteId = dataBaseUtil.SqlExec("INSERT INTO vote_activity VALUES (default, '" + 
		vActivity.getVoteTitle() + "','"+ vActivity.getVoteSummary() + "','" + 
		vActivity.getVotePicture() + "','" + vActivity.getStartDate() + "','" + 
		vActivity.getEndDate() + "', " + vActivity.getIsMultiChoice() + ", " + 
		vActivity.getMaxChoice() + ", " + vActivity.getEnableAdvice() + ", " + vStatus + ")");
		
		//insert items
		for (int i = 0; i < vActivity.getViList().size(); i++) {
			VoteItem temp = vActivity.getViList().get(i);
			insertItem(temp, voteId, dataBaseUtil);
		}
		
		vActivity.setVoteId(voteId);
		return voteId;
	}
	
	/**
	 * @Title: updateActivity
	 * @Description: 更新投票活动信息记录以及关联记录
	 * @param vActivity
	 * @param vStatus
	 * @param dataBaseUtil
	 * @return
	 */
	public int updateActivity(VoteActivity vActivity, int vStatus, DataBaseUtil dataBaseUtil){
		int result = -1;
		vActivity.setVoteStatus(vStatus);
		
		result = dataBaseUtil.SqlExec("UPDATE vote_activity SET VoteTitle = '" + 
		vActivity.getVoteTitle() + "', VoteSummary = '" + vActivity.getVoteSummary() + 
		"', VotePicture = '" + vActivity.getVotePicture() + "', StartDate = '" + vActivity.getStartDate() 
		+ "', EndDate = '" + vActivity.getEndDate() + "', IsMultiChoice = " + vActivity.getIsMultiChoice() 
		+ ", MaxChoice = " + vActivity.getMaxChoice() + ", EnableAdvice = " + vActivity.getEnableAdvice() 
		+ ", VoteStatus = " + vStatus + " WHERE VoteId = " + vActivity.getVoteId());
		
		if (result == 0) {  //引用完整性约束
			System.out.println("Update failure!");
			return result;
		}
		//delete old items
		List<VoteItem> viList = selectItems(vActivity.getVoteId(), dataBaseUtil);
		for (int i = 0; i < viList.size(); i++) {
			result &= deleteItem(viList.get(i), dataBaseUtil);
		}
		
		//insert new items
		for (int i = 0; i < vActivity.getViList().size(); i++) {
			insertItem(vActivity.getViList().get(i), vActivity.getVoteId(), dataBaseUtil);
		}
		
		return result;
	}
	
	/**
	 * @Title: selectActivity
	 * @Description: 根据活动Id查询一条活动记录（配置信息）
	 * @param voteId
	 * @param dataBaseUtil
	 * @return
	 */
	public VoteActivity selectActivity(int voteId, DataBaseUtil dataBaseUtil){
		VoteActivity vActivity = new VoteActivity();
		
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT * FROM vote_activity WHERE VoteId = " + voteId);
		ResultSet result = con.getResultSet();
		try {
			while (result.next()) {
				vActivity.setVoteId(voteId);
				vActivity.setVoteTitle(result.getString("VoteStatus"));
				vActivity.setVoteSummary(result.getString("VoteSummary"));
				vActivity.setVotePicture(result.getString("VotePicture"));
				vActivity.setStartDate(result.getTimestamp("StartDate"));
				vActivity.setEndDate(result.getTimestamp("EndDate"));
				vActivity.setIsMultiChoice(result.getInt("IsMultiChoice"));
				vActivity.setMaxChoice(result.getInt("MaxChoice"));
				vActivity.setEnableAdvice(result.getInt("EnableAdvice"));
				vActivity.setVoteStatus(result.getInt("VoteStatus"));
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<VoteItem> viList = selectItems(voteId, dataBaseUtil);
		vActivity.setViList(viList);
		
		return vActivity;
	}
	
	/**
	 * @Title: selectActivities
	 * @Description: 获取数据库中所有活动配置信息记录
	 * @param dataBaseUtil
	 * @return
	 */
	public List<VoteActivity> selectActivities(DataBaseUtil dataBaseUtil) {
		List<VoteActivity> vaList = new ArrayList<VoteActivity>();
		List<Integer> iList = new ArrayList<Integer>();
		
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT VoteId FROM vote_activity");
		ResultSet result = con.getResultSet();
		try {
			while(result.next()){
				iList.add(result.getInt("VoteId"));
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < iList.size(); i++) {
			VoteActivity temp = selectActivity(iList.get(i), dataBaseUtil);
			vaList.add(temp);
		}
		
		return vaList;
	}
	
	/**
	 * @Title: releaseActivity
	 * @Description: 发布活动并改变活动起始时间为当前时间
	 * @param voteId
	 * @param status
	 * @param dataBaseUtil
	 * @return
	 */
	public int releaseActivity(int voteId, DataBaseUtil dataBaseUtil){
		int result = -1;
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		
		result = dataBaseUtil.SqlExec("UPDATE vote_activity SET StartDate = " +
		currentDate + ", VoteStatus = " + Constant.ACTIVITY_RELEASE_STATUS + " WHERE VoteId = " + voteId);
		
		return result;
	}
	
	/**
	 * @Title: closeActivity
	 * @Description: 关闭活动并改变活动终止时间为当前时间
	 * @param voteId
	 * @param dataBaseUtil
	 * @return
	 */
	public int closeActivity(int voteId, DataBaseUtil dataBaseUtil){
		int result = -1;
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		
		result = dataBaseUtil.SqlExec("UPDATE vote_activity SET EndDate = " + 
		currentDate + ", VoteStatus = " + Constant.ACTIVITY_CLOSED_STATUS + " WHERE VoteId = " + voteId);
		
		return result;
	}
	
	/**
	 * @Title: deleteActivity
	 * @Description: 删除活动记录并删除相关记录表
	 * @param voteId
	 * @param dataBaseUtil
	 * @return
	 */
	public int deleteActivity(int voteId, DataBaseUtil dataBaseUtil){
		int result = 1;
		int vStatus = checkVoteStatus(voteId, dataBaseUtil);
		switch (vStatus) {
		case Constant.ACTIVITY_DRAFT_STATUS:
		case Constant.ACTIVITY_SAVE_STATUS:
			List<Integer> iList = selectItemsForId(voteId, dataBaseUtil);
			for(int i = 0; i < iList.size(); i++){
				result &= deleteItem(iList.get(i), dataBaseUtil);
			}
			result &= dataBaseUtil.SqlExec("DELETE FROM vote_activity WHERE VoteId = " + voteId);
			break;
		case Constant.ACTIVITY_CLOSED_STATUS:
			List<Integer> iList2 = selectItemsForId(voteId, dataBaseUtil);
			for (int i = 0; i < iList2.size(); i++) {
				result &= deleteItem(iList2.get(i), dataBaseUtil);
				result &= deleteChoice(iList2.get(i), dataBaseUtil);
			}
			int enableAdvice = checkEnableAdvice(voteId, dataBaseUtil);
			if (enableAdvice == 1) {
				result &= deleteAdvice(voteId, dataBaseUtil);
			}
			result &= dataBaseUtil.SqlExec("DELETE FROM vote_activity WHERE VoteId = " + voteId);
			break;
		default:
			System.out.println("illegal delete operation!");
			result = 0;
			break;
		}
		return result;
	}
	
	/**
	 * @Title: checkEnableAdvice
	 * @Description: 根据活动Id返回当前活动是否可留言信息
	 * @param voteId
	 * @param dataBaseUtil
	 * @return
	 */
	public int checkEnableAdvice(int voteId, DataBaseUtil dataBaseUtil) {
		int enableAdvice = -1;
		
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT EnableAdvice FROM vote_activity WHERE VoteId = " + voteId);
		ResultSet result = con.getResultSet();
		
		try {
			if (result.last()) {
				enableAdvice = result.getInt("EnableAdvice");
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return enableAdvice;
	}

	/**
	 * @Title: checkVoteStatus
	 * @Description: 根据活动Id返回活动当前状态
	 * @param voteId
	 * @param dataBaseUtil
	 * @return
	 */
	public int checkVoteStatus(int voteId, DataBaseUtil dataBaseUtil) {
		int vStatus = -1;
		
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT VoteStatus FROM vote_activity WHERE VoteId = " + voteId);
		ResultSet result = con.getResultSet();
		
		try {
			if (result.last()) {
				vStatus = result.getInt("VoteStatus");
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vStatus;
	}

	/**
	 * @Title: insertItem
	 * @Description: 向数据库中插入投票选项记录，并与投票活动记录绑定
	 * @param vItem
	 * @param voteId
	 * @param dataBaseUtil
	 * @return int itemIds
	 */
	public int insertItem(VoteItem vItem, int voteId, DataBaseUtil dataBaseUtil){
		int itemId = -1;
		vItem.setVoteId(voteId);
		
		itemId = dataBaseUtil.SqlExec("INSERT INTO vote_item VALUES (default, "
		+ voteId + ", '" + vItem.getItemImage() + "','" + vItem.getItemDesc() + "')");
		
		vItem.setItemId(itemId);
		return itemId;
	}
	
	/**
	 * @Title: deleteItem
	 * @Description: 删除抽奖奖项信息记录
	 * @param vItem
	 * @param dataBaseUtil
	 * @return int result 删除结果
	 */
	public int deleteItem(VoteItem vItem, DataBaseUtil dataBaseUtil) {
		int result = -1;
		
		result = dataBaseUtil.SqlExec("DELETE FROM vote_item WHERE ItemId = " + vItem.getItemId());
		
		return result;
	}
	
	/**
	 * @Title: deleteItem
	 * @Description: 删除抽奖奖项信息记录
	 * @param itemId
	 * @param dataBaseUtil
	 * @return int result 删除结果
	 */
	public int deleteItem(int itemId, DataBaseUtil dataBaseUtil) {
		int result = -1;
		
		result = dataBaseUtil.SqlExec("DELETE FROM vote_item WHERE ItemId = " + itemId);
		
		return result;
	}
	
	/**
	 * @Title: selectItems
	 * @Description: 根据活动Id查找对应的活动选项记录
	 * @param voteId
	 * @param dataBaseUtil
	 * @return
	 */
	public List<VoteItem> selectItems(int voteId, DataBaseUtil dataBaseUtil){
		List<VoteItem> viList = new ArrayList<VoteItem>();
		
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT * FROM vote_item WHERE VoteId = " + voteId);
		ResultSet result = con.getResultSet();
		try {
			while (result.next()) {
				VoteItem temp = new VoteItem();
				temp.setItemId(result.getInt("ItemId"));
				temp.setVoteId(voteId);
				temp.setItemImage(result.getString("ItemImage"));
				temp.setItemDesc(result.getString("ItemDesc"));
				viList.add(temp);
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return viList;
	}

	/**
	 * @Title: selectItemsForId
	 * @Description: 根据活动Id查找对应的活动选项记录,并返回Id列表
	 * @param voteId
	 * @param dataBaseUtil
	 * @return
	 */
	public List<Integer> selectItemsForId(int voteId, DataBaseUtil dataBaseUtil){
		List<Integer> iList = new ArrayList<Integer>();
		
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT ItemId FROM vote_item WHERE VoteId = " + voteId);
		ResultSet result = con.getResultSet();
		try {
			while (result.next()) {
				iList.add(result.getInt("ItemId"));
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return iList;
	}
	
	/**
	 * @Title: insertChoice
	 * @Description: 插入一条用户选择记录
	 * @param choice
	 * @param dataBaseUtil
	 * @return int choiceId
	 */
	public int insertChoice(Choice choice, DataBaseUtil dataBaseUtil){
		int choiceId = -1;
		
		choiceId = dataBaseUtil.SqlExec("INSERT INTO vote_item_choice VALUES (default, " 
		+ choice.getItemId() + ", '" + choice.getOpenId() + "')");
		choice.setChoiceId(choiceId);
		
		return choiceId;
	}
	
	/**
	 * @Title: insertChoices
	 * @Description: 插入一组用户的选择记录
	 * @param cList
	 * @param dataBaseUtil
	 * @return List<Integer> choiceId[]
	 */
	public List<Integer> insertChoices(List<Choice> cList, DataBaseUtil dataBaseUtil){
		List<Integer> iList = new ArrayList<Integer>();
		
		for (int i = 0; i < cList.size(); i++) {
			int temp = insertChoice(cList.get(i), dataBaseUtil);
			iList.add(temp);
		}
		
		return iList;
	}
	
	/**
	 * @Title: deleteChoice
	 * @Description: 根据选项Id删除相关的用户选择记录
	 * @param itemId
	 * @param dataBaseUtil
	 * @return
	 */
	public int deleteChoice(int itemId, DataBaseUtil dataBaseUtil){
		int result = -1;
		
		result = dataBaseUtil.SqlExec("DELETE FROM vote_item_choice WHERE ItemId = " + itemId);
		
		return result;
	}
	
	/**
	 * @Title: selectChoices
	 * @Description: 查找一条投票选项对应的选择记录
	 * @param itemId
	 * @param dataBaseUtil
	 * @return
	 */
	public List<Choice> selectChoices(int itemId, DataBaseUtil dataBaseUtil){
		List<Choice> cList = new ArrayList<Choice>();
		
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT * FROM vote_item_choice WHERE ItemId = " + itemId);
		ResultSet result =con.getResultSet();
        
		try {
			while(result.next()){
				Choice temp = new Choice();
				temp.setChoiceId(result.getInt("ChoiceId"));
				temp.setItemId(result.getInt("ItemId"));
				temp.setOpenId(result.getString("OpenId"));
				cList.add(temp);
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cList;
	}
	
	/**
	 * @Title: selectChoicesForNum
	 * @Description: 查找一条投票选项对应的选择记录数
	 * @param itemId
	 * @param dataBaseUtil
	 * @return
	 */
	public int selectChoicesForNum(int itemId, DataBaseUtil dataBaseUtil){
		int count = 0;
		
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT * FROM vote_item_choice WHERE ItemId = " + itemId);
		ResultSet result =con.getResultSet();
        
		try {
			if(result.last()){
				count = result.getRow();
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	/**
	 * @Title: insertAdvice
	 * @Description: 插入一条用户评论记录
	 * @param advice
	 * @param dataBaseUtil
	 * @return
	 */
	public int insertAdvice(Advice advice, DataBaseUtil dataBaseUtil){
		int adviceId = -1;
		
		adviceId = dataBaseUtil.SqlExec("INSERT INTO vote_advice VALUES (default, " 
		+ advice.getVoteId() + ", '" + advice.getOpenId() + "','" + advice.getAdviceContent() + "')");
		advice.setAdviceId(adviceId);
		
		return adviceId;
	}
	
	/**
	 * @Title: deleteAdvice
	 * @Description: 根据活动Id删除活动相关的评论信息
	 * @param voteId
	 * @param dataBaseUtil
	 * @return
	 */
	public int deleteAdvice(int voteId, DataBaseUtil dataBaseUtil){
		int result = -1;
		
		result = dataBaseUtil.SqlExec("DELETE FROM vote_advice WHERE VoteId = " + voteId);
		
		return result;
	}
	/**
	 * @Title: selectAdvicesByVoteId
	 * @Description: 根据活动Id查找相对应的所有评论记录
	 * @param voteId
	 * @param dataBaseUtil
	 * @return
	 */
	public List<Advice> selectAdvicesByVoteId(int voteId, DataBaseUtil dataBaseUtil){
		List<Advice> aList = new ArrayList<Advice>();
		
		ConnectionAndResultSet con = dataBaseUtil.SqlQuery("SELECT * FROM vote_advice WHERE VoteId = " + voteId);
		ResultSet result = con.getResultSet();
		
		try {
			while(result.next()){
				Advice advice = new Advice();
				advice.setAdviceId(result.getInt("AdviceId"));
				advice.setVoteId(result.getInt("VoteId"));
				advice.setOpenId(result.getString("OpenId"));
				advice.setAdviceContent(result.getString("AdviceContent"));
				aList.add(advice);
			}
			result.close();
			con.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aList;
	}
	
	public static void main(String[] args){
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		Timestamp startDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = new Timestamp(System.currentTimeMillis() + 36000000);
		VoteItem t1 = new VoteItem("images/test.png","hhaha");
		VoteItem t2 = new VoteItem("images/test2.png","haha");
		List<VoteItem> viList = new ArrayList<VoteItem>();
		viList.add(t1);
		viList.add(t2);
		VoteActivity test = new VoteActivity("one", "测试一", "", startDate, endDate, 1, 3, 1, viList);
		Vendor testVendor = new Vendor();
		int id = testVendor.insertActivity(test, Constant.ACTIVITY_SAVE_STATUS, dataBaseUtil);
		
		VoteActivity test2 = new VoteActivity(5, "three", "测试三", "", startDate, endDate, 0, 1, 0, viList);
	//	int id = testVendor.updateActivity(test2, Constant.ACTIVITY_RELEASE_STATUS, dataBaseUtil);
		
		System.out.println(id);
		
		List<VoteActivity> test3 = testVendor.selectActivities(dataBaseUtil);
		Gson gson = new Gson();
		String jsonString = gson.toJson(test3);
		System.out.println(jsonString);
	}
}
