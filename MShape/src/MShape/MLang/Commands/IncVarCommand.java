package MShape.MLang.Commands;

public class IncVarCommand implements ICommand {
    public final String variableName;
    public final Object value;
    public IncVarCommand(String variableName, Object value) {
        this.variableName = variableName;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Increase var \"" + variableName + "\" : " + value;
    }
}