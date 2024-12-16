package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import model.Employee;

@ApplicationScoped
public class EmployeeDAO {
	private DBContext dbContext;
	
	public EmployeeDAO() {
		dbContext = new DBContext();
	}
	// Lấy danh sách toàn bộ nhân viên
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = dbContext.getConnection().prepareStatement("select * from Mt_employee")) {
            ResultSet rs = preparedStatement.executeQuery();
            // Lặp qua kết quả trả về và thêm vào danh sách
            while (rs.next()) {
                int code = rs.getInt("code");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                java.sql.Date dob = rs.getDate("dob");

                // Thêm vào danh sách
                employees.add(new Employee(code,name, age, dob.toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
 // Phương thức thêm nhân viên vào cơ sở dữ liệu
    public boolean addEmployee(Employee employee) {
        String query = "INSERT INTO Mt_employee (name, age, dob) VALUES (?,?,?)";
        try (PreparedStatement stmt = dbContext.getConnection().prepareStatement(query)) {
            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getAge()); 
            stmt.setDate(3, java.sql.Date.valueOf(employee.getDob()));
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Trả về false nếu có lỗi
        }
    }
 // Xóa phần tử
    public boolean delete(int x) {
        String sql = "DELETE FROM Mt_employee WHERE code = ?";
        try (PreparedStatement stmt =  dbContext.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, x);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }

    // Sửa thông tin nhân viên
    public Employee update(Employee employee) {
        String sql = "UPDATE Mt_employee SET name = ?, age = ?, dob = ? WHERE code = ?";
        try (PreparedStatement stmt =  dbContext.getConnection().prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getAge());
            stmt.setDate(3, java.sql.Date.valueOf(employee.getDob())); // Chuyển đổi LocalDate sang java.sql.Date
            stmt.setInt(4, employee.getCode());
            int rowsAffected = stmt.executeUpdate();
            return search(employee.getCode()); // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Trả về false nếu có lỗi
        }
    }
    public Employee search(int x) {
    	Employee employee = new Employee();
        try (PreparedStatement preparedStatement = dbContext.getConnection().prepareStatement("select * from Mt_employee where code = ?")) {
        	preparedStatement.setInt(1, x);
            ResultSet rs = preparedStatement.executeQuery();
            // Scan giá trị trả về
            if (rs.next()) {
                int code = rs.getInt("code");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                java.sql.Date dob = rs.getDate("dob");

                // Trả  lại giá trị
              return new Employee(code,name, age, dob.toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Hàm main để kiểm tra
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee e = new Employee(1, "TungPV", 121211, java.time.LocalDate.now());
        System.out.println(employeeDAO.update(e));
//        List<Employee> employees = employeeDAO.getAllEmployees();
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
    }
}
