package kindof.gokzachievementsweb;

import kindof.gokzachievementsweb.utils.SteamOpenID;

import java.util.Map;
import java.util.Properties;

public class Globals {
    private static final Properties properties = new Properties();

    static {
        Map<String, String> envVars = System.getenv();
        for (Map.Entry<String, String> var : envVars.entrySet()) {
            properties.setProperty(var.getKey(), var.getValue());
        }
    }

    public static final String URL = properties.getProperty("app-url");
    public static final String HEROKU_DATABASE_URL = properties.getProperty("DATABASE_URL");
    public static final String DATABASE_URL;
    public static final String DATABASE_USERNAME;
    public static final String DATABASE_PASSWORD;

    static {
        String[] splitted = HEROKU_DATABASE_URL.split("@");

        String[] usernamePassword = splitted[0].split("://")[1].split(":");
        String[] hostPortName = splitted[1].split("/");
        String[] hostPort = hostPortName[0].split(":");

        String databaseName = hostPortName[1];
        String databaseHost = hostPort[0];
        String databasePort = hostPort[1];

        DATABASE_URL = "jdbc:postgresql://" + databaseHost + ":" + databasePort + "/" + databaseName;
        DATABASE_USERNAME = usernamePassword[0];
        DATABASE_PASSWORD = usernamePassword[1];
    }

    public static final String DISCORD_AUTH_URL = properties.getProperty("discord-auth-url");
    public static final String DISCORD_CALLBACK_URL = URL + "/discord-auth";
    public static final String DISCORD_CLIENT_ID = properties.getProperty("discord-client-id");
    public static final String DISCORD_CLIENT_SECRET = properties.getProperty("discord-client-secret");

    public static final SteamOpenID STEAM_OPEN_ID = new SteamOpenID();
}
