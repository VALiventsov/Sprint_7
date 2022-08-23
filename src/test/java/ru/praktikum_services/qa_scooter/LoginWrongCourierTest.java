package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.Assert.assertEquals;

public class LoginWrongCourierTest {
    private Courier courier;
    private CourierClient courierClient;


    @Before
    public void setUp() {
        courier = CourierGenerator.getNotRegCourier();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Didn't registered courier can't login")
    @Description("Service return 404 when courier login")
    public void courierCantLoginTest () {
        //логинимся курьером
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        int loginStatusCode = loginResponse.extract().statusCode();
        //проверяем, что курьер залогинился
        assertEquals("Courier is not login", SC_NOT_FOUND, loginStatusCode);
    }
}
