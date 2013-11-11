package song;

public class Song {
	private int id;
	private int styleId;
	private String linkString;
	public Song(){}
	public Song(int id, int styleId, String linksString){
		this.id = id;
		this.styleId = styleId;
		this.linkString = linksString;
	}
	public int getStyleId() {
		return styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}
	public String getLinkString() {
		return linkString;
	}
	public void setLinkString(String linkString) {
		this.linkString = linkString;
	}
	public int getId() {
		return id;
	}
	
	
}
