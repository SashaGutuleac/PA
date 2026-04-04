package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Desktop;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(" ne conectam la baza de date si citim din view");
           //cerem dao ului sa ne aduca filmele din baza de date
            MovieDAO movieDAO = new MovieDAO();
            List<Movie> listaFilme = movieDAO.getAllMoviesFromView();
            System.out.println("am gasit " + listaFilme.size() + " filme in baza de date.");

            System.out.println(" pregatim  ffreemarke");
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
            // ii spunem sa caute fisierul .ftl in folderul resources
            cfg.setClassForTemplateLoading(Main.class, "/");
            Template template = cfg.getTemplate("report.ftl");

            System.out.println(" combinam datele cu sablonul HTML...");
            //punem lista in lpachet sa i dam lu freemaker
            Map<String, Object> data = new HashMap<>();
            // movies este cuvantul pe care freemarker il cauta in html
            data.put("movies", listaFilme);

            System.out.println(" generam fisierul...");
            File fisierReport = new File("raport_filme.html");
            Writer out = new FileWriter(fisierReport);
            template.process(data, out);
            out.close();

            System.out.println("raportul a fost creat cu succes.");

            // Deschidem automat fisierul in browser-ul tau
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(fisierReport);
            }

        } catch (Exception e) {
            System.err.println("a aparut o eroare:");
            e.printStackTrace();
        }
    }
}