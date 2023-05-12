package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.DetailedInvoice;
import model.Invoice;

public class InvoiceDAO extends DAO{

	public InvoiceDAO() {
		super();
	}
	/**
	 * Thêm hóa đơn mới bao gồm hóa đơn chi tiết. Tất cả được thêm vào trong một giao dịch đơn.
	 * @param i
	 * @return
	 */
	public boolean addInvoice(Invoice i) {
		String sqlAddInvoice = "INSERT INTO tblInvoice(createDay, userID, supplierID) VALUES(?,?,?)";
		String sqlAddDetailedInvoice = "INSERT INTO tblDetailedInvoice(quantity, price, invoiceID, materialID) VALUES(?,?,?,?)";
		boolean result = true;
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(sqlAddInvoice, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, i.getCreateDay());
			ps.setInt(2, i.getUser().getId());
			ps.setInt(3, i.getSupplier().getId());		
			
			ps.executeUpdate();	
			//lấy id của hóa đơn mới thêm
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				i.setId(generatedKeys.getInt(1));
				
				//chèn danh sách detailedInvoice
				for(DetailedInvoice di: i.getDetailedInvoices()) {
					//insert detailed invoice
					ps = connection.prepareStatement(sqlAddDetailedInvoice, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, di.getQuantity());
					ps.setFloat(2, di.getPrice());
					ps.setInt(3, i.getId());
					ps.setInt(4, di.getMaterial().getId());
					
					ps.executeUpdate();	
					//lấy id của hóa đơn mới thêm
					generatedKeys = ps.getGeneratedKeys();
					if (generatedKeys.next()) {
						di.setId(generatedKeys.getInt(1));
					}
				}
			}
			
//			connection.commit();//set this line into comment in JUnit test mode
		}catch(Exception e) {
			result = false;			
			try {				
				connection.rollback();
			}catch(Exception ex) {
				result = false;
				ex.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {		
				connection.setAutoCommit(true);//set this line into comment in JUnit test mode
			}catch(Exception ex) {
				result = false;
				ex.printStackTrace();
			}
		}
		return result;
	}
}
