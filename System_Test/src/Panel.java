import javax.swing.*;

public class Panel {
    public static JFrame jFrame = Frame.getFrame();
    static JPanel getPanel(){
        JPanel jPanel =new JPanel();
        jFrame.add(jPanel);
        JButton Test = new JButton(new Test());
        Test.setText("Тест");
        jPanel.add(Test);
        JButton New_Question = new JButton(new New_Question());
        New_Question.setText("Ввести новый вопрос");
        jPanel.add(New_Question);
        JButton Kol_Question = new JButton(new Kol_Question());
        Kol_Question.setText("Задать количество задаваемых вопросов");
        jPanel.add(Kol_Question);
        JButton Threshold_value = new JButton(new Threshold_value());
        Threshold_value.setText("Изменить порог оценивания");
        jPanel.add(Threshold_value);
        JButton Resultat = new JButton(new Res_info());
        Resultat.setText("Результаты");
        jPanel.add(Resultat);
        JButton Result1 = new JButton(new Result_Date());
        Result1.setText("Результаты по дате");
        jPanel.add(Result1);
        JButton Result2 = new JButton(new Result_rating());
        Result2.setText("Результаты по оценке");
        jPanel.add(Result2);
        JButton Result = new JButton(new Result_Group());
        Result.setText("Результаты по группе");
        jPanel.add(Result);
        jFrame.setVisible(true);
        return jPanel;
    }
}
