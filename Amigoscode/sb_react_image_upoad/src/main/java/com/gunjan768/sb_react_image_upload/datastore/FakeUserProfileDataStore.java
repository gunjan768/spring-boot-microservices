package com.gunjan768.sb_react_image_upload.datastore;

import com.gunjan768.sb_react_image_upload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.*;

// Basically this class serves as a fake database
@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.addAll(Arrays.asList(
                new UserProfile(UUID.randomUUID(), "emilia768", null),
                new UserProfile(UUID.randomUUID(), "mallika 768", null)
        ));
    }

    public static List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}