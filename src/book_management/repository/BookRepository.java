
package book_management.repository;

import book_management.domain.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookRepository {
    ArrayList<Book> bookList;

    public ArrayList<Book> select(String searchWord, int selectedIndex) {
        bookList = new ArrayList<Book>();

        String[] columnName = {"name", "publish_name", "author"};

        String sql = "select isbn, name, author, price, inventory, category_name, publish_name from book b, category c, publish p " +
                "where b.category_id = c.category_id and b.publish_id = p.publish_id " +
                "and " + columnName[selectedIndex] + " like ? order by isbn";

        try (Connection con = JDBCConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, "%" + searchWord + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                Book book = null;

                while (rs.next()) {
                    book = new Book();
                    book.setIsbn(rs.getString("isbn"));
                    book.setName(rs.getString("name"));
                    book.setAuthor(rs.getString("author"));
                    book.setPrice(rs.getInt("price"));
                    book.setInventory(rs.getInt("inventory"));
                    book.setCategory_name(rs.getString("category_name"));
                    book.setPublish_name(rs.getString("publish_name"));

                    bookList.add(book);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookList;
    }

    public void insert(Book book) {
        String sql = "insert into book (isbn, name, author, price, inventory, category_id, publish_id) values(?, ?, ?, ?, ?, ?, ?)";

        int publish_id = toPublishId(book.getPublish_name());
        int category_id = toCategoryId(book.getCategory_name());

        try (Connection con = JDBCConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, book.getIsbn());
            pstmt.setString(2, book.getName());
            pstmt.setString(3, book.getAuthor());
            pstmt.setInt(4, book.getPrice());
            pstmt.setInt(5, book.getInventory());
            pstmt.setInt(6, category_id);
            pstmt.setInt(7, publish_id);

            pstmt.executeUpdate();

            if (!con.getAutoCommit()) {
                con.commit();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Book book) {
        String sql = "update book set name=?, author=?, price=?, inventory=?, category_id=?, publish_id=? where isbn=?";

        int category_id = toCategoryId(book.getCategory_name());
        int publish_id = toPublishId(book.getPublish_name());

        try (Connection con = JDBCConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPrice());
            pstmt.setInt(4, book.getInventory());
            pstmt.setInt(5, category_id);
            pstmt.setInt(6, publish_id);
            pstmt.setString(7, book.getIsbn());

            int result = pstmt.executeUpdate();

            if (result == 0) {
                throw new SQLException("수정할 도서가 없습니다: " + book.getIsbn());
            }

            if (!con.getAutoCommit()) {
                con.commit();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String isbn){
        Connection con = JDBCConnector.getConnection();
        String sql = "delete from book where isbn = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int toPublishId(String publishName) {
        int publish_id = 1;

        switch (publishName) {
            case "한빛아카데미":
                publish_id = 1;
                break;
            case "생능출판사":
                publish_id = 2;
                break;
            case "민음사":
                publish_id = 3;
                break;
            case "해냄출판사":
                publish_id = 4;
                break;
        }

        return publish_id;
    }

    public int toCategoryId(String categoryName) {
        int category_id = 10;

        switch (categoryName) {
            case "IT도서":
                category_id = 10;
                break;
            case "소설":
                category_id = 20;
                break;
            case "수필":
                category_id = 30;
                break;
            case "시":
                category_id = 40;
                break;
        }

        return category_id;
    }
}
