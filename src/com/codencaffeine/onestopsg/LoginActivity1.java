package com.codencaffeine.onestopsg;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class LoginActivity1 extends ActionBarActivity implements OnClickListener {
	
	EditText username;
	EditText password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_activity1);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        
                
        
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
				doPost(username.getText().toString(), password.getText().toString());
			
				
			}
		});

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_activity1, menu);
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

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_login_activity1,
					container, false);
			return rootView;
		}
	}
	
	private void doPost(final String sUserName, final String sPassword) { Thread t = new Thread() { public void run() {
        // By creating a HttpClient object, Android is
        // now web service client sending data to a HTTP server.
        HttpClient client = new DefaultHttpClient();

        // Set up parameters 
        HttpConnectionParams.setConnectionTimeout(client.getParams(),
                10000); // Timeout Limit

        // Set up and JSON object & gives the POST message the
        // "entity" (data) in the form of a string to send to the server. 
        HttpResponse response;
        JSONObject json = new JSONObject();
        String URL = "http://desilva.net46.net/login.php";
        try {
            HttpPost post = new HttpPost(URL);
            json.put("username", sUserName);
            json.put("password", sPassword);
            StringEntity se = new StringEntity("JSON: "
                    + json.toString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/json"));
            post.setEntity(se);
            //System.out.println(json.toString());

            // Execute (send) POST message to target server. 
            response = client.execute(post);
            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
            if(response!=null)
            {
                InputStream in = response.getEntity().getContent(); //Get the data in the entity

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
};
t.start();  
}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	

}
