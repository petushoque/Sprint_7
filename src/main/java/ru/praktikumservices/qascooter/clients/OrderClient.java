package ru.praktikumservices.qascooter.clients;

import io.restassured.response.Response;
import ru.praktikumservices.qascooter.models.Order;

public class OrderClient extends RestAssuredClient {

    private final String ORDER_URL = "/orders";

    public Response post (Order order){
        return reqSpec
                .body(order)
                .when()
                .post(ORDER_URL);
    }

    public Response get (){
        return reqSpec
                .when()
                .get(ORDER_URL);
    }
}