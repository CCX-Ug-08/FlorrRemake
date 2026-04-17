package noob.ccxug.florr.client.render;

@FunctionalInterface
public interface TextPositionFunction {
    double[] calculate(double canvasWidth, double canvasHeight, double textWidth, double textHeight);
}
