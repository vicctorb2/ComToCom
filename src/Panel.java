import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by ррор on 14.09.2016.
 */
public class Panel extends JPanel {
    private JTextArea input_field;
    private JTextArea output_field;
    private JTextArea debug_field;

//    static JLabel input_label;
//    static JLabel output_label;
//    static JLabel debug_label;

    public Panel(){
        setLayout(new GridLayout(3,1));
        setBackground(Color.gray);

        input_field = new JTextArea();
        output_field = new JTextArea();
        debug_field = new JTextArea();

        output_field.setEnabled(false);
        output_field.setBackground(Color.BLUE);
        output_field.setBackground(Color.cyan);
        debug_field.setEnabled(false);

//        input_label = new JLabel("Input (COM1):");
//        output_label = new JLabel("Output (COM2):");
//        debug_label = new JLabel("Debug/Info):");
//
//        input_field.setSize(200,50);
//        output_field.setSize(200,50);
//        debug_field.setSize(200,100);
//        input_field.setLocation(50,50);
//        output_field.setLocation(50,130);
//        debug_field.setLocation(50,225);

//        input_label.setSize(100,30);
//        output_label.setSize(100,30);
//        debug_label.setSize(100,30);

//        input_label.setLocation(50,20);
//        output_label.setLocation(50,100);
//        debug_label.setLocation(50,195);

        this.add(input_field);
        this.add(output_field);
        this.add(debug_field);
//        this.add(input_label);
//        this.add(output_label);
//        this.add(debug_label);
        setListeners();
    }

    public void setListeners() {
        input_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER){
                    if(Main.mainframe.isActive()) {
                        Main.sendData(Main.mainpanel,Main.secondpanel,Main.com1,Main.com2);
                    }
                    if(Main.secondframe.isActive()) {
                        Main.sendData(Main.secondpanel,Main.mainpanel,Main.com2,Main.com1);
                    }
                }
            }
        });
    }

    public JTextArea getInput_field() {
        return input_field;
    }

    public JTextArea getOutput_field() {
        return output_field;
    }

    public JTextArea getDebug_field() {
        return debug_field;
    }
}
