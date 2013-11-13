package lottery;

/**
 * @Title: PrizeInfo
 * @Description: 奖品信息配置，用于后台记录、查询
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月12日
 */
public class PrizeInfo {
	private int prizeId;
	private String prizeName;
	private String prizeContent;
	private int luckyNum;
	private double luckyPercent;
	
	/**
	 * @category constructor()
	 */
	public PrizeInfo(){
		
	}
	
	public PrizeInfo(int prizeId, String prizeName, String prizeContent, int luckyNum, 
			double luckyPercent){
		this.prizeId = prizeId;
		this.prizeName = prizeName;
		this.prizeContent = prizeContent;
		this.luckyNum = luckyNum;
		this.luckyPercent = luckyPercent;
	}
	/**
	 * @return the prizeId
	 */
	public int getPrizeId() {
		return prizeId;
	}
	/**
	 * @param prizeId the prizeId to set
	 */
	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
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
	 * @return the luckyNum
	 */
	public int getLuckyNum() {
		return luckyNum;
	}
	/**
	 * @param luckyNum the luckyNum to set
	 */
	public void setLuckyNum(int luckyNum) {
		this.luckyNum = luckyNum;
	}
	/**
	 * @return the luckyPercent
	 */
	public double getLuckyPercent() {
		return luckyPercent;
	}
	/**
	 * @param luckyPercent the luckyPercent to set
	 */
	public void setLuckyPercent(double luckyPercent) {
		this.luckyPercent = luckyPercent;
	}
}
