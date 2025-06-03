package Backend;

public class Rounder
{
    //Класс для округления double
    public static double round(double value, int order)
    {
        //Определяется порядок округления
        int ordermultiplayer=1;
        for (int i=0;i<order; i++)
        {
            ordermultiplayer=ordermultiplayer*10;
        }
        //Разбито построчно для возвращения правильного округления
        value = value*ordermultiplayer;
        value=Math.round(value);
        value=value/ordermultiplayer;
        return value;
    }

    public static double roundTenth(double value)
    {
        //Разбито построчно для возвращения правильного округления
        value = value*100;
        value=Math.round(value);
        value=value/100;
        return value;
    }
}
