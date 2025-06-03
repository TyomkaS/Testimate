package Backend.Documents;

import Backend.Prices.DBPrice;

import java.util.ArrayList;
import java.util.Objects;

public class BookPart
{
    private String name;
    private boolean isEditable;
    private ArrayList<DBPrice> col;

    public BookPart()
    {
        this.name=null;
        this.col=null;
        this.isEditable=true;
    }

    public BookPart(String name)
    {
        this.name=name;
        this.col=null;
        this.isEditable=true;
    }

    public BookPart(BookPart other)
    {
        this.name=other.name;
        this.isEditable= other.isEditable;
        if (other.col!=null)
        {
            this.col=new ArrayList<>(other.col.size());
            for (int i=0;i<other.col.size();i++)
            {
                DBPrice tmp=new DBPrice(other.col.get(i));
                tmp.setEditable(this.isEditable);
                this.col.add(tmp);
            }
        }

    }

    public void add()
    {
        if (isEditable)
        {
            if (this.col==null)
            {
                this.col=new ArrayList<>(1);
            }
            DBPrice tmp=new DBPrice();
            if (!isContains(tmp))
            {
                this.col.add(tmp);
            }
        }

    }

    public void add(DBPrice price)
    {
        if (isEditable)
        {
            DBPrice tmp=new DBPrice(price);
            if (this.col==null)
            {
                this.col=new ArrayList<>(1);
                this.col.add(tmp);
            }
            else
            {
                if (!this.isContains(price))
                {
                    this.col.add(price);
                }

            }
        }
    }

    public void add(int index, DBPrice price)
    {
        if (isEditable)
        {
            DBPrice tmp=new DBPrice(price);
            if (this.col==null)
            {
                this.col=new ArrayList<>(1);
                this.col.add(tmp);
            }
            else
            {
                if (index<this.col.size())
                {
                    if (!this.isContains(price))
                    {
                        this.col.add(index,price);
                    }

                }
                else
                {
                    this.add(price);
                }
            }
        }
    }

    public boolean isContains(DBPrice price)
    {
        if (this.col!=null)
        {
            for (int i=0; i<this.col.size();i++)
            {
                if (this.col.get(i).equals(price))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEditable(){return this.isEditable;}

    public boolean isHasLink(DBPrice link)
    {
        if (this.col!=null)
        {
            for (int i=0; i<this.col.size();i++)
            {
                if (this.col.get(i)==link)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public String getname(){return this.name;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookPart bookPart = (BookPart) o;
        return isEditable == bookPart.isEditable && Objects.equals(name, bookPart.name) && Objects.equals(col, bookPart.col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isEditable, col);
    }

    public DBPrice getprice(int index)
    {
        if (this.col!=null)
        {
            if (index<this.col.size())
                return this.col.get(index);
        }
        return null;
    }

    public int getsize(){if (this.col!=null){return this.col.size();}return 0;}

    public boolean moveUp(DBPrice price)
    {
        if (this.isHasLink(price))
        {
            if (this.col.getFirst()!=(price))
            {
                for (int i = 1; i < this.col.size(); i++)
                {
                    if (this.col.get(i)==price)
                    {
                        DBPrice tmp=new DBPrice(this.col.get(i));
                        this.col.remove(i);
                        this.col.add(i-1,tmp);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean moveDown(DBPrice price)
    {
        if (this.isHasLink(price))
        {
            if (this.col.getLast()!=(price))
            {
                for (int i = 0; i < this.col.size()-1; i++)
                {
                    if (this.col.get(i)==(price))
                    {
                        DBPrice tmp=new DBPrice(this.col.get(i));
                        this.col.remove(i);
                        this.col.add(i+1,tmp);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void remove(int index)
    {
        if (this.col!=null)
        {
            if (index<this.col.size())
            {
                this.col.get(index).removeAll();
                this.col.remove(index);
            }
        }
    }

    public void remove(DBPrice removing)
    {
        if (isHasLink(removing))
        {
            for (int i = 0; i < this.col.size(); i++)
            {
                if (this.col.get(i)==removing)
                {
                    this.col.get(i).removeAll();
                    this.col.remove(i);
                }
            }
        }
    }

    public void removeAll()
    {
        if (this.col!=null)
        {
            for (int i = this.col.size()-1; i > -1; i--)
            {
                this.col.get(i).removeAll();
                this.col.remove(i);
            }
        }
    }

    public void setEditable(boolean isEditable){this.isEditable=isEditable;}
    public void setName(String name){this.name=name;}
}
