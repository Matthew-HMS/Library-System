public class Student extends Member{
	public Student(){}
    public Student(String account, String password, String name, String email, String phone, String identity) {
        super(account, password, name, email, phone, identity);
    }
    public void borrowBook(){}
    public void returnBook(){}

    public int borrowlimit = 5;
    public int fineperday = 10;
    public int getBorrowLimit() {return borrowlimit;}
    public int getFinePerDay() {return fineperday;}
}