package com.test;


import java.util.ArrayList;
import java.util.List;


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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ParsedWithSearch extends Activity{
	
	SQLiteHelper database;
	private final String TAG = "Parsed With Search";
	
	private List<String> names;
	EditText editText;
	ListView listView;
	
	private ArrayList<String> array_sort;
	
    int textLength=0;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parsed_with_search);
		
		editText = (EditText) findViewById(R.id.inputSearch);
		listView = (ListView) findViewById(R.id.list);
		
		database = new SQLiteHelper(getApplicationContext());
		names = database.getAllCourseNames();
		
		array_sort=new ArrayList<String> (names);
		listView.setAdapter(new bsAdapter(this));
	    //setListAdapter(new bsAdapter(this));
	    
	    editText.addTextChangedListener(new TextWatcher(){
	    	public void afterTextChanged(Editable s){
	    	}
	    	public void beforeTextChanged(CharSequence s, int start, int count, int after){
	    	}
	    	
	    	public void onTextChanged(CharSequence s,int start, int before, int count){
	    		textLength = editText.getText().length();
	    		array_sort.clear();
	    		//listView.get();
	    		for (int i = 0; i < names.size(); i++){ // this has been changed tomatch the datatype
	    			if (textLength <= names.get(i).length()){ // also been changed
	    				if(names.get(i).toLowerCase().contains(editText.getText().toString().toLowerCase().trim())){
	    					array_sort.add(names.get(i));
	    				}
	    			}	
	    		}
	    	AppendList(array_sort);
	    	}
	    });
	    listView.setOnItemClickListener(new OnItemClickListener(){
	    	public void onItemClick(AdapterView<?> l, View v, int position, long id){
	    		//super.onListItemClick(l, v, position, id);
	    		Log.d(TAG, "Position clicked = "+names.get(position) + " and Position = "+ position);
	    		Log.d(TAG, "Adapter View = "+l+", View = "+ v+", position = "+ position +", id = "+ id);
	    		String selectedFromList = (String) (listView.getItemAtPosition(position));
	    		Log.d(TAG,"selected from list = " + selectedFromList);
	    		//Course clicked = database.getCourseByName(names.get(position));
	    		Bundle b=new Bundle();
	    		b.putString("course",selectedFromList);
	    		try{
	    			Class ourClass = Class.forName("com.test.CourseView");
	    			Intent ourIntent = new Intent(ParsedWithSearch.this, ourClass);
	    			ourIntent.putExtras(b);
	    			Log.d(TAG,"Extras put");
	    			startActivity(ourIntent);
	    		} catch (ClassNotFoundException e){
	    			e.printStackTrace();
	    		}
	    	}
	    });
	    
	    
	}
	
	public void AppendList(ArrayList<String> str)
	{
	    //setListAdapter(new bsAdapter(this));
	    listView.setAdapter(new bsAdapter(this));
	}
	
	public class bsAdapter extends BaseAdapter{
		
		Activity cntx;
		
        public bsAdapter(Activity context){
            this.cntx=context;
        }
		@Override
		public int getCount(){
			return array_sort.size();
		}

		@Override
		public Object getItem(int position) {
			   return array_sort.get(position);
		}

		@Override
		public long getItemId(int arg0) {
			 return array_sort.size();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row=null;
            LayoutInflater inflater=cntx.getLayoutInflater();
            row=inflater.inflate(R.layout.list_item, null);
            TextView tv = (TextView) row.findViewById(R.id.item_in_list);
            tv.setText(array_sort.get(position));  
        return row;
		}
	}
	
}

