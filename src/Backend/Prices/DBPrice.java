package Backend.Prices;

import Backend.Resourses.*;

import java.util.ArrayList;
import java.util.Objects;

public class DBPrice  extends Price
{
    private boolean isEditable;
    //Конструкторы
    public DBPrice()
    {
        super();
        this.workers = null;
        this.machines = null;
        this.materials = null;
        this.isEditable=true;
    }

    public DBPrice(String name)
    {
        super(name);
        this.workers = null;
        this.machines = null;
        this.materials = null;
    }

    public DBPrice(String id, String name)
    {
        super(id,name);
        workers = null;
        machines = null;
        materials = null;
        this.isEditable=true;
    }

    public DBPrice(DBPrice price)
    {
        super(price);
        workers = new ArrayList<>(price.getWorkersCount());
        machines = new ArrayList<>(price.getMachineCount());
        materials = new ArrayList<>(price.getMaterialCount());

        for (int i=0 ; i < price.getWorkersCount(); i++)
        {
            DBWorker tmp = new DBWorker(price.workers.get(i));
            this.workers.add(tmp);
        }

        for (int i=0 ; i < price.getMachineCount(); i++)
        {
            DBMachine tmp = new DBMachine(price.machines.get(i));
            this.machines.add(tmp);
        }

        for (int i=0 ; i < price.getMaterialCount(); i++)
        {
            DBMaterial tmp = new DBMaterial(price.materials.get(i));
            this.materials.add(tmp);
        }
    }

    public DBPrice(CalcPrice price)
    {
        super(price);
        workers = new ArrayList<>(price.getWorkersCount());
        machines = new ArrayList<>(price.getMachineCount());
        materials = new ArrayList<>(price.getMaterialCount());

        for (int i=0 ; i < price.getWorkersCount(); i++)
        {
            DBWorker tmp = new DBWorker(price.workers.get(i));
            this.workers.add(tmp);
        }

        for (int i=0 ; i < price.getMachineCount(); i++)
        {
            DBMachine tmp = new DBMachine(price.machines.get(i));
            this.machines.add(tmp);
        }

        for (int i=0 ; i < price.getMaterialCount(); i++)
        {
            DBMaterial tmp = new DBMaterial(price.materials.get(i));
            this.materials.add(tmp);
        }
    }

    //Методы
    //Методы добавления ресурсов
    @Override
    public void addWorker()
    {
        if (isEditable)
        {
            if (this.workers==null)
            {
                this.workers = new ArrayList<>(1);
            }

            DBWorker tmp = new DBWorker();
            this.workers.add(tmp);
        }
    }

    @Override
    public void addMachine()
    {
        if (isEditable)
        {
            if (this.machines==null)
            {
                this.machines = new ArrayList<>(1);
            }

            DBMachine tmp = new DBMachine();
            this.machines.add(tmp);
        }
    }

    @Override
    public void addMaterial()
    {
        if (isEditable)
        {
            if (this.materials==null)
            {
                this.materials = new ArrayList<>(1);
            }

            DBMaterial tmp = new DBMaterial();
            this.materials.add(tmp);
        }
    }

    @Override
    public void addWorker(Worker worker)
    {
        if (isEditable)
        {
            if (this.workers==null)
            {
                this.workers = new ArrayList<>(1);
            }

            this.workers.add(this.paramadpter(worker));
        }
    }

    @Override
    public void addMachine(Machine machine)
    {
        if (isEditable)
        {
            if (this.machines==null)
            {
                this.machines = new ArrayList<>(1);
            }

            this.machines.add(this.paramadapter(machine));
        }
    }

    @Override
    public void addMaterial(Material material)
    {
        if (isEditable)
        {
            if (this.materials==null)
            {
                this.materials = new ArrayList<>(1);
            }

            this.materials.add(this.paramadapter(material));
        }
    }

