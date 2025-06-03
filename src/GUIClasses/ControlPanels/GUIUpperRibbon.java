package GUIClasses.ControlPanels;

import GUIClasses.GUIMainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIUpperRibbon extends JFrame implements ActionListener
{

    private int height, width;
    private GUIMainFrame parent;
    private JPanel upperRibbonPanel;
    private JButton main, edit, document, parametrs;
    //private GUIClasses.ControlPanels.GUIMiddleRibbonState mrs;

    public GUIUpperRibbon(GUIMainFrame parent)
    {
        //Dimension parentsize = parent.getBounds().getSize();

        this.upperRibbonPanel = new JPanel();
        this.parent = parent;
        this.parent.add(upperRibbonPanel);
        upperRibbonPanel.setBackground(new java.awt.Color(65, 132, 232));
        //this.mrs=GUIClasses.ControlPanels.GUIMiddleRibbonState.MAIN;


        main = new JButton("Главная");
        upperRibbonPanel.add(main);
        main.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println("MAIN BUTTON pressed");
                //mrs=GUIClasses.ControlPanels.GUIMiddleRibbonState.MAIN;
                //parent.setVisible(false);
                //parent.setVisible(true);
                parent.setMainMode();
            }
        }
        );

        edit = new JButton("Редактирование");
        upperRibbonPanel.add(edit);
        edit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //mrs=GUIClasses.ControlPanels.GUIMiddleRibbonState.EDIT;
                //System.out.println("EDIT BUTTON pressed");
                parent.setEditMode();
            }
        }
        );

        document = new JButton("Документ");
        upperRibbonPanel.add(document);
        document.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //mrs=GUIClasses.ControlPanels.GUIMiddleRibbonState.DOC;
                //System.out.println("DOC BUTTON pressed");
                parent.setDocMode();
            }
        }
        );

        parametrs = new JButton("Параметры");
        upperRibbonPanel.add(parametrs);
        parametrs.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println("PARAM BUTTON pressed");
                //mrs=GUIClasses.ControlPanels.GUIMiddleRibbonState.PARAM;
                parent.setParamMode();
            }
        }
        );
        //this.reSize();
    }

    public void actionPerformed(ActionEvent e)
    {
        System.out.println(e);
    }

//    public void reSize()
//    {
//        Dimension parentsize = parent.getBounds().getSize();
//        //System.out.println("parentsize width="+parentsize.width+" parentsize height="+parentsize.height);
//        if ((int)(parentsize.height * 0.02)<20)
//        {
//            this.height=20;
//        }
//        else
//        {
//            this.height=(((int)(parentsize.height * 0.02))/10)*10;
//            //такая форма записи нужна, чтобы высота была кратна 10
//        }
//        System.out.println("upper ribbon height="+this.height);
//        upperRibbonPanel.setBounds(0,0,parentsize.width, this.height);
//        main.setBounds(2,2,100, this.height-4);
//        edit.setBounds(104,2,140, this.height-4);
//        document.setBounds(246,2,100, this.height-4);
//        parametrs.setBounds(348,2,120, this.height-4);
//    }

    public void setBounds(int x, int y, int width, int height )
    {
        this.height=height;
        this.width=width;

        upperRibbonPanel.setBounds(x,y,this.width, this.height);
        main.setBounds(2,2,100, this.height-4);
        edit.setBounds(104,2,140, this.height-4);
        document.setBounds(246,2,100, this.height-4);
        parametrs.setBounds(348,2,120, this.height-4);
    }

    public void reSize(int width, int height)
    {
        this.height=height;
        upperRibbonPanel.setBounds(0,0,width, this.height);
        main.setBounds(2,2,100, this.height-4);
        edit.setBounds(104,2,140, this.height-4);
        document.setBounds(246,2,100, this.height-4);
        parametrs.setBounds(348,2,120, this.height-4);
    }

    public int getHeight()
    {return this.height;}

    public JPanel getpannel()
    {return this.upperRibbonPanel;}



//    public GUIClasses.ControlPanels.GUIMiddleRibbonState getMrs()
//    {return this.mrs;}
}
