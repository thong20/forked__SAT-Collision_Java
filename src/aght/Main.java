package aght;

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

    final static int width = 1280;
    final static int height = 768;

    Polygon p = Polygon.fromVertices(new Vector2d[] {
        new Vector2d(100, 100),
        new Vector2d(150, 125),
        new Vector2d(200, 100),
        new Vector2d(200, 200),
        new Vector2d(100, 200)
    });

    Polygon p2 = Polygon.fromVertices(new Vector2d[] {
        new Vector2d(100, 100),
        new Vector2d(150, 125),
        new Vector2d(200, 100),
        new Vector2d(200, 200),
        new Vector2d(100, 200)
    });

    Group root;

    Scene scene;

    public void start(Stage stage) throws Exception {
        p.setFill(Color.LIGHTBLUE);

        root = new Group(p, p2);

        p2.setX(width / 2);
        p2.setY(height / 2);

        scene = new Scene(root, width, height, Color.rgb(51, 51, 51));

        scene.setOnMouseMoved(this::handleMouseMoved);
        scene.setOnScroll(this::handleOnScroll);

        stage.setScene(scene);
        stage.setTitle("Collision Detection Test");
        stage.show();

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                scene.setFill(Color.rgb(51, 51, 51));

                if (SAT.collide(p2, p)) {
                    scene.setFill(Color.LIGHTCORAL);
                }
            }

        }.start();
    }

    public void handleOnScroll(ScrollEvent e) {}

    public void handleMouseMoved(MouseEvent e) {
        p.setX(e.getX());
        p.setY(e.getY());
    }

    public static void main(String[] args) {
        Vector2d v = new Vector2d(22, 63);
        Vector2d v2 = new Vector2d(55, 110.63);
        System.out.println(v.dot(v2));
        launch(args);
    }

}
