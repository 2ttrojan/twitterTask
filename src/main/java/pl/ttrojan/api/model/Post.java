package pl.ttrojan.api.model;

/**
 * Created by HomeAccount on 25.07.2017.
 */
public class Post implements Model, Comparable<Post> {

    private Long postId;
    private Long userId;
    private String message;

    public Post() {
    }

    public Post(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(Post o) {
        return -this.postId.compareTo(o.postId);
    }
}
