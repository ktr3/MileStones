package Milestones.MilesStone4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboListener implements ActionListener {
    private JLabel imageLabel;

    public ComboListener(JLabel imageLabel) {
        this.imageLabel = imageLabel;
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox<String> jcb = (JComboBox<String>) e.getSource();
        String selected = (String) jcb.getSelectedItem();
        ImageIcon newImage = new ImageIcon("src/img/"+selected);
        imageLabel.setIcon(newImage);
    }
}
