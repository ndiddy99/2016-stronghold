package org.usfirst.frc.team2537.robot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public final class SaberMessage {
	public static void printMessage() {
		 BufferedReader in = null;
		 String s = "";
		 try {
		 in = new BufferedReader(new FileReader("SaberPic"));
		 } catch (FileNotFoundException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		 Scanner fileScan = new Scanner(in);
		 try {
		 while(true) {
		 s += fileScan.nextLine() + "\n";
		 }
		 } catch(Exception e) {
		
		 }
		 System.out.println(s + "\nWelcome to Team 2537's Robot!");
		 fileScan.close();
	}
}
