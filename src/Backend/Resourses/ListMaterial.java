package Backend.Resourses;

import Backend.Rounder;

import java.util.ArrayList;
import java.util.Objects;

public class ListMaterial implements IResourse
{
    private ArrayList<CalcMaterial> collection;
    private String id;
    private String name;
    private String uom;
    private double materialSingleprice;

    //Конструкторы
    public ListMaterial(CalcMaterial material)
    {
        if (material==null)
        {
            this.id=null;
            this.name=null;
            this.uom=null;
            this.materialSingleprice=0;
        }
        else
        {
            this.id = material.id;
            this.name = material.name;
            this.uom=material.uom;
            this.materialSingleprice=material.materialSingleprice;
        }
        this.collection=new ArrayList<>(1);
        this.collection.add(material);
    }

    //Методы
    public boolean add(CalcMaterial material)
    {
        if (this.collection.getFirst().equals(material))
        {
            this.collection.add(material);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o != null)
        {
            if (getClass() != o.getClass())
            {
                CalcMaterial tmp=new CalcMaterial();
                if (tmp.getClass() == o.getClass())
                {
                    CalcMaterial that = (CalcMaterial)o;
                    if( this.id.equals(that.id) && this.name.equals(that.name) && this.uom.equals(that.uom) &&
                            this.materialSingleprice==that.materialSingleprice){return true;}
                }
            }
            else
            {
                ListMaterial that = (ListMaterial) o;
                if( this.id.equals(that.id) && this.name.equals(that.name) && this.uom.equals(that.uom) &&
                        this.materialSingleprice==that.materialSingleprice){return true;}
            }
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(collection, id, name, uom, materialSingleprice);
    }

    public String getID(){return this.id;};
    public String getName(){return this.name;};
    public String getUom(){return this.uom;};

    public double getSinglePrice(boolean isCurrentPrice){ return this.materialSingleprice;}
    public double getSingleMachprice(boolean isCurrentPrice){return 0;};
    public double getSingleMatprice(boolean isCurrentPrice){ return this.materialSingleprice;};
    public double getSingleOperprice(boolean isCurrentPrice){return 0;};
    public double getSingleWorkprice(boolean isCurrentPrice){return 0;};

    public double getNormalCount(){return 0;};
    public double getTotalPrice(boolean isCurrentPrice)
    {
        if (this.collection!=null)
        {
            double totalprice=0;
            for (int i=0; i<this.collection.size(); i++)
            {
                totalprice += this.collection.get(i).getTotalPrice(isCurrentPrice);
            }
            return totalprice;
        }
        return 0;
    };

    public double getTotalMachprice(boolean isCurrentPrice){return 0;};
    public double getTotalMatprice(boolean isCurrentPrice)
    {
        if (this.collection!=null)
        {
            double totalmatprice=0;
            for (int i=0; i<this.collection.size(); i++)
            {
                totalmatprice=+this.collection.get(i).getTotalMatprice(isCurrentPrice);
            }
            return totalmatprice;
        }
        return 0;
    }

    public double getTotalOperprice(boolean isCurrentPrice){return 0;};
    public double getTotalWorkprice(boolean isCurrentPrice){return 0;};

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

    public void remove(CalcMaterial material)
    {
        if (this.collection!=null)
        {
            for (int i=0; i<this.collection.size();i++)
            {
                if (this.collection.get(i)==material)//проверяется именно ссылка
                {this.collection.remove(i);}
            }
        }
    }

    public void setID(String id) {this.id=id; for(CalcMaterial item : this.collection){item.setID(id);}}
    public void setName(String name){this.name=name; for(CalcMaterial item : this.collection){item.setName(name);}};
    public void setUom(String uom){this.uom=uom; for(CalcMaterial item : this.collection){item.setUom(uom);}};
    public void setSingleMachprice(double machSingleprice,boolean isCurrentPrice){};
    public void setSingleMatprice(double singleprice,boolean isCurrentPrice)
    {
        if (!isCurrentPrice)
        {
            this.materialSingleprice=Rounder.roundTenth(singleprice);
            for(CalcMaterial item : this.collection){item.setSingleMatprice(singleprice,isCurrentPrice);}
        }
    }
    public void setSingleOperprice(double operSingleprice,boolean isCurrentPrice){};
    public void setSingleWorkprice(double singleprice,boolean isCurrentPrice){};

    public void setNormalCount(double normalCount){};
    public void setCalcCount(double totalCount){};
    public void setCalcTotalCount(double totalCount)
    {
        double presentTotalCount=this.getCalcTotalCount();
        double multiplayer;
        for (CalcMaterial item: this.collection)
        {
            multiplayer=item.getCalcTotalCount()/presentTotalCount;
            item.setCalcTotalCount(multiplayer*totalCount);
        }
    }
    public void setCoef(double coef){};
    public void setIndex(double index){};
    public void setOperIndex(double index){};
}
