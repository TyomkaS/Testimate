package GUIClasses.Viewers;

import Backend.Documents.BookPart;
import Backend.Prices.DBPrice;
import Backend.Resourses.IResourse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DBPriceViewer implements Viewer
{
    private DBPriceViewer next;
    private BookPartViewer parent;
    private JPanel parentviewerbase;            //панель родитель, куда добавляется панель viewerbase
    private JPanel viewerbase;                  //основная панель для размещения элементов
    private boolean isCollapsed;
    private boolean isWraped;
    private boolean isEditable;

    //Вертикальные координаты, для возможности прокручивания и отрисовки
    //Координата х всегда должна быть 0
    private int top;
    private int height;
    private int width;

    private JTextArea priceID;
    private JTextArea priceName;
    private JTextArea priceUOM;
    //private JTextArea priceCount;
    private JTextArea singeCost;
    private JTextArea singleWorkCost;
    private JTextArea singleMachCost;
    private JTextArea singleOperCost;
    private JTextArea singleMatCost;

    private JScrollPane idSP;
    private JScrollPane nameSP;
    private JScrollPane uomSP;
    //private JScrollPane countSP;
    private JScrollPane singeCostSP;
    private JScrollPane singleWorkCostSP;
    private JScrollPane singleMachCostSP;
    private JScrollPane singleOperCostSP;
    private JScrollPane singleMatCostSP;

    private JPanel idP;
    private JPanel nameP;
    private JPanel uomP;
    //private JScrollPane countSP;
    private JPanel singeCostP;
    private JPanel singleWorkCostP;
    private JPanel singleMachCostP;
    private JPanel singleOperCostP;
    private JPanel singleMatCostP;



    private JButton colapseButton;
    private JButton uncolapseButton;

    private DBPrice price;

    private ArrayList<DBResViewer> elviCol;

    //Конструкторы
    public DBPriceViewer(JPanel parentviewerbase)
    {
        //Блок основных параметров
        this.parentviewerbase=parentviewerbase;
        this.viewerbase = new JPanel();
        this.parentviewerbase.add(viewerbase);
        this.viewerbase.setBackground(new java.awt.Color(159, 250, 7));
        this.price = null;
        this.height=60;
        this.next=null;
        this.parent=null;
        this.addSelfComponent();
        this.isEditable=true;
    }

    public DBPriceViewer(BookPartViewer parent)
    {
        //Блок основных параметров
        this.parent=parent;
        this.viewerbase = new JPanel();
        this.parent.addChildPanel(viewerbase);
        this.viewerbase.setBackground(new java.awt.Color(159, 250, 7));
        this.price = null;

        this.height=60;
        this.next=null;
        this.addSelfComponent();
        this.isEditable=parent.isEditable();
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
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        //Блок Collapse
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
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        this.uncolapseButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        uncollapse();
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
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );

        //Блок полей с данными
        this.priceID = new JTextArea(3,11);
        this.priceID.setLineWrap(true);
        this.priceID.setWrapStyleWord(true);
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
        this.idSP = new JScrollPane(this.priceID);
        this.viewerbase.add(this.idSP);


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

        /*this.priceCount  = new JTextArea(3,12);
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
                                    for(ElementViewer item : elviCol)
                                    {
                                        item.fillTextAreas();
                                    }
                                }
                                sendMassage(Message.UPDATE);

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
        this.viewerbase.add(this.countSP);*/

        this.singeCost = new JTextArea(3,5);
        this.singeCost.setLineWrap(true);
        this.singeCost.setWrapStyleWord(true);
        this.singeCost.setEditable(false);
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
        this.singeCostSP = new JScrollPane(this.singeCost);
        this.viewerbase.add(this.singeCostSP);


        this.singleWorkCost = new JTextArea(3,5);
        this.singleWorkCost.setLineWrap(true);
        this.singleWorkCost.setWrapStyleWord(true);
        this.singleWorkCost.setEditable(false);
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
        this.singleWorkCostSP = new JScrollPane(this.singleWorkCost);
        this.viewerbase.add(this.singleWorkCostSP);



        this.singleMachCost = new JTextArea(3,5);
        this.singleMachCost.setLineWrap(true);
        this.singleMachCost.setWrapStyleWord(true);
        this.singleMachCost.setEditable(false);
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
        this.singleMachCostSP = new JScrollPane(this.singleMachCost);
        this.viewerbase.add(this.singleMachCostSP);


        this.singleOperCost = new JTextArea(3,5);
        this.singleOperCost.setLineWrap(true);
        this.singleOperCost.setWrapStyleWord(true);
        this.singleOperCost.setEditable(false);
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
        this.singleOperCostSP = new JScrollPane(this.singleOperCost);
        this.viewerbase.add(this.singleOperCostSP);


        this.singleMatCost = new JTextArea(3,5);
        this.singleMatCost.setLineWrap(true);
        this.singleMatCost.setWrapStyleWord(true);
        this.singleMatCost.setEditable(false);
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
        this.singleMatCostSP = new JScrollPane(this.singleMatCost);
        this.viewerbase.add(this.singleMatCostSP);


        this.elviCol=null;          //Коллекция ElementVier для расценки, инициализация в методе setPrice
        this.fillTextAreas();

        /*idP=new JPanel();
        nameP=new JPanel();
        uomP=new JPanel();
        singeCostP=new JPanel();
        singleWorkCostP=new JPanel();
        singleMachCostP=new JPanel();
        singleOperCostP=new JPanel();
        singleMatCostP=new JPanel();

        idP.add(idSP);
        nameP.add(nameSP);
        uomP.add(uomSP);
        singeCostP.add(singeCostSP);
        singleWorkCostP.add(singleWorkCostSP);
        singleMachCostP.add(singleMachCostSP);
        singleOperCostP.add(singleOperCostSP);
        singleMatCostP.add(singleMatCostSP);

        this.viewerbase.add(idP);
        this.viewerbase.add(nameP);
        this.viewerbase.add(uomP);
        this.viewerbase.add(singeCostP);
        this.viewerbase.add(singleWorkCostP);
        this.viewerbase.add(singleMachCostP);
        this.viewerbase.add(singleOperCostP);
        this.viewerbase.add(singleMatCostP);*/

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

    @Override
    public boolean isEditable() {return isEditable;}

    public int getBottom()
    {
        if (isWraped){return this.top;}
        return this.top+this.height;
    };

    public boolean isHasLink(IResourse link)
    {
        if (elviCol!=null)
        {
            for (int i = 0; i < elviCol.size(); i++)
            {
                if (elviCol.get(i)==link)
                {return true;}
            }
        }
        return false;
    }

    public BookPartViewer getParent() {return this.parent;}

    public DBPrice getSource(){return this.price;}

    public int getTop(){return this.top;};

    private void fillTextAreas()
    {
        if (this.price==null)
        {
            this.priceID.setText("ID");
            this.priceName.setText("Name");
            this.priceUOM.setText("UOM");

            //this.priceCount.setText("count");

            this.singeCost.setText("SC");
            this.singleWorkCost.setText("SWC");
            this.singleMachCost.setText("SMexC");
            this.singleOperCost.setText("SOpC");
            this.singleMatCost.setText("SMatC");
        }
        else
        {
            this.priceID.setText(this.price.getID());
            this.priceName.setText(this.price.getName());
            this.priceUOM.setText(this.price.getUom());

            //this.priceCount.setText(""+this.price.getCount());

            this.singeCost.setText(""+this.price.getSinglePrice(false));
            this.singleWorkCost.setText(""+this.price.getSingleWorkprice(false));
            this.singleMachCost.setText(""+this.price.getSingleMachprice(false));
            this.singleOperCost.setText(""+this.price.getSingleOperprice(false));
            this.singleMatCost.setText(""+this.price.getSingleMatprice(false));
        }
    }

    private void fillElviCol()
    {
        if (this.elviCol!=null)
        {
            //Удаляяются существующие
            for (int i = 0; i < this.elviCol.size(); i++)
            {
                this.elviCol.get(i).selfDistruct();
            }
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
                DBResViewer tmp=new DBResViewer(this);
                tmp.setResourse(this.price.getResourse(i));
                this.elviCol.add(tmp);
            }
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
            default:
                break;
        }
    }

    public void removeInnerPanel(JPanel removing)
    {
        this.viewerbase.remove(removing);
    }

    public void removeAll()
    {
        this.price=null;
        if (this.elviCol!=null)
        {
            for (DBResViewer item : this.elviCol){item.selfDistruct();}
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
            System.out.println("sendActivationMessgae "+this);
            this.parent.readMessgae(Message.PriceActive,this);
        }
    }

    public void setBounds(int top, int width)
    {
        this.height=60;
        this.top=top;
        this.width=width;

        int priceNameWidth=this.width-0-12*45-75-16;
        int afetrNameHorizBegin=priceNameWidth+75;
        int afetrNameStep=90;

        this.idSP.setBounds(0,0,75,30);
        this.nameSP.setBounds(75,0,priceNameWidth,60);
        this.uomSP.setBounds(afetrNameHorizBegin,0,90,60);
        afetrNameHorizBegin+=afetrNameStep;

        this.singeCostSP.setBounds(afetrNameHorizBegin,0,90,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleWorkCostSP.setBounds(afetrNameHorizBegin,0,90,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleMachCostSP.setBounds(afetrNameHorizBegin,0,90,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleOperCostSP.setBounds(afetrNameHorizBegin,0,90,60);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleMatCostSP.setBounds(afetrNameHorizBegin,0,90,60);

        //Добавляются ElementViewer
        if (!isCollapsed)   //isColapsed=false -> !isColapsed==true     сработает,если значение false
        {

            this.colapseButton.setBounds(14,32,40,25);
            this.uncolapseButton.setBounds(0,0,0,0);
            if (this.elviCol!=null)
            {
                for (int i=0; i<this.elviCol.size();i++)
                {
                    DBResViewer tmp=this.elviCol.get(i);
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

    public void setNext(DBPriceViewer next)
    {
        this.next=next;
    }

    public void setEditable(boolean isEditable)
    {
        this.isEditable=isEditable;

        this.priceID.setEditable(this.isEditable);
        this.priceName.setEditable(this.isEditable);
        this.priceUOM.setEditable(this.isEditable);
    }


    public void setPrice(DBPrice otherPrice)
    {
        this.price=otherPrice;
        this.isEditable=price.isEditable();
        this.fillTextAreas();
        this.fillElviCol();
    }

    public void setTop(int top)
    {
        this.top=top;
    }

    public void setWidth(int width)
    {
        this.width=width;
    }

    public void wrap()
    {
        this.isWraped=true;
        this.viewerbase.setBounds(0,0,0,0);
    }

    public void uncollapse()
    {
        this.isCollapsed=false;
        this.setBounds(this.top,this.width);
        this.sendMassage(Message.UNCOLLAPSE,null);
    }

    public void unwrap()
    {
        this.isWraped=false;
        this.setBounds(this.top,this.width);
    }

    public void update()
    {
        this.fillTextAreas();
        if (this.elviCol!=null)
        {
            for(DBResViewer item : this.elviCol)
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

                        //добавляются недостающие DBResViewer
                        for (int i = oldsize; i < neededResCount; i++)
                        {
                            DBResViewer tmp =new DBResViewer(this);
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
                for(DBResViewer item : this.elviCol){item.selfDistruct();}
            }
            this.elviCol=null;
            this.setBounds(this.top,this.width);
        }
    }
}
