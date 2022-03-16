package severstal.zorin.hw17;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

public class TestHomework17 {

    @Test
    void singleUserTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void userListTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void successfulLogin() {
        String data = "{ \"email\": \"eve.holt@reqres.in\", " +
                "\"password\": \"cityslicka\" }";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void negativeLogin() {
        String data = "{ \"email\": \"eve.holt@reqres.in\" }";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void resourceListTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void singleResourceTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("data.color", is("#C74375"));
    }

    @Test
    void singleUserNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);


    }
}