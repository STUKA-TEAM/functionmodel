package vote;

/**
 * @Title: VoteItem
 * @Description: 投票活动选项信息
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月14日
 */
public class VoteItem {
	private int itemId;
	private int voteId;
	private String itemImage;
	private String itemDesc;
	
	/**
	 * @category constructor
	 */
	public VoteItem(){
		
	}
	public VoteItem(String itemImage, String itemDesc){
		this.itemImage = itemImage;
		this.itemDesc = itemDesc;
		
		this.voteId = -1;
		this.itemId = -1;
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
	 * @return the itemImage
	 */
	public String getItemImage() {
		return itemImage;
	}
	/**
	 * @param itemImage the itemImage to set
	 */
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	/**
	 * @return the itemDesc
	 */
	public String getItemDesc() {
		return itemDesc;
	}
	/**
	 * @param itemDesc the itemDesc to set
	 */
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
}
