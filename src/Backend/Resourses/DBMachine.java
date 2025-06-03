package Backend.Resourses;

import Backend.Rounder;

public class DBMachine extends Machine  implements IResourse
{
    protected double normalCount;

    //Конструкторы
    public DBMachine()
    {
        super();
        this.id=null;
        this.machineSingleprice=0;
        this.normalCount=0;
    }

    public DBMachine(String name)
    {
        super(name);
        this.id=null;
        this.machineSingleprice=0;
        this.normalCount=0;
    }

    public DBMachine(String id, String name)
    {
        super(name);
        this.id=id;
        this.machineSingleprice=0;
        this.normalCount=0;
    }

    public DBMachine(String id, String name, double machSingleprice)
    {
        super(id,name,machSingleprice);
        this.normalCount=0;
    }

    public DBMachine(String id, String name, double machSingleprice, double normalCount)
    {
        super(id,name,machSingleprice);
        this.normalCount=normalCount;
    }

    public DBMachine(String id, String name, double machSingleprice,double operSingleprice, double normalCount)
    {
        super(id,name, machSingleprice, operSingleprice);
        this.normalCount=normalCount;
    }

    public DBMachine(Machine machine)
    {
        super(machine);
        this.normalCount=0;
    }

    public DBMachine(Machine machine, double normalCount)
    {
        super(machine);
        if (machine==null)
        {
            this.normalCount=0;
        }
        else
        {
            this.normalCount=normalCount;
        }
    }

    public DBMachine(DBMachine machine)
    {
        super(machine);
        if(machine==null)
        {
            this.normalCount=0;
        }
        else
        {
            this.normalCount=machine.normalCount;
        }
    }

    public DBMachine(CalcMachine machine)
    {
        super(machine);
        if(machine==null)
        {
            this.normalCount=0;
        }
        else
        {
            this.normalCount=machine.normalCount;
        }
    }

    //Методы
    @Override
    public double getNormalCount(){return this.normalCount;}

    @Override
    public double getTotalMachprice(boolean isCurrentPrice)
    {
        double totalMachPrice =  this.normalCount*this.machineSingleprice;
        totalMachPrice= Rounder.roundTenth(totalMachPrice);
        return totalMachPrice;
    }

    @Override
    public double getTotalOperprice(boolean isCurrentPrice)
    {
        double totalOperPrice =  this.normalCount*this.operatorSingleprice;
        totalOperPrice= Rounder.roundTenth(totalOperPrice);
        return totalOperPrice;
    }

    @Override
    public double getTotalPrice(boolean isCurrentPrice){return this.getTotalMachprice(isCurrentPrice);}

    @Override
    public void setNormalCount(double normalCount) {this.normalCount=normalCount;}
}
