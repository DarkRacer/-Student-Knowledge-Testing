import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Result_Group extends AbstractAction {
    private int id;
    private String Firstname;
    private String name;
    private String Group;
    private String data;
    private int idRat;
    public static JComboBox<String> box_Group = new JComboBox<>();
    public static JFrame jFrame_Res;
    public static JPanel jPanel_Res;
    public static JTextArea jTextArea_Res;
    Result_Group(){
        putValue(AbstractAction.SHORT_DESCRIPTION, "Просмотр результатов тестирования по группе");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Panel.jFrame.setVisible(false);
        jFrame_Res = Frame.getFrameRes();
        jPanel_Res = new JPanel();
        jFrame_Res.add(jPanel_Res);
        Connect connect = null;
        try {
            connect = new Connect();
            Statement statement = connect.getConnection().createStatement();
            final ResultSet resultSet = statement.executeQuery("select `group` from result");
            while (resultSet.next()) {
                box_Group.addItem(resultSet.getString("Group"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jPanel_Res.add(new JLabel("Группа "));
        jPanel_Res.add(box_Group);
        JButton jButton=new JButton(new Res_info_Group());
        jButton.setText("Информация");
        jPanel_Res.add(jButton);
        JButton jButton_end=new JButton(new Res_end_Group());
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
        return " Фамилия: "+ Firstname
                + "; Имя: " + name
                + "; Группа: " + Group
                + "; Дата: "+ data
                + "; Оценка: "+ idRat;
    }
}
class Res_info_Group extends AbstractAction {
    Res_info_Group() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Просмотр результатов");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Connect connect = null;
        String group1;
        final char dm = (char) 34;
        try {
            connect = new Connect();
            Statement statement = connect.getConnection().createStatement();
            final ResultSet resultSet = statement.executeQuery("select * from result where `Group`=" + dm + Result_Group.box_Group.getSelectedItem() + dm);
            while (resultSet.next()) {
                Result_Group group= new Result_Group();
                group.setFirstname(resultSet.getString(2));
                group.setName(resultSet.getString(3));
                group.setGroup(resultSet.getString(4));
                group.setData(resultSet.getString(5));
                group.setIdRat(resultSet.getInt(6));
                group1 = group.toString() + "\n";
                Result_Group.jTextArea_Res.append(group1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
class Res_end_Group extends AbstractAction{
    Res_end_Group() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Вернуться к главному меню");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Result_Group.jFrame_Res.setVisible(false);
        Panel.jFrame.setVisible(true);
    }
}