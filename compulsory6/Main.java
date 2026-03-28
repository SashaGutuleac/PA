import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            GenreDAO genres = new GenreDAO();

            // cream cateva genuri in baza de date
            //genres.create("Action");
            //genres.create("Comedy");
            //genres.create(("Horror"));
            genres.create(("alabala"));
            System.out.println("am adaugat genurile cu succes!");

            //  testam cautaarea dupa nume
            Integer idActiune = genres.findByName("Action");
            System.out.println("id ul pentru action este " + idActiune);

            //  testam cautarea dupa id
            if (idActiune != null) {
                String numeGasit = genres.findById(idActiune);
                System.out.println("genul cu id ul " + idActiune + " este " + numeGasit);
            }

            // inchidem conexiunea la final
            Connection con = DatabaseConnection.getInstance();
            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {
            System.err.println("eroare la baza de date " + e.getMessage());
            e.printStackTrace();
        }
    }
}