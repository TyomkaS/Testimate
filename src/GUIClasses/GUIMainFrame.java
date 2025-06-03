package GUIClasses;

import Backend.Documents.Book;
import Backend.Documents.BookPart;
import Backend.Documents.Calculation;
import Backend.Header;
import Backend.Prices.CalcPrice;
import Backend.Prices.DBPrice;
import Backend.Resourses.*;
import GUIClasses.ControlPanels.*;
import GUIClasses.Viewers.BookViewer;
import GUIClasses.Viewers.CalcViewer;
import GUIClasses.Viewers.DocViewer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import static javax.swing.text.StyleConstants.Orientation;

public class GUIMainFrame  extends JFrame implements AdjustmentListener
{
    final int MINLINEHEIGHT=20;
    private GUIUpperRibbon uperribbon;
    private GUIMiddleRibbon middleribon;
    private GUILowerRibbon lowerribon;
    private GUIDocHeader docheader;
    private GUIDocSpace docspace;
    private GUIPanelState panelstate;
    private JScrollBar scrollY;
    private boolean scrollEnable;

    private boolean refitState;   //Эта переменная нужна для метода refit

    private int width,height;

    private DocViewer dv;
    private Object buffer;

    public GUIMainFrame()
    {
        this.setVisible(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dm = tk.getScreenSize();
        System.out.printf("Screen width=" +dm.width+"\n");
        System.out.printf("Screen height=" +dm.height+"\n");


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("T estimate");
        this.setMinimumSize(new Dimension(900,600));
        this.setLayout(null);
        this.setBounds((dm.width-900)/2, (dm.height-600)/2,900,600 );
        this.refitState=true;

        this.scrollY=new JScrollBar();
        this.add(scrollY);
        this.scrollY.setMinimum(0);
        this.scrollY.setMaximum(100);
        this.scrollY.setValue(0);
        this.scrollY.setOrientation(Scrollbar.VERTICAL);
        this.scrollY.setBackground(new java.awt.Color(65, 132, 232));
        this.scrollY.addAdjustmentListener(this);
        this.scrollEnable=false;

        this.uperribbon = new GUIUpperRibbon(this);
        this.middleribon = new GUIMiddleRibbon(this);
        this.lowerribon = new GUILowerRibbon(this);
        this.docheader = new GUIDocHeader(this);
        this.panelstate = new GUIPanelState(this);
        this.docspace = new GUIDocSpace(this);

        //this.dv=this.test2(this.docspace.getPanel());
        this.dv = new CalcViewer(this.docspace.getPanel());
        this.dv.setMainFrame(this);
        this.docspace.setDocViewer(this.dv);
        this.middleribon.setDocViewer(this.dv);

        this.fitInnerComponents();

        System.out.println("********New Session********");
        System.out.println("***************************");
        System.out.println("***************************");
        System.out.println("***************************");
        System.out.println("***************************");

        //Обработчик изменения размера окна
        this.addComponentListener(new ComponentAdapter()
        {

            @Override
            public void componentResized(ComponentEvent e)
            {
                System.out.printf(e.paramString()+"\n");
                GUIMainFrame tmp = (GUIMainFrame)e.getSource();
                tmp.fitInnerComponents();
            }
        });
    }

    //Обработчик изменения положения ScrollBar
    @Override
    public void adjustmentValueChanged (AdjustmentEvent arg0)
    {
        System.out.println("adjustmentValueChanged works");
        if (scrollEnable)
        {
            System.out.println(scrollY.getValue());
            dv.scroll(scrollY.getValue());
        }
    }

    //Обработчики кнопок
    public void copy(Object obj)
    {
        /*JOptionPane.showMessageDialog(null, "Mainframe copy");
        this.buffer=obj;*/
    }

    public void paste()
    {
        /*JOptionPane.showMessageDialog(null, "Mainframe copy");
        this.dv.paste(this.buffer);*/
    }

    public void fitInnerComponents()
    {
        Dimension parentsize = this.getSize();
        this.width= parentsize.width;
        this.height= parentsize.height;

        this.docspace.setBounds(0,this.MINLINEHEIGHT*7,parentsize.width-30,(parentsize.height-this.MINLINEHEIGHT*10));
        this.uperribbon.setBounds(0,0,parentsize.width,this.MINLINEHEIGHT);
        this.middleribon.setBounds(0,this.MINLINEHEIGHT,parentsize.width,this.MINLINEHEIGHT*3);
        this.lowerribon.setBounds(0,this.MINLINEHEIGHT*4,parentsize.width,this.MINLINEHEIGHT);
        this.docheader.setBounds(0,this.MINLINEHEIGHT*5,parentsize.width-30,this.MINLINEHEIGHT*2);

        this.panelstate.setBounds(0,(parentsize.height-this.MINLINEHEIGHT*3),parentsize.width,this.MINLINEHEIGHT);

        this.scrollY.setBounds(this.width-30,this.MINLINEHEIGHT*5,15,(parentsize.height-this.MINLINEHEIGHT*8));
    }

    public void fitScroll()
    {
        if (this.dv.getHeight()>this.docspace.getHeight())
        {
            this.scrollY.setMaximum(this.dv.getHeight());
            this.scrollEnable=true;
        }
        else
        {
            this.scrollEnable=false;
        }
        System.out.println("Scroll Enable "+this.scrollEnable);
    }

    public void open(){
        //JOptionPane.showMessageDialog(null, "Open file");
        JFileChooser ojfc = new JFileChooser();
        ojfc.showOpenDialog(this);
        File openfile=ojfc.getSelectedFile();
        System.out.println(openfile.getAbsolutePath());
        if (openfile.getAbsolutePath()!=null)
        {
            try(FileInputStream fileInputStream = new FileInputStream(openfile.getAbsolutePath()))
            {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Calculation calc =(Calculation) objectInputStream.readObject();
                JOptionPane.showMessageDialog(null, "Файл открыт");
                this.dv.setDocument(calc);
                this.reFit();
            }
            catch (FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(null, "Не удалось открыть файл"+e.getMessage());
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (ClassNotFoundException e)
            {
                JOptionPane.showMessageDialog(null, "Не подходящий тип данных"+e.getMessage());
                //throw new RuntimeException(e);
            }
        }
    }

    public void save()
    {
        //JOptionPane.showMessageDialog(null, "Save file");
        JFileChooser sjfc = new JFileChooser();
        sjfc.showSaveDialog(this);
        File savefile=sjfc.getSelectedFile();
        if (savefile.getAbsolutePath()!=null)
        {
            try(FileOutputStream fileOutputStream = new FileOutputStream(savefile.getAbsolutePath()))
            {
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(this.dv.getSourse());
                objectOutputStream.close();
                JOptionPane.showMessageDialog(null, "Файл сохранён");

            }
            catch (FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(null, "Не удалось сохранить файл"+e.getMessage());
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public void setMainMode()
    {
        System.out.println("Main Mode seted");
        this.middleribon.setMainMode();
        this.middleribon.setBounds(0,this.MINLINEHEIGHT,this.width,this.MINLINEHEIGHT*3);
        //this.middleribon.reSize(this.width,this.height*3,this.height);

    };

    public void setEditMode()
    {
        System.out.println("Edit Mode seted");
        this.middleribon.setEditMode();
        this.middleribon.setBounds(0,this.MINLINEHEIGHT,this.width,this.MINLINEHEIGHT*3);
        //this.middleribon.reSize(this.width,this.height*3,this.height);
    };

    public void setDocMode()
    {
        System.out.println("Doc Mode seted");
        this.middleribon.setDocMode();
        this.middleribon.setBounds(0,this.MINLINEHEIGHT,this.width,this.MINLINEHEIGHT*3);
        //this.middleribon.reSize(this.width,this.height*3,this.height);
    };

    public void setParamMode()
    {
        System.out.println("Param Mode seted");
        this.middleribon.setParamMode();
        this.middleribon.setBounds(0,this.MINLINEHEIGHT,this.width,this.MINLINEHEIGHT*3);
        //this.middleribon.reSize(this.width,this.height*3,this.height);
    };


    //Метод для корректного отображения
    public void reFit()
    {
        //Этот метод чисто костыль т.к. из-за использования JFrameTextArea
        //расположение компонентов оказывается не правильным
        int a=this.getExtendedState();

        if (a==6)//Т.е окно развёрнуто максимально
        {
            this.setExtendedState(0);
            this.setExtendedState(6);
        }
        else
        {
            if (this.refitState)
            {
                this.setBounds(this.getLocation().x,this.getLocation().y,this.width+1,this.height);
            }
            else
            {
                this.setBounds(this.getLocation().x,this.getLocation().y,this.width-1,this.height);
            }
            this.refitState=(!this.refitState);
        }
    }

    public void setTotalCost(String cost)
    {
        this.panelstate.setTotalCost(cost);
    }

    //Тестовые методы используемые до возможности открывать и сохранять файлы
    public DocViewer test1(JPanel panel)
    {
        IResourse w1 = new DBWorker();
        w1.setID("W-3r");
        w1.setName("Рабочий 3 разряд");
        w1.setUom("h/h");
        w1.setSingleWorkprice(200,false);

        IResourse w2 = new DBWorker();
        w2.setID("W-4r");
        w2.setName("Рабочий 4 разряд");
        w2.setUom("h/h");
        w2.setSingleWorkprice(250,false);

        IResourse w3 = new DBWorker();
        w3.setID("W-5r");
        w3.setName("Рабочий 5 разряд");
        w3.setUom("h/h");
        w3.setSingleWorkprice(300,false);

        //Создание машин
        IResourse mach1 = new DBMachine();
        mach1.setID("Cr-T01");
        mach1.setName("Кран башенный г/п 5т");
        mach1.setUom("m/h");
        mach1.setSingleMachprice(1000,false);
        mach1.setSingleOperprice(350,false);

        IResourse mach2 = new DBMachine();
        mach2.setID("Tr-T02");
        mach2.setName("Экскаватор на пневмоколёсном ходу");
        mach2.setUom("m/h");
        mach2.setSingleMachprice(2000,false);
        mach2.setSingleOperprice(500,false);

        IResourse mach3 = new DBMachine();
        mach3.setID("BM-1000");
        mach3.setName("Бетономешалка");
        mach3.setUom("m/h");
        mach3.setSingleMachprice(100,false);

        //Создание материалов
        IResourse mat1 = new DBMaterial();
        mat1.setID("M-M150");
        mat1.setName("Смесь М150");
        mat1.setUom("kg");
        mat1.setSingleMatprice(16,false);

        IResourse mat2 = new DBMaterial();
        mat2.setID("M-M300");
        mat2.setName("Пескобетон М300");
        mat2.setUom("kg");
        mat2.setSingleMatprice(20,false);

        IResourse mat3 = new DBMaterial();
        mat3.setID("M-Rng1,8/9");
        mat3.setName("Кольцо бетонное ф-1800 h-900");
        mat3.setUom("шт");
        mat3.setSingleMatprice(3000,false);

        IResourse mat4 = new DBMaterial();
        mat4.setID("C-PWD01");
        mat4.setName("Краса водно-дисперсионная");
        mat4.setUom("кг");
        mat4.setSingleMatprice(200,false);

        IResourse mat5 = new DBMaterial();
        mat5.setID("C-Gr");
        mat5.setName("Грунтовка");
        mat5.setUom("кг");
        mat5.setSingleMatprice(20,false);

        //Создание расценок
        DBPrice c1=new DBPrice();
        c1.setID("ГЭСН-20-01-003");
        c1.setName("Монтаж колодцев водопроводных в грунте");
        c1.setUom("10м3");
        c1.addWorker((DBWorker)w1);
        c1.addMachine((DBMachine)mach2);
        c1.addMaterial((DBMaterial)mat3);
        c1.addMaterial((DBMaterial)mat2);

        DBPrice c1a=new DBPrice();
        c1a.setID("ГЭСН-20-02-003");
        c1a.setName("Устройство днищ колодцев");
        c1a.setUom("м3");
        c1a.addWorker((DBWorker)w1);
        c1a.addMachine((DBMachine)mach2);
        c1a.addMaterial((DBMaterial)mat2);

        DBPrice c2=new DBPrice();
        c2.setID("ГЭСН-15-004-02");
        c2.setName("Штукатурка стен толщиной до 30мм");
        c2.setUom("100м2");
        c2.setCount(2);
        c2.addWorker((DBWorker)w3);
        mach1.setNormalCount(2);
        c2.addMachine((DBMachine)mach1);
        c2.addMaterial((DBMaterial)mat1);

        DBPrice c3=new DBPrice();
        c3.setID("ГЭСН-15-004-05");
        c3.setName("Окраска стен водно-десперсионной краской");
        c3.setUom("100м2");
        c3.setCount(3);
        c3.addWorker((DBWorker)w2);
        mach1.setNormalCount(1);
        c3.addMachine((DBMachine)mach1);
        c3.addMaterial((DBMaterial)mat5);
        c3.addMaterial((DBMaterial)mat4);

        DBPrice c4=new DBPrice();
        c4.setID("ГЭСН-15-004-04");
        c4.setName("Штукатурка потолков толщиной до 30мм");
        c4.setUom("100м2");
        c4.setCount(2);
        c4.addWorker((DBWorker)w3);
        mach1.setNormalCount(2.1);
        c4.addMachine((DBMachine)mach1);
        c4.addMaterial((DBMaterial)mat1);

        //Создание параграфов
        BookPart p1 = new BookPart();
        p1.setName("Работы по устройству колодцев");
        p1.add(c1);
        p1.add(c1a);

        BookPart p2 = new BookPart();
        p2.setName("Отделочные работы");
        p2.add(c2);
        p2.add(c3);
        p2.add(c4);

        Book book=new Book("Tyomka");
        book.add(p1);
        book.add(p2);

        BookViewer bv=new BookViewer(panel);
        bv.setDocument(book);

        return bv;
    }

    public DocViewer test2(JPanel panel)
    {
        //Создание рабочих
        IResourse w1 = new CalcWorker();
        w1.setID("W-3r");
        w1.setName("Рабочий 3 разряд");
        w1.setUom("h/h");
        w1.setSingleWorkprice(200,false);

        IResourse w2 = new CalcWorker();
        w2.setID("W-4r");
        w2.setName("Рабочий 4 разряд");
        w2.setUom("h/h");
        w2.setSingleWorkprice(250,false);

        IResourse w3 = new CalcWorker();
        w3.setID("W-5r");
        w3.setName("Рабочий 5 разряд");
        w3.setUom("h/h");
        w3.setSingleWorkprice(300,false);

        //Создание машин
        IResourse mach1 = new CalcMachine();
        mach1.setID("Cr-T01");
        mach1.setName("Кран башенный г/п 5т");
        mach1.setUom("m/h");
        mach1.setSingleMachprice(1000,false);
        mach1.setSingleOperprice(350,false);

        IResourse mach2 = new CalcMachine();
        mach2.setID("Tr-T02");
        mach2.setName("Экскаватор на пневмоколёсном ходу");
        mach2.setUom("m/h");
        mach2.setSingleMachprice(2000,false);
        mach2.setSingleOperprice(500,false);

        IResourse mach3 = new CalcMachine();
        mach3.setID("BM-1000");
        mach3.setName("Бетономешалка");
        mach3.setUom("m/h");
        mach3.setSingleMachprice(100,false);

        //Создание материалов
        IResourse mat1 = new CalcMaterial();
        mat1.setID("M-M150");
        mat1.setName("Смесь М150");
        mat1.setUom("kg");
        mat1.setSingleMatprice(16,false);

        IResourse mat2 = new CalcMaterial();
        mat2.setID("M-M300");
        mat2.setName("Пескобетон М300");
        mat2.setUom("kg");
        mat2.setSingleMatprice(20,false);

        IResourse mat3 = new CalcMaterial();
        mat3.setID("M-Rng1,8/9");
        mat3.setName("Кольцо бетонное ф-1800 h-900");
        mat3.setUom("шт");
        mat3.setSingleMatprice(3000,false);

        IResourse mat4 = new CalcMaterial();
        mat4.setID("C-PWD01");
        mat4.setName("Краса водно-дисперсионная");
        mat4.setUom("кг");
        mat4.setSingleMatprice(200,false);

        IResourse mat5 = new CalcMaterial();
        mat5.setID("C-Gr");
        mat5.setName("Грунтовка");
        mat5.setUom("кг");
        mat5.setSingleMatprice(20,false);

        //Создание расценок
        CalcPrice c1=new CalcPrice();
        c1.setID("ГЭСН-20-01-003");
        c1.setName("Монтаж колодцев водопроводных в грунте");
        c1.setUom("10м3");

        CalcPrice c2=new CalcPrice();
        c2.setID("ГЭСН-15-004-02");
        c2.setName("Штукатурка стен толщиной до 30мм");
        c2.setUom("100м2");
        c2.setCount(2);
        w3.setNormalCount(2);
        c2.addWorker((CalcWorker)w3);
        mach1.setNormalCount(2);
        c2.addMachine((CalcMachine)mach1);
        c2.addMaterial((CalcMaterial)mat1);

        CalcPrice c3=new CalcPrice();
        c3.setID("ГЭСН-15-004-05");
        c3.setName("Окраска стен водно-десперсионной краской");
        c3.setUom("100м2");
        c3.setCount(3);
        c3.addWorker((CalcWorker)w2);
        mach1.setNormalCount(1);
        c3.addMachine((CalcMachine)mach1);
        c3.addMaterial((CalcMaterial)mat5);
        c3.addMaterial((CalcMaterial)mat4);

        CalcPrice c4=new CalcPrice();
        c4.setID("ГЭСН-15-004-04");
        c4.setName("Штукатурка потолков толщиной до 30мм");
        c4.setUom("100м2");
        c4.setCount(2);
        w3.setNormalCount(1);
        c4.addWorker((CalcWorker)w3);
        mach1.setNormalCount(2.1);
        c4.addMachine((CalcMachine)mach1);
        c4.addMaterial((CalcMaterial)mat1);

        Calculation calc=new Calculation();
        calc.addPrice(c2);
        calc.addPrice(c3);
        calc.addPrice(c4);

        CalcViewer cv=new CalcViewer(panel);
        cv.setDocument(calc);

        return cv;
    }
}
