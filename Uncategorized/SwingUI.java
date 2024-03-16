import javax.swing.*;
import java.awt.event.*;

public class SwingExps {
    static JFrame frame = new JFrame("Swing UI examples");

    public static void main(String[] args) {
        // frame.setLayout(null);
        // frame.setVisible(true);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDialog();

    }

    public static void ButtonExample() {
        JButton btn = new JButton("Click here");
        btn.setBounds(50, 100, 95, 30);
        frame.add(btn);
        frame.setSize(300, 300);
    }

    public static void ButtonExample1() {
        final JTextField textField = new JTextField();
        textField.setBounds(200, 100, 250, 20);

        JButton btn = new JButton("Click here");
        btn.setBounds(50, 100, 95, 30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("Welcome to JAVA swing program.");
            }
        });

        btn.setText("Click here2");

        frame.add(btn);
        frame.add(textField);
        frame.setSize(300, 300);

    }

    public static void ButtonExample2() {
        JButton b = new JButton(new ImageIcon("C:\\Screenshot 2023-12-15 135730.png"));
        b.setBounds(500, 500, 500, 40);
        frame.add(b);
        frame.setSize(300, 400);
    }

    public static void CheckBoxExample() {
        JCheckBox checkBoxMca1 = new JCheckBox("MCA 1");
        checkBoxMca1.setBounds(100, 100, 100, 50);

        JCheckBox checkBoxMca2 = new JCheckBox("MCA 2");
        checkBoxMca2.setBounds(100, 150, 100, 50);

        frame.add(checkBoxMca1);
        frame.add(checkBoxMca2);
        frame.setSize(500, 500);
    }

    public static void CheckBoxExample1() {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(400, 100);

        JCheckBox checkbox1 = new JCheckBox("C++");
        checkbox1.setBounds(150, 100, 50, 50);

        JCheckBox checkbox2 = new JCheckBox("Java");
        checkbox2.setBounds(150, 150, 50, 50);

        frame.add(checkbox1);
        frame.add(checkbox2);
        frame.add(label);

        checkbox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                label.setText("C++" + (e.getStateChange() == 1 ? "checked" : "unchecked"));
            }
        });

        checkbox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                label.setText("Java" + (e.getStateChange() == 1 ? "checked" : "unchecked"));
            }
        });

        frame.setSize(400, 400);
    }

    public static void textArea() {
        final JTextArea textArea = new JTextArea("Text area");
        textArea.setBounds(200, 100, 250, 20);

        frame.add(textArea);
        frame.setSize(300, 300);
    }

    public static void textFields() {

        JTextField t1 = new JTextField("Java Swing.");
        t1.setBounds(50, 100, 200, 30);
        JTextField t2 = new JTextField("Swing  textField");
        t2.setBounds(50, 150, 200, 30);

        frame.add(t1);
        frame.add(t2);
    }

    public static void label() {
        JLabel label = new JLabel("Java swing label");
        label.setBounds(50, 100, 250, 20);
        frame.add(label);
        frame.setSize(300, 300);
    }

    public static void list() {
        String days[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        JList<String> list = new JList<String>(days);
        list.setBounds(50, 50, 200, days.length * 18);
        list.setSelectedIndex(2);

        frame.add(list);
        frame.setSize(300, 300);
    }

    public static void optionPane() {
        JOptionPane.showMessageDialog(null, "Alert!", "Don't use AWT", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void JMenuItem() {
        JMenu menu = new JMenu("Menu");

        JMenuItem i1 = new JMenuItem("Item 1");
        JMenuItem i2 = new JMenuItem("Item 2");
        JMenuItem i3 = new JMenuItem("Item 3");

        menu.add(i1);
        menu.add(i2);
        menu.add(i3);

        JMenuBar menubar = new JMenuBar();
        menubar.add(menu);

        frame.setJMenuBar(menubar);
        frame.setSize(300, 300);
    }

    public static void JDialog() {
        JDialog dialog = new JDialog(frame, "Swing Dialog Box");
        dialog.add(new JLabel("Dialog box content"));
        dialog.setSize(300, 300);
        dialog.setVisible(true);
    }
}
