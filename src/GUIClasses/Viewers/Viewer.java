package GUIClasses.Viewers;

import javax.swing.*;

public interface Viewer
{
    public void addChildPanel(JPanel childPanel);
    public boolean isEditable();
    public void setBounds(int top, int width);
    public void readMessgae(Message msg, Object obj);
    public void removeInnerPanel(JPanel removing);
}
