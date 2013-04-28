import java.awt.print.Printable;
import java.util.Scanner;


public class BubbleSort {
	public static void main(String[] args) {
		System.out.println("请输入需要排序的数据组数t：");
		Scanner scanner = new Scanner(System.in);
		int t = Integer.parseInt(scanner.nextLine());
		String[] results = new String[t];
		
		for (int i = 0; i < t; i++) {
			System.out.println("请输入第" + (i+1) + "组的数据个数");
			int elementsCount = Integer.parseInt(scanner.nextLine());
			System.out.println("请输入第" + (i+1) + "组的数据，数字之间用空格隔开");
			String[] elementStrings = scanner.nextLine().split(" ");
			
			int[] elements = changeToInt(elementStrings);
			bubbleSort(elements);
			results[i] = getResultString(elements);
		}
		
		printResults(results);
	}
	
	public static int[] changeToInt(String[] strings) {
		int[] elements = new int[strings.length];
		for (int i = 0; i < strings.length; i++) {
			elements[i] = Integer.parseInt(strings[i]);
		}		
		return elements;
	}
	
	public static void bubbleSort(int[] elements) {
		int temp = 0;
		for (int i = 1; i < elements.length ; i++) {
			for (int j = 0; j < elements.length - i ; j++) {
				if (elements[j] > elements[j+1]) {
					temp = elements[j];
					elements[j] = elements[j+1];
					elements[j+1] = temp;
				}
			}			
		}		
	}
	
	public static String getResultString(int[] elements) {
		String result = "";
		for (int i = 0; i < elements.length; i++) {
			result += elements[i] + " "; 
		}
		return result;
	}
	
	public static void printResults(String[] results) {
		for (int i = 0; i < results.length; i++) {
			System.out.println(results[i]);
		}
	}
}
