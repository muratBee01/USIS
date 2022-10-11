package kz.astana.university.dao;

import kz.astana.university.model.StudentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDAO {
    private final JdbcTemplate jdbcTemplate;
    private static int COUNT_ID;

    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StudentInformation> commonPage(){
        return jdbcTemplate.query("SELECT * FROM student",
                new BeanPropertyRowMapper<>(StudentInformation.class));
    }

    public StudentInformation show(int id){
        return jdbcTemplate.query("SELECT * FROM student WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(StudentInformation.class))
                .stream().findAny().orElse(null);
    }

    public void save(StudentInformation studentInformation){
        jdbcTemplate.update("INSERT INTO student VALUES(1, ?, ?, ?, ?, ?, ?, ?, ?)",
            studentInformation.getStudentID(), studentInformation.getStudentName(), studentInformation.getStudentLastname(),
            studentInformation.getStudentEmail(), studentInformation.getUniversityName(), studentInformation.getFacultyName(),
            studentInformation.getStudentGPA(), studentInformation.getGraduatedYear());
    }

    public void update(int id, StudentInformation updateStudentInformation){
        jdbcTemplate.update("UPDATE student SET studentID=?, studentName=?, studentLastname=?, studentEmail=?, universityName=?, facultyName=?, studentGPA=?, graduatedYear=? WHERE id=?",
                updateStudentInformation.getStudentID(), updateStudentInformation.getStudentName(), updateStudentInformation.getStudentLastname(),
                updateStudentInformation.getStudentEmail(), updateStudentInformation.getUniversityName(), updateStudentInformation.getFacultyName(),
                updateStudentInformation.getStudentGPA(), updateStudentInformation.getGraduatedYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM student WHERE id=?", id);
    }
}
