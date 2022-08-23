package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginCourierTest {
    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courier = CourierGenerator.getRight();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Registered courier can login")
    @Description("Service return 200 when courier login")
    public void courierCanLoginTest () {
        //логинимся курьером
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        int loginStatusCode = loginResponse.extract().statusCode();
        //проверяем, что курьер залогинился
        assertEquals("Courier is not login", SC_OK, loginStatusCode);
        courierId = loginResponse.extract().path("id");
        //проверяем, что id что не равен нулю
        assertNotNull("Id is null", courierId);
    }



}
