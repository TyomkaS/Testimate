package GUIClasses.Viewers;

import Backend.Documents.Calculation;
import Backend.Prices.CalcPrice;
import Backend.Prices.DBPrice;
import Backend.Resourses.IResourse;
import Backend.Resourses.ListMachine;
import Backend.Resourses.ListMaterial;
import Backend.Resourses.ListWorker;
import GUIClasses.GUIMainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalcViewer implements Viewer, DocViewer
{
    private JPanel parent;          //панель родитель, куда добавляется панель viewerbase
    private JPanel viewerbase;      //основная панель для размещения элементов
    //private JPanel listPanel;
    private GUIMainFrame mf;
    private ViewMode mode;

    //Вертикальные координаты, для возможности прокручивания и отрисовки
    //Координата х всегда должна быть 0
    private int top;
    private int height;
    private int width;
    private boolean isCurrentPrice;
    private boolean isEditable;

    //Эти объекты нужны для обработки полученных сообщений
    private Object activeRes;
    private Object activePrice;

    private Calculation calc;
    private ArrayList<CalcPriceViewer>pwcol;
    private ArrayList<CalcResViewer>resList;
    JLabel listLabel;

    /*private JLabel wListLabel;
    private JButton collapsewl;
    private JButton uncollapsewl;
    private boolean isCollapsedwl;
    private ArrayList<CalcResViewer>workerList;*/

    /*private JLabel machListLabel;
    private JButton collapsemach;
    private JButton uncollapsemach;
    private boolean isCollapsedmachl;
    private ArrayList<CalcResViewer>machineList;*/

    /*private JLabel matListLabel;
    private JButton collapsemat;
    private JButton uncollapsemat;
    private boolean isCollapsedmatl;
    private ArrayList<CalcResViewer>matList;*/



    public CalcViewer(JPanel parent)
    {
        //Блок основных параметров
        this.parent=parent;
        this.viewerbase = new JPanel();
        this.parent.add(viewerbase);
        //this.listPanel = new JPanel();
        //this.parent.add(listPanel);
        this.pwcol=null;
        this.resList=null;
        this.listLabel=null;
        this.height=0;
        this.top=0;
        this.width=0;
        this.isEditable=true;
        this.mf=null;
        this.mode = ViewMode.CALC;

        /*this.wListLabel=new JLabel("Ведомость рабочих");
        this.collapsewl=new JButton("-");
        this.uncollapsewl=new JButton("+");
        this.isCollapsedwl=false;
        this.viewerbase.add(this.wListLabel);
        this.viewerbase.add(this.collapsewl);
        this.viewerbase.add(this.uncollapsewl);
        workerList=null;
        this.collapsewl.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isCollapsedwl=true;
                        setBounds(top,width);
                    }
                }
        );
        this.uncollapsewl.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isCollapsedwl=false;
                        setBounds(top,width);
                    }
                }
        );*/


        /*this.machListLabel=new JLabel("Ведомость машин");;
        this.collapsemach=new JButton("-");
        this.uncollapsemach=new JButton("+");
        this.isCollapsedmachl=false;
        this.viewerbase.add(this.machListLabel);
        this.viewerbase.add(this.collapsemach);
        this.viewerbase.add(this.uncollapsemach);
        machineList=null;
        this.collapsemach.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isCollapsedmachl=true;
                        setBounds(top,width);
                    }
                }
        );
        this.uncollapsemach.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isCollapsedmachl=false;
                        setBounds(top,width);
                    }
                }
        );*/

        /*this.matListLabel=new JLabel("Ведомость материалов");
        this.collapsemat=new JButton("-");
        this.uncollapsemat=new JButton("+");
        this.isCollapsedmatl=false;
        this.viewerbase.add(this.matListLabel);
        this.viewerbase.add(this.collapsemat);
        this.viewerbase.add(this.uncollapsemat);
        matList=null;

        this.collapsemat.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isCollapsedmatl=true;
                        setBounds(top,width);
                    }
                }
        );
        this.uncollapsemat.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isCollapsedmatl=false;
                        setBounds(top,width);
                    }
                }
        );*/
    }

    public void addChildPanel(JPanel childPanel)
    {
        this.viewerbase.add(childPanel);
    }

    @Override
    public Calculation getSourse()
    {
        return this.calc;
    }

    public int getHeight(){return this.height;}

    public boolean isEditable(){return this.isEditable;}

    private void fillPriceCol()
    {
        if (this.calc!=null)
        {
            if (this.pwcol!=null)
            {
                //Список ElementsViewer обнуляется, для нового заполнения
                for (CalcPriceViewer item : this.pwcol){item.selfDistruct();}
                this.pwcol=null;
            }

            //Определяется количество расценок
            if (this.calc.getPriceCount()!=0)
            {

                //Создаётся новая коллекция CalcPriceViewer
                this.pwcol=new ArrayList<>(this.calc.getPriceCount());

                //Заполняется коллекция CalcPriceViewwer, и каждый элемент связывается с соответвующим ресурсом
                CalcPriceViewer previous=null;
                for (int i=0; i<this.calc.getPriceCount();i++)
                {
                    CalcPriceViewer tmp=new CalcPriceViewer(this);
                    tmp.setPrice(this.calc.getPrice(i));
                    tmp.setEditable(this.isEditable);
                    this.pwcol.add(tmp);

                    if (previous!=null)
                    {
                        previous.setNext(tmp);
                    }
                    previous=tmp;
                }
            }
        }
    }

    public void readMessgae(Message msg, Object obj)
    {
        switch (msg)
        {
            case UPDATE:
                System.out.println("Case update");
                if (this.pwcol!=null) {for (CalcPriceViewer item : this.pwcol){item.update();}}
                this.mf.setTotalCost(Double.toString(this.calc.getTotalCost(false)));
                /*if (this.workerList!=null){for (CalcResViewer item : this.workerList){item.fillTextAreas();}}
                if (this.machineList!=null){for (CalcResViewer item : this.machineList){item.fillTextAreas();}}
                if (this.matList!=null){for (CalcResViewer item : this.matList){item.fillTextAreas();}}*/
                this.mf.reFit();
                break;
            case COLLAPSE:
            case UNCOLLAPSE:
                System.out.println("Case COLLAPSE/UNCOLLAPSE");
                this.setBounds(this.top,this.width);
                break;
            case ResActive:
                System.out.println("Case ResActive");
                this.activeRes=(CalcResViewer)obj;
                if (((CalcResViewer) this.activeRes).getParent()==this)
                {
                    this.activePrice=null;
                }
                else
                {
                    this.activePrice=((CalcResViewer) this.activeRes).getParent();
                }
                System.out.println("ResParent "+this.activePrice);
                break;
            case PriceActive:
                System.out.println("Case PriceActive");
                this.activeRes=null;
                this.activePrice=(CalcPriceViewer)obj;
                break;
            case DisActRes:
                System.out.println("Case DisActRes");
                this.activeRes=null;
                break;
            case DisActPrice:
                System.out.println("Case DisActPrice");
                this.activePrice=null;
                break;
            default:
                break;
        }
    }

    public void removeInnerPanel(JPanel removing)
    {
        this.viewerbase.remove(removing);
    }

    public void setBounds(int top, int width)
    {
        switch (this.mode)
        {
            case CALC:
                this.top=top;
                this.width=width;
                this.height=0;
                if (pwcol!=null)
                {
                    pwcol.getFirst().setBounds(this.height,this.width);
                    //Перебирать цикл не нужно, т.к. CalcViewer вызовет у следующего элемента метод SetBounds
                    //У элемента CalcViewer иницилизиованно поле следующего элемента в коллекции в методе SetPrice
                    this.height=pwcol.getLast().getBottom();
                }
                this.viewerbase.setBounds(0,0,this.width,this.height);
                //this.listPanel.setBounds(0,0,0,0);
                this.mf.fitScroll();
                break;
            case WORK:
                if (pwcol!=null)
                {
                    for (int i = 0; i < pwcol.size(); i++)
                    {
                        this.pwcol.get(i).wrap();
                    }
                }

                if (this.resList!=null)
                {
                    for (CalcResViewer item : this.resList)
                    {
                        item.selfDistruct();
                    }
                    this.resList=null;
                }
                if (this.listLabel!=null)
                {
                    this.viewerbase.remove(this.listLabel);
                    this.listLabel=null;
                }

                ArrayList<ListWorker> workerList=this.calc.getWorkerList();
                if (workerList!=null)
                {
                    this.resList=new ArrayList<>(workerList.size());
                    this.height=20;

                    for (int i = 0; i < workerList.size(); i++)
                    {
                        CalcResViewer tmp=new CalcResViewer(this);
                        tmp.setResourse(workerList.get(i));
                        this.resList.add(tmp);
                    }

                    this.listLabel=new JLabel("Ведомость рабочих");
                    this.viewerbase.add(listLabel);
                    listLabel.setBounds(0,0,this.width,20);

                    for (int i = 0; i < this.resList.size(); i++)
                    {
                        this.resList.get(i).setBounds(this.height,this.width);
                        this.height+=20;
                    }

                    this.height=this.resList.getLast().getBottom();

                    this.viewerbase.setBounds(0,0,this.width,this.height);

                }
                else
                {
                    this.viewerbase.setBounds(0,0,0,0);
                }
                this.mf.fitScroll();
                break;
            case MACH:
                if (pwcol!=null)
                {
                    for (int i = 0; i < pwcol.size(); i++)
                    {
                        this.pwcol.get(i).wrap();
                    }
                }

                if (this.resList!=null)
                {
                    for (CalcResViewer item : this.resList)
                    {
                        item.selfDistruct();
                    }
                    this.resList=null;
                }
                if (this.listLabel!=null)
                {
                    this.viewerbase.remove(this.listLabel);
                    this.listLabel=null;
                }

                ArrayList<ListMachine> machineList=this.calc.getMachineList();
                if (machineList!=null)
                {
                    this.resList=new ArrayList<>(machineList.size());
                    this.height=20;

                    for (int i = 0; i < machineList.size(); i++)
                    {
                        CalcResViewer tmp=new CalcResViewer(this);
                        tmp.setResourse(machineList.get(i));
                        this.resList.add(tmp);
                    }

                    this.listLabel=new JLabel("Ведомость машин");
                    this.viewerbase.add(listLabel);
                    listLabel.setBounds(0,0,this.width,20);

                    for (int i = 0; i < this.resList.size(); i++)
                    {
                        this.resList.get(i).setBounds(this.height,this.width);
                        this.height+=20;
                    }

                    this.viewerbase.setBounds(0,0,this.width,this.resList.getLast().getBottom());

                }
                else
                {
                    this.viewerbase.setBounds(0,0,0,0);
                }
                this.mf.fitScroll();
                break;
            case MAT:
                if (pwcol!=null)
                {
                    for (int i = 0; i < pwcol.size(); i++)
                    {
                        this.pwcol.get(i).wrap();
                    }
                }

                if (this.resList!=null)
                {
                    for (CalcResViewer item : this.resList)
                    {
                        item.selfDistruct();
                    }
                    this.resList=null;
                }
                if (this.listLabel!=null)
                {
                    this.viewerbase.remove(this.listLabel);
                    this.listLabel=null;
                }

                ArrayList<ListMaterial> materialList=this.calc.getMaterialList();
                if (materialList!=null)
                {
                    this.resList=new ArrayList<>(materialList.size());
                    this.height=20;

                    for (int i = 0; i < materialList.size(); i++)
                    {
                        CalcResViewer tmp=new CalcResViewer(this);
                        tmp.setResourse(materialList.get(i));
                        this.resList.add(tmp);
                    }

                    this.listLabel=new JLabel("Ведомость машин");
                    this.viewerbase.add(listLabel);
                    listLabel.setBounds(0,0,this.width,20);

                    for (int i = 0; i < this.resList.size(); i++)
                    {
                        this.resList.get(i).setBounds(this.height,this.width);
                        this.height+=20;
                    }

                    this.viewerbase.setBounds(0,0,this.width,this.resList.getLast().getBottom());

                }
                else
                {
                    this.viewerbase.setBounds(0,0,0,0);
                }
                break;
            default:
                this.viewerbase.setBounds(0,0,0,0);
                this.mf.fitScroll();
                break;
        }




        //Расположение ведомости рабочих
        /*this.height+=20;
        this.wListLabel.setBounds(75,this.height,this.width,20);
        this.height+=20;
        if (this.workerList!=null)
        {
            if (this.isCollapsedwl)
            {
                this.uncollapsewl.setBounds(0,this.height-20,75,20);
                this.collapsewl.setBounds(0,0,0,0);
                for (int i=0; i<this.workerList.size();i++)
                {
                    this.workerList.get(i).collapse();
                }
            }
            else
            {
                this.collapsewl.setBounds(0,this.height-20,75,20);
                this.uncollapsewl.setBounds(0,0,0,0);
                for (int i=0; i<this.workerList.size();i++)
                {
                    this.workerList.get(i).uncollapse();
                    this.workerList.get(i).setBounds(this.height,this.width);
                    this.height=workerList.get(i).getBottom();
                }
            }
        }
        else
        {
            this.collapsewl.setBounds(0,0,0,0);
            this.uncollapsewl.setBounds(0,0,0,0);
        }*/

        //Расположение ведомости машин
        /*this.height+=20;
        this.machListLabel.setBounds(75,this.height,this.width,20);
        this.height+=20;
        if (this.machineList!=null)
        {

            if (this.isCollapsedmachl)
            {
                this.uncollapsemach.setBounds(0,this.height-20,75,20);
                this.collapsemach.setBounds(0,0,0,0);
                for (int i=0; i<this.machineList.size();i++)
                {
                    this.machineList.get(i).collapse();
                }

            }
            else
            {
                this.collapsemach.setBounds(0,this.height-20,75,20);
                this.uncollapsemach.setBounds(0,0,0,0);
                for (int i=0; i<this.machineList.size();i++)
                {
                    this.machineList.get(i).uncollapse();
                    this.machineList.get(i).setBounds(this.height,this.width);
                    this.height=machineList.get(i).getBottom();
                }
            }

        }
        else
        {
            this.collapsemach.setBounds(0,0,0,0);
            this.uncollapsemach.setBounds(0,0,0,0);
        }*/

        //Расположение ведомости материалов
        /*this.height+=20;
        this.matListLabel.setBounds(75,this.height,this.width,20);
        this.height+=20;
        if (this.matList!=null)
        {

            if (this.isCollapsedmatl)
            {
                this.uncollapsemat.setBounds(0,this.height-20,75,20);
                this.collapsemat.setBounds(0,0,0,0);
                for (int i=0; i<this.matList.size();i++)
                {
                    this.matList.get(i).collapse();
                }
            }
            else
            {
                this.collapsemat.setBounds(0,this.height-20,75,20);
                this.uncollapsemat.setBounds(0,0,0,0);
                for (int i=0; i<this.matList.size();i++)
                {
                    this.matList.get(i).uncollapse();
                    this.matList.get(i).setBounds(this.height,this.width);
                    this.height=matList.get(i).getBottom();
                }
            }
        }
        else
        {
            this.collapsemat.setBounds(0,0,0,0);
            this.uncollapsemat.setBounds(0,0,0,0);
        }*/

    }

    public void setDocument(Calculation calculation)
    {
        this.calc = calculation;
        this.isEditable=this.calc.isEditable();
        this.isCurrentPrice = false;

        this.fillPriceCol();

        /*if (this.calc.getPriceCount()>0)
        {
            this.height = this.calc.getPriceCount() * 60;
            this.pwcol=new ArrayList<>(this.calc.getPriceCount());

            //Заполнение pwcol (основная часть сметы)
            CalcPriceViewer previous=null;
            for (int i=0; i<this.calc.getPriceCount();i++)
            {
                CalcPriceViewer tmp=new CalcPriceViewer(this);
                tmp.setPrice(this.calc.getPrice(i));
                tmp.setEditable(this.isEditable);
                this.pwcol.add(tmp);

                if (previous!=null)
                {
                    previous.setNext(tmp);
                }
                previous=tmp;
            }*/

            //Заполнение ведомости рабочих
            /*if (this.calc.getWorkerListCount()>0)
            {
                this.workerList=new ArrayList<>(this.calc.getWorkerListCount());

                for (int i=0; i<this.calc.getWorkerListCount();i++)
                {
                    CalcResViewer tmp=new CalcResViewer(this);
                    tmp.setResourse(this.calc.getWorker(i));
                    tmp.setEditable(this.isEditable);
                    this.workerList.add(tmp);
                }
            }*/

            //Заполнение ведомости машин
           /* if (this.calc.getMachineListCount()>0)
            {
                this.machineList=new ArrayList<>(this.calc.getMachineListCount());
                for (int i=0; i<this.calc.getMachineListCount();i++)
                {
                    CalcResViewer tmp=new CalcResViewer(this);
                    tmp.setResourse(this.calc.getMachine(i));
                    tmp.setEditable(this.isEditable);
                    this.machineList.add(tmp);
                }
            }*/

            //Заполнение ведомости материалов
            /*if (this.calc.getMaterialListCount()>0)
            {
                this.matList=new ArrayList<>(this.calc.getMaterialListCount());
                for (int i=0; i<this.calc.getMaterialListCount();i++)
                {
                    CalcResViewer tmp=new CalcResViewer(this);
                    tmp.setResourse(this.calc.getMaterial(i));
                    tmp.setEditable(this.isEditable);
                    this.matList.add(tmp);
                }
            }
        }*/
        this.setBounds(this.top,this.width);
        this.mf.setTotalCost(Double.toString(this.calc.getTotalCost(false)));
        this.mf.reFit();
    }

    public void setEditable(boolean isEditable){this.isEditable=isEditable;}

    //DockViewer implementation

    public void setMainFrame(GUIMainFrame mf){this.mf=mf;}

    public void setViewMode(ViewMode mode)
    {
        this.mode=mode;
        this.setBounds(this.top,this.width);
        this.mf.reFit();
    }

    public void update()
    {
        //this.addSelfComponent();
        this.fillPriceCol();
        this.mf.setTotalCost(Double.toString(this.calc.getTotalCost(false)));
    }

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
            Viewer tempViewer=((CalcResViewer)this.activeRes).getParent();
            CalcPriceViewer tempPriceViewer=((CalcPriceViewer)tempViewer);
            tempPriceViewer.getSource().removeSource((IResourse)((CalcResViewer)this.activeRes).getSource());
            tempPriceViewer.updateIresourses();
            this.setBounds(this.top,this.width);
            return;
        }
        else if(this.activePrice!=null)
        {
            Viewer tempViewer=((Viewer)this.activePrice);
            CalcPriceViewer tempPriceViewer=((CalcPriceViewer)tempViewer);
            if (this.pwcol!=null && this.pwcol.size()==1)
            {
                System.out.println("collection size = 1");
            }
            this.calc.removePrice(tempPriceViewer.getSource());
            this.update();
            this.setBounds(this.top,this.width);
            this.mf.reFit();
            return;

        }
    };

    public Object copy()
    {
        JOptionPane.showMessageDialog(null, "В этой версии программы данная функция не реализована");
        /*JOptionPane.showMessageDialog(null, "Bookviewer copy");*/
        return null;
    };
    public Object cut()
    {
        JOptionPane.showMessageDialog(null, "В этой версии программы данная функция не реализована");
        /*JOptionPane.showMessageDialog(null, "Bookviewer cut");*/
        return null;
    };
    public void paste(Object obj)
    {
        JOptionPane.showMessageDialog(null, "В этой версии программы данная функция не реализована");
        /*JOptionPane.showMessageDialog(null, "Bookviewer paste");*/
    };

    public void moveup()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer moveup");
        if (this.activeRes!=null)
        {
            Viewer tempViewer=((CalcResViewer)this.activeRes).getParent();
            CalcPriceViewer tempPriceViewer=((CalcPriceViewer)tempViewer);
            tempPriceViewer.getSource().moveResUP((IResourse)((CalcResViewer)this.activeRes).getSource());
            tempPriceViewer.updateIresourses();
            return;
        }
        else if(this.activePrice!=null)
        {
            Viewer tempViewer=((Viewer)this.activePrice);
            CalcPriceViewer tempPriceViewer=((CalcPriceViewer)tempViewer);
            this.calc.moveUp(tempPriceViewer.getSource());
            this.update();
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
            Viewer tempViewer=((CalcResViewer)this.activeRes).getParent();
            CalcPriceViewer tempPriceViewer=((CalcPriceViewer)tempViewer);
            tempPriceViewer.getSource().moveResDown((IResourse)((CalcResViewer)this.activeRes).getSource());
            tempPriceViewer.updateIresourses();
            return;
        }
        else if(this.activePrice!=null)
        {
            Viewer tempViewer=((Viewer)this.activePrice);
            CalcPriceViewer tempPriceViewer=((CalcPriceViewer)tempViewer);
            this.calc.moveDown(tempPriceViewer.getSource());
            this.update();
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
        this.calc.addPrice();
        CalcPriceViewer tmp=new CalcPriceViewer(this);
        CalcPrice temprpice=this.calc.getPrice(this.calc.getPriceCount()-1);
        tmp.setPrice(temprpice);
        if (this.pwcol!=null)
        {
            this.pwcol.getLast().setNext(tmp);
        }
        if (this.pwcol==null)
        {
            this.pwcol=new ArrayList<>(1);
        }
        this.pwcol.add(tmp);
        this.setBounds(this.top,this.width);
        this.mf.reFit();

    };
    public void addHeader()
    {
        JOptionPane.showMessageDialog(null, "В данной версии программы смета не имеет заголовков");
    };
    public void addPart()
    {
        JOptionPane.showMessageDialog(null, "В данной версии программы смета не имеет разделов");
    };
    public void addWorker()
    {
        JOptionPane.showMessageDialog(null, "Bookviewer addWorker");
        if (this.activePrice!=null)
        {
            CalcPrice tmp=((CalcPriceViewer)this.activePrice).getSource();
            tmp.addWorker();
            ((CalcPriceViewer)this.activePrice).updateIresourses();
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
            CalcPrice tmp=((CalcPriceViewer)this.activePrice).getSource();
            tmp.addMachine();
            ((CalcPriceViewer)this.activePrice).updateIresourses();
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
            CalcPrice tmp=((CalcPriceViewer)this.activePrice).getSource();
            tmp.addMaterial();
            ((CalcPriceViewer)this.activePrice).updateIresourses();
            this.setBounds(this.top,this.width);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Выберите расценку");
        }
    };

    public void scroll(int value)
    {
        this.viewerbase.setLocation(0,-value);
    };
    public int getHieght(){return 0;};
    public void open(){};
    public void save(){};
    public void close(){};
}
