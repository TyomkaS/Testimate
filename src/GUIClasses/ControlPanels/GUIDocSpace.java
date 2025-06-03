package GUIClasses.ControlPanels;

import Backend.Documents.Book;
import Backend.Documents.BookPart;
import Backend.Documents.Calculation;
import Backend.Prices.CalcPrice;
import Backend.Prices.DBPrice;
import Backend.Resourses.*;
import GUIClasses.GUIMainFrame;
import GUIClasses.Viewers.*;

import javax.swing.*;
import java.awt.*;

public class GUIDocSpace
{
    //Поля
    private GUIMainFrame parent;
    private JPanel docSpace;
    private DocViewer activedocviewer;
    private int height;

    //Для тестов ElementViewer и PriceViewer
    /*private ElementViewer test;
    private ElementViewer test1;
    private ElementViewer test2;
    private PriceViewer test3;
    private PriceViewer test4;
    private PriceViewer test5;
    private IResourse resOne, resTwo;
    private CalcWorker cw;
    private CalcMachine cm;
    private CalcMaterial cmt;
    private CalcMaterial cmt2;

    private CalcPrice cp;
    private DBPrice dp;

    private JTextArea sArea;
    private JScrollPane jsp;
    private JTextComponent jtc;*/

    //Для тестов Calculation и Calviewer
    /*private CalcViewer cv;
    private Calculation calc;
    private CalcPriceViewer price1;
    private CalcPriceViewer price2;
    private CalcPriceViewer price3;*/

    //Для тестов Book и BookViewer
    private BookViewer bv;
    private Book book;
    private BookPartViewer bpv;
    private BookPartViewer bpv2;
    private DBPriceViewer price1;
    private DBPriceViewer price2;
    private DBPriceViewer price3;
    private DBPriceViewer price4;
    private DBPriceViewer price5;

