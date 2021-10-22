package kindof.gokzachievementsweb.models;

public class UserModel {
    private String discordID;
    private String steamID64;
    private String steamID;

    public UserModel(String discordID, String steamID64, String steamID) {
        this.discordID = discordID;
        this.steamID64 = steamID64;
        this.steamID = steamID;
    }

    public void setDiscordID(String discordID) {
        this.discordID = discordID;
    }

    public void setSteamID64(String steamID64) {
        this.steamID64 = steamID64;
    }

    public void setSteamID(String steamID) {
        this.steamID = steamID;
    }

    public String getDiscordID() {
        return discordID;
    }

    public String getSteamID64() {
        return steamID64;
    }

    public String getSteamID() {
        return steamID;
    }
}
