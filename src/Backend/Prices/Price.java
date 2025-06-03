package Backend.Prices;

import Backend.Resourses.*;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Price  implements Serializable
{
    protected ArrayList<Worker> workers;
    protected ArrayList<Machine> machines;
    protected ArrayList<Material> materials;

    protected String id;
    protected String name;
    protected String uom;

    //Конструкторы
    public Price()
    {
        this.name=null;
        this.id=null;
        this.uom=null;
    }

    public Price(String name)
    {
        this.name=name;
        this.id=null;
        this.uom=null;
    }

    public Price(String id, String name)
    {
        this.name=name;
        this.id=id;
        this.uom=null;
    }

    public Price(DBPrice price)
    {
        this.name=price.name;
        this.id=price.id;
        this.uom=price.uom;
    }

    public Price(CalcPrice price)
    {
        this.name=price.name;
        this.id=price.id;
        this.uom=price.uom;
    }

    //Методы
    //Методы добавления ресурсов
    public abstract void addWorker();
    public abstract void addMachine();
    public abstract void addMaterial();
    public abstract void addWorker(Worker worker);
    public abstract void addMachine(Machine machine);
    public abstract void addMaterial(Material material);

    public abstract void addWorker(Worker worker, int index);
    public abstract void addMachine(Machine machine, int index);
    public abstract void addMaterial(Material material, int index);

    public boolean isEditable(){return true;};

    //Геттеры
    public String getID() {return this.id;}
    public String getName() {return this.name;}
    public String getUom() {return this.uom;}
    public double getCount(){return 0;}

    public abstract int getWorkersCount();
    public abstract int getMachineCount();
    public abstract int getMaterialCount();

    public abstract double getSinglePrice(boolean isCurrentPrice);
    public abstract double getSingleMachprice(boolean isCurrentPrice);
    public abstract double getSingleMatprice(boolean isCurrentPrice);
    public abstract double getSingleOperprice(boolean isCurrentPrice);
    public abstract double getSingleWorkprice(boolean isCurrentPrice);
    
    public abstract double getTotalPrice(boolean isCurrentPrice);
    public abstract double getTotalMachprice(boolean isCurrentPrice);
    public abstract double getTotalMatprice(boolean isCurrentPrice);
    public abstract double getTotalOperprice(boolean isCurrentPrice);
    public abstract double getTotalWorkprice(boolean isCurrentPrice);

    //Геттеры ресурсов
    public abstract IResourse getResourse(int index);
    public abstract String getResID(int index);
    public abstract String getResName(int index);
    public abstract String getResUom(int index);
    public abstract double getResNormalCount(int index);
    public abstract double getResCalcCount(int index);
    public abstract double getResTotalCount(int index);
    public abstract double getResSingleprice (int index, boolean isCurrentPrice);
    public abstract double getResSingleMachprice (int index, boolean isCurrentPrice);
    public abstract double getResSingleMatprice (int index, boolean isCurrentPrice);
    public abstract double getResSingleOperprice (int index, boolean isCurrentPrice);
    public abstract double getResSingleWorkprice (int index, boolean isCurrentPrice);
    public abstract double getResTotalprice (int index, boolean isCurrentPrice);
    public abstract double getResTotalMachprice (int index, boolean isCurrentPrice);
    public abstract double getResTotalMatprice (int index, boolean isCurrentPrice);
    public abstract double getResTotalOperprice (int index, boolean isCurrentPrice);
    public abstract double getResTotalWorkprice (int index, boolean isCurrentPrice);
//    public abstract double getResCoef(int index);
//    public abstract double getResIndex(int index);
//    public abstract double getResOperIndex(int index);

    //Методы удаления ресурсов
    public void removeAll()
    {
        this.workers=null;
        this.machines=null;
        this.materials=null;
    };

    public abstract void removeSource(IResourse res);
    public abstract void removeWorker(int index);
    public abstract void removeMachine(int index);
    public abstract void removeMaterial(int index);

    //Сеттеры
    public void setID(String id) {this.id=id;}
    public void setName(String name){this.name=name;}
    public void setUom(String uom) {this.uom=uom;}
    public void setCount(double count){};

    //Сеттеры ресурсов
    public abstract void setResID(int index, String id);
    public abstract void setResName(int index,String name);
    public abstract void setResUom(int index,String uom);
    public abstract void setResSingleMachprice(int index,double machSingleprice,boolean isCurrentPrice);
    public abstract void setResSingleMatprice(int index,double singleprice,boolean isCurrentPrice);
    public abstract void setResSingleOperprice(int index,double operSingleprice,boolean isCurrentPrice);
    public abstract void setResSingleWorkprice(int index,double singleprice,boolean isCurrentPrice);
    public abstract void setResNormalCount(int index,double normalCount);
//    public abstract void setResCalcCount(int index,double totalCount);
//    public abstract void setResCoef(int index,double coef);
//    public abstract void setResIndex(int resindex,double index);
//    public abstract void setResOperIndex(int resindex,double index);

}
