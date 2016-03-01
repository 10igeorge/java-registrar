import org.sql2o.*;
import java.util.List;
import java.sql.Date;



public class Student {
  private int id;
  private String name;
  private java.sql.Date enrollment_date;

  public Student(String studentName, String enrollment){
    name = studentName;
    java.sql.Date enrollment_date = java.sql.Date.valueOf(enrollment);
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public java.sql.Date getEnrollmentDate(){
    return enrollment_date;
  }

  @Override
  public boolean equals(Object newStudent) {
    if (newStudent instanceof Student) {
      Student otherStudent = (Student) newStudent;
      return this.getName().equals(otherStudent.getName()) &&
        this.getId() == otherStudent.getId() &&
        this.getEnrollmentDate() == otherStudent.getEnrollmentDate();
    } else {
      return false;
    }
  }

  public static List<Student> all(){
    String sql = "SELECT * FROM students";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .executeAndFetch(Student.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO students (name, enrollment_date) VALUES (:name, :enrollment_date)";
    try(Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("enrollment_date", enrollment_date)
        .executeUpdate()
        .getKey();
    }
  }
}
