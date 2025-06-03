package Backend.Resourses;

import Backend.Rounder;

public class CalcWorker extends DBWorker  implements IResourse
{
    protected double calcCount;
    protected double coefficient;
    protected double index;

    //Конструкторы
    public CalcWorker()
    {
        super();
        this.id=null;
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcWorker(String name)
    {
        super(name);
        this.id=null;
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcWorker(String id, String name)
    {
        super(name);
        this.id=id;
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcWorker(String id, String name, double singleprice)
    {
        super(id,name,singleprice);
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcWorker(String id, String name, double singleprice, double normalCount)
    {
        super(id,name,singleprice,normalCount);
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcWorker(Worker worker)
    {
        super(worker);
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcWorker(Worker worker, double normalCount)
    {
        super(worker,normalCount);
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcWorker(DBWorker worker)
    {
        super(worker);
        this.calcCount=0;
        this.coefficient=1;
        this.index=1;
    }

    public CalcWorker(DBWorker worker, double totalCount)
    {
        super(worker);
        this.calcCount=totalCount;
        this.coefficient=1;
        this.index=1;
    }

    public CalcWorker(DBWorker worker, double totalCount, double coef)
    {
        super(worker);
        this.calcCount=totalCount;
        this.coefficient=coef;
        this.index=1;
    }

    public CalcWorker(DBWorker worker, double totalCount, double coef, double ind)
    {
        super(worker);
        this.calcCount=totalCount;
        this.coefficient=coef;
        this.index=ind;
    }

    public CalcWorker(CalcWorker worker)
    {
        super(worker);
        if (worker==null)
        {
            this.calcCount=0;
        }
        else
        {
            this.calcCount=worker.calcCount;
        }
        this.coefficient=1;
        this.index=1;
    }

    //Методы
    @Override
    public double getSingleWorkprice(boolean isCurrentPrice)
    {
        if (isCurrentPrice)
        {
            double workerprice = this.workerSingleprice*this.index;
            return workerprice = Rounder.roundTenth(workerprice);

        }
        else
        {return this.workerSingleprice;}

    }

    @Override
    public double getTotalWorkprice(boolean isCurrentPrice)
    {
        double workerprice;
        if (isCurrentPrice)
        {
            workerprice = this.getCalcTotalCount()*this.workerSingleprice*this.index;
        }
        else
        {
            workerprice = this.getCalcTotalCount()*this.workerSingleprice;
        }
        return workerprice = Rounder.roundTenth(workerprice);
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
    public void setSingleWorkprice(double singleprice, boolean isCurrentPrice)

    {
        if (isCurrentPrice)
        {
            this.workerSingleprice=singleprice/this.index;
        }
        else
        {
            this.workerSingleprice=singleprice;
        }
        this.workerSingleprice=Rounder.roundTenth(singleprice);
    }
}
