package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class parseXMLHandler{
	
	//String coursesURL = "http://www.inf.ed.ac.uk/teaching/courses/selp/xml/courses.xml";
	List<Course> courses;
	private Course course;
	private String text;
	private final String TAG = "parseXMLHandler";
	
	/*public void XmlPullParserHandler(){
		Log.d(TAG,"courses ArrayList initialised.");
	}
	*/
	public List<Course> parse(InputStream is){
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		courses = new ArrayList<Course>();
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
				//Log.d(TAG,"tagName = "+tagName);
				switch(eventType){
				case XmlPullParser.START_TAG:
					if(tagName.equalsIgnoreCase("course")){
						//create new instance
						//Log.i(TAG,"start tag is course, beginning new Course()");
						course = new Course();
					}
					break;
				case XmlPullParser.TEXT:
					text=parser.getText();
					//Log.i("Parsing Text", text);
					break;
					
				case XmlPullParser.END_TAG:
					if(tagName.equalsIgnoreCase("course")){
						courses.add(course); //
						//Log.i("Parsing","Course added");
						//Log.i("Parsing", "Size of courses : "+ courses.size());
					}
					else if(tagName.equalsIgnoreCase("url")){
						course.setUrl(text);
						//Log.i("Parsing",course.getUrl());
					}
					else if (tagName.equalsIgnoreCase("name")){
						course.setName(text);
						//Log.i("Parsing",course.getName());
					}
					else if (tagName.equalsIgnoreCase("drps")){
						course.setDrps(text);
						//Log.i("Parsing",course.getDrps());
					}
					else if(tagName.equalsIgnoreCase("euclid")){
						course.setEuclid(text);
						//Log.i("Parsing",course.getEuclid());
					}
					else if(tagName.equalsIgnoreCase("acronym")){
						course.setAcronym(text);
						//Log.i("Parsing",course.getAcronym());
					}
					else if(tagName.equalsIgnoreCase("ai")){
						course.setAi(text);
						//Log.i("Parsing",course.getAi());
					}
					else if(tagName.equalsIgnoreCase("cg")){
						course.setCg(text);
						//Log.i("Parsing",course.getCg());
					}
					else if(tagName.equalsIgnoreCase("cs")){
						course.setCs(text);
						//Log.i("Parsing",course.getCs());
					}
					else if(tagName.equalsIgnoreCase("se")){
						course.setSe(text);
						//Log.i("Parsing",course.getSe());
					}
					else if(tagName.equalsIgnoreCase("level")){
						course.setLevel(Integer.parseInt(text));
						//Log.i("Parsing",Integer.toString(course.getLevel()));
					}
					else if(tagName.equalsIgnoreCase("points")){
						course.setPoints(Integer.parseInt(text));
						//Log.i("Parsing",Integer.toString(course.getPoints()));
					}
					else if(tagName.equalsIgnoreCase("year")){
						course.setYear(Integer.parseInt(text));
						//Log.i("Parsing",Integer.toString(course.getYear()));
					}
					else if(tagName.equalsIgnoreCase("deliveryperiod")){
						course.setDeliveryPeriod(text);
						//Log.i("Parsing",course.getDeliveryPeriod());
					}
					else if(tagName.equalsIgnoreCase("lecturer")){
						course.setLecturer(text);
						//Log.i("Parsing",course.getLecturer());
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
		
		return courses;
		
	}
	
}
