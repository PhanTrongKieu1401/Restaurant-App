package test;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dao.DAO;
import dao.SupplierDAO;
import model.Supplier;

public class SupplierDAOTest {
	SupplierDAO supplierDAO = new SupplierDAO();
	
	@Test
	public void testSearchSupplierStandard1() {
		String key = "hai";
		ArrayList<Supplier> suppliers = supplierDAO.searchSupplier(key);
		Assert.assertNotNull(suppliers);
		Assert.assertEquals(4, suppliers.size());
		for(int i=0; i<suppliers.size(); i++) {
			Assert.assertTrue(suppliers.get(i).getName().toLowerCase().contains(key.toLowerCase()));
		}
		return;
	}
	
	@Test
	public void testSearchSupplierStandard2() {
		String key = "deli";
		ArrayList<Supplier> suppliers = supplierDAO.searchSupplier(key);
		Assert.assertNotNull(suppliers);
		Assert.assertEquals(1, suppliers.size());
		for(int i=0; i<suppliers.size(); i++) {
			Assert.assertTrue(suppliers.get(i).getName().toLowerCase().contains(key.toLowerCase()));
		}
		return;
	}
	
	@Test
	public void testSearchSupplierException1() {
		String key = "xxxxxx";
		ArrayList<Supplier> suppliers = supplierDAO.searchSupplier(key);
		Assert.assertNotNull(suppliers);
		Assert.assertEquals(0, suppliers.size());
		return;
	}
	
	@Test
	public void testSearchSupplierException2() {
		String key = "md";
		ArrayList<Supplier> suppliers = supplierDAO.searchSupplier(key);
		Assert.assertNotNull(suppliers);
		Assert.assertEquals(0, suppliers.size());
		return;
	}
	
	@Test
	public void testAddSupplier() {
		Connection connection = DAO.connection;
		String newName = "test name";
		String newAddress = "test address";
		String newEmail = "test email";
		String newDescription = "test description";
		String key = "test name";
		
		try {
			connection.setAutoCommit(false);
			Supplier supplier = new Supplier();
			supplier.setName(newName);
			supplier.setAddress(newAddress);
			supplier.setEmail(newEmail);
			supplier.setDescription(newDescription);
			supplierDAO.addSupplier(supplier);
			
			ArrayList<Supplier> testSuppliers = supplierDAO.searchSupplier(key);
			Assert.assertNotNull(testSuppliers);
			Assert.assertEquals(1, testSuppliers.size());
			Assert.assertTrue(testSuppliers.get(0).getName().contains(key));
			Assert.assertEquals(newAddress, testSuppliers.get(0).getAddress());
			Assert.assertEquals(newEmail, testSuppliers.get(0).getEmail());
			Assert.assertEquals(newDescription, testSuppliers.get(0).getDescription());
			
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
