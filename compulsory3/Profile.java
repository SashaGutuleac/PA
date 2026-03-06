/**
 * reprezinta profilul unei entitati din retea.
 */
public interface Profile {
    /**
     * returneaza id-ul unic al profilului.
     * @return id-ul profilului
     */
    String getUniqueId();

    /**
     * returneaza numele entitatii pentru afisare.
     * @return numele entitatii
     */
    String getDisplayName();
}