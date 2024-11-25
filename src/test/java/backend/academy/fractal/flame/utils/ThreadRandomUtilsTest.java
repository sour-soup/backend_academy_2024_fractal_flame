package backend.academy.fractal.flame.utils;

import backend.academy.fractal.flame.exception.RandomUtilsException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static backend.academy.fractal.flame.utils.ThreadRandomUtils.getRandomElement;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ThreadRandomUtilsTest {

    @Test
    @DisplayName("GetRandomElement should return an element when collection has elements")
    void getRandomElement_ShouldReturnElement_WhenCollectionHasElements() {
        // Arrange
        List<String> elements = List.of("apple", "banana", "cherry");

        // Act
        String randomElement = getRandomElement(elements);

        // Assert
        assertThat(elements).contains(randomElement);
    }

    @Test
    @DisplayName("getRandomElement should throw RandomUtilsException when collection is empty")
    void getRandomElement_ShouldThrowException_WhenCollectionIsEmpty() {
        // Arrange
        List<String> emptyList = List.of();

        // Assert & Act
        assertThatThrownBy(() -> getRandomElement(emptyList))
            .isInstanceOf(RandomUtilsException.class);
    }

    @Test
    @DisplayName("getRandomFloat should return value between 0.0 and 1.0")
    void getRandomFloat_ShouldReturnCorrectValue() {
        // Act
        float result = ThreadRandomUtils.getRandomFloat();

        // Assert
        assertThat(result).isBetween(0.0f, 1.0f);
    }

    @Test
    @DisplayName("getRandomDouble should return value within specified range")
    void getRandomDouble_ShouldReturnCorrectValue() {
        // Arrange
        double min = 1;
        double max = 5;

        // Act
        double result = ThreadRandomUtils.getRandomDouble(min, max);

        // Assert
        assertThat(result).isBetween(min, max);
    }

    @ParameterizedTest
    @CsvSource("0, 0")
    @CsvSource("2, 1")
    @DisplayName("getRandomDouble should throw exception when min is not less max")
    void getRandomDouble_ShouldThrowException_WhenMinIsLessThanMax(double min, double max) {
        // Act & Assert
        assertThatThrownBy(() -> ThreadRandomUtils.getRandomDouble(min, max))
            .isInstanceOf(RandomUtilsException.class);
    }
}
