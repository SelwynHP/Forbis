package data;

public class IdComparator {
	public static int MaxValue(int v1, int v2) {
		if(v1 > v2)
		{
			return v1;
		}
		else {
			return v2;
		}
	}
	public static int MaxValue(int[] myList) {
		int max = 0;
		for(int i = 0;i < myList.length;++i) {
			max = MaxValue(max, myList[i]);
		}
		return max;
	}
}
