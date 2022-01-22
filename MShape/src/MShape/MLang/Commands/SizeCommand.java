package MShape.MLang.Commands;

public class SizeCommand implements ICommand {
    public final String sizeName;
    public SizeCommand(String sizeName) {
        this.sizeName = sizeName;
    }

    @Override
    public String toString() {
        return "Size "+sizeName;
    }
}