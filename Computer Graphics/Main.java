package pkg3dproject;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.*;
import javafx.scene.image.ImageView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root node to hold all elements in the scene
        Group root = new Group();

        // Set up background image and center it in the scene
        Image backgroundImage = new Image(getClass().getResourceAsStream("amusementPark.JPG"));
        ImageView amusementPark = new ImageView(backgroundImage);
        amusementPark.setFitWidth(500);
        amusementPark.setFitHeight(300);
        amusementPark.getTransforms().add(new Translate(-250, -150, 0));

        // The start of building the components of the ferris wheel 
  
        // Centre for ferris wheel
        Sphere ferrisWheelCentre = new Sphere(14); // Sphere (radius)
        ferrisWheelCentre.setMaterial(new PhongMaterial(Color.web("#caa5b8"))); // Set 3D shape material

        // Set surfaces texture materials for ferris wheel pod
        PhongMaterial ferrisWheelPod = new PhongMaterial();
        ferrisWheelPod.setDiffuseMap(new Image(getClass().getResourceAsStream("ferrisWheelPod.png")));

        // Ferris wheel pods and wireropes
        Group ferrisWheelPodsAndWireropes = new Group(); // Group to hold all pods and wires
        for (int i = 0; i < 8; i++) {           
            // Loop to create 8 pods and their wires

            // Create a wire rope (thin box)
            Box wireropes = new Box(95, 2, 2); // Box (width, height, depth)
            wireropes.setMaterial(new PhongMaterial(Color.web("#caa5b8"))); // Set 3D shape material
            wireropes.setRotate(i * -45); // Rotate the wires to align with the pods 

            // Create a pod as a small box
            Box pod = new Box(30, 30, 0); // Box (width, height, depth)
            pod.setMaterial(ferrisWheelPod); // Set 3D shape material
            double angle = i * 45; // 360° / 8 = 45° // Calculate the angle for each pod and wire
            // Calculate the position of the pods
            double x = 60 * Math.cos(Math.toRadians(angle));
            double y = 60 * Math.sin(Math.toRadians(angle));
            pod.getTransforms().add(new Translate(x, y, 0));
            // Add the pods and wires to the group
            ferrisWheelPodsAndWireropes.getChildren().addAll(wireropes, pod);
        }

        // Create a support columns for the Ferris Wheel
        Cylinder column2 = new Cylinder(4, 100); // Cylinder (radius, height)
        column2.setMaterial(new PhongMaterial(Color.web("#caa5b8"))); // Set 3D shape material
        column2.setTranslateY(60);
        column2.setTranslateX(-20);
        column2.setRotate(20);

        Cylinder column3 = new Cylinder(4, 100);// Cylinder (radius, height)
        column3.setMaterial(new PhongMaterial(Color.web("#caa5b8")));// Set 3D shape material
        column3.setTranslateY(60);
        column3.setTranslateX(20);
        column3.setRotate(-20);
        // Add all Ferris Wheel components to the root
        root.getChildren().addAll(amusementPark, column2, column3, ferrisWheelCentre, ferrisWheelPodsAndWireropes);
        // The end of building the components of the ferris wheel.

        // The start of building the components of the cable car 
        // Set surfaces texture materials for cable car pods
        PhongMaterial cableCarPod = new PhongMaterial();
        cableCarPod.setDiffuseMap(new Image(getClass().getResourceAsStream("cableCarPod.png")));

        // Create the car pods 
        Group cableCarPods = new Group();
        for (int i = 0; i < 20; i++) {
            Box pod = new Box(30, 30, 30); // Box (width, height, depth)
            pod.setMaterial(cableCarPod);
            // Position the cable car pods
            pod.getTransforms().add(new Translate(130 - (i * 50), -110, 0));
            cableCarPods.getChildren().add(pod);
        }
        // Create the wire rope for the cable cars.
        Box wirerope = new Box(2000, 2, 2); // Box (width, height, depth)
        wirerope.setMaterial(new PhongMaterial(Color.BLACK)); // Set 3D shape material
        wirerope.getTransforms().add(new Translate(0, -126, 0));
        
        // Add the cable cars and wire to the root
        root.getChildren().addAll(wirerope, cableCarPods);
        // The end of building the components of the cable car

        // Set surfaces texture materials for balloons and characters
        PhongMaterial largeBalloonMaterial = new PhongMaterial();
        largeBalloonMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("largeBalloon.jpg")));
        PhongMaterial smallBalloonMaterial = new PhongMaterial();
        smallBalloonMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("smallBalloon.jpg")));
        PhongMaterial boy = new PhongMaterial();
        boy.setDiffuseMap(new Image(getClass().getResourceAsStream("boy.png")));
        PhongMaterial girl = new PhongMaterial();
        girl.setDiffuseMap(new Image(getClass().getResourceAsStream("girl.png")));
        PhongMaterial cat = new PhongMaterial();
        cat.setDiffuseMap(new Image(getClass().getResourceAsStream("cat.png")));
        
        // The start of building the components of the balloons

        // Large balloon
        Sphere largeBalloon = new Sphere(25); // Sphere (radius)
        largeBalloon.setMaterial(largeBalloonMaterial); // Large balloon texture
        largeBalloon.setTranslateX(-95);
        largeBalloon.setTranslateY(-40);

        // Small balloon
        Sphere smallBalloon = new Sphere(15); // Sphere (radius)
        smallBalloon.setMaterial(smallBalloonMaterial); // Small balloon texture
        smallBalloon.setTranslateX(-120);
        smallBalloon.setTranslateY(0);

        // Girl
        Box girlTemplate = new Box(50, 70, 0); // Box (width, height, depth)
        girlTemplate.setMaterial(girl); // Set 3D shape material
        girlTemplate.setTranslateY(90);
        girlTemplate.setTranslateX(-90);

        // Cat
        Box catTemplate = new Box(20, 20, 0); // Box (width, height, depth)
        catTemplate.setMaterial(cat); // Set 3D shape material
        catTemplate.setTranslateY(120);
        catTemplate.setTranslateX(-70);
        
        // Boy
        Box boyTemplate = new Box(40, 40, 0);// Box (width, height, depth)
        boyTemplate.setMaterial(boy); // Set 3D shape material
        boyTemplate.setTranslateY(90);

        // Ballons ropes
        Cylinder rope1 = new Cylinder(1, 80); // Cylinder (radius, height)
        rope1.setMaterial(new PhongMaterial(Color.PINK)); // Set 3D shape material
        rope1.setTranslateX(-115);
        rope1.setTranslateY(40);
        rope1.setRotate(-5);

        Cylinder rope2 = new Cylinder(1, 100); // Cylinder (radius, height)
        rope2.setMaterial(new PhongMaterial(Color.PINK)); // Set 3D shape material
        rope2.setTranslateX(-105);
        rope2.setTranslateY(30);
        rope2.setRotate(10);

        // Create a group to contain all parts of ballons
        Group balloons = new Group();
        balloons.getChildren().addAll(largeBalloon, smallBalloon, rope1, rope2);
        
        // Add the ballons to the root
        root.getChildren().addAll(balloons, girlTemplate, catTemplate, boyTemplate);
        // The end of building the components of the balloons

        // Lighting
        root.getChildren().add(new AmbientLight(Color.WHITE));
