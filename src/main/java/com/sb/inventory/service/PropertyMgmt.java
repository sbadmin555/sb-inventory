package com.sb.inventory.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyMgmt {

	private static Properties prop = new Properties();

	public static void loadProperties() {
		InputStream input = null;
		try {
			input = new FileInputStream("./config.properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
