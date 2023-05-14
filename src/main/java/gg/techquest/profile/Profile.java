package gg.techquest.profile;

import gg.techquest.profile.state.PlayerState;
import gg.techquest.util.database.MongoRequest;

import lombok.Getter;
import lombok.Setter;

import org.bson.Document;

import java.util.UUID;

@Getter @Setter
public class Profile extends PlayerProfile {

    private UUID uuid;
    private String name;

    private PlayerState playerState;

    private int kills, deaths, killstreak;

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
                .put("name", name);

        return request;
    }

    @Override
    public void deserialize(Document document) {
        setKills(document.getInteger("kills", 0));
        setDeaths(document.getInteger("deaths", 0));
        setKillstreak(document.getInteger("kill_streak", 0));
        setEconomy(document.getInteger("economy", 0));
    }
}
