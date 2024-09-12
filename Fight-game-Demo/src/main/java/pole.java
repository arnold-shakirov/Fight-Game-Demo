import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class pole extends JPanel {
    private Image heart;
    private boolean actionKeyPressed = false;
    private boolean showPlayer = true;
    private Image fon;
    public int hp = 20;
    private int counter = 20;
    public int x = 400;
    public int y = 600;
    private int slozhn;
    private podar[] gamePodar;
    private Image endGame;
    private int stage = 1; // Этап: 1 - уклонение, 2 - выбор действий
    public Timer timerUpdate, timerDraw;
    private long startTime = System.currentTimeMillis(); // Время начала этапа уклонения
    private okno parent; // Ссылка на экземпляр класса Okno
    private int selectedAction = -1; // Выбранное действие: 0 - Атаковать, 1 - Действовать, 2 - Магия, 3 - Предметы, 4 - Пощада
    private int selectedAction1 = -1;

    public pole(okno parent, int slozhn) {
        this.parent = parent;
        this.slozhn = slozhn;

        try {
            heart = ImageIO.read(new File("/Users/daniilshakirov/Downloads/heart.png"));
            fon = ImageIO.read(new File("/Users/daniilshakirov/Downloads/fon.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        gamePodar = new podar[7];
        for (int i = 0; i < 7; i++) {
            try {
                gamePodar[i] = new podar(ImageIO.read(new File("/Users/daniilshakirov/Downloads/p" + i + ".png")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        timerUpdate = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStart();
            }
        });
        timerUpdate.start();

        timerDraw = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timerDraw.start();

        setFocusable(true);
        requestFocus();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                handleKeyPressed(e);
            }
        });
    }

    public void setOkno(okno parent) {
        this.parent = parent;
    }

    public void setStage(int stage) {
        this.stage = stage;
        if (stage == 1) {
            startTime = System.currentTimeMillis();
        }
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g2d = (Graphics2D) gr;
        gr.drawImage(fon, 0, 0, null);
        gr.setColor(Color.WHITE);
        gr.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        gr.drawString("LV", 100, 600);
        if (showPlayer) {
            gr.drawImage(heart, x, y, null);
        }
        int boxWidth = getWidth() / 5;
        int boxHeight = getHeight() - 650;
        int boxY = getHeight() - boxHeight;
        String[] actionTexts = {"Атаковать", "Действовать", "Магия", "Предметы", "Пощада"};

        for (int i = 0; i < 5; i++) {
            int boxX = i * boxWidth;
            gr.setColor(Color.WHITE);
            gr.fillRect(boxX, boxY, boxWidth, boxHeight);
            gr.setColor(Color.BLACK);
            gr.drawRect(boxX, boxY, boxWidth, boxHeight);

            if (i == selectedAction && stage == 2) {
                gr.setColor(Color.RED);
                gr.drawRect(boxX, boxY, boxWidth, boxHeight);
            }

            gr.setColor(Color.BLACK);
            gr.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            int textX = boxX + boxWidth / 2 - 50;
            int textY = boxY + boxHeight / 2;
            gr.drawString(actionTexts[i], textX, textY);
        }

        if (stage == 1) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 12000) {
                stage = 2;
            } else {
                updateStart();
                for (int i = 0; i < 7; i++) {
                    gamePodar[i].draw(gr);

                    if (gamePodar[i].act) {
                        if (gamePodar[i].y + gamePodar[i].img.getHeight(null) >= getParent().getHeight()) {
                            gamePodar[i].act = false;
                        }

                        int heartCenterX = x + heart.getWidth(null) / 2;
                        int heartCenterY = y + heart.getHeight(null) / 2;
                        int podarCenterX = gamePodar[i].x + gamePodar[i].img.getWidth(null) / 2;
                        int podarCenterY = gamePodar[i].y + gamePodar[i].img.getHeight(null) / 2;

                        if (Math.abs(heartCenterX - podarCenterX) < (heart.getWidth(null) + gamePodar[i].img.getWidth(null)) / 2 &&
                                Math.abs(heartCenterY - podarCenterY) < (heart.getHeight(null) + gamePodar[i].img.getHeight(null)) / 2) {
                            gr.drawImage(endGame, 300, 300, null);
                            counter = counter - (int) (Math.random() * 5);
                            timerDraw.stop();
                            timerUpdate.stop();
                            break;
                        }
                    }
                }
            }
        } else if (stage == 2) {
            showPlayer = false;
            //if (=KeyEvent.VK_SPACE) {

            //}
        }
    }

    private int podarIndex = 0;

    private void updateStart() {
        int kol = 0;
        for (int i = 0; i < 7; i++) {
            if (!gamePodar[i].act) {
                if (kol < slozhn) {
                    gamePodar[i].start();
                    break;
                }
            } else {
                kol++;
            }
        }
    }

    private void handleKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (stage == 2) {
            if (keyCode == KeyEvent.VK_LEFT) {
                if (selectedAction >= 0 && selectedAction <= 5) {
                    selectedAction1 = selectedAction - 1;
                    repaint();
                }
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                if (selectedAction >= 0 && selectedAction <= 5) {
                    selectedAction1 = selectedAction + 1;
                    repaint();
                }
            } else if (keyCode == KeyEvent.VK_ENTER) {
                actionKeyPressed = true;
                selectedAction = selectedAction1;
                System.out.println("ENTER");
                repaint();
            }
        }
    }


    private void handleSelectedAction(int selectedAction) {
        if (selectedAction == 0) {
            // Атаковать
            parent.setResult("Вы выбрали действие: Атаковать");
        } else if (selectedAction == 1) {
            // Действовать
            parent.setResult("Вы выбрали действие: Действовать");
        } else if (selectedAction == 2) {
            // Магия
            parent.setResult("Вы выбрали действие: Магия");
        } else if (selectedAction == 3) {
            // Предметы
            parent.setResult("Вы выбрали действие: Предметы");
        } else if (selectedAction == 4) {
            // Пощада
            parent.setResult("Вы выбрали действие: Пощада");
        }
    }
}
