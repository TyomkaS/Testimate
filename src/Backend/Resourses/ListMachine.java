package Backend.Resourses;

import Backend.Rounder;

import java.util.ArrayList;
import java.util.Objects;

public class ListMachine implements IResourse
{
    private ArrayList<CalcMachine> collection;
    private String id;
    private String name;
    private String uom;
    private double machineSingleprice;
    private double operSingleprice;

    //Конструкторы


    public ListMachine(CalcMachine machine)
    {
        if (machine==null)
        {
            this.id=null;
            this.name=null;
            this.uom=null;
            this.machineSingleprice=0;
        }
        else
        {
            this.id = machine.id;
            this.name = machine.name;
            this.uom=machine.uom;
            this.machineSingleprice=machine.machineSingleprice;
            this.operSingleprice=machine.operatorSingleprice;
        }
        this.collection=new ArrayList<>(1);
        this.collection.add(machine);
    }

    //Методы
    public boolean add(CalcMachine machine)
    {
        if (this.collection.getFirst().equals(machine))
        {
            this.collection.add(machine);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o!=null)
        {
            if (getClass() != o.getClass())
            {
                CalcMachine tmp=new CalcMachine();
                if (tmp.getClass() == o.getClass())
                {
                    CalcMachine that=(CalcMachine) o;
                    if( this.id.equals(that.id) && this.name.equals(that.name) && this.uom.equals(that.uom) &&
                    this.machineSingleprice==that.machineSingleprice && this.operSingleprice==that.operatorSingleprice)
                    {return true;}
                }
            }
            else
            {
                ListMachine that=(ListMachine)o;
                if( this.id.equals(that.id) && this.name.equals(that.name) && this.uom.equals(that.uom) &&
                this.machineSingleprice==that.machineSingleprice && this.operSingleprice==that.operSingleprice)
                {return true;}
            }
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(collection, id, name, uom, machineSingleprice,operSingleprice);
    }

    public String getID(){return this.id;};
    public String getName(){return this.name;};
    public String getUom(){return this.uom;};

    public double getSinglePrice(boolean isCurrentPrice){return 0;}
    public double getSingleMachprice(boolean isCurrentPrice){return this.machineSingleprice;};
    public double getSingleMatprice(boolean isCurrentPrice){ return 0;};
    public double getSingleOperprice(boolean isCurrentPrice){return this.operSingleprice;};
    public double getSingleWorkprice(boolean isCurrentPrice){return 0;};

    public double getNormalCount(){return 0;};
    public double getTotalPrice(boolean isCurrentPrice)
    {
        if (this.collection!=null)
        {
            double totalprice=0;
            for (int i=0; i<this.collection.size(); i++)
            {
                totalprice+=this.collection.get(i).getTotalPrice(isCurrentPrice);
            }
            return totalprice;
        }
        return 0;
    };

    public double getTotalMachprice(boolean isCurrentPrice)
    {
        if (this.collection!=null)
        {
            double totalmachineprice=0;
            for (int i=0; i<this.collection.size(); i++)
            {
                totalmachineprice+=this.collection.get(i).getTotalMachprice(isCurrentPrice);
            }
            return totalmachineprice;
        }
        return 0;
    };
    public double getTotalMatprice(boolean isCurrentPrice){return 0;};
    public double getTotalOperprice(boolean isCurrentPrice)
    {
        if (this.collection!=null)
        {
            double totaloperprice=0;
            for (int i=0; i<this.collection.size(); i++)
            {
                totaloperprice+=this.collection.get(i).getTotalOperprice(isCurrentPrice);
            }
            return totaloperprice;
        }
        return 0;
    };
    public double getTotalWorkprice(boolean isCurrentPrice) {return 0;};

    public double getCalcCount(){return 0;};
    public double getCalcTotalCount()
    {
        if (this.collection!=null)
        {
            double totalcount=0;
            for (int i=0; i<this.collection.size(); i++)
            {
                totalcount+=this.collection.get(i).getCalcTotalCount();
            }
            return totalcount;
        }
        return 0;
    }
    public double getCoef(){return 0;};
    public double getindex(){return 0;};
    public double getOperIndex(){return 0;};

    public void remove(CalcMachine machine)
    {
        if (this.collection!=null)
        {
            for (int i=0; i<this.collection.size();i++)
            {
                if (this.collection.get(i)==machine)//проверяется именно ссылка
                {this.collection.remove(i);}
            }
        }
    }

    public void setID(String id) {this.id=id; for(CalcMachine item : this.collection){item.setID(id);}}
    public void setName(String name){this.name=name; for(CalcMachine item : this.collection){item.setName(name);}};
    public void setUom(String uom){this.uom=uom; for(CalcMachine item : this.collection){item.setUom(uom);}};
    public void setSingleMachprice(double machSingleprice,boolean isCurrentPrice)
    {
        if (!isCurrentPrice)
        {
            this.machineSingleprice= Rounder.roundTenth(machSingleprice);
            for(CalcMachine item : this.collection){item.setSingleMatprice(machSingleprice,isCurrentPrice);}
        }
    };
    public void setSingleMatprice(double singleprice,boolean isCurrentPrice){};
    public void setSingleOperprice(double operSingleprice,boolean isCurrentPrice)
    {
        if (!isCurrentPrice)
        {
            this.operSingleprice= Rounder.roundTenth(operSingleprice);
            for(CalcMachine item : this.collection){item.setSingleOperprice(operSingleprice,isCurrentPrice);}
        }};
    public void setSingleWorkprice(double singleprice,boolean isCurrentPrice){};

    public void setNormalCount(double normalCount){};
    public void setCalcCount(double totalCount){};
    public void setCalcTotalCount(double totalCount)
    {
        double presentTotalCount=this.getCalcTotalCount();
        double multiplayer;
        for (CalcMachine item: this.collection)
        {
            multiplayer=item.getCalcTotalCount()/presentTotalCount;
            item.setCalcTotalCount(multiplayer*totalCount);
        }
    }
    public void setCoef(double coef){};
    public void setIndex(double index){};
    public void setOperIndex(double index){};
}
