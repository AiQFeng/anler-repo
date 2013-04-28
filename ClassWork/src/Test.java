
public class Test {
	public static void main(String[] args) {
		String a = new String("aa");
		String b = "aa";
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(a == b);   		//== 是比较对象的引用 如果是new一个String 对象地址不同 结果为false 
											//	如果两个string都是直接赋值并且赋值相同 则两者被放在同一块区域 结果为true
		System.out.println(a.equals(b)); 	//equals()默认比较对象的引用，string内重写了equals()方法，比较的是string的内容
											
		Integer ai = new Integer(1);		//integer与string相同
		Integer bi = 1;
		System.out.println(ai == bi);
		System.out.println(ai.equals(bi));
		
		Pepole aPepole = new Pepole();
		aPepole.name = "a";
		Pepole bPepole = new Pepole();
		bPepole.name = "a";
		System.out.println(aPepole);
		System.out.println(bPepole);
		System.out.println(aPepole.equals(bPepole));
	}
}

class Pepole {
	String name;
	public boolean equals(Object p) {		
		return p instanceof Pepole && (name == ((Pepole)p).name);
	}
}
