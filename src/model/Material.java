package model;

import java.io.Serializable;
import java.util.Objects;

public class Material implements Serializable{
	private int id;
	private String name;
	private float price;
	public Material() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Material(String name, float price) {
		super();
//		this.id = id;
		this.name = name;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean equals(Object obj) {
	    if (obj == this) {
	        return true;
	    }
	    if (!(obj instanceof Material)) {
	        return false;
	    }
	    Material other = (Material) obj;
	    return Objects.equals(id, other.id) && Objects.equals(name, other.name)
	            && Objects.equals(price, other.price);
	}
	@Override
	public String toString() {
		return "Material [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}
