package GUIClasses.ControlPanels;

import Backend.Documents.Calculation;
import GUIClasses.GUIMainFrame;
import GUIClasses.Viewers.DocViewer;
import GUIClasses.Viewers.ViewMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMiddleRibbon
{
    private int height, width, vertShift;
    private GUIMiddleRibbonState mrs;
    private GUIMainFrame parent;
    private JPanel mainMidRibbonPanel, editMidRibbonPanel,docMidRibbonPanel, paramMidRibbonPanel;
    private  JButton open,save,createCalc, createDB,editDB,close,                //главная панель
                     undoaction, doaction, delete,copy,cut,paste,moveup,movedown, moreinfo,//панель редактирования ч1
                     insertprice,insertheader,insertchapter, insertworkers, insertmachine,insertmaterial,//панель редактирования ч2
                     calcView, workerslistView, machinelistView, materialListView; //панель документа
    private JLabel mainCreate,editInsert,docViewMode;

    private DocViewer activedocviewer;

    public GUIMiddleRibbon(GUIMainFrame parent)
    {
        this.parent = parent;
        this.mrs= GUIMiddleRibbonState.DOC;
        this.activedocviewer=null;

        //Добавление mainMidRibbonPanel
        this.mainMidRibbonPanel = new JPanel();
        this.parent.add(mainMidRibbonPanel);
        mainMidRibbonPanel.setBackground(Color.lightGray);

        //Добавление кнопок для mainMidRibbonPanel
        open = new JButton("Открыть");
        mainMidRibbonPanel.add(open);
        save = new JButton("Сохранить");
        mainMidRibbonPanel.add(save);
        createCalc = new JButton("Смету");
        mainMidRibbonPanel.add(createCalc);
        createDB = new JButton("Базу данных");
        mainMidRibbonPanel.add(createDB);
        editDB  = new JButton("<html>" + "Редакт.базу" + "<br>" + "данных"+ "</html>");
        mainMidRibbonPanel.add(editDB);
        close = new JButton("Закрыть");
        mainMidRibbonPanel.add(close);
        //Добавление Label для mainMidRibbonPanel
        mainCreate = new JLabel("Создать");
        mainMidRibbonPanel.add(mainCreate);

        //Добавление editMidRibbonPanel
        this.editMidRibbonPanel = new JPanel();
        this.parent.add(editMidRibbonPanel);
        editMidRibbonPanel.setBackground(Color.lightGray);
        //Добавление кнопок для editMidRibbonPanel
        undoaction = new JButton("Отм.");
        editMidRibbonPanel.add(undoaction);
        doaction = new JButton("Верн.");
        editMidRibbonPanel.add(doaction);
        delete = new JButton("Удалить");
        editMidRibbonPanel.add(delete);
        copy = new JButton("Копир.");
        editMidRibbonPanel.add(copy);
        cut = new JButton("Вырез.");
        editMidRibbonPanel.add(cut);
        paste = new JButton("Встав.");
        editMidRibbonPanel.add(paste);
        moveup = new JButton("Вверх");
        editMidRibbonPanel.add(moveup);
        movedown = new JButton("Вниз");
        editMidRibbonPanel.add(movedown);
        moreinfo = new JButton("<html>" + "Доп." + "<br>" + "инфо"+ "</html>");
        editMidRibbonPanel.add(moreinfo);
        insertprice = new JButton("Расц.");
        editMidRibbonPanel.add(insertprice);
        insertheader = new JButton("Заголовок");
        editMidRibbonPanel.add(insertheader);
        insertchapter = new JButton("Раздел");
        editMidRibbonPanel.add(insertchapter);
        insertworkers = new JButton("Раб.");
        editMidRibbonPanel.add(insertworkers);
        insertmachine = new JButton("Маш.");
        editMidRibbonPanel.add(insertmachine);
        insertmaterial = new JButton("Мат.");
        editMidRibbonPanel.add(insertmaterial);
        //Добавление Label для editMidRibbonPanel
        editInsert = new JLabel("Вставить");
        editMidRibbonPanel.add(editInsert);

        //Добавление docMidRibbonPanel
        this.docMidRibbonPanel = new JPanel();
        this.parent.add(docMidRibbonPanel);
        docMidRibbonPanel.setBackground(Color.lightGray);
        //Добавление кнопок для docMidRibbonPanel
        calcView = new JButton("Смета");
        docMidRibbonPanel.add(calcView);
        workerslistView = new JButton("Ведомость рабочих");
        docMidRibbonPanel.add(workerslistView);
        machinelistView = new JButton("Ведомость машин");
        docMidRibbonPanel.add(machinelistView);
        materialListView = new JButton("Ведомость материалов");
        docMidRibbonPanel.add(materialListView);
        //Добавление Label для docMidRibbonPanel
        docViewMode = new JLabel("Режим просмотра");
        docMidRibbonPanel.add(docViewMode);

        //Добавление paramMidRibbonPanel
        this.paramMidRibbonPanel = new JPanel();
        this.parent.add(paramMidRibbonPanel);
        paramMidRibbonPanel.setBackground(Color.magenta);

        this.addActions();
    }

    public GUIMiddleRibbon(GUIMainFrame parent, int verticalShift)
    {
        this.vertShift=verticalShift;
        this.parent = parent;
        this.mrs= GUIMiddleRibbonState.DOC;

        this.mainMidRibbonPanel = new JPanel();
        this.parent.add(mainMidRibbonPanel);
        mainMidRibbonPanel.setBackground(Color.lightGray);

        //Добавление кнопок для mainMidRibbonPanel
        open = new JButton("Открыть");
        mainMidRibbonPanel.add(open);
        save=new JButton("Сохранить");
        mainMidRibbonPanel.add(save);
        createCalc = new JButton("Смету");
        mainMidRibbonPanel.add(createCalc);
        createDB = new JButton("Базу данных");
        mainMidRibbonPanel.add(createDB);
        editDB  = new JButton("<html>" + "Редакт.базу" + "<br>" + "данных"+ "</html>");
        mainMidRibbonPanel.add(editDB);
        close = new JButton("Закрыть");
        mainMidRibbonPanel.add(close);
        //Добавление Label для mainMidRibbonPanel
        mainCreate = new JLabel("Создать");
        mainMidRibbonPanel.add(mainCreate);



        this.editMidRibbonPanel = new JPanel();
        this.parent.add(editMidRibbonPanel);
        editMidRibbonPanel.setBackground(Color.lightGray);
        //Добавление кнопок для editMidRibbonPanel
        undoaction = new JButton("Отм.");
        editMidRibbonPanel.add(undoaction);
        doaction = new JButton("Верн.");
        editMidRibbonPanel.add(doaction);
        delete = new JButton("Удалить");
        editMidRibbonPanel.add(delete);
        copy = new JButton("Копир.");
        editMidRibbonPanel.add(copy);
        cut = new JButton("Вырез.");
        editMidRibbonPanel.add(cut);
        paste = new JButton("Встав.");
        editMidRibbonPanel.add(paste);
        moveup = new JButton("Вверх");
        editMidRibbonPanel.add(moveup);
        movedown = new JButton("Вниз");
        editMidRibbonPanel.add(movedown);
        moreinfo = new JButton("<html>" + "Доп." + "<br>" + "инфо"+ "</html>");
        editMidRibbonPanel.add(moreinfo);
        insertprice = new JButton("Расц.");
        editMidRibbonPanel.add(insertprice);
        insertheader = new JButton("Заголовок");
        editMidRibbonPanel.add(insertheader);
        insertchapter = new JButton("Раздел");
        editMidRibbonPanel.add(insertchapter);
        insertworkers = new JButton("Раб.");
        editMidRibbonPanel.add(insertworkers);
        insertmachine = new JButton("Маш.");
        editMidRibbonPanel.add(insertmachine);
        insertmaterial = new JButton("Мат.");
        editMidRibbonPanel.add(insertmaterial);
        //Добавление Label для editMidRibbonPanel
        editInsert = new JLabel("Вставить");
        editMidRibbonPanel.add(editInsert);


        this.docMidRibbonPanel = new JPanel();
        this.parent.add(docMidRibbonPanel);
        docMidRibbonPanel.setBackground(Color.lightGray);
        //Добавление кнопок для docMidRibbonPanel
        calcView = new JButton("Смета");
        docMidRibbonPanel.add(calcView);
        workerslistView = new JButton("Ведомость рабочих");
        docMidRibbonPanel.add(workerslistView);
        machinelistView = new JButton("Ведомость машин");
        docMidRibbonPanel.add(machinelistView);
        materialListView = new JButton("Ведомость материалов");
        docMidRibbonPanel.add(materialListView);
        //Добавление Label для docMidRibbonPanel
        docViewMode = new JLabel("Режим просмотра");
        docMidRibbonPanel.add(docViewMode);

        this.paramMidRibbonPanel = new JPanel();
        this.parent.add(paramMidRibbonPanel);
        paramMidRibbonPanel.setBackground(Color.magenta);

        this.addActions();
    }

    private void addActions()
    {
        //Добавление действий для mainMidRibbonPanel
        open.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        parent.open();
                    }
                }
        );

        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        parent.save();
                    }
                }
        );

        createCalc.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Calculation calc=new Calculation();
                        activedocviewer.setDocument(calc);
                    }
                }
        );
        createDB.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JOptionPane.showMessageDialog(null, "Данный функционал пока не реализован");
                    }
                }
        );
        editDB.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JOptionPane.showMessageDialog(null, "Данный функционал пока не реализован");
                    }
                }
        );
        close.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JOptionPane.showMessageDialog(null, "Данный функционал пока не реализован");
                    }
                }
        );



        //Добавление дейстивй для editMidRibbonPanel
        undoaction.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.undoaction();
                        }
                    }
                }
        );

        doaction.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.doaction();
                        }
                    }
                }
        );
        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.del();
                        }
                    }
                }
        );
        copy.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            parent.copy(activedocviewer.copy());
                        }
                    }
                }
        );
        cut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            parent.copy(activedocviewer.cut());
                        }
                    }
                }
        );
        paste.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            parent.paste();
                        }
                    }
                }
        );
        moveup.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.moveup();
                        }
                    }
                }
        );
        movedown.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.movedown();
                        }
                    }
                }
        );
        moreinfo.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.moreinfo();
                        }
                    }
                }
        );
        insertprice.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.addPrice();
                        }
                    }
                }
        );
        //Обработчик будет добавлен, когда будет реализован класс Header
        insertheader.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.addHeader();
                        }
                    }
                }
        );
        insertchapter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.addPart();
                        }
                    }
                }
        );
        insertworkers.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.addWorker();
                        }
                    }
                }
        );
        insertmachine.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.addMachine();
                        }
                    }
                }
        );
        insertmaterial.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.addMaterial();
                        }
                    }
                }
        );


        calcView.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.setViewMode(ViewMode.CALC);
                        }
                    }
                }
        );

        workerslistView.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.setViewMode(ViewMode.WORK);
                        }
                    }
                }
        );

        machinelistView.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.setViewMode(ViewMode.MACH);
                        }
                    }
                }
        );

        materialListView.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (activedocviewer!=null)
                        {
                            activedocviewer.setViewMode(ViewMode.MAT);
                        }
                    }
                }
        );
    }

    public void reSize(int width, int height, int vertShift)
    {
        //Dimension parentsize = parent.getBounds().getSize();
        this.height=height;
        this.vertShift=vertShift;
//        if ((int)(parentsize.height * 0.02)<20)
//        {
//            this.height=60;
//        }
//        else
//        {
//            this.height=(((int)(parentsize.height * 0.02))/10)*3*10;
//            //такая форма записи нужна, чтобы высота была кратна 10
//        }
//        System.out.println("middle ribbon height="+this.height);
        //System.out.println("middle ribbon state="+this.mrs);

        switch (this.mrs)
        {
            case GUIMiddleRibbonState.MAIN:
                this.mainMidRibbonPanel.setBounds(0,this.vertShift,width, this.height);
                //Расстановка кнопок
                open.setBounds(2,2,100, this.height-4);
                mainCreate.setBounds(104,2,242, (int)(this.height/3)-4);
                mainCreate.setHorizontalAlignment(SwingConstants.CENTER);
                createCalc.setBounds(104,(int)(this.height/3)+2,120, (int)((this.height/3))*2-4);
                createDB.setBounds(226,(int)(this.height/3)+2,120, (int)((this.height/3))*2-4);
                editDB.setBounds(348,2,120, this.height-4);
                close.setBounds(470,2,120, this.height-4);

                this.editMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                this.docMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                this.paramMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                break;
            case GUIMiddleRibbonState.EDIT:
                this.mainMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                this.editMidRibbonPanel.setBounds(0,this.vertShift,width, this.height);
                //Расстановка кнопок
                undoaction.setBounds(2,2,70, (int)((this.height/3))*2-4);
                doaction.setBounds(74,2,70, (int)((this.height/3))*2-4);
                delete.setBounds(2,(int)((this.height/3))*2,142, (int)((this.height/3))-2);
                copy.setBounds(146,2,75, this.height-4);
                cut.setBounds(223,2,75, this.height-4);
                paste.setBounds(300,2,75, this.height-4);
                moveup.setBounds(377,2,70, (int)(this.height/2)-2);
                movedown.setBounds(377,(int)(this.height/2)+2,70, (int)(this.height/2)-4);
                moreinfo.setBounds(449,2,60, this.height-4);
                insertprice.setBounds(511,(int)(this.height/3),70, (int)((this.height/3))*2-2);
                insertheader.setBounds(583,(int)(this.height/3),100, (int)((this.height/3))-2);
                insertchapter.setBounds(583,(int)((this.height/3)*2),100, (int)((this.height/3))-2);
                insertworkers.setBounds(685,(int)(this.height/3),60, (int)((this.height/3))*2-2);
                insertmachine.setBounds(747,(int)(this.height/3),65, (int)((this.height/3))*2-2);
                insertmaterial.setBounds(814,(int)(this.height/3),65, (int)((this.height/3))*2-2);
                editInsert.setBounds(511,2,368, (int)((this.height/3))-2);
                editInsert.setHorizontalAlignment(SwingConstants.CENTER);

                this.docMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                this.paramMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                break;
            case GUIMiddleRibbonState.DOC:
                this.mainMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                this.editMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                this.docMidRibbonPanel.setBounds(0,this.vertShift,width, this.height);
                //Расстановка кнопок
                calcView.setBounds(2,(int)(this.height/3),100, (int)((this.height/3))*2-2);
                workerslistView.setBounds(104,(int)(this.height/3),150, (int)((this.height/3))*2-2);
                machinelistView.setBounds(256,(int)(this.height/3),150, (int)((this.height/3))*2-2);
                materialListView.setBounds(408,(int)(this.height/3),180, (int)((this.height/3))*2-2);
                docViewMode.setBounds(2,2,586, (int)((this.height/3))-2);
                docViewMode.setHorizontalAlignment(SwingConstants.CENTER);

                this.paramMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                break;
            case GUIMiddleRibbonState.PARAM:
                this.mainMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                this.editMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                this.docMidRibbonPanel.setBounds(0,this.vertShift,0, 0);
                this.paramMidRibbonPanel.setBounds(0,this.vertShift,width, this.height);
                break;
        }

        //
    }

    public int getHeight()
    {return this.height;}

    public void setBounds(int x, int y, int width, int height)
    {
        this.height = height;
        switch (this.mrs) {
            case GUIMiddleRibbonState.MAIN:
                this.mainMidRibbonPanel.setBounds(x, y, width, this.height);
                //Расстановка кнопок
                open.setBounds(2, 2, 100, this.height - 4);
                save.setBounds(104,2,100,this.height-4);
                mainCreate.setBounds(206, 2, 242, (int) (this.height / 3) - 4);
                mainCreate.setHorizontalAlignment(SwingConstants.CENTER);
                createCalc.setBounds(206, (int) (this.height / 3) + 2, 120, (int) ((this.height / 3)) * 2 - 4);
                createDB.setBounds(328, (int) (this.height / 3) + 2, 120, (int) ((this.height / 3)) * 2 - 4);
                editDB.setBounds(450, 2, 120, this.height - 4);
                close.setBounds(572, 2, 120, this.height - 4);


                this.editMidRibbonPanel.setBounds(x, y, 0, 0);
                this.docMidRibbonPanel.setBounds(x, y, 0, 0);
                this.paramMidRibbonPanel.setBounds(x, y, 0, 0);
                break;
            case GUIMiddleRibbonState.EDIT:
                this.mainMidRibbonPanel.setBounds(x, y, 0, 0);
                this.editMidRibbonPanel.setBounds(x, y, width, this.height);
                //Расстановка кнопок
                undoaction.setBounds(2, 2, 70, (int) ((this.height / 3)) * 2 - 4);
                doaction.setBounds(74, 2, 70, (int) ((this.height / 3)) * 2 - 4);
                delete.setBounds(2, (int) ((this.height / 3)) * 2, 142, (int) ((this.height / 3)) - 2);
                copy.setBounds(146, 2, 75, this.height - 4);
                cut.setBounds(223, 2, 75, this.height - 4);
                paste.setBounds(300, 2, 75, this.height - 4);
                moveup.setBounds(377, 2, 70, (int) (this.height / 2) - 2);
                movedown.setBounds(377, (int) (this.height / 2) + 2, 70, (int) (this.height / 2) - 4);
                moreinfo.setBounds(449, 2, 60, this.height - 4);
                insertprice.setBounds(511, (int) (this.height / 3), 70, (int) ((this.height / 3)) * 2 - 2);
                insertheader.setBounds(583, (int) (this.height / 3), 100, (int) ((this.height / 3)) - 2);
                insertchapter.setBounds(583, (int) ((this.height / 3) * 2), 100, (int) ((this.height / 3)) - 2);
                insertworkers.setBounds(685, (int) (this.height / 3), 60, (int) ((this.height / 3)) * 2 - 2);
                insertmachine.setBounds(747, (int) (this.height / 3), 65, (int) ((this.height / 3)) * 2 - 2);
                insertmaterial.setBounds(814, (int) (this.height / 3), 65, (int) ((this.height / 3)) * 2 - 2);
                editInsert.setBounds(511, 2, 368, (int) ((this.height / 3)) - 2);
                editInsert.setHorizontalAlignment(SwingConstants.CENTER);

                this.docMidRibbonPanel.setBounds(x, y, 0, 0);
                this.paramMidRibbonPanel.setBounds(x, y, 0, 0);
                break;
            case GUIMiddleRibbonState.DOC:
                this.mainMidRibbonPanel.setBounds(x, y, 0, 0);
                this.editMidRibbonPanel.setBounds(x, y, 0, 0);
                this.docMidRibbonPanel.setBounds(x, y, width, this.height);
                //Расстановка кнопок
                calcView.setBounds(2, (int) (this.height / 3), 100, (int) ((this.height / 3)) * 2 - 2);
                workerslistView.setBounds(104, (int) (this.height / 3), 150, (int) ((this.height / 3)) * 2 - 2);
                machinelistView.setBounds(256, (int) (this.height / 3), 150, (int) ((this.height / 3)) * 2 - 2);
                materialListView.setBounds(408, (int) (this.height / 3), 180, (int) ((this.height / 3)) * 2 - 2);
                docViewMode.setBounds(2, 2, 586, (int) ((this.height / 3)) - 2);
                docViewMode.setHorizontalAlignment(SwingConstants.CENTER);

                this.paramMidRibbonPanel.setBounds(0, this.vertShift, 0, 0);
                break;
            case GUIMiddleRibbonState.PARAM:
                this.mainMidRibbonPanel.setBounds(0, this.vertShift, 0, 0);
                this.editMidRibbonPanel.setBounds(0, this.vertShift, 0, 0);
                this.docMidRibbonPanel.setBounds(0, this.vertShift, 0, 0);
                this.paramMidRibbonPanel.setBounds(x, y, width, this.height);
                break;
        }
    }

    public void setDocViewer(DocViewer doc)
    {this.activedocviewer=doc;}

    public void setVertShift (int val)
    {this.vertShift=val;}

    public void setMainMode()
    {
        this.mrs= GUIMiddleRibbonState.MAIN;
        //this.reSize();
    };

    public void setEditMode()
    {
        this.mrs= GUIMiddleRibbonState.EDIT;
        //this.reSize();
    };

    public void setDocMode()
    {
        this.mrs= GUIMiddleRibbonState.DOC;
        //this.reSize();
    };

    public void setParamMode()
    {
        this.mrs= GUIMiddleRibbonState.PARAM;
        //this.reSize();
    };
}
