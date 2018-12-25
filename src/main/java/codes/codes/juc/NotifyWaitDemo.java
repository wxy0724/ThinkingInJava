package codes.codes.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData
{
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment() throws InterruptedException
	{
		lock.lock();
		try 
		{
			//1 判断
			while(number != 0)
			{
				condition.await();//this.wait();
			}
			//2 干活
			++number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//3 通知
			condition.signalAll();//this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}	
	public void decrement() throws InterruptedException
	{
		lock.lock();
		try 
		{
			//1 判断
			while(number == 0)
			{
				condition.await();//this.wait();
			}
			//2 干活
			--number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//3 通知
			condition.signalAll();//this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}	
	
	
	/*public synchronized void increment() throws InterruptedException
	{
		//1 判断
		while(number != 0)
		{
			this.wait(); // A B
		}
		//2 干活
		++number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		//3 通知
		this.notifyAll();
	}
	
	public synchronized void decrement() throws InterruptedException
	{
		//1 判断
		while(number == 0)
		{
			this.wait();
		}
		//2 干活
		--number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		//3 通知
		this.notifyAll();
	}*/
}


/**
 * 
 * @Description: 
 * 现在两个线程，
 * 可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 交替，来5轮，变量初始值为零。
 * @author zzyy
 * @date 2018年3月1日
 */
public class NotifyWaitDemo
{
	public static void main(String[] args)
	{
		ShareData sd = new ShareData();

		new Thread(() -> {
			for (int i = 1; i <=10; i++) 
			{
				try 
				{
					sd.increment();
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "AA").start();
		
		new Thread(() -> {
			for (int i = 1; i <=10; i++) 
			{
				try 
				{
					sd.decrement();
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();		
		
		new Thread(() -> {
			for (int i = 1; i <=10; i++) 
			{
				try 
				{
					sd.increment();
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "CC").start();
		
		new Thread(() -> {
			for (int i = 1; i <=10; i++) 
			{
				try 
				{
					sd.decrement();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "DD").start();			
		
	}
}
