package Backend;

import Backend.Resourses.CalcMachine;
import Backend.Resourses.CalcMaterial;
import Backend.Resourses.CalcWorker;

import java.io.Serializable;

public class Index  implements Serializable
{
    private String name;
    private boolean doapplyTotalCoef;
    private double totalIndex;
    private double machIndex;
    private double operIndex;
    private double matIndex;
    private double workIndex;

    //Конструкторы
    public Index()
    {
        this.name=null;
        this.totalIndex=1;
        this.machIndex=1;
        this.operIndex=1;
        this.workIndex=1;
        this.doapplyTotalCoef=false;
    }

    public Index(String name)
    {
        this.name=name;
        this.totalIndex=1;
        this.machIndex=1;
        this.operIndex=1;
        this.workIndex=1;
        this.doapplyTotalCoef=false;
    }

    public Index(String name, double totalCoef)
    {
        this.name=name;
        this.totalIndex=1;
        this.machIndex=1;
        this.operIndex=1;
        this.workIndex=1;
        this.doapplyTotalCoef=true;
    }

    public Index(Index index)
    {
        this.name= index.name;
        this.totalIndex=index.totalIndex;
        this.machIndex=index.machIndex;
        this.operIndex=index.operIndex;
        this.workIndex=index.workIndex;
        this.doapplyTotalCoef=index.doapplyTotalCoef;
    }

    //Методы назначения коэффициента на ресурсы
    public void applyToRes(CalcWorker worker)
    {
        if (this.doapplyTotalCoef)
        {
            worker.setIndex(this.totalIndex);
        }
        else
        {
            worker.setIndex(this.workIndex);
        }
    }

    public void applyToRes(CalcMachine machine)
    {
        if (this.doapplyTotalCoef)
        {
            machine.setIndex(this.totalIndex);
            machine.setOperIndex(this.totalIndex);
        }
        else
        {
            machine.setIndex(this.machIndex);
            machine.setOperIndex(this.operIndex);
        }
    }

    public void applyToRes(CalcMaterial material)
    {
        if (this.doapplyTotalCoef)
        {
            material.setIndex(this.totalIndex);
        }
        else
        {
            material.setIndex(this.matIndex);
        }
    }
}
