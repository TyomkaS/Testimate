package GUIClasses.Viewers;

import Backend.Resourses.IResourse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CalcResViewer
{
    private JPanel parentviewerbase;        //панель родитель, куда добавляется панель viewerbase
    private JPanel viewerbase;              //основная панель для размещения элементов
    private Viewer parent;
    private boolean isEditable;

    //Вертикальные координаты, для возможности прокручивания и отрисовки
    //Координата х всегда должна быть 0
    private int top, width;
    private boolean isCurrentPrice;
    private boolean isCollapsed;

    private JTextField resID;
    private JTextField resName;
    private JTextField resUOM;
    private JTextField normalCount;
    private JTextField coef;
    private JTextField totalCount;
    private JTextField singeCost;
    private JTextField singleWorkCost;
    private JTextField singleMachCost;
    private JTextField singleOperCost;
    private JTextField singleMatCost;
    private JTextField totalCost;
    private JTextField totalWorkCost;
    private JTextField totalMachCost;
    private JTextField totalOperCost;
    private JTextField totalMatCost;

    IResourse resourse;

    //Конструкторы
    public CalcResViewer(JPanel parent)
    {
        this.parentviewerbase=parent;
        this.viewerbase = new JPanel();
        this.parentviewerbase.add(viewerbase);
        this.viewerbase.setBackground(new java.awt.Color(15, 108, 98));
        resourse=null;
        this.isCurrentPrice=false;
        this.parent=null;
        this.addSelfComponent();

        this.setEditable(true);
    }

    public CalcResViewer(Viewer parent)
    {
        this.parent=parent;
        this.viewerbase = new JPanel();
        this.parent.addChildPanel(viewerbase);
        //this.viewerbase.setBackground(new java.awt.Color(15, 108, 98));
        resourse=null;
        this.isCurrentPrice=false;

        this.addSelfComponent();
        this.setEditable(parent.isEditable());
    }

    private void addSelfComponent()
    {
        this.resID = new JTextField();
        this.viewerbase.add(this.resID);

        this.resName = new JTextField();
        this.viewerbase.add(this.resName);

        this.resUOM = new JTextField();
        this.viewerbase.add(this.resUOM);

        this.normalCount  = new JTextField();
        this.viewerbase.add(this.normalCount);

        this.coef = new JTextField();
        this.viewerbase.add(this.coef);
        this.coef.setEditable(false);

        this.totalCount = new JTextField();
        this.viewerbase.add(this.totalCount);
        this.totalCount.setEditable(false);

        this.singeCost = new JTextField();
        this.viewerbase.add(this.singeCost);
        this.singeCost.setEditable(false);

        this.singleWorkCost = new JTextField();
        this.viewerbase.add(this.singleWorkCost);

        this.singleMachCost = new JTextField();
        this.viewerbase.add(this.singleMachCost);

        this.singleOperCost = new JTextField();
        this.viewerbase.add(this.singleOperCost);

        this.singleMatCost = new JTextField();
        this.viewerbase.add(this.singleMatCost);

        this.totalCost = new JTextField();
        this.viewerbase.add(this.totalCost);
        this.totalCost.setEditable(false);

        this.totalWorkCost = new JTextField();
        this.viewerbase.add(this.totalWorkCost);
        this.totalWorkCost.setEditable(false);

        this.totalMachCost = new JTextField();
        this.viewerbase.add(this.totalMachCost);
        this.totalMachCost.setEditable(false);

        this.totalOperCost = new JTextField();
        this.viewerbase.add(this.totalOperCost);
        this.totalOperCost.setEditable(false);

        this.totalMatCost = new JTextField();
        this.viewerbase.add(this.totalMatCost);
        this.totalMatCost.setEditable(false);

        this.fillTextAreas();

        this.resID.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if (resourse!=null) {
                            resourse.setID(resID.getText());
                            fillTextAreas();
                        }
                    }
                }
        );
        this.resID.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.resName.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if (resourse!=null) {
                            resourse.setName(resName.getText());
                            fillTextAreas();
                        }
                    }
                }
        );
        this.resName.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.resUOM.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if (resourse!=null) {
                            resourse.setUom(resUOM.getText());
                            fillTextAreas();
                        }
                    }
                }
        );
        this.resUOM.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.normalCount.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        double value = Double.parseDouble(normalCount.getText());
                        if (resourse!=null)
                        {
                            resourse.setNormalCount(value);
                            fillTextAreas();
                            sendMassage(Message.UPDATE,null);
                        }
                    }
                }
        );
        this.normalCount.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.totalCount.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        double value = Double.parseDouble(totalCount.getText());
                        if (resourse!=null)
                        {
                            resourse.setCalcTotalCount(value);
                            fillTextAreas();
                            sendMassage(Message.UPDATE,null);
                        }
                    }
                }
        );
        this.totalCount.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.singleWorkCost.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        double value = Double.parseDouble(singleWorkCost.getText());
                        if (resourse!=null)
                        {
                            resourse.setSingleWorkprice(value, isCurrentPrice);
                            fillTextAreas();
                            sendMassage(Message.UPDATE,null);
                        }
                    }
                }
        );
        this.singleWorkCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.singleMachCost.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        double value = Double.parseDouble(singleMachCost.getText());
                        if (resourse!=null)
                        {
                            resourse.setSingleMachprice(value, isCurrentPrice);
                            fillTextAreas();
                            sendMassage(Message.UPDATE,null);
                        }
                    }
                }
        );
        this.singleMachCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.singleOperCost.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        double value = Double.parseDouble(singleOperCost.getText());
                        if (resourse!=null)
                        {
                            resourse.setSingleOperprice(value, isCurrentPrice);
                            fillTextAreas();
                            sendMassage(Message.UPDATE,null);
                        }
                    }
                }
        );
        this.singleOperCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.singleMatCost.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        double value = Double.parseDouble(singleMatCost.getText());
                        if (resourse!=null)
                        {
                            resourse.setSingleMatprice(value, isCurrentPrice);
                            fillTextAreas();
                            sendMassage(Message.UPDATE,null);
                        }
                    }
                }
        );
        this.singleMatCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );
    }


    public void collapse()
    {
        this.isCollapsed=true;
        this.viewerbase.setBounds(0,0,0,0);
    }

    public boolean isEditable(){return this.isEditable;}

    public boolean getIsCurrentPrice() {return this.isCurrentPrice;}

    public int getBottom()
    {
        if (isCollapsed)
        {
            return this.top;
        }
        else
        {
            int tmp=this.top+20;
            return tmp;
        }
    };

    public Viewer getParent(){return this.parent;}

    public int getWidth(){return this.width;}

    public int getTop(){return this.top;}

    public IResourse getSource(){return this.resourse;}

    public void fillTextAreas()
    {
        if (this.resourse==null)
        {
            this.resID.setText("ID");
            this.resName.setText("Name");
            this.resUOM.setText("UOM");

            this.normalCount.setText("Ncount");
            this.coef.setText("Coef");
            this.totalCount.setText("Tcount");

            this.singeCost.setText("SC");
            this.singleWorkCost.setText("SWC");
            this.singleMachCost.setText("SMexC");
            this.singleOperCost.setText("SOpC");
            this.singleMatCost.setText("SMatC");

            this.totalCost.setText("TP");
            this.totalWorkCost.setText("TWP");
            this.totalMachCost.setText("TMexP");
            this.totalOperCost.setText("TOpP");
            this.totalMatCost.setText("TMatP");
        }
        else
        {
            this.resID.setText(this.resourse.getID());
            this.resName.setText(this.resourse.getName());
            this.resUOM.setText(this.resourse.getUom());

            this.normalCount.setText(""+this.resourse.getNormalCount());
            this.coef.setText(""+this.resourse.getCoef());
            this.totalCount.setText(""+this.resourse.getCalcTotalCount());

            this.singeCost.setText(""+this.resourse.getSinglePrice(this.isCurrentPrice));
            this.singleWorkCost.setText(""+this.resourse.getSingleWorkprice(this.isCurrentPrice));
            this.singleMachCost.setText(""+this.resourse.getSingleMachprice(this.isCurrentPrice));
            this.singleOperCost.setText(""+this.resourse.getSingleOperprice(this.isCurrentPrice));
            this.singleMatCost.setText(""+this.resourse.getSingleMatprice(this.isCurrentPrice));

            this.totalCost.setText(""+this.resourse.getTotalPrice(this.isCurrentPrice));
            this.totalWorkCost.setText(""+this.resourse.getTotalWorkprice(this.isCurrentPrice));
            this.totalMachCost.setText(""+this.resourse.getTotalMachprice(this.isCurrentPrice));
            this.totalOperCost.setText(""+this.resourse.getTotalOperprice(this.isCurrentPrice));
            this.totalMatCost.setText(""+this.resourse.getTotalMatprice(this.isCurrentPrice));
        }
    }

    public void selfDistruct()
    {

        if (this.parent!=null)
        {
            this.parent.removeInnerPanel(this.viewerbase);
        }
        this.resourse=null;
        this.viewerbase.removeAll();
    }

    public void sendMassage(Message msg, Object obj)
    {
        if (this.parent!=null)
        {this.parent.readMessgae(msg,obj);}
    }

    public void sendActivationMessage()
    {
        //Этот метод сделан, чтобы отправлять сообщения родителю из FocusListener
        if (this.parent!=null)
        {
            System.out.println("sendActivationMessgae"+this);
            this.parent.readMessgae(Message.DisActPrice,null);
            this.parent.readMessgae(Message.ResActive,this);
        }
    }

    public void setBounds(int top, int width)
    {
        this.top=top;
        this.width=width;
        this.viewerbase.setBounds(0,this.top,this.width,20);

        int resNameWidth=this.width-0-14*45-75;
        int afetrNameHorizBegin=resNameWidth+75;
        int afetrNameStep=45;

        this.resID.setBounds(0,0,75,20);
        this.resName.setBounds(75,0,resNameWidth,20);

        this.resUOM.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.normalCount.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.coef.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalCount.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.singeCost.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleWorkCost.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleMachCost.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleOperCost.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleMatCost.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalCost.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalWorkCost.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalMachCost.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalOperCost.setBounds(afetrNameHorizBegin,0,45,20);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalMatCost.setBounds(afetrNameHorizBegin,0,45,20);
    }

    public void setEditable(boolean isEditable)
    {
        this.isEditable=isEditable;

        this.resID.setEditable(this.isEditable);
        this.resName.setEditable(this.isEditable);
        this.resUOM.setEditable(this.isEditable);
        this.normalCount.setEditable(this.isEditable);
        this.totalCount.setEditable(this.isEditable);
        this.singleWorkCost.setEditable(this.isEditable);
        this.singleMachCost.setEditable(this.isEditable);
        this.singleOperCost.setEditable(this.isEditable);
        this.singleMatCost.setEditable(this.isEditable);
    }

    public void setIsCurrentPrice(boolean isCurrentPrice)
    {
        this.isCurrentPrice=isCurrentPrice;
    }

    public void setResourse(IResourse res)
    {
        this.resourse=res;
        this.fillTextAreas();
        //this.viewerbase.revalidate();
    }

    public void setWidth(int width){this.width=width;}

    public void setTop(int top){this.top=top;}

    public void uncollapse()
    {
        this.isCollapsed=false;
        this.setBounds(this.top,this.width);
    }

}
