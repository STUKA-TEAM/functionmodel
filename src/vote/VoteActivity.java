package vote;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Title: VoteActivity
 * @Description: 投票活动全部信息
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月14日
 */
public class VoteActivity {
	private int voteId;
	private String voteTitle;
	private String voteSummary;
	private String votePicture;
	private Timestamp startDate;
	private Timestamp endDate;
	private int isMultiChoice;
	private int maxChoice;
	private int enableAdvice;
	private int voteStatus;
	private List<VoteItem> viList;
	
	/**
	 * @category constructor()
	 */
	public VoteActivity(){
		
	}
	public VoteActivity(String voteTitle, String voteSummary, String votePicture, 
			Timestamp startDate, Timestamp endDate, int isMultiChoice, int maxChoice, 
			int enableAdvice, List<VoteItem> viList){
		this.voteTitle = voteTitle;
		this.voteSummary = voteSummary;
		this.votePicture = votePicture;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isMultiChoice = isMultiChoice;
		this.maxChoice = maxChoice;
		this.enableAdvice = enableAdvice;
		this.viList = viList;
		
		this.voteId = -1;
		this.voteStatus = -1;
	}
	public VoteActivity(int voteId, String voteTitle, String voteSummary, String votePicture, 
			Timestamp startDate, Timestamp endDate, int isMultiChoice, int maxChoice, 
			int enableAdvice, List<VoteItem> viList){
		this.voteTitle = voteTitle;
		this.voteSummary = voteSummary;
		this.votePicture = votePicture;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isMultiChoice = isMultiChoice;
		this.maxChoice = maxChoice;
		this.enableAdvice = enableAdvice;
		this.viList = viList;		
		this.voteId = voteId;
		
		this.voteStatus = -1;
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
	 * @return the voteTitle
	 */
	public String getVoteTitle() {
		return voteTitle;
	}
	/**
	 * @param voteTitle the voteTitle to set
	 */
	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}
	/**
	 * @return the voteSummary
	 */
	public String getVoteSummary() {
		return voteSummary;
	}
	/**
	 * @param voteSummary the voteSummary to set
	 */
	public void setVoteSummary(String voteSummary) {
		this.voteSummary = voteSummary;
	}
	/**
	 * @return the votePicture
	 */
	public String getVotePicture() {
		return votePicture;
	}
	/**
	 * @param votePicture the votePicture to set
	 */
	public void setVotePicture(String votePicture) {
		this.votePicture = votePicture;
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
	 * @return the isMultiChoice
	 */
	public int getIsMultiChoice() {
		return isMultiChoice;
	}
	/**
	 * @param isMultiChoice the isMultiChoice to set
	 */
	public void setIsMultiChoice(int isMultiChoice) {
		this.isMultiChoice = isMultiChoice;
	}
	/**
	 * @return the maxChoice
	 */
	public int getMaxChoice() {
		return maxChoice;
	}
	/**
	 * @param maxChoice the maxChoice to set
	 */
	public void setMaxChoice(int maxChoice) {
		this.maxChoice = maxChoice;
	}
	/**
	 * @return the enableAdvice
	 */
	public int getEnableAdvice() {
		return enableAdvice;
	}
	/**
	 * @param enableAdvice the enableAdvice to set
	 */
	public void setEnableAdvice(int enableAdvice) {
		this.enableAdvice = enableAdvice;
	}
	/**
	 * @return the voteStatus
	 */
	public int getVoteStatus() {
		return voteStatus;
	}
	/**
	 * @param voteStatus the voteStatus to set
	 */
	public void setVoteStatus(int voteStatus) {
		this.voteStatus = voteStatus;
	}
	/**
	 * @return the viList
	 */
	public List<VoteItem> getViList() {
		return viList;
	}
	/**
	 * @param viList the viList to set
	 */
	public void setViList(List<VoteItem> viList) {
		this.viList = viList;
	}
	
}
