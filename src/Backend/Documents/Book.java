package Backend.Documents;

import java.util.ArrayList;

public class Book
{
    private String name;
    private ArrayList<BookPart>col;
    private boolean isEditable;
    private String password;
    private String issuer;

    //Конструкторы
    public Book(String issuer)
    {
        this.name=null;
        this.col=null;
        this.isEditable=true;
        this.password=null;
        this.issuer=issuer;
    }

    public Book(Book other)
    {
        this.name=other.name;
        this.isEditable=other.isEditable;
        this.password=other.password;
        this.issuer=other.issuer;

        if (other.col!=null)
        {
            this.col=new ArrayList<>(other.col.size());
            for (int i = 0; i < other.col.size(); i++)
            {
                BookPart tmp=new BookPart(other.col.get(i));
                tmp.setEditable(this.isEditable);
                this.col.add(tmp);
            }
        }
        else
        {this.col=null;}
    }

    //Методы
    public void add()
    {
        if (isEditable)
        {
            BookPart tmp=new BookPart();
            tmp.setEditable(this.isEditable);
            if (this.col==null)
            {
                this.col=new ArrayList<>(1);
            }
            if (!this.isContains(tmp))
            {
                this.col.add(tmp);
            }
        }
    }

    public void add(BookPart bookPart)
    {
        if (isEditable)
        {
            BookPart tmp=new BookPart(bookPart);
            tmp.setEditable(this.isEditable);
            if (this.col==null)
            {
                this.col=new ArrayList<>(1);
            }
            if (!this.isContains(tmp))
            {
                this.col.add(tmp);
            }
        }
    }

    public void add(int index, BookPart bookPart)
    {
        if (isEditable)
        {
            BookPart tmp=new BookPart(bookPart);
            tmp.setEditable(this.isEditable);
            if (this.col==null)
            {
                this.col=new ArrayList<>(1);
            }
            if (index<this.col.size())
            {
                if (!this.isContains(tmp))
                {
                    this.col.add(index,tmp);
                }
            }
            else
            {this.col.add(tmp);}
        }
    }

    public String getName(){return this.name;}

    public String getIssuer(){return this.issuer;}

    public int getBookPartCount()
    {
        if (this.col!=null)
        {
            return this.col.size();
        }
        return 0;
    }

    public BookPart getBookPart(int index)
    {
        if (this.col!=null && index<this.col.size())
        {return this.col.get(index);}
        return null;
    }

    public boolean isEditable() {return isEditable;}

    public boolean isContains(BookPart bookPart)
    {
        if (this.col!=null)
        {
            for (int i = 0; i < this.col.size(); i++)
            {
                if(this.col.get(i).equals(bookPart))
                {return true;}
            }
        }
        return false;
    }

    public boolean isHasLink(BookPart bookPart)
    {
        if (this.col!=null)
        {
            for (int i = 0; i < this.col.size(); i++)
            {
                if(this.col.get(i)==(bookPart))
                {return true;}
            }
        }
        return false;
    }

    public void moveUp(BookPart part)
    {
        if (this.isHasLink(part))
        {
            if (this.col.getFirst()!=part)
            {
                for (int i = 1; i < this.col.size(); i++)
                {
                    if (this.col.get(i)==(part))
                    {
                        BookPart tmp=new BookPart(this.col.get(i));
                        this.col.remove(i);
                        this.col.add(i-1,tmp);
                    }
                }
            }
        }
    }

    public void moveDown(BookPart part)
    {
        if (this.isHasLink(part))
        {
            if (this.col.getLast()!=(part))
            {
                for (int i = 0; i < this.col.size()-1; i++)
                {
                    if (this.col.get(i)==(part))
                    {
                        BookPart tmp=new BookPart(this.col.get(i));
                        this.col.remove(i);
                        this.col.add(i+1,tmp);
                    }
                }
            }
        }
    }

    public void setName(String name)
    {
        if (isEditable)
        {this.name=name;}
    }

    public int setEditable(String pass, boolean value)
    {
        if (this.password==null)
        {
            this.isEditable=value;
            if (this.col!=null)
            {
                for(BookPart item:this.col)
                {item.setEditable(this.isEditable);}
            }
            return 0;
        }
        else
        {
            if (this.password.equals(pass))
            {
                this.isEditable=value;
                return 0;
            }
            return 1;
        }
    }

    public void setIssuer(String issuer)
    {
        if (isEditable)
        {
            this.issuer=issuer;
        }
    }

    public int setPass(String cpass, String newpass)
    {
        if (this.password!=null)
        {
            if (this.password.equals(cpass))
            {
                this.password=newpass;
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            this.password=newpass;
            return 0;
        }
    }

    public void remove (int index)
    {
        if (isEditable && this.col!=null)
        {
            if (index<this.col.size())
            {this.col.remove(index);}
        }
    }

    public void remove(BookPart removing)
    {
        if (isHasLink(removing))
        {
            for (int i = 0; i < this.col.size(); i++)
            {
                if (this.col.get(i)==removing)
                {
                    this.col.remove(i);
                }
            }
        }
    }
}
