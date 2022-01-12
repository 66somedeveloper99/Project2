package MShape.Drawing;

import MShape.Drawing.Drawer.Style;
import MShape.MLang.Commands.ColorCommand;
import MShape.MLang.Commands.DownCommand;
import MShape.MLang.Commands.ICommand;
import MShape.MLang.Commands.MoveCommand;
import MShape.MLang.Commands.SizeCommand;
import MShape.MLang.Commands.StyleCommand;
import MShape.MLang.Commands.UpCommand;

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
        /*
         * it should call the appropriate method on the drawer variable
         * for example, if command is a DownCommand, it should call drawer.Down() like
         * below
         * 
         * if (command.getClass().equals(DownCommand.class)) {
         * drawer.Down();
         * }
         * 
         * if the method requires variables, we should get the variables from the
         * command with casting
         * for example, in the below example we're checking if the command is
         * SizeCommand. if it is, we get it's size
         * 
         * if (command.getClass().equals(SizeCommand.class)) {
         * int size = ((SizeCommand) command).size;
         * }
         */

        // the solution :

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
            int x = ((MoveCommand) command).x;
            int y = ((MoveCommand) command).y;
            drawer.Move(x, y);
        }

        else if (command.getClass().equals(ColorCommand.class)) {
            ColorCommand cmd = (ColorCommand) command;
            drawer.SetColor(cmd.color);
        }

        else if (command.getClass().equals(StyleCommand.class)) {
            Style style = ((StyleCommand) command).style;
            drawer.SetStyle(style);
        }
    }
}
