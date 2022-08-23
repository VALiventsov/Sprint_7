package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class CreateCourierTest {

    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courier = CourierGenerator.getDefault();
        courierClient = new CourierClient();

    }

    //удаляем курьера
    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Courier can be created Successfully")
    @Description("Service return 201 Created when new courier created")
    public void courierCanBeCreatedTest() {
        //создвем и проверяем что курьер создан
        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCreated);
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
