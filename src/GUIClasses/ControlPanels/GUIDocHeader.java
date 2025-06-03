package GUIClasses.ControlPanels;

import GUIClasses.GUIMainFrame;

import javax.swing.*;
import java.awt.*;

public class GUIDocHeader
{
    //Поля
    private GUIMainFrame parent;
    private JPanel docHeader;
    private JLabel id,name,uom,count,single,singleCost, singleWorks,singleMachines,singleOperator,singleMaterial;
    private JLabel                   total, totalCost, totalWorks,totalMachines,totalOperator,totalMaterial;
    private JLabel normal,coef,totalCount;

    //Конструктор
    public GUIDocHeader (GUIMainFrame parent)
    {
        this.parent=parent;
        docHeader = new JPanel();
        parent.add(docHeader);
        //docHeader.setBackground(new java.awt.Color(65, 132, 232));
        docHeader.setBackground(Color.lightGray);

        this.id=new JLabel();
        this.docHeader.add(id);
        this.id.setText("Шифр");
        this.id.setHorizontalAlignment(SwingConstants.CENTER);
        this.id.setBorder(BorderFactory.createLineBorder(Color.black));

        this.name=new JLabel();
        this.docHeader.add(name);
        this.name.setText("Наименование");
        this.name.setHorizontalAlignment(SwingConstants.CENTER);
        this.name.setBorder(BorderFactory.createLineBorder(Color.black));

        this.uom=new JLabel();
        this.docHeader.add(uom);
        this.uom.setText("Ед.изм.");
        this.uom.setHorizontalAlignment(SwingConstants.CENTER);
        this.uom.setBorder(BorderFactory.createLineBorder(Color.black));

        this.count=new JLabel();
        this.docHeader.add(count);
        this.count.setText("Кол-во");
        this.count.setHorizontalAlignment(SwingConstants.CENTER);
        this.count.setBorder(BorderFactory.createLineBorder(Color.black));

        this.normal=new JLabel();
        this.docHeader.add(normal);
        this.normal.setText("Норм.");
        this.normal.setHorizontalAlignment(SwingConstants.CENTER);
        this.normal.setBorder(BorderFactory.createLineBorder(Color.black));

        this.coef=new JLabel();
        this.docHeader.add(coef);
        this.coef.setText("Коэф.");
        this.coef.setHorizontalAlignment(SwingConstants.CENTER);
        this.coef.setBorder(BorderFactory.createLineBorder(Color.black));

        this.totalCount=new JLabel();
        this.docHeader.add(totalCount);
        this.totalCount.setText("Всего");
        this.totalCount.setHorizontalAlignment(SwingConstants.CENTER);
        this.totalCount.setBorder(BorderFactory.createLineBorder(Color.black));

        this.single=new JLabel();
        this.docHeader.add(single);
        this.single.setText("На еденицу");
        this.single.setHorizontalAlignment(SwingConstants.CENTER);
        this.single.setBorder(BorderFactory.createLineBorder(Color.black));

        this.singleCost=new JLabel();
        this.docHeader.add(singleCost);
        this.singleCost.setText("ПЗ");
        this.singleCost.setHorizontalAlignment(SwingConstants.CENTER);
        this.singleCost.setBorder(BorderFactory.createLineBorder(Color.black));

        this.singleWorks=new JLabel();
        this.docHeader.add(singleWorks);
        this.singleWorks.setText("Раб.");
        this.singleWorks.setHorizontalAlignment(SwingConstants.CENTER);
        this.singleWorks.setBorder(BorderFactory.createLineBorder(Color.black));

        this.singleMachines=new JLabel();
        this.docHeader.add(singleMachines);
        this.singleMachines.setText("Маш.");
        this.singleMachines.setHorizontalAlignment(SwingConstants.CENTER);
        this.singleMachines.setBorder(BorderFactory.createLineBorder(Color.black));

        this.singleOperator=new JLabel();
        this.docHeader.add(singleOperator);
        this.singleOperator.setText("Опер.");
        this.singleOperator.setHorizontalAlignment(SwingConstants.CENTER);
        this.singleOperator.setBorder(BorderFactory.createLineBorder(Color.black));

        this.singleMaterial=new JLabel();
        this.docHeader.add(singleMaterial);
        this.singleMaterial.setText("Мат.");
        this.singleMaterial.setHorizontalAlignment(SwingConstants.CENTER);
        this.singleMaterial.setBorder(BorderFactory.createLineBorder(Color.black));

        this.total=new JLabel();
        this.docHeader.add(total);
        this.total.setText("Всего");
        this.total.setHorizontalAlignment(SwingConstants.CENTER);
        this.total.setBorder(BorderFactory.createLineBorder(Color.black));

        this.totalCost=new JLabel();
        this.docHeader.add(totalCost);
        this.totalCost.setText("ПЗ");
        this.totalCost.setHorizontalAlignment(SwingConstants.CENTER);
        this.totalCost.setBorder(BorderFactory.createLineBorder(Color.black));

        this.totalWorks=new JLabel();
        this.docHeader.add(totalWorks);
        this.totalWorks.setText("Раб.");
        this.totalWorks.setHorizontalAlignment(SwingConstants.CENTER);
        this.totalWorks.setBorder(BorderFactory.createLineBorder(Color.black));

        this.totalMachines=new JLabel();
        this.docHeader.add(totalMachines);
        this.totalMachines.setText("Маш.");
        this.totalMachines.setHorizontalAlignment(SwingConstants.CENTER);
        this.totalMachines.setBorder(BorderFactory.createLineBorder(Color.black));

        this.totalOperator=new JLabel();
        this.docHeader.add(totalOperator);
        this.totalOperator.setText("Опер.");
        this.totalOperator.setHorizontalAlignment(SwingConstants.CENTER);
        this.totalOperator.setBorder(BorderFactory.createLineBorder(Color.black));

        this.totalMaterial=new JLabel();
        this.docHeader.add(totalMaterial);
        this.totalMaterial.setText("Мат.");
        this.totalMaterial.setHorizontalAlignment(SwingConstants.CENTER);
        this.totalMaterial.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    //Методы
    public void setBounds(int x, int y, int width, int height )
    {
        int nameWidth=width-0-14*45-75;
        int afetrNameHorizBegin=nameWidth+75;
        int afetrNameStep=45;

        this.docHeader.setBounds(x,y,width,height);
        this.id.setBounds(0,0,75,40);
        this.name.setBounds(74,0,nameWidth+1,40);
        this.uom.setBounds(afetrNameHorizBegin-1,0,47,40);
        afetrNameHorizBegin+=afetrNameStep;
        this.count.setBounds(afetrNameHorizBegin,0,135,20);
        this.normal.setBounds(afetrNameHorizBegin,19,45,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.coef.setBounds(afetrNameHorizBegin-1,19,47,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalCount.setBounds(afetrNameHorizBegin,19,45,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.single.setBounds(afetrNameHorizBegin-1,0,227,20);
        this.singleCost.setBounds(afetrNameHorizBegin-1,19,47,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleWorks.setBounds(afetrNameHorizBegin,19,45,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleMachines.setBounds(afetrNameHorizBegin-1,19,47,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleOperator.setBounds(afetrNameHorizBegin,19,45,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.singleMaterial.setBounds(afetrNameHorizBegin-1,19,47,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.total.setBounds(afetrNameHorizBegin,0,225,20);
        this.totalCost.setBounds(afetrNameHorizBegin,19,45,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalWorks.setBounds(afetrNameHorizBegin-1,19,47,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalMachines.setBounds(afetrNameHorizBegin,19,45,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalOperator.setBounds(afetrNameHorizBegin-1,19,47,21);
        afetrNameHorizBegin+=afetrNameStep;
        this.totalMaterial.setBounds(afetrNameHorizBegin,19,45,21);



    }
}
