package ru.praktikumservices.qascooter.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List color;

    public static Order getDefault() {
        return new Order(
                "Джон",
                "Доу",
                "Москва, улица Воздвиженка, 3/5 ст2",
                "Библиотека им. Ленина",
                "88005553535",
                3,
                "2022-09-01",
                "Привет, мир",
                List.of("BLACK")
        );
    }
}