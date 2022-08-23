package ru.praktikum_services.qa_scooter;

public class CourierGenerator {
    //верный курьер
    public static Courier getDefault() {
        return new Courier("titanus", "1234", "aron");
    }

    //курьер без пароля
    public static Courier getWrong() {
        return new Courier ("fear", "", "ryuk");
    }

    //зарегестрированный курьер
    public static Courier getRight() {
        return new Courier ("titan", "1234", "armin");
    }

    //незарегестрированный курьер
    public static Courier getNotRegCourier() {
        return new Courier("titan", "apple", "armin");
    }
}

