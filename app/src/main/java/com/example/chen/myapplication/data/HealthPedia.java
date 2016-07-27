package com.example.chen.myapplication.data;


public class HealthPedia {
	
	private Integer id;
	
	private String title;
	
	private String cover;
	
	private String time;
	
	private String artwork;
	
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getArtwork() {
		return artwork;
	}

	public void setArtwork(String artwork) {
		this.artwork = artwork;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "HealthPedia [id=" + id + ", title=" + title + ", cover=" + cover + ", time=" + time + ", artwork="
				+ artwork + ", content=" + content + "]";
	}

}
