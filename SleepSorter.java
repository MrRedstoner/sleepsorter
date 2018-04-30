public class SleepSorter {
	private int sortingTime;
	private boolean sortingInProgress=false;
	
	public SleepSorter(int time){
		if(time<=0)throw new IllegalArgumentException("time has to be >0");
		sortingTime=time;
	}
	
	public void setTime(int time){
		if(time<=0)throw new IllegalArgumentException("time has to be >0");
		if(!sortingInProgress)sortingTime=time;
	}
	
	private int[]b;
	int j;
	public void sort(int[]a){
		if(sortingInProgress)throw new RuntimeException("one SleepSorter can only sort one array at a time");
		b=a;
		j=0;
		sortingInProgress=true;
		
		int min=min(a);
		
		Thread[]c=new Thread[a.length];
		for(int i=0;i<a.length;i++){
			int pom=a[i];
			c[i]=new Thread(){
				int val=pom;
				int minCor=min;
				@Override
				public void run(){
					try {
						Thread.sleep((val-minCor)*sortingTime);
						sorted(val);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}
		for(Thread t:c)t.start();
		
		try {
			Thread.sleep((max(a)-min+1)*sortingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(sortingInProgress){
			try {
				Thread.sleep(sortingTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void sorted(int n){
		b[j++]=n;
		if(j==b.length)sortingInProgress=false;
	}
	
	private static int min(int[]a){
		int min=a[0];
		
		for(int i=1;i<a.length;i++){
			if(min>a[i]){
				min=a[i];
			}
		}
		
		return min;
	}
	
	private static int max(int[]a){
		int max=a[0];
		
		for(int i=1;i<a.length;i++){
			if(max<a[i]){
				max=a[i];
			}
		}
		
		return max;
	}
}