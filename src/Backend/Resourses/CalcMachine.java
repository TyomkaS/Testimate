package Backend.Resourses;

import Backend.Rounder;

public class CalcMachine extends DBMachine  implements IResourse
{
    protected double calcCount;
    protected double coefficient;
    protected double machindex;
    protected double operindex;

    //Конструкторы
    public CalcMachine()
    {
        super();
        this.id=null;
        this.calcCount=0;
        this.coefficient=1;
        this.machindex=1;
        this.operindex=1;
    }

    public CalcMachine(String name)
    {
        super(name);
        this.id=null;
        this.calcCount=0;
        this.coefficient=1;
        this.machindex=1;
        this.operindex=1;
    }

    public CalcMachine(String id, String name)
    {
        super(name);
        this.id=id;
        this.calcCount=0;
        this.coefficient=1;
        this.machindex=1;
        this.operindex=1;
    }

    public CalcMachine(String id, String name, double singleprice)
    {
        super(id,name,singleprice);
        this.calcCount=0;
        this.coefficient=1;
        this.machindex=1;
        this.operindex=1;
    }

    public CalcMachine(String id, String name, double singleprice, double normalCount)
    {
        super(id,name,singleprice,normalCount);
        this.calcCount=0;
        this.coefficient=1;
        this.machindex=1;
        this.operindex=1;
    }

    public CalcMachine(Machine machine)
    {
        super(machine);
        this.calcCount=0;
        this.coefficient=1;
        this.machindex=1;
        this.operindex=1;
    }

    public CalcMachine(Machine machine, double normalCount)
    {
        super(machine,normalCount);
        this.calcCount=0;
        this.coefficient=1;
        this.machindex=1;
        this.operindex=1;
    }

    public CalcMachine(DBMachine machine)
    {
        super(machine);
        this.calcCount=0;
        this.coefficient=1;
        this.machindex=1;
        this.operindex=1;
    }

    public CalcMachine(DBMachine machine, double totalCount)
    {
        super(machine);
        this.calcCount=totalCount;
        this.coefficient=1;
        this.machindex=1;
        this.operindex=1;
    }

    public CalcMachine(DBMachine machine, double totalCount, double coef)
    {
        super(machine);
        this.calcCount=totalCount;
        this.coefficient=coef;
        this.machindex=1;
        this.operindex=1;
    }

    public CalcMachine(DBMachine machine, double totalCount, double coef, double machind, double operind)
    {
        super(machine);
        this.calcCount=totalCount;
        this.coefficient=coef;
        this.machindex=machind;
        this.operindex=operind;
    }

    public CalcMachine(CalcMachine machine)
    {
        super(machine);
        if (machine==null)
        {
            this.calcCount=0;
        }
        else
        {
            this.calcCount=machine.calcCount;
        }
        this.coefficient=1;
        this.machindex=1;
        this.operindex=1;
    }

    //Методы
    public double getSingleMachprice(boolean isCurrentPrice)
    {
        if (isCurrentPrice)
        {
            //так сделано потому что индекс на экплуатацию машин и операторов может быть разный
            //1)вычисляется стоимость экпл.машины без оператора
            double machineprice=this.machineSingleprice-this.operatorSingleprice;
            //2)индексируется стоимость экпл.машины и к ней прибавляется индексированная стоимость оператора
            machineprice=machineprice*this.machindex+this.getSingleOperprice(true);
            //Так сделано для возвращения правильного округления
            machineprice=machineprice*100;
            machineprice=Math.round(machineprice);
            machineprice=machineprice/100;
            return machineprice;
        }
        else
        {
            return this.machineSingleprice;
        }
    }

    @Override
    public double getSingleOperprice(boolean isCurrentPrice)
    {
        if (isCurrentPrice)
        {
            double operprice = this.operatorSingleprice*this.operindex;
            operprice=Rounder.roundTenth(operprice);
            return operprice;
        }
        else
        {
            return this.operatorSingleprice;
        }
    }

    @Override
    public double getTotalMachprice(boolean isCurrentPrice)
    {
        double machineprice;
        if (isCurrentPrice)
        {
            //так сделано потому что индекс на экплуатацию машин и операторов может быть разный
            //1)вычисляется стоимость экпл.машины без оператора
            machineprice=this.machineSingleprice-this.operatorSingleprice;
            //2)индексируется стоимость экпл.машины и к ней прибавляется индексированная стоимость оператора
            machineprice=machineprice*this.machindex+this.getSingleOperprice(true);
            //3)умножается на количество
            machineprice = machineprice*this.getCalcTotalCount();
        }
        else
        {
            machineprice = this.getCalcTotalCount()*this.machineSingleprice;
        }
        //Так сделано для возвращения правильного округления
        machineprice=Rounder.roundTenth(machineprice);
        return machineprice;
    }

    @Override
    public double getTotalOperprice(boolean isCurrentPrice)
    {
        double operprice;
        if (isCurrentPrice)
        {
            operprice = this.getCalcTotalCount()*this.operatorSingleprice*this.operindex;
        }
        else
        {
            operprice = this.getCalcTotalCount()*this.operatorSingleprice;
        }
        //Так сделано для возвращения правильного округления
        operprice=Rounder.roundTenth(operprice);
        return operprice;
    }

    @Override
    public double getCalcCount(){return this.calcCount;}

    @Override
    public double getCalcTotalCount()
    {
        double totalCalcCount=this.calcCount*this.normalCount*this.coefficient;
        //Так сделано для возвращения правильного округления
        totalCalcCount=Rounder.roundTenth(totalCalcCount);
        return totalCalcCount;
    }

    @Override
    public double getCoef(){return this.coefficient;}

    @Override
    public double getindex(){return this.machindex;}

    @Override
    public double getOperIndex(){return this.operindex;}

    @Override
    public void setCalcCount(double calcCount)
    {this.calcCount=calcCount;}

    public void setCalcTotalCount(double totalCount)
    {
        this.normalCount=totalCount/this.coefficient/this.calcCount;
        this.normalCount=Rounder.roundTenth(this.normalCount);
    }

    @Override
    public void setCoef(double coef)
    {this.coefficient=coef;}

    @Override
    public void setIndex(double index)
    {this.machindex=index;}

    @Override
    public void setOperIndex(double index)
    {this.operindex=index;}

    @Override
    public void setSingleMachprice(double machSingleprice,boolean isCurrentPrice)
    {
        if (isCurrentPrice)
        {
            this.machineSingleprice=machSingleprice/this.machindex;
        }
        else
        {
            this.machineSingleprice=machSingleprice;
        }

        this.machineSingleprice= Rounder.roundTenth(this.machineSingleprice);
        if (this.operatorSingleprice>machSingleprice)
        {
            this.operatorSingleprice = 0;
        }


    }

    @Override
    public void setSingleOperprice(double operSingleprice,boolean isCurrentPrice)
    {
        if (isCurrentPrice)
        {
            operSingleprice = operSingleprice/this.operindex;
        }
        if (this.machineSingleprice<operSingleprice)
        {
            return;
        }
        this.operatorSingleprice=operSingleprice;
        this.operatorSingleprice= Rounder.roundTenth(this.operatorSingleprice);
    }

}
