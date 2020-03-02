package data;

import java.util.ArrayList;
import bus.*;

public class FormatConverter<T> {
	public static int[] ConvertToIdCustomer(ArrayList<Customer> myArrayList){
		int[] myList = {0};
		int i = 0;
		for(Customer element : myArrayList) {
			myList[i] = element.getId();
		}
		return myList;
	}
	public static int[] ConvertToIdChecking(ArrayList<Checking> myArrayList){
		int[] myList = {0};
		int i = 0;
		for(Checking element : myArrayList) {
			myList[i] = element.getId();
		}
		return myList;
	}
	public static int[] ConvertToIdSaving(ArrayList<Saving> myArrayList){
		int[] myList = {0};
		int i = 0;
		for(Saving element : myArrayList) {
			myList[i] = element.getId();
		}
		return myList;
	}
	public static int[] ConvertToIdCredit(ArrayList<Credit> myArrayList){
		int[] myList = {0};
		int i = 0;
		for(Credit element : myArrayList) {
			myList[i] = element.getId();
		}
		return myList;
	}
	
}
