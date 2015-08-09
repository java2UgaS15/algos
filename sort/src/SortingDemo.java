/**
 * @author: Jonathan Yee
 * @description: this is to demo varies sorting algorithm. The list below is not completed
 * and the reference is from: http://en.wikipedia.org/wiki/Sorting_algorithm
 * 
 */
public class SortingDemo {

	public static void bubbleSort(int[] data)
	{
	   for (int k = 0; k < data.length - 1; k++)
	   {
	      boolean isSorted = true;

	      for (int i = 1; i < data.length - k; i++)
	      {
	         if (data[i] < data[i - 1])
	         {
	            int tempVariable = data[i];
	            data[i] = data[i - 1];
	            data[i - 1] = tempVariable;

	            isSorted = false;

	         }
	      }

	      if (isSorted)
	         break;
	   }
	}

	public static void insertionSort(int[] data){
		for(int i=0;i<data.length;i++){
			int val = data[i];
			int j = i-1;
			while(j>=0 && data[j]>val){
				data[j+1] = data[j];
				j--;
			}
			data[j+1] = val;
		}
	}
	
	
	public static int partition(int arr[], int left, int right)

	{

	      int i = left, j = right;

	      int tmp;

	      int pivot = arr[(left + right) / 2];

	     

	      while (i <= j) {

	            while (arr[i] < pivot)

	                  i++;

	            while (arr[j] > pivot)

	                  j--;

	            if (i <= j) {

	                  tmp = arr[i];

	                  arr[i] = arr[j];

	                  arr[j] = tmp;

	                  i++;

	                  j--;

	            }

	      };

	     

	      return i;

	}

	 

	public static void quickSort(int arr[], int left, int right) {

	      int index = partition(arr, left, right);

	      if (left < index - 1)

	            quickSort(arr, left, index - 1);

	      if (index < right)

	            quickSort(arr, index, right);

	}
	
	public static void main(String[] args) {
		int[] data = {5, 1, 4, 2, 8, 7, 35, 9,10, 95 };
		//bubbleSort(data);
		//insertionSort(data);
		quickSort(data, 0,data.length-1);
		for(int x: data){
			System.out.print(x);
			System.out.print('\t');
		}
	}

}

