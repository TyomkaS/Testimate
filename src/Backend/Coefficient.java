package Backend;

import Backend.Resourses.CalcMachine;
import Backend.Resourses.CalcMaterial;
import Backend.Resourses.CalcWorker;

import java.io.Serializable;

public class Coefficient  implements Serializable
{
    private String name;
    private boolean doapplyTotalCoef;
    private double totalCoef;
    private double machCoef;
    private double matCoef;
    private double workCoef;

    //Конструкторы
    public Coefficient()
    {
        this.name=null;
        this.totalCoef=1;
        this.machCoef=1;
        this.matCoef=1;
        this.workCoef=1;
        this.doapplyTotalCoef=false;
    }

    public Coefficient(String name)
    {
        this.name=name;
        this.totalCoef=1;
        this.machCoef=1;
        this.matCoef=1;
        this.workCoef=1;
        this.doapplyTotalCoef=false;
    }

    public Coefficient(String name, double totalCoef)
    {
        this.name=name;
        this.totalCoef=totalCoef;
        this.machCoef=1;
        this.matCoef=1;
        this.workCoef=1;
        this.doapplyTotalCoef=true;
    }

    public Coefficient(Coefficient coef)
    {
        this.name= coef.name;
        this.totalCoef=coef.totalCoef;
        this.machCoef=coef.machCoef;
        this.matCoef=coef.matCoef;
        this.workCoef=coef.workCoef;
        this.doapplyTotalCoef=coef.doapplyTotalCoef;
    }

    //Методы назначения коэффициента на ресурсы
    public void applyToRes(CalcWorker worker)
    {
        if (this.doapplyTotalCoef)
        {
            worker.setCoef(this.totalCoef);
        }
        else
        {
            worker.setCoef(this.workCoef);
        }
    }

    public void applyToRes(CalcMachine machine)
    {
        if (this.doapplyTotalCoef)
        {
            machine.setCoef(this.totalCoef);
        }
        else
        {
            machine.setCoef(this.machCoef);
        }
    }

    public void applyToRes(CalcMaterial material)
    {
        if (this.doapplyTotalCoef)
        {
            material.setCoef(this.totalCoef);
        }
        else
        {
            material.setCoef(this.matCoef);
        }
    }

    //Геттеры
    public String getName()
    {
        return name;
    }

    public boolean isAppliedTotalCoef()
    {
        return doapplyTotalCoef;
    }

    public double getTotalCoef()
    {
        return totalCoef;
    }

    public double getMachCoef() {return machCoef;}

    public double getActiveMachCoef()
    {
        if (this.doapplyTotalCoef)
        {
            return this.totalCoef;
        }
        else
        {
            return this.machCoef;
        }
    }

    public double getMatCoef()
    {
        return matCoef;
    }

    public double getActiveMatCoef()
    {
        if (this.doapplyTotalCoef)
        {
            return this.totalCoef;
        }
        else
        {
            return this.matCoef;
        }
    }

    public double getWorkCoef()
    {
        return workCoef;
    }

    public double getActiveWorkCoef()
    {
        if (this.doapplyTotalCoef)
        {
            return this.totalCoef;
        }
        else
        {
            return this.workCoef;
        }
    }

    //Сеттеры
    public void setName(String name)
    {
        this.name = name;
    }

    public void setDoapplyTotalCoef(boolean doapplyTotalCoef)
    {
        this.doapplyTotalCoef = doapplyTotalCoef;
    }

    public void setTotalCoef(double totalCoef)
    {
        this.totalCoef = totalCoef;
    }

    public void setMachCoef(double machCoef)
    {
        this.machCoef = machCoef;
    }

    public void setMatCoef(double matCoef)
    {
        this.matCoef = matCoef;
    }

    public void setWorkCoef(double workCoef)
    {
        this.workCoef = workCoef;
    }
}
