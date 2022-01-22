package UserInterface;

import java.awt.event.*;
import java.io.IOException;

public class SaveButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = MainGUI.script_txt.getText();
        try {
            MUtilities.SaveFile(text);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
