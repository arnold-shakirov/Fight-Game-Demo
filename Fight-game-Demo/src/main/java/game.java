import javax.swing.*;
public class game {
    public static void main(String[] args) {

        String rez = JOptionPane.showInputDialog(null,"И какая будет сложность?","Сложности", 1);
        int slozhn = rez.charAt(0)-'0';
        if ((slozhn >= 1) && (slozhn <= 7)) {
            okno window = new okno(slozhn);
            window.getPole().setOkno(window);
        }
    }
}
