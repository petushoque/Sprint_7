package ru.praktikumservices.qascooter;

import ru.praktikumservices.qascooter.Courier;
import ru.praktikumservices.qascooter.CourierCredentials;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient{

    private final String COURIER = "/courier";
    private final String LOGIN_URL = COURIER + "/login";
    private final String DELETE_COURIER_URL = COURIER + "/{courierId}";


    public boolean create(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(COURIER)
                .then().log().all()
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

    public int login(CourierCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN_URL)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }

    public Response postLogin(CourierCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN_URL);
    }

}