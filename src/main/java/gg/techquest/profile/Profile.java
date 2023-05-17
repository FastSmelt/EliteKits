package gg.techquest.profile;

import gg.techquest.EliteKits;
import gg.techquest.kit.Kit;
import gg.techquest.profile.state.PlayerState;
import gg.techquest.region.cube.tasks.VulnerableTask;
import gg.techquest.util.database.MongoRequest;

import lombok.Getter;
import lombok.Setter;

import org.bson.Document;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter @Setter
public class Profile extends PlayerProfile {

    private UUID uuid;
    private String name;

    private PlayerState playerState;
    private VulnerableTask vulnerableTask;

    private int kills, deaths, killstreak;

    private Kit previousKit;
    private int economy;

    public Profile(String name, UUID uuid) {
        super(uuid, "players");
        this.name = name;
        this.uuid = uuid;

        playerState = PlayerState.LOBBY;

        load();
    }

    @Override
    public MongoRequest serialize() {
        MongoRequest request = MongoRequest.newRequest("players", uuid)
                .put("deaths", getDeaths())
                .put("kills", getKills())
                .put("kill_streak", getKillstreak())
                .put("economy", getEconomy())
                .put("name", name)
                .put("previous_kit", previousKit != null ? previousKit.getName() : null);


        return request;
    }

    @Override
    public void deserialize(Document document) {
        setKills(document.getInteger("kills", 0));
        setDeaths(document.getInteger("deaths", 0));
        setKillstreak(document.getInteger("kill_streak", 0));
        setEconomy(document.getInteger("economy", 0));

        String previousKitName = document.getString("previous_kit");
        if (previousKitName != null) {
            previousKit = EliteKits.getInstance().getKitManager().getKit(previousKitName);
        } else {
            previousKit = null;
        }
    }
}
