package Backend.Resourses;

import Backend.Rounder;

import java.util.Objects;

public class Material extends Resourse  implements IResourse
{
    protected double materialSingleprice;

    //Конструкторы
    public Material()
    {
        super();
        this.id=null;
        this.materialSingleprice=0;
    }

    public Material(String name)
    {
        super(name);
        this.id=null;
        this.materialSingleprice=0;
    }

    public Material(String id, String name)
    {
        super(name);
        this.id=id;
        this.materialSingleprice=0;
    }

    public Material(String id, String name, double singleprice)
    {
        super(id,name);
        this.materialSingleprice=singleprice;
    }

    public Material(Material material)
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
    }

    public Material(DBMaterial material)
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
    }

    public Material(CalcMaterial material)
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
    }


    //Методы
    @Override
    public double getSingleMatprice(boolean isCurrentPrice)
    {
        return this.materialSingleprice;
    }

    @Override
    public double getSinglePrice(boolean isCurrentPrice)
    {
        return this.materialSingleprice;
    }


    @Override
    public void setSingleMatprice(double singleprice,boolean isCurrentPrice)
    {
        this.materialSingleprice=singleprice;
        this.materialSingleprice = Rounder.roundTenth(this.materialSingleprice);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        if (    this.id.equals(material.id) &&
                this.name.equals(material.name) &&
                this.uom.equals(material.uom) &&
                this.materialSingleprice==material.materialSingleprice)
        {return true;}
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,uom,String.valueOf(materialSingleprice));
    }
}
