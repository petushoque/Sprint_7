package ru.praktikumservices.qascooter;

import io.restassured.response.Response;

public class CourierClient extends RestAssuredClient{

    private final String COURIER = "/courier";
    private final String LOGIN_URL = COURIER + "/login";


    public boolean create(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(COURIER)
                .then()
                .log()
                .all()
                .statusCode(201)
                .extract()
                .path("ok");
    }

    public Response post(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(COURIER);
    }

    public Response postLogin(CourierCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN_URL);
    }

}