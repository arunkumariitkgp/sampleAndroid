package com.startupsourcing.airwala;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class HomeScreen extends Activity {

	private TextView fromTextView;
	private TextView toTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_home_screen);
		View view = this.getWindow().getDecorView();
	    view.setBackgroundColor(Color.parseColor("#2F2F2F"));
	    this.fromTextView = (TextView)findViewById(R.id.from_text_search_airport);
	    this.toTextView = (TextView)findViewById(R.id.to_text_search_airport);
	    this.fromTextView.setVisibility(View.INVISIBLE);
	    this.toTextView.setVisibility(View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	public void fromAirportSearch(View view){
		Intent intent = new Intent(this,AirportList.class);
		intent.putExtra("SEARCH", 1);
		startActivityForResult(intent, 1);
	}
	
	public void toAirportSearch(View view){
		Intent intent = new Intent(this,AirportList.class);
		intent.putExtra("SEARCH", 2);
		startActivityForResult(intent, 2);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)  
	{  
		super.onActivityResult(requestCode, resultCode, data);  
		switch (requestCode) {
		case 1:
			String from = data.getStringExtra("SEARCH");
			this.fromTextView.setText(from);
			this.fromTextView.setVisibility(View.VISIBLE);
			break;
		case 2:
			String to = data.getStringExtra("SEARCH");
			this.toTextView.setText(to);
			this.toTextView.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}  
}
