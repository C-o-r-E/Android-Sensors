package concordia.ieee.gpstest;

import java.util.List;
import concordia.ieee.gpstest.R;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GpsTest extends Activity {
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Button b1 = (Button)findViewById(R.id.Button01);
        b1.setOnClickListener(new View.OnClickListener()
        {

			public void onClick(View v) 
			{
				LocationManager lMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				derpListener lLis = new derpListener();
				lMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lLis);
				TextView t = (TextView)findViewById(R.id.TextView01);
				List<String> l = lMan.getAllProviders();
				t.setText("Location services available (" + l.size() + "): ");
				for(int i = 0; i<l.size(); i++)
				{
					t.setText(t.getText() + "\n" + (i+1) + " " + l.get(i));
				}
			}
        
        });

    }
    
    public class derpListener implements LocationListener
    {

		public void onLocationChanged(Location location) {
			location.getLatitude();
			location.getLongitude();

			String Text = "My current location is:\n" +
			"\nLatitude = " + location.getLatitude() +
			"\nLongitude = " + location.getLongitude();

			TextView t = (TextView)findViewById(R.id.TextView02);
			t.setText(Text);
		}

		public void onProviderDisabled(String provider) {
			TextView t = (TextView)findViewById(R.id.TextView02);
			t.setText("Gps Disabled");
		}

		public void onProviderEnabled(String provider) {
			TextView t = (TextView)findViewById(R.id.TextView02);
			t.setText("Gps Enabled");
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
}