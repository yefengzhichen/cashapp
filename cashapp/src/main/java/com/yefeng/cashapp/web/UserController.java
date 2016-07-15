package com.yefeng.cashapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yefeng.cashapp.dao.ProductDao;
import com.yefeng.cashapp.dao.UserDao;
import com.yefeng.cashapp.model.Product;
import com.yefeng.cashapp.model.User;

/**
 * @author yefengzhichen
 * 2016Äê7ÔÂ15ÈÕ
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterForm() {
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegister(User user) {
		userDao.save(user);
		return "redirect:/user/" + user.getName();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(User user, Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(User user, Model model) {
		boolean valid = userDao.isValid(user);
		if (!valid) {
			return "redirect:/user/login";
		}
		/*List<Product> productList = productDao.getAll();
		model.addAttribute(productList);
		String discountList = "";
		for (int i = 0; i < productList.size(); ++i) {
			Product product = productList.get(i);
			if (product.isDiscount()) {
				discountList += product.getBarcode();
				if (i != productList.size()) {
					discountList += ", ";
				}
			}
		}
		String detail = "The input product barcode is null";
		model.addAttribute(discountList);
		model.addAttribute(detail);*/
		return "redirect:/start";
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String showBloggerProfile(@PathVariable String name, Model model) {
		User user = userDao.get(name);
		model.addAttribute(user);
		return "profile";
	}
}
