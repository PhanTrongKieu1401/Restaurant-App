package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Invoice implements Serializable{
	private int id;
	private Date createDay;
	private float totalAmount;
	private User user;
	private Supplier supplier;
	private ArrayList<DetailedInvoice> detailedInvoices;
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invoice(Date createDay, float totalAmount, User user, Supplier supplier,
			ArrayList<DetailedInvoice> detailedInvoices) {
		super();
//		this.id = id;
		this.createDay = createDay;
		this.totalAmount = totalAmount;
		this.user = user;
		this.supplier = supplier;
		this.detailedInvoices = detailedInvoices;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateDay() {
		return createDay;
	}
	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public ArrayList<DetailedInvoice> getDetailedInvoices() {
		return detailedInvoices;
	}
	public void setDetailedInvoices(ArrayList<DetailedInvoice> detailedInvoices) {
		this.detailedInvoices = detailedInvoices;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", createDay=" + createDay + ", totalAmount=" + totalAmount + ", user=" + user
				+ ", supplier=" + supplier + ", detailedInvoices=" + detailedInvoices + "]";
	}
	
}
