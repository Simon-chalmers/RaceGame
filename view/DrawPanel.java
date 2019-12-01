package view;

import model.IDrawable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

// This panel represent the animated part of the view with the car images.

class DrawPanel extends JPanel {

    private Image backgroundImage;

    private GameFrame gameFrame;

    // Initializes the panel and reads the images
    DrawPanel(GameFrame gameFrame, int x, int y) {
        this.gameFrame = gameFrame;
        this.addKeyListener(gameFrame);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));

        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getClassLoader().getResource("res/images/grass.png"));
            backgroundImage = bufferedImage.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        requestFocus();
        g.drawImage(backgroundImage, 0, 0, null);

        for(IDrawable drawable : gameFrame.controller.getDrawables()){
            g.drawImage(drawable.getRotatedImage(), drawable.getPosition().x, drawable.getPosition().y, null);
        }

    }

}
