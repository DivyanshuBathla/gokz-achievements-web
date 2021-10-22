package kindof.gokzachievementsweb.utils;

import org.apache.commons.lang3.StringUtils;

public class SteamIDUtil {

    public static String convertFrom64(String steamID64) {
        String bin = Long.toBinaryString(Long.parseLong(steamID64));
        String fullBin = StringUtils.repeat("0", Long.SIZE - bin.length()) + bin;

        String first32 = fullBin.substring(0, 32);
        String last32 = fullBin.substring(32, 64);

        return "STEAM_" + Integer.parseInt(first32.substring(0, 8), 2) + ":" + last32.charAt(31) + ":" + Integer.parseInt(last32.substring(0, 31), 2);
    }
}
