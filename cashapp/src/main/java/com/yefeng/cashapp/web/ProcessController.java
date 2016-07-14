package com.yefeng.cashapp.web;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yefeng.cashapp.dao.ProductDao;
import com.yefeng.cashapp.dao.UserDao;
import com.yefeng.cashapp.model.Product;
import com.yefeng.cashapp.model.User;
import com.yefeng.cashapp.service.ProcessService;

@Controller
@RequestMapping(value = "/start")
public class ProcessController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProcessService processService;
	

	@RequestMapping(method = RequestMethod.GET)
	public String shopApp(Model model) {
		List<Product> productList = productDao.getAll();
		model.addAttribute("productList",productList);
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
		model.addAttribute("discountList",discountList);
		model.addAttribute("detail",detail);	
		return "start";
	}

	@RequestMapping(value = "/addprodcut", method = RequestMethod.GET)
	public String addProduct(Product Product, Model model) {
		productDao.save(Product);
		List<Product> productList = productDao.getAll();
		model.addAttribute("productList", productList);
		return "start";
	}

	@RequestMapping(value = "/addDiscount", method = RequestMethod.GET)
	public String addDiscount(String string, Model model) {
		String[] barcodes = string.split(",");
		for (String barcode : barcodes) {
			productDao.setDiscount(barcode);
		}
		List<Product> productList = productDao.getAll();
		model.addAttribute("productList", productList);
		String discountList = "";
		for (int i = 0; i < productList.size(); ++i) {
			Product product = productList.get(i);
			if (product.isDiscount()) {
				if (i != productList.size()) {
					discountList += ", ";
				}
			}
		}
		model.addAttribute("discountList", discountList);	
		return "start";
	}

	@RequestMapping(value = "/inputItem", method = RequestMethod.GET)
	public String inputItem(String string, Model model) {
		String detail = processService.calculateAll(string);
		model.addAttribute("datail", detail);	
		return "start";
	}
	@RequestMapping(value = "/inputbarcode", method = RequestMethod.GET)
	public String inputBarcode(String string, Model model) {
		String detail = processService.calculateAll(string);
		model.addAttribute("datail", detail);	
		return "start";
	}
}
