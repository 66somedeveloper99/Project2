package MShape.MLang.Commands;

public class SetVarCommand implements ICommand {
    public final String variableName;
    public final int value;

    public SetVarCommand(String variableName, int value) {
        this.variableName = variableName;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Set var \"" + variableName + "\" : " + value;
    }
}