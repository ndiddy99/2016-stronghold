package org.usfirst.frc.team2537.robot.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sensors {
	private List<SensorListener> listeners = new ArrayList<SensorListener>();
	private HashMap<Sensor, Double> sensorVals =  new HashMap<Sensor, Double>();
	
	public void registerListener(SensorListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Handle sensor values from Arduino
	 */
	public void handleEvents() {
		try {
			String sentence = getLastSentence();
			if (sentence != null) {
				//TODO: Store into hashmap rather than event
				@SuppressWarnings("unused")
				boolean iterate = false;
				String tag = "";
				double val = 0;

				ArrayList<String> parseArray = parse(sentence);
				if (parseArray.size() >= 2) {
					for (String s : parseArray) {
						if (iterate = false) {
							tag = s;
							iterate = true;
						} else if (iterate = true) {
							try {
								val = Integer.parseInt(s);
								e.addPair(tag, val);
								iterate = false;
							} catch (Exception serialTrouble) {
								System.out.println("Trouble reading serial");
								serialTrouble.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (SensorListener b : listeners) {
			b.receivedValue(sensorVals);
		}
	}
	
	private ArrayList<String> parse(String STP) {
		ArrayList<String> parts2 = new ArrayList<>();
		String[] parts = STP.split("!");
		
		for (String s : parts) {
			String[] subparts = s.split(":");
			
			for (String b : subparts) {
				parts2.add(b);
			}
		}
		return parts2;
		
	}

	private String getLastSentence() {
		try {
			int numBytes = SerialSubsystem.serial.getBytesReceived();
			if (numBytes >= 5) {
				byte readBytes[] = SerialSubsystem.serial.read(numBytes);
				String sentence = new String(readBytes);
				int end = sentence.lastIndexOf('\n');
				if (end >= 4) {
					sentence = sentence.substring(0, end);
					int start = sentence.lastIndexOf('<');
					if ((start >= 0) && (end > start + 1)) {
						return sentence.substring(start+1, end);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
