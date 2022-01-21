package MShape.MLang.Variables;

public class IntVariable {

    public int value;
    public final String name;

    public IntVariable(String name) {
        this.name = name;
    }

    public IntVariable(String name, int value) {
        this.name = name;
        this.value = value;
    }
}

