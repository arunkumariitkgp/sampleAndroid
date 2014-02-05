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
import android.widget.LinearLayout;

public class HomeScreen extends Activity {

	private LinearLayout fromTextLayout;
	private LinearLayout toTextLayout;
	private TextView fromTextView;
	private TextView toTextView;
	private TextView fromDetailTextView;
	private TextView toDetailTextView;
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
			this.fromTextLayout.setVisibility(View.VISIBLE);
			String from = data.getStringExtra("SEARCH");
			this.fromTextView.setText(from.substring(1, 4));
			String[] from_arr = from.split(" - ");
			from_arr = from_arr[1].split(", ");
			this.fromDetailTextView.setText(from_arr[0]+", "+from_arr[1]);
			break;
		case 2:
			this.toTextLayout.setVisibility(View.VISIBLE);
			String to = data.getStringExtra("SEARCH");
			this.toTextView.setText(to.substring(1, 4));
			String[] to_arr = to.split(" - ");
			to_arr = to_arr[1].split(", ");
			this.toDetailTextView.setText(to_arr[0]+", "+to_arr[1]);
			break;
		default:
			break;
		}
	}  
	
	private void adjustViewOutlets () {
	    this.fromTextView = (TextView)findViewById(R.id.from_text_search_airport);
	    this.toTextView = (TextView)findViewById(R.id.to_text_search_airport);
	    this.fromDetailTextView = (TextView)findViewById(R.id.from_search_airport_details_text);
	    this.toDetailTextView = (TextView)findViewById(R.id.to_search_airport_details_text);
	    this.fromTextLayout  = (LinearLayout)findViewById(R.id.from_airport_layout);
	    this.toTextLayout  = (LinearLayout)findViewById(R.id.to_airport_layout);
	    this.fromTextLayout.setVisibility(View.INVISIBLE);
	    this.toTextLayout.setVisibility(View.INVISIBLE);
	    Typeface tFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
	    this.fromTextView.setTypeface(tFace);
	    this.toTextView.setTypeface(tFace);
	    
	    tFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"); 
	    this.fromDetailTextView.setTypeface(tFace);
	    this.toDetailTextView.setTypeface(tFace);
	    
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
