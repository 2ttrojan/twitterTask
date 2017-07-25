package pl.ttrojan.api.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by HomeAccount on 25.07.2017.
 */
public class SavePostRequest implements Serializable {
    @NotNull
    private Long userId;
    @Length(max = 140, message = "Message too long")
    private String message;

    public Long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }
}
