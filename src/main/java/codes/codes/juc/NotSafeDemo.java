package codes.codes.juc;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NotSafeDemo
{
	public static void main(String[] args)
	{
		Map<String,String> map = new ConcurrentHashMap<String,String>();//new HashMap<String,String>();
		for (int i = 1; i <=30; i++) 
		{
			new Thread(() -> {
				map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
				System.out.println(map);
			}, String.valueOf(i)).start();
		}
	}

	public static void SetNotSafe()
	{
		Set<String> set = new CopyOnWriteArraySet<String>();//new HashSet<String>();
		for (int i = 1; i <=30; i++) 
		{
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(set);
			}, String.valueOf(i)).start();
		}
	}

	public static void ListNotSafe()
	{
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();//new ArrayList<String>();
		
		for (int i = 1; i <=30; i++) 
		{
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
			}, String.valueOf(i)).start();
		}
	}
}