package pl.ttrojan.api;

import org.springframework.stereotype.Component;
import pl.ttrojan.api.model.Post;
import pl.ttrojan.api.model.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by HomeAccount on 25.07.2017.
 */
@Component
public class DataStorage {

    private long nextUserId;
    private long nextPostId;
    private Map<Long, User> userRepository;
    private Map<Long, TreeSet<Post>> postRepository;
    private Map<Long, HashSet<Long>> followeeRepository;
    private Map<Long, HashSet<Long>> followersRepository;

    @PostConstruct
    void init() {
        nextUserId = 1L;
        nextPostId = 1L;
        userRepository = new HashMap<>();
        postRepository = new HashMap<>();
        followeeRepository = new HashMap<>();
        followersRepository = new HashMap<>();
    }

    public Map<Long, User> getUserRepository() {
        return userRepository;
    }

    public Map<Long, TreeSet<Post>> getPostRepository() {
        return postRepository;
    }

    public Map<Long, HashSet<Long>> getFolloweeRepository() {
        return followeeRepository;
    }

    public Map<Long, HashSet<Long>> getFollowersRepository() {
        return followersRepository;
    }

    public long getAndIncrementNextUserId() {
        return nextUserId++;
    }

    public long getAndIncrementNextPostId() {
        return nextPostId++;
    }
}
