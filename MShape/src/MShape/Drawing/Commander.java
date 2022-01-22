package MShape.Drawing;

import java.awt.Color;
import MShape.MLang.Commands.*;
import MShape.Drawing.Drawer.Style;
import MShape.MLang.Variables.VariableDictionary;

/**
 * holds commands, then executes them on a Drawer
 */
public class Commander {
    public final ICommand[] commands;
    public final Drawer drawer;
    private int currentCommandIndex = 0;

    public Commander(ICommand[] commands, Drawer drawer) {
        this.commands = commands;
        this.drawer = drawer;
    }

    public int getCurrentCommandIndex() {
        return currentCommandIndex;
    }

    public boolean hasNextCommand() {
        return currentCommandIndex < commands.length;
    }

    /**
     * Executes next command (commands[currentCommandIndex++]) on the current Drawer
     */
    public void ExecuteNextCommand() {
        if (hasNextCommand())
            Execute(commands[currentCommandIndex++]);
    }

    private void Execute(ICommand command) {
        
        if (command.getClass().equals(DownCommand.class)) {

            drawer.Down();

        } else if (command.getClass().equals(SizeCommand.class)) {

            int size = ((SizeCommand) command).size;
            drawer.SetSize(size);
        }

        else if (command.getClass().equals(UpCommand.class)) {
            drawer.Up();
        }

        else if (command.getClass().equals(MoveCommand.class)) {
            int x = VariableDictionary.GetValueOf(((MoveCommand) command).xName).value;
            int y = VariableDictionary.GetValueOf(((MoveCommand) command).yName).value;
            System.out.println("MOVE : "+x+" : "+y);
            drawer.Move(x, y);
        }

        else if (command.getClass().equals(ColorCommand.class)) {
            ColorCommand cmd = (ColorCommand) command;
            int r = VariableDictionary.GetValueOf(cmd.rName).value;
            int g = VariableDictionary.GetValueOf(cmd.gName).value;
            int b = VariableDictionary.GetValueOf(cmd.bName).value;
            drawer.SetColor(new Color(r, g, b));
        }

        else if (command.getClass().equals(StyleCommand.class)) {
            Style style = ((StyleCommand) command).style;
            drawer.SetStyle(style);
        }

        else if (command.getClass().equals(SetVarCommand.class)) {
            SetVarCommand cmd = ((SetVarCommand) command);
            VariableDictionary.SetValueOf(cmd.variableName, cmd.value);
        }
        else if (command.getClass().equals(IncIntCommand.class)) {
            IncIntCommand cmd = ((IncIntCommand) command);
            System.out.println("val name : " + cmd.valName);
            int prev_val = VariableDictionary.GetValueOf(cmd.valName).value;
            int value = VariableDictionary.GetValueOf(cmd.variableName).value;
            VariableDictionary.SetValueOf(cmd.variableName, prev_val + value);
        }
    }
}
