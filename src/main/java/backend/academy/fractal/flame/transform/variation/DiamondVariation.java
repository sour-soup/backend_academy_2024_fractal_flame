package backend.academy.fractal.flame.transform.variation;

import backend.academy.fractal.flame.model.Point;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class DiamondVariation implements Variation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(x * x + y * y);
        double theta = atan2(y, x);
        double newX = sin(theta) * cos(r);
        double newY = cos(theta) * sin(r);
        return new Point(newX, newY);
    }
}
