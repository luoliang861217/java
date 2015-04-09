
public class Test {
	public static void main(String[] args) throws Exception {
		Student stu1 = new Student();
		stu1.setAge(10);
		stu1.setName("luoliang");
		Address info1 = new Address();
		info1.setInfo("dizhi1");
		stu1.setAddress(info1);
		
		Student stu2 = (Student)stu1.clone();

		Address info2 = new Address();
		info2.setInfo("dizhi1");
//		stu2.setAddress(info2);
		

		System.out.println(stu1.getAge());
		System.out.println(stu1.getName());
		System.out.println(stu1.getAddress());
		System.out.println("---------------------------------");		
		System.out.println(stu2.getAge());
		System.out.println(stu2.getName());
		System.out.println(stu2.getAddress());
		

		System.out.println(stu2.getName().intern() == stu1.getName().intern());
		System.out.println(stu2.getAddress() == stu1.getAddress());
		System.out.println(stu2.getAddress().getInfo().intern() == stu1.getAddress().getInfo().intern());
	}
}

class Student implements Cloneable{
	private int age;
	private String name;
	private Address address;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
//		return super.clone();
		
		Address address = (Address)this.getAddress().clone();
		Student obj = (Student)super.clone();
		obj.setAddress(address);
		return obj;		
	}
}
class Address implements Cloneable{
	private String info;

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
