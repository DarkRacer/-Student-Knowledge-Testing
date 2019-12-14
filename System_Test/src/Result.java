import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Result{
    private int id;
    private String Firstname;
    private String name;
    private String Group;
    private String data;
    private int idRat;

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

class Res_info extends AbstractAction{
    public static JFrame jFrame_Res;
    public static JPanel jPanel_Res;
    Res_info() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Просмотр результатов");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Panel.jFrame.setVisible(false);
        jFrame_Res = Frame.getFrameRes();
        jPanel_Res = new JPanel();
        jFrame_Res.add(jPanel_Res);
        Connect connect = null;
        JTextArea jTextArea_Res = new JTextArea(15, 55);
        String result1;
        try {
            connect = new Connect();
            Statement statement = connect.getConnection().createStatement();
            final ResultSet resultSet = statement.executeQuery("select * from result");
            while (resultSet.next()) {
                Result result=new Result();
                result.setFirstname(resultSet.getString(2));
                result.setName(resultSet.getString(3));
                result.setGroup(resultSet.getString(4));
                result.setData(resultSet.getString(5));
                result.setIdRat(resultSet.getInt(6));
                result1 = result.toString() + "\n";
                jTextArea_Res.append(result1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JScrollPane jScrollPane = new JScrollPane(jTextArea_Res);
        jPanel_Res.add(jScrollPane);
        JButton jButton_end=new JButton(new Res_end());
        jButton_end.setText("Вернуться назад");
        jPanel_Res.add(jButton_end);
        jTextArea_Res.setEditable(false);
    }
}

class Res_end extends AbstractAction{
    Res_end() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Вернуться к главному меню");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Res_info.jFrame_Res.setVisible(false);
        Panel.jFrame.setVisible(true);
    }
}
