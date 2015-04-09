
public class simpleTest{
	static class simple extends Thread{
		public void run(){
			System.out.println(Thread.currentThread().getId());
			try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	public static void main(String[] args){
		for(int i=0;i<10;i++){
			simple s = new simple();
			s.start();
		}
	}
}
