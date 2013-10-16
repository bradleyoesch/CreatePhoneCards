import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads the roster downloaded from Google Spreadsheets into a .tsv format and 
 * writes relevant information to a .vcf vCard format so that users with 
 * smartphones can easily access all brother and pledge numbers quickly and
 * easily, clicking only a few times instead of manually inputting all of them.
 * Email addresses were added so that when drafting emails on a phone,
 * people's emails pop up automatically after typing their initials.
 * 
 * This will only work if the .tsv files are generated from the roster used
 * currently (as of Fall 2013) on Google Drive.
 * 
 * @author Bradley Oesch
 * @version 1.0 September 2013
 */
public class CreateVCards {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	private String line;
	private String[] split;
	private String[] nameSplit;
	private Pattern p = Pattern.compile("[a-zA-Z][a-zA-Z]+"); //matches 2 or more letters
	
	/**
	 * Adds brother info to a Card object.
	 * 
	 * @param reader Reader of the Brothers.tsv file
	 * @throws IOException
	 */
	public void addBrother(BufferedReader reader) throws IOException {
		line = reader.readLine();
		line = reader.readLine(); //reads first two lines
		while ((line = reader.readLine()) != null) {
			Card card = new Card();
			split = line.split("\t");
			nameSplit = split[2].split(",");
			Matcher m = p.matcher(nameSplit[1]);
			String firstName = "";
			while (m.find()) {
				firstName = m.group();
			}
			card.setBrother(true);
			card.setLastName(nameSplit[0]);
			card.setFirstName(firstName);
			card.setPhoneNumber(split[4]);
			card.setInitials(split[1]);
			this.cards.add(card);
		}
	}
	
	/**
	 * Adds pledge info to a Card object.
	 * 
	 * @param reader Reader of the Pledges.tsv file
	 * @throws IOException
	 */
	public void addPledge(BufferedReader reader) throws IOException {
		line = reader.readLine();
		line = reader.readLine(); //reads first two lines
		while ((line = reader.readLine()) != null && line.length() > 10) { //blank lines at end
			Card card = new Card();
			split = line.split("\t");
			nameSplit = split[0].split(",");
			Matcher m = p.matcher(nameSplit[1]);
			String firstName = "";
			while (m.find()) {
				firstName = m.group();
			}
			card.setBrother(false);
			card.setFirstName(firstName);
			card.setLastName(nameSplit[0]);
			card.setPhoneNumber(split[3]);
			this.cards.add(card);
		}
	}
	
	/**
	 * Actually writes the info from a Card object to the final .vcf file.
	 * 
	 * @param writer Writer that writes to the final .vcf file
	 * @param c Card object that holds relevant information
	 * @throws IOException
	 */
	public void writeCard(BufferedWriter writer, Card c) throws IOException {
		writer.write("BEGIN:VCARD");
		writer.newLine();
		writer.newLine();
		writer.write("VERSION:3.0");
		writer.newLine();
		writer.newLine();
		writer.write("N;CHARSET=UTF-8:" + c.getLastName() + ";" + c.getFirstName() + ";;;");
		writer.newLine();
		writer.newLine();
		writer.write("FN;CHARSET=UTF-8:" + c.getFirstName() + " " + c.getLastName());
		writer.newLine();
		writer.newLine();
		writer.write("TEL;CHARSET=UTF-8;TYPE=CELL:" + c.getPhoneNumber());
		writer.newLine();
		writer.newLine();
		if (c.getBrother()) {
			writer.write("item5.EMAIL;CHARSET=UTF-8:" + c.getInitials().toLowerCase() + "@pikapp.net");
			writer.newLine();
			writer.newLine();
			writer.write("item5.X-ABLabel:email");
			writer.newLine();
			writer.newLine();
		}
		writer.write("END:VCARD");
		writer.newLine();
		writer.newLine();
	}
	
	public static void main(String[] args) throws IOException {
		CreateVCards all = new CreateVCards();
		
		try {
			BufferedReader readerBrothers = new BufferedReader(new FileReader("Info Brothers.tsv"));
			try {
				all.addBrother(readerBrothers);
			} finally {
				readerBrothers.close();
			}
			
			BufferedReader readerPledges = new BufferedReader(new FileReader("Info Pledges.tsv"));
			try {
				all.addPledge(readerPledges);
			} finally {
				readerPledges.close();
			}
		} catch (IOException e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		}
		
		try {
			BufferedWriter writerAll = new BufferedWriter(new FileWriter("Phone Card All.vcf"));
			BufferedWriter writerBrothers = new BufferedWriter(new FileWriter("Phone Card Brothers.vcf"));
			BufferedWriter writerPledges = new BufferedWriter(new FileWriter("Phone Card Pledges.vcf"));
			
			try {
				for (Card c : all.cards) {
					all.writeCard(writerAll, c);
					if (c.getBrother())
						all.writeCard(writerBrothers, c);
					else
						all.writeCard(writerPledges, c);
				}
			} finally {
				writerAll.close();
				writerBrothers.close();
				writerPledges.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
