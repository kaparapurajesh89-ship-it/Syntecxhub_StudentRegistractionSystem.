import java.sql.*;
import java.util.Scanner;

public class StudentCRUD {

    static Scanner sc = new Scanner(System.in);

    // CREATE
    public static void addStudent() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO students(name,email,course) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Name: ");
            ps.setString(1, sc.nextLine());

            System.out.print("Enter Email: ");
            ps.setString(2, sc.nextLine());

            System.out.print("Enter Course: ");
            ps.setString(3, sc.nextLine());

            ps.executeUpdate();
            System.out.println("Student Added!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // READ
    public static void viewStudents() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            System.out.println("\n--- Student List ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getString("course")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // UPDATE
    public static void updateStudent() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("New Name: ");
            String name = sc.nextLine();

            System.out.print("New Email: ");
            String email = sc.nextLine();

            System.out.print("New Course: ");
            String course = sc.nextLine();

            String sql = "UPDATE students SET name=?, email=?, course=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println(" Student Updated!");
            else
                System.out.println(" ID not found!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // DELETE
    public static void deleteStudent() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println(" Student Deleted!");
            else
                System.out.println("ID not found!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}