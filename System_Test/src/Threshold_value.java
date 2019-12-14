import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;

public class Threshold_value extends AbstractAction {
    public static JFrame jFrameThreshold_value;
    public static JTextField jTextField5;
    public static JTextField jTextField4;
    public static JTextField jTextField3;
    Threshold_value()
    {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Задание порога правильных ответов для получения оценки");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Panel.jFrame.setVisible(false);
        jFrameThreshold_value=Frame.getFrameThreshold_value();
        JPanel jPanelThreshold_value =new JPanel();
        jFrameThreshold_value.add(jPanelThreshold_value);
        JButton Threshold_value = new JButton(new Threshold_value1());
        jPanelThreshold_value.add(new JLabel("Порог ответов на оценку 5 "));
        jTextField5=new JTextField(40);
        jPanelThreshold_value.add(jTextField5);
        jPanelThreshold_value.add(new JLabel("Порог ответов на оценку 4 "));
        jTextField4=new JTextField(40);
        jPanelThreshold_value.add(jTextField4);
        jPanelThreshold_value.add(new JLabel("Порог ответов на оценку 3 "));
        jTextField3=new JTextField(40);
        jPanelThreshold_value.add(jTextField3);
        Threshold_value.setText("Изменить порог оценивания");
        jPanelThreshold_value.add(Threshold_value);
        JButton jButton = new JButton(new End_Threshold());
        jButton.setText("Вернуться назад");
        jPanelThreshold_value.add(jButton);
    }
    class Threshold_value1 extends AbstractAction {
        Threshold_value1() {
            putValue(AbstractAction.SHORT_DESCRIPTION, "Сохранение нового порога оценивания");
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Connect connect=null;
            final char dm = (char) 34;
            int Threshold_value5, Threshold_value4, Threshold_value3;
            if(Threshold_value.jTextField5.getText().matches("\\d+")&& Threshold_value.jTextField4.getText().matches("\\d+")&& Threshold_value.jTextField3.getText().matches("\\d+")) {
                Threshold_value5= Integer.parseInt(Threshold_value.jTextField5.getText());
                Threshold_value4= Integer.parseInt(Threshold_value.jTextField4.getText());
                Threshold_value3= Integer.parseInt(Threshold_value.jTextField3.getText());
                if(Threshold_value5>Threshold_value4 && Threshold_value4>Threshold_value3 && Threshold_value3!=0) {
                    try {
                        connect = new Connect();
                        try (Statement statement = connect.getConnection().createStatement()) {
                            statement.execute("update test.rating set Threshold=" +dm + Threshold_value5 + dm + "where idRating=5");
                            statement.execute("update test.rating set Threshold=" + dm + Threshold_value4 + dm + "where idRating=4");
                            statement.execute("update test.rating set Threshold=" + dm + Threshold_value3 + dm + "where idRating=3");
                            Panel.jFrame.setVisible(true);
                            Threshold_value.jFrameThreshold_value.setVisible(false);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else JOptionPane.showMessageDialog(Kol_Question.jPanelKol_Question, "Проверьте введенные данные", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            }
            else JOptionPane.showMessageDialog(Kol_Question.jPanelKol_Question, "Проверьте введенные данные", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        }
    }
}
class End_Threshold extends AbstractAction {
    End_Threshold() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Вернуться к главному меню");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Threshold_value.jFrameThreshold_value.setVisible(false);
        Panel.jFrame.setVisible(true);
    }
}