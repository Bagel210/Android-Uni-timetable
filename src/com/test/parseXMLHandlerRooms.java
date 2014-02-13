package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class parseXMLHandlerRooms{
	
	//String coursesURL = "http://www.inf.ed.ac.uk/teaching/courses/selp/xml/venues.xml";
	List<Room> rooms;
	private Room room;
	private String text;
	private final String TAG = "parseXMLHandler Rooms";
	boolean doneWithBuildings = false;
	
	/*public void XmlPullParserHandler(){
		Log.d(TAG,"courses ArrayList initialised.");
	}
	*/
	public List<Room> parse(InputStream is){
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		rooms = new ArrayList<Room>();
		try {
			Log.d(TAG,"trying new instance of XmlPullParserFactory");
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();
			parser.setInput(is, null);
			Log.d(TAG,"Input Stream is: " + is);
			int eventType = parser.getEventType();
			Log.d(TAG,"Beginning while loop");
			while (eventType != XmlPullParser.END_DOCUMENT){
				String tagName = parser.getName();
				switch(eventType){
				case XmlPullParser.START_TAG:
				
					if(tagName.equalsIgnoreCase("room")){
						doneWithBuildings = true;
						room = new Room();
					}
					
					break;
					
				case XmlPullParser.TEXT:
					if (doneWithBuildings == false){
						break;
					}
					text=parser.getText();
					break;
					
				case XmlPullParser.END_TAG:
					if (doneWithBuildings == false){
						break;
					}
					else if(tagName.equalsIgnoreCase("room")){
						rooms.add(room); 
					}
					else if(tagName.equalsIgnoreCase("name")){
						room.setName(text);
						
					}
					else if (tagName.equalsIgnoreCase("description")){
						room.setDescription(text);
					}
					break;
					
				default:
					Log.d("Default","Break");
					break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		return rooms;
		
	}
	
}
