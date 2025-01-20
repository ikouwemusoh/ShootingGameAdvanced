
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Main extends JPanel implements ActionListener, KeyListener {
    private int playerX, playerY; // Player's position
    private int bulletX, bulletY; // Bullet's position
    private boolean bulletFired; // Indicates if the bullet is fired
    private int enemyX, enemyY;  //Enemy's position
    private int enemyBulletX, enemyBulletY;  //Enemy bullet position
    private boolean enemyBulletFired;
    private int score=0;
    private int life=10;
    private  Label labelScore;


    public Main() {
        playerX = 200;
        playerY = 500;
        bulletX = playerX;
        bulletY = playerY;
        bulletFired = false;
        enemyX=getWidth();
        enemyY=20;
        enemyBulletFired=true;
        enemyBulletX=enemyX;
        enemyBulletY=enemyY;



        Timer timer = new Timer(10, this); // Timer to update game state
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawPlayer(g);
        drawEnemy(g);
        drawEnemyBullet(g);
        drawScore(g);

        if (!bulletFired) {


        } else {
            drawBullet(g);

        }
    }

    private void drawBackground(Graphics g) {
        // Draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawPlayer(Graphics g) {
        // Draw the player
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, 50, 50);
    }

    private void drawEnemy(Graphics g) {
        // Draw the player
        g.setColor(Color.GREEN);
        g.fillRect(enemyX, enemyY, 50, 50);
    }

    private void drawBullet(Graphics g) {
        // Draw the bullet
        g.setColor(Color.RED);
        g.fillRect(bulletX, bulletY, 10, 10);
    }

    private void drawEnemyBullet(Graphics g) {
        // Draw the enemy bullet
        g.setColor(Color.YELLOW);
        g.fillRect(enemyBulletX, enemyBulletY, 10, 10);
        Random random=new Random(2);
    }

    private void drawScore(Graphics g){
        if(bulletX==enemyX && bulletY==enemyY){
            score=score+1;
        }
        if(enemyBulletX==playerX && enemyBulletY==playerY){
            life=life-1;
        }
        g.setColor(Color.MAGENTA);
        g.drawString("SCORE : "+String.valueOf(score),getWidth()-100,getHeight()-100);
        g.setColor(Color.RED);
        g.drawString("Life : "+String.valueOf(life),getWidth()-100,getHeight()-90);

        labelScore=new Label(String.valueOf(score));
        labelScore.setLocation(100,getHeight()-100);
        labelScore.setBackground(Color.CYAN);
        labelScore.setForeground(Color.MAGENTA);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the game state
        if (bulletFired) {
            bulletY -= 2; // Move the bullet upwards
            if (bulletY < 0) {
                bulletFired = false; // Reset the bullet when it goes off-screen
            }
        }

        if (enemyBulletFired) {
            enemyBulletY += 2; // Move the bullet upwards
            if (enemyBulletY > getHeight()) {
                enemyBulletFired = false; // Reset the bullet when it goes off-screen
                enemyBulletX=enemyX;
                enemyBulletY=enemyY;
                enemyBulletFired = true;

            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && !bulletFired) {
            bulletX = playerX + 20; // Set bullet position to player's position
            bulletY = playerY - 10;
            bulletFired = true;
        }

        if (key == KeyEvent.VK_LEFT && playerX > 0) {
            playerX -= 10; // Move player to the left
        }

        if (key == KeyEvent.VK_RIGHT && playerX < getWidth() - 50) {
            playerX += 10; // Move player to the right
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shooting Game");
        Main game = new Main();
        frame.add(game);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
