import java.util.LinkedList;

//suppose we have 10 nickels and 2 dimes,
//find out how many possible they can combine.
//Sample output: [0, 4, 3, 7, 6, 10, 2, 6, 5, 9, 8, 12]
public class CoinValues {

	public static void main(String[] args) {
		int[] faceValues = new int[]{2, 3, 4}; //new int[]{5, 10};
		int[] numCoins = new int[]{1, 2, 1}; //new int[]{10, 2};
		LinkedList<Integer> lst = totalValue(faceValues, numCoins, 0, faceValues.length-1);
		System.out.println(lst);
	}

	static LinkedList<Integer> totalValue(int[] faceValues, int[] numCoins, int startIdx, int endIdx) {
		LinkedList<Integer> lst = new LinkedList<Integer>();
		LinkedList<Integer> tempLst;
		if (endIdx == startIdx)
			for (int i = 0; i <= numCoins[startIdx]; i++)
				lst.add(i * faceValues[startIdx]);
		else {
			//tempLst to store the result of all the possible combination values of faceValues and numCoins
			//except the first one.
			tempLst = totalValue(faceValues, numCoins, startIdx+1, endIdx);
			//System.out.println("tempLst = " + tempLst);
			for (int i = 0; i <= numCoins[startIdx]; i++) {
				for (int elm: tempLst)
					lst.add(elm + i * faceValues[startIdx]);
			}
			//System.out.println("lst = " + lst);
		}
		return lst;
	}
}
