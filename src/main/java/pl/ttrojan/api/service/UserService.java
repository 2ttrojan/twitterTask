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

    public Set<Long> getFollowees(Long userId) {
        HashSet<Long> followeeSet = dataStorage.getFolloweeRepository().get(userId);
        if (followeeSet == null) {
            return Collections.emptySet();
        }
        return followeeSet;
    }

    public Set<Long> getFollowers(Long userId) {
        HashSet<Long> followerSet = dataStorage.getFollowersRepository().get(userId);
        if (followerSet == null) {
            return Collections.emptySet();
        }
        return followerSet;
    }

    public void follow(Long userId, Long followId) {
        HashSet<Long> followeeSet = dataStorage.getFolloweeRepository().get(userId);
        if (followeeSet == null) {
            followeeSet = new HashSet<>();
        }
        followeeSet.add(followId);
        dataStorage.getFolloweeRepository().put(userId, followeeSet);

        HashSet<Long> followerSet = dataStorage.getFollowersRepository().get(userId);
        if (followerSet == null) {
            followerSet = new HashSet<>();
        }
        followerSet.add(userId);
        dataStorage.getFollowersRepository().put(followId, followerSet);
    }
}
