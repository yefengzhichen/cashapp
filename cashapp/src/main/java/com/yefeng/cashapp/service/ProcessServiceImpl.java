package com.yefeng.cashapp.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yefeng.cashapp.dao.ProductDao;
import com.yefeng.cashapp.model.Product;

@Repository(value = "processService")
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private ProductDao productDao;

	public String calculateAll(String inputString){
		Map<String, Double> map = parseInput(inputString);
		return calculatePrice(map);
	}
		
	public Map<String, Double> parseInput(String inputString) {
		String[] input = inputString.split(",");
		Map<String, Double> buy = new HashMap<>();
		for (String str : input) {
			String[] content = str.split("-");
			double num = 0.0;
			if (content.length == 1) {
				num = 1.0;
			} else if (content.length == 2) {
				num = Double.parseDouble(content[1]);
			}
			String key = content[0];
			if (buy.containsKey(key)) {
				num += buy.get(key);
				buy.put(key, num);
			} else {
				buy.put(key, num);
			}
		}
		return buy;
	}

	public String calculatePrice(Map<String, Double> buy) {
		String result;
		List<Product> productList = productDao.getAll();
		String detail = "";

		StringBuffer detailPrice = new StringBuffer("` *<没钱赚商店>购物清单*");
		StringBuffer discountPrice = new StringBuffer("");
		StringBuffer tatalPrice = new StringBuffer("");
		double totalSum = 0.0;
		double discountSum = 0.0;
		// 小数位数
		DecimalFormat df = new DecimalFormat("######0.00");
		for (Map.Entry<String, Double> entry : buy.entrySet()) {
			String barcode = entry.getKey();
			Product product = productDao.getByBarcode(barcode);
			double value = entry.getValue();

			double price = product.getPrice();
			String name = product.getName();
			String unit = product.getUnit();
			boolean isDiscount = product.isDiscount();
			// String sub = product.getSubCategory();
			detailPrice.append(" 名称：" + name + ",数量：" + value + "" + unit + ",单价：" + df.format(price) + "(元)");
			double subTotal = value * price;
			if (isDiscount && subTotal >= 100.0) {
				double dis = (int) subTotal / 100 * 10;
				totalSum += subTotal;
				discountSum += dis;
				detailPrice.append(",小计：" + df.format(subTotal) + "(元),优惠：" + df.format(dis) + "(元) ");
				if (discountPrice.length() < 1) {
					discountPrice.append("单品满100减10块商品:");
				}
				discountPrice.append(" 商品：" + name + ",原价：" + subTotal + "(元),优惠：" + dis + "(元) ");
			} else {
				totalSum += subTotal;
				detailPrice.append(",小计：" + df.format(subTotal) + "(元) ");
			}
		}
		tatalPrice.append("总计：" + df.format(totalSum) + "(元)");
		if (discountPrice.length() > 1) {
			tatalPrice.append(" 节省：" + df.format(discountSum) + "(元)");
		}
		result = detailPrice.toString() + "\n\n" + discountPrice.toString() + "\n\n" + tatalPrice.toString();
		return result;
	}

}
