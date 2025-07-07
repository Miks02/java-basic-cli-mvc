package repository;

import utility.DBUtil;
import model.Book;
import utility.LoggerHelper;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.*;
import java.sql.*;


public class SqlBookRepository implements ICrudOperations  {
    private final DBUtil db;
    private Connection conn;
    private static final Logger logger = LoggerHelper.getLogger();

    public SqlBookRepository(DBUtil db) {
        this.db = db;
    }

    public void beginTransaction() {
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);

            logger.info("Transaction begun successfully!");
        }
        catch (SQLException e) {
            logger.severe("Failed to establish connection with database!\nMessage: " + e.getMessage());
        }
    }

    public void commitTransaction() {
        try {
            conn.commit();
            logger.info("*Transaction commited successfully!*");
        }
        catch (SQLException e) {
            logger.severe("*Transaction failed to commit!*\nMessage: " + e.getMessage());
        }
    }

    public void rollback() {
        try {
            conn.rollback();
            logger.info("Rolling back changes... done!");
        }
        catch(SQLException e) {
            logger.severe("*Failed to rollback changes*\nMessage: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if(conn != null)
                conn.close();
            logger.info("Connection closed!");
        }
        catch (SQLException e) {
            logger.severe("*Failed to close a connection*\nMessage: " + e.getMessage());
        }
    }

    @Override
    public void insert(Object object) {
        String sql = "INSERT INTO books(Title,Author,YearOfRelease,Status) VALUES(?,?,?,?)";
        Book newBook = (Book) object;
        try (PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, newBook.getTitle());
            pstmt.setString(2, newBook.getAuthor());
            pstmt.setInt(3, newBook.getYear());
            pstmt.setString(4, newBook.getStatus());

            pstmt.executeUpdate();


        } catch(Exception e) {

            logger.severe("Critical error!\nMessage: " + e.getMessage());
        }

    }

    @Override
    public void delete(String title) {
        String sql = "DELETE FROM books WHERE Title = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, title);
            pstmt.executeUpdate();


        } catch(Exception e) {
            logger.severe("Critical error!\nMessage: " + e.getMessage());
        }


    }
    @Override
    public Optional<Object> update(String titleSearch, Object object) {
        String sql = "UPDATE books SET Title = ? , Author = ?, YearOfRelease = ?, Status = ? WHERE Title = ?";
        Book updatedBook = (Book) object;
        try(PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, updatedBook.getTitle());
            pstmt.setString(2, updatedBook.getAuthor());
            pstmt.setInt(3, updatedBook.getYear());
            pstmt.setString(4, updatedBook.getStatus());
            pstmt.setString(5, titleSearch);

            pstmt.executeUpdate();
            logger.info("Update executed successfully!");
            return Optional.of(updatedBook);
        }
        catch (SQLException e) {
            logger.severe("Critical error!\nMessage: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ArrayList<Object>> selectAll() {
        String sql = "SELECT * FROM books";
        ArrayList<Object> list = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery())
        {
            if(rs == null)
                return Optional.empty();

            while(rs.next()) {
                list.add(new Book (rs.getInt("ID"), rs.getString("Title"), rs.getString("Author"), rs.getInt("YearOfRelease"), rs.getString("Status")));
            }

            return Optional.of(list);
        }
        catch (SQLException e) {
            logger.severe("*'SELECT' operation failed!*\nMessage: " + e.getMessage());
            return Optional.empty();
        }

    }



}
