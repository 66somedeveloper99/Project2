package MShape.MLang.Commands;

import MShape.MLang.Variables.Variable;

public class IncVarCommand implements ICommand {
    public final Variable variable;
    public final Object value;
    public IncVarCommand(Variable variable, Object value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Increase var \""+variable.name+"\" : "+value.toString();
    }
}