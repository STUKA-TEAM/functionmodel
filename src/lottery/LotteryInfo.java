package lottery;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Title: LotteryInfo
 * @Description: 抽奖活动全部信息
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月11日
 */
public class LotteryInfo {
	private int lotteryId;
	private String lotteryName;
	private String lotterySummary;
	private String lotteryPicture;
	private Timestamp startDate;
	private Timestamp endDate;
	private int chanceNum;
	private int lotteryStatus;
	private List<LotteryPrize> prizeList;
	
	/**
	 * @category constructor()
	 */
	public LotteryInfo(int lotteryId, String lotteryName, String lotterySummary,
			String lotteryPicture, Timestamp startDate, Timestamp endDate, int chanceNum, 
			int lotteryStatus, List<LotteryPrize> prizeList){
		this.lotteryId = lotteryId;
		this.lotteryName = lotteryName;
		this.lotterySummary = lotterySummary;
		this.lotteryPicture = lotteryPicture;
		this.startDate = startDate;
		this.endDate = endDate;
		this.chanceNum = chanceNum;
		this.lotteryStatus = lotteryStatus;
		this.prizeList = prizeList;
	}
	/**
	 * @return the lotteryId
	 */
	public int getLotteryId() {
		return lotteryId;
	}
	/**
	 * @param lotteryId the lotteryId to set
	 */
	public void setLotteryId(int lotteryId) {
		this.lotteryId = lotteryId;
	}
	/**
	 * @return the lotteryName
	 */
	public String getLotteryName() {
		return lotteryName;
	}
	/**
	 * @param lotteryName the lotteryName to set
	 */
	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
	}
	/**
	 * @return the lotterySummary
	 */
	public String getLotterySummary() {
		return lotterySummary;
	}
	/**
	 * @param lotterySummary the lotterySummary to set
	 */
	public void setLotterySummary(String lotterySummary) {
		this.lotterySummary = lotterySummary;
	}
	/**
	 * @return the lotteryPicture
	 */
	public String getLotteryPicture() {
		return lotteryPicture;
	}
	/**
	 * @param lotteryPicture the lotteryPicture to set
	 */
	public void setLotteryPicture(String lotteryPicture) {
		this.lotteryPicture = lotteryPicture;
	}
	/**
	 * @return the startDate
	 */
	public Timestamp getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Timestamp getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the chanceNum
	 */
	public int getChanceNum() {
		return chanceNum;
	}
	/**
	 * @param chanceNum the chanceNum to set
	 */
	public void setChanceNum(int chanceNum) {
		this.chanceNum = chanceNum;
	}
	/**
	 * @return the lotteryStatus
	 */
	public int getLotteryStatus() {
		return lotteryStatus;
	}
	/**
	 * @param lotteryStatus the lotteryStatus to set
	 */
	public void setLotteryStatus(int lotteryStatus) {
		this.lotteryStatus = lotteryStatus;
	}
	/**
	 * @return the lpList
	 */
	public List<LotteryPrize> getLpList() {
		return prizeList;
	}
	/**
	 * @param lpList the lpList to set
	 */
	public void setLpList(List<LotteryPrize> prizeList) {
		this.prizeList = prizeList;
	}
		
}
