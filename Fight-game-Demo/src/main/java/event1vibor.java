import java.awt.event.KeyEvent;
public class event1vibor {
    public pole gameP;
    public int[] vibor = new int[6];
    public int i = 0;

    public void choosing() {
        for (i = 0; i < 6; i++) {
            vibor[i] = 0;
        }
    }
    public void keyPressed(KeyEvent e) {
        int key_ = e.getKeyCode();
        if (key_ == 27) {
            System.exit(0);
        } else if (key_ == 37 && i > 0) { // влево
            vibor[i] = 0;
            i--;
            vibor[i] = 1;
        } else if (key_ == 39 && i < 5) { // вправо
            vibor[i] = 0;
            i++;
            vibor[i] = 1;
        }
    }
}

