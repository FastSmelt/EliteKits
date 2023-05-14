package gg.techquest.profile;

import gg.techquest.profile.Profile;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {
    private final Map<UUID, Profile> profiles = new HashMap<>();
    public Profile createProfile(String name, UUID id) {
        Profile profile = new Profile(name, id);
        profiles.put(id, profile);
        return profile;
    }

    public Profile getProfile(UUID id) {
        return profiles.get(id);
    }

    public Profile getProfile(Player player) {
        return getProfile(player.getUniqueId());
    }

    public void removeProfile(UUID id) {
        profiles.remove(id);
    }

    public void saveProfiles() {
        for (Profile profile : profiles.values()) {
            profile.save(false);
        }
    }
}