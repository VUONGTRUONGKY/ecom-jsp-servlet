package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Product> items;

	public Cart() {
		items = new ArrayList<>();
	}

	public void add(Product ci) {

		// add a new product to cart
		for (Product x : items) {
			if (ci.getId() == x.getId()) {

				x.setNumber(x.getNumber() + 1);
				return;
			}
		}

		items.add(ci);
	}
	
	public void less(Product ci) {
		
		for(Product x : items) {
			
			if (ci.getId() == x.getId()) {

				x.setNumber(x.getNumber() - 1);
				return;
			}
		}

		items.add(ci);
		
	}
	
	// add quantity product in cart
	public void addQuantity (int id) {
		int num = 0;
		for (Product x : items) {
			
			if (x.getId() == id) {
				num = x.getNumber() + 1;
				x.setNumber(num);
				return;
				
			}
			
		}
		
	}
	
	//less quantity product in cart
	public void lessQuantity(int id) {
		
		int num = 0;
		for(Product x: items) {
			
			if(x.getId() == id) {
				
				num = x.getNumber() - 1;
				x.setNumber(num);
				return;
				
			}
			
		}
		
	}

	// remove a product to cart
	public void remove(int id) {

		for (Product x : items) {

			if (x.getId() == id) {

				items.remove(x);
				return;
			}
		}
	}
	
	//return total amount of cart
	public double getAmount() {
		
		double s = 0;
		for(Product x : items) {
			
			s += x.getPrice()*x.getNumber();
		}
		
		return Math.round(s*100.0)/100.0;
	}
	
	//return list product
	public List<Product> getItems() {
		return items;
	}
	
public String StringAmount() {
		
		return "" + getAmount();
	}

}







