package lottery;

/**
 * @Title: LotteryPrize
 * @Description: 奖品活动信息配置
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月11日
 */
public class LotteryPrize {
	private String prizeName;
	private String prizeContent;
	private int luckyNum;
	private double luckyPercent;
	
	/**
	 * @category constructor()
	 */
	public LotteryPrize(String prizeName, String prizeContent, int luckyNum, 
			double luckyPercent){
		this.prizeName = prizeName;
		this.prizeContent = prizeContent;
		this.luckyNum = luckyNum;
		this.luckyPercent = luckyPercent;
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
	public void setPrizeName(String prizeNameString) {
		this.prizeName = prizeNameString;
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
	
/*	public static void main(String[] args){
		LotteryPrize lp = new LotteryPrize("特等奖", "iphone5s 一部", 3, 0.0001);
		LotteryPrize lp2 = new LotteryPrize("一等奖", "香港三日游旅游券1张", 30, 0.05);
		List<LotteryPrize> list = new ArrayList<LotteryPrize>();
		list.add(lp);
		list.add(lp2);
		Gson gson = new Gson();
		String jsonRepresentation = gson.toJson(list);
        System.out.println(jsonRepresentation);
	}*/
}
