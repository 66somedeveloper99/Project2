package MShape.MLang.Variables;

import java.util.HashMap;
import java.util.Map;

public class VariableDictionary {
    private VariableDictionary() {} // it should not have any local instances
    
    
    static Map<String, IntVariable> variables = new HashMap<String, IntVariable>();

    static public IntVariable GetValueOf(String name) {
        return variables.get(name);
    }

    static public void SetValueOf(String name, int value) {
        IntVariable var = variables.get(name);
        if (var != null)
            var.value = value;
        else
            variables.put(name, new IntVariable(name, value));
    }

    static public boolean exists(String name) {
        return variables.get(name) != null;
    }
}
