package ru.praktikumservices.qascooter;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
    private Courier courier;
    private CourierClient courierClient;

    @Before
    public void createCourier(){
        courier = Courier.getRandom();
        courierClient = new CourierClient();
        courierClient.create(courier);
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
                .statusCode(200)
                .assertThat()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Check status code and error message of /courier/login: Invalid login")
    @Description("A test for a negative scenario, for a request with an incorrect login, the system responds with a 404 code and an error message")
    public void loginCourierWithInvalidLoginTest(){
        CourierCredentials creds = CourierCredentials.from(courier);
        creds.setLogin(RandomStringUtils.randomAlphanumeric(8));
        Response response = courierClient.postLogin(creds);
        response.then()
                .log()
                .all()
                .statusCode(404)
                .assertThat()
                .body("message", notNullValue())
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    //система вернёт ошибку, если неправильно указать логин или пароль;

    @Test
    @DisplayName("Check 404 status code and error message of /courier/login")
    @Description("Login with invalid password for /courier/login endpoint")
    public void loginCourierWithInvalidPasswordAndCheckError(){
        CourierCredentials creds = CourierCredentials.from(courier);
        creds.setPassword("error");
        Response response = courierClient.postLogin(creds);
        response.then().log().all()
                .statusCode(404)
                .assertThat().body("message", notNullValue())
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
        System.out.println("Courier didn't login. Test passed" + response.asString());
    }

    //если какого-то поля нет, запрос возвращает ошибку;
    @Test
    @DisplayName("Check 400 status code and error message of /courier/login")
    @Description("Login with invalid login for /courier/login endpoint")
    public void loginCourierWithoutLoginAndCheckError(){
        CourierCredentials creds = CourierCredentials.from(courier);
        creds.setLogin(null);
        Response response = courierClient.postLogin(creds);
        response.then().log().all()
                .statusCode(400)
                .assertThat().body("message", notNullValue())
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
        System.out.println("Courier didn't login. Test passed" + response.asString());
    }

    //если какого-то поля нет, запрос возвращает ошибку;
    @Test
    public void loginCourierWithoutPasswordAndCheckError(){
        CourierCredentials creds = CourierCredentials.from(courier);
        creds.setPassword(null);
        Response response = courierClient.postLogin(creds);
        response.then().log().all()
                .statusCode(400)
                .assertThat().body("message", notNullValue())
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
        System.out.println("Courier didn't login. Test passed" + response.asString());
    }
    // Дефект. Неверный код ошибки (ошибка сервера 504)

}