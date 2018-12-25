package codes.codes.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket
{
	//Lock implementations provide more extensive locking operations 
	//than can be obtained using synchronized methods and statements. 
	private int number = 30;
	private Lock lock = new ReentrantLock();//List list = new ArrayList();
	
	public void sale()
	{
		lock.lock();
		try 
		{
			if(number > 0)
			{
				System.out.println(Thread.currentThread().getName()+"卖出第:"+(number--)+"\t 还剩下:"+number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
}

/**
 * 第一个case
 * @Description: 买票程序复习线程知识 ,三个售票员			卖出			30张票
 * @author zzyy
 * @date 2018年3月1日
 * 
 * 1	什么是concurrent? 
 * 2	什么是并发?什么是并行?
 * 3	什么是进程?什么是线程?
 * 4	多线程有哪些状态及其转换?
 * ---------------------------------------------
 * 1	线程		操作		资源类()
 * 2	高内聚	低耦合
 * 3	线程调用
 * Thread(Runnable target, String name) 		Allocates a new Thread object.
 */
public class SaleTicket
{
	public static void main(String[] args)
	{
		Ticket ticket = new Ticket();
		
		new Thread(() -> { for (int i = 1; i <=40; i++)  ticket.sale(); }, "AA").start();
		new Thread(() -> { for (int i = 1; i <=40; i++)  ticket.sale(); }, "BB").start();
		new Thread(() -> { for (int i = 1; i <=40; i++)  ticket.sale(); }, "CC").start();
		
		/*new Thread(new Runnable() {
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++) 
				{
					ticket.sale();
				}
			}
		}, "AA").start();
		
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++) 
				{
					ticket.sale();
				}
			}
		}, "BB").start();
		
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++) 
				{
					ticket.sale();
				}
			}
		}, "CC").start();*/
		
	}
}
//1	class MyThread implemtns Runnable   	

//2 匿名内部类

//3 Lambda Express

