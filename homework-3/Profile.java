// interfata care defineste un profil din retea
public interface Profile {
    // returneaza id-ul unic
    String getUniqueId();

    // returneaza numele pentru afisare
    String getDisplayName();
}