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
        final Drawer drawer = new Drawer();
        final Commander commander = new Commander(commands, drawer);

        Thread thread = new Thread() {
            @Override
            public void run() {
                // executing commands
                while (commander.hasNextCommand()) {
                    commander.ExecuteNextCommand();
                    // wait for 0.1 second
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

}
