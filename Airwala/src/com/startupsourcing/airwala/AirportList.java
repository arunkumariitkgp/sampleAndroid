package com.startupsourcing.airwala;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

import com.startupsourcing.asynctasks.AirportListTask;


public class AirportList extends Activity {

	private List<String> airportList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_airport_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.airport_list, menu);
		return true;
	}

	public List<String> getAirportList() {
		return this.airportList;
	}
	
	public void setAirportList(List<String> airList) {
		this.airportList = airList;
	}
	
	public void searchAirport(View view) {
		Log.v("searchAirport calleddddd","YES");
		EditText eText = (EditText)findViewById(R.id.airport_list);
		Log.v("text is:",eText.getText().toString());
		new AirportListTask(this, eText.getText().toString()).execute("");
	}
	
	public void loadAirportList(){
		Log.v("loadAirportList calleddddd","YES");
		ListView listView = (ListView)findViewById(R.id.airport_list_listview);
	    StableArrayAdapter adapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1,this.airportList);
	    listView.setAdapter((ListAdapter) adapter);
	}
	
	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

}
