package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }
    //1. Verify the if the total record is 20
    @Test
    public void test1() {
        response.body("total.size()", equalTo(20));
    }
    //2. Verify the if the name of id = 2322233 is equal to "Dharitri Nayar"
    @Test
    public void test2() {
        response.body("[2].name", equalTo("Dharitri Nayar"));
    }
    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test3() {
        response.body("[2].name", equalTo("Dharitri Nayar"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList ("Aaryan Sharma", "Dharitri Nayar", "Mayoor Adiga", "Shubhaprada Sethi")
    @Test
    public void multipleNames() {
        response.body("name", hasItems("Aaryan Sharma", "Dharitri Nayar", "Mayoor Adiga", "Shubhaprada Sethi"));
    }

    //5. Verify the email of userid = 2322231 is equal “mayoor_adiga@gusikowski.example”
    @Test
    public void verifyEmail() {
        response.body("[3].email", equalTo("shubhaprada_sethi@kris.example"));
    }

    //6. Verify the status is “Active” of user name is “Dharitri Nayar”
    @Test
    public void verifyStatus(){
        response.body("[2].status", equalTo("active"));
    }
    //7. Verify the Gender = female of user name is “Sen. Bhisham Pillai"
    @Test
    public void verifyGenderFemale(){
        response.body("[0].gender", equalTo("female"));
    }
}
