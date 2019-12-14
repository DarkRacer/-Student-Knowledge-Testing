import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Result_Date extends AbstractAction {
    private int id;
    private String Firstname;
    private String name;
    private String Group;
    private String data;
    private int idRat;
    public static JComboBox<String> box_date = new JComboBox<>();
    public static JFrame jFrame_Res;
    public static JPanel jPanel_Res;
    public static JTextArea jTextArea_Res1;
    Result_Date(){
        putValue(AbstractAction.SHORT_DESCRIPTION, "Просмотр результатов тестирования по дате");
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
            final ResultSet resultSet = statement.executeQuery("select `Date` from result");
            while (resultSet.next()) {
                box_date.addItem(resultSet.getString("Date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jPanel_Res.add(new JLabel("Дата "));
        jPanel_Res.add(box_date);
        JButton jButton=new JButton(new Res_info_Date());
        jButton.setText("Информация");
        jPanel_Res.add(jButton);
        JButton jButton_end=new JButton(new Res_end_date());
        jButton_end.setText("Вернуться назад");
        jPanel_Res.add(jButton_end);
        jTextArea_Res1 = new JTextArea(15, 55);
        JScrollPane jScrollPane = new JScrollPane(jTextArea_Res1);
        jPanel_Res.add(jScrollPane);
        jTextArea_Res1.setEditable(false);
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
class Res_info_Date extends AbstractAction {
    Res_info_Date() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Просмотр результатов");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Connect connect = null;
        String date1;
        final char dm = (char) 34;
        try {
            connect = new Connect();
            Statement statement = connect.getConnection().createStatement();
            final ResultSet resultSet = statement.executeQuery("select * from re sultwhere `Date`=" + dm + Result_Date.box_date.getSelectedItem() + dm);
            while (resultSet.next()) {
                Result_Date date= new Result_Date();
                date.setFirstname(resultSet.getString(2));
                date.setName(resultSet.getString(3));
                date.setGroup(resultSet.getString(4));
                date.setData(resultSet.getString(5));
                date.setIdRat(resultSet.getInt(6));
                date1 = date.toString() + "\n";
                Result_Date.jTextArea_Res1.append(date1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
class Res_end_date extends AbstractAction{
    Res_end_date() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Вернуться к главному меню");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Result_Date.jFrame_Res.setVisible(false);
        Panel.jFrame.setVisible(true);
    }
}