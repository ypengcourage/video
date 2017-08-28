package com.zhiyou100.video.model;

public class speakerVO {
	private String speakerName;
	private String speakerJob;
	private String videoTitle;
	private int videoSpeaker;
	private int videoCourse;
	private int page;
	private double times;
	private String courseName;
	public double getTimes() {
		return times;
	}
	public void setTimes(double times) {
		this.times = times;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSpeakerName() {
		return speakerName;
	}
	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}
	public String getSpeakerJob() {
		return speakerJob;
	}
	public void setSpeakerJob(String speakerJob) {
		this.speakerJob = speakerJob;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public int getVideoSpeaker() {
		return videoSpeaker;
	}
	public void setVideoSpeaker(int videoSpeaker) {
		this.videoSpeaker = videoSpeaker;
	}
	public int getVideoCourse() {
		return videoCourse;
	}
	public void setVideoCourse(int videoCourse) {
		this.videoCourse = videoCourse;
	}
	@Override
	public String toString() {
		return "speakerVO [speakerName=" + speakerName + ", speakerJob=" + speakerJob + ", videoTitle=" + videoTitle
				+ ", videoSpeaker=" + videoSpeaker + ", videoCourse=" + videoCourse + ", page=" + page + ", times="
				+ times + ", courseName=" + courseName + "]";
	}
}
