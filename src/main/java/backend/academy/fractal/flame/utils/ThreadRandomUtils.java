package backend.academy.fractal.flame.utils;

import backend.academy.fractal.flame.exception.RandomUtilsException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class providing thread-safe methods for generating random values.
 * This class uses {@link ThreadLocalRandom} for better performance in concurrent environments.
 */
@SuppressFBWarnings("PREDICTABLE_RANDOM")
public final class ThreadRandomUtils {
    private ThreadRandomUtils() {
    }

    /**
     * Retrieves a random element from the given collection.
     *
     * @throws RandomUtilsException if the collection is null or empty.
     */
    public static <T> T getRandomElement(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new RandomUtilsException("Collection must not be null or empty");
        }

        Random random = ThreadLocalRandom.current();

        return collection.stream()
            .skip(random.nextInt(collection.size()))
            .toList()
            .getFirst();
    }

    /**
     * Generates a random float value in the range [0.0, 1.0).
     */
    public static float getRandomFloat() {
        return ThreadLocalRandom.current().nextFloat();
    }

    /**
     * Generates a random double value within the specified range.
     *
     * @param min the lower bound (inclusive).
     * @param max the upper bound (exclusive).
     * @return a random double value between min (inclusive) and max (exclusive).
     * @throws RandomUtilsException if  {@code min} is not less than {@code max}.
     */
    public static double getRandomDouble(double min, double max) {
        if (min >= max) {
            throw new RandomUtilsException("Min must be less than max");
        }

        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
