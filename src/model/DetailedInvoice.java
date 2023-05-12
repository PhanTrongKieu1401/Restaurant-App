package model;

import java.io.Serializable;

public class DetailedInvoice implements Serializable, Comparable<DetailedInvoice>{
	private int id;
	private int quantity;
	private float price;
	private float amount;
	private Material material;
	public DetailedInvoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailedInvoice(int quantity, float price, float amount, Material material) {
		super();
//		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
		this.material = material;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getAmount() {
		return quantity*price;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	@Override
	public String toString() {
		return "DetailedInvoice [id=" + id + ", quantity=" + quantity + ", price=" + price + ", amount=" + amount
				+ ", material=" + material + "]";
	}
	@Override
	public int compareTo(DetailedInvoice o) {
		if(this.material.getName().compareTo(o.material.getName()) > 0) return 1;
		if(this.material.getName().compareTo(o.material.getName()) < 0) return -1;
		return 0;
	}
}
