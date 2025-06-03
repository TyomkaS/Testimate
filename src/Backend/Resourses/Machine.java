package Backend.Resourses;

import Backend.Rounder;

import java.util.Objects;

public class Machine  extends Resourse  implements IResourse
{
    protected double machineSingleprice;
    protected double operatorSingleprice;

    //Конструкторы
    public Machine()
    {
        super();
        this.id=null;
        this.machineSingleprice=0;
        this.operatorSingleprice = 0;
    }

    public Machine(String name)
    {
        super(name);
        this.id=null;
        this.machineSingleprice=0;
        this.operatorSingleprice = 0;
    }

    public Machine(String id, String name)
    {
        super(name);
        this.id=id;
        this.machineSingleprice = 0;
        this.operatorSingleprice = 0;
    }

    public Machine(String id, String name, double machSingleprice)
    {
        super(id,name);
        this.id=id;
        this.machineSingleprice = machSingleprice;
        this.operatorSingleprice = 0;
    }

    public Machine(String id, String name, double machSingleprice, double operSingleprice)
    {
        super(id,name);
        this.id=id;
        this.machineSingleprice = machSingleprice;
        this.operatorSingleprice = operSingleprice;
    }

    public Machine(Machine machine)
    {
        if (machine==null)
        {
            this.id=null;
            this.name=null;
            this.uom=null;
            this.machineSingleprice=0;
            this.operatorSingleprice = 0;
        }
        else
        {
            this.id = machine.id;
            this.name = machine.name;
            this.uom=machine.uom;
            this.machineSingleprice = machine.machineSingleprice;
            this.operatorSingleprice = machine.operatorSingleprice;
        }
    }

    public Machine(DBMachine machine)
    {
        if (machine==null)
        {
            this.id=null;
            this.name=null;
            this.uom=null;
            this.machineSingleprice=0;
            this.operatorSingleprice = 0;
        }
        else
        {
            this.id = machine.id;
            this.name = machine.name;
            this.uom=machine.uom;
            this.machineSingleprice = machine.machineSingleprice;
            this.operatorSingleprice = machine.operatorSingleprice;
        }
    }

    public Machine(CalcMachine machine)
    {
        if (machine==null)
        {
            this.id=null;
            this.name=null;
            this.uom=null;
            this.machineSingleprice=0;
            this.operatorSingleprice = 0;
        }
        else
        {
            this.id = machine.id;
            this.name = machine.name;
            this.uom=machine.uom;
            this.machineSingleprice = machine.machineSingleprice;
            this.operatorSingleprice = machine.operatorSingleprice;
        }
    }

    //Методы
    @Override
    public double getSingleMachprice(boolean isCurrentPrice)
    {
        return this.machineSingleprice;
    }

    @Override
    public double getSingleOperprice(boolean isCurrentPrice)
    {
        return this.operatorSingleprice;
    }

    @Override
    public double getSinglePrice(boolean isCurrentPrice){return this.getSingleMachprice(isCurrentPrice);}

    @Override
    public void setSingleMachprice(double machSingleprice,boolean isCurrentPrice)
    {
        if (this.operatorSingleprice>machSingleprice)
        {
            this.operatorSingleprice = 0;
        }
        this.machineSingleprice=machSingleprice;
        this.machineSingleprice= Rounder.roundTenth(this.machineSingleprice);
    }

    @Override
    public void setSingleOperprice(double operSingleprice,boolean isCurrentPrice)
    {
        if (this.machineSingleprice<operSingleprice)
        {
            return;
        }
        this.operatorSingleprice=operSingleprice;
        this.operatorSingleprice= Rounder.roundTenth(this.operatorSingleprice);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        if (    this.id.equals(machine.id) &&
                this.name.equals(machine.name) &&
                this.uom.equals(machine.uom) &&
                this.machineSingleprice==machine.machineSingleprice &&
                this.operatorSingleprice==machine.operatorSingleprice)
        {return true;}
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,uom,String.valueOf(machineSingleprice),String.valueOf(operatorSingleprice));
    }
}
