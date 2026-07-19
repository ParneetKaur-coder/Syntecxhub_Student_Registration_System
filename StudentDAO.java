import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    public void addStudent(Student student) {

        String sql = "INSERT INTO students(name, age, course, email) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getEmail());

            ps.executeUpdate();

            System.out.println("Student Registered Successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewStudents() {

        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nID\tName\t\tAge\tCourse\tEmail");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("age") + "\t" +
                        rs.getString("course") + "\t" +
                        rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(int id, String course) {

        String sql = "UPDATE students SET course=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, course);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student Updated Successfully.");
            else
                System.out.println("Student Not Found.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student Deleted Successfully.");
            else
                System.out.println("Student Not Found.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchStudent(int id) {

        String sql = "SELECT * FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\nStudent Details");
                System.out.println("--------------------------");
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Age : " + rs.getInt("age"));
                System.out.println("Course : " + rs.getString("course"));
                System.out.println("Email : " + rs.getString("email"));

            } else {

                System.out.println("Student Not Found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}