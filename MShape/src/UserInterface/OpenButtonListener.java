package UserInterface;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        File file = MUtilities.GetFileFromUser();
        StringBuilder str = new StringBuilder();
        try {

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                str.append(scanner.nextLine() + System.lineSeparator());
            }
            MainGUI.script_txt.setText(str.toString());
            scanner.close();
        } 
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

}
