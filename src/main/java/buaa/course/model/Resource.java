package buaa.course.model;

public class Resource {
	private int id;
	private int semesterCourseId;
	private String fileUrl;
	private String category;
	public Resource() {
		super();
	}
	public Resource(int semesterCourseId, String fileUrl, String category) {
		super();
		this.semesterCourseId = semesterCourseId;
		this.fileUrl = fileUrl;
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSemesterCourseId() {
		return semesterCourseId;
	}
	public void setSemesterCourseId(int semesterCourseId) {
		this.semesterCourseId = semesterCourseId;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Resource [id=" + id + ", semesterCourseId=" + semesterCourseId + ", fileUrl=" + fileUrl + ", category="
				+ category + "]";
	}
}
