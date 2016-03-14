package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class HighScoreUtility {

	public static class HighScoreRecord implements Comparable<HighScoreRecord> {
		private String name = "";
		private int score = 0;
		
		public HighScoreRecord(String name, int score) {
			this.name = name;
			this.score = score;
		}
		
		/* 
		 * Parse the given string "record"
		 * record format is name:score
		 */
		public HighScoreRecord(String record) throws ScoreParsingException{
				int colon = record.indexOf(":");
				if(colon == -1) throw new ScoreParsingException(1);
				
				try {
					name = record.substring(0, colon);
					score = Integer.parseInt(record.substring(colon+1));
				} catch (NumberFormatException e) {
					throw new ScoreParsingException(0);
				}
		}
		
		private String getRecord(){
			return name.trim()+":"+score;
		}
		
		private String getRecordFormated(){
			return String.format("%-20s %s%02d", name.trim(), score/60 + ":", score%60) ;
		}
		
		private static String[] defaultRecord(){
			return new String[]{"A:800","B:750","C:700",
					"D:650","E:600","F:500",
					"G:400","H:300","I:200","J:100"};
		}

		@Override
		public int compareTo(HighScoreRecord o) {
			if (o.score == this.score) return 0;
			else if(this.score > o.score) return 1;
			else return -1;
		}
	}
	
	private static HighScoreRecord[] highScoreRecord = null;

	private static String readFileName = "highscore";
	
	/*
	 * Display player's score and record if the player rank is 10 or higher.
	 */
	public static void recordHighScore(int score){
		if(!loadHighScore() || highScoreRecord == null){
			JOptionPane.showMessageDialog(null, "Error loading highscore record", "ERROR", JOptionPane.ERROR_MESSAGE);
			return ;
		}
		int index=highScoreRecord.length;
		for(int i=0; i<highScoreRecord.length; i++){
			if(score < highScoreRecord[i].score){
				index = i;
				break;
			}
		}
		if(index >= highScoreRecord.length){
			String t = "Congratulations, you got the golden egg!\n";
			t += "Your time is " + score/60 + ":" + String.format("%02d", score%60) + "\n";
			JOptionPane.showMessageDialog(null, t, "You Won", JOptionPane.DEFAULT_OPTION);
			
		}else{
			for(int i=highScoreRecord.length-1; i>=index+1; i--){
				highScoreRecord[i] = highScoreRecord[i-1];
			}
			String t = "	Your time is " + score/60 + ":" + String.format("%02d", score%60) + "\n";
			t += "Congratulations, you are ranked " + (index+1) + "! \n";
			t += "Please enter your name";

			String name = JOptionPane.showInputDialog(null,t,
					"High Score", JOptionPane.INFORMATION_MESSAGE);
			if(name == null || name.equalsIgnoreCase("")) {
				return ;
			}
			highScoreRecord[index] = new HighScoreRecord(name, score);
			
			try {
				
				BufferedWriter out = new BufferedWriter(new FileWriter("highscore"));
				String recordString = "";
				for (HighScoreRecord hsr : highScoreRecord) {
					recordString += hsr.getRecord() + "\n";
				}
				recordString = recordString.trim();
				out.write(getXORed(recordString));
				out.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Error saving high score record", 
						"Error", JOptionPane.ERROR_MESSAGE);
				highScoreRecord = null;
				return;
			}
		}
	}
	
	public static void displayTop10(){
		if(!loadHighScore() || highScoreRecord == null){
			JOptionPane.showMessageDialog(null, "Error loading highscore record", "ERROR", JOptionPane.ERROR_MESSAGE);
			return ;
		}
		String msg = "======= Top 10 players ======="+System.getProperty("line.separator");
		int rank = 1;
		for(HighScoreRecord record : highScoreRecord){
			msg += String.format("%2s", rank)+" "+record.getRecordFormated()+System.getProperty("line.separator");
			rank++;
		}
		JOptionPane.showMessageDialog(null, msg.trim(), "Top 10", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static boolean loadHighScore(){
		File f = new File(readFileName);
		
		//If no high score, create default
		
		if(!f.exists()){
			if(!createDefaultScoreFile()) return false;
		}
		//Read high score -- if fail: delete the file, create default one and read it again 
		if(!readAndParseScoreFile(f)){
			f.delete();
			if(!createDefaultScoreFile()) return false;
			return readAndParseScoreFile(f);
		}
		return true;
		
	}
	
	private static boolean readAndParseScoreFile(File f){
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			highScoreRecord = new HighScoreRecord[10];
			String str = "";
			int c;
			while((c = in.read()) != -1){
				str += (char)c;
			}
			in.close();
			String[] records = getXORed(str).split("\n");
			for(int i=0; i<highScoreRecord.length; i++){
				try{
					highScoreRecord[i] = new HighScoreRecord(records[i]);
				}catch(ScoreParsingException e){
					System.err.println("Error parsing line "+(i+1)+", "+e.getMessage());
					highScoreRecord[i] = new HighScoreRecord("ERROR_RECORD", 0);
				}
			}
			Arrays.sort(highScoreRecord);
			return true;
		} catch (Exception e) {
			highScoreRecord = null;
		}
		return false;
	}
	
	private static boolean createDefaultScoreFile(){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("highscore"));
			String str = "";
			for(String s : HighScoreRecord.defaultRecord()){
				str += s+"\n";
			}
			str = str.trim();
			out.write(getXORed(str));
			out.close();
			return true;
		} catch (IOException e1) {
			highScoreRecord = null;
			return false;
		}
	}
	
	private static final byte[] key = "RmAAq2b5d8fjgu9dhher".getBytes();
	
	//This method does both encryption and decryption 
	private static String getXORed(String in){
		byte[] inData = in.getBytes();
		for (int i=0,j=0 ; i<inData.length ; i++,j++) {
			j%=key.length;
			inData[i] ^= key[j];
		}
		return new String(inData);
	}

	public static void setReadFileName(String name){
		readFileName = name;
	}	
}
