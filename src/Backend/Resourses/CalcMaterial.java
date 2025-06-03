package Backend.Resourses;

import Backend.Rounder;

public class CalcMaterial  extends DBMaterial  implements IResourse
{
    protected double calcCount;
    protected double coefficient;
    protected double index;

    //Конструкторы
    public CalcMaterial()
    {
        super();
        this.id=null;
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcMaterial(String name)
    {
        super(name);
        this.id=null;
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcMaterial(String id, String name)
    {
        super(name);
        this.id=id;
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcMaterial(String id, String name, double singleprice)
    {
        super(id,name,singleprice);
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcMaterial(String id, String name, double singleprice, double normalCount)
    {
        super(id,name,singleprice,normalCount);
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcMaterial(Material material)
    {
        super(material);
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcMaterial(Material material, double normalCount)
    {
        super(material,normalCount);
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcMaterial(DBMaterial material)
    {
        super(material);
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcMaterial(DBMaterial material, double totalCount)
    {
        super(material);
        this.calcCount=totalCount;
        this.coefficient=1;
        this.index=1;
    }

    public CalcMaterial(DBMaterial material, double totalCount, double coef)
    {
        super(material);
        this.calcCount=totalCount;
        this.coefficient=coef;
        this.index=1;
    }

    public CalcMaterial(DBMaterial material, double totalCount, double coef, double ind)
    {
        super(material);
        this.calcCount=totalCount;
        this.coefficient=coef;
        this.index=ind;
    }

    public CalcMaterial(CalcMaterial material)
    {
        super(material);
        if (material==null)
        {
            this.calcCount=0;
        }
        else
        {
            this.calcCount=material.calcCount;
        }
        this.coefficient=1;
        this.index=1;
    }

    //Методы
    @Override
    public double getSingleMatprice(boolean isCurrentPrice)
    {
        if(isCurrentPrice)
        {
            double materialprice = this.materialSingleprice*this.index;
            return materialprice = Rounder.roundTenth(materialprice);
        }
        else
        {
            return this.materialSingleprice;
        }
    }
    @Override
    public double getTotalMatprice(boolean isCurrentPrice)
    {
        double materialprice;
        if(isCurrentPrice)
        {
            materialprice = this.getCalcTotalCount()*this.materialSingleprice*this.index;
        }
        else
        {
            materialprice = this.getCalcTotalCount()*this.materialSingleprice;
        }
        return materialprice = Rounder.roundTenth(materialprice);
    }

    @Override
    public double getCalcCount(){return this.calcCount;}

    @Override
    public double getCalcTotalCount()
    {
        double totalCalcCount=this.calcCount*this.normalCount*this.coefficient;
        return totalCalcCount = Rounder.roundTenth(totalCalcCount);
    }

    @Override
    public double getCoef(){return this.coefficient;}

    @Override
    public double getindex(){return this.index;}

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
    {this.index=index;}

    @Override
    public void setSingleMatprice(double singleprice, boolean isCurrentPrice)

    {
        if (isCurrentPrice)
        {
            this.materialSingleprice=singleprice/this.index;
        }
        else
        {
            this.materialSingleprice=singleprice;
        }
        this.materialSingleprice=Rounder.roundTenth(singleprice);
    }
}
