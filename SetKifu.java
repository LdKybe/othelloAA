import java.io.*;
import java.util.*;

public class SetKifu {
    BufferedReader br;
    ArrayList<int[]> kifu = new ArrayList<int[]>();
    int kifuNum = 0;
    int playKifuNum = 0;

    
    public void setKifuFile() {
	setKifuFile("sen/senkou.txt");
    }
    public void setKifuFile(String filename) {
	try {
	    br = new BufferedReader(new FileReader(filename));
	} catch (Exception e) {
	    System.out.println("読み取り失敗");
	    e.printStackTrace();
	}	
    }

    public void setKifuData() {
	try {
	    //setKifuFile();
	    if (br == null)throw new Exception("null");
	    String[] str;
	    String brStr = br.readLine();
	    int count = 0;
	    while (brStr != null) {
		//System.out.println(count);
		int[] k = new int[60];
		str = brStr.split(" ", -1);
		for (int i = 0; !str[i].equals("") ; i++) {
		    k[i] = Integer.parseInt(str[i]);
		}
	    
		kifu.add(k);
		brStr = br.readLine();
		count++;
	    }

	    kifuNum = count;
	    playKifuNum = 0;
	} catch (Exception e) {
	    System.out.println("error");
	    e.printStackTrace();
	}
    }
}
