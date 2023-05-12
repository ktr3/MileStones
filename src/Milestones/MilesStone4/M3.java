package Milestones.MilesStone4;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class M3 extends JFrame { //implements ActionListener {
//    private JTextArea jTextArea;
//    private JComboBox<String> imageList;
//    private JCheckBox jcb;
//
//    public void loadCombo(){
//
//        imageList = new JComboBox<String>();
//        add(imageList);
//
//        File folder = new File("src/img");
//        File[] files = folder.listFiles();
//        for (File file : files) {
//            if (file.isFile()) {
//                imageList.addItem(file.getName());
//            }
//        }
//        imageList.setMaximumSize(new Dimension(200,25));
//    }
//    public M3(){
//        String password = JOptionPane.showInputDialog(null, "Enter password:","Password", JOptionPane.QUESTION_MESSAGE);
//
//        if (password != null && password.equals("damocles")) {
//            JFrame mainFrame = new JFrame("Main Window");
//            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            mainFrame.setSize(400, 300);
//            mainFrame.setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "Incorrect password. Application will close.", "Error", JOptionPane.ERROR_MESSAGE);
//            System.exit(0);
//        }
//
//        Container c = this.getContentPane();
//
//        this.setTitle("Milestone 4");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                JOptionPane.showMessageDialog(M3.this, "Bye!");
//            }
//        });
//
//        c.setLayout(new BorderLayout());
//
//        JPanel jp1 = new JPanel();
//        jp1.setSize(new Dimension(250, 200));
//        loadCombo();
//        jp1.add(imageList);
//
//        JPanel jp2 = new JPanel();
//        ImageIcon imageIcon = new ImageIcon(("src/img/"+imageList.getSelectedItem()));
//        JLabel image = new JLabel(imageIcon);
//        image.setMaximumSize(new Dimension(500,600));
//        jp2.add(image);
//        ComboListener listener = new ComboListener(image);
//        imageList.addActionListener(listener);
//
//
//        JPanel jp3 = new JPanel();
//        jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
//        JPanel jp31 = new JPanel();
//        jp31.setLayout(new BoxLayout(jp31, BoxLayout.X_AXIS));
//        jcb = new JCheckBox("Save your comment");
//        jcb.setSelected(true);
//        jTextArea = new JTextArea();
//        jTextArea.setMaximumSize(new Dimension(200,25));
//        jTextArea.setBorder(new LineBorder(Color.BLACK));
//        jp31.add(jcb);
//        jp31.add(jTextArea);
//
//        JPanel jp32 = new JPanel();
//        jp32.setLayout(new BoxLayout(jp32, BoxLayout.Y_AXIS));
//        JButton save = new JButton("SAVE");
//        save.addActionListener(this);
//        jp32.add(save);
//
//
//        jp3.add(jp31);
//        jp3.add(jp32);
//
//
//        c.add(jp1, BorderLayout.NORTH);
//        c.add(jp2, BorderLayout.CENTER);
//        c.add(jp3, BorderLayout.SOUTH);
//
//        this.pack();
//        this.setVisible(true);
//        this.setSize(500, 500);
//    }
//
//    public static void main(String[] args) {
//        M3 m3 = new M3();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        File file = new File("src/Files/Imagestxt/"+imageList.getSelectedItem().toString().replace(".jpg","")+".txt");
//
//        BufferedWriter br = null;
//        try {
//            br = new BufferedWriter(new FileWriter(file,true));
//            if (jcb.isSelected()&&jTextArea!=null){
//                br.write("Name: "+imageList.getSelectedItem().toString()+", Comment:"+jTextArea.getText());
//                br.newLine();
//            } else {
//                br.write("Name: "+imageList.getSelectedItem().toString());
//                br.newLine();
//            }
//        } catch (IOException ex) {
//            System.out.println("IOException");
//        }finally {
//            if (br!=null) {
//                try {
//                    br.close();
//                } catch (IOException ex) {
//                    System.out.println("IOException");
//                }
//            }else {
//                System.out.println("Error closing Buffered Writer");
//            }
//        }
//    }
//}

    static JFrame frame0;
    static JTextArea textArea;
    static JComboBox combo;

    /*===  Elements at the left.*/
    public static void west(Container frame0) {
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setBorder(new EmptyBorder(20, 20, 20, 0));
        westPanel.setPreferredSize(new Dimension(300, 200));

        JPanel conbazo = new JPanel();
        JPanel img = new JPanel();
        JPanel check = new JPanel();

        combo = new JComboBox();
        combo.setSize(new Dimension(100, 50));
        combo.setBorder(new EmptyBorder(0, 0, 10, 0));
        combo.addItem("luffi");
        combo.addItem("baki");
        combo.addItem("baki2");
        combo.addItem("goku2");
        combo.addItem("luffi2");

        JLabel imgs = new JLabel();
        imgs.setIcon(new ImageIcon("src/img/luffi2.jpg"));
        imgs.setSize(new Dimension(150, 300));
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                imgs.setIcon(new ImageIcon("src/img/" + combo.getSelectedItem().toString() + ".jpg"));
            }
        });
        JCheckBox saveOrNot = new JCheckBox("Save comment");
        conbazo.add(combo);
        img.add(imgs);
        check.add(saveOrNot);
        westPanel.add(conbazo);
        westPanel.add(img);
        westPanel.add(check);
        frame0.add("West", westPanel);
    }

    public static void middle(Container frame0) {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        centerPanel.setBorder(new EmptyBorder(20, 0, 20, 20));
        centerPanel.setPreferredSize(new Dimension(250, 200));

        JPanel text = new JPanel();
        text.setBorder(new EmptyBorder(390, 10, 10, 10));

        TextField comment = new TextField(30);
        comment.setSize(new Dimension(45, 10));

        text.add(comment);
        centerPanel.add(text);
        frame0.add("Center", centerPanel);
    }

    /*====ELEMENTS AT BOTTOM.*/
    public static void south(Container frame0) {
        JPanel southP = new JPanel();
        southP.setBorder(new EmptyBorder(10, 400, 10, 10));

        JButton but1 = new JButton("Close");
        but1.setPreferredSize(new Dimension(100, 30));
        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });


        southP.add(but1);
        frame0.add("South", southP);
    }

    /*===     Creation frame and call to the methods.*/
    public static void GUI() {
        frame0 = new JFrame("Milestone 3");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setLayout(new BorderLayout());
        frame0.setPreferredSize(new Dimension(600, 600));


        west(frame0);
        middle(frame0);
        south(frame0);

        frame0.pack();
        frame0.setLocationRelativeTo(null);
        frame0.setVisible(true);
    }

    /*==  LogIn.*/
    public static void logIn() {
        String pass = "damocles";
        String password = JOptionPane.showInputDialog
                (null, "Input password", "MileStone 3", JOptionPane.QUESTION_MESSAGE);
        if (password.equals("damocles")) {
            GUI();
        } else {
            JOptionPane.showMessageDialog
                    (null, "invalid user id and password", "MileStone3", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        logIn();
    }
}