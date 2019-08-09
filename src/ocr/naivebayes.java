package ocr;

import java.util.ArrayList;

public class naivebayes {

	public ArrayList<int[][]> tdata;
	public ArrayList<int[][]> vdata;
	
	public ArrayList<int[][]> test;
	
	
	public ArrayList<Integer> tvalid;
	public ArrayList<Integer> vvalid;
	public ArrayList<Integer> tevalid;
	
	
	public naivebayes(ArrayList<int[][]> t1, ArrayList<Integer> tv1, ArrayList<int[][]>t2,
			 ArrayList<Integer> tv2, ArrayList<int[][]>t3,  ArrayList<Integer> tv3) {
		tdata = t1;
		vdata = t2;
		test = t3;
		
		tvalid = tv1;
		vvalid = tv2;
		tevalid = tv3;
	}
	
	public void train() {
		
	}
}
