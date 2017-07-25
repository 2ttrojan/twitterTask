package pl.ttrojan.api.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by HomeAccount on 25.07.2017.
 */
public class FollowUserRequest implements Serializable {

    @NotNull
    private Long userId;
    @NotNull
    private Long followId;

    public Long getUserId() {
        return userId;
    }

    public Long getFollowId() {
        return followId;
    }
}
