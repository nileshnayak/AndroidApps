package com.pesit.grocerykart;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.view.*;
public class InsertScreen extends Activity {
	static DatabaseHandler db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertscreen);
		Button b=(Button)findViewById(R.id.back);
		Button update=(Button)findViewById(R.id.order);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent homepage = new Intent(InsertScreen.this,MenuScreen.class); 
				startActivity(homepage);
			}}
		);	
		db = new DatabaseHandler(this);
		update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				boolean noerror = true;
				AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(InsertScreen.this);
				
			    EditText nam = (EditText) findViewById(R.id.itemNameText);
			    String grocname = nam.getText().toString();
			    
			   if (grocname.equals(""))
			    {
			    	dlgAlert.setMessage("No grocery Name mentioned");
					dlgAlert.setTitle("Invalid grocery Name");
					dlgAlert.setPositiveButton("OK", null);
					dlgAlert.setCancelable(true);
					dlgAlert.create().show();
					noerror=false;
			    	
			    }
			    
			    EditText quant = (EditText) findViewById(R.id.quantity);
		        String quantvalue = quant.getText().toString();
		        
		        if (quantvalue.equals("") || (Integer.parseInt(quantvalue))>1000)
		        {
		        	dlgAlert.setMessage("Invalid/No Quantity mentioned");
					dlgAlert.setTitle("Invalid Quantity");
					dlgAlert.setPositiveButton("OK", null);
					dlgAlert.setCancelable(true);
					dlgAlert.create().show();
					noerror=false;
					
		        	
		        }
			    
		        EditText pric = (EditText) findViewById(R.id.price);
		        String pricevalue = pric.getText().toString();
		        
		        if (pricevalue.equals("") )
		        {
		        	dlgAlert.setMessage("No Price mentioned");
					dlgAlert.setTitle("Invalid Price");
					dlgAlert.setPositiveButton("OK", null);
					dlgAlert.setCancelable(true);
					dlgAlert.create().show();
					noerror=false;
					
		        	
		        }
		        
		        EditText distr = (EditText) findViewById(R.id.distributor);
		        String dis = distr.getText().toString();
		        
		        
			    
		        if(noerror==true)
				{
		        GroceryList  grocery = new GroceryList(grocname,Integer.parseInt(quantvalue),Integer.parseInt(pricevalue),dis);
		       
		        db.addGrocery(grocery);
		        startActivity( new Intent(InsertScreen.this,InsertScreen.class));
		        dlgAlert.setMessage("Database Updated");
				dlgAlert.setTitle("Database Status");
				dlgAlert.setPositiveButton("OK", null);
				dlgAlert.setCancelable(true);
				dlgAlert.create().show();
				
				}
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_welcome_screen, menu);
		return true;
	}

}
