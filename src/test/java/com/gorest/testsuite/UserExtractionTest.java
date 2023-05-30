package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI="https://gorest.co.in/public/v2";

        response =given()
                .when()
                .queryParam("page","1")
                .queryParam("per_page","20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }
    //1. Extract the All Ids
    @Test
    public void test1() {
        List<Object> totalId = response.extract().path("id");
        System.out.println("Total records are : " + totalId.size());
    }
    //2. Extract the all Names
    @Test
    public void test2() {
        List<Integer> allName = response.extract().path("name");
        System.out.println("Name of the All store is : " + allName);
    }
    //3. Extract the name of 5th object
    @Test
    public void test3() {
        String objectName = response.extract().path("[4].name");
        System.out.println("Name of 5th store : " + objectName);
    }
    //4. Extract the names of all object whose status = inactive
    @Test
    public void test4() {
        List<?> allInactiveStatus = response.extract().path("findAll{it.status=='inactive'}.id");
        System.out.println("Status of Inactive: " + allInactiveStatus);
    }
    //5. Extract the gender of all the object whose status = active
    @Test
    public void test5() {
        List<?> allActiveStatusGender = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("Status of Active: " + allActiveStatusGender);
    }
    //6. Print the names of the object whose gender = female
    @Test
    public void test6() {
        List<?> allObjectWithGenderFemale = response.extract().path("findAll{it.gender=='female'}.id");
        System.out.println("Gender is Female: " + allObjectWithGenderFemale);
    }
    //7. Get all the emails of the object where status = inactive
    @Test
    public void test7() {
        List<?> allEmailsOfStatusInactive = response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("Email Of Inactive : " + allEmailsOfStatusInactive);
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test8() {
        List<?> allIdWithGenderMale = response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("Gender is Female: " + allIdWithGenderMale);
    }

    //9. Get all the status
    @Test
    public void test9() {
        List<Integer> statusList = response.extract().path("status");
        statusList.size();
        System.out.println("All Status list : " + statusList.size());

    }
    //10. Get email of the object where name = Karthik Dubashi IV
    //11. Get gender of id = 5471
}
