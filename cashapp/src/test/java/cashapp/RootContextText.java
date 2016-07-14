package cashapp;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yefeng.cashapp.dao.ProductDao;
import com.yefeng.cashapp.dao.UserDao;
import com.yefeng.cashapp.model.Product;

public class RootContextText {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
	
	@Test
	public void dataSourceTest() throws SQLException{
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void sessionFactoryTest() {
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		System.out.println(sessionFactory.openSession());
	}
	
	@Test
	public void productDaoTest() {
		ProductDao productDao = (ProductDao) context.getBean("productDao");
		System.out.println(productDao);
	}
	@Test
	public void userDaoTest() {
		UserDao userDao = (UserDao) context.getBean("userDao");
		System.out.println(userDao);
	}
}
