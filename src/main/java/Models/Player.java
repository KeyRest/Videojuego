/*
*Keiron Garro M
*C23212
*UCR
 */
package Models;

import Views.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {

    private boolean upON, downON, rightON, leftON;

    public Player() {
    }

    public void setDefault() {
        x = 320;
        y = 252;
        speed = 4;
        direction = "right";
        width = 36;
        height = 36;
    }

    public void update() {
        if (upON) {
            y -= speed;
        } else if (downON) {
            y += speed;
        } else if (leftON) {
            direction = "left";
            x -= speed;
        } else if (rightON) {
            direction = "right";
            x += speed;
        }
    }

    public void getImage() {
        try {
            leftImage = ImageIO.read(getClass().getResourceAsStream("/player/Angel1.png"));
            rightImage = ImageIO.read(getClass().getResourceAsStream("/player/Angel2.png"));
        } catch (IOException e) {
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image;

        switch (direction) {
            case "left":
                image = leftImage;
                break;
            case "right":
                image = rightImage;
                break;
            default:
                image = rightImage;
        }

        g2.drawImage(image, x, y, 96, 96, null);

    }

    public boolean isUpON() {
        return upON;
    }

    public void setUpON(boolean upON) {
        this.upON = upON;
    }

    public boolean isDownON() {
        return downON;
    }

    public void setDownON(boolean downON) {
        this.downON = downON;
    }

    public boolean isRightON() {
        return rightON;
    }

    public void setRightON(boolean rightON) {
        this.rightON = rightON;
    }

    public boolean isLeftON() {
        return leftON;
    }

    public void setLeftON(boolean leftON) {
        this.leftON = leftON;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public Rectangle getHitBox() {

        return super.getHitBox();

    }

}
