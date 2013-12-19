package com.pesit.grocerykart;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteScreen extends Activity {
	DatabaseHandler sdb=new DatabaseHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_screen);
		Button b=(Button)findViewById(R.id.back);
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent homepage = new Intent(DeleteScreen.this,MenuScreen.class); 
				startActivity(homepage);
			}}
		);
		Button b1=(Button)findViewById(R.id.delete);
		
		b1.setOnClickListener(new View.OnClickListener() {
			Spinner spinner1 = (Spinner) findViewById(R.id.list);
			@Override
			public void onClick(View v) {
				sdb.deleteGrocery(String.valueOf(spinner1.getSelectedItem()));
				Toast.makeText(DeleteScreen.this, String.valueOf(spinner1.getSelectedItem()),Toast.LENGTH_SHORT).show();
				addItemsOnSpinner2(sdb.getAllNames());
			}}
		);
		
		addItemsOnSpinner2(sdb.getAllNames());
	}
	public void addItemsOnSpinner2(List<String> list) {
		Spinner spinner2 = (Spinner) findViewById(R.id.list);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter);
	  }
	}


