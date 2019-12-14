import javax.swing.*;
import java.awt.event.ActionEvent;

public class Otvet extends AbstractAction {
    Otvet() {
        putValue(AbstractAction.SHORT_DESCRIPTION, "Сохранить ответ");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(Testing.getI()<Kol_Question.getKolichestvo()) {
            String str=(String)Testing.box.getSelectedItem();
            if (str.equals(Testing.stringotv)) {
                Testing.prav++;
            }
            Testing.jFrameTesting.setVisible(false);
            Testing.Vopros();
            Testing.setI(Testing.getI()+1);
            if(Testing.getI()==Kol_Question.getKolichestvo()){
                Testing.jFrameTesting.setVisible(false);
                Testing.Vopros_end();
            Testing.setI(Testing.getI()+1);
            }
        }
    }
}