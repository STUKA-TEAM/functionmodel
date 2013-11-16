package vote;

/**
 * @Title: Choice
 * @Description: 标识用户投票的一种选择
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月14日
 */
public class Choice {
	private int choiceId;
	private int itemId;
	private String openId;
	
	/**
	 * @category constructor()
	 */
	public Choice(){
		
	}
	
	public Choice(int itemId, String openId){
		this.itemId = itemId;
		this.openId = openId;
		
		this.choiceId = -1;
	}

	/**
	 * @return the choiceId
	 */
	public int getChoiceId() {
		return choiceId;
	}

	/**
	 * @param choiceId the choiceId to set
	 */
	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
