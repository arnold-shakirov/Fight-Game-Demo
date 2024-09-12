import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class okno extends JFrame {
    public pole gameP;
    private boolean enterKeyPressed = false;
    private final int slozhn;

    public pole getPole() {
        return gameP;
    }

    public okno(int slozhn) {
        this.slozhn = slozhn;
        event1vibor event1vibor = new event1vibor();
        addKeyListener(new myKey());
        setFocusable(true);
        setBounds(550, 200, 800, 800);
        setTitle("Questionnaire");
        gameP = new pole(this, slozhn);
        Container con = getContentPane();
        con.add(gameP);
        setVisible(true);

    }


    public void setResult(String s) {
    }

    public class myKey implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            int key_ = e.getKeyCode();
            int minX = 100;
            int maxX = 600;
            int minY = 200;
            int maxY = 600;
            if (key_ == 27) {
                System.exit(0);
            } else if (key_ == 37) { // влево
                if (gameP.x - 30 >= minX) {
                    gameP.x = gameP.x - 30;
                } else {
                    gameP.x = minX;
                }
            } else if (key_ == 39) { // вправо
                if (gameP.x + 30 <= maxX) {
                    gameP.x = gameP.x + 30;
                } else {
                    gameP.x = maxX;
                }
            } else if (key_ == 40) { // вниз
                if (gameP.y + 30 <= maxY) {
                    gameP.y = gameP.y + 30;
                } else {
                    gameP.y = maxY;
                }
            } else if (key_ == 38) { // вверх
                if (gameP.y - 30 >= minY) {
                    gameP.y = gameP.y - 30;
                } else {
                    gameP.y = minY;
                }
            } else if (key_ == KeyEvent.VK_ENTER) {
                enterKeyPressed = true;
                System.out.println("ENTER");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }
}
