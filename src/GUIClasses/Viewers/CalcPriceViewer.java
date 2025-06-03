package GUIClasses.Viewers;

import Backend.Prices.CalcPrice;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CalcPriceViewer implements Viewer
{
    private CalcPriceViewer next;
    private CalcViewer parent;
    private JPanel parentviewerbase;            //панель родитель, куда добавляется панель viewerbase
    private JPanel viewerbase;                  //основная панель для размещения элементов

    //Вертикальные координаты, для возможности прокручивания и отрисовки
    //Координата х всегда должна быть 0
    private int top, height, width;
    private boolean isCurrentPrice;
    private boolean isCollapsed;
    private boolean isEditable;

    private JTextArea priceID;
    private JTextArea priceName;
    private JTextArea priceUOM;
    private JTextArea priceCount;
    private JTextArea singeCost;
    private JTextArea singleWorkCost;
    private JTextArea singleMachCost;
    private JTextArea singleOperCost;
    private JTextArea singleMatCost;
    private JTextArea totalCost;
    private JTextArea totalWorkCost;
    private JTextArea totalMachCost;
    private JTextArea totalOperCost;
    private JTextArea totalMatCost;

    private JScrollPane idSP;
    private JScrollPane nameSP;
    private JScrollPane uomSP;
    private JScrollPane countSP;
    private JScrollPane singeCostSP;
    private JScrollPane singleWorkCostSP;
    private JScrollPane singleMachCostSP;
    private JScrollPane singleOperCostSP;
    private JScrollPane singleMatCostSP;
    private JScrollPane totalCostSP;
    private JScrollPane totalWorkCostSP;
    private JScrollPane totalMachCostSP;
    private JScrollPane totalOperCostSP;
    private JScrollPane totalMatCostSP;

    private JButton colapseButton;
    private JButton uncolapseButton;

    private CalcPrice price;

    private ArrayList<CalcResViewer> elviCol;

    //Конструкторы
    public CalcPriceViewer(JPanel parentviewerbase)
    {
        //Блок основных параметров
        this.parentviewerbase=parentviewerbase;
        this.viewerbase = new JPanel();
        this.parentviewerbase.add(viewerbase);
        this.viewerbase.setBackground(new java.awt.Color(230, 170, 17));
        this.price = null;
        this.isCurrentPrice=false;
        this.height=60;
        this.next=null;
        this.parent=null;
        this.isEditable=true;
        this.addSelfComponent();

    }

    public CalcPriceViewer(CalcViewer parent)
    {
        //Блок основных параметров
        this.parent=parent;
        this.viewerbase = new JPanel();
        this.parent.addChildPanel(viewerbase);
        //this.viewerbase.setBackground(new java.awt.Color(230, 170, 17));
        this.price = null;
        this.isCurrentPrice=false;
        this.height=60;
        this.next=null;
        this.isEditable=this.parent.isEditable();
        this.addSelfComponent();
    }

    public void addChildPanel(JPanel childPanel)
    {
        this.viewerbase.add(childPanel);
    }

    private void addSelfComponent()
    {
        this.viewerbase.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();
                    }

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        //Блок Collaps
        this.isCollapsed=true;
        this.colapseButton=new JButton("-");
        this.viewerbase.add(this.colapseButton);
        this.uncolapseButton = new JButton("+");
        this.viewerbase.add(this.uncolapseButton);

        this.colapseButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        collapse();
                    }
                }
        );
        this.colapseButton.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();
                    }

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.uncolapseButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        uncollapce();
                    }
                }
        );
        this.uncolapseButton.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();
                    }

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        //Блок полей с данными
        this.priceID = new JTextArea(3,11);
        this.priceID.setLineWrap(true);
        this.priceID.setWrapStyleWord(true);
        this.idSP = new JScrollPane(this.priceID);
        this.viewerbase.add(this.idSP);
        this.priceID.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();
                    }

                    @Override
                    public void focusLost(FocusEvent e)
                    {
                        if(price!=null)
                        {
                            JTextArea tmp=(JTextArea)e.getSource();
                            price.setID(tmp.getText());
                            update();
                        }
                    }
                }
        );
        this.priceID.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e)
                    {
                        String kchar = String.valueOf(e.getKeyText(e.getKeyCode()));
                        if (kchar.equals("Enter")) {viewerbase.requestFocus();}
                    }
                }
        );


        this.priceName = new JTextArea(3,29);
        this.priceName.setLineWrap(true);
        this.priceName.setWrapStyleWord(true);
        this.priceName.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();
                    }

                    @Override
                    public void focusLost(FocusEvent e)
                    {
                        if(price!=null)
                        {
                            JTextArea tmp=(JTextArea)e.getSource();
                            price.setName(tmp.getText());
                            update();
                        }
                    }
                }
        );
        this.priceName.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e)
                    {
                        String kchar = String.valueOf(e.getKeyText(e.getKeyCode()));
                        if (kchar.equals("Enter")) {viewerbase.requestFocus();}
                    }
                }
        );
        this.nameSP=new JScrollPane(this.priceName);
        this.viewerbase.add(this.nameSP);

        this.priceUOM = new JTextArea(3,5);
        this.priceUOM.setLineWrap(true);
        this.priceUOM.setWrapStyleWord(true);
        this.priceUOM.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();
                    }

                    @Override
                    public void focusLost(FocusEvent e)
                    {
                        if(price!=null)
                        {
                            JTextArea tmp=(JTextArea)e.getSource();
                            price.setUom(tmp.getText());
                            update();
                        }
                    }
                }
        );
        this.priceUOM.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e)
                    {
                        String kchar = String.valueOf(e.getKeyText(e.getKeyCode()));
                        if (kchar.equals("Enter")) {viewerbase.requestFocus();}
                    }
                }
        );
        this.uomSP = new JScrollPane(this.priceUOM);
        this.viewerbase.add(this.uomSP);


        this.priceCount  = new JTextArea(3,12);
        this.priceCount.setLineWrap(true);
        this.priceCount.setWrapStyleWord(true);
        this.priceCount.addFocusListener(
                new FocusListener()
                {
                    private String textbefore;
                    private String textafter;
                    double textcount;
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        textbefore = priceCount.getText();
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();
                    }

                    @Override
                    public void focusLost(FocusEvent e)
                    {
                        //System.out.printf("Focus Lost worked" +"\n");
                        String textafter = priceCount.getText();
                        if (!textbefore.equals(textafter))
                        {
                            try
                            {
                                textcount = Double.parseDouble(textafter);
                                price.setCount(textcount);
                                priceCount.setText(""+price.getCount());

                                fillTextAreas();

                                if (elviCol!=null)
                                {
                                    for(CalcResViewer item : elviCol)
                                    {
                                        item.fillTextAreas();
                                    }
                                }
                                sendMassage(Message.UPDATE,null);

                            }
                            catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null, "данные должны быть числом");
                                textcount = Double.parseDouble(textafter);
                                price.setCount(textcount);
                                priceCount.setText(""+price.getCount());
                            }
                        }
                    }
                }
        );
        this.priceCount.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e)
                    {
                        //String text=priceCount.getText();
                        String kchar = String.valueOf(e.getKeyText(e.getKeyCode()));

                        if (kchar.equals("Enter")) {viewerbase.requestFocus();}
                    }
                }
        );
        this.countSP = new JScrollPane(this.priceCount);
        this.viewerbase.add(this.countSP);

        this.singeCost = new JTextArea(3,5);
        this.singeCost.setLineWrap(true);
        this.singeCost.setWrapStyleWord(true);
        this.singeCost.setEditable(false);
        this.singeCostSP = new JScrollPane(this.singeCost);
        this.viewerbase.add(this.singeCostSP);
        this.singeCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.singleWorkCost = new JTextArea(3,5);
        this.singleWorkCost.setLineWrap(true);
        this.singleWorkCost.setWrapStyleWord(true);
        this.singleWorkCost.setEditable(false);
        this.singleWorkCostSP = new JScrollPane(this.singleWorkCost);
        this.viewerbase.add(this.singleWorkCostSP);
        this.singleWorkCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );


        this.singleMachCost = new JTextArea(3,5);
        this.singleMachCost.setLineWrap(true);
        this.singleMachCost.setWrapStyleWord(true);
        this.singleMachCost.setEditable(false);
        this.singleMachCostSP = new JScrollPane(this.singleMachCost);
        this.viewerbase.add(this.singleMachCostSP);
        this.singleMachCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.singleOperCost = new JTextArea(3,5);
        this.singleOperCost.setLineWrap(true);
        this.singleOperCost.setWrapStyleWord(true);
        this.singleOperCost.setEditable(false);
        this.singleOperCostSP = new JScrollPane(this.singleOperCost);
        this.viewerbase.add(this.singleOperCostSP);
        this.singleOperCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.singleMatCost = new JTextArea(3,5);
        this.singleMatCost.setLineWrap(true);
        this.singleMatCost.setWrapStyleWord(true);
        this.singleMatCost.setEditable(false);
        this.singleMatCostSP = new JScrollPane(this.singleMatCost);
        this.viewerbase.add(this.singleMatCostSP);
        this.singleMatCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.totalCost = new JTextArea(3,5);
        this.totalCost.setLineWrap(true);
        this.totalCost.setWrapStyleWord(true);
        this.totalCost.setEditable(false);
        this.totalCostSP = new JScrollPane(this.totalCost);
        this.viewerbase.add(this.totalCostSP);
        this.totalCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.totalWorkCost = new JTextArea(3,5);
        this.totalWorkCost.setLineWrap(true);
        this.totalWorkCost.setWrapStyleWord(true);
        this.totalWorkCost.setEditable(false);
        this.totalWorkCostSP = new JScrollPane(this.totalWorkCost);
        this.viewerbase.add(this.totalWorkCostSP);
        this.totalWorkCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.totalMachCost = new JTextArea(3,5);
        this.totalMachCost.setLineWrap(true);
        this.totalMachCost.setWrapStyleWord(true);
        this.totalMachCost.setEditable(false);
        this.totalMachCostSP = new JScrollPane(this.totalMachCost);
        this.viewerbase.add(this.totalMachCostSP);
        this.totalMachCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.totalOperCost = new JTextArea(3,5);
        this.totalOperCost.setLineWrap(true);
        this.totalOperCost.setWrapStyleWord(true);
        this.totalOperCost.setEditable(false);
        this.totalOperCostSP = new JScrollPane(this.totalOperCost);
        this.viewerbase.add(this.totalOperCostSP);
        this.totalOperCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.totalMatCost = new JTextArea(3,5);
        this.totalMatCost.setLineWrap(true);
        this.totalMatCost.setWrapStyleWord(true);
        this.totalMatCost.setEditable(false);
        this.totalMatCostSP = new JScrollPane(this.totalMatCost);
        this.viewerbase.add(this.totalMatCostSP);
        this.totalMatCost.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.elviCol=null;          //Коллекция ElementVier для расценки, инициализация в методе setPrice
        this.fillTextAreas();
        this.viewerbase.revalidate();//Перерисовка viewerbase для корректного отображения полей с данными
    }

    public void deleteNext()
    {this.next=null;}

    public void collapse()
    {
        this.isCollapsed=true;
        this.setBounds(this.top,this.width);
        this.sendMassage(Message.COLLAPSE,null);
    }

    public boolean isEditable(){return this.isEditable;}

    public boolean getIsCurrentPrice() {return this.isCurrentPrice;}

    public int getBottom()
    {
        return this.top+this.height;
    };

    public CalcPrice getSource(){return this.price;}

    public int getTop(){return this.top;};

    private void fillTextAreas()
    {
        if (this.price==null)
        {
            this.priceID.setText("ID");
            this.priceName.setText("Name");
            this.priceUOM.setText("UOM");

            this.priceCount.setText("count");

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
            this.priceID.setText(this.price.getID());
            this.priceName.setText(this.price.getName());
            this.priceUOM.setText(this.price.getUom());

            this.priceCount.setText(""+this.price.getCount());

            this.singeCost.setText(""+this.price.getSinglePrice(this.isCurrentPrice));
            this.singleWorkCost.setText(""+this.price.getSingleWorkprice(this.isCurrentPrice));
            this.singleMachCost.setText(""+this.price.getSingleMachprice(this.isCurrentPrice));
            this.singleOperCost.setText(""+this.price.getSingleOperprice(this.isCurrentPrice));
            this.singleMatCost.setText(""+this.price.getSingleMatprice(this.isCurrentPrice));

            this.totalCost.setText(""+this.price.getTotalPrice(this.isCurrentPrice));
            this.totalWorkCost.setText(""+this.price.getTotalWorkprice(this.isCurrentPrice));
            this.totalMachCost.setText(""+this.price.getTotalMachprice(this.isCurrentPrice));
            this.totalOperCost.setText(""+this.price.getTotalOperprice(this.isCurrentPrice));
            this.totalMatCost.setText(""+this.price.getTotalMatprice(this.isCurrentPrice));
        }
    }

    private void fillElviCol() {
        if (this.elviCol != null)
        {
            //Удаляяются существующие
            for (int i = 0; i < this.elviCol.size(); i++) {
                this.elviCol.get(i).selfDistruct();
            }
            //Список ElementsViewer обнуляется, для нового заполнения
            this.elviCol = null;

        }

        //Определяется количество ресурсов
        int resCount=this.price.getWorkersCount()+this.price.getMachineCount()+this.price.getMaterialCount();
        if (resCount!=0)
        {

            //Создаётся новая коллекция ElementViewer
            this.elviCol=new ArrayList<>(resCount);

            //Заполняется коллекция ElementViewer, и каждый элемент связывается с соответвующим ресурсом
            for (int i=0; i<resCount;i++)
            {
                CalcResViewer tmp=new CalcResViewer(this);
                tmp.setResourse(this.price.getResourse(i));
                this.elviCol.add(tmp);
            }
        }
    }

    public void removeAll()
    {
        this.price=null;
        if (this.elviCol!=null)
        {
            for (CalcResViewer item : this.elviCol){item.selfDistruct();}
        }
    }

    public void readMessgae(Message msg, Object obj)
    {
        switch (msg)
        {
            case UPDATE:
                this.fillTextAreas();
                this.sendMassage(Message.UPDATE,null);
                break;
            case ResActive:
                this.sendMassage(msg,obj);
                break;
            case DisActPrice:
                this.sendMassage(msg,obj);
                break;
            default:
                break;
        }
    }

    public void selfDistruct()
    {

        this.removeAll();
        if (this.parent!=null)
        {
            this.parent.removeInnerPanel(this.viewerbase);
        }
        this.price=null;
        this.viewerbase.removeAll();
    }

    public void removeInnerPanel(JPanel removing)
    {
        this.viewerbase.remove(removing);
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
            this.parent.readMessgae(Message.PriceActive,this);
        }
    }

    public void setBounds(int top, int width)
    {
        this.height=60;
        this.top=top;
        this.width=width;

        int priceNameWidth=this.width-0-14*45-75;
        int afetrNameHorizBegin=priceNameWidth+75;
        int afetrNameStep=45;
        
        this.idSP.setBounds(0,0,75,30);
        this.nameSP.setBounds(75,0,priceNameWidth,60);
        this.uomSP.setBounds(afetrNameHorizBegin,0,45,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.countSP.setBounds(afetrNameHorizBegin,0,135,60);
        afetrNameHorizBegin+=afetrNameStep*3; //*3 потому что ширина priceCount 45*3=135 px
        this.singeCostSP.setBounds(afetrNameHorizBegin,0,45,60);
        afetrNameHorizBegin+=afetrNameStep;

        this.singleWorkCostSP.setBounds(afetrNameHorizBegin,0,45,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleMachCostSP.setBounds(afetrNameHorizBegin,0,45,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleOperCostSP.setBounds(afetrNameHorizBegin,0,45,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleMatCostSP.setBounds(afetrNameHorizBegin,0,45,60);
        afetrNameHorizBegin+=afetrNameStep;

        this.totalCostSP.setBounds(afetrNameHorizBegin,0,45,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalWorkCostSP.setBounds(afetrNameHorizBegin,0,45,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalMachCostSP.setBounds(afetrNameHorizBegin,0,45,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalOperCostSP.setBounds(afetrNameHorizBegin,0,45,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalMatCostSP.setBounds(afetrNameHorizBegin,0,45,60);

        //Добавляются ElementViewer
        if (!isCollapsed)   //isColapsed=false -> !isColapsed==true     сработает,если значение false
        {

            this.colapseButton.setBounds(14,32,40,25);
            this.uncolapseButton.setBounds(0,0,0,0);
            if (this.elviCol!=null)
            {
                for (int i=0; i<this.elviCol.size();i++)
                {
                    CalcResViewer tmp=this.elviCol.get(i);
                    tmp.setWidth(this.width);
                    tmp.setTop(this.height);
                    tmp.uncollapse();
                    this.height=tmp.getBottom();
                }
            }
        }
        else
        {
            this.colapseButton.setBounds(0,0,0,0);
            this.uncolapseButton.setBounds(14,32,45,25);

            if (this.elviCol!=null)
            {
                for (int i=0; i<this.elviCol.size();i++)
                {
                    this.elviCol.get(i).collapse();
                }
            }
            this.height=60;
        }

        this.viewerbase.setBounds(0,this.top, this.width,this.height);
        if (this.next!=null)
        {
            this.next.setBounds(this.getBottom(),this.width);
        }

    }

    public void setIsCurrentPrice(boolean isCurrentPrice)
    {
        this.isCurrentPrice=isCurrentPrice;
    }

    public void setEditable(boolean isEditable){this.isEditable=isEditable;}

    public void setNext(CalcPriceViewer next)
    {
        this.next=next;
    }

    public void setPrice(CalcPrice otherPrice)
    {
        this.price=otherPrice;
        this.fillTextAreas();
        if (this.elviCol!=null)
        {
            //Список ElementsViewer обнуляется, для нового заполнения
            this.elviCol=null;
        }

        //Определяется количество ресурсов
        int resCount=this.price.getWorkersCount()+this.price.getMachineCount()+this.price.getMaterialCount();
        if (resCount!=0)
        {

            //Создаётся новая коллекция ElementViewer
            this.elviCol=new ArrayList<>(resCount);

            //Заполняется коллекция ElementViewer, и каждый элемент связывается с соответвующим ресурсом
            for (int i=0; i<resCount;i++)
            {
                CalcResViewer tmp=new CalcResViewer(this);
                tmp.setResourse(this.price.getResourse(i));
                tmp.setEditable(this.isEditable);
                this.elviCol.add(tmp);
            }
        }
    }

    public void wrap()
    {
        this.viewerbase.setBounds(0,0,0,0);
    }

    public void uncollapce()
    {
        this.isCollapsed=false;
        this.setBounds(this.top,this.width);
        this.sendMassage(Message.UNCOLLAPSE,null);
    }

    public void update()
    {
        this.fillTextAreas();
        if (this.elviCol!=null)
        {
            for(CalcResViewer item : this.elviCol)
            {
                item.fillTextAreas();
            }
        }
    }

    public void updateIresourses()
    {
        int neededResCount=this.price.getWorkersCount()+this.price.getMachineCount()+this.price.getMaterialCount();
        if(neededResCount>0)
        {
            if (this.elviCol!=null)
            {
                if (this.elviCol.size()!=neededResCount)
                {
                    if (this.elviCol.size()>neededResCount)
                    {
                        //соответсвующие Viewers связываются с соответсвующими Iresourse
                        for (int i = 0; i < neededResCount; i++)
                        {
                            this.elviCol.get(i).setResourse(this.price.getResourse(i));
                        }

                        //Удаляются лишние
                        for (int i = neededResCount; i < this.elviCol.size(); i++)
                        {
                            this.elviCol.get(i).selfDistruct();
                            this.elviCol.remove(i);
                        }
                        this.setBounds(this.top,this.width);
                    }
                    else
                    {
                        //соответсвующие Viewers связываются с соответсвующими Iresourse
                        for (int i = 0; i < this.elviCol.size(); i++)
                        {
                            this.elviCol.get(i).setResourse(this.price.getResourse(i));
                            this.elviCol.get(i).setEditable(this.isEditable);
                        }

                        int oldsize=this.elviCol.size();

                        //добавляются недостающие CalcResViewer
                        for (int i = oldsize; i < neededResCount; i++)
                        {
                            CalcResViewer tmp =new CalcResViewer(this);
                            tmp.setResourse(this.price.getResourse(i));
                            tmp.setEditable(this.isEditable);
                            this.elviCol.add(tmp);
                        }

                        this.setBounds(this.top,this.width);
                    }
                }
                else
                {
                    //соответсвующие Viewers связываются с соответсвующими Iresourse
                    for (int i = 0; i < neededResCount; i++)
                    {
                        this.elviCol.get(i).setResourse(this.price.getResourse(i));
                        this.elviCol.get(i).setEditable(this.isEditable);
                    }
                }
            }
            else
            {
                this.fillElviCol();
                this.setBounds(this.top,this.width);
            }

        }
        else
        {
            //Если необходимое количество ресурсво 0, тогда проверить,что их 0, либо очистить
            if (this.elviCol!=null)
            {
                for(CalcResViewer item : this.elviCol){item.selfDistruct();}
            }
            this.elviCol=null;
            this.setBounds(this.top,this.width);
        }
    }
}
