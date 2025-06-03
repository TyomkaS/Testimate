package Backend.Prices;

import Backend.Coefficient;
import Backend.Index;
import Backend.Resourses.*;

import java.util.ArrayList;

//Дописать применение коэффициентов и индексов к ресурсам

public class CalcPrice extends Price
{
    ArrayList<Coefficient> coefficients;
    Coefficient summarycoef;
    Index ind;
    double count;

    double workCount;

    //Конструкторы
    public CalcPrice()
    {
        super();
        workers = null;
        machines = null;
        materials = null;
        coefficients=null;
        workCount=0;
        count=0;
        summarycoef=new Coefficient();
        ind = new Index();

    }

    public CalcPrice(String name)
    {
        super(name);
        workers = null;
        machines = null;
        materials = null;
        coefficients=null;
        workCount=0;
        count=0;
        summarycoef=new Coefficient();
        ind = new Index();
    }

    public CalcPrice(String id, String name)
    {
        super(id,name);
        workers = null;
        machines = null;
        materials = null;
        coefficients=null;
        workCount=0;
        count=0;
        summarycoef=new Coefficient();
        ind = new Index();
    }

    public CalcPrice(DBPrice price)
    {
        super(price);
        coefficients=null;
        count=0;
        summarycoef=new Coefficient();
        ind = new Index();

        if (price.getWorkersCount()>0)
        {
            this.workers = new ArrayList<>(price.getWorkersCount());
            for (int i=0 ; i < price.getWorkersCount(); i++)
            {
                CalcWorker tmp = new CalcWorker((DBWorker) price.workers.get(i));
                this.workers.add(tmp);
            }
        }
        else
        {
            this.workers=null;
        }

        if (price.getMachineCount()>0)
        {
            this.machines = new ArrayList<>(price.getMachineCount());
            for (int i=0 ; i < price.getMachineCount(); i++)
            {
                CalcMachine tmp = new CalcMachine((DBMachine) price.machines.get(i));
                this.machines.add(tmp);
            }
        }
        else
        {
            this.machines=null;
        }

        if (price.getMaterialCount()>0)
        {
            this.materials = new ArrayList<>(price.getMaterialCount());
            for (int i=0 ; i < price.getMaterialCount(); i++)
            {
                CalcMaterial tmp = new CalcMaterial((DBMaterial) price.materials.get(i));
                this.materials.add(tmp);
            }
        }
        else
        {
            this.materials=null;
        }

    }

    public CalcPrice(CalcPrice price)
    {
        super(price);
        this.count=price.count;
        this.ind = new Index();

        if (price.getWorkersCount()>0)
        {
            this.workers = new ArrayList<>(price.getWorkersCount());
            for (int i=0 ; i < price.getWorkersCount(); i++)
            {
                CalcWorker tmp = new CalcWorker((CalcWorker)price.workers.get(i));
                this.workers.add(tmp);
            }
        }
        else
        {
            this.workers=null;
        }

        if (price.getMachineCount()>0)
        {
            this.machines = new ArrayList<>(price.getMachineCount());
            for (int i=0 ; i < price.getMachineCount(); i++)
            {
                CalcMachine tmp = new CalcMachine((CalcMachine)price.machines.get(i));
                this.machines.add(tmp);
            }
        }
        else
        {
            this.machines=null;
        }

        if (price.getMaterialCount()>0)
        {
            this.materials = new ArrayList<>(price.getMaterialCount());
            for (int i=0 ; i < price.getMaterialCount(); i++)
            {
                CalcMaterial tmp = new CalcMaterial((CalcMaterial)price.materials.get(i));
                this.materials.add(tmp);
            }
        }
        else
        {
            this.materials=null;
        }

        summarycoef = new Coefficient();
        if (price.coefficients!=null)
        {
            this.coefficients=new ArrayList<>(price.coefficients.size());
            for (int i=0 ; i < price.coefficients.size(); i++)
            {
                Coefficient tmp = new Coefficient(price.coefficients.get(i));
                this.coefficients.add(tmp);

                summarycoef.setWorkCoef(tmp.getActiveWorkCoef()+summarycoef.getWorkCoef());
                summarycoef.setMachCoef(tmp.getActiveMachCoef()+summarycoef.getMachCoef());
                summarycoef.setMatCoef(tmp.getActiveMatCoef()+summarycoef.getMatCoef());
            }
        }
        else
        {
            this.coefficients=null;
        }

    }

    //Методы
    //Методы добавления ресурсов

