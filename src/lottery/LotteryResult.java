package lottery;

/**
 * @Title: LotteryResult
 * @Description: 用户抽奖结果信息
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月12日
 */
public class LotteryResult {
	private String prizeName;
	private String prizeContent;
	private int chanceNum;
	
	/**
	 * @category constructor()
	 */
	public LotteryResult(){
		
	}
	
	public LotteryResult(String prizeName, String prizeContent, int chanceNum){
		this.prizeName = prizeName;
		this.prizeContent = prizeContent;
        this.chanceNum = chanceNum;
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
	 * @return the prizeContent
	 */
	public String getPrizeContent() {
		return prizeContent;
	}
	/**
	 * @param prizeContent the prizeContent to set
	 */
	public void setPrizeContent(String prizeContent) {
		this.prizeContent = prizeContent;
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
}
