package MShape.MLang.Commands;

public class ColorCommand implements ICommand {
    public final String rName, gName, bName;
    public ColorCommand(String rName, String gName, String bName) {
        this.rName = rName;
        this.gName = gName;
        this.bName = bName;
    }
    @Override
    public String toString() {
        return "Set Color(" + rName + ", " + gName + ", " + bName + ")";
    }
    @Override
    public float GetDelayTiem() {
        return 500;
    }
}
