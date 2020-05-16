import java.util.Scanner;

class MaximumSumCircularSubarray{
    public int kadane(int[] a){
        int max_so_far=0;
        int max_ending_here=0;
        for(int i=0;i<a.length;++i){
            max_ending_here+=a[i];
            if(max_ending_here<0)
                max_ending_here=0;
            if(max_ending_here>max_so_far)
                max_so_far=max_ending_here;
        }
        return max_so_far;
    }
    public int wrap(int[] a){
        int max_wrap=0;
        for(int i=0;i<a.length;++i){
            max_wrap+=a[i];     //array_sum
            a[i]=-a[i];         //inverting all elements
        }
        //max_wrap = array_sum - (-max of inverted array)
        //max_wrap= array_sum +kadane(a)
        
        max_wrap=max_wrap+kadane(a);
        return max_wrap;
    }
    public int maxSubarraySumCircular(int[] a) {
        //Exception - all elements are negative
        int flag=0;
        for(int i:a){
            if(i>=0){
                flag=1;
                break;
            }       
        }
        if(flag==0){
            Arrays.sort(a);
            return(a[a.length-1]);
        }
        //Case1: contributing elements have non-wrapping - Kadane's algorithm - Maximum continuous positive sum
        
        int case1=kadane(a);
        
        //Case2: contributing elements have wrapping - convert to non-wrapping
        //Means non-contributing elements have no wrapping, we find them and subtract from total sum  -> 
        //Find max of inverted array using kadane's since min of non-contributing array is max of inverted non-contrbuting array.
        
        int case2=wrap(a);
        
        //Finding max of the two cases
        return case1>=case2?case1:case2;
    }
    public static void main(String args[]){
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      int[] a=new int[n];
      for(int i=0;i<a.length;++i)
        a[i]=sc.nextInt();
        
      System.out.println(maxSubarraySumCircular(a));
   }
}
