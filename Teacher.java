public class Teacher extends Member{
	public Teacher(){}
    public Teacher(String account, String password, String name, String email, String phone, String identity) {
        super(account, password, name, email, phone, identity);
    }
    public void borrowBook(){}
    public void returnBook(){}
    public int borrowlimit = 10;
    public int fineperday = 100;
    public int getBorrowLimit() {return borrowlimit;}
    public int getFinePerDay() {return fineperday;}
}