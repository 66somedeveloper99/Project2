package MShape.MLang.Commands;

public class DownCommand implements ICommand {
    @Override
    public String toString() {
        return "Down";
    }

    @Override
    public float GetDelayTiem() {
        return 200;
    }

 }
