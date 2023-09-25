package com.driver.delivery.managment.entity;

import java.nio.charset.Charset;
import java.util.Random;

public class TestHelper {
	public static Long strictlyPositiveLong() {
		return Long.valueOf(Math.abs((long) (Math.random() * 100000)) + 1);
	}

	public static String randomString() {
		byte[] array = new byte[7]; // length is bounded by 7
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));
	}

}
