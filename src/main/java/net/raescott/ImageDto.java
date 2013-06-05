package net.raescott;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
public class ImageDto implements Serializable {
	public static final long serialVersionUID = 1l;
	private String name;
	private MultipartFile image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
