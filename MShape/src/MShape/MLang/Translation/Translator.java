package MShape.MLang.Translation;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import MShape.MLang.Commands.*;

/**
 * Translates MLang to Commands
 */
public class Translator {

    String script;

    public Translator(File MFile) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(MFile);
        Scanner input = new Scanner(fileInputStream);
        StringBuilder str = new StringBuilder();
        while (input.hasNextLine())
            str.append(input.nextLine() + System.lineSeparator());

        script = str.toString();
        input.close();
        fileInputStream.close();
    }

    public Translator(String script) {
        this.script = script;
    }

    /**
     * returns an array of commands from the input M file, based on M language
     * @throws Exception
     */
    public ICommand[] Translate() throws Exception {

        System.out.println("translating " + script);
        String[] lines = script.split("\n");
        ArrayList<String> mod_lines = new ArrayList<String>();

        for (String line : lines) {
            if(line.trim().isEmpty()) continue; // ignoring blank lines
            mod_lines.add(line);
        }

        ArrayList<ICommand> commands = translateLines(mod_lines);

        System.out.println("Compilation successful! "+commands.size()+" commands in total");

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
                    String xName = GetIntVariableName(commands, line_split[1].trim(), "TMP_X");
                    String yName = GetIntVariableName(commands, line_split[2].trim(), "TMP_X");

                    commands.add(new MoveCommand(xName, yName));
                }
                    break;
                
                case "COLOR": 
                {
                    String rName = GetIntVariableName(commands, line_split[1].trim(), "TMP_R");
                    String gName = GetIntVariableName(commands, line_split[2].trim(), "TMP_G");
                    String bName = GetIntVariableName(commands, line_split[3].trim(), "TMP_B");

                    commands.add(new ColorCommand(rName, gName, bName));
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
                    for (; k > 1; k--) {
                        commands.addAll(for_commands);
                    }
                }
                    break;

                case "SET": 
                {

                    String name = line_split[1];
                    String value = line_split[2].trim();

                    int int_value = Integer.parseInt(value);
                    commands.add( new SetVarCommand(name, Integer.valueOf(int_value)));
                    break;
                }
                    
                case "INC": 
                {
                    String name = line_split[1].trim();
                    String valName = GetIntVariableName(commands, line_split[2].trim(), "TMP_INC");
                    commands.add(new IncIntCommand(name, valName));
                    break;
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

    private String GetIntVariableName(ArrayList<ICommand> commands, String str, String tmp_var_name) {
        String name;
        try {
            int val = Integer.parseInt(str);
            name = tmp_var_name;
            commands.add(new SetVarCommand(name, val));
        } catch (Exception e) {
            name = str;
        }
        return name;
    }
}
