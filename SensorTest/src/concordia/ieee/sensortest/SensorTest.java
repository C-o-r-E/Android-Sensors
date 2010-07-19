package concordia.ieee.sensortest;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorTest extends Activity implements SensorEventListener
{
	// //////////////////////////////////
	// / Take care of some UI stuff
	// /////////////////////////////////
	TextView actxt, magtxt, oritxt, tmptxt;
	SensorManager sMan;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sMan = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		actxt = (TextView) findViewById(R.id.TextView01);
		magtxt = (TextView) findViewById(R.id.TextView02);
		oritxt = (TextView) findViewById(R.id.TextView03);
		tmptxt = (TextView) findViewById(R.id.TextView04);

	}

	@Override
	protected void onResume()
	{
		super.onResume();
		List<Sensor> sList = sMan.getSensorList(Sensor.TYPE_ALL);
		for(int i=0; i<sList.size(); i++)
		{
			sMan.registerListener(this, sList.get(i), SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		
	}

	@Override
	protected void onStop()
	{
		// unregister listener
		sMan.unregisterListener(this);
		super.onStop();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		Log.d("SensorTest","onAccuracyChanged: " + sensor + ", accuracy: " + accuracy);
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		{
			actxt.setText("" + event.sensor.getName() + "\n\t" +
					"Gx: " + event.values[0] + "\n\t" +
					"Gy: " + event.values[1] + "\n\t" +
					"Gz: " + event.values[2] + "\n");
		}
		else if(event.sensor.getType() == Sensor.TYPE_ORIENTATION)
		{
			oritxt.setText("" + event.sensor.getName() + "\n\t" +
					"Azimuth: " + event.values[0] + "\n\t" +
					"Pitch: " + event.values[1] + "\n\t" +
					"Roll: " + event.values[2] + "\n");
		}
		else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
		{
			magtxt.setText("" + event.sensor.getName() + "\n\t" +
					"x: " + event.values[0] + " uTeslas\n\t" +
					"y: " + event.values[1] + " uTeslas\n\t" +
					"z: " + event.values[2] + " uTeslas\n");
		}
		else if(event.sensor.getType() == Sensor.TYPE_TEMPERATURE)
		{
			tmptxt.setText("" + event.sensor.getName() + "\n\t"
					+ event.values[0] + "degrees C\n");
		}
		
	}

}