package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class parseXMLHandlerVenues{
	
	//String coursesURL = "http://www.inf.ed.ac.uk/teaching/courses/selp/xml/venues.xml";
	List<Building> buildings;
	List<Room> rooms;
	private Building building;
	private String text;
	private final String TAG = "parseXMLHandler Venues";
	
	/*public void XmlPullParserHandler(){
		Log.d(TAG,"courses ArrayList initialised.");
	}
	*/
	public List<Building> parse(InputStream is){
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		buildings = new ArrayList<Building>();
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
					if(tagName.equalsIgnoreCase("building")){
						building = new Building();
					}
					break;
				case XmlPullParser.TEXT:
					text=parser.getText();
					break;
					
				case XmlPullParser.END_TAG:
					if(tagName.equalsIgnoreCase("building")){
						buildings.add(building); 
					}
					else if(tagName.equalsIgnoreCase("name")){
						building.setName(text);
					}
					else if (tagName.equalsIgnoreCase("description")){
						building.setDescription(text);
					}
					else if (tagName.equalsIgnoreCase("map")){
						building.setMap(text);
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
		
		return buildings;
		
	}
	
}
