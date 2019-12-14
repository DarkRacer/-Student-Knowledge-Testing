import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Testing extends AbstractAction {
    public static JFrame jFrameTesting;
    public static String currentTime;
    public static JTextArea jTextArea = new JTextArea(15, 50);
    public static JScrollPane jScrollPane = new JScrollPane(jTextArea);
    public static JButton jButton_end = new JButton(new Testing_end());
    public static JButton jButton_Otvet = new JButton(new Otvet());
    public static String stringotv;
    public static String stringotv_end;
    private static String[] Otvet = {"Ответ №1", "Ответ №2", "Ответ №3"};
    public static JComboBox<String> box =new JComboBox<String>(Otvet);
    public static JComboBox<String> box_end =new JComboBox<String>(Otvet);
    public static int g=1;
    public static int i=1;
    private static int k=0;
    public static int prav=0;

    Testing() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Запуск теста");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(k==1) {
            k=0;
            jFrameTesting.removeAll();
        }
        prav=0;
        Connect connect = null;
        setI(1);
        final char dm = (char) 34;
        if (Test.jTextField.getText().matches("^[а-яА-Я]+$")
                && Test.jTextField1.getText().matches("^[а-яА-Я]+$")
                && !Test.jTextField3.getText().isEmpty()) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            currentTime = sdf.format(date);
            Panel.jFrame.setVisible(false);
            try {
                connect = new Connect();
                try (Statement statement = connect.getConnection().createStatement()) {
                    statement.execute("insert into test.result (`Firstname`, `Name`, `Group`, `Date`) values (" + dm + Test.jTextField.getText() + dm + ","
                            + dm + Test.jTextField1.getText() + dm + "," + dm + Test.jTextField3.getText() + dm + "," + dm + currentTime + dm + ");");
                    g=1;
                    Vopros();
                   }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            JOptionPane.showMessageDialog(Kol_Question.jPanelKol_Question, "Проверьте введенные данные", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
    }

    public static void Vopros() {
        jFrameTesting = Frame.getFrameTesting();
        JPanel jPanel_Vopros = new JPanel();
        jFrameTesting.add(jPanel_Vopros);
        Connect connect = null;
        Test.jFrameTest.setVisible(false);
        jTextArea.setEditable(true);
        jTextArea.setText(null);
        try {
            connect = new Connect();
            try (Statement statement = connect.getConnection().createStatement()) {
                final ResultSet resultSet_Vopros = statement.executeQuery("select Vopros, Otvet1, Otvet2, Otvet3, Correctanswer from voprosi order by rand() limit 1");
                while (resultSet_Vopros.next()) {
                    jTextArea.append(resultSet_Vopros.getString("Vopros")+"\n");
                    jTextArea.append("Ответ №1: "+resultSet_Vopros.getString("Otvet1")+"\n");
                    jTextArea.append("Ответ №2: "+resultSet_Vopros.getString("Otvet2") +"\n");
                    jTextArea.append("Ответ №3: "+resultSet_Vopros.getString("Otvet3")+"\n");
                    stringotv=resultSet_Vopros.getString("Correctanswer");
               }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jPanel_Vopros.add(new JLabel("Вопрос("+g+"/"+Kol_Question.getKolichestvo()+")"));
        g++;
        jTextArea.setEditable(false);
        jPanel_Vopros.add(jScrollPane);
        jPanel_Vopros.add(new JLabel("Правильный ответ "));
        jPanel_Vopros.add(box);
        jButton_end.setText("Вернуться назад");
        jButton_Otvet.setText("Ответить");
        jPanel_Vopros.add(jButton_Otvet);
        jPanel_Vopros.add(jButton_end);
    }

    public static void Vopros_end(){
        jFrameTesting = Frame.getFrameTesting();
        JPanel jPanel_Vopros = new JPanel();
        jFrameTesting.add(jPanel_Vopros);
        String[] Otvet = {"Ответ №1", "Ответ №2", "Ответ №3"};
        box_end = new JComboBox<>(Otvet);
        Connect connect = null;
        Test.jFrameTest.setVisible(false);
        jTextArea.setEditable(true);
        jTextArea.setText(null);
        try {
            connect = new Connect();
            try (Statement statement = connect.getConnection().createStatement()) {
                final ResultSet resultSet_Vopros = statement.executeQuery("select Vopros, Otvet1, Otvet2, Otvet3, Correctanswer from voprosi order by rand() limit 1");
                while (resultSet_Vopros.next()) {
                    jTextArea.append(resultSet_Vopros.getString("Vopros")+"\n");
                    jTextArea.append("Ответ №1: "+resultSet_Vopros.getString("Otvet1")+"\n");
                    jTextArea.append("Ответ №2: "+resultSet_Vopros.getString("Otvet2") +"\n");
                    jTextArea.append("Ответ №3: "+resultSet_Vopros.getString("Otvet3")+"\n");
                    stringotv=resultSet_Vopros.getString("Correctanswer");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        g--;
        jPanel_Vopros.add(new JLabel("Вопрос("+g+"/"+Kol_Question.getKolichestvo()+")"));
        jTextArea.setEditable(false);
        jPanel_Vopros.add(jScrollPane);
        jPanel_Vopros.add(new JLabel("Правильный ответ "));
        jPanel_Vopros.add(box_end);
        stringotv_end=(String)box_end.getSelectedItem();
        jButton_end.setText("Вернуться назад");
        JButton jButton=new JButton(new  Rating_info());
        jButton.setText("Ответить и закончить тест");
        jPanel_Vopros.add(jButton);
        jPanel_Vopros.add(jButton_end);
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        Testing.i = i;
    }

    public static int getK() {
        return k;
    }

    public static void setK(int k) {
        Testing.k = k;
    }
}
    class Testing_end extends AbstractAction {
        Testing_end() {
            putValue(AbstractAction.SHORT_DESCRIPTION, "Вернуться к главному меню. Результаты не будут сохранены");
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Connect connect = null;
            try {
                connect = new Connect();
                try (Statement statement = connect.getConnection().createStatement()) {
                    statement.execute("DELETE FROM `test`.`result` WHERE Date = '"+ Testing.currentTime+"'");
                    Testing.jFrameTesting.setVisible(false);
                    Panel.jFrame.setVisible(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }