package com.hieubq.testlogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.IntArraySerializer;

public class testLogic {
	public static void main(String[] args) {
		// Khởi tạo mảng có 200 phần tử
		int[] arry200 = new int[200];

		// Khởi tạo mảng có 21 phần tử để đếm số lần xuất hiện
		int[] arrayCount = new int[20];
	
		int sum = 0;

		for (int i = 0; i < arry200.length; i++) {
			int random = (int) (Math.random() * 20 + 1);
			System.out.println("random = " + random);
			arrayCount[random - 1] += 1;
		}

		for (int i = 0; i < arrayCount.length; i++) {
			System.out.println("Số lần lặp lại của [" + (i + 1) + "] là:" + arrayCount[i]);
			sum += arrayCount[i];
		}

	}

}
