package noob.ccxug.florr.client.render.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import noob.ccxug.florr.client.render.TextPositionFunction;
import noob.ccxug.florr.other.Identifier;

public class OutlinedText extends RenderableText{
    private final Color strokeColor;
    private final double strokeWidth;
    public OutlinedText(Identifier id, String text, double fontSize, Color fillColor, Color strokeColor, double strokeWidth, TextPositionFunction positionFunction) {
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        super(id, text, fontSize, fillColor, positionFunction);
    }
    @Override
    public void render(Object context, double deltaTime) {
        GraphicsContext gc = (GraphicsContext) context;
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
        measureTool.setText(text);
        measureTool.setFont(font);
        double textWidth = measureTool.getLayoutBounds().getWidth();
        double textHeight = measureTool.getLayoutBounds().getHeight();
        double[] pos = positionFunction.calculate(canvasWidth, canvasHeight, textWidth + strokeWidth / 2, textHeight + strokeWidth / 2);
        double x = pos[0];
        double y = pos[1];
        double oldLineWidth = gc.getLineWidth();
        Font oldFont = gc.getFont();
        gc.setFont(font);
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);
        gc.strokeText(text, x, y);
        gc.setFill(color);
        gc.fillText(text, x, y);
        gc.setLineWidth(oldLineWidth);
        gc.setFont(oldFont);
    }
}
