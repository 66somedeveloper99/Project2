package MShape.MLang.Translation;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import MShape.MLang.Commands.ColorCommand;
import MShape.MLang.Commands.DownCommand;
import MShape.MLang.Commands.ICommand;
import MShape.MLang.Commands.IncVarCommand;
import MShape.MLang.Commands.MoveCommand;
import MShape.MLang.Commands.SetVarCommand;
import MShape.MLang.Commands.SizeCommand;
import MShape.MLang.Commands.StyleCommand;
import MShape.MLang.Commands.UpCommand;
import MShape.MLang.Variables.IntVariable;

/**
 * Translates MLang to Commands
 */
public class Translator {
    final FileInputStream fileInputStream;
    final Scanner input;

    public Translator(File MFile) throws FileNotFoundException {
        this.fileInputStream = new FileInputStream(MFile);
        this.input = new Scanner(fileInputStream);
    }

    /**
     * returns an array of commands from the input M file, based on M language
     * @throws Exception
     */
    public ICommand[] Translate() throws Exception {

        ArrayList<String> all_lines = new ArrayList<String>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if(line.isEmpty()) continue; // ignoring blank lines
            all_lines.add(line);
        }

        ArrayList<ICommand> commands = translateLines(all_lines);

        System.out.println("Compilation successful!");

        // ArrayList<ICommand> => ICommand[]
        ICommand[] commands_arr = new ICommand[commands.size()];
        for (int i = 0; i < commands_arr.length; i++) commands_arr[i] = commands.get(i);

        return commands_arr;
    }

    /**
     * returns a list of commands based on the argument's lines
     * @throws Exception
     */
    private ArrayList<ICommand> translateLines(ArrayList<String> lines) throws Exception {
        
        // the returning command
        ArrayList<ICommand> commands = new ArrayList<ICommand>();

        String line;
        String command;
        int line_index = 0;

        // a lable for later usage
        main_loop:
        do {
            line = lines.get(line_index);
            String[] line_split = line.split("[(),]");
            command = line_split[0];    

            switch (command) {

                case "UP":
                {
                    commands.add(new UpCommand());
                }
                    break;
                
                case "DOWN":
                {
                    commands.add(new DownCommand());
                }
                    break;

                case "MOVE": 
                {
                    int x = Integer.parseInt(line_split[1].trim());
                    int y = Integer.parseInt(line_split[2].trim());
                    commands.add(new MoveCommand(x, y));
                }
                    break;
                
                case "COLOR": 
                {
                    int r = Integer.parseInt(line_split[1].trim());
                    int g = Integer.parseInt(line_split[2].trim());
                    int b = Integer.parseInt(line_split[3].trim());

                    Color color = new Color(r, g, b);
                    commands.add(new ColorCommand(color));
                }
                    break;

                case "SIZE":
                {
                    int size = Integer.parseInt(line_split[1].trim());
                    commands.add(new SizeCommand(size));
                }
                    break;
                
                case "STYLE":
                {
                    commands.add(new StyleCommand(line_split[1]));
                }
                    break;
                
                case "FOR": 
                {
                    ArrayList<String> new_lines = new ArrayList<String>();
                    int h = Integer.parseInt(line_split[1].trim());
                    int k = Integer.parseInt(line_split[2].trim());

                    // get next h lines of string commands
                    for (int i = 1; i <= h; i++) {
                        new_lines.add(lines.get(line_index + i));
                    }

                    // convert to commands
                    ArrayList<ICommand> for_commands = translateLines(new_lines);

                    // add the commands to the list of commands, k times
                    for (; k > 0; k--) {
                        commands.addAll(for_commands);
                    }
                }
                    break;

                case "SET": 
                {

                    String name = line_split[1];
                    String value = line_split[2].trim();

                    /**
                     * check for type of value in a try catch. if it did not throw exception in convertion one type ,
                     *  it means it's of that type
                     */

                    try{
                        int int_value = Integer.parseInt(value);
                        commands.add(
                            new SetVarCommand(new IntVariable(name), Integer.valueOf(int_value))
                            );

                        // this variable was of type int. done.
                        continue main_loop;
                    } catch (Exception e) {}

                    try{
                        // other variable types here :...

                        // this variable was of type ___. done.
                        continue main_loop;
                    } catch (Exception e) {}

                    // if reached this line, then the variable was not recognized.
                    System.err.println("Variable "+name+" was not recognized.");
                    throw new Exception("Translation Aborted.");
                    
                }
                    
                case "INC": 
                {
                    String name = line_split[1];
                    String value = line_split[2];

                    /**
                     * check for type of value in a try catch. if it did not throw exception in convertion one type ,
                     *  it means it's of that type
                     */

                    try{
                        int int_value = Integer.parseInt(value);
                        commands.add(
                            new IncVarCommand(new IntVariable(name), Integer.valueOf(int_value))
                            );

                        // this variable was of type int. done.
                        continue main_loop;
                    } catch (Exception e) {}

                    try{
                        // other variable types here :...

                        // this variable was of type ___. done.
                        continue main_loop;
                    } catch (Exception e) {}

                    // if reached this line, then the variable was not recognized.
                    System.err.println("Variable "+name+" was not recognized.");
                    throw new Exception("Translation Aborted.");
                }
                
                default:
                {
                    /**
                     * if reached here, the command was not recognized. throw exception
                     */
                    System.err.println("Command "+command+" was not recognized");
                    throw new Exception("Translation aborted.");
                }
            }

            // line_index ++;
        }
        while (++line_index < lines.size());

        
        return commands;
    }

    /**
     * Closes input files and disposes other disposables
     */
    public void Dispose() {
        input.close();
        try { fileInputStream.close(); } 
        catch (IOException e) { }
    }
}
