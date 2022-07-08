package dao;

import java.util.ArrayList;
import java.util.List;
import model.*;
import bean.*;

public class ListProductDAO {

	// return the list of products by product name
	public List<Product> search(String characters) throws Exception {

		String nameProduct = characters;
		Body bd = new Body();
		List<Product> listProduct = new ArrayList<Product>();
		List<Product> searchProduct = new ArrayList<Product>();
		listProduct = bd.getProduct();

		for (int i = 0; i < listProduct.size(); i++) {

			if (listProduct.get(i).getName().equalsIgnoreCase(nameProduct)) {

				searchProduct.add(listProduct.get(i));
			}

		}

		return searchProduct;
	}

	// get the product
	public Product getProduct(int id) {

		Body bd = new Body();
		Product e = new Product();
		int productId = id;
		List<Product> listProduct = new ArrayList<Product>();
		listProduct = bd.getProduct();

		for (int i = 0; i < listProduct.size(); i++) {

			if (listProduct.get(i).getId() == productId) {

				e = listProduct.get(i);
			}

		}

		return e;

	}
}
