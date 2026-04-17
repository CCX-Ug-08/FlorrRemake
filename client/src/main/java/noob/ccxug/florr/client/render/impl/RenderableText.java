package noob.ccxug.florr.client.render.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import noob.ccxug.florr.client.render.Renderable;
import noob.ccxug.florr.client.render.TextPositionFunction;
import noob.ccxug.florr.other.Identifier;

import java.io.InputStream;

public class RenderableText implements Renderable {
    private final Identifier id;
    public final String text;
    private final double fontSize;
    public final Color color;
    private final String fontSourcePath = "/assets/fonts/Ubuntu-B.ttf";
    public final TextPositionFunction positionFunction;
    public final Text measureTool = new Text();
    public Font font;
    public RenderableText(Identifier id, String text, double fontSize, Color color, TextPositionFunction textPositionFunction)
    {
        this.id = id;
        this.text = text;
        this.fontSize = fontSize;
        this.color = color;
        this.positionFunction = textPositionFunction;
        loadFont();
    }
    private void loadFont()
    {
        try (InputStream is = getClass().getResourceAsStream(fontSourcePath)) {
            if (is == null)
            {
                System.err.println("Font not found: " + fontSourcePath + ", using system default.");
                font = Font.font("System", this.fontSize);
            }
            else
                font = Font.loadFont(is, this.fontSize);
        }
        catch (Exception e)
        {
            System.err.println("Failed to load font: " + e.getMessage());
            font = Font.font("System", this.fontSize);
        }
    }
    @Override
    public void render(Object context, double deltaTime)
    {
        GraphicsContext gc = (GraphicsContext) context;
        gc.setFont(font);
        gc.setFill(color);
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
        measureTool.setText(text);
        measureTool.setFont(font);
        double textWidth = measureTool.getLayoutBounds().getWidth();
        double textHeight = measureTool.getLayoutBounds().getHeight();
        double[] pos = positionFunction.calculate(canvasWidth, canvasHeight, textWidth, textHeight);
        gc.fillText(text, pos[0], pos[1]);
    }
    @Override
    public Identifier getId()
    {
        return id;
    }
    public static TextPositionFunction centered()
    {
        return (cw, ch, tw, th) -> new double[]{
                (cw - tw) / 2,
                (ch + th / 2) / 2
        };
    }
    public static TextPositionFunction leftUp(double marginX, double marginY)
    {
        return (cw, ch, tw, th) -> new double[]{
                marginX,
                marginY + th / 2
        };
    }
    public static TextPositionFunction bottomRight(double marginX, double marginY) {
        return (cw, ch, tw, th) -> new double[]{
                cw - tw - marginX,
                ch - marginY
        };
    }
    public static TextPositionFunction custom(double fixedX, double fixedY) {
        return (cw, ch, tw, th) -> new double[]{
                fixedX,
                fixedY
        };
    }
}
