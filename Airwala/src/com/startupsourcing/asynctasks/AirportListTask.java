package com.startupsourcing.asynctasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import com.startupsourcing.airwala.AirportList;

import java.util.ArrayList;
import java.util.List;

//  
public class AirportListTask extends AsyncTask<String, Void, String> {
	
	private AirportList context;
	private String message;
	String result = "fail";
	private List<String> listdata;
	public AirportListTask(AirportList context, String searchString){
		this.message = searchString;
		this.context = context;
	}
	@Override
	protected String doInBackground(String...params) {
//		this.myTextView = params[0];
		return makeHttpRequest();
	}
	
	final String makeHttpRequest()
	{
		String url = "http://api.hop2.com/airticket/v1/airport.aspx?cid=I3I6P1&customerSessionId=12345&string="+message+"";
		BufferedReader inStream = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpRequest = new HttpGet(url);
			
			HttpResponse response = httpClient.execute(httpRequest);
			inStream = new BufferedReader(
					new InputStreamReader(
							response.getEntity().getContent()));

			StringBuffer buffer = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = inStream.readLine()) != null) {
				buffer.append(line + NL);
			}
			inStream.close();

			result = buffer.toString();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try{
		JSONObject json = new JSONObject(result);
		JSONObject resultJsonObject = json.getJSONObject("airportListResponse");
		JSONArray arr = resultJsonObject.getJSONArray("airports");
		listdata = new ArrayList<String>();     
		if (arr != null) { 
		   for (int i=0;i<arr.length();i++){ 
		    listdata.add(arr.get(i).toString());
		    Log.v(+i+ "is:",arr.get(i).toString());
		   } 
		}
		this.context.setAirportList(listdata);
		}catch(Exception e){
			e.printStackTrace();
		}
//		Log.v("airports array is" + Arrays.toString(listdata));
		return result;
	}	
	
	protected void onPostExecute(String page)
	{    	
		this.context.loadAirportList();
//    	myTextView.setText(page);    	
	}	
}