//        PointLight pointLight = new PointLight(Color.BLACK);
//        root.getChildren().add(pointLight);

        // Prepare camera
        Camera camera = new PerspectiveCamera(true); // true: fixes the camera's position at (0, 0, 0)
        camera.setNearClip(1);
        camera.setFarClip(1000);
        camera.setTranslateZ(-500); // Move camera back to get a good view of the scene

        // Scene setup
        Scene scene = new Scene(root, 800, 600, true);
        scene.setCamera(camera);
        
        // Create a rotation animation for the Ferris Wheel pods and wire ropes.
        RotateTransition rotateWheel = new RotateTransition(Duration.seconds(20), ferrisWheelPodsAndWireropes);
        // Create a horizontal movement animation for the cable cars.
        TranslateTransition moveRightCableCar = new TranslateTransition(Duration.seconds(10), cableCarPods);
        // Create a vertical movement animation for the balloons to fly upwards.
        TranslateTransition flyUp = new TranslateTransition(Duration.seconds(20), balloons);
        // Create a scaling animation to shrink the balloons.
        ScaleTransition shrink = new ScaleTransition(Duration.seconds(10), balloons);
        // Combine the upward movement and shrinking animations into a parallel animation.
        ParallelTransition flyAndShrink = new ParallelTransition(flyUp, shrink);

        // Keyboard controls
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R) {
                // Rotation for Ferris wheel
                rotateWheel.setAxis(Rotate.Z_AXIS); // Set the rotation axis for the Ferris Wheel to the Z-axis
                rotateWheel.setByAngle(360); // Rotate the Ferris Wheel 360 degrees
                rotateWheel.setCycleCount(RotateTransition.INDEFINITE); // Set the rotation to repeat indefinitely
                rotateWheel.play(); // Start the rotation animation
            } else if (event.getCode() == KeyCode.S) {
                rotateWheel.stop(); // Stop the Ferris Wheel rotation
            } else if (event.getCode() == KeyCode.G) {
                moveRightCableCar.setByX(250); // Move the cable cars 250 units along the X-axis
                moveRightCableCar.setCycleCount(TranslateTransition.INDEFINITE); // Set the cable cars to move continuously
                moveRightCableCar.play(); // Start the cable car movement animation
            } else if (event.getCode() == KeyCode.SPACE) {
                flyUp.setByY(-200); // Move the balloons upward by 200 units along the Y-axis
                flyUp.setCycleCount(1); // Play the upward movement animation once
                // Shrink the balloons to 50% of their original size in all dimensions
                shrink.setToX(0.5);
                shrink.setToY(0.5);
                shrink.setToZ(0.5);
                shrink.setCycleCount(1); // Play the shrinking animation once
                flyAndShrink.play(); // Start both the upward movement and shrinking animations together
            } else if (event.getCode() == KeyCode.LEFT) {
                catTemplate.setTranslateX(catTemplate.getTranslateX() - 5); // Move the cat 5 units to the left along the X-axis
            } else if (event.getCode() == KeyCode.RIGHT) {
                catTemplate.setTranslateX(catTemplate.getTranslateX() + 5); // Move the cat 5 units to the right along the X-axis
            } else if (event.getCode() == KeyCode.Z) {
                boyTemplate.setTranslateZ(boyTemplate.getTranslateZ() - 20); // Move the boy 20 units forward (negative Z direction)
            }else if (event.getCode() == KeyCode.U) {
                cableCarPods.setRotationAxis(Rotate.X_AXIS); // Set rotation axis to X-axis
                cableCarPods.setRotate(cableCarPods.getRotate() + 10); // Rotate +10 degrees along X-axis
                
                largeBalloon.setRotationAxis(Rotate.Y_AXIS); // Set rotation axis to Y-axis
                largeBalloon.setRotate(largeBalloon.getRotate() + 10); // Rotate +10 degrees along Y-axis
                
                smallBalloon.setRotationAxis(Rotate.Y_AXIS); // Set rotation axis to Y-axis
                smallBalloon.setRotate(smallBalloon.getRotate() + 10); // Rotate +10 degrees along Y-axis
            }  
        });

        // Show stage
        primaryStage.setTitle("Amusement Park");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
