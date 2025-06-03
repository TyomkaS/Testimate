package Backend.Documents;

import Backend.Coefficient;
import Backend.Index;
import Backend.Prices.CalcPrice;
import Backend.Prices.DBPrice;
import Backend.Prices.Price;
import Backend.Resourses.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Calculation implements Serializable
{
    private String name;
    private ArrayList<CalcPrice> priceCollection;
    /*private ArrayList<ListWorker> workerList;
    private ArrayList<ListMachine> machineList;
    private ArrayList<ListMaterial> materialList;*/

    private Index ind;
    private Coefficient finalcoef;
    private boolean isSelfResourse;
    private double selfWorkerPrice;
    private boolean isEditable;


    //Конструкторы
    public Calculation()
    {
        this.name=null;
        this.priceCollection=null;
        /*this.workerList=null;
        this.machineList=null;
        this.materialList=null;*/

        this.ind=new Index();
        this.finalcoef=new Coefficient();
        this.isSelfResourse=false;
        this.selfWorkerPrice=0;
        this.isEditable=true;
    }

    public Calculation(Calculation other)
    {
        this.name=other.name;

        if (other.priceCollection!=null)
        {
            this.priceCollection=new ArrayList<>(other.priceCollection.size());
            for (int i=0; i<other.priceCollection.size();i++)
            {
                CalcPrice tmp=new CalcPrice(other.priceCollection.get(i));
                this.priceCollection.add(tmp);
            }
        }

        this.ind=new Index(other.ind);
        this.finalcoef=new Coefficient(other.finalcoef);
        this.isSelfResourse=other.isSelfResourse;
        this.selfWorkerPrice=other.selfWorkerPrice;
        this.isEditable=other.isEditable;
    }

    //Методы
    public void addPrice()
    {
        if (this.priceCollection==null)
        {
            this.priceCollection=new ArrayList<>(1);
        }
        CalcPrice tmp = new CalcPrice();
        this.priceCollection.add(tmp);
    }

    public void addPrice(DBPrice price)
    {
        if (this.priceCollection==null)
        {
            this.priceCollection=new ArrayList<>(1);
        }
        CalcPrice tmp = new CalcPrice(price);
        this.priceCollection.add(tmp);
        //this.autoAddResourceToList(tmp);

    }

    public void addPrice(CalcPrice price)
    {
        if (this.priceCollection==null)
        {
            this.priceCollection=new ArrayList<>(1);
        }
        CalcPrice tmp = new CalcPrice(price);
        this.priceCollection.add(tmp);
        //this.autoAddResourceToList(tmp);

    }

    public void addPrice(int index)
    {
        CalcPrice tmp = new CalcPrice();
        if (this.priceCollection==null)
        {
            this.priceCollection=new ArrayList<>(1);
        }
        if (index<this.priceCollection.size())
        {
            this.priceCollection.add(index,tmp);
        }
        this.priceCollection.add(tmp);
        //this.autoAddResourceToList(tmp);

    }

    public void addPrice(DBPrice price,int index)
    {
        CalcPrice tmp = new CalcPrice(price);
        if (this.priceCollection==null)
        {
            this.priceCollection=new ArrayList<>(1);
        }
        if (index<this.priceCollection.size())
        {
            this.priceCollection.add(index,tmp);
        }
        this.priceCollection.add(tmp);
        //this.autoAddResourceToList(tmp);
    }

    public void addPrice(CalcPrice price,int index)
    {
        CalcPrice tmp = new CalcPrice(price);
        if (this.priceCollection==null)
        {
            this.priceCollection=new ArrayList<>(1);
        }
        if (index<this.priceCollection.size())
        {
            this.priceCollection.add(index,tmp);
        }
        this.priceCollection.add(tmp);
        //this.autoAddResourceToList(tmp);
    }



    /*private void addMachineToList(CalcMachine machine)
    {
        if (this.machineList==null)
        {
            this.machineList=new ArrayList<>(1);
            ListMachine tmp = new ListMachine(machine);
            this.machineList.add(tmp);
            return;
        }
        else
        {
            for (ListMachine item : this.machineList)
            {
                if (item.equals(machine))
                {
                    item.add(machine);
                    return;
                }
            }
            ListMachine tmp = new ListMachine(machine);
            this.machineList.add(tmp);
        }
    }

    private void addMaterialToList(CalcMaterial material)
    {
        if (this.materialList==null)
        {
            this.materialList=new ArrayList<>(1);
            ListMaterial tmp = new ListMaterial(material);
            this.materialList.add(tmp);
            return;
        }
        else
        {
            for (ListMaterial item : this.materialList)
            {
                if (item.equals(material))
                {
                    item.add(material);
                    return;
                }
            }
            ListMaterial tmp = new ListMaterial(material);
            this.materialList.add(tmp);
        }
    }
    
    private void addWorkerToList(CalcWorker worker)
    {
        if (this.workerList==null)
        {
            this.workerList=new ArrayList<>(1);
            ListWorker tmp = new ListWorker(worker);
            this.workerList.add(tmp);
            return;
        }
        else
        {
            for (ListWorker item : this.workerList)
            {
                if (item.equals(worker))
                {
                    item.add(worker);
                    return;
                }
            }
            ListWorker tmp = new ListWorker(worker);
            this.workerList.add(tmp);
        }
    }*/

    /*private void autoAddResourceToList(CalcPrice price)
    {
        IResourse tmp=null;
        for(int i=0;i<(price.getWorkersCount()+price.getMachineCount()+price.getMaterialCount());i++)
        {
            tmp=price.getResourse(i);

            //Получение типа класса для проверки
            String checkClass = tmp.getClass().toString();
            checkClass = checkClass.substring(24,checkClass.length());

            //Проверка типа класса для добавления в соотествующий список
            switch (checkClass)
            {
                case "CalcWorker":
                    this.addWorkerToList((CalcWorker) tmp);
                    break;
                case "CalcMachine":
                    this.addMachineToList((CalcMachine) tmp);
                    break;
                case "CalcMaterial":
                    this.addMaterialToList((CalcMaterial) tmp);
                    break;
            }
        }
    }*/

    /*private void autoRemoveResourseFromList(CalcPrice price)
    {
        IResourse tmp=null;
        for(int i=0;i<(price.getWorkersCount()+price.getMachineCount()+price.getMaterialCount());i++)
        {
            tmp=price.getResourse(i);
            //Получение типа класса для проверки
            String checkClass = tmp.getClass().toString();
            checkClass = checkClass.substring(24,checkClass.length());

            //Проверка типа класса для удаления из соотествующего списка
            switch (checkClass)
            {
                case "CalcWorker":
                    this.removeWorkerFromList((CalcWorker) tmp);
                    break;
                case "CalcMachine":
                    this.removeMachineFromList((CalcMachine) tmp);
                    break;
                case "CalcMaterial":
                    this.removeMaterialFromList((CalcMaterial) tmp);
                    break;
            }
        }
    }*/

    public boolean isEditable(){return this.isEditable;}

    public boolean isHasLink(CalcPrice link)
    {
        if (this.priceCollection!=null)
        {
            for (int i=0; i<this.priceCollection.size();i++)
            {
                if (this.priceCollection.get(i)==link)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public Index getIndex(){return this.ind;}

    public boolean isSelfResourse(){return this.isSelfResourse;}

    public Coefficient getFinalcoef(){return this.finalcoef;}

    public String getName(){return this.name;}

    public double getSelfWorkerPrice(){return this.selfWorkerPrice;}

    public CalcPrice getPrice(int index)
    {
        if (this.priceCollection!=null && index<this.priceCollection.size())
        {
            return this.priceCollection.get(index);
        }
        return null;
    }

    public int getPriceCount()
    {
        if(this.priceCollection!=null)
        {return this.priceCollection.size();}
        return 0;
    }

    /*public IResourse getMachine(int index)
    {
        if (this.machineList!=null && index<this.machineList.size())
        {
            return this.machineList.get(index);
        }
        return null;
    }*/

    /*public int getMachineListCount()
    {
        if (this.machineList!=null)
        {return this.machineList.size();}
        return 0;
    }*/

    /*public IResourse getMaterial(int index)
    {
        if (this.materialList!=null && index<this.materialList.size())
        {
            return this.materialList.get(index);
        }
        return null;
    }*/

    /*public int getMaterialListCount()
    {
        if (this.materialList!=null)
        {return this.materialList.size();}
        return 0;
    }*/

    /*public IResourse getWorker(int index)
    {
        if (this.workerList!=null && index<this.workerList.size())
        {
            return this.workerList.get(index);
        }
        return null;
    }*/

    /*public int getWorkerListCount()
    {
        if (this.workerList!=null)
        {return this.workerList.size();}
        return 0;
    }*/

    public double getTotalWorkerCount()
    {
        double totalWorkerCount=0;
        if(this.priceCollection!=null)
        {
            for (CalcPrice item : this.priceCollection){totalWorkerCount+=item.getTotalWorkCount();}
        }
        return totalWorkerCount;
    }

    public double getTotalMachineCount(){
        double totalMachineCount=0;
        if(this.priceCollection!=null)
        {
            for (CalcPrice item : this.priceCollection){totalMachineCount+=item.getTotalMachineCount();}
        }
        return totalMachineCount;
    }

    public double getTotalCost(boolean isCurrentPrice)
    {
        double totalCost=0;
        if(this.priceCollection!=null)
        {
            for (CalcPrice item : this.priceCollection){totalCost+=item.getTotalPrice(isCurrentPrice);}
        }
        return totalCost;
    }

    public double getTotalMachineCost(boolean isCurrentPrice)
    {
        double totalMachineCost=0;
        if(this.priceCollection!=null)
        {
            for (CalcPrice item : this.priceCollection){totalMachineCost+=item.getTotalMachprice(isCurrentPrice);}
        }
        return totalMachineCost;
    }

    public double getTotalMaterialCost(boolean isCurrentPrice)
    {
        double totalMaterialCost=0;
        if(this.priceCollection!=null)
        {
            for (CalcPrice item : this.priceCollection){totalMaterialCost+=item.getTotalMatprice(isCurrentPrice);}
        }
        return totalMaterialCost;
    }

    public double getTotalOperatorCost(boolean isCurrentPrice)
    {
        double totalOperatorCost=0;
        if(this.priceCollection!=null)
        {
            for (CalcPrice item : this.priceCollection){totalOperatorCost+=item.getTotalOperprice(isCurrentPrice);}
        }
        return totalOperatorCost;
    }

    public double getTotalWorkerCost(boolean isCurrentPrice)
    {
        double totalWorkerCost=0;
        if(this.priceCollection!=null)
        {
            for (CalcPrice item : this.priceCollection){totalWorkerCost+=item.getTotalWorkprice(isCurrentPrice);}
        }
        return totalWorkerCost;
    }



    public ArrayList<ListMachine> getMachineList()
    {
        ArrayList<ListMachine>  machineList=null;

        if (this.priceCollection!=null)
        {
            for (int i = 0; i < this.priceCollection.size(); i++)
            {
                int machineCount=this.priceCollection.get(i).getMachineCount();
                int workerCount=this.priceCollection.get(i).getWorkersCount();
                int circleUpperLimit=machineCount+workerCount;
                if (machineCount>0)
                {
                    for (int j = workerCount; j < (circleUpperLimit); j++)
                    {
                        if (machineList==null)
                        {
                            machineList=new ArrayList<>(1);
                            ListMachine tmp = new ListMachine((CalcMachine) this.priceCollection.get(i).getResourse(j));
                            machineList.add(tmp);
                        }
                        else
                        {
                            /*for (ListMachine item : machineList)
                            {
                                if (item.equals((CalcMachine) this.priceCollection.get(i).getResourse(j)))
                                {
                                    item.add((CalcMachine) this.priceCollection.get(i).getResourse(j));
                                }
                                else
                                {
                                    ListMachine tmp = new ListMachine((CalcMachine) this.priceCollection.get(i).getResourse(j));
                                    machineList.add(tmp);
                                }
                            }*/

                            boolean isNeedtoAdd=false;
                            for (int k = 0; k < machineList.size(); k++)
                            {
                                if (machineList.get(k).equals((CalcMachine)this.priceCollection.get(i).getResourse(j)))
                                {
                                    machineList.get(k).add((CalcMachine) this.priceCollection.get(i).getResourse(j));
                                    isNeedtoAdd=false;
                                    break;
                                }
                                else
                                {
                                    isNeedtoAdd=true;
                                }
                            }

                            if (isNeedtoAdd)
                            {
                                ListMachine tmp = new ListMachine((CalcMachine) this.priceCollection.get(i).getResourse(j));
                                machineList.add(tmp);
                            }
                        }
                    }
                }
            }
        }
        return machineList;
    }

    public ArrayList<ListMaterial> getMaterialList()
    {
        ArrayList<ListMaterial>  materialList=null;
        if (this.priceCollection!=null)
        {
            for (int i = 0; i < this.priceCollection.size(); i++)
            {
                int materialCount=this.priceCollection.get(i).getMaterialCount();
                int machineCount=this.priceCollection.get(i).getMachineCount();
                int workerCount=this.priceCollection.get(i).getWorkersCount();
                int circleLowerLimit=machineCount+workerCount;
                int circleUpperLimit=materialCount+machineCount+workerCount;
                if (machineCount>0)
                {
                    for (int j = circleLowerLimit; j < (circleUpperLimit); j++)
                    {
                        if (materialList==null)
                        {
                            materialList=new ArrayList<>(1);
                            ListMaterial tmp = new ListMaterial((CalcMaterial) this.priceCollection.get(i).getResourse(j));
                            materialList.add(tmp);
                        }
                        else
                        {
                            /*for (ListMaterial item : materialList)
                            {
                                if (item.equals((CalcMaterial) this.priceCollection.get(i).getResourse(j)))
                                {
                                    item.add((CalcMaterial) this.priceCollection.get(i).getResourse(j));
                                }
                                else
                                {
                                    ListMaterial tmp = new ListMaterial((CalcMaterial) this.priceCollection.get(i).getResourse(j));
                                    materialList.add(tmp);
                                }
                            }*/

                            boolean isNeedtoAdd=false;
                            for (int k = 0; k < materialList.size(); k++)
                            {
                                if (materialList.get(k).equals((CalcMaterial)this.priceCollection.get(i).getResourse(j)))
                                {
                                    materialList.get(k).add((CalcMaterial) this.priceCollection.get(i).getResourse(j));
                                    isNeedtoAdd=false;
                                    break;
                                }
                                else
                                {
                                    isNeedtoAdd=true;
                                }
                            }

                            if (isNeedtoAdd)
                            {
                                ListMaterial tmp = new ListMaterial((CalcMaterial) this.priceCollection.get(i).getResourse(j));
                                materialList.add(tmp);
                            }
                        }
                    }
                }
            }
        }
        return materialList;
    }

    public ArrayList<ListWorker> getWorkerList()
    {
        ArrayList<ListWorker>  workerList=null;
        if (this.priceCollection!=null)
        {
            for (int i = 0; i < this.priceCollection.size(); i++)
            {
                int workerCount=this.priceCollection.get(i).getWorkersCount();
                if (workerCount>0)
                {
                    for (int j = 0; j < (workerCount); j++)
                    {
                        if (workerList==null)
                        {
                            workerList=new ArrayList<>(1);
                            ListWorker tmp = new ListWorker((CalcWorker) this.priceCollection.get(i).getResourse(j));
                            workerList.add(tmp);
                        }
                        else
                        {
                            boolean isNeedtoAdd=false;
                            for (int k = 0; k < workerList.size(); k++)
                            {
                                if (workerList.get(k).equals((CalcWorker)this.priceCollection.get(i).getResourse(j)))
                                {
                                    workerList.get(k).add((CalcWorker)this.priceCollection.get(i).getResourse(j));
                                    isNeedtoAdd=false;
                                    break;
                                }
                                else
                                {
                                    isNeedtoAdd=true;
                                }
                            }

                            if (isNeedtoAdd)
                            {
                                ListWorker tmp = new ListWorker((CalcWorker) this.priceCollection.get(i).getResourse(j));
                                workerList.add(tmp);
                            }

                        }
                    }
                }
            }
        }
        return workerList;
    }

    public boolean moveUp(CalcPrice price)
    {
        if (this.isHasLink(price))
        {
            if (this.priceCollection.getFirst()!=(price))
            {
                for (int i = 1; i < this.priceCollection.size(); i++)
                {
                    if (this.priceCollection.get(i)==price)
                    {
                        CalcPrice tmp=new CalcPrice(this.priceCollection.get(i));
                        this.priceCollection.remove(i);
                        this.priceCollection.add(i-1,tmp);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean moveDown(CalcPrice price)
    {
        if (this.isHasLink(price))
        {
            if (this.priceCollection.getLast()!=(price))
            {
                for (int i = 0; i < this.priceCollection.size()-1; i++)
                {
                    if (this.priceCollection.get(i)==(price))
                    {
                        CalcPrice tmp=new CalcPrice(this.priceCollection.get(i));
                        this.priceCollection.remove(i);
                        this.priceCollection.add(i+1,tmp);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void removePrice(int index)
    {
        if (this.priceCollection!=null)
        {
            if (index< this.priceCollection.size())
            {
                CalcPrice tmp=this.priceCollection.get(index);
                //this.autoRemoveResourseFromList(tmp);
                this.priceCollection.remove(index);
                if (this.priceCollection.size()==0)
                {
                    this.priceCollection=null;
                }
            }
        }
    }

    public void removePrice(CalcPrice price)
    {
        if (this.priceCollection!=null)
        {
            for (int i=0; i<this.priceCollection.size();i++)
            {
                if (price==this.priceCollection.get(i))
                {
                    removePrice(i);
                    return;
                }
            }
        }
    }

    /*private void removeMachineFromList(CalcMachine machine)
    {
        if (this.machineList!=null)
        {
            for (ListMachine item : this.machineList)
            {
                if (item.equals(machine))
                {
                    item.remove(machine);
                    return;
                }
            }
        }
    }

    private void removeMaterialFromList(CalcMaterial material)
    {

        if (this.materialList!=null)
        {
            for (ListMaterial item : this.materialList)
            {
                if (item.equals(material))
                {
                    item.remove(material);
                    return;
                }
            }
        }
    }

    private void removeWorkerFromList(CalcWorker worker)
    {
        if (this.workerList!=null)
        {
            for (ListWorker item : this.workerList)
            {
                if (item.equals(worker))
                {
                    item.remove(worker);
                    return;
                }
            }
        }
    }*/

}