    @Override
    public void addWorker()
    {
        if (this.workers==null)
        {
            this.workers = new ArrayList<>(1);
        }

        CalcWorker tmp = new CalcWorker();
        tmp.setCalcCount(this.count);
        this.summarycoef.applyToRes(tmp);
        this.ind.applyToRes(tmp);
        this.workers.add(tmp);
    }

    @Override
    public void addMachine()
    {
        if (this.machines==null)
        {
            this.machines = new ArrayList<>(1);
        }

        CalcMachine tmp = new CalcMachine();
        tmp.setCalcCount(this.count);
        this.summarycoef.applyToRes(tmp);
        this.ind.applyToRes(tmp);
        this.machines.add(tmp);
    }

    @Override
    public void addMaterial()
    {
        if (this.materials==null)
        {
            this.materials = new ArrayList<>(1);
        }

        CalcMaterial tmp = new CalcMaterial();
        tmp.setCalcCount(this.count);
        this.summarycoef.applyToRes(tmp);
        this.ind.applyToRes(tmp);
        this.materials.add(tmp);
    }

    @Override
    public void addWorker(Worker worker)
    {
        if (worker!=null)
        {
            if (this.workers==null)
            {
                this.workers = new ArrayList<>(1);
            }
            this.workers.add(this.paramadpter(worker,this.getCount()));
        }

    }

    @Override
    public void addMachine(Machine machine)
    {
        if (machine!=null)
        {
            if (this.machines==null)
            {
                this.machines = new ArrayList<>(1);
            }
            this.machines.add(this.paramadapter(machine, this.getCount()));
        }
    }

    @Override
    public void addMaterial(Material material)
    {
        if (material!=null)
        {
            if (this.materials==null)
            {
                this.materials = new ArrayList<>(1);
            }
            this.materials.add(this.paramadapter(material, this.getCount()));
        }
    }

    @Override
    public void addWorker(Worker worker, int index)
    {
        if (this.workers==null)
        {
            this.workers = new ArrayList<>(1);
        }

        if (index<this.workers.size())
        {
            this.workers.add(index,this.paramadpter(worker, this.getCount()));
        }
        else
        {
            this.workers.add(this.paramadpter(worker, this.getCount()));
        }

    }

    @Override
    public void addMachine(Machine machine, int index)
    {
        if (this.machines==null)
        {
            this.machines = new ArrayList<>(1);
        }

        if (index<(this.workers.size()+this.machines.size()))
        {
            //индексы ресурсов отсчитываются от нулевого индекса материалов,
            //затем индекс машин+workers.size(),
            //затем индекс материалов++workers.size()+machines.size()
            this.machines.add((index - this.workers.size()),this.paramadapter(machine,this.getCount()));
        }
        else
        {
            this.machines.add(this.paramadapter(machine,this.getCount()));
        }
    }

    @Override
    public void addMaterial(Material material, int index)
    {
        if (this.materials==null)
        {
            this.materials = new ArrayList<>(1);
        }

        if (index<(this.workers.size()+this.machines.size()+this.materials.size()))
        {
            //индексы ресурсов отсчитываются от нулевого индекса материалов,
            //затем индекс машин+workers.size(),
            //затем индекс материалов++workers.size()+machines.size()
            this.materials.add((index - this.workers.size()-this.machines.size()),this.paramadapter(material,this.getCount()));
        }
        else
        {
            this.materials.add(this.paramadapter(material,this.getCount()));
        }
    }

    private void applySummaryCoef()
    {
        if (this.workers!=null)
        {
            for (int i=0; i<this.workers.size();i++)
            {
                this.summarycoef.applyToRes((CalcWorker) this.workers.get(i));
            }
        }

        if (this.machines!=null)
        {
            for (int i=0; i<this.machines.size();i++)
            {
                this.summarycoef.applyToRes((CalcMachine) this.machines.get(i));
            }
        }

        if (this.materials!=null)
        {
            for (int i=0; i<this.materials.size();i++)
            {
                this.summarycoef.applyToRes((CalcMaterial) this.materials.get(i));
            }
        }
    }

    private void applyIndex()
    {
        if (this.workers!=null)
        {
            for (int i=0; i<this.workers.size();i++)
            {
                this.ind.applyToRes((CalcWorker) this.workers.get(i));
            }
        }

        if (this.machines!=null)
        {
            for (int i=0; i<this.machines.size();i++)
            {
                this.ind.applyToRes((CalcMachine) this.machines.get(i));
            }
        }

        if (this.materials!=null) {
            for (int i = 0; i < this.materials.size(); i++) {
                this.ind.applyToRes((CalcMaterial) this.materials.get(i));
            }
        }
    }

