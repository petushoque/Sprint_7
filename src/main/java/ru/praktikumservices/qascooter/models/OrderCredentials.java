package ru.praktikumservices.qascooter.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCredentials {

    private Integer id;
    private Integer courierId;
}