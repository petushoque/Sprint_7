package ru.praktikumservices.qascooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.apache.http.HttpStatus.*;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private OrderClient orderClient;
    private Order order;
    private final List<String> color;
    private final int expectedCode;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    public CreateOrderTest(List<String> color, int expectedCode) {
        this.color = color;
        this.expectedCode = expectedCode;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {List.of("BLACK"), SC_CREATED},
                {List.of("GREY"), SC_CREATED},
                {List.of("BLACK", "GREY"), SC_CREATED},
                {null, SC_CREATED},
        };
    }

    @Test
    @DisplayName("Check status code and body of /orders")
    @Description("A test for a positive scenario, a successful server response is 201, the response body contains the track number")
    public void createOrderTest(){
        order = Order.getDefault();
        order.setColor(color);
        Response response = orderClient.post(order);
        response.then()
                .statusCode(SC_CREATED)
                .assertThat()
                .body("track", notNullValue());
        int actualCode = response.statusCode();
        Assert.assertEquals(expectedCode, actualCode);
    }
}