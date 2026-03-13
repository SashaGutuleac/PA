// clasa pentru companii
public class Company implements Profile {
    private String corpName;
    private String corpId;
    private String mascotName; // creativitate: mascota firmei

    // constructorul
    public Company(String corpName, String corpId, String mascotName) {
        this.corpName = corpName;
        this.corpId = corpId;
        this.mascotName = mascotName;
    }

    @Override
    public String getUniqueId() {
        return corpId;
    }

    @Override
    public String getDisplayName() {
        return corpName;
    }

    public String getMascotName() {
        return mascotName;
    }

    @Override
    public String toString() {
        return "companie: " + corpName + " (mascota: " + mascotName + ")";
    }
}