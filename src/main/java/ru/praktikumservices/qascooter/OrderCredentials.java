package ru.praktikumservices.qascooter;

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