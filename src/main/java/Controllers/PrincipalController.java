/*
*Keiron Garro M
*C23212
*UCR
 */
package Controllers;

import Models.Player;
import Views.GameFrame;
import Views.GamePanel;
import Views.PrincipalFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalController implements ActionListener {

    private PrincipalFrame principalFrame;
    private GameController gameController;
    private Player player;
    private GameFrame gameFrame;
    private GamePanel gamePanel;

    public PrincipalController() {
        this.principalFrame = new PrincipalFrame();
        this.principalFrame.play_btn.addActionListener(this);
        this.gameFrame = new GameFrame();
        this.gamePanel = gameFrame.getGamePanel();
        this.player = new Player();
        gamePanel.setPlayer(player);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "JUGAR":
                this.gameController = new GameController(gamePanel);
                gameController.setGameFrame(gameFrame);
                gamePanel.setGameController(gameController);
                gameController.start();
                gameFrame.setVisible(true);
                break;
            default:
                throw new AssertionError();
        }
    }

}
