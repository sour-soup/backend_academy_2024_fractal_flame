package backend.academy.fractal.flame.transform.variation;

import backend.academy.fractal.flame.model.Point;

public class SphericalVariation implements Variation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = 1 / (x * x + y * y);
        return new Point(
            r * x,
            r * y
        );
    }
}
