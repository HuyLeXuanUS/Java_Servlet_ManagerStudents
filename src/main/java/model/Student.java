package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Student {
    private String id;
    private String name;
    private String birthday;
    private String address;
    private String notes;

    public Student() {
    }

    public Student(String id, String name, String birthday, String address, String notes) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.notes = notes;
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

    public String getBirthday() {
        return birthday;
    }

    public Date getBirthdayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return new Date(formatter.parse(birthday).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBirthday(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.birthday = formatter.format(date);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