    public void deleteIndex()
    {
        this.ind=new Index();
        this.applyIndex();
    }

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


    //Геттеры
    @Override
    public double getCount(){return this.count;}
    public Index getIndex(){return this.ind;}
    public Coefficient getCoef(int index)
    {
        if (this.coefficients==null || index>this.coefficients.size() )
        {
            return null;
        }
        else
        {
            return this.coefficients.get(index);
        }
    }

    public double getTotalWorkCount()
    {
        double totalWorkCount=0;
        if (this.workers!=null)
        {
            for (Worker item : this.workers){totalWorkCount+=item.getCalcTotalCount();}
        }
        return totalWorkCount;
    }

    public double getTotalMachineCount()
    {
        double totalMachineCount=0;
        if (this.machines!=null)
        {
            for (Machine item : this.machines){totalMachineCount+=item.getCalcTotalCount();}
        }
        return totalMachineCount;
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

    public int getCoefCount(){return this.coefficients.size();}

    public String getCoefName(int index)
    {
        if (index<this.coefficients.size())
        {
            return this.coefficients.get(index).getName();
        }
        else
        {
            return null;
        }
    }

    public double getCoefTotal(int index)
    {
        if (index<this.coefficients.size())
        {
            return this.coefficients.get(index).getTotalCoef();
        }
        else
        {
            return 0;
        }
    }
    public double getCoefWork(int index)
    {
        if (index<this.coefficients.size())
        {
            return this.coefficients.get(index).getWorkCoef();
        }
        else
        {
            return 0;
        }
    }
    public double getCoefMach(int index)
    {
        if (index<this.coefficients.size())
        {
            return this.coefficients.get(index).getMachCoef();
        }
        else
        {
            return 0;
        }
    }
    public double getCoefMat(int index)
    {
        if (index<this.coefficients.size())
        {
            return this.coefficients.get(index).getMatCoef();
        }
        else
        {
            return 0;
        }
    }

    public boolean getIsAppliedTotalCoef(int index)
    {
        if (index<this.coefficients.size())
        {
            return this.coefficients.get(index).isAppliedTotalCoef();
        }
        else
        {
            return false;
        }
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
            IResourse tmp=this.machines.get(i);
            singlePrice = singlePrice+(tmp.getNormalCount() * tmp.getSingleMachprice(isCurrentPrice));
        }
        return singlePrice;
    }

    @Override
    public double getSingleMatprice(boolean isCurrentPrice)
    {
        double singlePrice=0;
        for (int i=0 ; i < this.getMaterialCount(); i++)
        {
            IResourse tmp=this.materials.get(i);
            singlePrice = singlePrice+(tmp.getNormalCount() * tmp.getSingleMatprice(isCurrentPrice));
        }
        return  singlePrice;
    }

    @Override
    public double getSingleOperprice(boolean isCurrentPrice)
    {
        double singlePrice=0;
        for (int i=0 ; i < this.getMachineCount(); i++)
        {
            IResourse tmp=this.machines.get(i);
            singlePrice = singlePrice+(tmp.getNormalCount() * tmp.getSingleOperprice(isCurrentPrice));
        }
        return  singlePrice;
    }

    @Override
    public double getSingleWorkprice(boolean isCurrentPrice)
    {
        double singlePrice=0;
        for (int i=0 ; i < this.getWorkersCount(); i++)
        {
            IResourse tmp=this.workers.get(i);
            singlePrice = singlePrice+(tmp.getNormalCount() * tmp.getSingleWorkprice(isCurrentPrice));
        }
        return  singlePrice;
    }

    @Override
    public double getTotalPrice(boolean isCurrentPrice)
    {
        return this.getTotalWorkprice(isCurrentPrice)+this.getTotalMachprice(isCurrentPrice)+this.getTotalMatprice(isCurrentPrice);
    }

    @Override
    public double getTotalMachprice(boolean isCurrentPrice)
    {
        double totalPrice=0;
        for (int i=0 ; i < this.getMachineCount(); i++)
        {
            totalPrice = totalPrice+this.machines.get(i).getTotalMachprice(isCurrentPrice);
        }
        return totalPrice;
    }

    @Override
    public double getTotalMatprice(boolean isCurrentPrice)
    {
        double totalPrice=0;
        for (int i=0 ; i < this.getMaterialCount(); i++)
        {
            totalPrice = totalPrice+this.materials.get(i).getTotalMatprice(isCurrentPrice);
        }
        return  totalPrice;
    }

    @Override
    public double getTotalOperprice(boolean isCurrentPrice)
    {
        double totalPrice=0;
        for (int i=0 ; i < this.getMachineCount(); i++)
        {
            totalPrice = totalPrice+this.machines.get(i).getTotalOperprice(isCurrentPrice);
        }
        return  totalPrice;
    }

