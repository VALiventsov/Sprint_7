package ru.praktikum_services.qa_scooter;

public class OrderGenerator {
    public static Order getDefault(String[] color) {
        return new Order("aron",
                "eger",
                "shiganshina",
                4,
                "+7 800 355 35 35",
                5,
                "2022-01-09",
                "i kill all titans",
                color);

    }
}
