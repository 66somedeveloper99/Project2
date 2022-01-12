package MShape.MLang.Commands;

public class MoveCommand implements ICommand {
    public final int x, y;
    public MoveCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Move ("+x+", "+y+")";
    }
}