    @Override
    public void addWorker(Worker worker, int index)
    {
        if (isEditable)
        {
            if (this.workers==null)
            {
                this.workers = new ArrayList<>(1);
            }

            if (index<this.workers.size())
            {
                this.workers.add(index,this.paramadpter(worker));
            }
            else
            {
                this.workers.add(this.paramadpter(worker));
            }
        }
    }

    @Override
    public void addMachine(Machine machine, int index)
    {
        if (isEditable)
        {
            if (this.machines==null)
            {
                this.machines = new ArrayList<>(1);
            }

            DBMachine tmp = new DBMachine(machine);
            if (index<(this.workers.size()+this.machines.size()))
            {
                //индексы ресурсов отсчитываются от нулевого индекса материалов,
                //затем индекс машин+workers.size(),
                //затем индекс материалов++workers.size()+machines.size()
                this.machines.add((index - this.workers.size()),this.paramadapter(machine));
            }
            else
            {
                this.machines.add(this.paramadapter(machine));
            }
        }
    }

    @Override
    public void addMaterial(Material material, int index)
    {
        if (isEditable)
        {
            if (this.materials==null)
            {
                this.materials = new ArrayList<>(1);
            }

            DBMaterial tmp = new DBMaterial(material);
            if (index<(this.workers.size()+this.machines.size()+this.materials.size()))
            {
                //индексы ресурсов отсчитываются от нулевого индекса материалов,
                //затем индекс машин+workers.size(),
                //затем индекс материалов++workers.size()+machines.size()
                this.materials.add((index - this.workers.size()-this.machines.size()),this.paramadapter(material));
            }
            else
            {
                this.materials.add(this.paramadapter(material));
            }
        }
    }

    public boolean isContains(IResourse res)
    {
        int totalResCount=this.getWorkersCount()+this.getMachineCount()+this.getMaterialCount();
        for (int i = 0; i < totalResCount; i++)
        {
            if (this.getResourse(i).equals(res))
            {return true;}
        }
        return false;
    }

    @Override
    public boolean isEditable(){return this.isEditable;}

