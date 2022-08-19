package ru.praktikumservices.qascooter.clients;

import io.restassured.response.Response;
import ru.praktikumservices.qascooter.models.Courier;
import ru.praktikumservices.qascooter.models.CourierCredentials;

public class CourierClient extends RestAssuredClient {

    private final String COURIER = "/courier";
    private final String LOGIN_URL = COURIER + "/login";
    private final String DELETE_COURIER_URL = COURIER + "/{courierId}";


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

    public Response deleteCourier(int courierId){
        return reqSpec
                .pathParam("courierId", courierId)
                .when()
                .delete(DELETE_COURIER_URL);
    }
}