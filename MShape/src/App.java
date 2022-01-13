import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import MShape.Drawing.Commander;
import MShape.Drawing.Drawer;
import MShape.MLang.Commands.ICommand;
import MShape.MLang.Translation.Translator;

public class App {
    public static void main(String[] args) throws FileNotFoundException {


        System.out.println("Choose a M file to open");
        System.out.print("Open graphical File chooser?[Y/<address of file>]");

        Scanner input = new Scanner(System.in);
        String ans = input.nextLine();

        File file = null;

        if (ans.toLowerCase().equals("y")) {
            file = Utilities.GetFileFromUser();
        } else {
            file = new File(ans);
        }

        System.out.println("User chose file " + file.getAbsolutePath());
        System.out.println("Compiling the script...");

        ICommand[] commands = null;

        try {
            Translator translator;
            translator = new Translator(file);
            commands = translator.Translate();
            translator.Dispose();

            // printing the commands
            // for (ICommand com : commands) {
            //     System.out.println(com.toString());
            // }


        } catch (Exception e) {
            e.printStackTrace();
            input.close();
            System.exit(1);
        }

        // transfering commands to Commander to draw on Drawer
        RunDrawing(commands);
        
        input.close();
    }
    
    private static void RunDrawing(ICommand[] commands) {
        Drawer drawer = new Drawer();
        Commander commander = new Commander(commands, drawer);

        // executing commands 
        while(commander.hasNextCommand()) {
            commander.ExecuteNextCommand();
            
            // wait for 1 second
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }
    }
}
