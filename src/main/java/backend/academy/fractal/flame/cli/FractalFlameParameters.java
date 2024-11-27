package backend.academy.fractal.flame.cli;

import backend.academy.fractal.flame.cli.converters.ImageFormatConverter;
import backend.academy.fractal.flame.cli.converters.RectangleConverter;
import backend.academy.fractal.flame.cli.converters.VariationConverter;
import backend.academy.fractal.flame.cli.validators.PositiveDoubleValidator;
import backend.academy.fractal.flame.cli.validators.PositiveIntegerValidator;
import backend.academy.fractal.flame.cli.validators.RectangleValidator;
import backend.academy.fractal.flame.model.ImageFormat;
import backend.academy.fractal.flame.model.Rectangle;
import backend.academy.fractal.flame.transform.variation.Variation;
import com.beust.jcommander.Parameter;
import java.nio.file.Path;
import java.util.List;
import lombok.Getter;

@Getter
public class FractalFlameParameters {
    private static final int DEFAULT_SYMMETRY = 1;
    private static final double DEFAULT_GAMMA = 1.0;
    private static final int DEFAULT_WIDTH = 2000;
    private static final int DEFAULT_HEIGHT = 2000;
    private static final Rectangle RECTANGLE =
        new Rectangle(-2, -2, 4, 4);
    private static final ImageFormat DEFAULT_IMAGE_FORMAT = ImageFormat.PNG;

    @Parameter(
        names = "--file",
        description = "Path to the output file",
        required = true
    )
    private Path filePath;

    @Parameter(
        names = "--format",
        description = "Image format (PNG, JPEG, BMP).",
        defaultValueDescription = "PNG",
        converter = ImageFormatConverter.class
    )
    private ImageFormat imageFormat = DEFAULT_IMAGE_FORMAT;

    @Parameter(
        names = "--width",
        description = "Width of the image in pixels.",
        defaultValueDescription = "2000",
        validateValueWith = PositiveIntegerValidator.class
    )
    private Integer width = DEFAULT_WIDTH;

    @Parameter(
        names = "--height",
        description = "Height of the image in pixels.",
        defaultValueDescription = "2000",
        validateValueWith = PositiveIntegerValidator.class
    )
    private Integer height = DEFAULT_HEIGHT;

    @Parameter(
        names = "--world",
        description = "Fractal world bounds in format x,y,width,height",
        defaultValueDescription = "-2,-2,4,4",
        converter = RectangleConverter.class,
        validateValueWith = RectangleValidator.class
    )
    private Rectangle world = RECTANGLE;

    @Parameter(
        names = "--samples",
        description = "Number of samples.",
        required = true,
        validateValueWith = PositiveIntegerValidator.class
    )
    private Integer samples;

    @Parameter(
        names = "--iterations",
        description = "Number of iterations per sample.",
        required = true,
        validateValueWith = PositiveIntegerValidator.class
    )
    private Integer iterations;

    @Parameter(
        names = "--symmetry",
        description = "Symmetry level.",
        validateValueWith = PositiveIntegerValidator.class
    )
    private Integer symmetry = DEFAULT_SYMMETRY;

    @Parameter(
        names = "--variation",
        description = "List of variation types (LINEAR, DIAMOND, HEART, POLAR, SPHERICAL, SWIRL)",
        variableArity = true,
        required = true,
        converter = VariationConverter.class
    )
    private List<Variation> variations;

    @Parameter(
        names = "--gamma",
        description = "Gamma value for rendering.",
        validateValueWith = PositiveDoubleValidator.class
    )
    private Double gamma = DEFAULT_GAMMA;

    @Parameter(
        names = "--help",
        description = "Display help information",
        help = true
    )
    private boolean help;
}
