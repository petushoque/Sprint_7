package ru.praktikumservices.qascooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.apache.http.HttpStatus.*;

public class GetOrderListTest {
    private Order order;
    private OrderClient orderClient;

    @Before
    public void setUp() {
        order = Order.getDefault();
        orderClient = new OrderClient();
        orderClient.post(order);
    }

    @Test
    @DisplayName("Check status code and body of /orders")
    @Description("A test for a positive scenario, a successful server response is 200, the response body contains the orders")
    public void getOrdersList(){
        Response response = orderClient.get();
        response.then()
                .statusCode(SC_OK)
                .assertThat()
                .body("orders", notNullValue());
    }
}