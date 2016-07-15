package com.yefeng.cashapp.model;

/**
 * @author yefengzhichen
 * 2016年7月10日
 */
public class Product {
    // 名称，数量单位，单价，类别和条形码（伪）
    private String barcode;
    private String name;
    private String unit;
    private String category;
    private String subCategory;
    private Double price;
    private boolean isDiscount;

    public Product() {
    }

    public Product(String barcode, String name, String unit, String category, String subCategory, Double price) {
        this.barcode = barcode;
        this.name = name;
        this.unit = unit;
        this.category = category;
        this.subCategory = subCategory;
        this.price = price;
    }
    
    public Product(String barcode, String name, String unit, String category, String subCategory, Double price,
			boolean isDiscount) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.unit = unit;
		this.category = category;
		this.subCategory = subCategory;
		this.price = price;
		this.isDiscount = isDiscount;
	}

	public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

	public boolean isDiscount() {
		return isDiscount;
	}

	public void setDiscount(boolean isDiscount) {
		this.isDiscount = isDiscount;
	}

	@Override
	public String toString() {
		return "Product [barcode=" + barcode + ", name=" + name + ", unit=" + unit + ", category=" + category
				+ ", subCategory=" + subCategory + ", price=" + price + "]";
	}
    
}
