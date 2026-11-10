package book_management.repository;

import java.sql.*;

public class JDBCConnector {
    private static final String DRIVER_PATH = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
    private static final String USER_NAME = "c##aisoft";
    private static final String PASSWORD = "1234";

    private static Connection con;
    public static Connection getConnection() {
        try {
            // 1. JDBC Driver Memory Loading
            Class.forName(DRIVER_PATH);
            System.out.println("JDBC Driver Class founded!");

            // 2. Connection
            con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connection Done Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Class not founded.");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Connection Failed.");
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void resultSetTest() {
        // 3. SQL문을 실행할 수 있는 문장객체 생성 (미리 준비된 SQL문을 실행할 때 사용)
        try {
            String sql = "SELECT * FROM 고객";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getString("고객아이디") + "\t");
                System.out.print(rs.getString("고객이름") + "\t");
                System.out.print(rs.getString("직업") + "\t");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("연결된 DB로 문장 객체 생성이 되지 않았습니다.");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        getConnection();
//        resultSetTest();
    }
}