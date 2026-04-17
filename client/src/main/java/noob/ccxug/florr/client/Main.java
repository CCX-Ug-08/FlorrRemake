package noob.ccxug.florr.client;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import noob.ccxug.florr.client.render.Renderable;
import noob.ccxug.florr.client.render.impl.OutlinedText;
import noob.ccxug.florr.client.render.impl.RenderableText;
import noob.ccxug.florr.other.Identifier;

import java.util.Collection;
import java.util.List;

public class Main extends Application {
    private static Main instance;
    private Canvas canvas;
    private GraphicsContext gc;
    private long lastFrameTime;
    private double viewportWidth = 1280;
    private double viewportHeight = 720;
    //all the texts
    RenderableText TestText = new RenderableText(
            new Identifier("text", "test"),
            "Test Text",
            48.0,
            Color.BLACK,
            RenderableText.centered()
    );
    OutlinedText Test1 = new OutlinedText(
            new Identifier("text", "test1"),
            "Test 1",
            48.0,
            Color.WHITE,
            Color.BLACK,
            3.0,
            RenderableText.leftUp(10, 20)
    );
    //
    @Override
    public void start(Stage stage)
    {
        instance = this;
        stage.setTitle("Florr Client");
        canvas = new Canvas(1280, 720);
        gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setWidth(newVal.doubleValue());
            viewportWidth = newVal.doubleValue();
        });
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setHeight(newVal.doubleValue());
            viewportHeight = newVal.doubleValue();
        });
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        lastFrameTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long now)
            {
                double deltaTime = (now - lastFrameTime) / 1_000_000_000.0;
                lastFrameTime = now;
                processInput();
                processNetwork();
                render(deltaTime);
            }
        }.start();
    }
    private void processInput()
    {

    }
    private void processNetwork()
    {

    }
    private void render(double deltaTime)
    {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Collection<Renderable> renderables = getVisibleRenderables();
        for (Renderable r : renderables)
            r.render(gc, deltaTime);
    }
    private Collection<Renderable> getVisibleRenderables()
    {
        return List.of(TestText, Test1);
    }
    public double getViewportWidth()
    {
        return viewportWidth;
    }
    public double getViewportHeight()
    {
        return viewportHeight;
    }
    static void main(String[] args) {
        launch(args);
    }
}
