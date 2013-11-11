package tools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;



/**
 * @Title: ImageUtil
 * @Description: Provide frequently used functions in image processing 
 * @Company: ZhongHe
 * @author ben
 * @date 2013年11月7日
 */
public class ImageUtil {
	/**
	 * @Title: scaleRatio
	 * @Description: 按比例缩放图像
	 * @param srcImage 源图像输入流
	 * @param serverPath 服务器上资源路径
	 * @param scale 缩放比例 
	 * @return String 最终图像存放地址
	 */
	public final static String scaleRatio(InputStream srcImage, String serverPath, double ratio){
		String destImagePath = null;
		try {
			// 获取源图像长宽
			BufferedImage src = ImageIO.read(srcImage);		
			int width = src.getWidth();
			int height = src.getHeight();
			
			// 缩放长宽
			width = (int) (width * ratio);
			height = (int) (height * ratio);
			
			// 生成新的图像 
			Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			BufferedImage img = drawPicture(width, height, image);
			
			// 保存新图像到动态生成地址，并设置用户访问路径
			destImagePath = saveNewImage(img, serverPath, Constant.IMAGE_TYPE_PNG);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return destImagePath;
	}

	/**
	 * @Title: scaleCut
	 * @Description: 按宽高缩放图像(裁剪法)
	 * @param srcImage 源图像输入流
	 * @param serverPath 服务器上资源路径
	 * @param destHeight 目标图像高度
	 * @param destWidth 目标图像宽度
	 * @return String 最终图像存放地址
	 */
	public final static String scaleCut(InputStream srcImage, String serverPath, int destWidth, int destHeight){
		String destImagePath = null;
		try {
			// 获取源图像长宽
			BufferedImage src = ImageIO.read(srcImage);
			int srcWidth = src.getWidth();
			int srcHeight = src.getHeight();
			
			// 源图像等比列缩小或放大后的过渡图像宽高
			int transWidth = 0;
			int transHeight = 0;
			
			if((double)srcWidth / srcHeight > (double)destWidth / destHeight){ // 以高为基准缩放
				transWidth = (int) (((double)destHeight / srcHeight) * srcWidth);
				transHeight = destHeight;
				// 若高度一致，则宽度必然大于目标宽度，将其等分成两份
				int halfExcessWidth = (transWidth - destWidth) / 2;
				
				// 生成过渡图像
				Image image = src.getScaledInstance(transWidth, transHeight, 
						Image.SCALE_DEFAULT);
				BufferedImage transImg = drawPicture(transWidth, transHeight, image);

				// 过渡图像宽度大于目标图像，截去多余部分
				CropImageFilter cropImageFilter = new CropImageFilter(halfExcessWidth, 0, 
						transWidth - halfExcessWidth * 2, transHeight);
				image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(
						transImg.getScaledInstance(transWidth, transHeight, 
								Image.SCALE_DEFAULT).getSource(), cropImageFilter));
				transImg = drawPicture(transWidth - halfExcessWidth * 2, transHeight, image);

				// 保存新图像到动态生成地址，并设置用户访问路径
				destImagePath = saveNewImage(transImg, serverPath, Constant.IMAGE_TYPE_PNG);
			}
			else { // 以宽为基准缩放
				transWidth = destWidth;
				transHeight = (int) (((double)destWidth / srcWidth) * srcHeight);
				// 若宽度一致，则高度必然大于目标高度，将其等分成两份
				int halfExcessHeight = (transHeight - destHeight) / 2;
				
				// 生成过渡图像
				Image image = src.getScaledInstance(transWidth, transHeight, 
						Image.SCALE_DEFAULT);
				BufferedImage transImg = drawPicture(transWidth, transHeight, image);

				// 过渡图像高度大于目标图像，截去多余部分
				CropImageFilter cropImageFilter = new CropImageFilter(0, halfExcessHeight, 
						transWidth, transHeight - halfExcessHeight * 2);
				image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(
						transImg.getScaledInstance(transWidth, transHeight, 
								Image.SCALE_DEFAULT).getSource(), cropImageFilter));
				transImg = drawPicture(transWidth, transHeight - halfExcessHeight * 2, image);
				
				// 保存新图像到动态生成地址，并设置用户访问路径
				destImagePath = saveNewImage(transImg, serverPath, Constant.IMAGE_TYPE_PNG);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return destImagePath;
	}
	
	/**
	 * @Title: scaleFill
	 * @Description: 按宽高缩放图像(填补空白法)
	 * @param srcImage 源图像输入流
	 * @param serverPath 服务器上资源路径
	 * @param destWidth 目标图像宽度
	 * @param destHeight 目标图像高度
	 * @return String 最终图像存放地址
	 */
	public final static String scaleFill(InputStream srcImage, String serverPath, int destWidth, int destHeight){
		String destImagePath = null;
		try {
			// 获取源图像长宽,定义最小缩放比率
			BufferedImage src = ImageIO.read(srcImage);
			int srcWidth = src.getWidth();
			int srcHeight = src.getHeight();
			double ratio = (double)destWidth / srcWidth < (double)destHeight / srcHeight ? 
					((double)destWidth / srcWidth) : ((double)destHeight / srcHeight);
			
			// 线性缩放加补白			
			Image image = src.getScaledInstance(destWidth, destHeight, Image.SCALE_DEFAULT);			
            AffineTransformOp op = new AffineTransformOp(AffineTransform
            		.getScaleInstance(ratio, ratio), null);
			image = op.filter(src, null);
			BufferedImage img = new BufferedImage(destWidth, destHeight, 
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gra = img.createGraphics();
			img = gra.getDeviceConfiguration().createCompatibleImage(destWidth, destHeight, 
					Transparency.TRANSLUCENT);
			gra.dispose();
			gra = img.createGraphics();
            if (destWidth == image.getWidth(null))
                gra.drawImage(image, 0, (destHeight - image.getHeight(null)) / 2,
                        image.getWidth(null), image.getHeight(null), null);
            else
                gra.drawImage(image, (destWidth - image.getWidth(null)) / 2, 0,
                        image.getWidth(null), image.getHeight(null), null);
            gra.dispose();
			
			// 保存新图像到动态生成地址，并设置用户访问路径
			destImagePath = saveNewImage(img, serverPath, Constant.IMAGE_TYPE_PNG); 		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return destImagePath;	
	}
	
	/**
	 * @Title: getLength
	 * @Description: detect the length of one sentence
	 * @param text
	 * @return int --the length
	 */
    public final static int getLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (new String(text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return (int) Math.round(length / 2.0);
    }
    
	/**
	 * @Title: drawImage
	 * @Description: draw image automatic
	 * @param width
	 * @param height
	 * @param image
	 * @return BufferedImage 
	 */
	private static BufferedImage drawPicture(int width, int height,
			Image image) {
		// TODO Auto-generated method stub
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = newImage.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		return newImage;
	}
	
	/**
	 * @Title: generateRandomImageName
	 * @Description: generate image name randomly
	 * @param imageType
	 * @return String
	 */
	private static String generateRandomImageName(String imageType) {
		// TODO Auto-generated method stub
		long current = System.currentTimeMillis();
		Random rand = new Random();
		int random = rand.nextInt(10000000);
		String randomImageName = current + String.format("%07d", random) + "." + imageType;
		return randomImageName;
	}
	
    /**
     * @Title: saveNewImage
     * @Description: save processed image and return its access path
     * @param img
     * @param rsPath --the path of resource in server
     * @param imageType
     * @return String
     * @throws IOException
     */
	private static String saveNewImage(BufferedImage img, String rsPath, String imageType) throws IOException {
		// TODO Auto-generated method stub
		String imageName = generateRandomImageName(imageType);
		if (OSUtil.isWindows()) {
			ImageIO.write(img, imageType, new File(rsPath + Constant.IMAGE_WINDOWS_PATH
					+ imageName));
		}
		else {
			ImageIO.write(img, imageType, new File(rsPath + Constant.IMAGE_NORMAL_PATH
					+ imageName));
		}
		return Constant.IMAGE_NORMAL_PATH + imageName;
	}
	
}

