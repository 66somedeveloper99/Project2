package MShape.MLang.Commands;

public class IncIntCommand implements ICommand {
    public final String variableName;
    public final String valName;
    public IncIntCommand(String variableName, String valName) {
        this.variableName = variableName;
        this.valName = valName;
    }

    @Override
    public String toString() {
        return "Increase var \"" + variableName + "\" : " + valName;
    }
}