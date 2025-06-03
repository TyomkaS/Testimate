package Backend.Resourses;

public interface IResourse
{
    //Интерфейс нужен для того, чтобы возвращать русурсы из расценок по ссылке

    public String getID();
    public String getName();
    public String getUom();

    //Геттеры для Worker, Machine, Material и наследников
    public double getSinglePrice(boolean isCurrentPrice);
    public double getSingleMachprice(boolean isCurrentPrice);
    public double getSingleMatprice(boolean isCurrentPrice);
    public double getSingleOperprice(boolean isCurrentPrice);
    public double getSingleWorkprice(boolean isCurrentPrice);

    //Геттеры для DBWorker, DBMachine, DBMaterial и наследников
    public double getNormalCount();
    public double getTotalPrice(boolean isCurrentPrice);
    public double getTotalMachprice(boolean isCurrentPrice);
    public double getTotalMatprice(boolean isCurrentPrice);
    public double getTotalOperprice(boolean isCurrentPrice);
    public double getTotalWorkprice(boolean isCurrentPrice);

    //Геттеры для CalcWorker, CalcMachine, CalcMaterial и наследников
    public double getCalcCount();
    public double getCalcTotalCount();
    public double getCoef();
    public double getindex();
    public double getOperIndex();

    //Сеттеры для Worker, Machine, Material и наследников
    public void setID(String id);
    public void setName(String name);
    public void setUom(String uom);
    public void setSingleMachprice(double machSingleprice,boolean isCurrentPrice);
    public void setSingleMatprice(double singleprice,boolean isCurrentPrice);
    public void setSingleOperprice(double operSingleprice,boolean isCurrentPrice);
    public void setSingleWorkprice(double singleprice,boolean isCurrentPrice);

    //Сеттеры для DBWorker, DBMachine, DBMaterial и наследников
    public void setNormalCount(double normalCount);

    //Сеттеры для CalcWorker, CalcMachine, CalcMaterial
    public void setCalcCount(double totalCount);
    public void setCalcTotalCount(double totalCount);
    public void setCoef(double coef);
    public void setIndex(double index);
    public void setOperIndex(double index);
}
