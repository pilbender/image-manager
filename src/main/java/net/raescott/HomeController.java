package net.raescott;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
@Controller(value = "/")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired ImageUtils imageUtils;
	@Autowired Properties config;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage() {
		logger.debug("*** Rendering homePage ***");

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String uploadImage(@RequestParam(value = "name", required = true) String name,
							  @RequestParam(value = "image", required = true) MultipartFile image) throws IOException {
		logger.debug("uploadImage: {}", name);

		ImageDto imageDto = new ImageDto();
		//LobContent image = new LobContent(file.getContentType(), file.getOriginalFilename(), file.getSize(), file.getBytes());

		/*
		if (image.getSize() > 0) {
			Image rawImage = Toolkit.getDefaultToolkit().createImage(image.getBytes());
			BufferedImage bufferedImage = imageUtils.toBufferedImage(rawImage);
			if (bufferedImage.getHeight() > maxImageHeight || bufferedImage.getWidth() > maxImageWidth) {
				bufferedImage = Crop.scalePreserveAspectRatio(bufferedImage, maxImageWidth, maxImageHeight);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "PNG", baos);
				byte[] bytesOut = baos.toByteArray();
				image.setContentType("image/png");
				image.setContentSize(bytesOut.length);
				image.setData(bytesOut);
		}
		*/
		Image rawImage = Toolkit.getDefaultToolkit().createImage(image.getBytes());
		BufferedImage bufferedImage = imageUtils.toBufferedImage(rawImage);

		String outputFilenameWithoutExtension = imageUtils.getFilenameWithoutExtension(name);
		String originalFilename = image.getOriginalFilename();
		String extension = imageUtils.getFilenameExtension(originalFilename); // Should be jpg, png, or gif
		StringBuilder outputFilename = new StringBuilder();
		outputFilename.append(outputFilenameWithoutExtension).append(".").append(extension);
		File outputFile = new File(outputFilename.toString());

		ImageIO.write(bufferedImage, extension, outputFile);

		return "home";
	}
}
