/*
*Keiron Garro M
*C23212
*UCR
 */
package Models;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Player extends Entity {

    private ArrayList<Projectile> projectiles;

    private boolean upON, downON, rightON, leftON;

    public Player() {
        this.projectiles = new ArrayList<>(0);
    }

    public void setDefault() {
        x = 320;
        y = 252;
        speed = 4;
        direction = "right";
        width = 50;
        height = 50;
    }

    public void update() {
        if (upON) {
            direction = "up";
            y -= speed;
        } else if (downON) {
            direction = "down";
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

        g2.drawImage(image, x, y, 64, 64, null);

    }

    public void shootProjectile() {

        Projectile projectile = new Projectile(x + 12, y + 12, this);
        projectiles.add(projectile);
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

    public String getDirection() {
        return direction;
    }

    public Rectangle getHitBox() {

        return super.getHitBox(x, y, width, height);

    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

}
