package StudentsModel;

public class Student {
    private int id;
    private String surname;
    private String name;
    private String faculty;
    private double averageScore;

    public Student() {
    }

    public Student(String surname, String name, String faculty, double averageScore) {
        this.faculty = faculty;
        this.surname = surname;
        this.name = name;
        this.averageScore = averageScore;
    }

    public Student(int id, String surname, String name, String faculty, double averageScore) {
        this.faculty = faculty;
        this.surname = surname;
        this.name = name;
        this.averageScore = averageScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
