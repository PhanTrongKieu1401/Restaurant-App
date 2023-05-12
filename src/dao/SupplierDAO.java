package dao;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Supplier;

public class SupplierDAO extends DAO{
	
	public SupplierDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * search all suppliers in the tblsupplier whose name contains the @key
	 * @param key
	 * @return list of supplier whose name contains the @key
	 */
	public ArrayList<Supplier> searchSupplier(String key){
		ArrayList<Supplier> result = new ArrayList<Supplier>();
		String sql = "SELECT * FROM tblSupplier WHERE name LIKE ?";
		try(PreparedStatement ps = connection.prepareStatement(sql);)
		{
			
			ps.setString(1, "%" + key + "%");
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("id"));
				supplier.setName(rs.getString("name"));
				supplier.setAddress(rs.getString("address"));
				supplier.setEmail(rs.getString("email"));
				supplier.setDescription(rs.getString("description"));
				result.add(supplier);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}
	
	/**
	 * add a new @supplier into the DB
	 * @param supplier
	 */
	public boolean addSupplier(Supplier supplier){
		String sql = "INSERT INTO tblSupplier(name, address, email, description) VALUES(?,?,?,?)";
		boolean result = false;
		try{
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, supplier.getName());
			ps.setString(2, supplier.getAddress());
			ps.setString(3, supplier.getEmail());
			ps.setString(4, supplier.getDescription());

			ps.executeUpdate();
			result = true;
			//get id of the new inserted supplier
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				supplier.setId(generatedKeys.getInt(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
