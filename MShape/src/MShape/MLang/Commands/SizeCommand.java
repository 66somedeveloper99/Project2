package MShape.MLang.Commands;

public class SizeCommand implements ICommand {
    public final int size;
    public SizeCommand(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Size "+size;
    }
}