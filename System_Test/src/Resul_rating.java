import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Result_rating extends AbstractAction {
    private int id;
    private String Firstname;
    private String name;
    private String Group;
    private String data;
    private int idRat;
    public static JComboBox<String> box_rat = new JComboBox<>();
    public static JFrame jFrame_Res;
    public static JPanel jPanel_Res;
    public static JTextArea jTextArea_Res;
    Result_rating(){
        putValue(AbstractAction.SHORT_DESCRIPTION, "Просмотр результатов тестирования по дате");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Panel.jFrame.setVisible(false);
        jFrame_Res = Frame.getFrameRes();
        jPanel_Res = new JPanel();
        jFrame_Res.add(jPanel_Res);
        String[] rat = {"2", "3", "4", "5"};
        box_rat= new JComboBox<>(rat);
        jPanel_Res.add(new JLabel("Оценка "));
        jPanel_Res.add(box_rat);
        JButton jButton=new JButton(new Res_info_rat());
        jButton.setText("Информация");
        jPanel_Res.add(jButton);
        JButton jButton_end=new JButton(new Res_end_rating());
        jButton_end.setText("Вернуться назад");
        jPanel_Res.add(jButton_end);
        jTextArea_Res = new JTextArea(15, 55);
        JScrollPane jScrollPane = new JScrollPane(jTextArea_Res);
        jPanel_Res.add(jScrollPane);
        jTextArea_Res.setEditable(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdRat() {
        return idRat;
    }

    public void setIdRat(int idRat) {
        this.idRat = idRat;
    }

    @Override
    public String toString() {
        return "Фамилия: "+ Firstname
                + "; Имя: " + name
                + "; Группа: " + Group
                + "; Дата: "+ data
                + "; Оценка: "+ idRat;
    }
}
class Res_info_rat extends AbstractAction {
    Res_info_rat() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Просмотр результатов");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Connect connect = null;
        String rating1;
        final char dm = (char) 34;
        try {
            connect = new Connect();
            Statement statement = connect.getConnection().createStatement();
            final ResultSet resultSet = statement.executeQuery("select * from result where idRating=" + Result_rating.box_rat.getSelectedItem()+"");
            while (resultSet.next()) {
                Result_rating rating= new Result_rating();
                rating.setFirstname(resultSet.getString(2));
                rating.setName(resultSet.getString(3));
                rating.setGroup(resultSet.getString(4));
                rating.setData(resultSet.getString(5));
                rating.setIdRat(resultSet.getInt(6));
                rating1 =rating.toString() + "\n";
                Result_rating.jTextArea_Res.append(rating1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
class Res_end_rating extends AbstractAction{
    Res_end_rating() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Вернуться к главному меню");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Result_rating.jFrame_Res.setVisible(false);
        Panel.jFrame.setVisible(true);
    }
}