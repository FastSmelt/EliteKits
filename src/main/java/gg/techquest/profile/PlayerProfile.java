package gg.techquest.profile;

import gg.techquest.EliteKits;
import gg.techquest.database.MongoRequest;

import gg.techquest.util.TaskUtil;

import lombok.Getter;
import org.bson.Document;

import java.util.UUID;
public abstract class PlayerProfile {

    @Getter
    private final UUID id;
    private final String collectionName;

    public PlayerProfile(UUID id, String collectionName) {
        this.id = id;
        this.collectionName = collectionName;
    }
    public final void load() {
        EliteKits.getInstance().getMongoStorage().getOrCreateDocument(collectionName, id, (document, exists) -> {
            if (exists) {
                deserialize(document);
            }
            save(false);
        });
    }
    public final void save(boolean async) {
        MongoRequest request = serialize();
        if (async) {
            TaskUtil.runAsync(EliteKits.getInstance(), request::run);
        } else {
            request.run();
        }
    }
    public abstract MongoRequest serialize();
    public abstract void deserialize(Document serialized);
}