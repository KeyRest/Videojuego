/*
*Keiron Garro M
*C23212
*UCR
 */
package Models;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Enemy extends Entity {

    public Enemy(int id) {
        getImage(id);
        setDefault();
    }

    public void setDefault() {
        x = 100;
        y = 100;
        speed = 12;
        direction = "right";
        width = 50;
        height = 50;
        hitbox = new Rectangle(x, y, width, height);
    }

    public void getImage(int i) {
        try {
            switch (i) {
                case 1:
                    leftImage = ImageIO.read(getClass().getResourceAsStream("/enemies/Diablo1.png"));
                    rightImage = ImageIO.read(getClass().getResourceAsStream("/enemies/Diablo2.png"));
                    break;
                case 2:
                    leftImage = ImageIO.read(getClass().getResourceAsStream("/enemies/muerte1.png"));
                    rightImage = ImageIO.read(getClass().getResourceAsStream("/enemies/muerte2.png"));
                    break;
            }

        } catch (Exception e) {
            e.toString();
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

    public void update() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void seguirPersonaje(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();

        if (x < playerX && y < playerY) {
            direction = "right";
            x++;
            y++;
        } else if (x > playerX && y < playerY) {
            direction = "left";
            x--;
            y++;
        } else if (x < playerX && y > playerY) {
            direction = "right";
            x++;
            y--;
        } else if (x > playerX && y > playerY) {
            direction = "left";
            x--;
            y--;
        } else if (x < playerX) {
            direction = "right";
            x++;
        } else if (x > playerX) {
            direction = "left";
            x--;
        } else if (y < playerY) {
            direction = "down";
            y++;
        } else if (y > playerY) {
            direction = "up";
            y--;
        }

    }

    public int getRadius() {

        int radius = Math.max(width / 2, height / 2);
        return radius;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

}
