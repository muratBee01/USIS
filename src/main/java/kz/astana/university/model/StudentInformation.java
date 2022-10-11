package kz.astana.university.model; // Контейнер для данных приложения


public class StudentInformation {

    private int id;
    private int studentID;
    private String studentName;
    private String studentLastname;
    private String studentEmail;
    private String universityName;
    private String facultyName;
    private double studentGPA;
    private int graduatedYear;


    public StudentInformation(){

    }

    public StudentInformation(int id, int studentID, String studentName, String studentLastname,
                              String studentEmail, String universityName, String facultyName,
                              double studentGPA, int graduatedYear) {
        this.id = id;
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentLastname = studentLastname;
        this.studentEmail = studentEmail;
        this.universityName = universityName;
        this.facultyName = facultyName;
        this.studentGPA = studentGPA;
        this.graduatedYear = graduatedYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLastname() {
        return studentLastname;
    }

    public void setStudentLastname(String studentLastname) {
        this.studentLastname = studentLastname;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public double getStudentGPA() {
        return studentGPA;
    }

    public void setStudentGPA(double studentGPA) {
        this.studentGPA = studentGPA;
    }

    public int getGraduatedYear() {
        return graduatedYear;
    }

    public void setGraduatedYear(int graduatedYear) {
        this.graduatedYear = graduatedYear;
    }
}
