
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class podar {
    public Image img;
    public int x;
    public int y;
    public boolean act;
    Timer timerUpdate;
    public podar(Image img) {
        timerUpdate = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vniz();
            }
        });
        this.img = img;
        act = false;
    }
    public void start () {
        timerUpdate.start();
        y=0;
        x=(int)(Math.random()*700);
        act = true;
    }
    public void vniz() {
        if (act == true) {
            y+=6;
        }
        if ((y+img.getHeight(null)) >=800) {
            timerUpdate.stop();
        }
    }
    public void draw(Graphics gr) {
        if (act == true) {
            gr.drawImage(img,x,y,null);
        }
    }

}
