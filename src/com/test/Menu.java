package com.test;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import org.xmlpull.v1.XmlPullParserException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Menu extends Activity{
	
	
	Button search, browse,download;
	Button showTimetable;
	private final String TAG = "Menu start";
	private static final String URL = "http://www.inf.ed.ac.uk/teaching/courses/selp/xml/courses.xml";
	private static final String URL_timetable = "http://www.inf.ed.ac.uk/teaching/courses/selp/xml/timetable.xml";
	private static final String URL_venues = "http://www.inf.ed.ac.uk/teaching/courses/selp/xml/venues.xml";
	private List<Course> courses;
	private List<Lecture> lectures;
	private List<Building> buildings;
	private List<Room> rooms;
    SQLiteHelper database;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		database = new SQLiteHelper(getApplicationContext());
		if (database.checkIfEmpty()){
			AsyncTasks();
		}
		
		Log.d(TAG,"content view set and going");
		search = (Button) findViewById(R.id.bSearch);
		search.setOnClickListener(new View.OnClickListener(){
			public void onClick(View arg0) {
				try{
					Class<?> ourClass = Class.forName("com.test.ParsedWithSearch");
					Intent ourIntent = new Intent(Menu.this,ourClass);
					startActivity(ourIntent);
				} catch(Exception e){
					e.printStackTrace();
					Log.d(TAG,"error starting new intent");
				}
			}
		});
		
		browse = (Button) findViewById(R.id.bBrowse);
		browse.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				try{
					Class<?> ourClass = Class.forName("com.test.ParsedWithFilter");
					Intent ourIntent = new Intent(Menu.this,ourClass);
					startActivity(ourIntent);
				} catch(Exception e){
					e.printStackTrace();
					Log.d(TAG,"error starting new intent");
				}
			}
		});
		
		showTimetable = (Button) findViewById(R.id.bTimetable);
		showTimetable.setOnClickListener(new View.OnClickListener(){
			public void onClick(View arg0) {
				try{
					Class<?> ourClass = Class.forName("com.test.ShowTimetable");
					Intent ourIntent = new Intent(Menu.this,ourClass);
					startActivity(ourIntent);
					
				} catch(Exception e){
					e.printStackTrace();
					Log.d(TAG,"error starting new intent");
				}
			}
		});
		
		download = (Button) findViewById(R.id.bDownload);
		download.setOnClickListener(new View.OnClickListener(){
			public void onClick(View arg0){
				AsyncTasks();
			
			}
		});
		
		
	}
	
	public void AsyncTasks(){
		
		Context context = getApplicationContext();
		CharSequence text = "Downloading information, please wait";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
		
		database.rebuild();
		new DownloadXmlTask().execute(URL);
		new DownloadXmlTimetable().execute(URL_timetable);
		new DownloadVenuesTask().execute(URL_venues);
		new DownloadRoomsTask().execute(URL_venues);
		database.close();

	}
	
	
	/*------------------Courses------------------------------------------------------*/
	private class DownloadXmlTask extends AsyncTask<String, Void, List<Course>>{
		@Override
		protected List<Course> doInBackground(String... urls) {
			try {
				courses = loadXmlFromNetwork(urls[0]); // returns List<Course> courses
			} catch (IOException e){
				Log.e(TAG + " DownloadXmlTask","IO error trying to download xml");
			} catch (XmlPullParserException e){
				Log.e(TAG+ " DownloadXmlTask","xml pull parser exception");
			}
			Log.d(TAG,"The size of courses in List<Course> is " + courses.size());
			return courses;
		}
		@Override
		protected void onPostExecute(List<Course> courses){
			database.getWritableDatabase();
			for (Course course : courses){
				long id = database.createCourse(course);
				if (id == -1){
					database.replaceCourse(course);
				}
			}
			Log.d(TAG,"Courses done");
		}		
	}
	private List<Course> loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException{
		InputStream stream = null;
		parseXMLHandler parseXMLHandler = new parseXMLHandler();
		List<Course> courses = null;
		Log.d("loadXmlFromNetwork","Initialised");
		try{
			Log.d("loadXmlFromNetwork","loading download method");
			stream = downloadUrl(urlString);
			Log.d("loadXmlFromNetwork","PARSE STREAM");
			courses = parseXMLHandler.parse(stream);
		} finally {
			if (stream != null){
				stream.close();
				
			}
		}
		return courses;
	}
	private InputStream downloadUrl(String urlString) throws IOException {
		Log.d("downloadUrl","Starting method");
		URL url = new URL(urlString);
		Log.d("downloadUrl","URL = " + url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		Log.d("downLoadUrl","HttpURLConnection conn established");
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		//start Q
		Log.d("Download Url","Attempting download");
		conn.connect(); 
		Log.d("DownloadUrl","conn is connected");
		InputStream stream = conn.getInputStream();
		Log.d("downloadURL","stream set to conn.getInputStream()");
		return stream;
	}
	/*------------------ Time Table ------------------------------------------------------*/
	private class DownloadXmlTimetable extends AsyncTask<String, Void, List<Lecture>>{

		protected List<Lecture> doInBackground(String... urls) {
			try {
				lectures = loadXmlTimetable(urls[0]); // returns List<Course> courses
			} catch (IOException e){
				Log.e(TAG + " DownloadXmlTask","IO error trying to download xml");
			} catch (XmlPullParserException e){
				Log.e(TAG+ " DownloadXmlTask","xml pull parser exception");
			}
			Log.d(TAG,"The size of lectures in List<Lecture> is " + lectures.size());
			return lectures;
		}
		@Override
		protected void onPostExecute(List<Lecture> lectures){
			database.getWritableDatabase();
			//Log.d(TAG,database.findEditTable());
			//database.getAllLectures();
			//database.createLecture(lectures.get(0));
		
			for (Lecture lecture : lectures){
				long id = database.createLecture(lecture);
				if (id == -1){
					database.replaceLecture(lecture);
				}
			}
			Context context = getApplicationContext();
			CharSequence text = "Finished downloading information";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			Log.d(TAG,"Lectures done");
		}
	}
	private List<Lecture> loadXmlTimetable(String urlString) throws XmlPullParserException, IOException{
		InputStream stream = null;
		parseXmlHandler_timetable parseXmlHandler_timetable = new parseXmlHandler_timetable();
		List<Lecture> lectures = null;;
		Log.d("loadXmlFromNetwork","Initialised");
		try{
			Log.d("loadXmlFromNetwork","loading download method");
			stream = downloadUrl(urlString);
			Log.d("loadXmlFromNetwork","PARSE STREAM");
			lectures = parseXmlHandler_timetable.parse(stream);
		} finally {
			if (stream != null){
				stream.close();
			}
		}
		return lectures;
	}
	
	/* ----------------------------------- Venues -------------------------------------------------*/
	/*------------------ Buildings ------------------------------------------------------*/
	
	private class DownloadVenuesTask extends AsyncTask<String, Void, List<Building>>{
		@Override
		protected List<Building> doInBackground(String... urls) {
			try {
				buildings = loadXmlVenuesFromNetwork(urls[0]); // returns List<Course> courses
			} catch (IOException e){
				Log.e(TAG + " DownloadXmlTask","IO error trying to download xml");
			} catch (XmlPullParserException e){
				Log.e(TAG+ " DownloadXmlTask","xml pull parser exception");
			}
			//Log.d(TAG,"The size of courses in List<Course> is " + courses.size());
			return buildings;
		}
		@Override
		protected void onPostExecute(List<Building> buildings){
			database.getWritableDatabase();
			for (Building building : buildings){
				long id = database.createBuilding(building);
			}
			Log.d(TAG,"Buildings done");
		}		
	}
	private List<Building> loadXmlVenuesFromNetwork(String urlString) throws XmlPullParserException, IOException{
		InputStream stream = null;
		parseXMLHandlerVenues parseXMLHandlerVenues = new parseXMLHandlerVenues();
		List<Building> buildings = null;
		Log.d("loadXmlFromNetwork","Initialised");
		try{
			Log.d("loadXmlFromNetwork","loading download method");
			stream = downloadUrl(urlString);
			Log.d("loadXmlFromNetwork","PARSE STREAM");
			buildings = parseXMLHandlerVenues.parse(stream);
		} finally {
			if (stream != null){
				stream.close();
				
			}
		}
		return buildings;
	}
	
	/*------------------ Rooms  ------------------------------------------------------*/
	
	private class DownloadRoomsTask extends AsyncTask<String, Void, List<Room>>{
		@Override
		protected List<Room> doInBackground(String... urls) {
			try {
				rooms = loadXmlRoomsFromNetwork(urls[0]); // returns List<Course> courses
			} catch (IOException e){
				Log.e(TAG + " DownloadXmlTask","IO error trying to download xml");
			} catch (XmlPullParserException e){
				Log.e(TAG+ " DownloadXmlTask","xml pull parser exception");
			}
			//Log.d(TAG,"The size of courses in List<Course> is " + courses.size());
			return rooms;
		}
		@Override
		protected void onPostExecute(List<Room> rooms){
			database.getWritableDatabase();
			for (Room room : rooms){
				long id = database.createRoom(room);
			}
			Log.d(TAG,"Rooms done");
		}		
	}
	private List<Room> loadXmlRoomsFromNetwork(String urlString) throws XmlPullParserException, IOException{
		InputStream stream = null;
		parseXMLHandlerRooms parseXMLHandlerRooms = new parseXMLHandlerRooms();
		List<Room> rooms = null;
		Log.d("loadXmlFromNetwork","Initialised");
		try{
			Log.d("loadXmlFromNetwork","loading download method");
			stream = downloadUrl(urlString);
			Log.d("loadXmlFromNetwork","PARSE STREAM");
			rooms = parseXMLHandlerRooms.parse(stream);
		} finally {
			if (stream != null){
				stream.close();
				
			}
		}
		return rooms;
	}
	
	

	
}
