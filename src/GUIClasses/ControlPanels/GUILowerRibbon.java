package GUIClasses.ControlPanels;

import GUIClasses.GUIMainFrame;

import javax.swing.*;
import java.awt.*;

public class GUILowerRibbon
{
    //Поля
    private GUIMainFrame parent;
    private JPanel lowerRibbon;

    //Конструктор
    public GUILowerRibbon(GUIMainFrame parent)
    {
        this.parent=parent;
        lowerRibbon = new JPanel();
        parent.add(lowerRibbon);
        //docHeader.setBackground(new java.awt.Color(65, 132, 232));
        //lowerRibbon.setBackground(Color.ORANGE);
        lowerRibbon.setBackground(new java.awt.Color(19, 93, 126));
    }

    //Методы
    public void setBounds(int x, int y, int width, int hight )
    {
        this.lowerRibbon.setBounds(x,y,width,hight);
    }

}
