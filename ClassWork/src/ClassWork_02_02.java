import java.util.LinkedList;
import javax.swing.JOptionPane;


public class ClassWork_02_02 {

	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("请依次输入n，k，m三个参数，参数之间用空格隔开：");
		String[] strs = str.split(" ");
		int n = Integer.parseInt(strs[0]);
		int k = Integer.parseInt(strs[1]);
		int m = Integer.parseInt(strs[2]);
		int count = n;
		int flag1 = -1;
		int flag2 = count;
		LinkedList<Integer> employee = new LinkedList<Integer>();
		
		for (int i = 0; i < n; i++) {
			Integer integer = new Integer(i+1);
			employee.add(integer);
		}
		
		while (count != 0) {			
			for (int i = 0; i < k; i++) {
				if (flag1 < count-1) {
					flag1++;
				} else {
					flag1 = 0;
				}
			}			
			for (int i = 0; i < m; i++) {
				if (flag2 > 0) {
					flag2--;
				} else {
					flag2 = count-1;
				}
			}			
			if (flag1 != flag2 ) {
				System.out.println(employee.get(flag1).intValue() + " " + employee.get(flag2).intValue());			
				if (flag1 > flag2) {
					employee.remove(flag1);
					employee.remove(flag2);
					flag1 = flag1 - 2;
				} else {
					employee.remove(flag2);
					employee.remove(flag1);
					flag2--;
					flag1--;
				}
				count = count-2;
			} else {
				System.out.println(employee.get(flag1).intValue());
				employee.remove(flag1);
				flag1--;
				count--;				
			}			
		}		
	}
}
