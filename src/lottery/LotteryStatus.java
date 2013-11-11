package lottery;

/**
 * @Title: LotteryStatus
 * @Description: 商家查看抽奖活动状态信息
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月11日
 */
public class LotteryStatus {
	private int lotteryId;
	private String lotteryName;
	private int lotteryStatus;
	
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
}
