package GUIClasses.Viewers;

import Backend.Documents.Calculation;
import GUIClasses.GUIMainFrame;

public interface DocViewer
{
    public Calculation getSourse();
    public int getHeight();
    public void setDocument(Calculation calculation);
    public void setBounds(int top, int width);
    public void setMainFrame(GUIMainFrame mf);
    public void setViewMode(ViewMode mode);

    //GUIMiddleRibbon methods (для редактирования документа)
    public void doaction();
    public void undoaction();
    public void del();

    public Object copy();
    public Object cut();
    public void paste(Object obj);

    public void moveup();
    public void movedown();
    public void moreinfo();

    public void addPrice();
    public void addHeader();
    public void addPart();
    public void addWorker();
    public void addMachine();
    public void addMaterial();

    //Методы для перемещения документа в окне
    public void scroll(int value);
    public int getHieght();
    public void open();
    public void save();
    public void close();
}
