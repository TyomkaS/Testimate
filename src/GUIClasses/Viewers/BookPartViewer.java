package GUIClasses.Viewers;

import Backend.Documents.BookPart;
import Backend.Prices.DBPrice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class BookPartViewer
{
    private BookPartViewer next;
    private BookViewer parent;
    private JPanel parentviewerbase;            //панель родитель, куда добавляется панель viewerbase
    private JPanel viewerbase;


    //Вертикальные координаты, для возможности прокручивания и отрисовки
    //Координата х всегда должна быть 0
    private int top, height, width;
    private boolean isCollapsed;
    private boolean isEditable;

    private JTextField name;
    private JButton colapseButton;
    private JButton uncolapseButton;

    private BookPart bookPart;

    private ArrayList<DBPriceViewer> priceCol;

    //Конструкторы
    public BookPartViewer(JPanel parentviewerbase)
    {
        this.parentviewerbase=parentviewerbase;
        this.viewerbase = new JPanel();
        this.parentviewerbase.add(viewerbase);
        this.viewerbase.setBackground(new java.awt.Color(223, 35, 71));
        this.height=20;
        this.parent=null;
        this.next=null;
        this.isEditable=true;
        this.addSelfComponent();
    }

    public BookPartViewer(BookViewer parent)
    {
        this.parent=parent;
        this.viewerbase = new JPanel();
        this.parent.addChildPanel(viewerbase);
        this.viewerbase.setBackground(new java.awt.Color(223, 35, 71));
        this.height=20;
        this.next=null;
        this.isEditable=this.parent.isEditable();
        this.addSelfComponent();

    }

    public void add()
    {
        DBPriceViewer tmp =new DBPriceViewer(this);
        if (this.priceCol==null){this.priceCol=new ArrayList<>(1);}
        this.priceCol.add(tmp);
    }

    public void addChildPanel(JPanel childPanel)
    {
        this.viewerbase.add(childPanel);
    }

    private void addSelfComponent()
    {
        this.name=new JTextField();
        this.viewerbase.add(this.name);

        this.name.addFocusListener(
                new FocusListener()
                {
                    @Override
                    public void focusGained(FocusEvent e)
                    {
                        sendMassage(Message.DisActRes,null);
                        sendMassage(Message.DisActPrice,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e)
                    {
                    }
                }
        );
        this.name.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(bookPart!=null)
                        {
                            JTextField tmp=( JTextField)e.getSource();
                            bookPart.setName(tmp.getText());
                            update();
                        }
                    }
                }
        );


        //Блок Collaps
        this.isCollapsed=true;
        this.colapseButton=new JButton("-");
        this.viewerbase.add(this.colapseButton);
        this.uncolapseButton = new JButton("+");
        this.viewerbase.add(this.uncolapseButton);

        this.viewerbase.addFocusListener(
            new FocusListener()
            {
                @Override
                public void focusGained(FocusEvent e)
                {
                    sendMassage(Message.DisActRes,null);
                    sendMassage(Message.DisActPrice,null);
                    sendActivationMessage();}

                @Override
                public void focusLost(FocusEvent e) {}
            }
        );

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
                        sendMassage(Message.DisActPrice,null);
                        sendActivationMessage();}

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
                        sendMassage(Message.DisActPrice,null);
                        sendActivationMessage();}

                    @Override
                    public void focusLost(FocusEvent e) {}
                }
        );
    }

    public void deleteNext()
    {this.next=null;}

    public void collapse()
    {
        this.isCollapsed=true;
        this.setBounds(this.top,this.width);
        this.sendMassage(Message.COLLAPSE,null);
    }

    public boolean isEditable(){return this.isEditable;};

    public boolean isHasLink(DBPriceViewer link)
    {
        if (this.priceCol!=null)
        {
            for (int i = 0; i < this.priceCol.size(); i++)
            {
                if (this.priceCol.get(i)==link)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int getBottom()
    {
        return this.top+this.height;
    };

    public BookViewer getParent()
    {
        if (this.parent!=null){return this.parent;}
        return null;
    }

    public DBPriceViewer getPriceViewer(int index)
    {
        if (this.priceCol!=null && index<this.priceCol.size())
        {
            return this.priceCol.get(index);
        }
        return null;
    }

    public int getPriceViewerCount()
    {
        if (this.priceCol!=null)
        {
            return this.priceCol.size();
        }
        return 0;
    }

    public BookPart getSource(){return this.bookPart;}

    public int getTop(){return this.top;};

    private void fillTextAreas()
    {
        if (this.bookPart==null)
        {
            this.name.setText("Параграф");
        }
        else
        {
            this.name.setText(this.bookPart.getname());
        }
    }



    private void fillPriceCol()
    {
        if (this.bookPart!=null)
        {
            if (this.priceCol!=null)
            {
                //Список ElementsViewer обнуляется, для нового заполнения
                for (DBPriceViewer item : this.priceCol){item.selfDistruct();}
                this.priceCol=null;
            }

            //Определяется количество расценок
            if (this.bookPart.getsize()!=0)
            {

                //Создаётся новая коллекция DBPriceViewwer
                this.priceCol=new ArrayList<>(this.bookPart.getsize());

                //Заполняется коллекция DBPriceViewwer, и каждый элемент связывается с соответвующим ресурсом
                for (int i=0; i<this.bookPart.getsize();i++)
                {
                    DBPriceViewer tmp=new DBPriceViewer(this);
                    tmp.setPrice(this.bookPart.getprice(i));
                    tmp.setEditable(this.isEditable);
                    this.priceCol.add(tmp);
                }
            }
        }
    }

    public void MoveUp(DBPriceViewer price)
    {
        if (isEditable)
        {
            if (this.bookPart!=null && isHasLink(price))
            {
                if(this.bookPart.moveUp(price.getSource()))
                {
                    for (int i = 0; i < this.priceCol.size(); i++)
                    {
                        if (this.priceCol.get(i)==price)
                        {
                            DBPriceViewer tmp=new DBPriceViewer(this);
                            tmp.setPrice(this.bookPart.getprice(i-1));
                            this.priceCol.get(i).selfDistruct();
                            this.priceCol.remove(i);
                            this.priceCol.add(i-1,tmp);
                            this.priceCol.get(i-2).setNext(this.priceCol.get(i-1));
                            this.priceCol.get(i-1).setNext(this.priceCol.get(i));
                            this.setBounds(this.top,this.width);
                        }
                    }
                }
            }
        }
    }

    public void MoveDown(DBPriceViewer price)
    {
        if (isEditable)
        {
            if (this.bookPart!=null && isHasLink(price))
            {
                if(this.bookPart.moveDown(price.getSource()))
                {
                    for (int i = 0; i < this.priceCol.size(); i++)
                    {
                        if (this.priceCol.get(i)==price)
                        {
                            DBPriceViewer tmp=new DBPriceViewer(this);
                            tmp.setPrice(this.bookPart.getprice(i-1));
                            this.priceCol.get(i).selfDistruct();
                            this.priceCol.remove(i);
                            this.priceCol.add(i+1,tmp);
                            this.priceCol.get(i-1).setNext(this.priceCol.get(i));
                            this.priceCol.get(i).setNext(this.priceCol.get(i+1));
                            this.setBounds(this.top,this.width);
                        }
                    }
                }
            }
        }
    }

    public void readMessgae(Message msg, Object obj)
    {
        //this.setBounds(this.top,this.width);
        switch (msg)
        {
            case COLLAPSE:
            case UNCOLLAPSE:
                this.setBounds(this.top,this.width);
                this.sendMassage(msg,null);
                break;
            case ResActive:
                this.sendMassage(msg,obj);
                break;
            case PriceActive:
                this.sendMassage(msg,obj);
                break;
            case DisActRes:
            case DisActPrice:
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

        if (this.priceCol!=null)
        {
            for (DBPriceViewer item : this.priceCol){ item.selfDistruct();}
        }
        this.bookPart=null;
        this.viewerbase.removeAll();
    }

    public void setEditable(boolean isEditable){this.isEditable=isEditable;}

    public void setBookPart(BookPart bp)
    {
        this.bookPart=bp;

        this.fillTextAreas();
        this.fillPriceCol();
    }

    public void setBounds(int top, int width)
    {

        this.height=20;
        this.top=top;
        this.width=width;

        this.name.setBounds(75,0,this.width-75-16,this.height);

        if (!isCollapsed)   //isColapsed=false => !isColapsed==true     сработает,если значение false
        {

            this.colapseButton.setBounds(0, 0, 75, 20);
            this.uncolapseButton.setBounds(0, 0, 0, 0);
            if (this.priceCol!=null)
            {
                for (int i=0; i<this.priceCol.size();i++)
                {
                    DBPriceViewer tmp=this.priceCol.get(i);
                    tmp.setWidth(this.width);
                    tmp.setTop(this.height);
                    tmp.unwrap();
                    this.height=tmp.getBottom();
                }
            }
        }
        else
        {
            this.colapseButton.setBounds(0,0,0,0);
            this.uncolapseButton.setBounds(0,0,75,20);

            if (this.priceCol!=null)
            {
                for (int i=0; i<this.priceCol.size();i++)
                {
                    this.priceCol.get(i).wrap();
                }
            }
            this.height=20;
        }

        this.viewerbase.setBounds(0,this.top, this.width,this.height);

        if (this.next!=null)
        {this.next.setBounds(this.getBottom()+1,width);}
    }

    public void setNext(BookPartViewer next)
    {
        this.next=next;
    }

    public void selfDistruct()
    {

        this.removeAll();
        if (this.parent!=null)
        {
            this.parent.removeInnerPanel(this.viewerbase);
        }
        this.bookPart=null;
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
            this.parent.readMessgae(Message.PartActive,this);
        }
    }

    public void uncollapce()
    {
        this.isCollapsed=false;
        this.setBounds(this.top,this.width);
        this.sendMassage(Message.UNCOLLAPSE,null);
    }

    public void update()
    {
        //this.addSelfComponent();
        this.fillTextAreas();
        this.fillPriceCol();
    }
}
