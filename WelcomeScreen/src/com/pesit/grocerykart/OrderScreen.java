package com.pesit.grocerykart;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class OrderScreen extends Activity {
	DatabaseHandler sdb=new DatabaseHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_screen);
		Button b=(Button)findViewById(R.id.back);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent homepage = new Intent(OrderScreen.this,MenuScreen.class); 
				startActivity(homepage);
			}}
		);
		Button b1=(Button)findViewById(R.id.order);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Spinner spinner1=(Spinner)findViewById(R.id.spinner1);
				EditText quant = (EditText) findViewById(R.id.editText2);
				EditText price = (EditText) findViewById(R.id.editText3);
				String grocquant = quant.getText().toString();
				String grocprice = price.getText().toString();
				String distributor=((EditText)findViewById(R.id.editText4)).getText().toString();
				
		        
		        
				AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(OrderScreen.this);
				if(grocquant.equals(""))
				{
					dlgAlert.setMessage("No Quantity mentioned");
					dlgAlert.setTitle("Invalid Quantity");
					dlgAlert.setPositiveButton("OK", null);
					dlgAlert.setCancelable(true);
					dlgAlert.create().show();
				}
				else if(grocprice.equals(""))
				{
					dlgAlert.setMessage("No Price mentioned");
					dlgAlert.setTitle("Invalid Price");
					dlgAlert.setPositiveButton("OK", null);
					dlgAlert.setCancelable(true);
					dlgAlert.create().show();
				}
				
				
				else
				{
				int quantvalue = Integer.parseInt(quant.getText().toString());
				if(quantvalue>1000)
				{
					dlgAlert.setMessage("invalid quantity mentioned");
					dlgAlert.setTitle("Invalid Quantity");
					dlgAlert.setPositiveButton("OK", null);
					dlgAlert.setCancelable(true);
					dlgAlert.create().show();
				}
				
				else
				{
				int pricevalue = Integer.parseInt(price.getText().toString());
				GroceryList groc=new GroceryList();
				groc.setQuantity(quantvalue);
				groc.setPrice(pricevalue);
				groc.setName( String.valueOf(spinner1.getSelectedItem()));
				groc.setDistributor(distributor);
				sdb.orderGrocery(groc);
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_EMAIL, "nikrock6@gmail.com");
				intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
				intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(spinner1.getSelectedItem())+", quantity: "+quant.getText().toString()+" price:"+price.getText().toString());
				startActivity( new Intent(OrderScreen.this,OrderScreen.class));
				startActivity(Intent.createChooser(intent, "Send Email"));
				
				Toast.makeText(OrderScreen.this, String.valueOf(spinner1.getSelectedItem())+"has been ordered!",Toast.LENGTH_SHORT).show();
				}
				}
			}}
		);
		addItemsOnSpinner2(sdb.getAllNames());
		
	}
	public void addItemsOnSpinner2(List<String> list) {
		Spinner spinner2 = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter);
	  }

}
