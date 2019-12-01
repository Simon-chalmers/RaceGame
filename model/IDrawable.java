package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IDrawable {

    BufferedImage getImage();
    Point getPosition();
    BufferedImage getRotatedImage();

}
