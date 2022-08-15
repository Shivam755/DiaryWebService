package com.innovative.webService;

import java.util.Objects;


public class DiaryEntry {
	
	private int Id;
	private String title;
	private String content;
	private String date;
	public DiaryEntry(int id, String title, String content, String date) {
//		super();
		Id = id;
		this.title = title;
		this.content = content;
		this.date = date;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "DiaryEntry [Id=" + Id + ", title=" + title + ", content=" + content + ", date=" + date + "]";
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.Id, this.title, this.content,  this.date);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		 if (this == obj) 
		      return true;
		    if (!(obj instanceof DiaryEntry))
		      return false;
		    DiaryEntry entry = (DiaryEntry) obj;
		    return Objects.equals(this.Id, entry.Id) && Objects.equals(this.title, entry.title)
		        && Objects.equals(this.content, entry.content) && Objects.equals(this.date, entry.date);
	}
	
	
	
}
