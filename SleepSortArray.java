import java.util.Arrays;
import java.util.Random;

public class SleepSortArray {

	public static void main(String[] args) {
		int[]a=new int[100];
		Random rnd=new Random();
		for(int i=0;i<a.length;i++){
			a[i]=rnd.nextInt(200)-100;
		}
		
		System.out.println(Arrays.toString(a));
		
		SleepSorter ss=new SleepSorter(10);
		ss.sort(a);
		
		System.out.println(Arrays.toString(a));
		System.out.println(isSorted(a));
	}
	
	static boolean isSorted(int[]a){
		boolean b=true;
		for(int i=1;i<a.length;i++){
			if(a[i-1]>a[i]){
				b=false;
				break;
			}
		}
		return b;
	}
}