package com.test;


import java.util.ArrayList;
import java.util.List;

import com.test.ParsedWithSearch.bsAdapter;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ParsedWithFilter extends Activity{
	
	SQLiteHelper database;
	private final String TAG = "Parsed With Search";
	
	private List<String> names;
	EditText editText;
	ListView listView;
	Spinner filter;
	String[] years = {"All Years","Year 1"," Year 2","Year 3","Year 4","Year 5"};
	String selected = "All Years";
	
	private ArrayList<String> courses;

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parsed_with_filter);
		
		filter = (Spinner) findViewById(R.id.spinner1);
		listView = (ListView) findViewById(R.id.list);
		
		database = new SQLiteHelper(getApplicationContext());
		names = database.getAllCourseNames();
		listView.setAdapter(new ArrayAdapter<String>(ParsedWithFilter.this,R.layout.base, names));
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, years);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		filter.setAdapter(dataAdapter);
		
	    
	    listView.setOnItemClickListener(new OnItemClickListener(){
	    	public void onItemClick(AdapterView<?> l, View v, int position, long id){
	    		//super.onListItemClick(l, v, position, id);
	    		Log.d(TAG, "Position clicked = "+names.get(position) + " and Position = "+ position);
	    		Log.d(TAG, "Adapter View = "+l+", View = "+ v+", position = "+ position +", id = "+ id);
	    		String selectedFromList =(String) (listView.getItemAtPosition(position));
	    		Log.d(TAG,"selected from list = " + selectedFromList);
	    		//Course clicked = database.getCourseByName(names.get(position));
	    		Bundle b=new Bundle();
	    		b.putString("course",selectedFromList);
	    		try{
	    			Class ourClass = Class.forName("com.test.CourseView");
	    			Intent ourIntent = new Intent(ParsedWithFilter.this, ourClass);
	    			ourIntent.putExtras(b);
	    			Log.d(TAG,"Extras put");
	    			startActivity(ourIntent);
	    		} catch (ClassNotFoundException e){
	    			e.printStackTrace();
	    		}
	    	}
	    });
	    filter.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> l, View v, int position, long id) {
				Log.d(TAG, "View = "+l+", V = "+v+", Position = "+position+", id = " + id);
				//names = database.getCoursesByYear()
				String selectedFromList = (String) (filter.getItemAtPosition(position));
				Log.d(TAG, "Selected from list = "+ selectedFromList);
				if (position != 0){
					names = database.getCoursesByYear(position);
					listView.setAdapter(new ArrayAdapter<String>(ParsedWithFilter.this,R.layout.base, names));
				} else {
					names = database.getAllCourseNames();
					listView.setAdapter(new ArrayAdapter<String>(ParsedWithFilter.this,R.layout.base, names));
				}
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    
	}

	
	
}

