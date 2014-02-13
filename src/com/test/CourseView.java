package com.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CourseView extends Activity{

	private final String TAG = "View Course";
	Button bAdd;
	TextView tvCourse, tvAcronym, tvUrl, tvDrps, tvEuclid, tvLevel, tvPoints, tvYear, tvDp, tvLecturer, tvAi, tvCg, tvSe, tvCs;
	SQLiteHelper database;
	Course thisCourse;
	Boolean inTimetable;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_view);
		database = new SQLiteHelper(getApplicationContext());
		
		tvCourse = (TextView) findViewById(R.id.tvCourse);
		tvAcronym = (TextView) findViewById(R.id.tvAcronym);
		tvUrl = (Button) findViewById(R.id.tvUrl);
		tvDrps = (TextView) findViewById(R.id.tvDrps);
		tvEuclid = (TextView) findViewById(R.id.tvEuclid);
		tvLevel = (TextView) findViewById(R.id.tvLevel);
		tvPoints = (TextView) findViewById(R.id.tvPoints);
		tvYear = (TextView) findViewById(R.id.tvYear);
		tvLecturer = (TextView) findViewById(R.id.tvLecturer);
		tvDp = (TextView) findViewById(R.id.tvDp);
		tvAi = (TextView) findViewById(R.id.tvAi);
		tvCg = (TextView) findViewById(R.id.tvCg);
		tvSe = (TextView) findViewById(R.id.tvSe);
		tvCs = (TextView) findViewById(R.id.tvCs);
		bAdd = (Button) findViewById(R.id.bAdd);
		
		Bundle b=this.getIntent().getExtras();
		String chosen=b.getString("course");
		Log.d(TAG,"Chosen = "+ chosen);
		thisCourse = database.getCourseByName(chosen);
		Log.i(TAG,thisCourse.getAcronym());
		inTimetable = database.checkIfInTimetable(thisCourse.getAcronym());
		Log.d(TAG, "inTimetable after check = " +inTimetable); //tt
		setText();
		bAdd.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				Log.d(TAG, "inTimetable on click = " +inTimetable); //tt
				if (inTimetable){
					long id = database.deleteClassesByAcronym(thisCourse.getAcronym());
					Log.d(TAG,id + " Lectures deleted");
					bAdd.setText("Add to Timetable");
					inTimetable = false;
				} else if(inTimetable == false){
					Log.d(TAG, "inTimetable = " +inTimetable); //tt
					database.addClassesByAcronym(thisCourse.getAcronym());
					bAdd.setText("Delete from Timetable");
					inTimetable = true;
				}
			}
		});	
		tvUrl.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(thisCourse.getUrl()));
				Log.d(TAG,"URL = " +thisCourse.getUrl());
				startActivity(browserIntent);
			}
			
		});
	}
	
	@Override
	protected void onPause(){
	finish();
	        super.onPause();
	}

	private void setText() {
		tvCourse.setText(thisCourse.getName());
		
		tvAcronym.setText(thisCourse.getAcronym()); 
		tvDrps.setText(thisCourse.getDrps()); 
		
		// Need to specify what they are at this stage
		tvEuclid.setText("Euclid : "+thisCourse.getEuclid());
		tvLevel.setText("Level : "+thisCourse.getLevel());
		tvPoints.setText("Points : "+thisCourse.getPoints());
		tvYear.setText("Year : "+thisCourse.getYear());
		tvDp.setText("Delivery Period : "+thisCourse.getDeliveryPeriod());
		tvLecturer.setText("Lectured by : "+thisCourse.getLecturer());
		
		if (thisCourse.getAi().equals("AI"))
			tvAi.setText("Artificial Intelligence");
		if (thisCourse.getCg().equals("CG"))
			tvCg.setText("Cognitive Science");
		if (thisCourse.getSe().equals("SE")){
			tvSe.setText("Software Engineering");
		if (thisCourse.getCs().equals("CS"))
				tvCs.setText("Computer Science");
		}
		if(inTimetable){
			bAdd.setText("Remove from Timetable");
		} else {
			bAdd.setText("Add to Timetable");
		}
		
	}

}
