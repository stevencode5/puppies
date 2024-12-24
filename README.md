## Puppies

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

## Technologies
* Java 17
* Spring Boot 3.0+ running on Tomcat.
* Maven 3.8.4
* Docker

## How to run

1. Execute the script [create_database.sh](scripts/create_database.sh) which will create a MySQL database and a user with the necessary permissions in a Docker container.
2. Connect to the database and execute the script [create_tables.sql](scripts/create_tables.sql) to create the necessary tables and base data.
3. Set the environment variables
```bash
   export DATASOURCE_URL=jdbc:mysql://localhost:3306/puppies
   export DATASOURCE_USERNAME=puppies
   export DATASOURCE_PASSWORD=foo
```
4. Run the application using the command `mvn spring-boot:run`.

### Test endpoints using cURL

* POST /api/user - Create a user: They should have a name and an email
```bash
curl --location 'http://localhost:8080/api/user' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "name": "Steven",
      "email": "steven@email.com"
  }'
```

* POST /post - Create a post: They should have an image, some text content, and a date
```bash
curl -X POST -F "image=@src/main/resource/image.png" \
  -F "textContent=This is new post" \
  -F "userId=1" \
  http://localhost:8080/api/post 
```

* POST /post/like - Like a post
```bash
curl --location --request POST 'http://localhost:8080/api/post/like?postId=3&userId=3'
```

* GET /post/{id} - Fetch details of an individual post
```bash
curl --location 'http://localhost:8080/api/post/1'
```

* GET /post/user/{userId} - Fetch a list of posts the user made
```bash
curl --location 'http://localhost:8080/api/post/user/1'
```

## How to run unit tests

* Run the tests using the command `mvn test`.



