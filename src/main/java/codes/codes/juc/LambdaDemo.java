package codes.codes.juc;


interface Foo
{
	//public void sayHello();
	
	//public void say886();
	public int add(int x, int y);
	
	default int div(int x, int y)
	{
		return x/y;
	}
	
	public static int sub(int x, int y)
	{
		return x - y;
	}
}


/**
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author zzyy
 * @date 2018年3月2日
 * 1	拷贝中括号+写死右箭头+落地大括号
 * 2	有且仅有一个方法
 * 3	@FunctionalInterface表示为它是一个函数式接口
 * 4	default默认方法
 * 5	静态方法实现
 */
public class LambdaDemo
{
	public static void main(String[] args)
	{
		
//		Foo foo = new Foo() {
//			@Override
//			public void sayHello()
//			{
//				System.out.println("-----sayHello 0906");
//			}
//
//			@Override
//			public void say886()
//			{
//				System.out.println("-----say886 0906");
//			}
//		};
//		foo.sayHello();
		
		System.out.println("================");
		
		Foo foo = (x,y) -> { 
				System.out.println("11111111111111111");
				return x + y; 
			};
		
		System.out.println("*******result: "+foo.add(31, 5));
		
		System.out.println("*******result: "+foo.div(10, 5));
		System.out.println("*******result: "+Foo.sub(10, 3));
	}
}
