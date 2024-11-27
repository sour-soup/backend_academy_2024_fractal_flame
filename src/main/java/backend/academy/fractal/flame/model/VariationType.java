package backend.academy.fractal.flame.model;

import backend.academy.fractal.flame.transform.variation.DiamondVariation;
import backend.academy.fractal.flame.transform.variation.HeartVariation;
import backend.academy.fractal.flame.transform.variation.LinearVariation;
import backend.academy.fractal.flame.transform.variation.PolarVariation;
import backend.academy.fractal.flame.transform.variation.SphericalVariation;
import backend.academy.fractal.flame.transform.variation.SwirlVariation;
import backend.academy.fractal.flame.transform.variation.Variation;
import java.util.function.Supplier;

/**
 * Enum representing different types of variations used in fractal flame rendering.
 * Each variation type provides a specific transformation behavior.
 */
public enum VariationType {
    LINEAR(LinearVariation::new),
    DIAMOND(DiamondVariation::new),
    HEART(HeartVariation::new),
    POLAR(PolarVariation::new),
    SPHERICAL(SphericalVariation::new),
    SWIRL(SwirlVariation::new);

    private final Supplier<Variation> constructor;

    VariationType(Supplier<Variation> constructor) {
        this.constructor = constructor;
    }

    /**
     * Creates a new instance of the variation associated with this type.
     *
     * @return a new {@link Variation} instance.
     */
    public Variation create() {
        return constructor.get();
    }
}
