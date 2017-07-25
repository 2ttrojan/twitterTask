package pl.ttrojan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ttrojan.api.model.User;
import pl.ttrojan.api.request.FollowUserRequest;
import pl.ttrojan.api.service.UserService;

@RestController
@RequestMapping("rest/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> byId(@PathVariable("id") long id) {
        User user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/follow", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> follow(@RequestBody FollowUserRequest request) {

        if (request.getFolloweeId().equals(request.getUserId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        User user = userService.getById(request.getUserId());
        if (user == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        User followUser = userService.getById(request.getFolloweeId());
        if (followUser == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        userService.follow(request.getUserId(), request.getFolloweeId());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
