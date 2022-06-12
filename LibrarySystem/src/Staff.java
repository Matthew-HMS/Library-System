public class Staff extends Member {
    public Staff(){}
    public Staff(String account, String password, String name, String email, String phone, String identity) {
        super(account, password, name, email, phone, identity);
    }
    public void borrowBook(){}
    public void returnBook(){}
    public int borrowlimit = 8;
    public int fineperday = 200;
    public int getBorrowLimit() {return borrowlimit;}
    public int getFinePerDay() {return fineperday;}
}