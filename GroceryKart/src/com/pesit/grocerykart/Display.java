package com.pesit.grocerykart;


import android.app.Activity;
import android.os.Bundle;
import java.util.List;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


import android.content.*;


public class Display extends Activity{
	
	   
	   ListView listView;
       List<GroceryList> rowItems;
       DatabaseHandler db; 
       
       
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.display);
	        
	        Button back=(Button)findViewById(R.id.name1);
	        
	        db=new DatabaseHandler(this);
	     
	        rowItems = new ArrayList<GroceryList>();
	       
	        
	     
	        rowItems = db.getAllItems();
	       
	        
	    
            listView = (ListView) findViewById(R.id.listView1);
	        
	       CustomListView adapter = new CustomListView(this,
	                R.layout.listitems, rowItems);
	        
	       listView.setAdapter(adapter);

	        
	        back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Intent mainIntent = new Intent(Display.this,MenuScreen.class);
		             startActivity(mainIntent);
		             finish();
					
				}
			});
	        
	        
	 }
	 
	 

}