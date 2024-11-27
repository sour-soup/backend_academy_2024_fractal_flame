package backend.academy.fractal.flame;

import backend.academy.fractal.flame.cli.FractalFlameParameters;
import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.processor.ImageProcessor;
import backend.academy.fractal.flame.processor.LogGammaCorrectionProcessor;
import backend.academy.fractal.flame.renderer.ParallelRenderer;
import backend.academy.fractal.flame.renderer.Renderer;
import backend.academy.fractal.flame.utils.ImageUtils;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.UnixStyleUsageFormatter;
import lombok.experimental.UtilityClass;

/**
 * Main class for generating fractal flame images based on user-defined parameters.
 * <p>
 * This class uses JCommander for parsing command-line arguments and delegates the rendering
 * and processing tasks to the respective classes. The generated fractal image is saved
 * to the specified file in the chosen format.
 * </p>
 */
@UtilityClass
public class Main {

    public static void main(String[] args) {
        FractalFlameParameters parameters = new FractalFlameParameters();
        JCommander jCommander = JCommander.newBuilder()
            .addObject(parameters)
            .build();

        jCommander.parse(args);
        jCommander.setUsageFormatter(new UnixStyleUsageFormatter(jCommander));

        if (parameters.help()) {
            jCommander.usage();
            return;
        }

        FractalImage image = FractalImage.createCanvas(parameters.width(), parameters.height());
        Renderer renderer = new ParallelRenderer();
        renderer.render(
            image,
            parameters.world(),
            parameters.variations(),
            parameters.samples(),
            parameters.iterations(),
            parameters.symmetry()
        );

        ImageProcessor processor = new LogGammaCorrectionProcessor(parameters.gamma());
        processor.process(image);

        ImageUtils.saveFractalImage(
            image,
            parameters.filePath(),
            parameters.imageFormat()
        );
    }
}
