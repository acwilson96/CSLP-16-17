import java.util.*;
import java.io.*;

public class readfile {

	public ArrayList<String> readFile(String y) {

		ArrayList<String> file = new ArrayList<String>();

		try {
			FileInputStream fstream = new FileInputStream(y);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String currLine;
			while ((currLine = br.readLine()) != null) {
				if (currLine.charAt(0) != '#') {
					file.add(currLine);
				}
			}
			in.close();
		}
		catch (Exception e) {
			//If error
			System.err.println("Error: readfile.java " + e.getMessage());
		}
		return file;
	}
	

	public ArrayList<String> readFile2(String y) {

		ArrayList<String> file = new ArrayList<String>();

		try {

			BufferedReader br = new BufferedReader(new FileReader(y));
			String line = br.readLine();
			while (line != null) {
				if (!line.isEmpty()) {
					if (line.charAt(0) != '#') {
						file.add(line);
					}
				}
				line = br.readLine();
			}
			br.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("readfile error");
		}
		return file;
	}
	
}
