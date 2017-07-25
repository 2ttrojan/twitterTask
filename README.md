# Building app

mvn clean install

# Running

mvn spring-boot:run

# Testing

You can use for example Postman to test api

# Api method:
Note that I added extra validation.
Below you will can find more information about it.

All method have response format: JSON

# Posting - Scenario name Posting

Request format: JSON
Method - POST
URL: localhost:8080/rest/post/save
Request e.g:

            {
                "userId": 1,
                "message": "New Post"
            }

Validation:
userId cannot be null
message cannot be empty
message length less than 141 chars

New user will be created if userId not exist .

Response e.g.:

            {
                "postId": 6,
                "userId": 1,
                "message": "New Post"
            }

# Getting user details by user id

Method - GET
URL: localhost:8080/rest/user/byId/{id}
e.g localhost:8080/rest/user/byId/2

Response e.g.
            {
                "id": 2,
                "name": "MockName 2",
                "surname": "MockSurname 2"
            }

# Getting user posts by user id - Scenario name Wall

Method - GET
URL: localhost:8080/rest/post/getUserPosts/{id}
e.g localhost:8080/rest/post/getUserPosts/1

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

Request format: JSON
Method - POST
URL: localhost:8080/rest/user/follow

Request e.g.
            {
                "userId": 1,
                "followeeId": "2"
            }

Validation
userId cannot be null
followeeId cannot be null
user and followee user must exist
userId not equals followeeId

# Getting followees posts by user id - Scenario name Timeline

Method - GET
URL: localhost:8080/rest/post/getFolloweesPosts/{id}
e.g localhost:8080/rest/post/getFolloweesPosts/3

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