    @Override
    public double getTotalWorkprice(boolean isCurrentPrice)
    {
        double totalPrice=0;
        for (int i=0 ; i < this.getWorkersCount(); i++)
        {
            totalPrice = totalPrice+this.workers.get(i).getTotalWorkprice(isCurrentPrice);
        }
        return  totalPrice;
    }

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

            if (checkClass.equals("CalcWorker"))
            {
                if (this.workers.getFirst()!=(CalcWorker)res)
                {
                    for (int i = 1; i < this.workers.size(); i++)
                    {
                        if (this.workers.get(i)==(CalcWorker)res)
                        {
                            CalcWorker tmp=new CalcWorker(this.workers.get(i));
                            this.workers.remove(i);
                            this.workers.add(i-1,tmp);
                            return;
                        }
                    }
                }
            }
            else if (checkClass.equals("CalcMachine"))
            {
                if (this.machines.getFirst()!=((CalcMachine)res))
                {
                    for (int i = 1; i < this.machines.size(); i++)
                    {
                        if (this.machines.get(i)==((CalcMachine)res))
                        {
                            CalcMachine tmp=new CalcMachine(this.machines.get(i));
                            this.machines.remove(i);
                            this.machines.add(i-1,tmp);
                            return;
                        }
                    }
                }
            }
            else
            {
                if (this.materials.getFirst()!=((CalcMaterial)res))
                {
                    for (int i = 1; i < this.materials.size(); i++)
                    {
                        if (this.materials.get(i)==((CalcMaterial)res))
                        {
                            CalcMaterial tmp=new CalcMaterial(this.materials.get(i));
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

            if (checkClass.equals("CalcWorker"))
            {
                if (this.workers.getLast()!=((CalcWorker)res))
                {
                    for (int i = 0; i < this.workers.size()-1; i++)
                    {
                        if (this.workers.get(i)==((CalcWorker)res))
                        {
                            CalcWorker tmp=new CalcWorker(this.workers.get(i));
                            this.workers.remove(i);
                            this.workers.add(i+1,tmp);
                            return;
                        }
                    }
                }
            }
            else if (checkClass.equals("CalcMachine"))
            {
                if (this.machines.getLast()!=((CalcMachine)res))
                {
                    for (int i = 0; i < this.machines.size()-1; i++)
                    {
                        if (this.machines.get(i)!=((CalcMachine)res))
                        {
                            CalcMachine tmp=new CalcMachine(this.machines.get(i));
                            this.machines.remove(i);
                            this.machines.add(i+1,tmp);
                            return;
                        }
                    }
                }
            }
            else
            {
                if (this.materials.getLast()!=((CalcMaterial)res))
                {
                    for (int i = 0; i < this.materials.size()-1; i++)
                    {
                        if (this.materials.get(i)==((CalcMaterial)res))
                        {
                            CalcMaterial tmp=new CalcMaterial(this.materials.get(i));
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
    private CalcWorker paramadpter(Worker worker, double count)
    {
        //Получение типа класса для проверки
        String checkClass = worker.getClass().toString();
        checkClass = checkClass.substring(24,checkClass.length());
        CalcWorker tmp = null;

        //Проверка типа класса для вызова соответсвующего конструктора
        if (checkClass.equals("Worker"))
        {
            tmp = new CalcWorker(worker);
        }
        else if (checkClass.equals("DBWorker"))
        {
            tmp = new CalcWorker((DBWorker) worker);
        }
        else
        {
            tmp = new CalcWorker((CalcWorker) worker);
        }
        tmp.setCalcCount(count);
        return tmp;
    }

    private CalcMachine paramadapter(Machine machine, double count)
    {
        //Получение типа класса для проверки
        String checkClass = machine.getClass().toString();
        checkClass = checkClass.substring(24,checkClass.length());
        CalcMachine tmp = null;

        //Проверка типа класса для вызова соответсвующего конструктора
        if (checkClass.equals("Machine"))
        {
            tmp = new CalcMachine(machine);
        }
        else if (checkClass.equals("DBMachine"))
        {
            tmp = new CalcMachine((DBMachine) machine);
        }
        else
        {
            tmp = new CalcMachine((CalcMachine) machine);
        }
        tmp.setCalcCount(count);
        return tmp;
    }

    private CalcMaterial paramadapter(Material material, double count)
    {
        //Получение типа класса для проверки
        String checkClass = material.getClass().toString();
        checkClass = checkClass.substring(24,checkClass.length());
        CalcMaterial tmp = null;

        //Проверка типа класса для вызова соответсвующего конструктора
        if (checkClass.equals("Material"))
        {
            tmp = new CalcMaterial(material);
        }
        else if (checkClass.equals("DBMaterial"))
        {
            tmp = new CalcMaterial((DBMaterial) material);
        }
        else
        {
            tmp = new CalcMaterial((CalcMaterial) material);
        }
        tmp.setCalcCount(count);
        return tmp;
    }

    //Методы удаления ресурсов
    @Override
    public void removeWorker(int index)
    {
        if (this.workers!=null)
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
        if (this.machines!=null)
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
        if (this.materials!=null)
        {
            if (index<this.workers.size()+this.machines.size()+this.materials.size()-1)
            {
                this.materials.remove(index-this.workers.size()-this.machines.size());
            }
        }
    }

    @Override
    public void removeSource(IResourse res)
    {
        if (isHasLink(res))
        {
            String checkClass=res.getClass().toString();
            checkClass = checkClass.substring(24,checkClass.length());

            if (checkClass.equals("CalcWorker"))
            {
                for (int i = 0; i < this.workers.size(); i++)
                {
                    if (this.workers.get(i)==(CalcWorker)res)
                    {
                        CalcWorker tmp=new CalcWorker(this.workers.get(i));
                        this.workers.remove(i);
                        return;
                    }
                }
            }
            else if (checkClass.equals("CalcMachine"))
            {
                for (int i = 0; i < this.machines.size(); i++)
                {
                    if (this.machines.get(i)==((CalcMachine)res))
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
                    if (this.materials.get(i)==((CalcMaterial)res))
                    {
                        CalcMaterial tmp=new CalcMaterial(this.materials.get(i));
                        this.materials.remove(i);
                        return;
                    }
                }
            }
        }
    }

    public void removeCoef(int index)
    {
        if (this.coefficients!=null && index<this.coefficients.size())
        {
            this.coefficients.remove(index);
        }
        this.updateSummeryCoef();
    }
    //Сеттеры
    @Override
    public void setCount(double count)
    {
        this.count=count;

        //Назначение количества работы для ресурсов
        if (this.workers!=null)
        {
            for (int i=0; i<this.workers.size(); i++)
            {
                this.workers.get(i).setCalcCount(this.count);
            }
        }

        if (this.machines!=null)
        {
            for (int i=0; i<this.machines.size(); i++)
            {
                this.machines.get(i).setCalcCount(this.count);
            }
        }

        if (this.materials!=null)
        {
            for (int i=0; i<this.materials.size(); i++)
            {
                this.materials.get(i).setCalcCount(this.count);
            }
        }
    }

    //Сеттеры ресурсов
    @Override
    public void setResID(int index, String id)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            tmp.setID(id);
        }
    }

    @Override

    public void setResName(int index, String name)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            tmp.setName(name);
        }
    }

    @Override
    public void setResUom(int index, String uom)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            tmp.setUom(uom);
        }
    }

    public void setIndex(Index index)
    {
        this.ind=new Index(index);
        this.applyIndex();
    }

    @Override
    public void setResSingleMachprice(int index,double machSingleprice,boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            tmp.setSingleMachprice(machSingleprice,isCurrentPrice);
        }
    }

    @Override
    public void setResSingleMatprice(int index,double singleprice,boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            tmp.setSingleMatprice(singleprice,isCurrentPrice);
        }
    }

    @Override
    public void setResSingleOperprice(int index,double operSingleprice,boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            tmp.setSingleOperprice(operSingleprice,isCurrentPrice);
        }
    }

    @Override
    public void setResSingleWorkprice(int index,double singleprice,boolean isCurrentPrice)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            tmp.setSingleWorkprice(singleprice,isCurrentPrice);
        }
    }

    @Override
    public void setResNormalCount(int index,double normalCount)
    {
        IResourse tmp=this.getResourse(index);
        if (tmp!=null)
        {
            tmp.setNormalCount(normalCount);
        }
    }

    private void updateSummeryCoef()
    {
        for (int i=0 ; i < this.coefficients.size(); i++)
        {
            summarycoef.setWorkCoef(this.coefficients.get(i).getActiveWorkCoef()+summarycoef.getWorkCoef());
            summarycoef.setMachCoef(this.coefficients.get(i).getActiveMachCoef()+summarycoef.getMachCoef());
            summarycoef.setMatCoef(this.coefficients.get(i).getActiveMatCoef()+summarycoef.getMatCoef());
        }

        this.applySummaryCoef();
    }
}