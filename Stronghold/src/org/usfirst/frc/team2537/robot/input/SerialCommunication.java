package org.usfirst.frc.team2537.robot.input;

import java.util.ArrayList;
import java.util.List;

public class SerialCommunication {
	private final static SerialCommunication INSTANCE = new SerialCommunication();
	private List<Sensor> listeners = new ArrayList<Sensor>();

	private SerialCommunication() {

	}

	static SerialCommunication getInstance() {
		return INSTANCE;
	}

	void handleEvents() {
		try {
			String sentence = getLastSentence();
			if (sentence != null) {
				SensorCommunicationEvent e = new SensorCommunicationEvent();
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
					for (Sensor b : listeners) {
						b.receiveValue(e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void register(Sensor listener){
		listeners.add(listener);
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
