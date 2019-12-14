import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Rating{
    private int idRating;
    private int Threshold;

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public int getThreshold() {
        return Threshold;
    }

    public void setThreshold(int threshold) {
        Threshold = threshold;
    }
}

class Rating_info extends AbstractAction {
    public static JFrame jFrame_Rating;
    public static JPanel jPanel_Rating;
    private static int rating;
    private static int o=0;
    Rating_info(){
        putValue(AbstractAction.SHORT_DESCRIPTION, "Закончить тест и просмотреть результат тестирования");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Testing.jFrameTesting.setVisible(false);
        if (o==1) {
            o=0;
            jFrame_Rating.removeAll();
        }
        String str1=(String)Testing.box_end.getSelectedItem();
        if (str1.equals(Testing.stringotv)) {
            Testing.prav++;
        }
        jFrame_Rating = Frame.getFrameRes();
        jPanel_Rating = new JPanel();
        jFrame_Rating.add(jPanel_Rating);
        Connect connect = null;
        Testing.setI(1);
        int otv;
        try {
            connect = new Connect();
            Statement statement = connect.getConnection().createStatement();
            final ResultSet resultSet = statement.executeQuery("select * from rating");
            while (resultSet.next()) {
                otv=resultSet.getInt(2);
                if(Testing.prav<=otv) {
                    setRating(resultSet.getInt(1));
                    statement.execute("UPDATE test.result SET idRating = "+rating+" WHERE Date = '"+ Testing.currentTime+"'");
                    jPanel_Rating.add(new JLabel("Тест окончен вы ответили правильно на "+Testing.prav+" вопросов из "+Kol_Question.getKolichestvo()));
                    jPanel_Rating.add(new JLabel("Ваша оценка: "+ getRating()));
                    JButton jButton_end=new JButton(new Rating_end());
                    jButton_end.setText("Вернуться назад");
                    jPanel_Rating.add(jButton_end);
                    o=1;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getRating() {
        return rating;
    }

    public static void setRating(int rating) {
        Rating_info.rating = rating;
    }
}

class Rating_end extends AbstractAction{
    Rating_end() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Вернуться к главному меню");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Rating_info.setRating(2);
        Testing.setK(1);
        Rating_info.jFrame_Rating.setVisible(false);
        Panel.jFrame.setVisible(true);
    }
}
