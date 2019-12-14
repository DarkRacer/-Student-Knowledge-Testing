import javax.swing.*;
import java.awt.event.ActionEvent;

public class Test extends AbstractAction {
    public static JFrame jFrameTest;
    public static JTextField jTextField;
    public static JTextField jTextField1;
    public static JTextField jTextField3;
    Test() {
            putValue(AbstractAction.SHORT_DESCRIPTION, "Ввод информации и запуск теста");
        }
        @Override
        public void actionPerformed (ActionEvent actionEvent){
            jFrameTest = Frame.getFrameTest();
            Panel.jFrame.setVisible(false);
            JPanel jPanel= new JPanel();
            jFrameTest.add(jPanel);
            jPanel.add(new JLabel("Фамилия"));
            jTextField=new JTextField(50);
            jPanel.add(jTextField);
            jPanel.add(new JLabel("Имя"));
            jTextField1=new JTextField(53);
            jPanel.add(jTextField1);
            jPanel.add(new JLabel("Группа"));
            jTextField3=new JTextField(51);
            jPanel.add(jTextField3);
            JButton Test = new JButton(new Testing());
            Test.setText("Тестирование");
            jPanel.add(Test);
            JButton jButton = new JButton(new End_Test());
            jButton.setText("Вернуться назад");
            jPanel.add(jButton);
    }
    }
class End_Test extends AbstractAction {
    End_Test() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Вернуться к главному меню");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Test.jFrameTest.setVisible(false);
        Panel.jFrame.setVisible(true);
    }
}
