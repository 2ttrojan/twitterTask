package pl.ttrojan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.ttrojan.api.model.Post;
import pl.ttrojan.api.model.User;
import pl.ttrojan.api.request.SavePostRequest;
import pl.ttrojan.api.service.PostService;
import pl.ttrojan.api.service.UserService;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("rest/post")
@Validated
public class PostController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public PostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @RequestMapping(value = "/getUserPosts/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Post>> getUserPosts(@PathVariable("id") long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<Post> allUserPost = postService.getAllUserPosts(userId);
        return new ResponseEntity<>(allUserPost, HttpStatus.OK);
    }

    @RequestMapping(value = "/getFollowingsPosts/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Post>> getFollowingsPosts(@PathVariable("id") long userId) {

        User user = userService.getById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<Post> followingPosts = postService.getFollowingPosts(userId);
        return new ResponseEntity<>(followingPosts, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> save(@Valid @RequestBody SavePostRequest request) {
        User user = userService.getById(request.getUserId());
        if (user == null) {
            user = userService.save(new User());
        }
        Post newPost = postService.save(request.getMessage(), user.getId());
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
}
