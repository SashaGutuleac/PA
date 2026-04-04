package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    // metoda asta citest fix din viweul creat
    public List<Movie> getAllMoviesFromView() {
        List<Movie> movies = new ArrayList<>();
        // apel viewul creat anterior
        String sql = "SELECT id, title FROM movie_report_view";
         //conexiunea hikari
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // cat timp avem randuri returnate din baza de date
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                // cream un obiect movie si il bagam in lista
                movies.add(new Movie(id, title));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }
}