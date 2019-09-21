package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department obj = new Department(1,"Books");
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findByID(2);
		System.out.println(seller);
		
		

	}

}
