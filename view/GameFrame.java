package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;


public class GameFrame extends JFrame implements KeyListener{
    private static final int X = 800;
    private static final int Y = 800;

    Controller controller;
    public DrawPanel drawPanel;

    long lastKeyPress1;
    long lastKeyPress2;

    public GameFrame(Controller cc){
        this.controller = cc;
        initComponents();
    }

    private void initComponents() {
        this.setTitle("Race");
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        drawPanel = new DrawPanel(this, X, Y);
        this.add(drawPanel);

        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                controller.gasVehicle(controller.getPlayers().get(0));
                break;
            case KeyEvent.VK_DOWN:
                controller.brakeVehicle(controller.getPlayers().get(0));
                break;
            case KeyEvent.VK_LEFT:
                controller.getPlayers().get(0).setTurningRight(false);
                controller.getPlayers().get(0).setTurningLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                controller.getPlayers().get(0).setTurningLeft(false);
                controller.getPlayers().get(0).setTurningRight(true);
                break;
        }

        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
                controller.gasVehicle(controller.getPlayers().get(1));
                break;
            case KeyEvent.VK_S:
                controller.brakeVehicle(controller.getPlayers().get(1));
                break;
            case KeyEvent.VK_A:
                controller.getPlayers().get(1).setTurningRight(false);
                controller.getPlayers().get(1).setTurningLeft(true);
                break;
            case KeyEvent.VK_D:
                controller.getPlayers().get(1).setTurningLeft(false);
                controller.getPlayers().get(1).setTurningRight(true);
                break;
            case KeyEvent.VK_SPACE:
                controller.getPlayers().get(0).startEngine();
                controller.getPlayers().get(1).startEngine();
        }
    }


    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_LEFT:
                controller.getPlayers().get(0).setTurningLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                controller.getPlayers().get(0).setTurningRight(false);
                break;
            case KeyEvent.VK_A:
                controller.getPlayers().get(1).setTurningLeft(false);
                break;
            case KeyEvent.VK_D:
                controller.getPlayers().get(1).setTurningRight(false);
                break;
        }
    }
}