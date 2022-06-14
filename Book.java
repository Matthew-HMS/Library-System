import java.time.LocalDate;

public class Book implements Cloneable{
	public Object clone() {
		Book book = null;
		try{
			book = (Book)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return book;
	}
	private String id;
	private String name;  // �ѦW�A���୫�`
	private String type;
	private String author;
	private String pub; // �X����
	private int hasLended; // �w�ɥX��
	private String address; // 
	private LocalDate date;
	private LocalDate returndate;
	private LocalDate returnduedate;
	private LocalDate reservedate;
	private Users reservemember;
	
	public Book(){}
	public Book(String id, String name, String type, String author, String pub, int hasLended,String address) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.author = author;
		this.pub = pub;
		this.hasLended = hasLended;
		this.address = address;
	}
	
	/*public Book(String name, int count, String type, String author, String address) {
	    this(0, name, count, type, author, 0, 0, address);
	}*/
	
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	
	public String getAuthor() {return author;}
	public void setAuthor(String author) {this.author = author;}
	 
	public String getPub() {return pub;}
	public void setPub(String pub) {this.pub = pub;}
	
	public int getHasLended() {return hasLended;}
	public void setHasLended(int hasLended) {this.hasLended = hasLended;}
	
	public LocalDate getBorrowDate() {return date;}
	public LocalDate getReturnDueDate() {return returnduedate;}
	public void setBorrowDate(LocalDate d) {
		
		this.date = d;
		if(d != null) {this.returnduedate = d.plusWeeks(2L);}
		else{this.returnduedate = null;}
		
	}
	public LocalDate getReturnDate() {return returndate;}
	public void setReturnDate(LocalDate d) {this.returndate = d;} 

	public LocalDate getReserveDate() {return reservedate;}
	public void setReserveDate(LocalDate d) {this.reservedate = d;}

	public Users getReserveMember() {return reservemember;}
	public void setReserveMember(Users user) {this.reservemember = user;}
	
	public String toString() {
		if (this.hasLended == 0) {
				return "ID:" + id + " �W��:" + name + " ����:" + type + " �@��:" + author
				+ " �X����:" + pub + " �ɾ\���A: �b�[�W �]�ð�:" + address + "\n";
		}
		else if (this.hasLended == 1){return "ID:" + id + " �W��:" + name + " ����:" + type + " �@��:" + author
				+ " �X����:" + pub  + " �ɾ\���A: �w�ɾ\ �]�ð�:" + address + " �ɾ\���:" + date+ " ���k�٤��:" + returnduedate + "\n";
		}
		else if(this.hasLended == 2) {return "ID:" + id + " �W��:" + name + " ����:" + type + " �@��:" + author
				+ " �X����:" + pub  + " �ɾ\���A: �w�w�q �]�ð�:" + address + " �w�q���:" + reservedate+ "\n";
		}
		else {return "ID:" + id + " �W��:" + name + " ����:" + type + " �@��:" + author
				+ " �X����:" + pub  + " �ɾ\���A: �w�k�� �]�ð�:" + address + " �ɾ\���:" + date+ " �w�k�٤��:" + returndate + "\n";
		}
	}
}

