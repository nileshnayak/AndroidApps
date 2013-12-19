package com.pesit.grocerykart;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class WelcomeScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_screen);
		moveToNextPage();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_welcome_screen, menu);
		return true;
	}
	public void moveToNextPage()
	{
		int timeout=3000;
		Timer timer=new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run(){
				finish();
				Intent homepage = new Intent(WelcomeScreen.this,MenuScreen.class); 
				startActivity(homepage);
			}
		}, timeout);
		
	}
}
