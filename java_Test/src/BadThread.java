
public class BadThread implements Runnable{
	public BadThread(){
		System.out.println( Thread.currentThread().getId() + " is in ");
		synchronized(BadThread.class){
			System.out.println( Thread.currentThread().getId() + " is Running ");
		}
	}
	public static void main(String[] args){
		for(int i=0;i<10;i++){
			BadThread bc = new BadThread();
			Thread t = new Thread(bc);
			t.start();
		}
	}
	@Override
	public void run() {
		System.out.println( Thread.currentThread().getId() + " is out ");
		try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
	}
}
