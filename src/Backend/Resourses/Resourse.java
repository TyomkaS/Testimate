package Backend.Resourses;

import java.io.Serializable;

public abstract class Resourse  implements Serializable
{
    protected String id;
    protected String name;
    protected String uom;

    //Конструкторы
    public Resourse()
    {
        this.name=null;
        this.id=null;
        this.uom=null;
    }

    public Resourse(String name)
    {
        this.name=name;
        this.id=null;
        this.uom=null;
    }

    public Resourse(String id, String name)
    {
        this.name=name;
        this.id=id;
        this.uom=null;
    }

    //Методы

    public String getID() {return this.id;}
    public String getName() {return this.name;}
    public String getUom() {return this.uom;}

    //Геттеры для Worker, Machine, Material и наследников
    public double getSinglePrice(boolean isCurrentPrice){return 0;}
    public double getSingleMachprice(boolean isCurrentPrice){return 0;}
    public double getSingleMatprice(boolean isCurrentPrice){return 0;}
    public double getSingleOperprice(boolean isCurrentPrice){return 0;}
    public double getSingleWorkprice(boolean isCurrentPrice){return 0;}

    //Геттеры для DBWorker, DBMachine, DBMaterial и наследников
    public double getNormalCount(){return 0;}
    public double getTotalPrice(boolean isCurrentPrice){return 0;}
    public double getTotalMachprice(boolean isCurrentPrice){return 0;}
    public double getTotalMatprice(boolean isCurrentPrice){return 0;}
    public double getTotalOperprice(boolean isCurrentPrice){return 0;}
    public double getTotalWorkprice(boolean isCurrentPrice){return 0;}

    //Геттеры для CalcWorker, CalcMachine, CalcMaterial и наследников
    public double getCalcCount(){return 0;}
    public double getCalcTotalCount(){return 0;}
    public double getCoef(){return 0;}
    public double getindex(){return 0;}
    public double getOperIndex(){return 0;}

    //Сеттеры для Worker, Machine, Material и наследников
    public void setID(String id) {this.id=id;}
    public void setName(String name)
    {
        this.name=name;
    }
    public void setUom(String uom) {this.uom=uom;}
    public void setSingleMachprice(double machSingleprice,boolean isCurrentPrice){}
    public void setSingleMatprice(double singleprice,boolean isCurrentPrice){}
    public void setSingleOperprice(double operSingleprice,boolean isCurrentPrice){}
    public void setSingleWorkprice(double singleprice,boolean isCurrentPrice){}

    //Сеттеры для DBWorker, DBMachine, DBMaterial и наследников
    public void setNormalCount(double normalCount){}

    //Сеттеры для CalcWorker, CalcMachine, CalcMaterial
    public void setCalcCount(double totalCount){}
    public void setCalcTotalCount(double totalCount){}
    public void setCoef(double coef){}
    public void setIndex(double index){}
    public void setOperIndex(double index){}
}