    public boolean isHasLink(IResourse link)
    {
        int totalResCount=this.getWorkersCount()+this.getMachineCount()+this.getMaterialCount();
        for (int i = 0; i < totalResCount; i++)
        {
            if (this.getResourse(i)==link)
            {return true;}
        }
        return false;
    }

    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        DBPrice that= (DBPrice) o;
        if (    this.id.equals(that.id) &&
                this.name.equals(that.name))
        {return true;}
        return false;
    }


    public int hashCode() {
        return Objects.hash(id,name);
    }

    @Override
    public int getWorkersCount()
    {
        if (this.workers==null)
        {
            return 0;
        }
        return this.workers.size();
    }
    @Override
    public int getMachineCount()
    {
        if (this.machines==null)
        {
            return 0;
        }
        return this.machines.size();
    }
    @Override
    public int getMaterialCount()
    {
        if (this.materials==null)
        {
            return 0;
        }
        return this.materials.size();
    }

    @Override
    public double getSinglePrice(boolean isCurrentPrice)
    {
        return this.getSingleWorkprice(isCurrentPrice)+this.getSingleMachprice(isCurrentPrice)+this.getSingleMatprice(isCurrentPrice);
    }

    @Override
    public double getSingleMachprice(boolean isCurrentPrice)
    {
        double singlePrice=0;
        for (int i=0 ; i < this.getMachineCount(); i++)
        {
            singlePrice = singlePrice+this.machines.get(i).getTotalMachprice(isCurrentPrice);
        }
        return singlePrice;
    }

    @Override
    public double getSingleMatprice(boolean isCurrentPrice)
    {
        double singlePrice=0;
        for (int i=0 ; i < this.getMaterialCount(); i++)
        {
            singlePrice = singlePrice+this.materials.get(i).getTotalMatprice(isCurrentPrice);
        }
        return  singlePrice;
    }

    @Override
    public double getSingleOperprice(boolean isCurrentPrice)
    {
        double singlePrice=0;
        for (int i=0 ; i < this.getMachineCount(); i++)
        {
            singlePrice = singlePrice+this.machines.get(i).getTotalOperprice(isCurrentPrice);
        }
        return  singlePrice;
    }

    @Override
    public double getSingleWorkprice(boolean isCurrentPrice)
    {
        double singlePrice=0;
        for (int i=0 ; i < this.getWorkersCount(); i++)
        {
            singlePrice = singlePrice+this.workers.get(i).getTotalWorkprice(isCurrentPrice);
        }
        return  singlePrice;
    }

    @Override public double getTotalPrice(boolean isCurrentPrice){return 0;}
    @Override public double getTotalMachprice(boolean isCurrentPrice){return 0;}
    @Override public double getTotalMatprice(boolean isCurrentPrice){return 0;}
    @Override public double getTotalOperprice(boolean isCurrentPrice){return 0;}
    @Override public double getTotalWorkprice(boolean isCurrentPrice){return 0;}

    //Геттеры ресурсов

    @Override
    public IResourse getResourse(int index)
    {
        //Поиск и возвращение нужного ресурса
        if (index<this.getWorkersCount()+this.getMachineCount()+this.getMaterialCount())
        {
            if (index<this.getWorkersCount())
            {
                return this.workers.get(index);
            }
            else if (index<(this.getWorkersCount()+this.getMachineCount()))
            {
                return this.machines.get(index-this.getWorkersCount());
            }
            else
            {
                return this.materials.get(index-this.getWorkersCount()-this.getMachineCount());
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public String getResID(int index)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getID();
        }
        else
        {
            return null;
        }
    }

    @Override
    public String getResName(int index)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getName();
        }
        else
        {
            return null;
        }
    }

    @Override
    public String getResUom(int index)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getUom();
        }
        else
        {
            return null;
        }
    }

    @Override
    public double getResNormalCount(int index)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getNormalCount();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResCalcCount(int index)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getCalcCount();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResTotalCount(int index)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getNormalCount();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResSingleprice (int index, boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getSinglePrice(isCurrentPrice);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResSingleMachprice (int index, boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getSingleMachprice(isCurrentPrice);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResSingleMatprice (int index, boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getSingleMatprice(isCurrentPrice);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResSingleOperprice (int index, boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getSingleOperprice (isCurrentPrice);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResSingleWorkprice (int index, boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getSingleWorkprice (isCurrentPrice);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResTotalprice (int index, boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getTotalPrice (isCurrentPrice);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResTotalMachprice (int index, boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getTotalMachprice (isCurrentPrice);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResTotalMatprice (int index, boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getTotalMatprice (isCurrentPrice);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResTotalOperprice (int index, boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getTotalOperprice (isCurrentPrice);
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double getResTotalWorkprice (int index, boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            return tmp.getTotalWorkprice (isCurrentPrice);
        }
        else
        {
            return 0;
        }
    }

    public void moveResUP(IResourse res)
    {
        if (this.isHasLink(res))
        {
            String checkClass=res.getClass().toString();
            checkClass = checkClass.substring(24,checkClass.length());

            if (checkClass.equals("DBWorker"))
            {
                if (this.workers.getFirst()!=(DBWorker)res)
                {
                    for (int i = 1; i < this.workers.size(); i++)
                    {
                        if (this.workers.get(i)==(DBWorker)res)
                        {
                            DBWorker tmp=new DBWorker(this.workers.get(i));
                            this.workers.remove(i);
                            this.workers.add(i-1,tmp);
                            return;
                        }
                    }
                }
            }
            else if (checkClass.equals("DBMachine"))
            {
                if (this.machines.getFirst()!=((DBMachine)res))
                {
                    for (int i = 1; i < this.machines.size(); i++)
                    {
                        if (this.machines.get(i)==((DBMachine)res))
                        {
                            DBMachine tmp=new DBMachine(this.machines.get(i));
                            this.machines.remove(i);
                            this.machines.add(i-1,tmp);
                            return;
                        }
                    }
                }
            }
            else
            {
                if (this.materials.getFirst()!=((DBMaterial)res))
                {
                    for (int i = 1; i < this.materials.size(); i++)
                    {
                        if (this.materials.get(i)==((DBMaterial)res))
                        {
                            DBMaterial tmp=new DBMaterial(this.materials.get(i));
                            this.materials.remove(i);
                            this.materials.add(i-1,tmp);
                            return;
                        }
                    }
                }
            }

        }
    }

    public void moveResDown(IResourse res)
    {
        if (this.isHasLink(res))
        {
            String checkClass=res.getClass().toString();
            checkClass = checkClass.substring(24,checkClass.length());

            if (checkClass.equals("DBWorker"))
            {
                if (this.workers.getLast()!=((DBWorker)res))
                {
                    for (int i = 0; i < this.workers.size()-1; i++)
                    {
                        if (this.workers.get(i)==((DBWorker)res))
                        {
                            DBWorker tmp=new DBWorker(this.workers.get(i));
                            this.workers.remove(i);
                            this.workers.add(i+1,tmp);
                            return;
                        }
                    }
                }
            }
            else if (checkClass.equals("DBMachine"))
            {
                if (this.machines.getLast()!=((DBMachine)res))
                {
                    for (int i = 0; i < this.machines.size()-1; i++)
                    {
                        if (this.machines.get(i)!=((DBMachine)res))
                        {
                            DBMachine tmp=new DBMachine(this.machines.get(i));
                            this.machines.remove(i);
                            this.machines.add(i+1,tmp);
                            return;
                        }
                    }
                }
            }
            else
            {
                if (this.materials.getLast()!=((DBMaterial)res))
                {
                    for (int i = 0; i < this.materials.size()-1; i++)
                    {
                        if (this.materials.get(i)==((DBMaterial)res))
                        {
                            DBMaterial tmp=new DBMaterial(this.materials.get(i));
                            this.materials.remove(i);
                            this.materials.add(i+1,tmp);
                            return;
                        }
                    }
                }
            }

        }
    }

    //Методы подготовки для добавления ресурсов
    private DBWorker paramadpter(Worker worker)
    {
        //Получение типа класса для проверки
        String checkClass = worker.getClass().toString();
        checkClass = checkClass.substring(24,checkClass.length());
        DBWorker tmp = null;

        //Проверка типа класса для вызова соответсвующего конструктора
        if (checkClass.equals("Worker"))
        {
            tmp = new DBWorker(worker);
        }
        else if (checkClass.equals("DBWorker"))
        {
            tmp = new DBWorker((DBWorker) worker);
        }
        else
        {
            tmp = new DBWorker((CalcWorker) worker);
        }
        return tmp;
    }

    private DBMachine paramadapter(Machine machine)
    {
        //Получение типа класса для проверки
        String checkClass = machine.getClass().toString();
        checkClass = checkClass.substring(24,checkClass.length());
        DBMachine tmp = null;

        //Проверка типа класса для вызова соответсвующего конструктора
        if (checkClass.equals("Machine"))
        {
            tmp = new DBMachine(machine);
        }
        else if (checkClass.equals("DBMachine"))
        {
            tmp = new DBMachine((DBMachine) machine);
        }
        else
        {
            tmp = new DBMachine((CalcMachine) machine);
        }
        return tmp;
    }

    private DBMaterial paramadapter(Material material)
    {
        //Получение типа класса для проверки
        String checkClass = material.getClass().toString();
        checkClass = checkClass.substring(24,checkClass.length());
        DBMaterial tmp = null;

        //Проверка типа класса для вызова соответсвующего конструктора
        if (checkClass.equals("Material"))
        {
            tmp = new DBMaterial(material);
        }
        else if (checkClass.equals("DBMaterial"))
        {
            tmp = new DBMaterial((DBMaterial) material);
        }
        else
        {
            tmp = new DBMaterial((CalcMaterial) material);
        }
        return tmp;
    }

    //Методы удаления ресурсов
    @Override
    public void removeSource(IResourse res)
    {
        if (isHasLink(res))
        {
            String checkClass=res.getClass().toString();
            checkClass = checkClass.substring(24,checkClass.length());

            if (checkClass.equals("DBWorker"))
            {
                for (int i = 0; i < this.workers.size(); i++)
                {
                    if (this.workers.get(i)==(DBWorker)res)
                    {
                        CalcWorker tmp=new CalcWorker(this.workers.get(i));
                        this.workers.remove(i);
                        return;
                    }
                }
            }
            else if (checkClass.equals("DBMachine"))
            {
                for (int i = 0; i < this.machines.size(); i++)
                {
                    if (this.machines.get(i)==((DBMachine)res))
                    {
                        CalcMachine tmp=new CalcMachine(this.machines.get(i));
                        this.machines.remove(i);
                        return;
                    }
                }
            }
            else
            {
                for (int i = 0; i < this.materials.size(); i++)
                {
                    if (this.materials.get(i)==((DBMaterial)res))
                    {
                        CalcMaterial tmp=new CalcMaterial(this.materials.get(i));
                        this.materials.remove(i);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void removeWorker(int index)
    {
        if (isEditable)
        {
            if (index<this.workers.size()-1)
            {
                this.workers.remove(index);
            }
        }
    }

    @Override
    public void removeMachine(int index)
    {
        if (isEditable)
        {
            if (index<this.workers.size()+this.machines.size()-1)
            {
                this.machines.remove(index-this.workers.size());
            }
        }
    }

    @Override
    public void removeMaterial(int index)
    {
        if (isEditable)
        {
            if (index<this.workers.size()+this.machines.size()+this.materials.size()-1)
            {
                this.machines.remove(index-this.workers.size()-this.machines.size());
            }
        }
    }

    //Сеттеры ресурсов
    @Override
    public void setResID(int index, String id)
    {
        if (isEditable)
        {
            IResourse tmp=this.getResourse(index);
            if (tmp!=null)
            {
                tmp.setID(id);
            }
        }
    }

    public void setEditable(boolean isEditable){this.isEditable=isEditable;}

    @Override
    public void setResName(int index, String name)
    {
        if (isEditable)
        {
            IResourse tmp=this.getResourse(index);
            if (tmp!=null)
            {
                tmp.setName(name);
            }
        }
    }

    @Override
    public void setResUom(int index, String uom)
    {
        if (isEditable)
        {
            IResourse tmp=this.getResourse(index);
            if (tmp!=null)
            {
                tmp.setUom(uom);
            }
        }
    }

    @Override
    public void setResSingleMachprice(int index,double machSingleprice,boolean isCurrentPrice)
    {
        if (isEditable)
        {
            IResourse tmp=this.getResourse(index);
            if (tmp!=null)
            {
                tmp.setSingleMachprice(machSingleprice,isCurrentPrice);
            }
        }
    }

    @Override
    public void setResSingleMatprice(int index,double singleprice,boolean isCurrentPrice)
    {
        if (isEditable)
        {
            IResourse tmp=this.getResourse(index);
            if (tmp!=null)
            {
                tmp.setSingleMatprice(singleprice,isCurrentPrice);
            }
        }

    }

    @Override
    public void setResSingleOperprice(int index,double operSingleprice,boolean isCurrentPrice)
    {
        if (isEditable)
        {
            IResourse tmp=this.getResourse(index);
            if (tmp!=null)
            {
                tmp.setSingleOperprice(operSingleprice,isCurrentPrice);
            }
        }
    }

    @Override
    public void setResSingleWorkprice(int index,double singleprice,boolean isCurrentPrice)
    {
        if (isEditable)
        {
            IResourse tmp=this.getResourse(index);
            if (tmp!=null)
            {
                tmp.setSingleWorkprice(singleprice,isCurrentPrice);
            }
        }
    }

    @Override
    public void setResNormalCount(int index,double normalCount)
    {
        if (isEditable)
        {
            IResourse tmp=this.getResourse(index);
            if (tmp!=null)
            {
                tmp.setNormalCount(normalCount);
            }
        }
    }
}
