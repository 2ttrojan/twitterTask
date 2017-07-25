package pl.ttrojan.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ttrojan.api.DataStorage;
import pl.ttrojan.api.model.Post;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by HomeAccount on 25.07.2017.
 */

@Service
public class PostService {

    private DataStorage dataStorage;

    @Autowired
    public PostService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public Set<Post> getAllUserPosts(Long userId) {
        TreeSet<Post> posts = dataStorage.getPostRepository().get(userId);
        if (posts == null) {
            return Collections.emptySet();
        }

        return posts;
    }

    public Post save(String message, Long userId) {
        long nextPostId = dataStorage.getAndIncrementNextPostId();
        Post post = new Post();
        post.setPostId(nextPostId);
        post.setMessage(message);
        post.setUserId(userId);

        TreeSet<Post> userPosts = dataStorage.getPostRepository().get(userId);
        if (userPosts == null) {
            userPosts = new TreeSet<>();
        }
        userPosts.add(post);

        dataStorage.getPostRepository().put(userId, userPosts);

        return post;
    }

    public Set<Post> getFolloweesPosts(Long userId) {
        Set<Post> resultSet = new TreeSet<>();

        HashSet<Long> followeeSet = dataStorage.getFolloweeRepository().get(userId);
        if (followeeSet == null) {
            return Collections.emptySet();
        }

        for (Long followeeId : followeeSet) {
            TreeSet<Post> followeesPosts = dataStorage.getPostRepository().get(followeeId);
            if (followeesPosts != null && !followeesPosts.isEmpty()) {
                resultSet.addAll(followeesPosts);
            }
        }

        return resultSet;
    }

}
