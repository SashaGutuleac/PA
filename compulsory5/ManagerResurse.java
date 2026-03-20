import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

// clasa care se ocupa de operatiile cu repository ul cum ar fi deschiderea fisierelor
public class ManagerResurse {

    // incercam sa deschidem fisierul cu aplicatia lui normala din windows
    public void deschideDocument(Document doc) {
        try {
            File fisier = new File(doc.getCaleFisier());

            // verificam daca calculator stie sa deschide
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                // daca fisierul exista il deschidem
                if (fisier.exists()) {
                    desktop.open(fisier);
                    System.out.println("am deschis fisierul " + doc.getNume()+"cu  calea: "+ doc.getCaleFisier());
                } else {
                    System.out.println("fisierul nu exista la calea data");
                }
            } else {
                System.out.println("desktop ul nu este suportat pe acest calculator");
            }
        } catch (IOException e) {
            // prindem eroarea daca ceva merge prost
            System.out.println("a dat o eroare cand am incercat sa deschid fisierul");
            e.printStackTrace();
        }
    }
}