import java.awt.*;
import java.awt.event.*;

public class ActionPanel extends Panel implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Button attackButton;
    private Button actButton;
    private Button magicButton;
    private Button itemsButton;
    private Button mercyButton;

    private String selectedAction;

    public ActionPanel() {
        setLayout(new FlowLayout());

        attackButton = new Button("Атаковать");
        actButton = new Button("Действовать");
        magicButton = new Button("Магия");
        itemsButton = new Button("Предметы");
        mercyButton = new Button("Пощада");

        attackButton.addActionListener(this);
        actButton.addActionListener(this);
        magicButton.addActionListener(this);
        itemsButton.addActionListener(this);
        mercyButton.addActionListener(this);

        add(attackButton);
        add(actButton);
        add(magicButton);
        add(itemsButton);
        add(mercyButton);
    }

    public String getSelectedAction() {
        return selectedAction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == attackButton) {
            selectedAction = "Атаковать";
        } else if (e.getSource() == actButton) {
            selectedAction = "Действовать";
        } else if (e.getSource() == magicButton) {
            selectedAction = "Магия";
        } else if (e.getSource() == itemsButton) {
            selectedAction = "Предметы";
        } else if (e.getSource() == mercyButton) {
            selectedAction = "Пощада";
        }
    }
}