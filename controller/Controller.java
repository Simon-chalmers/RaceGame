package controller;

import com.idrsolutions.image.process.ImageProcessingOperations;
import javafx.scene.input.RotateEvent;
import javafx.scene.transform.Rotate;
import model.AbstractVehicle;
import model.IDrawable;
import model.Saab95;
import model.Volvo240;
import view.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private final int FPS = 144;
    private Timer timer = new Timer((1000/FPS), new TimerListener());
    private GameFrame gameFrame;

    private Volvo240 player1;
    private Saab95 player2;

    ArrayList<AbstractVehicle> players = new ArrayList<>();
    private ArrayList<IDrawable> drawables = new ArrayList<>();

    void start(){
        setupPlayers();
        gameFrame = new GameFrame(this);
        timer.start();
    }

    private class TimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            for(AbstractVehicle vehicle : players){
                if(vehicle.isTurningLeft()){
                    turnLeft(vehicle);
                }else if(vehicle.isTurningRight()){
                    turnRight(vehicle);
                }
                moveVehicle(vehicle);
            }
            gameFrame.repaint();
        }
    }

    private void setupPlayers(){
        player1 = new Volvo240();
        player1.setPosition(new Point(600, 400));
        player1.setDirection(0);
        try {
            player1.setImage(imageToBufferedImage(ImageIO.read(getClass().getClassLoader().getResource("res/images/bluecar.png")).getScaledInstance(100, 60, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        player2 = new Saab95();
        player2.setPosition(new Point(200, 400));
        player2.setDirection(0);
        try {
            player2.setImage(imageToBufferedImage(ImageIO.read(getClass().getClassLoader().getResource("res/images/redcar.png")).getScaledInstance(100, 60, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawables.add(player1);
        drawables.add(player2);

        players.add(player1);
        players.add(player2);
    }

    public ArrayList<IDrawable> getDrawables(){
        return drawables;
    }

    public ArrayList<AbstractVehicle> getPlayers(){
        return players;
    }

    public void gasVehicle(AbstractVehicle vehicle){
        vehicle.gas(1);
    }

    public void brakeVehicle(AbstractVehicle vehicle){
        vehicle.brake(1);
    }

    private void turnLeft(AbstractVehicle vehicle){
        vehicle.turnLeft();
    }

    private void turnRight(AbstractVehicle vehicle){
        vehicle.turnRight();
    }

    private void moveVehicle(AbstractVehicle vehicle){
        vehicle.move();
        vehicle.setRotatedImage(rotateImageByDegrees(vehicle.getImage(), vehicle.getDirection()));
    }

    private BufferedImage rotateImageByDegrees(BufferedImage bimg, double angle) {
        ColorModel cm = bimg.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bimg.copyData(null);
        ImageProcessingOperations operations = new ImageProcessingOperations();
        operations.scale(1f);
        operations.rotate(angle);

        return operations.apply(new BufferedImage(cm, raster, isAlphaPremultiplied, null));
    }

    private BufferedImage imageToBufferedImage(Image img){
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }
}
