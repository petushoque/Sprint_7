package ru.praktikumservices.qascooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.apache.http.HttpStatus.*;

public class LoginCourierTest {
    private Courier courier;
    private CourierClient courierClient;

    @Before
    public void createCourier(){
        courier = Courier.getRandom();
        courierClient = new CourierClient();
        courierClient.createCourier(courier);
    }

    @Test
    @DisplayName("Check status code and body of /courier/login: Correct data")
    @Description("A test for a positive scenario, a successful server response is 200, the response body contains the courier ID")
    public void loginCourierWithCorrectDataTest(){
        CourierCredentials creds = CourierCredentials.from(courier);
        Response response = courierClient.postLogin(creds);
        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(SC_OK)
                .assertThat()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Check status code and error message of /courier/login: Invalid login")
    @Description("A test for a negative scenario, for a request with an incorrect login, the system responds with a 404 code and an error message")
    public void loginCourierWithInvalidLoginTest(){
        CourierCredentials creds = CourierCredentials.from(courier);
        creds.setLogin("lornemalvo");
        Response response = courierClient.postLogin(creds);
        response.then()
                .log()
                .all()
                .statusCode(SC_NOT_FOUND)
                .assertThat()
                .body("message", notNullValue())
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Check status code and error message of /courier/login: Invalid password")
    @Description("A test for a negative scenario, for a request with an incorrect password, the system responds with a 404 code and an error message")
    public void loginCourierWithInvalidPasswordTest(){
        CourierCredentials creds = CourierCredentials.from(courier);
        creds.setPassword("wrongpassword");
        Response response = courierClient.postLogin(creds);
        response.then()
                .log()
                .all()
                .statusCode(SC_NOT_FOUND)
                .assertThat()
                .body("message", notNullValue())
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Check status code and error message of /courier/login: Empty login")
    @Description("A test for a negative scenario, for a request with an empty login, the system responds with a 404 code and an error message")
    public void loginCourierWithoutLoginTest(){
        CourierCredentials creds = CourierCredentials.from(courier);
        creds.setLogin(null);
        Response response = courierClient.postLogin(creds);
        response.then()
                .log()
                .all()
                .statusCode(SC_BAD_REQUEST)
                .assertThat()
                .body("message", notNullValue())
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check status code and error message of /courier/login: Empty password")
    @Description("A test for a negative scenario, for a request with an empty password, the system responds with a 404 code and an error message")
    public void loginCourierWithoutPasswordTest(){
        CourierCredentials creds = CourierCredentials.from(courier);
        creds.setPassword(null);
        Response response = courierClient.postLogin(creds);
        response.then()
                .log()
                .all()
                .statusCode(SC_BAD_REQUEST)
                .assertThat()
                .body("message", notNullValue())
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}