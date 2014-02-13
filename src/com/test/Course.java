package com.test;


public class Course{
	private String mUrl;
	private String mName;
	private String mDrps;
	private String mEuclid;
	private String mAcronym;
	private String mAi; 
	private String mCg;
	private String mCs;
	private String mSe;
	private int mLevel;
	private int mPoints;
	private int mYear;
	private String mDeliveryPeriod;
	private String mLecturer;
	
	public String getUrl(){
		return mUrl;
	}
	public void setUrl(String Url){
		mUrl = Url;
	}
	public String getName(){
		return mName;
	}
	public void setName(String Name){
		mName = Name;
	}
	public String getDrps(){
		return mDrps;
	}
	public void setDrps(String Drps){
		mDrps = Drps;
	}
	public String getEuclid(){
		return mEuclid;
	}
	public void setEuclid(String Euclid){
		mEuclid = Euclid;
	}
	public String getAcronym(){
		return mAcronym;
	}
	public void setAcronym(String Acronym){
		mAcronym = Acronym;
	}
	public String getAi(){
		return mAi;
	}
	public void setAi(String Ai){
		mAi = Ai;
	}
	public String getCg(){
		return mCg;
	}
	public void setCg(String Cg){
		mCg = Cg;
	}
	public String getCs(){
		return mCs;
	}
	public void setCs(String Cs){
		mCs =Cs;
	}
	public String getSe(){
		return mSe;
	}
	public void setSe(String Se){
		mSe = Se;
	}
	public int getLevel(){
		return mLevel;
	}
	public void setLevel(int Level){
		mLevel = Level;
	}
	public int getPoints(){
		return mPoints;
	}
	public void setPoints(int Points){
		mPoints = Points;
	}
	public int getYear(){
		return mYear;
	}
	public void setYear(int Year){
		mYear = Year;
	}
	public String getDeliveryPeriod(){
		return mDeliveryPeriod;
	}
	public void setDeliveryPeriod(String DeliveryPeriod){
		mDeliveryPeriod = DeliveryPeriod;
	}
	public String getLecturer(){
		return mLecturer;
	}
	public void setLecturer(String Lecturer){
		mLecturer = Lecturer;
	}
	public String toString(){
		String full = getUrl() + "," + getName() + "," +getDrps() + "," + getEuclid() + "," + getAcronym() + "," + getAi() + "," + getCs() +
				"," + getSe() + "," + getLevel() +"," + getYear() +"," + getDeliveryPeriod() + "," + getLecturer();
		return full;
	}
	public String[] toStringList(){
		String level = Integer.toString(getLevel());
		String year = Integer.toString(getYear());
		String[] full = {getUrl(),getName(),getDrps(),getEuclid(),getAcronym(),getAi(),getCs(),getSe(),(String) level, year,getDeliveryPeriod(),getLecturer()};
		return full;
	}
	
}
