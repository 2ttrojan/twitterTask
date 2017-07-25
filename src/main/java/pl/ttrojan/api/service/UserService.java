package pl.ttrojan.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ttrojan.api.DataStorage;
import pl.ttrojan.api.model.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HomeAccount on 25.07.2017.
 */

@Service
public class UserService {

    private DataStorage dataStorage;

    @Autowired
    public UserService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public User getById(Long id) {
        return dataStorage.getUserRepository().get(id);
    }

    public User save(User user) {
        long nextUserId = dataStorage.getAndIncrementNextUserId();
        user.setId(nextUserId);
        user.setName("MockName " + nextUserId);
        user.setSurname("MockSurname " + nextUserId);
        dataStorage.getUserRepository().put(nextUserId, user);
        return user;
    }

    public Set<Long> getFollowing(Long userId) {
        HashSet<Long> followingSet = dataStorage.getFollowingRepository().get(userId);
        if (followingSet == null) {
            return Collections.emptySet();
        }
        return followingSet;
    }

    public Set<Long> getFollowers(Long userId) {
        HashSet<Long> followerSet = dataStorage.getFollowersRepository().get(userId);
        if (followerSet == null) {
            return Collections.emptySet();
        }
        return followerSet;
    }

    public void follow(Long userId, Long followId) {
        HashSet<Long> followingSet = dataStorage.getFollowingRepository().get(userId);
        if (followingSet == null) {
            followingSet = new HashSet<>();
        }
        followingSet.add(followId);
        dataStorage.getFollowingRepository().put(userId, followingSet);

        HashSet<Long> followerSet = dataStorage.getFollowersRepository().get(userId);
        if (followerSet == null) {
            followerSet = new HashSet<>();
        }
        followerSet.add(userId);
        dataStorage.getFollowersRepository().put(followId, followerSet);
    }
}
