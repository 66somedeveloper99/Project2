package UserInterface;

import java.awt.event.*;

import MShape.Drawing.Commander;
import MShape.Drawing.Drawer;
import MShape.MLang.Commands.ICommand;
import MShape.MLang.Translation.Translator;

public class RunButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ShowFile(MainGUI.script_txt.getText());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void ShowFile(String string) throws Exception {
        Translator translator = new Translator(string);
        ICommand[] commands = translator.Translate();
        RunDrawing(commands);
    }

    private static void RunDrawing(ICommand[] commands) {
        Drawer drawer = new Drawer();
        Commander commander = new Commander(commands, drawer);

        // executing commands
        while (commander.hasNextCommand()) {
            commander.ExecuteNextCommand();

            // wait for 1 second
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
