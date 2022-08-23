package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;


public class CourierClient extends RestClient {
    private static final String COURIER_PATH  = "api/v1/courier";
    private static final String COURIER_LOGIN = "api/v1/courier/login";
    private static final String COURIER_DELETE = "api/v1/courier/";

    //create
    @Step("Create new courier {courier}")
    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body (courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    //login

    @Step ("Courier login")
    public ValidatableResponse login(CourierCredentials credentials ) {
        return given()
                .spec(getBaseSpec())
                .body (credentials)
                .when()
                .post(COURIER_LOGIN)
                .then();
    }

    //delete
    @Step ("Delete courier")
    public ValidatableResponse delete (int id) {
        return given()
                .spec(getBaseSpec())
                .pathParam("id", id)
                .when()
                .delete(COURIER_DELETE + "{id}")
                .then();
    }
}
