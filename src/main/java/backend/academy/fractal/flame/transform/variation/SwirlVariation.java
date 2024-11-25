package backend.academy.fractal.flame.transform.variation;

import backend.academy.fractal.flame.model.Point;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SwirlVariation implements Variation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = x * x + y * y;
        return new Point(
            x * sin(r) - y * cos(r),
            x * cos(r) + y * sin(r)
        );
    }
}
