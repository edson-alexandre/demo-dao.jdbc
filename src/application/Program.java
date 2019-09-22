package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println("==== TEST 1 : seller findById ====");
		Seller seller = sellerDao.findByID(3);
		System.out.println(seller);
		
		System.out.println("\n==== TEST 2 : seller findByDepartment ====");
		Department department = new Department(2,null);
		List<Seller> list = sellerDao.findByDepartment(department);
		
		for(Seller obj : list) {
			System.out.println(obj);
		}
		

		System.out.println("\n==== TEST 3 : seller findAll ====");				
		list = sellerDao.findAll();
		
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==== TEST 4 : seller Insert ====");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Seller newSeller;
		try {
			newSeller = new Seller(0,"Funalo","fulano@gmail.com",new Date(sdf.parse("13/05/1981").getTime()),3000.0,department);
			sellerDao.insert(newSeller);
			System.out.println("Inserted! new Id: "+newSeller.getId());
		} catch (ParseException e) {
			e.printStackTrace();
		} 

		System.out.println("\n==== TEST 5 : seller Update ====");
		seller = sellerDao.findByID(9);
		seller.setName("Eduardo Alegre");
		sellerDao.update(seller);
		seller = sellerDao.findByID(9);
		System.out.println("Update completed! new Seller:"+seller);
	}

}
