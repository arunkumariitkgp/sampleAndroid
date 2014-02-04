package com.startupsourcing.airwala;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class HomeScreen extends Activity {

	private TextView fromTextView;
	private TextView toTextView;
	private Button oneWayButton;
	private Button roundTripButton;
	private Button multiCityButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_home_screen);
		View view = this.getWindow().getDecorView();
	    view.setBackgroundColor(Color.parseColor("#2F2F2F"));
	    this.adjustViewOutlets();
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
	
	public void oneWay(View view){
		this.oneWayButton.setBackgroundResource(R.drawable.button_selected);
		this.roundTripButton.setBackgroundResource(R.drawable.button_normal);
		this.multiCityButton.setBackgroundResource(R.drawable.button_normal);
	}

	public void roundTrip(View view){
		this.oneWayButton.setBackgroundResource(R.drawable.button_normal);
		this.roundTripButton.setBackgroundResource(R.drawable.button_selected);
		this.multiCityButton.setBackgroundResource(R.drawable.button_normal);
	}

	public void multiCity(View view){
		this.oneWayButton.setBackgroundResource(R.drawable.button_normal);
		this.roundTripButton.setBackgroundResource(R.drawable.button_normal);
		this.multiCityButton.setBackgroundResource(R.drawable.button_selected);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)  
	{  
		super.onActivityResult(requestCode,resultCode,data);  
		switch (resultCode) {
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
	
	private void adjustViewOutlets () {
	    this.fromTextView = (TextView)findViewById(R.id.from_text_search_airport);
	    this.toTextView = (TextView)findViewById(R.id.to_text_search_airport);
	    this.fromTextView.setVisibility(View.INVISIBLE);
	    this.toTextView.setVisibility(View.INVISIBLE);
	    Typeface tFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
	    this.fromTextView.setTypeface(tFace);
	    this.toTextView.setTypeface(tFace);
	    
	    this.oneWayButton = (Button)findViewById(R.id.one_way);
	    this.roundTripButton = (Button)findViewById(R.id.round_trip);
	    this.multiCityButton = (Button)findViewById(R.id.multi_city);
	    tFace = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueLTStd-Md.otf");
	    this.oneWayButton.setTypeface(tFace);
	    this.roundTripButton.setTypeface(tFace);
	    this.multiCityButton.setTypeface(tFace);
		this.roundTripButton.setBackgroundResource(R.drawable.button_selected);
	}
}
