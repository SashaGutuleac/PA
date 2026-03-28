import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreDAO {

    // creeaza un gen nou
    public void create(String name) throws SQLException {
        Connection con = DatabaseConnection.getInstance();
        String sql = "INSERT INTO genres (name) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    // gaseste id ul unui gen dupa nume
    public Integer findByName(String name) throws SQLException {
        Connection con = DatabaseConnection.getInstance();
        String sql = "SELECT id FROM genres WHERE name = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return null;
    }

    // gaseste numele unui gen dupa id
    public String findById(int id) throws SQLException {
        Connection con = DatabaseConnection.getInstance();
        String sql = "SELECT name FROM genres WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        }
        return null;
    }
}