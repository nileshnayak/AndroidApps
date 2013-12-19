package com.pesit.grocerykart;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;


public class UpdateScreen extends Activity {

	DatabaseHandler sdb=new DatabaseHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_screen);
		Button b=(Button)findViewById(R.id.back);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent homepage = new Intent(UpdateScreen.this,MenuScreen.class); 
				startActivity(homepage);
			}}
		);
		Button b1=(Button)findViewById(R.id.update);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Spinner spinner1=(Spinner)findViewById(R.id.item);
				EditText quant = (EditText) findViewById(R.id.editText2);
				GroceryList groc=new GroceryList();
				
				
				String quantit = quant.getText().toString();
				AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(UpdateScreen.this);
			   if (quantit.equals(""))
			    {
			    	dlgAlert.setMessage("No quantity mentioned");
					dlgAlert.setTitle("Invalid quantity");
					dlgAlert.setPositiveButton("OK", null);
					dlgAlert.setCancelable(true);
					dlgAlert.create().show();
					
			    	
			    }
			   else
			   {
				   int quantvalue = Integer.parseInt(quant.getText().toString());
				   groc.setQuantity(quantvalue);
				groc.setName( String.valueOf(spinner1.getSelectedItem()));
				if(sdb.updateGrocery(groc)!=-1)
				{
				
				startActivity( new Intent(UpdateScreen.this,UpdateScreen.class));
				Toast.makeText(UpdateScreen.this, "The item has been sold!",Toast.LENGTH_SHORT).show();
				}
				else
				{
					
					dlgAlert.setMessage("Invalid Quantity mentioned");
					dlgAlert.setTitle("Invalid Quantity");
					dlgAlert.setPositiveButton("OK", null);
					dlgAlert.setCancelable(true);
					dlgAlert.create().show();
				}
			   }
			}}
		);
		addItemsOnSpinner2(sdb.getAllNames());
	}
	public void addItemsOnSpinner2(List<String> list) {
		Spinner spinner2 = (Spinner) findViewById(R.id.item);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter);
	  }

}

