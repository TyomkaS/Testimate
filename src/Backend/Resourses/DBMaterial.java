package Backend.Resourses;

import Backend.Rounder;

public class DBMaterial  extends Material  implements IResourse
{
    protected double normalCount;

    //Конструкторы
    public DBMaterial()
    {
        super();
        this.id=null;
        this.materialSingleprice=0;
        this.normalCount=0;
    }

    public DBMaterial(String name)
    {
        super(name);
        this.id=null;
        this.materialSingleprice=0;
        this.normalCount=0;
    }

    public DBMaterial(String id, String name)
    {
        super(name);
        this.id=id;
        this.materialSingleprice=0;
        this.normalCount=0;
    }

    public DBMaterial(String id, String name, double singleprice)
    {
        super(id,name,singleprice);
        this.normalCount=0;
    }

    public DBMaterial(String id, String name, double singleprice, double normalCount)
    {
        super(id,name,singleprice);
        this.normalCount=normalCount;
    }

    public DBMaterial(Material material)
    {
        if (material==null)
        {
            this.id= null;
            this.name=null;
            this.materialSingleprice=0;
        }
        else
        {
            this.id= material.id;
            this.name=material.name;
            this.materialSingleprice=material.materialSingleprice;
        }
        this.normalCount=0;
    }

    public DBMaterial(Material material, double normalCount)
    {
        if (material==null)
        {
            this.id= null;
            this.name=null;
            this.materialSingleprice=0;
            this.normalCount=0;
        }
        else
        {
            this.id= material.id;
            this.name=material.name;
            this.materialSingleprice=material.materialSingleprice;
            this.normalCount=normalCount;
        }
    }

    public DBMaterial(DBMaterial material)
    {
        if(material==null)
        {
            this.id= null;
            this.name=null;
            this.materialSingleprice=0;
            this.normalCount=0;
        }
        else
        {
            this.id= material.id;
            this.name=material.name;
            this.materialSingleprice=material.materialSingleprice;
            this.normalCount=material.normalCount;
        }
    }

    public DBMaterial(CalcMaterial material)
    {
        super(material);
        if (material == null)
        {
            this.normalCount=0;
        }
        else
        {
            this.normalCount=material.normalCount;
        }
    }

    //Методы
    @Override
    public double getNormalCount(){return this.normalCount;}

    @Override
    public double getTotalMatprice(boolean isCurrentPrice)
    {
        double totalMatPrice =  this.normalCount*this.materialSingleprice;
        totalMatPrice= Rounder.roundTenth(totalMatPrice);
        return totalMatPrice;
    }

    @Override
    public double getTotalPrice(boolean isCurrentPrice){return this.getTotalMatprice(isCurrentPrice);}

    @Override
    public void setNormalCount(double normalCount)
    {this.normalCount=normalCount;}
}
