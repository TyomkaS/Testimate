package Backend.Resourses;

import Backend.Rounder;

import java.util.ArrayList;
import java.util.Objects;

public class ListWorker implements IResourse
{
    private ArrayList<CalcWorker> collection;
    private String id;
    private String name;
    private String uom;
    private double workerSingleprice;

    //Конструкторы


    public ListWorker(CalcWorker worker)
    {
        if (worker==null)
        {
            this.id=null;
            this.name=null;
            this.uom=null;
            this.workerSingleprice=0;
        }
        else
        {
            this.id = worker.id;
            this.name = worker.name;
            this.uom=worker.uom;
            this.workerSingleprice=worker.workerSingleprice;
        }
        this.collection=new ArrayList<>(1);
        this.collection.add(worker);
    }

    //Методы
    public boolean add(CalcWorker worker)
    {
        if (this.collection.getFirst().equals(worker))
        {
            this.collection.add(worker);
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
                CalcWorker tmp=new CalcWorker();
                if (tmp.getClass() == o.getClass())
                {
                    CalcWorker that=(CalcWorker) o;
                    if( this.id.equals(that.id) && this.name.equals(that.name) && this.uom.equals(that.uom) &&
                            this.workerSingleprice==that.workerSingleprice){return true;}
                }
            }
            else
            {
                ListWorker that=(ListWorker)o;
                if( this.id.equals(that.id) && this.name.equals(that.name) && this.uom.equals(that.uom) &&
                        this.workerSingleprice==that.workerSingleprice){return true;}
            }
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(collection, id, name, uom, workerSingleprice);
    }

    public String getID(){return this.id;};
    public String getName(){return this.name;};
    public String getUom(){return this.uom;};

    public double getSinglePrice(boolean isCurrentPrice){ return this.workerSingleprice;}
    public double getSingleMachprice(boolean isCurrentPrice){return 0;};
    public double getSingleMatprice(boolean isCurrentPrice){ return 0;};
    public double getSingleOperprice(boolean isCurrentPrice){return 0;};
    public double getSingleWorkprice(boolean isCurrentPrice){return this.workerSingleprice;};

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

    public double getTotalMachprice(boolean isCurrentPrice){return 0;};
    public double getTotalMatprice(boolean isCurrentPrice){return 0;};
    public double getTotalOperprice(boolean isCurrentPrice){return 0;};
    public double getTotalWorkprice(boolean isCurrentPrice)
    {if (this.collection!=null)
    {
        double totalworkprice=0;
        for (int i=0; i<this.collection.size(); i++)
        {
            totalworkprice+=this.collection.get(i).getTotalWorkprice(isCurrentPrice);
        }
        return totalworkprice;
    }
        return 0;
    };

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

    public void setID(String id) {this.id=id; for(CalcWorker item : this.collection){item.setID(id);}}
    public void setName(String name){this.name=name; for(CalcWorker item : this.collection){item.setName(name);}};
    public void setUom(String uom){this.uom=uom; for(CalcWorker item : this.collection){item.setUom(uom);}};
    public void setSingleMachprice(double machSingleprice,boolean isCurrentPrice){};
    public void setSingleMatprice(double singleprice,boolean isCurrentPrice){};
    public void setSingleOperprice(double operSingleprice,boolean isCurrentPrice){};
    public void setSingleWorkprice(double singleprice,boolean isCurrentPrice)
    {
        if (!isCurrentPrice)
        {
            this.workerSingleprice= Rounder.roundTenth(singleprice);
            for(CalcWorker item : this.collection){item.setSingleWorkprice(singleprice,isCurrentPrice);}
        }
    }

    public void remove(CalcWorker worker)
    {
        if (this.collection!=null)
        {
            for (int i=0; i<this.collection.size();i++)
            {
                if (this.collection.get(i)==worker)//проверяется именно ссылка
                {this.collection.remove(i);}
            }
        }
    }

    public void setNormalCount(double normalCount){};
    public void setCalcCount(double totalCount){};
    public void setCalcTotalCount(double totalCount)
    {
        double presentTotalCount=this.getCalcTotalCount();
        double multiplayer;
        for (CalcWorker item: this.collection)
        {
            multiplayer=item.getCalcTotalCount()/presentTotalCount;
            item.setCalcTotalCount(multiplayer*totalCount);
        }
    }
    public void setCoef(double coef){};
    public void setIndex(double index){};
    public void setOperIndex(double index){};
}
