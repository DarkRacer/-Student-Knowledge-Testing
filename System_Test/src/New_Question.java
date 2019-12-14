import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;


public class New_Question extends AbstractAction {
    public static JFrame jFrameNew_Question;
    public static JTextArea jTextArea;
    public static JTextField jTextField1;
    public static JTextField jTextField2;
    public static JTextField jTextField3;
    public String[] Otveti = {"Ответ №1", "Ответ №2", "Ответ №3"};
    public JComboBox Otvet = new JComboBox(Otveti);

    New_Question() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Ввод вопроса с указанием правильных ответов");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Panel.jFrame.setVisible(false);
        jFrameNew_Question = Frame.getFrameNew_Question();
        JPanel jPanel = new JPanel();
        jFrameNew_Question.add(jPanel);
        jPanel.add(new JLabel("Вопрос"));
        jTextArea = new JTextArea(3, 50);
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jPanel.add(jScrollPane);
        jPanel.revalidate();
        jPanel.add(new JLabel("Ответ №1:"));
        jTextField1 = new JTextField(48);
        jPanel.add(jTextField1);
        JTextField jTextField1 = new JTextField();
        jTextField1.getText();
        jPanel.add(new JLabel("Ответ №2:"));
        jTextField2 = new JTextField(48);
        jPanel.add(jTextField2);
        jTextField3 = new JTextField(48);
        jPanel.add(new JLabel("Ответ №3:"));
        jPanel.add(jTextField3);
        JButton Panel = new JButton(new New_Que());
        Panel.setText("Сохранить новый вопрос");
        jPanel.add(new JLabel("Правильный ответ "));
        jPanel.add(Otvet);
        JButton jButton = new JButton(new End_NewQuestion());
        jButton.setText("Вернуться назад");
        jPanel.add(Panel);
        jPanel.add(jButton);
    }

    class New_Que extends AbstractAction {
        New_Que() {
            putValue(AbstractAction.SHORT_DESCRIPTION, "Сохранение нового вопроса");
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!New_Question.jTextArea.getText().isEmpty()
                    && !New_Question.jTextField1.getText().isEmpty()
                    && !New_Question.jTextField2.getText().isEmpty()
                    && !New_Question.jTextField3.getText().isEmpty()) {
                String Otvet1 = (String) Otvet.getSelectedItem();
                String Vopros=jTextArea.getText();
                String OtvetV1=jTextField1.getText();
                String OtvetV2=jTextField2.getText();
                String OtvetV3=jTextField3.getText();
                Execute_Vopros(Vopros, OtvetV1, OtvetV2, OtvetV3, Otvet1);
                Panel.jFrame.setVisible(true);
                New_Question.jFrameNew_Question.setVisible(false);
            } else
                JOptionPane.showMessageDialog(Kol_Question.jPanelKol_Question, "Проверьте введенные данные", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        }

        public void Execute_Vopros(String Vopros, String  Otvet1, String  Otvet2, String  Otvet3, String  Otvet) {
            final char dm = (char) 34;
            Connect connect = null;
            try {
                connect = new Connect();
                Statement statement = connect.getConnection().createStatement();
                statement.execute("insert into test.voprosi (Vopros, Otvet1, Otvet2, Otvet3, Correctanswer) values (" + dm + Vopros + dm + ","
                        + dm + Otvet1 + dm + "," + dm + Otvet2 + dm
                        + "," + dm + Otvet3 + dm + "," + dm + Otvet + dm + ");");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
class End_NewQuestion extends AbstractAction {
    End_NewQuestion() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Вернуться к главному меню");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        New_Question.jFrameNew_Question.setVisible(false);
        Panel.jFrame.setVisible(true);
    }
}
