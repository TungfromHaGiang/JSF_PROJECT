package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    // Thông tin kết nối
    private String url = "jdbc:postgresql://localhost:5432/test"; // Địa chỉ và tên cơ sở dữ liệu
    private String username = "postgres"; // Tên người dùng PostgreSQL
    private String password = "123"; // Mật khẩu
    private Connection connection;

    // Constructor
    public DBContext() {
        try {
            // Tải Driver PostgreSQL (nếu sử dụng JDK cũ, có thể không cần dòng này)
            Class.forName("org.postgresql.Driver");
            
            // Thiết lập kết nối
            connection = DriverManager.getConnection(url, username, password);
            
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Lỗi khi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Lấy kết nối
    public Connection getConnection() {
        return connection;
    }

}
