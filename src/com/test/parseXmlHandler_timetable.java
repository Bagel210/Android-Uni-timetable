package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import android.util.Log;

public class parseXmlHandler_timetable {
	
	List<Lecture> lectures;
	private Lecture lecture;
	private String text;
	private final String TAG = "TimeTable Parser";
	private String pSemester;
	private String pDay;
	private String pStart;
	private String pFinish;
	
	public List<Lecture> parse(InputStream is){
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		// Array list of type Lecture
		lectures = new ArrayList<Lecture>();
		try{
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();
			parser.setInput(is,null);
			Log.d(TAG,"Input Stream is: "+is);
			int eventType = parser.getEventType();
			Log.d(TAG,"Beginning While loop");
			while(eventType != XmlPullParser.END_DOCUMENT){
				String tagName = parser.getName();
				switch(eventType){
				case XmlPullParser.START_TAG:
					if(tagName.equalsIgnoreCase("semester")){
						pSemester = parser.getAttributeValue(null,"number");
					}
					if(tagName.equalsIgnoreCase("day")){
						pDay = parser.getAttributeValue(null,"name");
					}
					if(tagName.equalsIgnoreCase("time")){
						pStart = parser.getAttributeValue(null,"start");
						pFinish = parser.getAttributeValue(null,"finish");
					}
					if(tagName.equalsIgnoreCase("lecture")){
						lecture = new Lecture();
						lecture.setSemester(pSemester);
						lecture.setDay(pDay);
						lecture.setStart(pStart);
						lecture.setFinish(pFinish);
					}
					break;
				case XmlPullParser.TEXT:
					text=parser.getText();
					break;
				case XmlPullParser.END_TAG:
					if(tagName.equalsIgnoreCase("lecture")){
						lectures.add(lecture);
						/*
						Log.i(TAG, "Adding lecture "+ lecture.getCourse()+" to arraylist");
						Log.i("Lecture", lecture.getSemester());
						Log.i("Lecture",lecture.getDay());
						Log.i("Lecture",lecture.getStart());
						Log.i("Lecture",lecture.getFinish());
						Log.i("Lecture",lecture.getCourse());
						Log.i("Lecture",lecture.getDay());
						Log.i("Lecture",lecture.getRoom());
						Log.i("Lecture",lecture.getBuilding());
						Log.i("Lecture","Year 1 = "+lecture.getYear1());
						Log.i("Lecture","Year 2 = "+lecture.getYear2());
						Log.i("Lecture","Year 3 = "+lecture.getYear3());
						Log.i("Lecture","Year 4 = "+lecture.getYear4());
						Log.i("Lecture","Year 5 = "+lecture.getYear5());
						Log.i("Lecture",lecture.getComment());
						*/
						
					}
					else if (tagName.equalsIgnoreCase("course")){
						lecture.setCourse(text);
					}
					else if (tagName.equalsIgnoreCase("year")){
						if (text.equals("1"))
							lecture.setYear1(1);
						else if (text.equals("2"))
							lecture.setYear2(1);
						else if (text.equals("3"))
							lecture.setYear3(1);
						else if (text.equals("4"))
							lecture.setYear4(1);
						else if (text.equals("5"))
							lecture.setYear5(1);
					}
					else if (tagName.equalsIgnoreCase("room")){
						lecture.setRoom(text);
					}
					else if(tagName.equalsIgnoreCase("building")){
						lecture.setBuilding(text);
					}
					else if(tagName.equalsIgnoreCase("comment")){
						lecture.setComment(text);
					}
					break;
				default:
					Log.i(TAG,"break");
					break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e){
			Log.e(TAG, "Pull Parser Error");
			e.printStackTrace();
		} catch (IOException e){
			Log.e(TAG, "IO error");
			e.printStackTrace();
		}
		
		return lectures;
	}

}
