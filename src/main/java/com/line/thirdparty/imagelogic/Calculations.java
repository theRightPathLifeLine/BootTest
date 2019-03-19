package com.line.thirdparty.imagelogic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Calculations {

	public static Object calculation(){
		Runtime rn = Runtime.getRuntime();
		String line = null;
		try {
//			rn.exec("cd E:/MyFirst/release/release");
			Process p = rn.exec("main.exe --image_path E:/byt60v32 --DEBUG 1");
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = br.readLine()) != null){
	            System.out.println(line);
	        }
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	public static void main(String[] args) {
		Runtime rn = Runtime.getRuntime();
		String line = null;
		try {
			Process p = rn.exec("main.exe --image_path E:/byt60v32 --DEBUG 1");
//			Process p = rn.exec("python C:\\Program Files (x86)\\MVS\\Development\\Samples\\Python\\ConnectSpecCamera\\ConnectSpecCamera.py");
			InputStream in = p.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			String str = dis.readLine();
			p.waitFor();
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	
}
