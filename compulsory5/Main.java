// clasa principala de unde pornim programul
public class Main {
    public static void main(String[] args) {
        // facem repository ul gol
        Repository repo = new Repository();

        // punem caile exacte din directorul meu
        Document doc4 = new Document("fisier text", "D:\\Facultate\\UNIVER AN-2\\PROGRAMARE_AVANSATA\\test");
        Document doc1 = new Document("fisier text", "D:\\Facultate\\UNIVER AN-2\\PROGRAMARE_AVANSATA\\test.txt");
        Document doc2 = new Document("poza mea", "D:\\Facultate\\UNIVER AN-2\\PROGRAMARE_AVANSATA\\poza.png");

        // bagam documentele in repository
        repo.adaugaResursa(doc1);
        repo.adaugaResursa(doc2);
        repo.adaugaResursa(doc4);

        // facem managerul ca sa lucram cu ele
        ManagerResurse manager = new ManagerResurse();

        // deschidem primul document adica textul
        manager.deschideDocument(doc1);
        manager.deschideDocument(doc4);


    }
}