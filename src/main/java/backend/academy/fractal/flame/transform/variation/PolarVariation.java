package backend.academy.fractal.flame.transform.variation;

import backend.academy.fractal.flame.model.Point;
import static java.lang.Math.atan;
import static java.lang.Math.sqrt;

public class PolarVariation implements Variation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(x * x + y * y);

        return new Point(
            atan(x / y) / Math.PI,
            r - 1
        );
    }
}
