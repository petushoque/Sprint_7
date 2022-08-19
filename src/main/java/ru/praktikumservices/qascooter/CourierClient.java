package ru.praktikumservices.qascooter;

import io.restassured.response.Response;

public class CourierClient extends RestAssuredClient{

    private final String COURIER = "/courier";
    private final String LOGIN_URL = COURIER + "/login";


    public Response createCourier(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(COURIER);
    }

    public Response loginCourier(CourierCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN_URL);
    }
}