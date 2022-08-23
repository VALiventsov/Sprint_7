package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

public class LoginCourierEmptyPasswordTest {
    private Courier courier;
    private CourierClient courierClient;

    @Before
    public void setUp() {
        courier = CourierGenerator.getWrong();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Login Courier without password")
    @Description("Service return 400 Bad Request when Courier login without field password")
    public void courierCannotCreateTest () {
        //логинимся с курьером без пароля
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, loginStatusCode);
    }

}
