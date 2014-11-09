package com.codencaffeine.onestopsg;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class EmpProfile extends ActionBarActivity {
	
	Button search;
	int n = 0;
	HttpClient client = new DefaultHttpClient();

    // Set up parameters 
     // Timeout Limit
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		search = (Button)findViewById(R.id.button1);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        HttpConnectionParams.setConnectionTimeout(client.getParams(),
                10000);

		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
		        EditText username;
		        username = (EditText)findViewById(R.id.editText1);
		        
		        // Set up and JSON object & gives the POST message the
		        // "entity" (data) in the form of a string to send to the server. 
		        HttpResponse response;
		        //JSONObject json = new JSONObject();
		        String URL = "http://desilva.net46.net/search_emp.php?empname="+username.getText().toString();
		        
		        try {
		            HttpGet post = new HttpGet(URL);
		            response=client.execute(post);
		            HttpEntity ey=response.getEntity();
		            
		            //post.setEntity(se);
		            //Toast.makeText(getApplicationContext(), json.toString(), Toast.LENGTH_LONG).show();
		            //System.out.println(json.toString());

		            // Execute (send) POST message to target server. 
		            response = client.execute(post);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		        n=1;
				
			}
			
		});
        if (n == 1){
        	getContentPhp();
        	n =0;
        }
	
}
	
	public void getContentPhp() { Thread t = new Thread() { public void run() {
        EditText username;
        username = (EditText)findViewById(R.id.editText1);
		HttpClient client = new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost("http://desilva.net46.net/search_emp.php?empname="+username.getText().toString());
		
		httppost.setHeader("Content-type", "application/json");

		InputStream inputStream = null;
		 
		String result = null;
		try {
		    HttpResponse response = client.execute(httppost);           
		    HttpEntity entity = response.getEntity();

		    inputStream = entity.getContent();
		    // json is UTF-8 by default
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		        sb.append(line + "\n");
		    }
		    result = sb.toString();
		    
		} catch (Exception e) { 
		    Toast.makeText(getApplicationContext(), "Data Not Available", Toast.LENGTH_LONG).show();
		    e.printStackTrace();
		}
		finally {
		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
		}
		
		JSONArray reader1 = null;
		try {
			 reader1 = new JSONArray(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TextView content01;
		content01 = (TextView)findViewById(R.id.textView2);
		TextView content02;
		content02 = (TextView)findViewById(R.id.textView4);
		TextView content03;
		content03 = (TextView)findViewById(R.id.textView6);
		TextView content04;
		content04 = (TextView)findViewById(R.id.textView8);
		TextView content05;
		content05 = (TextView)findViewById(R.id.textView10);
		TextView content06;
		content06 = (TextView)findViewById(R.id.textView12);
		TextView content07;
		content07 = (TextView)findViewById(R.id.textView14);
		TextView content08;
		content08 = (TextView)findViewById(R.id.textView16);
        
		//int n = reader1.length();
		//Toast.makeText(getApplicationContext(), String.valueOf(n), Toast.LENGTH_LONG).show();
		try {
			
		//for (int i = 0; i < n; i++) {
			JSONObject jo = reader1.getJSONObject(0);
			content01.setText(jo.getString("emp_id"));
			content02.setText(jo.getString("emp_name"));
			content03.setText(jo.getString("emp_desk_number"));
			content04.setText(jo.getString("emp_ext"));
			content05.setText(jo.getString("emp_mobile"));
			content06.setText(jo.getString("emp_mail_id"));
			content07.setText(jo.getString("emp_tech_featrs"));
			content08.setText(jo.getString("emp_intrests"));
						
		//}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	};
	t.start();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		
	}

}
