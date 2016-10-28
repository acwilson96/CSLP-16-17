import java.util.*;
import java.io.*;

public class readfile {

	private Scanner x;
	
	public void openFile(String y) {
		
		try {
			x = new Scanner(new File(y));
		}
		catch(Exception e) {
			System.out.println("could not find file");
		}
	}
	
	public ArrayList<String> readFile() {
		ArrayList<String> a = new ArrayList<String>();
		while(x.hasNext()) {
			a.add(x.next());
		}
		return a;
	}
	
	public void closeFile() {
		x.close();
	}
	
}
