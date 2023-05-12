package test;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import dao.DAO;
import dao.InvoiceDAO;
import model.DetailedInvoice;
import model.Invoice;
import model.Material;
import model.Supplier;
import model.User;

public class InvoiceDAOTest {
	InvoiceDAO invoiceDAO = new InvoiceDAO();
	
	@Test
    public void testAddInvoiceStandard() {
        Invoice invoice = new Invoice();
        //invoice createDate
        Date date = new Date();
        invoice.setCreateDay(new java.sql.Date(date.getTime()));

        //user
        User user = new User();
        user.setId(1);
        invoice.setUser(user);
        
        //supplier
        Supplier supplier = new Supplier();
        supplier.setId(1);
        invoice.setSupplier(supplier);
        
        //material 1
        Material material1 = new Material();
        material1.setId(1);
        
        ArrayList<DetailedInvoice> detailedInvoices = new ArrayList<>();
        //detailed invoice 1
        DetailedInvoice detailedInvoice1 = new DetailedInvoice();
        detailedInvoice1.setMaterial(material1);
        detailedInvoice1.setQuantity(1);
        detailedInvoice1.setPrice((float) 200);
        detailedInvoice1.setAmount(detailedInvoice1.getPrice() * detailedInvoice1.getQuantity());
        detailedInvoices.add(detailedInvoice1);

        //material 2
        Material material2 = new Material();
        material2.setId(2);
        
        //detailed invoice 2
        DetailedInvoice detailedInvoice2 = new DetailedInvoice();
        detailedInvoice2.setMaterial(material2);
        detailedInvoice2.setQuantity(1);
        detailedInvoice2.setPrice((float) 200);
        detailedInvoice2.setAmount(detailedInvoice2.getPrice() * detailedInvoice2.getQuantity());
        detailedInvoices.add(detailedInvoice2);
        
        invoice.setDetailedInvoices(detailedInvoices);

        // test          
        Connection connection = DAO.connection;
        try{                
            connection.setAutoCommit(false);           
            Assert.assertTrue(invoiceDAO.addInvoice(invoice));
        }catch(Exception e){
//        	System.out.println("catch 1 standard");
            e.printStackTrace();
        }finally{
            try{
                if(!connection.getAutoCommit()) {
                	connection.rollback();
                	connection.setAutoCommit(true);
//                	System.out.println("finally try auto commit false");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return;
    }
     
	@Test
    public void testAddInvoiceException() {
		
        Invoice invoice = new Invoice();
        //invoice createDate
        Date date = new Date();
        invoice.setCreateDay(new java.sql.Date(date.getTime()));

        //user
        User user = new User();
        user.setId(1);
        invoice.setUser(user);
        
        //supplier
        Supplier supplier = new Supplier();
        supplier.setId(2000);
        invoice.setSupplier(supplier);
        
        //material 1
        Material material1 = new Material();
        material1.setId(2000);
        
        ArrayList<DetailedInvoice> detailedInvoices = new ArrayList<>();
        //detailed invoice 1
        DetailedInvoice detailedInvoice1 = new DetailedInvoice();
        detailedInvoice1.setMaterial(material1);
        detailedInvoice1.setQuantity(1);
        detailedInvoice1.setPrice((float) 200);
        detailedInvoice1.setAmount(detailedInvoice1.getPrice() * detailedInvoice1.getQuantity());
        detailedInvoices.add(detailedInvoice1);
        
        invoice.setDetailedInvoices(detailedInvoices);

        // test          
        Connection connection = DAO.connection;
        try{                
            connection.setAutoCommit(false);           
            Assert.assertFalse(invoiceDAO.addInvoice(invoice));
            
        }catch(Exception e){
//        	System.out.println("catch 1 standard");
            e.printStackTrace();
        }finally{
            try{
                if(!connection.getAutoCommit()) {
                	connection.rollback();
                	connection.setAutoCommit(true);
//                	System.out.println("finally try auto commit false");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return;
    }

}
