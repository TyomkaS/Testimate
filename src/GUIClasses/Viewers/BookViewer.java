package GUIClasses.Viewers;

import Backend.Documents.Book;
import Backend.Documents.BookPart;
import Backend.Documents.Calculation;
import Backend.Prices.DBPrice;
import Backend.Resourses.IResourse;
import GUIClasses.GUIMainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BookViewer implements DocViewer
{
    private JPanel parent;          //панель родитель, куда добавляется панель viewerbase
    private JPanel viewerbase;      //основная панель для размещения элементов
    private GUIMainFrame mf;

    //Вертикальные координаты, для возможности прокручивания и отрисовки
    //Координата х всегда должна быть 0
    private int top, height, width;
    private boolean isEditable;

    //Эти объекты нужны для обработки полученных сообщений
    private Object activeRes;
    private Object activePrice;
    private Object activePart;

    private Book book;

    private ArrayList<BookPartViewer> bpCol;

    //Конструкторы
    public BookViewer(JPanel parent)
    {
        //Блок основных параметров
        this.parent=parent;
        this.viewerbase = new JPanel();
        this.parent.add(this.viewerbase);
        this.bpCol=null;
        this.height=0;
        this.top=0;
        this.width=0;

        this.activeRes=null;
        this.activePrice=null;
        this.activePart=null;
        this.mf=null;
    }

    public void addChildPanel(JPanel childPanel)
    {
        this.viewerbase.add(childPanel);
    }

    @Override
    public Calculation getSourse()
    {
        return null;
    }

    public int getHeight(){return this.height;}

    public boolean isEditable(){return this.isEditable;}

    public boolean isHasLink(BookPartViewer link)
    {
        if (this.bpCol!=null)
        {
            for (int i = 0; i < this.bpCol.size(); i++)
            {
                if (this.bpCol.get(i)==link)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private void fillbpCol()
    {
        this.bpCol=null;//очистка параграфов, если до этого там что-то было
        if (this.book.getBookPartCount()>0)
        {
            this.bpCol=new ArrayList<>(this.book.getBookPartCount());

            //Заполнение bpCol
            BookPartViewer previous=null;
            for (int i=0; i<this.book.getBookPartCount();i++)
            {
                BookPartViewer tmp=new BookPartViewer(this);
                tmp.setBookPart(this.book.getBookPart(i));
                tmp.setEditable(this.isEditable);
                this.bpCol.add(tmp);

                if (previous!=null)
                {
                    previous.setNext(tmp);
                }
                previous=tmp;
            }
        }

    }
    public void readMessgae(Message msg, Object obj)
    {
        switch (msg)
        {
            case COLLAPSE:
            case UNCOLLAPSE:
                this.setBounds(this.top,this.width);
                break;
            case ResActive:
                this.activeRes=(DBResViewer)obj;
                this.activePrice=((DBResViewer) this.activeRes).getParent();
                this.activePart=((DBPriceViewer)this.activePrice).getParent();
                break;
            case PriceActive:
                this.activeRes=null;
                this.activePrice=(DBPriceViewer)obj;
                this.activePart=((DBPriceViewer)this.activePrice).getParent();
                break;
            case PartActive:
                this.activeRes=null;
                this.activePrice=null;
                this.activePart=(BookPartViewer)obj;
                break;
            case DisActRes:
                this.activeRes=null;
                break;
            case DisActPrice:
                this.activePrice=null;
                break;
            default:
                break;
        }
        System.out.println("=====BookViwer Read Message "+msg+"=====");
        System.out.println("ActivePart="+this.activePart);
        System.out.println("ActivePrice="+this.activePrice);
        System.out.println("ActiveRes="+this.activeRes);
        System.out.println("==========");
    }

    public void remove(BookPartViewer removing)
    {
        if (isHasLink(removing))
        {
            if (removing==this.bpCol.getFirst())
            {
                this.bpCol.getFirst().removeAll();
                this.bpCol.getFirst().selfDistruct();
                this.bpCol.remove(0);
                if (this.bpCol.size()==0)
                {
                    this.bpCol=null;
                }
            }
            else if (removing==this.bpCol.getLast())
            {
                this.bpCol.get(this.bpCol.size()-2).setNext(null);
                this.bpCol.getLast().removeAll();
                this.bpCol.getLast().selfDistruct();
                this.bpCol.remove(this.bpCol.size()-1);
            }
            else
            {
                for (int i = 1; i < this.bpCol.size(); i++)
                {
                    if (this.bpCol.get(i)==removing)
                    {
                        this.bpCol.get(i-1).setNext(this.bpCol.get(i+1));
                        this.bpCol.get(i).removeAll();
                        this.bpCol.get(i).selfDistruct();
                        this.bpCol.remove(i);
                    }
                }
            }
        }
    }

    public void removeInnerPanel(JPanel removing)
    {
        this.viewerbase.remove(removing);
    }

    public void setBounds(int top, int width)
    {
        this.top=top;
        this.width=width;
        this.height=0;

        if (this.bpCol!=null)
        {
            this.bpCol.getFirst().setBounds(this.top, this.width);
            this.height =  this.bpCol.getLast().getBottom();
        }

        this.viewerbase.setBounds(0,0,this.width,this.height);
    }

    public void setDocument(Book book)
    {
        //Очистка коллекций параграфов на случай, если до этого уже была задана другая книга

        this.book=new Book(book);
        this.isEditable=this.book.isEditable();
        this.fillbpCol();
    }

    public void setDocument(Calculation calculation){}

    public void setMainFrame(GUIMainFrame mf)
    {
        this.mf=mf;
    }

    public void setViewMode(ViewMode mode){}

    //DockViewer implementation
    public void doaction()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer do");
    };
    public void undoaction()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer undo");
    };
    public void del()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer delete");
        if (this.activeRes!=null)
        {
            DBPriceViewer tempPriceViewer=((DBResViewer)this.activeRes).getParent();
            ((DBPriceViewer)tempPriceViewer).getSource().removeSource((IResourse) ((DBResViewer)this.activeRes).getSource());
            ((DBPriceViewer) tempPriceViewer).updateIresourses();
            this.setBounds(this.top,this.width);
            return;
        }
        else if(this.activePrice!=null)
        {
            BookPartViewer tmp=((DBPriceViewer)this.activePrice).getParent();
            tmp.getSource().remove(((DBPriceViewer)this.activePrice).getSource());
            tmp.update();
            this.setBounds(this.top,this.width);
            this.mf.reFit();
            return;
        }
        else if(this.activePart!=null)
        {
            this.book.remove(((BookPartViewer)this.activePart).getSource());

            this.remove((BookPartViewer)this.activePart);
            this.setBounds(this.top,this.width);
            this.mf.reFit();
            return;
        }

    };

    public Object copy()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer copy");
        return null;
    };
    public Object cut()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer cut");
        return null;
    };
    public void paste(Object obj)
    {
        JOptionPane.showMessageDialog(null, "Bookviewer paste");
    };

    public void moveup()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer moveup");
        if (this.activeRes!=null)
        {
            DBPriceViewer tempPriceViewer=((DBResViewer)this.activeRes).getParent();
            ((DBPriceViewer)tempPriceViewer).getSource().moveResUP((IResourse) ((DBResViewer)this.activeRes).getSource());
            ((DBPriceViewer) tempPriceViewer).updateIresourses();
            this.setBounds(this.top,this.width);
            return;
        }
        else if(this.activePrice!=null)
        {

            BookPartViewer tmp=((DBPriceViewer)this.activePrice).getParent();
            tmp.getSource().moveUp(((DBPriceViewer)this.activePrice).getSource());
            tmp.update();
            this.setBounds(this.top,this.width);
            this.mf.reFit();
            return;

        }
        else if(this.activePart!=null)
        {
            this.book.moveUp(((BookPartViewer)this.activePart).getSource());
            this.viewerbase.removeAll();
            this.fillbpCol();
            this.setBounds(this.top,this.width);
            this.mf.reFit();
            return;
        }
    };
    public void movedown()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer movedown");
        if (this.activeRes!=null)
        {
            DBPriceViewer tempPriceViewer=((DBResViewer)this.activeRes).getParent();
            ((DBPriceViewer)tempPriceViewer).getSource().moveResDown((IResourse) ((DBResViewer)this.activeRes).getSource());
            ((DBPriceViewer) tempPriceViewer).updateIresourses();
            this.setBounds(this.top,this.width);
            return;
        }
        else if(this.activePrice!=null)
        {

            BookPartViewer tmp=((DBPriceViewer)this.activePrice).getParent();
            tmp.getSource().moveDown(((DBPriceViewer)this.activePrice).getSource());
            tmp.update();
            this.setBounds(this.top,this.width);
            this.mf.reFit();
            return;

        }
        else if(this.activePart!=null)
        {
            this.book.moveDown(((BookPartViewer)this.activePart).getSource());
            this.viewerbase.removeAll();
            this.fillbpCol();
            this.setBounds(this.top,this.width);
            this.mf.reFit();
            return;
        }
    };
    public void moreinfo()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer moreinfo");
    };

    public void addPrice()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer addPrice");
        if (this.activePart!=null)
        {
            ((BookPartViewer) this.activePart).getSource().add();
            ((BookPartViewer) this.activePart).add();
            int index=((BookPartViewer) this.activePart).getPriceViewerCount()-1;
            ((BookPartViewer) this.activePart).getPriceViewer(index).setPrice(((BookPartViewer) this.activePart).getSource().getprice(index));
            ((BookPartViewer) this.activePart).getTop();
            ((BookPartViewer) this.activePart).setBounds(((BookPartViewer) this.activePart).getTop(),this.width);
            this.setBounds(this.top,this.width);
            this.mf.reFit();

        }
        else
        {
            JOptionPane.showMessageDialog(null, "Выберите раздел");
        }
    };
    public void addHeader()
    {
        JOptionPane.showMessageDialog(null, "Книга не имеет заголовков");
    };
    public void addPart()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer addPpart");
        this.book.add();
        BookPartViewer tmp=new BookPartViewer(this);
        tmp.setBookPart(this.book.getBookPart(this.book.getBookPartCount()-1));
        if (this.bpCol!=null)
        {
            this.bpCol.getLast().setNext(tmp);
        }
        else
        {
            this.bpCol=new ArrayList<>(1);
        }
        this.bpCol.add(tmp);
        this.setBounds(this.top,this.width);
        this.mf.reFit();
    };
    public void addWorker()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer addWorker");
        if (this.activePrice!=null)
        {
            DBPrice tmp=((DBPriceViewer)this.activePrice).getSource();
            tmp.addWorker();
            ((DBPriceViewer)this.activePrice).updateIresourses();
            this.setBounds(this.top,this.width);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Выберите расценку");
        }
    };
    public void addMachine()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer addMachine");
        if (this.activePrice!=null)
        {
            DBPrice tmp=((DBPriceViewer)this.activePrice).getSource();
            tmp.addMachine();
            ((DBPriceViewer)this.activePrice).updateIresourses();
            this.setBounds(this.top,this.width);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Выберите расценку");
        }
    };
    public void addMaterial()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer addMaterial");
        if (this.activePrice!=null)
        {
            DBPrice tmp=((DBPriceViewer)this.activePrice).getSource();
            tmp.addMaterial();
            ((DBPriceViewer)this.activePrice).updateIresourses();
            this.setBounds(this.top,this.width);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Выберите расценку");
        }
    };

    public void scroll(int value){};
    public int getHieght(){return 0;};
    public void open(){};
    public void save(){};
    public void close(){};

}
