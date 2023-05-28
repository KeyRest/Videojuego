/*
*Keiron Garro M
*C23212
*UCR
 */
package Models;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    protected int x, y;
    protected int width, height;
    protected int speed;
    protected BufferedImage leftImage, rightImage;
    protected String direction;
    protected Rectangle hitbox;

    public Rectangle getHitBox(int x, int y, int width, int height) {
        return this.hitbox;
    }

}
