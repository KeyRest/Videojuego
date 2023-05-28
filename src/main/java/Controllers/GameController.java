package Controllers;

import Models.Enemy;
import Models.Player;
import Models.Projectile;
import Views.GameFrame;
import Views.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
*Keiron Garro M
*C23212
*UCR
 */
public class GameController extends Thread implements KeyListener {

    private GamePanel gamePanel;
    private GameFrame gameFrame;
    private Player player;
    private ArrayList<Enemy> enemies;

    private int playerSpeed = 8;

    public GameController(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.player = this.gamePanel.getPlayer();
        this.player.setDefault();
        this.player.getImage();
        this.enemies = new ArrayList<>(0);
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / 60;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (this != null) {
            System.out.println(player.getX() + " " + player.getY());
            update();
            this.gamePanel.repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                this.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {
        player.update();
        checkCollision();
        this.enemies.forEach((n) -> n.seguirPersonaje(player));
        updateProjectiles();
    }

    @Override
    public void keyTyped(KeyEvent e
    ) {
    }

    @Override
    public void keyPressed(KeyEvent e
    ) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            player.setUpON(true);
        }
        if (keyCode == KeyEvent.VK_A) {
            player.setLeftON(true);
        }
        if (keyCode == KeyEvent.VK_S) {
            player.setDownON(true);
        }
        if (keyCode == KeyEvent.VK_D) {
            player.setRightON(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            player.setUpON(false);
        }
        if (keyCode == KeyEvent.VK_A) {
            player.setLeftON(false);
        }
        if (keyCode == KeyEvent.VK_S) {
            player.setDownON(false);
        }
        if (keyCode == KeyEvent.VK_D) {
            player.setRightON(false);
        }
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void generateEnemy() {

        int enemyId = (int) (Math.random() * 2 + 1);
        Enemy enemy = new Enemy(enemyId);
        int x = (int) (Math.random() * 768 + 1);
        int y = (int) (Math.random() * 576 + 1);
        if (isPositionValid(x, y)) {
            enemy.setX(x);
            enemy.setY(y);
        }
        this.enemies.add(enemy);

    }

    public void enemyGeneration(Graphics2D g2) {

        if (enemies.size() < 5) {
            generateEnemy();
        }
        this.enemies.forEach((n) -> n.draw(g2));
    }

    public void projectiles(Graphics2D g2) {

        player.getProjectiles().forEach((n) -> n.draw(g2));
    }

    public void updateProjectiles() {
        for (int i = player.getProjectiles().size() - 1; i >= 0; i--) {
            Projectile projectile = player.getProjectiles().get(i);
            projectile.update();

            // Verificar si el proyectil está fuera de los límites del juego
            if (projectile.getX() < 0 || projectile.getX() > 768 || projectile.getY() < 0 || projectile.getY() > 576) {
                player.getProjectiles().remove(i);  // Eliminar el proyectil de la lista
            }
        }
    }

    public void checkCollision() {
        Rectangle playerHitBox = player.getHitBox();

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            Rectangle enemyHitBox = enemy.getHitBox(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

            // Verifica la colisión entre el jugador y el enemigo
            if (playerHitBox.intersects(enemyHitBox)) {
                JOptionPane.showMessageDialog(gamePanel, "Usted perdio");
                this.stop();
                continue;
            }

            for (int j = i + 1; j < enemies.size(); j++) {
                Enemy enemy2 = enemies.get(j);
                Rectangle otherEnemyHitBox = enemy2.getHitBox(enemy2.getX(), enemy2.getY(), enemy2.getWidth(), enemy2.getHeight());

                // Verifica la colisión entre dos enemigos
                if (enemyHitBox.intersects(otherEnemyHitBox)) {
                    double dx = enemy.getX() - enemy2.getX();
                    double dy = enemy.getY() - enemy2.getY();
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    // Verificar si la colisión es horizontal o vertical
                }

            }
        }
    }

    private boolean isPositionValid(int x, int y) {
        // Verifica si la posición está lo suficientemente lejos del jugador principal
        double distanceToPlayer = Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
        if (distanceToPlayer < 15) {
            return false;
        }

        // Verifica si la posición está lo suficientemente lejos de otros enemigos existentes
        for (Enemy enemy : enemies) {
            double distanceToEnemy = Math.sqrt((x - enemy.getX()) * (x - enemy.getX()) + (y - enemy.getY()) * (y - enemy.getY()));

            // Si la posición está muy cerca de otro enemigo, no es válida
            if (distanceToEnemy < 15) {
                return false;
            }
        }

        return true; // La posición es válida si cumple todas las condiciones
    }

}
