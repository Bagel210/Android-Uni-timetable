package com.test;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
	
	private static final String TAG = "SQLiteHelper";
	
	/*------------------- Databae Information ----------------------*/
	private static final String DATABASE_NAME = "timetable.db";
	private static final int DATABASE_VERSION = 1;
	/*------------------- Tables ----------------------*/
	public static final String TABLE_COURSES = "courses";
	public static final String TABLE_LECTURES = "lectures";
	public static final String TABLE_TIMETABLE = "timetable";
	public static final String TABLE_BUILDINGS = "buildings";
	public static final String TABLE_ROOMS = "rooms";
	/*------------------- Columns for Table Courses ----------------------*/
	public static final String COLUMN_URL = "url";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DRPS = "drps";
	public static final String COLUMN_EUCLID = "euclid";
	public static final String COLUMN_ACRONYM = "acronym";
	public static final String COLUMN_AI = "ai";
	public static final String COLUMN_CG = "cg";
	public static final String COLUMN_CS = "cs";
	public static final String COLUMN_SE = "se";
	public static final String COLUMN_LEVEL = "level";
	public static final String COLUMN_POINTS = "points";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_DELIVERYPERIOD = "deliveryperiod";
	public static final String COLUMN_LECTURER = "lecturer";
	/*------------------- Columns for Table Lectures ----------------------*/
	public static final String COLUMN_SEMESTER = "semester";
	public static final String COLUMN_DAY = "day";
	public static final String COLUMN_COURSE = "course";
	public static final String COLUMN_START = "start";
	public static final String COLUMN_FINISH = "finish";
	public static final String COLUMN_BUILDING = "building";
	public static final String COLUMN_ROOM = "room";
	public static final String COLUMN_YEAR1 = "year1";
	public static final String COLUMN_YEAR2 = "year2";
	public static final String COLUMN_YEAR3 = "year3";
	public static final String COLUMN_YEAR4 = "year4";
	public static final String COLUMN_YEAR5 = "year5";
	public static final String COLUMN_COMMENT = "comment";
	/*------------------- Columns for Table Buildings ----------------------*/
	public static final String COLUMN_BUILDING_NAME = "building_name";
	public static final String COLUMN_DESCRIPTION = "building_description";
	public static final String COLUMN_MAP = "building_map";
	/*------------------- Columns for Table Rooms ----------------------*/
	public static final String COLUMN_ROOM_NAME = "room_name";
	public static final String COLUMN_ROOM_DESCRIPTION = "room_description";
	
	/*------------------- Create Table Courses ----------------------*/
	private static final String CREATE_TABLE_COURSES = "CREATE TABLE " + TABLE_COURSES+ "(" +
	COLUMN_ACRONYM +" TEXT PRIMARY KEY, "+ 
	COLUMN_DRPS+ " TEXT, "+
	COLUMN_EUCLID +" TEXT, "+ 
	COLUMN_SE +" TEXT, " + 
	COLUMN_LECTURER + " TEXT," + 
	COLUMN_URL + " TEXT NOT NULL, " + 
	COLUMN_CS +" TEXT, " + 
	COLUMN_DELIVERYPERIOD +" TEXT, "+
	COLUMN_AI +" TEXT, " +
	COLUMN_LEVEL +" INTEGER, " + 
	COLUMN_NAME +" TEXT NOT NULL, " + 
	COLUMN_YEAR +" TEXT," +
	COLUMN_CG +" TEXT, "+ 
	COLUMN_POINTS +" INTEGER" +
	")";
	/*------------------- Create Table Lectures ----------------------*/
	private static final String CREATE_TABLE_LECTURES = "CREATE TABLE " + TABLE_LECTURES + "(" +
	"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	COLUMN_SEMESTER + " TEXT, " +
	COLUMN_DAY + " TEXT, " +
	COLUMN_COURSE + " TEXT, " +
	COLUMN_START + " TEXT, " +
	COLUMN_FINISH + " TEXT, " +
	COLUMN_BUILDING + " TEXT, " +
	COLUMN_ROOM + " TEXT, " +
	COLUMN_YEAR1 + " INTEGER, " +
	COLUMN_YEAR2 + " INTEGER, " +
	COLUMN_YEAR3 + " INTEGER, " +
	COLUMN_YEAR4 + " INTEGER, " +
	COLUMN_YEAR5 + " INTEGER, " +
	COLUMN_COMMENT + " TEXT" + 
	")";
	/*------------------- Create Table timetable ----------------------*/
	private static final String CREATE_TABLE_TIMETABLE = "CREATE TABLE " + TABLE_TIMETABLE + "(" +
	"_id INTEGER PRIMARY KEY AUTOINCREMENT, " + // Taking out auto-increment 
	COLUMN_SEMESTER + " TEXT, " +
	COLUMN_DAY + " TEXT, " +
	COLUMN_COURSE + " TEXT, " +
	COLUMN_START + " TEXT, " +
	COLUMN_FINISH + " TEXT, " +
	COLUMN_BUILDING + " TEXT, " +
	COLUMN_ROOM + " TEXT, " +
	COLUMN_YEAR1 + " INTEGER, " +
	COLUMN_YEAR2 + " INTEGER, " +
	COLUMN_YEAR3 + " INTEGER, " +
	COLUMN_YEAR4 + " INTEGER, " +
	COLUMN_YEAR5 + " INTEGER, " +
	COLUMN_COMMENT + " TEXT" + 
	")";
	
	/*---------------------- Create Table Buildings------------------------------*/
	private static final String CREATE_TABLE_BUILDINGS ="CREATE TABLE buildings(" +
	"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	COLUMN_BUILDING_NAME + " TEXT, " +
	COLUMN_DESCRIPTION +" TEXT, "+
	COLUMN_MAP+ " TEXT" +
	")";
 	/*---------------------- Create Table Rooms ----------------------------------*/
	
	private static final String CREATE_TABLE_ROOMS ="CREATE TABLE rooms(" +
	"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	COLUMN_ROOM_NAME + " TEXT, " +
	COLUMN_ROOM_DESCRIPTION +" TEXT "+
	")";
	
	/*------------------- Important stuff ------------------------------*/
	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, CREATE_TABLE_COURSES);
		db.execSQL(CREATE_TABLE_COURSES);
		Log.d(TAG, CREATE_TABLE_LECTURES);
		db.execSQL(CREATE_TABLE_LECTURES);
		Log.d(TAG, CREATE_TABLE_TIMETABLE);
		db.execSQL(CREATE_TABLE_TIMETABLE);
		Log.d(TAG,CREATE_TABLE_BUILDINGS);
		db.execSQL(CREATE_TABLE_BUILDINGS);
		Log.d(TAG,CREATE_TABLE_ROOMS);
		db.execSQL(CREATE_TABLE_ROOMS);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("Course DBS","Upgrading data base from old version to new");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LECTURES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMETABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUILDINGS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOMS);
		onCreate(db);
	}
	@Override // may not need this
	public void close() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
	public void rebuild() {
		SQLiteDatabase db = this.getWritableDatabase();
		Log.w("Course DBS","Dropping tables to rebuild them");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LECTURES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMETABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUILDINGS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOMS);
		onCreate(db);
		db.close();
	}
	
	public Boolean checkIfEmpty(){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_COURSES, null);
		Boolean rowExists;
		if (mCursor.moveToFirst()){
		  rowExists = false;
		} else{
		   // I AM EMPTY
		   rowExists = true;
		}
		db.close();
		return rowExists;
		
	}
	
	/*------------------- Course CRUDS ----------------------*/
	public long createCourse(Course course){
		SQLiteDatabase database = this.getWritableDatabase();
		//Log.d(TAG, "Creating course in database");
		ContentValues values = new ContentValues();
		//Log.d(TAG,"Putting Values"); //Putting Values
		values.put(COLUMN_URL, course.getUrl());
		values.put(COLUMN_NAME, course.getName());
		values.put(COLUMN_DRPS, course.getDrps());
		values.put(COLUMN_EUCLID, course.getEuclid()); 
		values.put(COLUMN_ACRONYM, course.getAcronym());
		values.put(COLUMN_AI, course.getAi());
		values.put(COLUMN_CG, course.getCg());
		values.put(COLUMN_CS, course.getCs());
		values.put(COLUMN_SE, course.getSe());
		values.put(COLUMN_LEVEL, course.getLevel());
		values.put(COLUMN_POINTS, course.getPoints());
		values.put(COLUMN_YEAR, course.getYear());
		values.put(COLUMN_DELIVERYPERIOD, course.getDeliveryPeriod());
		values.put(COLUMN_LECTURER, course.getLecturer());
		long insertId = database.insert(TABLE_COURSES, null, values);
		database.close();
		//Log.d(TAG, "InsertId = " + insertId);
		return insertId;
	}
	public long replaceCourse(Course course){
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//Log.d(TAG,"Replacing row");
		values.put(COLUMN_URL, course.getUrl());
		values.put(COLUMN_NAME, course.getName());
		values.put(COLUMN_DRPS, course.getDrps());
		values.put(COLUMN_EUCLID, course.getEuclid()); 
		values.put(COLUMN_ACRONYM, course.getAcronym());
		values.put(COLUMN_AI, course.getAi());
		values.put(COLUMN_CG, course.getCg());
		values.put(COLUMN_CS, course.getCs());
		values.put(COLUMN_SE, course.getSe());
		values.put(COLUMN_LEVEL, course.getLevel());
		values.put(COLUMN_POINTS, course.getPoints());
		values.put(COLUMN_YEAR, course.getYear());
		values.put(COLUMN_DELIVERYPERIOD, course.getDeliveryPeriod());
		values.put(COLUMN_LECTURER, course.getLecturer());
		long replaceId = database.replace(TABLE_COURSES, null,values);
		database.close();
		return replaceId;
	}
	// could definitely change this so you pass in which column you want to search by
	public Course getCourseByAcronym(String acronym) {
	    SQLiteDatabase database = this.getReadableDatabase();
	    String selectQuery = "SELECT * FROM "+TABLE_COURSES +" WHERE "+ COLUMN_ACRONYM +" = \'"+ acronym+"\'";
	    //Log.d(TAG, selectQuery);
	    Cursor c = database.rawQuery(selectQuery, null);
	    if (c != null)
	        c.moveToFirst();
	    Course course = new Course();
	    course.setUrl(c.getString(c.getColumnIndex(COLUMN_URL)));
	    course.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
	    course.setDrps(c.getString(c.getColumnIndex(COLUMN_DRPS)));
	    course.setEuclid(c.getString(c.getColumnIndex(COLUMN_EUCLID)));
	    course.setAcronym(c.getString(c.getColumnIndex(COLUMN_ACRONYM)));
	    course.setAi(c.getString(c.getColumnIndex(COLUMN_AI)));
	    course.setCg(c.getString(c.getColumnIndex(COLUMN_CG)));
	    course.setCs(c.getString(c.getColumnIndex(COLUMN_CS)));
	    course.setSe(c.getString(c.getColumnIndex(COLUMN_SE)));
	    course.setLevel(c.getInt(c.getColumnIndex(COLUMN_LEVEL)));
	    course.setPoints(c.getInt(c.getColumnIndex(COLUMN_POINTS)));
	    course.setYear(c.getInt(c.getColumnIndex(COLUMN_YEAR)));
	    course.setDeliveryPeriod(c.getString(c.getColumnIndex(COLUMN_DELIVERYPERIOD)));
	    course.setLecturer(c.getString(c.getColumnIndex(COLUMN_LECTURER)));
	    database.close();
	    return course;
	}
	public Course getCourseByName(String name) {
	    SQLiteDatabase database = this.getReadableDatabase();
	    String selectQuery = "SELECT * FROM "+TABLE_COURSES +" WHERE "+ COLUMN_NAME +" = \""+ name+"\"";
	    //Log.d(TAG, selectQuery);
	    Cursor c = database.rawQuery(selectQuery, null);
	    if (c != null)
	        c.moveToFirst();
	    Course course = new Course();
	    course.setUrl(c.getString(c.getColumnIndex(COLUMN_URL)));
	    course.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
	    course.setDrps(c.getString(c.getColumnIndex(COLUMN_DRPS)));
	    course.setEuclid(c.getString(c.getColumnIndex(COLUMN_EUCLID)));
	    course.setAcronym(c.getString(c.getColumnIndex(COLUMN_ACRONYM)));
	    course.setAi(c.getString(c.getColumnIndex(COLUMN_AI)));
	    course.setCg(c.getString(c.getColumnIndex(COLUMN_CG)));
	    course.setCs(c.getString(c.getColumnIndex(COLUMN_CS)));
	    course.setSe(c.getString(c.getColumnIndex(COLUMN_SE)));
	    course.setLevel(c.getInt(c.getColumnIndex(COLUMN_LEVEL)));
	    course.setPoints(c.getInt(c.getColumnIndex(COLUMN_POINTS)));
	    course.setYear(c.getInt(c.getColumnIndex(COLUMN_YEAR)));
	    course.setDeliveryPeriod(c.getString(c.getColumnIndex(COLUMN_DELIVERYPERIOD)));
	    course.setLecturer(c.getString(c.getColumnIndex(COLUMN_LECTURER)));
	    database.close();
	    return course;
	}
	public List<Course> getAllCourses(){
		List<Course> courses = new ArrayList<Course>();
		String selectQuery = "SELECT  * FROM " + SQLiteHelper.TABLE_COURSES;
		//Log.d(TAG,selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		// Loop through all rows and add to list.
		if (c.moveToFirst()) {
            do {
            	Course course = new Course();
        	    course.setUrl(c.getString(c.getColumnIndex(COLUMN_URL)));
        	    course.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
        	    course.setDrps(c.getString(c.getColumnIndex(COLUMN_DRPS)));
        	    course.setEuclid(c.getString(c.getColumnIndex(COLUMN_EUCLID)));
        	    course.setAcronym(c.getString(c.getColumnIndex(COLUMN_ACRONYM)));
        	    course.setAi(c.getString(c.getColumnIndex(COLUMN_AI)));
        	    course.setCg(c.getString(c.getColumnIndex(COLUMN_CG)));
        	    course.setCs(c.getString(c.getColumnIndex(COLUMN_CS)));
        	    course.setSe(c.getString(c.getColumnIndex(COLUMN_SE)));
        	    course.setLevel(c.getInt(c.getColumnIndex(COLUMN_LEVEL)));
        	    course.setPoints(c.getInt(c.getColumnIndex(COLUMN_POINTS)));
        	    course.setYear(c.getInt(c.getColumnIndex(COLUMN_YEAR)));
        	    course.setDeliveryPeriod(c.getString(c.getColumnIndex(COLUMN_DELIVERYPERIOD)));
        	    course.setLecturer(c.getString(c.getColumnIndex(COLUMN_LECTURER)));
                courses.add(course);
            } while (c.moveToNext());
        }
		db.close();
		return courses;
	}
	public List<String> getAllCourseNames(){
		List<String> names = new ArrayList<String>();
		String selectQuery = "SELECT  * FROM " + SQLiteHelper.TABLE_COURSES;
		//Log.d(TAG,selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
            do {
            	String name = new String();
            	name = c.getString(c.getColumnIndex(COLUMN_NAME));
            	names.add(name);
            } while (c.moveToNext());
		}
		c.close();
		db.close();
		return names;
	}
	public List<String> getCoursesByYear(int year){
		List<String> lectures = new ArrayList<String>();
		String selectQuery = "SELECT DISTINCT courses.name FROM courses, lectures WHERE courses.acronym = lectures.course AND lectures.year"+year+" = 1";
		Log.d(TAG,selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		Log.d(TAG, "cursor = " + c.getCount());
		if (c.moveToFirst()) {
            do {
            	lectures.add(c.getString(c.getColumnIndex(COLUMN_NAME)));
            } while (c.moveToNext());
        }
		db.close();
		return lectures;
	}
	
	/*------------------------------ Lectures CRUDS ------------------------------------*/
	public long createLecture(Lecture lecture){
		SQLiteDatabase database = this.getWritableDatabase();
		//Log.d(TAG, "Creating lecture in database");
		ContentValues values = new ContentValues();
		//Log.d(TAG,"Putting Values"); //Putting Values
		values.put(COLUMN_SEMESTER, lecture.getSemester());
		//Log.i(TAG, "Semester = "+lecture.getSemester());	
		values.put(COLUMN_DAY, lecture.getDay());
		//Log.i(TAG,"Day = "+ lecture.getDay());
		values.put(COLUMN_COURSE, lecture.getCourse());
		values.put(COLUMN_START,lecture.getStart());
		values.put(COLUMN_FINISH,lecture.getFinish());
		values.put(COLUMN_BUILDING,lecture.getBuilding());
		values.put(COLUMN_ROOM,lecture.getRoom());
		values.put(COLUMN_YEAR1,lecture.getYear1());
		values.put(COLUMN_YEAR2,lecture.getYear2());
		values.put(COLUMN_YEAR3,lecture.getYear3());
		values.put(COLUMN_YEAR4,lecture.getYear4());
		values.put(COLUMN_YEAR5,lecture.getYear5());
		values.put(COLUMN_COMMENT,lecture.getComment());
		long insertId = database.insert(TABLE_LECTURES, null, values);
		database.close();
		//Log.d(TAG, "InsertId = " + insertId);
		return insertId;
	}
	public long replaceLecture(Lecture lecture){
		SQLiteDatabase database = this.getWritableDatabase();
		//Log.d(TAG, "Creating lecture in database");
		ContentValues values = new ContentValues();
		//Log.d(TAG,"Putting Values"); //Putting Values
		values.put(COLUMN_SEMESTER, lecture.getSemester());
		values.put(COLUMN_DAY, lecture.getDay());
		values.put(COLUMN_COURSE, lecture.getCourse());
		values.put(COLUMN_START,lecture.getStart());
		values.put(COLUMN_FINISH,lecture.getFinish());
		values.put(COLUMN_BUILDING,lecture.getBuilding());
		values.put(COLUMN_ROOM,lecture.getRoom());
		values.put(COLUMN_YEAR1,lecture.getYear1());
		values.put(COLUMN_YEAR2,lecture.getYear2());
		values.put(COLUMN_YEAR3,lecture.getYear3());
		values.put(COLUMN_YEAR4,lecture.getYear4());
		values.put(COLUMN_YEAR5,lecture.getYear5());
		values.put(COLUMN_COMMENT,lecture.getComment());
		long replaceId = database.replace(TABLE_COURSES, null,values);
		database.close();
		//Log.d(TAG,"Replace Id "+ replaceId);
		return replaceId;
	}
	public List<Lecture> getAllLectures(){
		List<Lecture> lectures = new ArrayList<Lecture>();
		String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_LECTURES;
		//Log.d(TAG,selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		// Loop through all rows and add to list.
		if (c.moveToFirst()) {
            do {
            	Lecture lecture = new Lecture();
            	lecture.setSemester(c.getString(c.getColumnIndex(COLUMN_SEMESTER)));
            	lecture.setDay(c.getString(c.getColumnIndex(COLUMN_DAY)));
            	lecture.setCourse(c.getString(c.getColumnIndex(COLUMN_COURSE)));
            	lecture.setStart(c.getString(c.getColumnIndex(COLUMN_START)));
            	lecture.setFinish(c.getString(c.getColumnIndex(COLUMN_FINISH)));
            	lecture.setBuilding(c.getString(c.getColumnIndex(COLUMN_BUILDING)));
            	lecture.setRoom(c.getString(c.getColumnIndex(COLUMN_ROOM)));
            	lecture.setYear1(c.getInt(c.getColumnIndex(COLUMN_YEAR1)));
            	lecture.setYear2(c.getInt(c.getColumnIndex(COLUMN_YEAR2)));;
            	lecture.setYear3(c.getInt(c.getColumnIndex(COLUMN_YEAR3)));;
            	lecture.setYear4(c.getInt(c.getColumnIndex(COLUMN_YEAR4)));;
            	lecture.setYear5(c.getInt(c.getColumnIndex(COLUMN_YEAR5)));;
            	lecture.setComment(c.getString(c.getColumnIndex(COLUMN_COMMENT)));
                // adding to courses list
            	//Log.i(TAG,lecture.getCourse());
                lectures.add(lecture);
            } while (c.moveToNext());
        }
		db.close();
		return lectures;
	}
	public List<Lecture> getAllLecturesOfCourse(String course){
		List<Lecture> lectures = new ArrayList<Lecture>();
		String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_LECTURES + " WHERE "+ COLUMN_COURSE +" = \""+ course+"\"";
		//Log.d(TAG,selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		// Loop through all rows and add to list.
		if (c.moveToFirst()) {
            do {
            	Lecture lecture = new Lecture();
            	lecture.setSemester(c.getString(c.getColumnIndex(COLUMN_SEMESTER)));
            	lecture.setDay(c.getString(c.getColumnIndex(COLUMN_DAY)));
            	lecture.setCourse(c.getString(c.getColumnIndex(COLUMN_COURSE)));
            	lecture.setStart(c.getString(c.getColumnIndex(COLUMN_START)));
            	lecture.setFinish(c.getString(c.getColumnIndex(COLUMN_FINISH)));
            	lecture.setBuilding(c.getString(c.getColumnIndex(COLUMN_BUILDING)));
            	lecture.setRoom(c.getString(c.getColumnIndex(COLUMN_ROOM)));
            	lecture.setYear1(c.getInt(c.getColumnIndex(COLUMN_YEAR1)));
            	lecture.setYear2(c.getInt(c.getColumnIndex(COLUMN_YEAR2)));;
            	lecture.setYear3(c.getInt(c.getColumnIndex(COLUMN_YEAR3)));;
            	lecture.setYear4(c.getInt(c.getColumnIndex(COLUMN_YEAR4)));;
            	lecture.setYear5(c.getInt(c.getColumnIndex(COLUMN_YEAR5)));;
            	lecture.setComment(c.getString(c.getColumnIndex(COLUMN_COMMENT)));
                // adding to courses list
                lectures.add(lecture);
            } while (c.moveToNext());
        }
		db.close();
		return lectures;
	}
	public List<Lecture> getAllLecturesOfCourseByAcronym(String acronym){
		List<Lecture> lectures = new ArrayList<Lecture>();
		String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_LECTURES + " WHERE "+ COLUMN_COURSE +" = \""+ acronym+"\"";
		//Log.d(TAG,selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		// Loop through all rows and add to list.
		if (c.moveToFirst()) {
            do {
            	Lecture lecture = new Lecture();
            	lecture.setSemester(c.getString(c.getColumnIndex(COLUMN_SEMESTER)));
            	lecture.setDay(c.getString(c.getColumnIndex(COLUMN_DAY)));
            	lecture.setCourse(c.getString(c.getColumnIndex(COLUMN_COURSE)));
            	lecture.setStart(c.getString(c.getColumnIndex(COLUMN_START)));
            	lecture.setFinish(c.getString(c.getColumnIndex(COLUMN_FINISH)));
            	lecture.setBuilding(c.getString(c.getColumnIndex(COLUMN_BUILDING)));
            	lecture.setRoom(c.getString(c.getColumnIndex(COLUMN_ROOM)));
            	lecture.setYear1(c.getInt(c.getColumnIndex(COLUMN_YEAR1)));
            	lecture.setYear2(c.getInt(c.getColumnIndex(COLUMN_YEAR2)));;
            	lecture.setYear3(c.getInt(c.getColumnIndex(COLUMN_YEAR3)));;
            	lecture.setYear4(c.getInt(c.getColumnIndex(COLUMN_YEAR4)));;
            	lecture.setYear5(c.getInt(c.getColumnIndex(COLUMN_YEAR5)));;
            	lecture.setComment(c.getString(c.getColumnIndex(COLUMN_COMMENT)));
                // adding to courses list
                lectures.add(lecture);
            } while (c.moveToNext());
        }
		db.close();
		return lectures;
	}
	
	/*------------------------------ Timetable CRUDS ------------------------------------*/
	// Add class takes a Lecture type and adds it to the database
	public long addClass(Lecture lecture){
		SQLiteDatabase database = this.getWritableDatabase();
		//Log.d(TAG, "Adding Class");
		ContentValues values = new ContentValues();
		//Log.d(TAG,"Putting Values"); //Putting Values
		values.put(COLUMN_SEMESTER, lecture.getSemester());
		values.put(COLUMN_DAY, lecture.getDay());
		values.put(COLUMN_COURSE, lecture.getCourse());
		values.put(COLUMN_START,lecture.getStart());
		values.put(COLUMN_FINISH,lecture.getFinish());
		values.put(COLUMN_BUILDING,lecture.getBuilding());
		values.put(COLUMN_ROOM,lecture.getRoom());
		values.put(COLUMN_YEAR1,lecture.getYear1());
		values.put(COLUMN_YEAR2,lecture.getYear2());
		values.put(COLUMN_YEAR3,lecture.getYear3());
		values.put(COLUMN_YEAR4,lecture.getYear4());
		values.put(COLUMN_YEAR5,lecture.getYear5());
		values.put(COLUMN_COMMENT,lecture.getComment());
		long insertId = database.insert(TABLE_TIMETABLE, null, values);
		database.close();
		//Log.d(TAG, "InsertId = " + insertId);
		return insertId;
	}
	public void addClassesByAcronym(String acronym){
		List<Lecture> classes = new ArrayList<Lecture>();
		classes = getAllLecturesOfCourseByAcronym(acronym);
		Log.d(TAG,"Adding all lectures for "+ acronym +"to timetable");
		for( Lecture lecture : classes){
			Log.d(TAG,"Adding class"+lecture.getCourse());
			addClass(lecture);
		}
	}
	public long deleteClassesByAcronym(String acronym){
		String whereClause = COLUMN_COURSE +" = \""+ acronym+"\"";
		SQLiteDatabase database = this.getWritableDatabase();
		long deleteNo = database.delete(TABLE_TIMETABLE, whereClause,null);
		database.close();
		return deleteNo;
	}
	public Boolean checkIfInTimetable(String acronym){
		String selectQuery = "SELECT * FROM " +TABLE_TIMETABLE + " WHERE "+ COLUMN_COURSE +" = \""+ acronym+"\"";
		Log.d(TAG,selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		Log.d(TAG, "c = "+c.getCount());
		if (c.getCount() > 0){
			db.close();
			return true;
		}
		else{
			db.close();
			return false;
		}	
	}
	
	public List<Lecture> getAllLecturesFromTimetable(){
		List<Lecture> lectures = new ArrayList<Lecture>();
		String selectQuery = "SELECT * FROM " + TABLE_TIMETABLE;
		Log.d(TAG,selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
            do {
            	Lecture lecture = new Lecture();
            	lecture.setSemester(c.getString(c.getColumnIndex(COLUMN_SEMESTER)));
            	lecture.setDay(c.getString(c.getColumnIndex(COLUMN_DAY)));
            	lecture.setCourse(c.getString(c.getColumnIndex(COLUMN_COURSE)));
            	lecture.setStart(c.getString(c.getColumnIndex(COLUMN_START)));
            	lecture.setFinish(c.getString(c.getColumnIndex(COLUMN_FINISH)));
            	lecture.setBuilding(c.getString(c.getColumnIndex(COLUMN_BUILDING)));
            	lecture.setRoom(c.getString(c.getColumnIndex(COLUMN_ROOM)));
            	lecture.setYear1(c.getInt(c.getColumnIndex(COLUMN_YEAR1)));
            	lecture.setYear2(c.getInt(c.getColumnIndex(COLUMN_YEAR2)));;
            	lecture.setYear3(c.getInt(c.getColumnIndex(COLUMN_YEAR3)));;
            	lecture.setYear4(c.getInt(c.getColumnIndex(COLUMN_YEAR4)));;
            	lecture.setYear5(c.getInt(c.getColumnIndex(COLUMN_YEAR5)));;
            	lecture.setComment(c.getString(c.getColumnIndex(COLUMN_COMMENT)));
                lectures.add(lecture);
            } while (c.moveToNext());
        }
		db.close();
		return lectures;
	}
	
	public List<Lecture> getAllLecturesOnDay(String day, int semester){
		List<Lecture> lectures = new ArrayList<Lecture>();
		String selectQuery = "SELECT * FROM " + TABLE_TIMETABLE +" WHERE " + COLUMN_DAY + " = \'" + day + "\' AND " + COLUMN_SEMESTER + " = " + semester;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		//Log.d(TAG, "cursor = " + c.getCount());
		if (c.moveToFirst()) {
            do {
            	Lecture lecture = new Lecture();
            	lecture.setSemester(c.getString(c.getColumnIndex(COLUMN_SEMESTER)));
            	lecture.setDay(c.getString(c.getColumnIndex(COLUMN_DAY)));
            	lecture.setCourse(c.getString(c.getColumnIndex(COLUMN_COURSE)));
            	lecture.setStart(c.getString(c.getColumnIndex(COLUMN_START)));
            	lecture.setFinish(c.getString(c.getColumnIndex(COLUMN_FINISH)));
            	lecture.setBuilding(c.getString(c.getColumnIndex(COLUMN_BUILDING)));
            	lecture.setRoom(c.getString(c.getColumnIndex(COLUMN_ROOM)));
            	lecture.setYear1(c.getInt(c.getColumnIndex(COLUMN_YEAR1)));
            	lecture.setYear2(c.getInt(c.getColumnIndex(COLUMN_YEAR2)));;
            	lecture.setYear3(c.getInt(c.getColumnIndex(COLUMN_YEAR3)));;
            	lecture.setYear4(c.getInt(c.getColumnIndex(COLUMN_YEAR4)));;
            	lecture.setYear5(c.getInt(c.getColumnIndex(COLUMN_YEAR5)));;
            	lecture.setComment(c.getString(c.getColumnIndex(COLUMN_COMMENT)));
                lectures.add(lecture);
            } while (c.moveToNext());
        }
		db.close();
		return lectures;
	}
	
	public List<String> getTimetable(int semester){
		List<String> timetable = new ArrayList<String>();
		List<Lecture> timetable_full = new ArrayList<Lecture>();
		String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
		//timetable_full = getAllLecturesFromTimetable();
		//for (Lecture lecture : timetable_full){
		//	Log.d(TAG, lecture.getCourse() + ", "+ lecture.getDay() + ", " +lecture.getSemester());
		//}
		for (String day : days){
			List<Lecture> lectures = getAllLecturesOnDay(day,semester);
			for(Lecture lecture : lectures){
				timetable.add(day +"\t :- \t" + lecture.getCourse() + "\t[" +lecture.getStart() + "-" + lecture.getFinish() + "]");
			}
		}
		return timetable;
	}
	/* -------------------- Building CRUDS ------------------*/
	public long createBuilding(Building building){
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_BUILDING_NAME, building.getName());
		values.put(COLUMN_DESCRIPTION, building.getDescription());
		values.put(COLUMN_MAP, building.getMap());
		long insertId = database.insert(TABLE_BUILDINGS, null, values);
		database.close();
		return insertId;
	}
	
	/* --------------------Room CRUDS ------------------*/
	public long createRoom(Room room){
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_ROOM_NAME, room.getName());
		values.put(COLUMN_ROOM_DESCRIPTION, room.getDescription());
		long insertId = database.insert(TABLE_ROOMS, null, values);
		database.close();
		return insertId;
	}
	
	public Full getLectureWithData(String day, String acronym, String start){
		List<String> lectures = new ArrayList<String>();
		Full data = new Full();
		Boolean doStrictSearch = false;
        String query = "SELECT * FROM lectures, rooms, buildings "+
	            "WHERE building = building_name AND room = room_name " +
	            "AND lectures.course = \'"+ acronym + "\' AND start = \'"+ start+"\' " +
	            "AND lectures.day = \'"+ day +"\'";
		Log.d(TAG,query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		Log.d(TAG, "size of cursor = " + c.getCount());
		if(c.getCount() == 0){
			doStrictSearch = true;
			String query2 = "SELECT * FROM lectures, buildings " +
					"WHERE building = building_name " +
					"AND lectures.course = \'"+ acronym + "\' AND start = \'"+ start+"\' " +
					"AND lectures.day = \'"+ day +"\'";
			c = db.rawQuery(query2, null);
			Log.d(TAG, "Size of less strict cursor = " + c.getCount());
		}
		if (c.moveToFirst()) {
			do {
				// set boolean check to test if you do query 2 or query 1
				data.setDay(c.getString(c.getColumnIndex(COLUMN_DAY)));
				data.setCourse(c.getString(c.getColumnIndex(COLUMN_COURSE)));
				data.setStart(c.getString(c.getColumnIndex(COLUMN_START)));
				data.setFinish(c.getString(c.getColumnIndex(COLUMN_FINISH)));
				data.setRoom(c.getString(c.getColumnIndex(COLUMN_ROOM)));
				data.setBuilding_name(c.getString(c.getColumnIndex(COLUMN_BUILDING_NAME)));
				data.setBuilding_description(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
				data.setMap(c.getString(c.getColumnIndex(COLUMN_MAP)));
				Log.d(TAG,"Gets to here");
				if (doStrictSearch){
					data.setRoom_name("room");
					data.setRoom_description("description");
				} else {
					data.setRoom_name(c.getString(c.getColumnIndex(COLUMN_ROOM_NAME)));
					data.setRoom_description(c.getString(c.getColumnIndex(COLUMN_ROOM_DESCRIPTION)));
				}
				Log.d(TAG, "Finished putting values");
				//} else {
				//	data.setRoom_name("");
				//	data.setRoom_description("");
				//}
	
			} while (c.moveToNext());
		}
		db.close();
		return data;
	} 
	public List<String> checkRoomsAndBuildings(){
		List<String> everything = new ArrayList<String>();
        String query = "SELECT * FROM rooms, lectures WHERE rooms.room_name = lectures.room";
        
		Log.d(TAG,query);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(query, null);
		Log.d(TAG, "size of cursor = " + c.getCount());
		if (c.moveToFirst()) {
            do {
                everything.add(c.getString(c.getColumnIndex("room_name")));
                everything.add(c.getString(c.getColumnIndex("course")));
                
            	
            } while (c.moveToNext());
        }
		db.close();
		return everything;
	} 
	
	
	
	

}








