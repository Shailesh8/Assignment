
import java.util.Scanner;
import java.util.StringTokenizer;

// create custom exception for array of length =0 
class NullArrayException extends Exception {
	public NullArrayException(String message) {
		super(message);
	}
}

class ResultObject {
	int lowerIndex;
	int maxLengthSubArray;
	int maxSum;
	int[] arr;

	ResultObject(int lowerIndex, int maxLengthSubArray, int maxSum, int[] arr) {
		this.lowerIndex = lowerIndex;
		this.maxLengthSubArray = maxLengthSubArray;
		this.maxSum = maxSum;
		this.arr = arr;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!ResultObject.class.isAssignableFrom(obj.getClass()))
			return false;
		final ResultObject other = (ResultObject) obj;
		if (this.lowerIndex != other.lowerIndex)
			return false;
		if (this.maxLengthSubArray != other.maxLengthSubArray)
			return false;
		if (this.maxSum != other.maxSum)
			return false;
		if (this.arr == null) {
			if (other.arr != null)
				return false;
		}
		for (int i = 0; i < other.arr.length; i++) {
			if (this.arr[i] != other.arr[i])
				return false;
		}
		return true;
	}
}

public class LongestSubarray {

	// find length of longest subarray with maximum sum
	public static ResultObject lengthLongestSubArrayWithMaxSum(int arr[]) throws NullArrayException {
		int arrSize = arr.length;
		if(arrSize == 0){
			throw new NullArrayException("Given array is empty");
		}
		
		int lower_limit = 0;
		int max_sum = 0;
		int sum = 0;
		int maxLengthSubArray = 0;
		int max_lower_index = 0;

		for (int i = 0; i < arrSize; i++) {
			sum = sum + arr[i];
			if (sum < 0) {
				// if sum is less than 0 then assign sum is equal to 0 and
				// assign lower_limit is equal to present index + 1
				lower_limit = i + 1;
				sum = 0;
			} else {
				if (max_sum <= sum) {
					if (max_sum == sum) {
						// if sum is equal to max_sum then check that length of
						// subarray of current sum is greater than
						// maxLengthSubarray or not if it is then reassign
						// maxLengthSubArray with cuurent length and
						// max_lower_index with lower_limit
						int length = i + 1 - lower_limit;
						if (length > maxLengthSubArray) {
							max_lower_index = lower_limit;
							maxLengthSubArray = length;
						}
					} else {
						// if max_sum is less than sum then reassign
						// max_lower_index with lower_limit ,maxLengthSubArray
						// and max_sum with current sum
						max_lower_index = lower_limit;
						maxLengthSubArray = i + 1 - max_lower_index;
						max_sum = sum;
					}
				}
			}
		}

		System.out.println("Start Index:" + max_lower_index);
		System.out.println("Length: " + maxLengthSubArray);
		System.out.println("Sum: " + max_sum);
		System.out.print("Elements: ");
		int[] brr = new int[maxLengthSubArray];
		int count = 0;
		for (int i = max_lower_index; i < max_lower_index + maxLengthSubArray; i++) {
			System.out.print(arr[i] + " ");
			brr[count++] = arr[i];
		}

		ResultObject resultObject = new ResultObject(max_lower_index, maxLengthSubArray, max_sum, brr);
		return resultObject;

	}

	public static String getString() {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		return str;
	}

	public static void main(String[] args) throws NullArrayException {

		String str = getString();
		StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
//		if (stringTokenizer.countTokens() == 0) {
//			throw new NullArrayException(" Given array is empty");
//		}
		int sizeOfArray = stringTokenizer.countTokens();
		int[] arr = new int[sizeOfArray];
		int i = 0;
		while (stringTokenizer.hasMoreTokens()) {
			arr[i++] = Integer.parseInt(stringTokenizer.nextToken());
		}
		lengthLongestSubArrayWithMaxSum(arr);
	}

}
