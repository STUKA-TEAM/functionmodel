package vote;

/**
 * @Title: Advice
 * @Description: 标识用户的一次留言
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月14日
 */
public class Advice {
	private int adviceId;
	private int voteId;
	private String openId;
	private String adviceContent;
	
	/**
	 * @category constructor()
	 */
	public Advice(){
		
	}
	
	public Advice(int voteId, String openId, String adviceContent){
		this.voteId = voteId;
		this.openId = openId;
		this.adviceContent = adviceContent;
		
		this.adviceId = -1;
	}

	/**
	 * @return the adviceId
	 */
	public int getAdviceId() {
		return adviceId;
	}

	/**
	 * @param adviceId the adviceId to set
	 */
	public void setAdviceId(int adviceId) {
		this.adviceId = adviceId;
	}

	/**
	 * @return the voteId
	 */
	public int getVoteId() {
		return voteId;
	}

	/**
	 * @param voteId the voteId to set
	 */
	public void setVoteId(int voteId) {
		this.voteId = voteId;
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

	/**
	 * @return the adviceContent
	 */
	public String getAdviceContent() {
		return adviceContent;
	}

	/**
	 * @param adviceContent the adviceContent to set
	 */
	public void setAdviceContent(String adviceContent) {
		this.adviceContent = adviceContent;
	}
}
