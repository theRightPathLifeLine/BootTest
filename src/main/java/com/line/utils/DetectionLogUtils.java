package com.line.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.line.entity.Device;

public class DetectionLogUtils {
	
	public static void delFile(){
		File file = new File("resource/log/detection.log");
		if(file.exists()){
			file.delete();
		}
	}
	
	public static List<Device> getFileData(){
		List<Device> devices = new ArrayList<Device>();
		File file = new File("resource/log/detection.log");
		String line = null;
		if(file.exists()){
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				int i = 0;
				while ((line = br.readLine()) != null){
		            System.out.println(line);
		            String[] s1 = line.split(" ");
		            if(s1.length == 7){
		            	String[] s2 = br.readLine().split(" "); 
		            	Device device = new Device();
		            	device.setDeviceName(s1[1]);
		            	device.setTime(s1[6]);
		            	device.setResult(s2[4]);
		            	if(device.getResult().equals("NG")){
		            		devices.add(device);
		            	}
		            }
		            
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(devices);
		return devices;
	}
	
	public static void main(String[] args) {
		getFileData();
	}

}
