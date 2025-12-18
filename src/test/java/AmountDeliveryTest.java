import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.example.AmountDelivery.costCalculation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AmountDeliveryTest {
    @ParameterizedTest
    @CsvSource({
            "1.9, маленький, true, повышенная, 540",
            "9.9, большой, false, высокая, 420",
            "29.9, нормальный, true, нормальная, 500",
            "30.1, маленький, false, очень высокая, 640"
    })
    @Tag("Smoke")
    @DisplayName("Позитивные сценарии расчета доставки")
    void positiveCostCalculation(double distance, String profile, boolean isFragile, String deliveryWorkload, int expected) {
        assertEquals(expected, costCalculation(distance, profile, isFragile, deliveryWorkload));
    }

    @Test
    @Tag("Negative")
    @DisplayName("Невозможность расчета доставки хрупкого груза на расстояние > 30 км")
    void negativeCostCalculationFragile() {
        assertThrows(IllegalArgumentException.class, () -> costCalculation(30.1, "нормальный", true, "нормальная") );
    }

    @ParameterizedTest
    @CsvSource({
            "1.9, нормальный, false, нормальная",
            "9.9, большой, false, нормальная",
            "29.9, маленький, false, повышенная",
            "30.1, нормальный, false, нормальная",
    })
    @Tag("Negative")
    @DisplayName("Сумма доставки менее 400 рублей")
    void negativeCostCalculationSumDelivery(double distance, String profile, boolean isFragile, String deliveryWorkload) {
        assertThrows(IllegalArgumentException.class, () -> costCalculation(distance, profile, isFragile, deliveryWorkload) );
    }

    @Test
    @Tag("Negative")
    @DisplayName("Не заполнены габариты груза и загруженность службы доставки")
    void negativeCostCalculationNPE() {
        assertThrows(NullPointerException.class, () -> costCalculation(30.1, null, false, null) );
    }
}
