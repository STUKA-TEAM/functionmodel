package lottery;

/**
 * @Title: LotteryStatus
 * @Description: 抽奖活动当前中奖人数信息
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月13日
 */
public class LotteryStatus {
	private String prizeName;
	private int currentLuckyNum;
	
	/**
	 * @category constructor()
	 */
	public LotteryStatus(){
		
	}
	public LotteryStatus(String prizeName, int currentLuckyNum) {
		// TODO Auto-generated constructor stub
		this.setPrizeName(prizeName);
		this.setCurrentLuckyNum(currentLuckyNum);
	}
	/**
	 * @return the prizeName
	 */
	public String getPrizeName() {
		return prizeName;
	}
	/**
	 * @param prizeName the prizeName to set
	 */
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	/**
	 * @return the currentLuckyNum
	 */
	public int getCurrentLuckyNum() {
		return currentLuckyNum;
	}
	/**
	 * @param currentLuckyNum the currentLuckyNum to set
	 */
	public void setCurrentLuckyNum(int currentLuckyNum) {
		this.currentLuckyNum = currentLuckyNum;
	}
	
}
