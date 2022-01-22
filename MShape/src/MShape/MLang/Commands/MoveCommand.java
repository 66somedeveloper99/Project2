package MShape.MLang.Commands;

import javax.xml.bind.annotation.XmlNs;

import MShape.MLang.Variables.VariableDictionary;

public class MoveCommand implements ICommand {
    public final String xName, yName;
    public MoveCommand(String x, String y) {
        this.xName = x;
        this.yName = y;
    }

    @Override
    public String toString() {
        return "Move (" + xName + ", " + yName + ")";
    }

    @Override
    public float GetDelayTiem() {
        return 5 * (VariableDictionary.GetValueOf(xName).value + VariableDictionary.GetValueOf(yName).value);
    }

}