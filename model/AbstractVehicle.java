package model;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class AbstractVehicle implements IMovable, IDrawable{

    /**
     * Number of doors on the vehicle.
     */
    int nrDoors;
    /**
     * Engine power of the vehicle.
     */
    double enginePower;
    /**
     * The current speed of the vehicle.
     */
    double currentSpeed;
    /**
     * Color of the vehicle.
     */
    Color color;
    /**
     * The vehicle model name
     */
    String modelName;
    /**
     * position of vehicle.
     */
    Point position;

    /**
     * Current direction of the vehicle.
     */
    double direction;
    /**
     * Weight of vehicle (KG)
     */
    int weight;

    /**
     * Is the car already loaded.
     */
    boolean isLoaded;

    /**
     * IS the car started
     */
    boolean isStarted;

    boolean turningLeft = false;

    boolean turningRight = false;

    private BufferedImage image;

    private BufferedImage rotatedImage;

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    private double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    /**
     * Starts the engine
     */
    public void startEngine() {
        currentSpeed = 0.1;
        isStarted = true;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isTurningLeft() {
        return turningLeft;
    }

    public void setTurningLeft(boolean turningLeft) {
        this.turningLeft = turningLeft;
    }

    public boolean isTurningRight() {
        return turningRight;
    }

    public void setTurningRight(boolean turningRight) {
        this.turningRight = turningRight;
    }

    /**
     * Stops the engine
     */
    public void stopEngine() {
        currentSpeed = 0;
        isStarted = false;
    }

    /**
     * @return Returns the current speedfactor
     */
    abstract double speedFactor();


    /**
     * @param amount Factor for incrementing speed.
     */
    void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    /**
     * @param amount Factor for decrementing speed.
     */
    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }


    /**
     * @param amount Gas factor, must be [0,1]
     */
    public boolean gas(double amount) {
        if (amount >= 0 && amount <= 1 && !isLoaded && isStarted) {
            incrementSpeed(amount);
            return true;
        }
        return false;
    }

    /**
     * @param amount Brake factor, must be [0,1]
     */
    public void brake(double amount) {
        if (amount >= 0 && amount <= 1)
            decrementSpeed(amount);
    }

    @Override
    public BufferedImage getImage(){
        return image;
    }

    public void setImage(BufferedImage image){
        this.image = image;
    }

    @Override
    public BufferedImage getRotatedImage() {
        return rotatedImage;
    }

    public void setRotatedImage(BufferedImage rotatedImage) {
        this.rotatedImage = rotatedImage;
    }

    @Override
    public void move() {

        position.x += currentSpeed * Math.cos(direction);
        position.y -= currentSpeed * Math.sin(direction);

    }


    @Override
    public void turnLeft() {
        direction -= 0.01;
    }


    @Override
    public void turnRight() {
        direction += 0.01;
    }
}
