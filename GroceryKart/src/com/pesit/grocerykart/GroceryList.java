package com.pesit.grocerykart;

public class GroceryList {

	//private variables
	
	String _name;
	int _quantity;
	int _price;
	String _distributor;
	
	// Empty constructor
	public GroceryList(){
		_name="";
		_quantity=0;
		_price=0;
		_distributor="";
		
	}
	
	
	// constructor
	public GroceryList(String name, int _quantity, int _price, String _distributor )
	{
		this._name = name;
		this._quantity = _quantity;
		this._price = _price;
		this._distributor = _distributor;
		
	}

	
	
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}
	
	
	public int getQuantity(){
		return this._quantity;
	}
	
	
	public void setQuantity(int quantity){
		this._quantity = quantity;
	}
	
	public int getPrice(){
		return this._price;
	}
	
	
	public void setPrice(int price){
		this._price = price;
	}
	public String getDistributor(){
		return this._distributor;
	}
	
	// setting name
	public void setDistributor(String distributor){
		this._distributor = distributor;
	}
}
	