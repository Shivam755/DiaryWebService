package DiaryMVC;

import java.util.Objects;


public class DiaryEntry {
	
	private Long Id;
	private String title;
	private String body;
	private String datetime;
	public DiaryEntry(Long id, String title, String body, String datetime) {
//		super();
		Id = id;
		this.title = title;
		this.body = body;
		this.datetime = datetime;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "DiaryEntry [Id=" + Id + ", title=" + title + ", body=" + body + ", datetime=" + datetime + "]";
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.Id, this.title, this.body,  this.datetime);
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
		        && Objects.equals(this.body, entry.body) && Objects.equals(this.datetime, entry.datetime);
	}
	
	
	
}
