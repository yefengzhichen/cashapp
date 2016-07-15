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
		addModelAttribute(model);
		String detail = "The input product barcode is null";
		model.addAttribute("detail", detail);
		return "start";
	}

	// Item0010,apple,kg,fruit,fresh fruit,13.00
	@RequestMapping(value = "/inputProduct", method = RequestMethod.POST)
	public String addProduct(String inputProduct, Model model) {
		String[] proStr = inputProduct.split(";");
		for (String pro : proStr) {
			String[] list = pro.split(",");
			String barcode = list[0];
			String name = list[1];
			String unit = list[2];
			String category = list[3];
			String subCategory = list[4];
			Double price = Double.parseDouble(list[5]);
			Product product = new Product(barcode, name, unit, category, subCategory, price);
			productDao.save(product);
		}
		addModelAttribute(model);
		return "start";
	}

	@RequestMapping(value = "/inputDiscount", method = RequestMethod.POST)
	public String addDiscount(String inputDiscount, Model model) {
		String[] barcodes = inputDiscount.split(",");
		for (String barcode : barcodes) {
			productDao.setDiscount(barcode);
		}
		addModelAttribute(model);
		return "start";
	}

	@RequestMapping(value = "/inputBarcode", method = RequestMethod.POST)
	public String inputItem(String inputBarcode, Model model) {
		String detail = processService.calculateAll(inputBarcode);
		model.addAttribute("datail", detail);
		addModelAttribute(model);
		return "start";
	}

	// @RequestMapping(value = "/inputbarcode", method = RequestMethod.POST)
	// public String inputBarcode(String string, Model model) {
	// String detail = processService.calculateAll(string);
	// model.addAttribute("datail", detail);
	// addModelAttribute(model);
	// return "start";
	// }

	public void addModelAttribute(Model model) {
		List<Product> productList = productDao.getAll();
		model.addAttribute("productList", productList);
		String discountList = "";
		for (int i = 0; i < productList.size(); ++i) {
			Product product = productList.get(i);
			if (product.isDiscount()) {
				if (discountList.length() == 0) {
					discountList += product.getBarcode();
				} else {
					discountList += (", " + product.getBarcode());
				}
			}
		}
		model.addAttribute("discountList", discountList);
	}

}
