package tools;

/**
 * @Title: Constant
 * @Description: define global constant
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月5日
 */
public final class Constant {
	/*database*/
	public static final String gDataBaseName = "stuka";
	public static final String gDataBaseUrl = "jdbc:mysql://localhost:3306/"+gDataBaseName;
	public static final String gDbUserName = "root";
	public static final String gDbPwd = "root";
	
	/*image*/
	public static final String IMAGE_ACTUAL_PATH = "./WebContent/images/";
	public static final String IMAGE_ACCESS_PATH = "/images/";
	public static final String IMAGE_TYPE_GIF = "gif";
	public static final String IMAGE_TYPE_JPG = "jpg";
	public static final String IMAGE_TYPE_JPEG = "jpeg";
	public static final String IMAGE_TYPE_BMP = "bmp";  
	public static final String IMAGE_TYPE_PNG = "png";
    public static final String IMAGE_TYPE_PSD = "psd";   // photoshop
}
