import javax.swing.*;
import java.awt.event.ActionEvent;

public class Kol_Question extends AbstractAction {
    public static JFrame jFrameFrameKol_Question;
    public static JPanel jPanelKol_Question;
    public static JTextField jTextField;
    private static int Kolichestvo=5;

    Kol_Question() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Задание количества задаемых вопросов из имеющихся");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Panel.jFrame.setVisible(false);
        jFrameFrameKol_Question = Frame.getFrameKol_Question();
        jPanelKol_Question = new JPanel();
        jFrameFrameKol_Question.add(jPanelKol_Question);
        JButton Threshold_value = new JButton(new Kol_Que());
        jTextField = new JTextField(5);
        jPanelKol_Question.add(new JLabel("Количество вопросов "));
        jPanelKol_Question.add(jTextField);
        Threshold_value.setText("Изменить количество вопросов");
        jPanelKol_Question.add(Threshold_value);
        JButton jButton = new JButton(new End_KolQuestion());
        jButton.setText("Вернуться назад");
        jPanelKol_Question.add(jButton);
    }

    class Kol_Que extends AbstractAction {
        Kol_Que() {
            putValue(AbstractAction.SHORT_DESCRIPTION, "Сохранить новое количество вопросов");
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (Kol_Question.jTextField.getText().matches("\\d+")) {
                Kolichestvo = Integer.parseInt(Kol_Question.jTextField.getText());
                Panel.jFrame.setVisible(true);
                Kol_Question.jFrameFrameKol_Question.setVisible(false);
            } else
                JOptionPane.showMessageDialog(Kol_Question.jPanelKol_Question, "Проверьте введенные данные", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static int getKolichestvo() {
        return Kolichestvo;
    }

    public static void setKolichestvo(int kolichestvo) {
        Kolichestvo = kolichestvo;
    }
}
class End_KolQuestion extends AbstractAction {
    End_KolQuestion() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Вернуться к главному меню");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Kol_Question.jFrameFrameKol_Question.setVisible(false);
        Panel.jFrame.setVisible(true);
    }
}
