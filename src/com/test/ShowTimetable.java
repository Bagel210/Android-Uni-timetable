package com.test;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShowTimetable extends Activity{
	
	SQLiteHelper database;
	private final String TAG = "ShowTimetable";
	private List<String> timetable;
	private List<Lecture> classes;
	ListView listView;
	Button switchSemester;
	int semester; //
	int otherSemester; // set these to null.
	String acronym, start, end, day, string;
	Full data;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.semester_view);
		final Calendar c = Calendar.getInstance();
	    int mMonth = c.get(Calendar.MONTH);
	    Log.d(TAG, "The Month is "+mMonth);
	    if (mMonth <= 11 && mMonth >= 7){
	    	semester = 1;
	    	otherSemester = 2; 
	    } else {
	    	semester = 2;
	    	otherSemester = 1;
	    }
		listView = (ListView) findViewById(R.id.lvTimetable);
		switchSemester = (Button) findViewById(R.id.bChangeSemester);
		
		// check the current date and use that to determine semester
		database = new SQLiteHelper(getApplicationContext());


		
		classes = database.getAllLecturesFromTimetable();
		
		switchSemester.setText("Showing Semester "+semester+ ", click here to switch");
		
		//Log.d(TAG,"size of classes is " + classes.size());
		if (classes.isEmpty()){
			Context context = getApplicationContext();
			CharSequence text = "You have not added any classes to your timetable yet!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			finish();
		}
		switchSemester.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				int temp = semester;
				semester = otherSemester;
				otherSemester = temp;
				switchSemester.setText("Showing Semester "+semester+ ", click here to switch");
				timetable = database.getTimetable(semester);
				listView.setAdapter(new ArrayAdapter<String>(ShowTimetable.this,R.layout.base,timetable));
				
			}
		});
		 listView.setOnItemClickListener(new OnItemClickListener(){
		    	public void onItemClick(AdapterView<?> l, View v, int position, long id){
		    		String selectedFromList = (String) (listView.getItemAtPosition(position));
		    		Pattern dayIs = Pattern.compile("[A-Z][a-z][a-z]*");
		    		Matcher matchDay = dayIs.matcher(selectedFromList);
		    		while(matchDay.find()){
		    			day = matchDay.group();
		    			Log.d(TAG, "MatchDay = " + day);
		    		}
		    		Pattern course = Pattern.compile("(INF)?[0-9]?[^ouehr\t]*[0-9]?[A-Z]+[0-9]?");
		    		Matcher matchCourse = course.matcher(selectedFromList);
		    		while (matchCourse.find()){
		    			acronym = matchCourse.group();
		    			Log.d(TAG,"MatchCourse = " + acronym);
		    		}
		    		Pattern time = Pattern.compile("[0-9][0-9]:[0-9][0-9]");
		    		Matcher matchTime = time.matcher(selectedFromList);
		    		boolean matched = false;
		            while (matchTime.find()) {
		            	if (matched ==false){
		            		start = matchTime.group(0);
		            		Log.d(TAG, "first = " + start);
		            		matched = true;
		            	}
		            	else{
		            		end = matchTime.group(0);
		            		Log.d(TAG,"Second = "+end);
		            	}
		            } 
		            
		            final Dialog dialog = new Dialog(ShowTimetable.this);
		            Log.d(TAG,"day = "+day+", acronym = "+ acronym+", start = "+start);
		            data = database.getLectureWithData(day, acronym, start);
		            Log.d(TAG, "course = " +data.getCourse() + ", "+ acronym+" was clicked");
					dialog.setContentView(R.layout.dialog);
					Course gettingCourseName = database.getCourseByAcronym(data.getCourse());
					dialog.setTitle(data.getCourse());
					
					TextView text = (TextView) dialog.findViewById(R.id.tv1);
					//Log.d(TAG,"get Room" + data.getRoom_description() + "check if null: " + data.getRoom_description().isEmpty());
					
					String stringFull = gettingCourseName.getName() + "\n" +
							"Starts at " + data.getStart() + "\n" +
							"At "+ data.getBuilding_description() + "\n" +
							"In "+ data.getRoom_name() +" " + data.getRoom_description() + "\n";
					
					String stringNotFull = gettingCourseName.getName() + "\n" +
							"Starts at " + data.getStart() + "\n" +
							"At "+ data.getBuilding_description() + "\n" +
							"In "+ data.getRoom() + "\n";
							
					if (data.getRoom_name().equals("room")){
						string = stringNotFull;
					} else {
						string = stringFull;
					}
					
					text.setText(string);
			
					Button dialogButton = (Button) dialog.findViewById(R.id.bOK);
					// if button is clicked, close the custom dialog
					dialogButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}
					});
					
					Button location = (Button) dialog.findViewById(R.id.bLoc);
					location.setOnClickListener(new OnClickListener(){
						@Override
						public void onClick(View arg0) {
							dialog.dismiss();
							Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getMap()));
							startActivity(browserIntent);
						}
						
					});
		 
					dialog.show();
				
			}
			
		});
		// do an onClickListener that sets the text of the button to "change to semester other" and listView is repopulated to be the other semester;
		timetable = database.getTimetable(semester);
		listView.setAdapter(new ArrayAdapter<String>(ShowTimetable.this,R.layout.base, timetable));
		
		
		
	}
	

}

