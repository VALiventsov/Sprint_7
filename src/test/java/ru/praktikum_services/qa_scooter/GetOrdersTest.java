package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class GetOrdersTest {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient  = new OrderClient();
    }

    @Test
    @DisplayName("Client get orders list")
    @Description("Service return 200 and orders not empty")
    public void clientCanGetOrdersTest() {
        //запрашиваем список всех ордеров и проверяем, что он не пустой и не равен нулю
        ValidatableResponse response = orderClient.allOrders();
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_OK, statusCode);
        ArrayList<String> orderBody = response.extract().path("orders");
        boolean isNotEmpty = orderBody!=null && !orderBody.isEmpty();
        assertTrue("Orders is empty", isNotEmpty);


    }

}
