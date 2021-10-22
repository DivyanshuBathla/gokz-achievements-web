package kindof.gokzachievementsweb.controllers;

import com.mrpowergamerbr.temmiediscordauth.TemmieDiscordAuth;
import kindof.gokzachievementsweb.Globals;
import kindof.gokzachievementsweb.dao.UserDAO;
import kindof.gokzachievementsweb.models.UserModel;
import kindof.gokzachievementsweb.utils.SteamIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/register")
    public String register() {
        return "redirect:" + Globals.DISCORD_AUTH_URL;
    }

    @GetMapping("/discord-auth")
    public String discordAuth(@RequestParam Map<String, String> requestParams) {
        return "redirect:" + Globals.STEAM_OPEN_ID.login(Globals.URL + "/auth?discord_auth=" + requestParams.get("code"));
    }

    @GetMapping("/auth")
    public String steamAuth(@RequestParam(name = "discord_auth") String discordAuthCode, @RequestParam Map<String, String> requestParams) {
        TemmieDiscordAuth temmieDiscordAuth = new TemmieDiscordAuth(
                discordAuthCode,
                Globals.DISCORD_CALLBACK_URL,
                Globals.DISCORD_CLIENT_ID,
                Globals.DISCORD_CLIENT_SECRET
        );
        temmieDiscordAuth.doTokenExchange();

        String discordID = temmieDiscordAuth.getCurrentUserIdentification().getId();
        String steamID64 = Globals.STEAM_OPEN_ID.verify(Globals.URL + "/auth?discord_auth=" + discordAuthCode, requestParams);
        String steamID = SteamIDUtil.convertFrom64(steamID64);

        UserDAO.add(new UserModel(discordID, steamID64, steamID));

        return "redirect:/";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
