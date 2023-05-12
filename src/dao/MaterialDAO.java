package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Material;

public class MaterialDAO extends DAO{
	
	public MaterialDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * search all materials in the tblmaterial whose name contains the @key
	 * @param key
	 * @return list of material whose name contains the @key
	 */
	public ArrayList<Material> searchMaterial(String key){
		ArrayList<Material> result = new ArrayList<Material>();
		String sql = "SELECT * FROM tblMaterial WHERE name LIKE ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + key + "%");
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Material material = new Material();
				material.setId(rs.getInt("id"));
				material.setName(rs.getString("name"));
				material.setPrice(rs.getFloat("price"));
				result.add(material);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}
	
	/**
	 * add a new @material into the DB
	 * @param material
	 */
	public boolean addMaterial(Material material){
		String sql = "INSERT INTO tblMaterial(name, price) VALUES(?,?)";
		boolean result = false;
		try{
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, material.getName());
			ps.setFloat(2, material.getPrice());

			ps.executeUpdate();
			result = true;
			//lấy id của material được thêm mới
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				material.setId(generatedKeys.getInt(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
