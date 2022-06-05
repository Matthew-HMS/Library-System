import java.util.ArrayList;

public class Test {
    public static void main(String [] args){
        SearchBook sb = new SearchBook();
        ArrayList<Book> booklist = new ArrayList<Book>();
        Book b1 = new Book("001","little prince","funny","jk rowling","crown pub",0,"A");
        Book b2 = new Book("002","little prince 2","funny","jdk rowling","crown pub",0,"B");
        booklist.add(b1);
        booklist.add(b2);
        sb.search(booklist);
    }
}
