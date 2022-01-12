package MShape.MLang.Commands;

import MShape.MLang.Variables.Variable;

public class SetVarCommand implements ICommand {
    public final Variable variable;
    public final Object value;
    public SetVarCommand(Variable variable, Object value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Set var \"" + variable.name + "\" : " + value.toString();
    }
}