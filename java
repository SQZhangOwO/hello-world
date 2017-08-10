#java语法小笔记
静态与非静态变量区别就在于一个是类独有
他存在于特殊的方法区或者叫共享数据区 是所有对象共有
然后
静态 只 能被静态所调用
非静态 静态非静态均可以访问
静态方法不可以用 this super 可以用类名直接调用
非静态方法一定需要对象去调用用

类是占内存的 这肯定的

函数是否用静态函数修饰 参考一点就是该函数是否有功能访问到对象中的特有数据

即是指该功能是否需要访问非静态的成员变量,如果需要,这就是非静态的
如果不需要就是静态的 当然也可以非静态的
但非静态需要被对象调用,而仅创建对象调用非静态却没有访问特有数据的方法 对象创建无意义
 
 静态代码块
 随着类的加载而执行 而且只执行一次
 作用
 	用于给类进行初始化
 	比如要给静态变量没赋值 可以在静态代码块进行赋值或者对这个静态变量多次运算
 我们都知道java有类 类中有main函数
 如果main函数前面还有个静态代码块 谁先?
 当然是静态代码块 类只要进内存 就执行了
 既然有静态代码块static{}

 那么对象肯定也有代码块 非静态代码块{}
 全名构造代码块 可以给所有对象进行初始化的

 区别:构造函数是给对应的对象进行针对性的初始化
 而构造代码块是给所有对象进行一个泛化的初始化

 函数内加了个{}叫局部代码块
 限制局部变量的生命周期
 执行先后顺序静态代码块--->构造代码块-->构造函数


 一个对象中的方法如果没有访问到私有数据的话
 使用非静态修饰纯粹浪费空间 那么就使用静态修饰一个没有访问私有数据的方法
 这样的话当我们使用要给数组工具类的时候
 就不必要建立一个对象并分配内存 直接类名.方法 就可以访问了
 然后既然一个类他就是一个工具类 里面都是静态方法
 那么我们根本不需要实例化这个类 可不可以强制抑制实例化对象
 根本不让你创建 针对构造函数 下手 对构造函数私有化
 如果不让构造不就不能初始化 private ArrayTool(){}即可
 

文档注释
能做成文档的都是共有的public的 无论是类还是方法
 我们写程序的时候有注释 使用javadoc可以对这些注释进行提取
 构造说明文档 以便他人使用
 @author zsq
 @version V1.0

 类名前加public 作用1 提高权限因为如果一个类是要做成文档的话必须是publi class 2 保证类名和文件名一致 
 
再看单例模式
保证一个类在内存中的对象 对象 对象 唯一性
如必须对于多个程序使用同一个配置信息对象时 就需要保证该对象的唯一性

如何?
1 不允许其他程序用new创建该类对象
2 在该类创建一个本类实例
3 对外提供一个方法让其他程序可以获取该对象 

答案:
1 私有化该类构造方法
2 通过new在本类中创建一个本类对象
3 定义一个共有的方法,将创建的对象返回 


//懒汉式 类加载进来 没有对象  当需要的时候才有 延迟加载
public class Single
{
	private int numtest;
	private static Single s = null;
	private Single(){};
	
	public static Single getInstance(){
		//开放接口对外方便控制 给予对象的权限
		if(s==null)
		{
			s = new Single();
		}
		return s;
	}

	public int getNumtest()
	{
		return numtest;
	}

	public void setNumtest(int numtest)
	{
		this.numtest = numtest;
	}
	
	
}


//饿汉式 类一加载 对象就存在了
public class Single
{
	private int numtest;
	private static final Single s = new Single();
	private Single(){};
	
	public static Single getInstance(){
		return s;
	}

	public int getNumtest()
	{
		return numtest;
	}

	public void setNumtest(int numtest)
	{
		this.numtest = numtest;
	}
	
	
}
