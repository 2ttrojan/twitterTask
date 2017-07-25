# Building app

mvn clean install<br />

# Running

mvn spring-boot:run<br />

# Testing

You can use for example Postman to test api<br />

# Api method:
Note that I added extra validation.<br />
Below you will can find more information about it.<br />

All method have response format: JSON<br />

# Posting - Scenario name Posting

Request format: JSON<br />
Method - POST<br />
URL: localhost:8080/rest/post/save<br />
Request e.g:

            {
                "userId": 1,
                "message": "New Post"
            }

Validation:<br />
userId cannot be null<br />
message cannot be empty<br />
message length less than 141 chars<br />

New user will be created if userId not exist.<br />

Response e.g.:<br />

            {
                "postId": 6,
                "userId": 1,
                "message": "New Post"
            }

# Getting user details by user id

Method - GET<br />
URL: localhost:8080/rest/user/byId/{id}<br />
e.g localhost:8080/rest/user/byId/2<br />

Response e.g.<br />
            {
                "id": 2,
                "name": "MockName 2",
                "surname": "MockSurname 2"
            }

# Getting user posts by user id - Scenario name Wall

Method - GET<br />
URL: localhost:8080/rest/post/getUserPosts/{id}<br />
e.g localhost:8080/rest/post/getUserPosts/1<br />

Response e.g.

            [
                {
                    "postId": 6,
                    "userId": 1,
                    "message": "test 3"
                },
                {
                    "postId": 2,
                    "userId": 1,
                    "message": "test 2"
                },
                {
                    "postId": 1,
                    "userId": 1,
                    "message": "test"
                }
            ]

# Follow other user - Scenario name Following

Request format: JSON<br />
Method - POST<br />
URL: localhost:8080/rest/user/follow<br />

Request e.g.
            {
                "userId": 1,
                "followeeId": "2"
            }

Validation
 userId cannot be null<br />
 followeeId cannot be null<br />
 user and followee user must exist<br />
 userId not equals followeeId<br />

# Getting followees posts by user id - Scenario name Timeline

Method - GET<br />
URL: localhost:8080/rest/post/getFolloweesPosts/{id}<br />
e.g localhost:8080/rest/post/getFolloweesPosts/3<br />

Response e.g.

            [
                {
                    "postId": 6,
                    "userId": 2,
                    "message": "New Post"
                },
                {
                    "postId": 5,
                    "userId": 1,
                    "message": "New Post"
                },
                {
                    "postId": 4,
                    "userId": 1,
                    "message": "New Post"
                },
                {
                    "postId": 3,
                    "userId": 1,
                    "message": "New Post"
                },
                {
                    "postId": 2,
                    "userId": 1,
                    "message": "New Post"
                },
                {
                    "postId": 1,
                    "userId": 1,
                    "message": "New Post"
                }
            ]