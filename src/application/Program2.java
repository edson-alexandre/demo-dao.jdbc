package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();		

		System.out.println("\n==== TEST 1 : Deparment findById ====");
		Department dep = departmentDao.findByID(2);
		System.out.println(dep);
		
		System.out.println("\n==== TEST 2 : Deparment findAll ====");				
		List<Department> list = new ArrayList<Department>();
		list=departmentDao.findAll();
		for(Department d: list) {
			System.out.println(d);
		}
		
		
		System.out.println("\n==== TEST 3 : Deparment Insert ====");

		dep = new Department(0, "Outro departamento");
		departmentDao.insert(dep);		
		System.out.println("Inserted! New id: "+dep.getId());
		 
		
		System.out.println("\n==== TEST 4 : Deparment Update ====");
		departmentDao.update(new Department(6,"Outro Departamento"));
		System.out.println("Updated");
		
		System.out.println("\n==== TEST 5 : Deparment delete ====");
		System.out.println("Enter id for de delete test");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Deleted!");
		
		sc.close();
	}

}
