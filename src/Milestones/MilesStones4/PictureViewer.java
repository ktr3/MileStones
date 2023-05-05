package Milestones.MilesStones4;

/*
package Exercise2;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import org.jdesktop.swingx.JXDatePicker;

public class PictureViewer extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> photographersComboBox;
    private JXDatePicker datePicker;
    private JList<Picture> pictureList;
    private JLabel pictureLabel;

    public PictureViewer(List<Photographer> photographers, List<Picture> pictures) {
        super("Picture Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the components
        photographersComboBox = new JComboBox<>(photographers.toArray(new String[0]));
        photographersComboBox.addActionListener(new PhotographerComboBoxListener());
        datePicker = new JXDatePicker();
        pictureList = new JList<>(new DefaultListModel<>());
        pictureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pictureList.addListSelectionListener(new PictureListSelectionListener());
        pictureLabel = new JLabel();

        // Add the components to the content pane
        JPanel contentPane = new JPanel(new GridLayout(2, 2));
        contentPane.add(photographersComboBox);
        contentPane.add(datePicker);
        contentPane.add(new JScrollPane(pictureList));
        contentPane.add(pictureLabel);
        getContentPane().add(contentPane, BorderLayout.CENTER);

        // Set the initial list of pictures
        updatePictureList(pictures);

        // Pack and show the window
        pack();
        setVisible(true);
    }

    private void updatePictureList(List<Picture> pictures) {
        DefaultListModel<Picture> model = (DefaultListModel<Picture>) pictureList.getModel();
        model.clear();
        for (Picture picture : pictures) {
            if (picture.getPhotographer().equals(photographersComboBox.getSelectedItem())
                    && picture.getDate().equals(datePicker.getDate())) {
                model.addElement(picture);
            }
        }
        if (model.getSize() > 0) {
            pictureList.setSelectedIndex(0);
        }
    }

    private class PhotographerComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatePictureList(DatabaseManager.getPictures());
        }
    }

    private class PictureListSelectionListener implements javax.swing.event.ListSelectionListener {
        @Override
        public void valueChanged(javax.swing.event.ListSelectionEvent e) {
            Picture selectedPicture = pictureList.getSelectedValue();
            if (selectedPicture != null) {
                pictureLabel.setIcon(new ImageIcon(String.valueOf(selectedPicture.getPhotographerId())));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Photographer> photographers = null;
            try {
                photographers = DatabaseManager.getPhotographers();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            List<Picture> pictures = DatabaseManager.getPictures();
            new PictureViewer(photographers, pictures);
        });
    }
}
*/


import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PictureViewer extends JFrame implements ActionListener {

    PhotographerManager myPhotographerManager;
    private JButton awardButton;
    private JButton removeButton;
    private JComboBox comboBox;
    private DefaultComboBoxModel comboBoxModel;
    private JXDatePicker datePicker;
    private DefaultListModel listModel;
    private ImageIcon image;
    private JLabel pickLabel;

/*    public void addComponentsToPanel1(Container panel1) {
        awardButton = new JButton("AWARD");
        awardButton.setPreferredSize(new Dimension(200, 50));
        awardButton.addActionListener(this);
        panel1.add(awardButton);
    }*/
/*

    public void addComponentsToPanel2(Container panel2) {
        removeButton = new JButton("REMOVE");
        removeButton.setPreferredSize(new Dimension(200, 50));
        removeButton.addActionListener(this);
        panel2.add(removeButton);
    }
*/

    public void addComponentsToPanel3(Container panel3) {
        JLabel label3 = new JLabel("Photographer: ");
        comboBoxModel = new DefaultComboBoxModel<String>();
        List<Photographer> myPhotographers = new ArrayList<Photographer>();
        myPhotographers = myPhotographerManager.getPhotographers();
        Iterator<Photographer> it = myPhotographers.iterator();
        while (it.hasNext()) {
            String name = it.next().getName();
            comboBoxModel.addElement(name);
        }
        comboBox = new JComboBox(comboBoxModel);
        comboBox.addActionListener(this);
        panel3.add(label3);
        panel3.add(comboBox);
    }

    public void addComponentsToPanel4(Container panel4) {
        JLabel label4 = new JLabel("Photos after: ");
        datePicker = new JXDatePicker();
        datePicker.addActionListener(this);
        panel4.add(datePicker);
    }

    public void addPictures() {
        listModel.removeAllElements();
        List<Picture> myPictures = myPhotographerManager.getPictures(comboBox.getSelectedIndex(), datePicker.getDate());
        Iterator<Picture> it = myPictures.iterator();
        while (it.hasNext()) {
            listModel.addElement(it.next().getTitle());
        }
    }

    public void award() {
        String inputValue = JOptionPane.showInputDialog("Minimum number of visits for getting a prize: ");
        myPhotographerManager.award(Integer.parseInt(inputValue));
    }

    public void remove() {
        myPhotographerManager.remove();
    }

    public void addComponentsToPanel5(Container panel5) {
        listModel = new DefaultListModel<String>();
        JList list = new JList(listModel);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    List<Picture> myPictures = myPhotographerManager.getPictures(comboBox.getSelectedIndex(), datePicker.getDate());
                    System.out.println(myPictures.get(list.getSelectedIndex()).getFile());
                    image = new ImageIcon("src/img/" + myPictures.get(list.getSelectedIndex()).getFile());
                    image.setImage(image.getImage().getScaledInstance(200, 100, 1));
                    pickLabel.setIcon(image);
                }
            }
        });
        addPictures();
        JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(270, 100));
        panel5.add(scrollPane);
    }

    public void addComponentsToPanel6(Container panel6) {
        image = new ImageIcon("src/img/baki.jpg");
        image.setImage(image.getImage().getScaledInstance(200, 100, 1));
        pickLabel = new JLabel();
        pickLabel.setIcon(image);

        panel6.add(pickLabel);
    }

    public PictureViewer() {
        Container p = this.getContentPane();
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                myPhotographerManager.closeCon();
            }
        });
        this.setLayout(new GridLayout(3, 2));
        this.setPreferredSize(new Dimension(600, 450));

        myPhotographerManager = new PhotographerManager();

/*      JPanel panel1 = new JPanel();
        panel1.setBorder(new EmptyBorder(20,20,10,10));
        addComponentsToPanel1(panel1);
        this.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBorder(new EmptyBorder(20,10,10,20));
        addComponentsToPanel2(panel2);
        this.add(panel2);
*/

        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(300, 100));
        panel3.setBorder(new EmptyBorder(10, 20, 10, 10));
        addComponentsToPanel3(panel3);
        this.add(panel3);

        JPanel panel4 = new JPanel();
        panel4.setPreferredSize(new Dimension(300, 100));
        panel4.setBorder(new EmptyBorder(10, 10, 10, 20));
        addComponentsToPanel4(panel4);
        this.add(panel4);

        JPanel panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(300, 100));
        panel5.setBorder(new EmptyBorder(10, 20, 20, 10));
        addComponentsToPanel5(panel5);
        this.add(panel5);

        JPanel panel6 = new JPanel();
        panel6.setPreferredSize(new Dimension(300, 100));
        panel6.setBorder(new EmptyBorder(10, 20, 20, 10));
        addComponentsToPanel6(panel6);
        this.add(panel6);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        PictureViewer myPictureViewer = new PictureViewer();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source == comboBox) {
            addPictures();

        } else if (source == datePicker) {
            addPictures();

        } else if (source == awardButton) {
            award();

        } else if (source == removeButton) {
            remove();
        }
    }
}