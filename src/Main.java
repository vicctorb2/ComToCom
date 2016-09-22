import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * Created by ррор on 14.09.2016.
 */
public class Main {
    static SerialPort com1;
    static SerialPort com2;
    static Panel mainpanel;
    static Panel secondpanel;
    static Frame mainframe;
    static Frame secondframe;

    public static void main(String[] args) {
        secondpanel = new Panel();
        mainpanel = new Panel();
        secondpanel.setListeners();
        mainpanel.setListeners();
        mainframe = new Frame();
        secondframe = new Frame();
        mainframe.setContentPane(mainpanel);
        secondframe.setContentPane(secondpanel);
        secondframe.setVisible(true);
        mainframe.setVisible(true);
        com1 = new SerialPort("COM1");
        com2 = new SerialPort("COM2");
    }

    static public void sendData(Panel sourcepanel, Panel receivepanel, SerialPort sourceport, SerialPort receiveport) {
        try {
            //Открываем порт
            sourceport.openPort();
            receiveport.openPort();
            //Выставляем параметры
            sourceport.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            receiveport.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Включаем аппаратное управление потоком
            sourceport.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            receiveport.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            //Устанавливаем ивент лисенер и маску
            sourceport.writeString(sourcepanel.getInput_field().getText());
            receivepanel.getOutput_field().setText(receiveport.readString());
            sourceport.closePort();
            receiveport.closePort();
        } catch (
                SerialPortException ex) {
            sourcepanel.getDebug_field().setText(ex.toString());
            receivepanel.getDebug_field().setText(ex.toString());
        }
    }

}
