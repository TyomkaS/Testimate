package Backend;

public class Header
{
    protected String name;

    public Header()
    {
        this.name=null;
    }

    public Header(String name)
    {
        this.name=name;
    }

    public String getID()
    {
        return null;
    }
    public String getName()
    {
        return this.name;
    }

    //Геттеры для CalcWorker, CalcMachine, CalcMaterial
    public double getPresentSingleMachpriece(){return 0;}
    public double getPresentSingleMatpriece(){return 0;}
    public double getPresentSingleOperpriece(){return 0;}
    public double getPresentSingleWorkpriece(){return 0;}

    public double getPresentNormalMachpriece(){return 0;}
    public double getPresentNormalMatpriece(){return 0;}
    public double getPresentNormalOperpriece(){return 0;}
    public double getPresentNormalWorkpriece(){return 0;}

    public double getPresentTotalMachpriece(){return 0;}
    public double getPresentTotalMatpriece(){return 0;}
    public double getPresentTotalOperpriece(){return 0;}
    public double getPresentTotalWorkpriece(){return 0;}
    public double getBasicTotalMachpriece(){return 0;}
    public double getBasicTotalMatpriece(){return 0;}
    public double getBasicTotalOperpriece(){return 0;}
    public double getBasicTotalWorkpriece(){return 0;}

    public double getCalcCount(){return 0;}
    public double getCalcTotalCount(){return 0;}

    //Геттеры для DBWorker, DBMachine, DBMaterial  и наследников
    public double getNormalCount(){return 0;}
    public double getNormalMachpriece(){return 0;}
    public double getNormalMatpriece(){return 0;}
    public double getNormalOperpriece(){return 0;}
    public double getNormalWorkprice(){return 0;}

    //Геттеры для Worker, Machine, Material и наследников
    public double getSingleMachprice(){return 0;}
    public double getSingleMatprice(){return 0;}
    public double getSingleOperprice(){return 0;}
    public double getSingleWorkprice(){return 0;}

    //Сеттеры для Worker, Machine, Material и наследников
    public void setID(String id) {}
    public void setName(String name)
    {
        this.name=name;
    }
    public void setSingleMachprice(double machSingleprice){}
    public void setSingleMatprice(double singleprice){}
    public void setSingleOperprice(double operSingleprice){}
    public void setSingleWorkprice(double singleprice){}

    //Сеттеры для DBWorker, DBMachine, DBMaterial и наследников
    public void setNormalCount(double normalCount){}

    //Сеттеры для CalcWorker, CalcMachine, CalcMaterial
    public void setCalcCount(double totalCount){}
    public void setCoef(double coef){}
    public void setIndex(double index){}
    public void setOperIndex(double index){}

    public void print()
    {
        System.out.printf("ID отсутсвует " +this.name+"\n" );
    }
}
