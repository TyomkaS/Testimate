package Backend.Resourses;

import Backend.Rounder;

public class DBWorker extends Worker  implements IResourse
{
    protected double normalCount;

    //Конструкторы
    public DBWorker()
    {
        super();
        this.id=null;
        this.workerSingleprice=0;
        this.normalCount=0;
    }

    public DBWorker(String name)
    {
        super(name);
        this.id=null;
        this.workerSingleprice=0;
        this.normalCount=0;
    }

    public DBWorker(String id, String name)
    {
        super(name);
        this.id=id;
        this.workerSingleprice=0;
        this.normalCount=0;
    }

    public DBWorker(String id, String name, double singleprice)
    {
        super(id,name,singleprice);
        this.normalCount=0;
    }

    public DBWorker(String id, String name, double singleprice, double normalCount)
    {
        super(id,name,singleprice);
        this.normalCount=normalCount;
    }

    public DBWorker(Worker worker)
    {
        super(worker);
        this.normalCount=0;
    }

    public DBWorker(Worker worker, double normalCount)
    {
        super(worker);
        if (worker==null)
        {
            this.normalCount=0;
        }
        else
        {
            this.normalCount=normalCount;
        }
    }

    public DBWorker(DBWorker worker)
    {
        super(worker);
        if (worker==null)
        {
            this.normalCount=0;
        }
        else
        {
            this.normalCount=worker.normalCount;
        }
    }

    public DBWorker(CalcWorker worker)
    {
        super(worker);
        if (worker == null)
        {
            this.normalCount=0;
        }
        else
        {
            this.normalCount=worker.normalCount;
        }
    }

    //Методы
    @Override
    public double getNormalCount(){return this.normalCount;}

    @Override
    public double getTotalWorkprice(boolean isCurrentPrice)
    {
        double totalWorkPrice=this.normalCount*this.workerSingleprice;
        totalWorkPrice= Rounder.roundTenth(totalWorkPrice);
        return totalWorkPrice;
    }

    @Override
    public double getTotalPrice(boolean isCurrentPrice){return this.getTotalWorkprice(isCurrentPrice);}

    @Override
    public void setNormalCount(double normalCount) {this.normalCount=normalCount;}
}
