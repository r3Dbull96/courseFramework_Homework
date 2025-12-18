package org.example;

public class AmountDelivery {
    public static int costCalculation(double distance, String profile, boolean isFragile, String deliveryWorkload) {
        int sumDelivery = 0;

        if (distance > 30 && isFragile) {
            throw new IllegalArgumentException("Fragile goods cannot be transported over a distance of more than 30 km");
        }

        if (distance > 30) {
            sumDelivery += 300;
        } else if (distance <= 30 && distance > 10) {
            sumDelivery += 200;
        } else if (distance < 10 && distance > 2) {
            sumDelivery += 100;
        } else if (distance < 2 && distance > 0) {
            sumDelivery += 50;
        }

        if (profile.equals("большой")) {
            sumDelivery += 200;
        } else if (profile.equals("маленький")) {
            sumDelivery += 100;
        }

        if (isFragile) {
            sumDelivery += 300;
        }

        switch (deliveryWorkload) {
            case "очень высокая" -> sumDelivery = (int) Math.round(sumDelivery * 1.6);
            case "высокая" -> sumDelivery = (int) Math.round(sumDelivery * 1.4);
            case "повышенная" -> sumDelivery = (int) Math.round(sumDelivery * 1.2);
        }

        if (sumDelivery < 400) {
            throw new IllegalArgumentException("Minimum delivery amount 400 rubles");
        }
        System.out.println("Ваша сумма доставки " + sumDelivery + " рублей");
        return sumDelivery;
    }
}

