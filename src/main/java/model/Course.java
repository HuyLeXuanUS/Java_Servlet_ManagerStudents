package model;

public class Course {
    private String id;
    private String name;
    private String lecture;
    private int year;
    private String notes;
    
    private float grade;

    public Course() {
    }

    public Course(String id, String name, String lecture, int year, String notes) {
        this.id = id;
        this.name = name;
        this.lecture = lecture;
        this.year = year;
        this.notes = notes;
    }
    
    public Course(String id, String name, String lecture, int year, String notes, float grade) {
        this.id = id;
        this.name = name;
        this.lecture = lecture;
        this.year = year;
        this.notes = notes;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public float getGrade() {
    	return grade;
    }
    
    public void setGrade(float grade) {
    	this.grade = grade;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lecture='" + lecture + '\'' +
                ", year=" + year +
                ", notes='" + notes + '\'' +
                '}';
    }

}
