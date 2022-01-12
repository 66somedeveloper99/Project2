package MShape.MLang.Variables;

public class IntVariable extends Variable {

    public int value;

    public IntVariable(String name) { super(name); }
    
    @Override
    public void SetValue(Object value) { this.value = (int) value; }
    @Override
    public Object GetValue() { return Integer.valueOf(value); }
}

