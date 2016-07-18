package com.example.twoviewcompassproject;


import android.app.Activity;
import android.os.Bundle;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

public class TwoViewCompassMainActivity extends Activity implements SensorEventListener{
	private ImageView compassImage;
	private ImageView poleIdentImage;
	private TextView degreeView;
	private TextView poleView;
	private SensorManager directionSensor;
	private float currentDegree = 0f;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two_view_compass_main);
		//Setting compass image
		compassImage = (ImageView) findViewById(R.id.compassImageView);
		//Setting pole image
		poleIdentImage = (ImageView) findViewById(R.id.directionImageView);
		//setting a degree of direction textview
		degreeView = (TextView) findViewById(R.id.directionDegree);
		//setting a pole textview
		poleView = (TextView) findViewById(R.id.poleDirectionView);
		//allows Sensor manager to the sensor service
		directionSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
	}
	@Override
	protected void onResume() {
		super.onResume();
		directionSensor.registerListener(this, directionSensor.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause() {
		super.onPause();

		directionSensor.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		
		//get the numbers of degree of direction
		float degreeOfDir = Math.round(event.values[0]);
		degreeView.setText(getString(R.string.numberofDegree)+ " " + Float.toString(degreeOfDir));
		/* you need to figure out all values that degree of circle of compass and compass that moved around.
		 * understand that degree of direction the compass is clockwise, but not counter-clockwise.  
		 * the compass needs to be set 0 to 360 degree and set the pole  */
		if (degreeOfDir == 0 || degreeOfDir == 360) {
			poleView.setText("Direction is " + getString(R.string.DirNorth));
		} else if (degreeOfDir > 0 && degreeOfDir < 90) {
			if (degreeOfDir > 0 && degreeOfDir < 15) {
				poleView.setText("Direction is " + getString(R.string.DirNorth));
			} else if (degreeOfDir >= 15 && degreeOfDir < 35) {
				poleView.setText("Direction is " + getString(R.string.DirNorthNorthEast));
			} else if (degreeOfDir >= 35 && degreeOfDir < 55) {
				poleView.setText("Direction is " + getString(R.string.DirNorthEast));
			} else if (degreeOfDir >= 55 && degreeOfDir < 75) {
				poleView.setText("Direction is " + getString(R.string.DirEastNorthEast));
			} else if (degreeOfDir >= 75 && degreeOfDir <= 89) {
				poleView.setText("Direction is " + getString(R.string.DirEast));
			}
		} else if (degreeOfDir == 90) {
			poleView.setText("Direction is " + getString(R.string.DirEast));
		} else if (degreeOfDir > 90 && degreeOfDir < 180) {
			if (degreeOfDir > 90 && degreeOfDir < 105) {
				poleView.setText("Direction is " + getString(R.string.DirEast));
			} else if (degreeOfDir >= 105 && degreeOfDir < 125) {
				poleView.setText("Direction is " + getString(R.string.DirEastSouthEast));
			} else if (degreeOfDir >= 125 && degreeOfDir < 145) {
				poleView.setText("Direction is " + getString(R.string.DirSouthEast));
			} else if (degreeOfDir >= 145 && degreeOfDir < 165) {
				poleView.setText("Direction is " + getString(R.string.DirSouthSouthEast));
			} else if (degreeOfDir >= 165 && degreeOfDir <= 179) {
				poleView.setText("Direction is " + getString(R.string.DirSouth));
			}
		} else if (degreeOfDir == 180) {
			poleView.setText("Direction is " + getString(R.string.DirSouth));
		} else if (degreeOfDir > 180 && degreeOfDir < 270) {
			if (degreeOfDir > 180 && degreeOfDir < 195) {
				poleView.setText("Direction is " + getString(R.string.DirSouth));
			} else if (degreeOfDir >= 195 && degreeOfDir < 215) {
				poleView.setText("Direction is " + getString(R.string.DirSouthSouthWest));
			} else if (degreeOfDir >= 215 && degreeOfDir < 235) {
				poleView.setText("Direction is " + getString(R.string.DirSouthWest));
			} else if (degreeOfDir >= 235 && degreeOfDir < 255) {
				poleView.setText("Direction is " + getString(R.string.DirWestSouthWest));
			} else if (degreeOfDir >= 255 && degreeOfDir <= 269) {
				poleView.setText("Direction is " + getString(R.string.DirWest));
			}
		} else if (degreeOfDir == 270) {
			poleView.setText("Direction is " + getString(R.string.DirWest));
		} else if (degreeOfDir > 270 && degreeOfDir < 360) {
			if (degreeOfDir > 270 && degreeOfDir < 285) {
				poleView.setText("Direction is " + getString(R.string.DirWest));
			} else if (degreeOfDir >= 285 && degreeOfDir < 305) {
				poleView.setText("Direction is " + getString(R.string.DirWestNorthWest));
			} else if (degreeOfDir >= 305 && degreeOfDir < 325) {
				poleView.setText("Direction is " + getString(R.string.DirNorthwest));
			} else if (degreeOfDir >= 325 && degreeOfDir < 345) {
				poleView.setText("Direction is " + getString(R.string.DirNorthNorthWest));
			} else if (degreeOfDir >= 345 && degreeOfDir <= 359) {
				poleView.setText("Direction is " + getString(R.string.DirNorth));
			}
		}
		// set the rotation animation to rotate compass image and pole image around. 
		RotateAnimation rotateAnimation = new RotateAnimation(currentDegree, -degreeOfDir, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

		rotateAnimation.setDuration(210);
		rotateAnimation.setFillAfter(true);

		compassImage.startAnimation(rotateAnimation);
		poleIdentImage.startAnimation(rotateAnimation);
		currentDegree = -degreeOfDir;
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
}
