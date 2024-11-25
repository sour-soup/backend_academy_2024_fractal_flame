package backend.academy.fractal.flame.transform.variation;

import backend.academy.fractal.flame.model.Point;

public class LinearVariation implements Variation {
    @Override
    public Point apply(Point point) {
        return point;
    }
}
