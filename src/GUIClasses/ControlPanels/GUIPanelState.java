package GUIClasses.ControlPanels;

import GUIClasses.GUIMainFrame;

import javax.swing.*;
import java.awt.*;

public class GUIPanelState
{
    //Поля
    private GUIMainFrame parent;
    private JPanel panelstate;
    private JLabel totalcost;

    //Конструктор
    public GUIPanelState(GUIMainFrame parent)
    {
        this.parent=parent;
        panelstate = new JPanel();
        this.parent.add(panelstate);
        panelstate.setBackground(new java.awt.Color(65, 132, 232));
        this.totalcost=new JLabel();
        this.totalcost.setText("");
        this.totalcost. setForeground(Color.white);
        this.totalcost.setHorizontalAlignment(SwingConstants.RIGHT);
        this.panelstate.add(totalcost,BorderLayout.EAST);
    }

    //Методы
    public void setBounds(int x, int y, int width, int height )
    {
        this.panelstate.setBounds(x,y,width,height);
        int tswidth = this.totalcost.getWidth();
        this.totalcost.setBounds(width-tswidth-50,0,tswidth,20);
    }

    public void setTotalCost(String cost)
    {
        this.totalcost.setText("Итого стоимость: "+cost);
    }
}
