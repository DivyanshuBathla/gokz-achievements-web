package kindof.gokzachievementsweb.dao;

import kindof.gokzachievementsweb.models.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO {

    public static void add(UserModel userModel) {
        String discordID = userModel.getDiscordID();
        UserModel user = selectByDiscordID(discordID);

        if (user == null) {
            add("users", discordID, userModel.getSteamID64(), userModel.getSteamID());
        } else {
            updateByDiscordID(userModel);
        }
    }

    public static UserModel selectByDiscordID(String discordID) {
        ResultSet resultSet = selectBy("users", "discord_id", discordID);
        try {
            if (resultSet.next()) {
                return new UserModel(
                        resultSet.getString("discord_id"),
                        resultSet.getString("steam_id64"),
                        resultSet.getString("steam_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.getStatement().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void updateByDiscordID(UserModel userModel) {
        updateBy(
                "users",
                "discord_id", userModel.getDiscordID(),
                "steam_id64", userModel.getSteamID64(),
                "steam_id", userModel.getSteamID()
        );
    }
}
