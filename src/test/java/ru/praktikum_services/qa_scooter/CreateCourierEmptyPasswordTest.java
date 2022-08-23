package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

public class CreateCourierEmptyPasswordTest {
    private Courier courier;
    private CourierClient courierClient;

    @Before
    public void setUp() {
        courier = CourierGenerator.getWrong();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Create Courier without password")
    @Description("Service return 400 Bad Request when Courier create without field password")
    public void courierCannotCreateTest () {
        //создаем и проверяем что курьер не создан
        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, statusCode);
    }

}
