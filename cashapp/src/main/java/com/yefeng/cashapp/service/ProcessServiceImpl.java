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

		StringBuffer detailPrice = new StringBuffer("` *<ûǮ׬�̵�>�����嵥*");
		StringBuffer discountPrice = new StringBuffer("");
		StringBuffer tatalPrice = new StringBuffer("");
		double totalSum = 0.0;
		double discountSum = 0.0;
		// С��λ��
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
			detailPrice.append(" ���ƣ�" + name + ",������" + value + "" + unit + ",���ۣ�" + df.format(price) + "(Ԫ)");
			double subTotal = value * price;
			if (isDiscount && subTotal >= 100.0) {
				double dis = (int) subTotal / 100 * 10;
				totalSum += subTotal;
				discountSum += dis;
				detailPrice.append(",С�ƣ�" + df.format(subTotal) + "(Ԫ),�Żݣ�" + df.format(dis) + "(Ԫ) ");
				if (discountPrice.length() < 1) {
					discountPrice.append("��Ʒ��100��10����Ʒ:");
				}
				discountPrice.append(" ��Ʒ��" + name + ",ԭ�ۣ�" + subTotal + "(Ԫ),�Żݣ�" + dis + "(Ԫ) ");
			} else {
				totalSum += subTotal;
				detailPrice.append(",С�ƣ�" + df.format(subTotal) + "(Ԫ) ");
			}
		}
		tatalPrice.append("�ܼƣ�" + df.format(totalSum) + "(Ԫ)");
		if (discountPrice.length() > 1) {
			tatalPrice.append(" ��ʡ��" + df.format(discountSum) + "(Ԫ)");
		}
		result = detailPrice.toString() + "\n\n" + discountPrice.toString() + "\n\n" + tatalPrice.toString();
		return result;
	}

}
