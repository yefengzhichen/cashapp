package com.yefeng.cashapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yefeng.cashapp.model.Product;

@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Product product) {
		if (getSession().contains(product) == false) {
			getSession().save(product);
		}
	}

	@Override
	public void update(Product product) {
		String barcode = product.getBarcode();
		String hql = "delete from Product where barcode = ?";
		getSession().createQuery(hql).setParameter(0, barcode).executeUpdate();
		getSession().save(product);
	}

	@Override
	public Product getByBarcode(String barcode) {
		String hql = "select p from Product p where barcode = ?";
		List<Product> result = getSession().createQuery(hql).setParameter(0, barcode).getResultList();
		return result.get(0);
	}

	@Override
	public boolean contains(Product product) {
		String barcode = product.getBarcode();
		String hql = "select p from Product p where barcode = ?";
		List<Product> result = getSession().createQuery(hql).setParameter(0, barcode).getResultList();
		return result.size() > 0;
	}

	@Override
	public List<Product> getAll() {
		String hql = "select p from Product p";
		List<Product> result = getSession().createQuery(hql).getResultList();
		return result;
	}

	@Override
	public void setDiscount(String barcode) {
		String hql = "update Product p set p.isDiscount=true where p.barcode=:barcode";
		Query query = getSession().createQuery(hql);
		query.setParameter("barcode", barcode);
		query.executeUpdate();		
	}

}
