package gg.techquest.profile;

import gg.techquest.database.MongoRequest;

import lombok.Getter;
import lombok.Setter;

import org.bson.Document;

import java.util.UUID;

@Getter @Setter
public class Profile extends PlayerProfile {

    private UUID uuid;
    private String name;

    private int kills, deaths, killstreak;

    public Profile(String name, UUID uuid) {
        super(uuid, "players");
        this.name = name;
        this.uuid = uuid;

        load();
    }

    @Override
    public MongoRequest serialize() {
        MongoRequest request = MongoRequest.newRequest("kitpvp", uuid)
                .put("deaths", getDeaths())
                .put("kills", getKills())
                .put("kill_streak", getKillstreak())
                .put("name", name);

        return request;
    }

    @Override
    public void deserialize(Document document) {
        setDeaths(document.getInteger("kills", 0));
        setDeaths(document.getInteger("deaths", 0));
        setDeaths(document.getInteger("killstreak", 0));
    }
}