    //Конструктор
    public GUIDocSpace (GUIMainFrame parent)
    {
        this.parent=parent;
        docSpace = new JPanel();
        parent.add(docSpace);
        docSpace.setBackground(Color.darkGray);
        this.height=0;
        //this.docSpace=null;

        // PriceViewer and ElementViewer test
        /*test=new ElementViewer(docSpace);
        test2=new ElementViewer(docSpace);

        resOne = new CalcMachine();
        resOne.setID("TR-01");
        resOne.setName("Tractor Bel50");
        resOne.setUom("m/h");
        resOne.setCalcCount(2);
        resOne.setNormalCount(2);
        resOne.setCoef(1.15);
        resOne.setSingleMachprice(3,false);
        resOne.setSingleOperprice(1,false);

        cm=new CalcMachine();
        cm.setID("TR-01");
        cm.setName("Tractor Bel50");
        cm.setUom("m/h");
        cm.setCalcCount(2);
        cm.setNormalCount(2);
        cm.setCoef(1.15);
        cm.setSingleMachprice(3,false);
        cm.setSingleOperprice(1,false);

        test2.setResourse(resOne);

        resTwo = new CalcWorker();
        resTwo.setID("W-01");
        resTwo.setName("Рабочий 3р-д");
        resTwo.setUom("w/h");
        resTwo.setCalcCount(2);
        resTwo.setNormalCount(2);
        resTwo.setCoef(1.15);
        resTwo.setSingleWorkprice(2,false);

        cw = new CalcWorker();
        cw.setID("W-01");
        cw.setName("Рабочий 3р-д");
        cw.setUom("w/h");
        cw.setCalcCount(2);
        cw.setNormalCount(2);
        cw.setCoef(1.15);
        cw.setSingleWorkprice(2,false);

        cmt = new CalcMaterial();
        cmt.setID("P-01");
        cmt.setName("Плинтус");
        cmt.setUom("m");
        cmt.setCalcCount(2);
        cmt.setNormalCount(1);
        cmt.setCoef(1.15);
        cmt.setSingleMatprice(2,false);

        cmt2 = new CalcMaterial();
        cmt2.setID("G-01");
        cmt2.setName("Гвоздь");
        cmt2.setUom("шт");
        cmt2.setCalcCount(2);
        cmt2.setNormalCount(1);
        cmt2.setCoef(1.15);
        cmt2.setSingleMatprice(0.35,false);

        cp=new CalcPrice();
        cp.setID("Расц-1");
        cp.setName("Прибивка плинтуса");
        cp.setUom("m");
        cp.setCount(20);
        cp.addWorker(cw);
        cp.addMachine(cm);
        cp.addMaterial(cmt2);
        cp.addMaterial(cmt,2);

        dp=new DBPrice();
        dp.setID("Расц-2");
        dp.setName("Прибивка плинтуса");
        dp.setUom("m");
        dp.addWorker(cw);
        dp.addMachine(cm);
        dp.addMaterial(cmt2);
        dp.addMaterial(cmt,2);

        test3=new PriceViewer(docSpace);
        test4=new PriceViewer(docSpace);
        test5=new PriceViewer(docSpace);
        test4.setPrice(cp);
        test4.setNext(test5);
        test5.setPrice(dp);*/

        //Calculation and CalculationViewer test
        /*this.cv=new CalcViewer(docSpace);
        this.calc=new Calculation();
        //this.cv.setCalculation(this.calc);

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
        c4.addWorker((CalcWorker)w3);
        mach1.setNormalCount(2.1);
        c4.addMachine((CalcMachine)mach1);
        c4.addMaterial((CalcMaterial)mat1);

        //calc.addPrice(c1);
        calc.addPrice(c2);
        calc.addPrice(c3);
        calc.addPrice(c4);

        this.cv.setDocument(this.calc);*/

        /*price1=new PriceViewer(this.docSpace);
        price2=new PriceViewer(this.docSpace);
        price3=new PriceViewer(this.docSpace);

        price1.setPrice(c1);
        price1.setNext(price2);

        price2.setPrice(c2);
        price2.setNext(price3);

        price3.setPrice(c3);*/


        //Book and BookViewer test
/*      //Создание рабочих
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

        this.book=new Book("Tyomka");
        this.book.add(p1);
        this.book.add(p2);

        this.bv=new BookViewer(docSpace);
        this.bv.setDocument(book);*/

/*        this.price1=new DBPriceViewer(docSpace);
        this.price1.setPrice(c1);
        this.price2=new DBPriceViewer(docSpace);
        this.price2.setPrice(c2);
        this.price1.setNext(price2);*/

       /* this.bpv=new BookPartViewer(docSpace);
        this.bpv.setBookPart(p2);
        this.bpv2=new BookPartViewer(docSpace);
        this.bpv2.setBookPart(p1);
        this.bpv.setNext(bpv2);*/
    }

    public JPanel getPanel(){return this.docSpace;}

    public int getHeight(){return this.height;}

    //Методы
    public void setBounds(int x, int y, int width, int height )
    {
        this.height=height;
        this.docSpace.setBounds(x,y,width,height);
        this.activedocviewer.setBounds(0,width);
        //this.bpv.setBounds(0,width);
        //this.price1.setBounds(0,width);
        //this.bv.setBounds(0,width);
        //this.cv.setBounds(0,width);
        /*price1.setBounds(0,width);
        price2.setBounds(price1.getBottom(), width);
        price3.setBounds(price2.getBottom(), width);*/

        /*test3.setBounds(0,width);
        //jsp.setBounds(0,0,100,50);
        this.test.setBounds(test3.getBottom(),width);
        this.test2.setBounds(test.getBottom(),width);
        this.test4.setBounds(test2.getBottom()+20,width);
        this.test5.setBounds(test4.getBottom(),width);*/
        //this.test3.setBounds(80,width);
    }

    public void setDocViewer(DocViewer doc)
    {this.activedocviewer=doc;}



}
