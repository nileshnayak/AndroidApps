package com.pesit.grocerykart;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menulist);
		Button click = (Button)findViewById(R.id.button1);
		click.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) {
			Intent i=new Intent(MenuScreen.this,InsertScreen.class);
			startActivity(i);	
			}
		});
	
	Button click2 = (Button)findViewById(R.id.button2);
	click2.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View arg0) {
		Intent i=new Intent(MenuScreen.this,UpdateScreen.class);
		startActivity(i);	
		}
	});
	Button click3 = (Button)findViewById(R.id.button5);
	click3.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View arg0) {
		Intent i=new Intent(MenuScreen.this,OrderScreen.class);
		startActivity(i);	
		}
	});
	Button click4 = (Button)findViewById(R.id.button3);
	click4.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View arg0) {
		Intent i=new Intent(MenuScreen.this,DeleteScreen.class);
		startActivity(i);	
		}
	});
	Button clickd = (Button)findViewById(R.id.button4);
	clickd.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View arg0) {
		Intent i=new Intent(MenuScreen.this,Display.class);
		startActivity(i);	
		}
	});
	Button exit = (Button)findViewById(R.id.exit);
	exit.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View arg0) {
		Intent i=new Intent(Intent.ACTION_MAIN);
		i.addCategory(Intent.CATEGORY_HOME);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);	
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
