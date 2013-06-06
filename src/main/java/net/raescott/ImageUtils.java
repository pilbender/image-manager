package net.raescott;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
@Service
public class ImageUtils {
	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	/**
	 * Take a raw image and convert it to a <code>BufferedImage</code>.  <code>BufferedImage</code> is the central
	 * image format within the Java APIs.
	 * @param image
	 * @return
	 */
	public BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		image = new ImageIcon(image).getImage();
		boolean hasAlpha = hasAlpha(image);
		BufferedImage bufferedImage = null;
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}
			GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
			GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
			bufferedImage = graphicsConfiguration.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			logger.error("Image processing error, perhaps you need to add: JAVA_OPTS=\"-Djava.awt.headless=true to your Tomcat installation's catalina.sh\"");
			logger.error(e.getMessage());
		}
		if (bufferedImage == null) {
			int type = BufferedImage.TYPE_INT_RGB;
			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		}
		Graphics graphics = bufferedImage.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		return bufferedImage;
	}

	/**
	 * Check for opacity.  If there is no alpha, the opacity is one.  We do this by only
	 * checking the upper left pixel.  If there is an alpha, we return true.  Otherwise
	 * there is no alpha and we return false.
	 *
	 * @param image
	 * @return
	 */
	public boolean hasAlpha(Image image) {
		if (image instanceof BufferedImage) {
			BufferedImage bufferedImage = (BufferedImage) image;
			return bufferedImage.getColorModel().hasAlpha();
		}
		// Grab the upper left pixel of the image, we only need one.
		PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, 1, 1, false);
		try {
			pixelGrabber.grabPixels();
		} catch (InterruptedException e) {
		}
		ColorModel colorModel = pixelGrabber.getColorModel();
		return colorModel.hasAlpha();
	}

	/**
	 * Return the filename extension for a given file.  If the file has not extension, an
	 * empty string is returned.
	 *
	 * @param name
	 * @return
	 */
	public String getFilenameExtension(String name) {
		if (name.contains(".")) {
			Integer extensionIndex = name.lastIndexOf('.') + 1;
			return name.substring(extensionIndex);
		} else {
			return "";
		}
	}

	/**
	 * Return the filename without it's extension for a given file name.
	 * @param name
	 * @return
	 */
	public String getFilenameWithoutExtension(String name) {
		if (name.contains(".")) {
			Integer extensionIndex = name.lastIndexOf('.');
			return name.substring(0, extensionIndex);
		} else {
			return name;
		}
	}
}
