package com.qa.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> listObj = new LinkedList<String>();
		listObj.add("A");
		listObj.add("B");
		listObj.add("C");
		listObj.add("D");
		listObj.add("E");
		
		System.out.println(listObj);
		
		ListIterator<String> iObj = listObj.listIterator();
		System.out.println(iObj);
		while(iObj.hasNext()) {
			
			iObj.next();
			iObj.next();
			iObj.next();
			iObj.next();
			
			iObj.remove();
			
			System.out.println("Previous element-->" + iObj.previous());
			System.out.println("Previous element-->" + iObj.previous());
			
			iObj.remove();
			
			System.out.println("Current element-->" + iObj.next());
			continue;
			
		}
		
	}

}
