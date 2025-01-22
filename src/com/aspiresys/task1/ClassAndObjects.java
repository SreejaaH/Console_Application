package com.aspiresys.task1;

public class Product {
    // Fields (instance variables)
    String carName;
    int model_num;
    String color;
	
	public Product (String carName,int model_num,String color) {
		this.carName=carName;
		this.model_num=model_num;
		this.color=color;
	}
	public void ShowDetails() {
		System.out.println(this.carName);
		System.out.println(this.model_num);
		System.out.println(this.color);
	}
}

	public class ClassAndObjects {
		public static void main(String[] args) {
			Product.product=new Product("Tesla",2345,"yellow");
			product.ShowDetails();
		}
}
	
