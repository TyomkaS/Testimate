package Backend.Resourses;

import Backend.Rounder;

import java.util.Objects;

public class Worker  extends Resourse implements IResourse
{
    protected double workerSingleprice;

    //Конструкторы
    public Worker()
    {
        super();
        this.id=null;
        this.workerSingleprice=0;
    }

    public Worker(String name)
    {
        super(name);
        this.id=null;
        this.workerSingleprice=0;
    }

    public Worker(String id, String name)
    {
        super(name);
        this.id=id;
        this.workerSingleprice=0;
    }

    public Worker(String id, String name, double singleprice)
    {
        super(id,name);
        this.workerSingleprice=singleprice;
    }

    public Worker(Worker worker)
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
    }

    public Worker(DBWorker worker)
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
    }

    public Worker(CalcWorker worker)
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
    }


    //Методы
    @Override
    public double getSingleWorkprice(boolean isCurrentPrice)
    {
        return this.workerSingleprice;
    }

    @Override
    public double getSinglePrice(boolean isCurrentPrice){return this.getSingleWorkprice(isCurrentPrice);}


    @Override
    public void setSingleWorkprice(double singleprice, boolean isCurrentPrice)

    {
        this.workerSingleprice=singleprice;
        this.workerSingleprice=Rounder.roundTenth(singleprice);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        if (    this.id.equals(worker.id) &&
                this.name.equals(worker.name) &&
                this.uom.equals(worker.uom) &&
                this.workerSingleprice==worker.workerSingleprice)
        {return true;}
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,uom,String.valueOf(workerSingleprice));
    }

}
