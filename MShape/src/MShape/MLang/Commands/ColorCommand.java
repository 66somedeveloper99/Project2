package MShape.MLang.Commands;

import java.awt.*;

public class ColorCommand implements ICommand {
    public final Color color;
    public ColorCommand(Color color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return "Set Color("+color.toString()+")";
    }
}
