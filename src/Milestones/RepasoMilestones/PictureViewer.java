package Milestones.RepasoMilestones;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PictureViewer extends JFrame implements ActionListener {

    PhotographerManager pManager;
    private JButton awardButton;
    private JButton removeButton;
    private JComboBox<String> comboBox;
    private DefaultComboBoxModel<String> comboBoxModel;
    private JXDatePicker datePicker;
    private DefaultListModel<String> listModel;
    private ImageIcon image;
    private JLabel pickLabel;

    public void addCompToPan1(Container panel1) {
        awardButton = new JButton("AWARD");
        awardButton.setPreferredSize(new Dimension(200, 50));
        awardButton.addActionListener(this);
        panel1.add(awardButton);
    }

    public void addCompToPan2(Container pan2) {
        removeButton = new JButton("REMOVE");
        removeButton.setPreferredSize(new Dimension(200, 50));
        removeButton.addActionListener(this);
        pan2.add(removeButton);
    }

    public void addCompToPan3(Container pan3) {
        JLabel lb3 = new JLabel("Photographer: ");
        comboBoxModel = new DefaultComboBoxModel<>();
        List<Photographer> mPhotographers = pManager.getPhotographers();
        Iterator<Photographer> it = mPhotographers.iterator();
        while (it.hasNext()) {
            String name = it.next().getName();
            comboBoxModel.addElement(name);
        }
        comboBox = new JComboBox<>(comboBoxModel);
        comboBox.addActionListener(this);
        pan3.add(lb3);
        pan3.add(comboBox);
    }

    public void addCompToPan4(Container pan4) {
        JLabel lb4 = new JLabel("Photos after: ");
        datePicker = new JXDatePicker();
        datePicker.addActionListener(this);
        pan4.add(datePicker);
    }

    public void addPictures() {
        listModel.removeAllElements();
        List<Picture> myPictures = pManager.getPicture(
                comboBox.getSelectedIndex(), (Date) datePicker.getDate());
        Iterator<Picture> it = myPictures.iterator();
        while (it.hasNext()) {
            listModel.addElement(it.next().getTitle());
        }
    }

    public void award(){
        String inputValue = JOptionPane.showInputDialog(
                "Minimum number of visits to get prize: ");
        pManager.award(Integer.parseInt(inputValue));
    }

    public void remove(){
        pManager.remove();
    }

    public void addCompTopan5(Container pan5){
        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        list.addMouseListener(new MouseAdapter() {
            /**
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    List<Picture> myPictures = pManager.getPicture(
                            comboBox.getSelectedIndex(), (Date) datePicker.getDate());
                    System.out.println(myPictures.get(list.getSelectedIndex()).getFile());
                    image = new ImageIcon("src/img/" + myPictures.get(
                            list.getSelectedIndex()).getFile());
                    image.setImage(image.getImage().getScaledInstance(
                            200, 100, 1));
                    pickLabel.setIcon(image);
                }
            }
        });
        addPictures();
        JScrollPane scrollPane = new JScrollPane(
                list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(270, 100));
        pan5.add(scrollPane);
    }
    public void addCompToPan6(Container panel6) {
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
                pManager.closeCon();
            }
        });
        this.setLayout(new GridLayout(3, 2));
        this.setPreferredSize(new Dimension(600, 450));

        pManager = new PhotographerManager();

        JPanel panel1 = new JPanel();
        panel1.setBorder(new EmptyBorder(20, 20, 10, 10));
        addCompToPan1(panel1);
        this.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBorder(new EmptyBorder(20, 10, 10, 20));
        addCompToPan2(panel2);
        this.add(panel2);

        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(300, 100));
        panel3.setBorder(new EmptyBorder(10, 20, 10, 10));
        addCompToPan3(panel3);
        this.add(panel3);

        JPanel panel4 = new JPanel();
        panel4.setPreferredSize(new Dimension(300, 100));
        panel4.setBorder(new EmptyBorder(10, 10, 10, 20));
        addCompToPan4(panel4);
        this.add(panel4);

        JPanel panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(300, 100));
        panel5.setBorder(new EmptyBorder(10, 20, 20, 10));
        addCompTopan5(panel5);
        this.add(panel5);

        JPanel panel6 = new JPanel();
        panel6.setPreferredSize(new Dimension(300, 100));
        panel6.setBorder(new EmptyBorder(10, 20, 20, 10));
        addCompToPan6(panel6);
        this.add(panel6);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        PictureViewer pv = new PictureViewer();
    }


    /**
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source == comboBox || source == datePicker) {
            addPictures();

        } else if (source == awardButton) {
            award();

        } else if (source == removeButton) {
            remove();
        }
    }

}
