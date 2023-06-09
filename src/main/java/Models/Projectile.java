package Models;

/**
 *
 * @author Keyron
 */
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Projectile {

    private int x;  // Coordenada X del proyectil
    private int y;  // Coordenada Y del proyectil
    private int speed = 12;
    private int width = 5;  // Ancho del proyectil
    private int height = 5;  // Alto del proyectil
    private boolean active;
    private String direction;
    private Rectangle hitbox;  // Hitbox del proyectil
    private Player player;
    private BufferedImage projectilImage;

    // Constructor
    public Projectile(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.active = true;
        hitbox = new Rectangle(x, y, width, height);
        this.player = player;
        getImage();
        getDirection();
    }

    public void getImage() {
        try {
            this.projectilImage = ImageIO.read(getClass().getResourceAsStream("/projectile/projectile.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    // Método para actualizar la posición y afectar la hitbox del proyectil
    public void update() {

        if (direction.equals("up")) {
            y -= speed;
        } else if (direction.equals("down")) {
            y += speed;
        } else if (direction.equals("left")) {
            x -= speed;
        } else if (direction.equals("right")) {
            x += speed;
        }

        hitbox.setLocation(x, y);

    }

    public void getDirection() {
        switch (player.getDirection()) {
            case "up":
                direction = "up";

                break;
            case "down":
                direction = "down";

                break;
            case "left":
                direction = "left";

                break;
            case "right":
                direction = "right";

                break;
            default:
                throw new AssertionError();
        }
    }
    // Método para dibujar el proyectil en un objeto Graphics2D

    public void draw(Graphics2D g2) {
        g2.drawImage(projectilImage, x, y, 32, 32, null);
    }

    // Método para comprobar colisiones con la hitbox de un enemigo
    public boolean collidesWithEnemy(Enemy enemy) {
        Rectangle enemyHitbox = enemy.getHitbox();
        return hitbox.intersects(enemyHitbox);
    }

    // Getters y setters para las propiedades del proyectil
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return width;
    }

    public int getAlto() {
        return height;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
