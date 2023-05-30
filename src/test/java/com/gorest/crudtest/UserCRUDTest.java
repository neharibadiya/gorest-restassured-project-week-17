package com.gorest.crudtest;

import com.gorest.models.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    @Test
    public void verifyUserCreatedSuccessfully(){
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Shreyashi Gupta");
        userPojo.setEmail("shreyashi_gupta@schiller.test");
        userPojo.setGender("female");
        userPojo.setStatus("inactive");
        Response response =
                given().log().all()
                        .header("Content-Type","application/json")
                        .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                        .body(userPojo)
                        .when()
                        .post("/users");
        response.then().statusCode(422);
        response.prettyPrint();
    }
    @Test
    public void verifyUserUpdatedSuccessfully(){
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Shreyashi Gupta");
        userPojo.setEmail("shreyashi_gupta111@schiller.test");
        userPojo.setGender("female");
        userPojo.setStatus("inactive");
        Response response =
                given().log().all()
                        .header("Content-Type","application/json")
                        .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                        .body(userPojo)
                        .when()
                        .patch("/users/10603");
        response.then().statusCode(404);
        response.prettyPrint();
    }
    @Test
    public void verifyUserUpdateSuccessfully() {
        Response response = given()
                .when()
                .get("/users");
        response.then().statusCode(200);
        response.prettyPrint();

    }
    @Test
    public void verifyUserDeleteSuccessfully() {
        Response response = given()

                .header("Content-Type","application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")

                .when()
                .delete("/users/5315");
        response.then().statusCode(404);
        response.prettyPrint();
    }

}
