package aght;

import aght.collision.SAT;
import aght.math.Vector2d;
import aght.shape.Polygon;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Collision Test.
 *
 * @author Andy Tang
 * @version 2018
 */
public class Main extends Application {

    private final static int WIDTH = 768;
    private final static int HEIGHT = 480;

    private Group root;

    private Scene scene;

    private Color clearColor = Color.rgb(51, 51, 51);
    private Color hitClearColor = Color.LIGHTCORAL;

    private Polygon star = Polygon.fromVertices(new Vector2d[]{
            new Vector2d(0, -50),
            new Vector2d(14, -20),
            new Vector2d(47, -15),
            new Vector2d(23, 7),
            new Vector2d(29, 40),
            new Vector2d(0, 25),
            new Vector2d(-29, 40),
            new Vector2d(-23, 7),
            new Vector2d(-47, -15),
            new Vector2d(-14, -20)
    });

    private Polygon letterH = Polygon.fromVertices(new Vector2d[]{
            new Vector2d(0, 0),
            new Vector2d(10, 0),
            new Vector2d(10, 20),
            new Vector2d(20, 20),
            new Vector2d(20, 0),
            new Vector2d(30, 0),
            new Vector2d(30, 50),
            new Vector2d(20, 50),
            new Vector2d(20, 30),
            new Vector2d(10, 30),
            new Vector2d(10, 50),
            new Vector2d(0, 50)
    });

    public void start(Stage stage) {
        star.setFill(Color.LIGHTBLUE);
        letterH.setFill(Color.DEEPSKYBLUE);

        root = new Group(star, letterH);

        letterH.setX(WIDTH / 2);
        letterH.setY(HEIGHT / 2);
        letterH.setScaleX(2.5);
        letterH.setScaleY(2);

        scene = new Scene(root, WIDTH, HEIGHT, Color.rgb(51, 51, 51));

        scene.setOnMouseMoved(this::handleMouseMoved);
        scene.setOnScroll(this::handleOnScroll);

        stage.setScene(scene);
        stage.setTitle("Collision Detection Test");
        stage.show();

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                scene.setFill(clearColor);

                if (SAT.collide(letterH, star)) {
                    scene.setFill(hitClearColor);
                }
            }

        }.start();
    }

    public void handleOnScroll(ScrollEvent e) {
        star.rotate(star.getRotate() + e.getDeltaY() * 0.1);
    }

    public void handleMouseMoved(MouseEvent e) {
        star.setX(e.getX());
        star.setY(e.getY());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
