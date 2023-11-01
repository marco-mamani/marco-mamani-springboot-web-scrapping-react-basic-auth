# springboot-web-scrapping-react-basic-auth

Web scraping allows you to acquire non-tabular or poorly structured data from websites and convert it into a usable, structured format, such as a . csv file or spreadsheet. Scraping is about more than just acquiring data: it can also help you archive data and track changes to data online.

## Applications

- ### web-scrapping-api

  `Spring Boot` Web Java backend application that exposes a Rest API to create, retrieve and delete users, links, linkDetails, and scrapwebsite.  
  If a user has `ADMIN` role he/she can also retrieve information of other users or delete them.

  The application secured endpoints that just can be accessed if a user has valid credentials (`username` and `password`) 
  and has autorization roles for it.

  `web-scrapping-api` stores its data in [`Postgres`](https://www.postgresql.org/) database.

  `webscrap-api` has the following endpoints

  | Endpoint                                                      | Secured | Roles           |
    | ------------------------------------------------------------- | ------- | --------------- |
  | `POST /auth/authenticate -d {"username","password"}`          | No      |                 |
  | `POST /auth/signup -d {"username","password","name","email"}` | No      |                 |
  | `GET /public/numberOfUsers`                                   | No      |                 |
  | `GET /public/numberOfLinks`                                   | No      |                 |
  | `GET /api/users/me`                                           | Yes     | `ADMIN`, `USER` |
  | `GET /api/users`                                              | Yes     | `ADMIN`         |
  | `GET /api/users/{username}`                                   | Yes     | `ADMIN`         |
  | `DELETE /api/users/{username}`                                | Yes     | `ADMIN`         |
  | `GET /api/links [?text]`                                      | Yes     | `ADMIN`, `USER` |
  | `POST /api/links -d {"url","description"}`                    | Yes     | `ADMIN`         |
  | `DELETE /api/links/{id}`                                      | Yes     | `ADMIN`         |

- ### webscrap-ui

  `React` frontend application where a user with role `USER` can retrieve the information of a specific link
  or a list of links. On the other hand, a user with role `ADMIN` has access to all secured endpoints.

  To login, a `user` or `admin` must provide valid `username` and `password` credentials. `webscrap-ui` communicates
  with `web-scrapping-api` to get `links` and `users` data.

  `webscrap-ui` uses [`Semantic UI React`](https://react.semantic-ui.com/) as CSS-styled framework.

## Prerequisites

- [`npm`](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)
- [`Java 17+`](https://www.oracle.com/java/technologies/downloads/#java17)
- [`Docker`](https://www.docker.com/)
- Lombok: Java library that makes the code cleaner and gets rid of boilerplate code.
  Spring WEB : Product of the Spring community focused on creating document-driven Web services.
- JSoup: jsoup is a Java library for working with real-world HTML. It provides a very convenient API for extracting and manipulating data, using the best of DOM, CSS, and jquery-like methods. - https://jsoup.org/   

## Start Environment

- In a terminal, make sure you are inside `springboot-web-scrapping-react-basic-auth` root folder

## Running book-app using Maven & Npm

- **web-scrapping-api**

    - Open a terminal and navigate to `springboot-web-scrapping-react-basic-auth/web-scrapping-api` folder

    - Run the following `Maven` command to start the application
      ```
      ./mvnw clean spring-boot:run
      ```

- **webscrap-ui**

    - Open another terminal and navigate to `springboot-web-scrapping-react-basic-auth/webscrap-ui` folder

    - Run the command below if you are running the application for the first time
      ```
      npm install
      ```

    - Run the `npm` command below to start the application
      ```
      npm start
      ```

## Applications URLs

| Application          | URL                                   | Credentials                                         |
| -------------------- | ------------------------------------- | --------------------------------------------------- |
| web-scrapping-api    | http://localhost:8080/swagger-ui.html |                                                     |
| webscrap-ui          | http://localhost:3000                 | `admin/admin`, `user/user` or signing up a new user |

> **Note**: the credentials shown in the table are the ones already pre-defined. You can signup new users.

## Util Commands

- **Postgres**
  ```
  docker exec -it postgres psql -U postgres -d webscrapdb
  \dt
  ```

## Shutdown

- To stop `web-scrapping-api` and `webscrap-ui`, go to the terminals where they are running and press `Ctrl+C`

## References

- https://www.taniarascia.com/using-context-api-in-react/
- https://jasonwatmore.com/post/2018/09/11/react-basic-http-authentication-tutorial-example
- https://jsoup.org/apidocs/

# TO - DO
- The APIs services are complete and ready to be called from FE
- However, because of the time I coudnt complete the FE part to display the Link Details for each Link, but the API is ready to be consumed.  
- Right now the application is displaying the Links stored in the database
- The users also are displayed correctly.