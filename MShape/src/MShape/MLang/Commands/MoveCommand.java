package MShape.MLang.Commands;

import MShape.MLang.Variables.IntVariable;

public class MoveCommand implements ICommand {
    public final String xName, yName;
    public MoveCommand(String x, String y) {
        this.xName = x;
        this.yName = y;
    }

    @Override
    public String toString() {
        return "Move ("+xName+", "+yName+")";
    }
}