package MShape.MLang.Commands;

import MShape.Drawing.Drawer.Style;

public class StyleCommand implements ICommand {
    
    public final Style style;
    public StyleCommand(Style style) {
        this.style = style;
    }

    public StyleCommand(String styleStr) throws Exception {
        styleStr = styleStr.toUpperCase();
        if (styleStr.equals("SOLID"))
            this.style = Style.Solid;
        else if (styleStr.equals("DASHED"))
            this.style = Style.Dashed;
        else if (styleStr.equals("DOTTED"))
            this.style = Style.Dotted;
        else
            throw new Exception("No familiar style is called " + styleStr);
    }
    
    
    @Override
    public float GetDelayTiem() {
        return 500;
    }

}