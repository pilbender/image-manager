package net.raescott;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
@Controller(value = "/")
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Model model) {
		return "home";
	}
}
