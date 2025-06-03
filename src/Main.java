import Backend.Resourses.*;
import GUIClasses.GUIMainFrame;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        //ArrayList <Resourse> res= new ArrayList<>();


        new GUIMainFrame();
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        //System.out.printf("Hello and welcome!");

//        for (int i = 1; i <= 5; i++) {
//            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//            System.out.println("i = " + i);
//        }
//        Inner a = new Inner(3);
//
//        System.out.printf(Integer.toString(a.getValue())+"\n");
//        a.setValue(5);
//        System.out.printf(Integer.toString(a.getValue())+"\n");
//
//        Inner b = a;
//        System.out.printf(Integer.toString(b.getValue())+"\n");
//        b.setValue(4);
//        System.out.printf("===============================\n");
//        System.out.printf(Integer.toString(a.getValue())+"\n");
//        System.out.printf(Integer.toString(b.getValue())+"\n");
//
//        Inner c = new Inner(b);
//        System.out.printf("*******************************\n");
//        System.out.printf(Integer.toString(a.getValue())+"\n");
//        System.out.printf(Integer.toString(b.getValue())+"\n");
//        System.out.printf(Integer.toString(c.getValue())+"\n");
//        System.out.printf("\n");
//        c.setValue(7);
//        System.out.printf(Integer.toString(a.getValue())+"\n");
//        System.out.printf(Integer.toString(b.getValue())+"\n");
//        System.out.printf(Integer.toString(c.getValue())+"\n");
    }

    public void testResourses()
    {

    }

    public void printResource(Worker worker)
    {
        System.out.printf("|" +worker.getID()+"|"+worker.getName()+"|"+worker.getUom()+"|"+worker.getNormalCount()+"||");
        //System.out.printf(worker.getSingleWorkprice()+"|"+worker.getSingleMachprice()+"|");
        //System.out.printf(worker.getSingleOperprice()+"|"+worker.getSingleMatprice()+"||");
    }

    public void printResource(DBWorker worker)
    {

    }

    public void printResource(CalcWorker worker)
    {

    }

    public void printResource(Machine machine)
    {

    }

    public void printResource(DBMachine machine)
    {

    }

    public void printResource(CalcMachine machine)
    {

    }

    public void printResource(Material material)
    {

    }

    public void printResource(DBMaterial material)
    {

    }

    public void printResource(CalcMaterial material)
    {

    }
}

//class Inner
//{
//    private int value;
//
//    public Inner()
//    {
//        this.value=0;
//    }
//
//    public Inner(int val)
//    {
//        this.value=val;
//    }
//
//    public Inner(Inner copy)
//    {
//        this.value = copy.value;
//    }
//
//    public int getValue()
//    {
//        return value;
//    }
//
//    public void setValue(int val)
//    {
//        this.value=val;
//    }
//}