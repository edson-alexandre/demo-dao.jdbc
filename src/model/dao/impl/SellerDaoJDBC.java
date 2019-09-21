package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findByID(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					 " SELECT seller.*,department.Name as DepName"  
 					+ "  FROM seller INNER "
 					+ "               JOIN department" 
					+ "                 ON seller.DepartmentId = department.Id"  
					+ " WHERE seller.Id = ?"  
					);
			st.setInt(1,id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs,dep); 
				return seller;
			}			
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);			
		}
				
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Seller> sellers = new ArrayList<>();
		Map<Integer , Department> map = new HashMap<>();
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
				   	+"FROM seller INNER "
				   	+ "            JOIN department "
					+"               ON seller.DepartmentId = department.Id "
					+"WHERE DepartmentId = ? "
					+"ORDER "
					+ "  BY Name"
					);
			st.setInt(1, department.getId());
			rs=st.executeQuery();
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				if(dep==null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				sellers.add(instantiateSeller(rs, dep));
			}
			return sellers;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);			
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		return new Seller(
	            rs.getInt("Id"), 
	            rs.getString("Name"), 
	            rs.getString("Email"), 
	            rs.getDate("BirthDate"), 
	            rs.getDouble("BaseSalary"), 
	            dep);	            
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("DepartmentId"),
                              rs.getString("DepName"));
	}
}
