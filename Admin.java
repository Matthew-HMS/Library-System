public class Admin extends Users{
    public Admin(){}
    ArrayList<Integer> bookId = new ArrayList<Integer>();
    ArrayList<String> bookName = new ArrayList<String>();
    ArrayList<Integer> bookCount = new ArrayList<Integer>();
    ArrayList<String> bookType = new ArrayList<String>();
    ArrayList<String> bookAuthor = new ArrayList<String>();
    ArrayList<String> bookPub = new ArrayList<String>();
    ArrayList<Integer> bookHasLended = new ArrayList<Integer>();
    ArrayList<String> bookAddress = new ArrayList<String>();
    
    public Admin(String account, String password, String name, String email, String phone, String identity){
        super(account, password, name, email, phone, identity);
    }
    
    public void addBook(Book book){bookId.add(book.id);
                                  bookName.add(book.name);
                                  bookCount.add(book.count);
                                  bookType.add(book.type);
                                  bookAuthor.add(book.author);
                                  bookPub.add(book.pub);
                                  bookHasLended.add(book.hasLended);
                                  bookAddress.add(book.address);}//添加書本到各資料陣列中
    public void EditBook(int id){
        if(bookId.contains(id) == true)
        {int NumOfArray = bookId.indexOf(id);
        }
        else
        {}
    }
    public void EditBook(String name){
        if(bookName.contains(name) == true)
        {int NumOfArray = bookName.indexOf(name);
        }
        else
        {}
    public void DeleteBook(int id){
        if(bookId.contains(id) == true)
        {int NumOfArray = bookId.indexOf(id);
        }
        else
        {}
    }
    public void DeleteBook(String name){
        if(bookName.contains(name) == true)
        {int NumOfArray = bookName.indexOf(name);
        }
        else
        {}
    public void viewInfo(int id){
        if(bookId.contains(id) == true)
        {int NumOfArray = bookId.indexOf(id);
        }
        else
        {}
    }
    public void viewInfo(String name){
        if(bookName.contains(name) == true)
        {int NumOfArray = bookName.indexOf(name);
        }
        else
        {}
    public void searchBook(int id){
        if(bookId.contains(id) == true)
        {int NumOfArray = bookId.indexOf(id);
        }
        else
        {}
    }
    public void searchBook(String name){
        if(bookName.contains(name) == true)
        {int NumOfArray = bookName.indexOf(name);
        }
        else
        {}
    }
}
