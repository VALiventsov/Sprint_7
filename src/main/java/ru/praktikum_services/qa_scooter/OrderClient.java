package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient{
    private static final String ORDER_PATH = "api/v1/orders";

    //создание заказа
    @Step("Create new order {order}")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body (order)
                .when()
                .post(ORDER_PATH)
                .then();
    }
    //вызов списка заказов
    @Step("Get all orders")
    public ValidatableResponse allOrders() {
        return given()
                .spec(getBaseSpec())
                .get(ORDER_PATH)
                .then();
    }
}
