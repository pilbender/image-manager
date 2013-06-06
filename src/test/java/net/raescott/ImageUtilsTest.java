package net.raescott;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-context.xml"})
public class ImageUtilsTest {

	@Resource(name = "imageUtils")
	ImageUtils imageUtils;

	@Test
	@Ignore
	public void testToBufferedImage() throws Exception {

	}

	@Test
	@Ignore
	public void testHasAlpha() throws Exception {

	}

	@Test
	public void testGetFilenameExtension() throws Exception {
		System.out.println("getFilenameExtension");

		String result = imageUtils.getFilenameExtension("test.jpg");
		assertEquals("jpg", result);

		result = imageUtils.getFilenameExtension("test");
		assertEquals("", result);
	}

	@Test
	public void testGetFilenameWithoutExtension() throws Exception {
		System.out.println("getFilenameWithoutExtension");

		String result = imageUtils.getFilenameWithoutExtension("test.jpg");
		assertEquals("test", result);

		result = imageUtils.getFilenameWithoutExtension("test");
		assertEquals("test", result);
	}
}
