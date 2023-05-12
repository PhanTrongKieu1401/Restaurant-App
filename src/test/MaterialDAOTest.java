package test;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dao.DAO;
import dao.MaterialDAO;
import model.Material;
import model.Supplier;

public class MaterialDAOTest {
	MaterialDAO materialDAO = new MaterialDAO();
	
	@Test
	public void testSearchMaterialStandard1() {
		String key = "tom";
		ArrayList<Material> materials = materialDAO.searchMaterial(key);
		Assert.assertNotNull(materials);
		Assert.assertEquals(5, materials.size());
		for(int i=0; i<materials.size(); i++) {
			Assert.assertTrue(materials.get(i).getName().toLowerCase().contains(key.toLowerCase()));
		}
		return;
	}
	
	@Test
	public void testSearchMaterialStandard2() {
		String key = "bien";
		ArrayList<Material> materials = materialDAO.searchMaterial(key);
		Assert.assertNotNull(materials);
		Assert.assertEquals(2, materials.size());
		for(int i=0; i<materials.size(); i++) {
			Assert.assertTrue(materials.get(i).getName().toLowerCase().contains(key.toLowerCase()));
		}
		return;
	}
	
	@Test
	public void testSearchMaterialException1() {
		String key = "xxx";
		ArrayList<Material> materials = materialDAO.searchMaterial(key);
		Assert.assertNotNull(materials);
		Assert.assertEquals(0, materials.size());
		return;
	}
	
	@Test
	public void testSearchMaterialException2() {
		String key = "md";
		ArrayList<Material> materials = materialDAO.searchMaterial(key);
		Assert.assertNotNull(materials);
		Assert.assertEquals(0, materials.size());
		return;
	}
	
	@Test
	public void testAddMaterial() {
		Connection connection = DAO.connection;
		String newName = "test name";
		float newPrice = 1f;
		String key = "test name";
		
		try {
			connection.setAutoCommit(false);
			Material material = new Material();
			material.setName(newName);
			material.setPrice(newPrice);
			materialDAO.addMaterial(material);
			
			ArrayList<Material> tesMaterials = materialDAO.searchMaterial(key);
			Assert.assertNotNull(tesMaterials);
			Assert.assertEquals(1, tesMaterials.size());
			Assert.assertTrue(tesMaterials.get(0).getName().contains(key));
			Assert.assertEquals(newPrice, tesMaterials.get(0).getPrice(),0.000001f);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return;
	}
}
