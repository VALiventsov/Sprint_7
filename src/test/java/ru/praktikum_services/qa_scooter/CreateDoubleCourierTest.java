package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class CreateDoubleCourierTest {

    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courier = CourierGenerator.getDefault();
        courierClient = new CourierClient();

    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Client can't create double courier")
    @Description("Service return 400 Bad Request when double courier created")
    public void doubleCourierCreatedTest() {
        //создаем и проверяем что курьер создан
        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCreated);
        //логинимся для получения id
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        int loginStatusCode = loginResponse.extract().statusCode();
        courierId = loginResponse.extract().path("id");
        assertNotNull("Id is null", courierId);
        //повторно создаем курьера
        ValidatableResponse doubleResponse = courierClient.create(courier);
        int doubleStatusCode = doubleResponse.extract().statusCode();
        assertEquals("Status code is incorrect",SC_CONFLICT, doubleStatusCode);
    }
}
