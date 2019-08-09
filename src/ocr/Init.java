package ocr;

import java.io.*;
import java.util.*;

public class Init {

	public static int TEST_SET_SIZE = 100;
	public static int DIGIT_DATUM_WIDTH = 28;
	public static int DIGIT_DATUM_HEIGHT = 28;
	public static int FACE_DATUM_WIDTH = 60;
	public static int FACE_DATUM_HEIGHT = 70;

	

	public static ArrayList<String>digitImage = new ArrayList<String>();
	public static ArrayList<String>digitLabel = new ArrayList<String>();
	public static ArrayList<String>digitTrainingImage = new ArrayList<String>();
	public static ArrayList<String>digitTrainingLabel = new ArrayList<String>();
	public static ArrayList<String>digitValidationImage = new ArrayList<String>();
	public static ArrayList<String>digitValidationLabel = new ArrayList<String>();
	
	
	
	public static ArrayList<String>testImage = new ArrayList<String>();
	public static ArrayList<String>testLabel = new ArrayList<String>();
	public static ArrayList<String>trainingImage = new ArrayList<String>();
	public static ArrayList<String>trainingLabel = new ArrayList<String>();
	public static ArrayList<String>validationImage = new ArrayList<String>();
	public static ArrayList<String>validationLabel = new ArrayList<String>();
	
	
	
	public static Map<String, Integer> features;
	
	public static void main(String[] args) {
		
		//features = new Hashtable();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("If faces type f, if digit then d");
		String c = sc.nextLine();
		sc.close();
		boolean isDigit = false;
		if(c.equals("f")) {
			readFiles();
		}
		else if(c.equals("d")) {
			readDigits();
			isDigit = true;
		}
		else {
			System.out.println("Wrong char entered returning");
			return;
		}
		
		
		ArrayList<int[][]> training = findFeatures(isDigit, 1);
		ArrayList<int[][]> validation  = findFeatures(isDigit, 2);
		ArrayList<int[][]> test = findFeatures(isDigit, 3);
		
		System.out.println("");
		
	}
	
	public static void readFiles() {
		File file = new File("facedata/facedatatrain");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;

			while ((st = br.readLine()) != null)
				trainingImage.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File file2 = new File("data/facedata/facedatatrainlabels");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file2));

			String st;

			while ((st = br.readLine()) != null)
				trainingLabel.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		File file3 = new File("data/facedata/facedatavalidation");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file3));

			String st;

			while ((st = br.readLine()) != null)
				validationImage.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file4 = new File("data/facedata/facedatavalidationlabels");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file4));

			String st;

			while ((st = br.readLine()) != null)
				validationLabel.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		File file5 = new File("data/facedata/facedatavalidation");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file5));

			String st;

			while ((st = br.readLine()) != null)
				testImage.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file6 = new File("data/facedata/facedatavalidationlabels");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file6));

			String st;

			while ((st = br.readLine()) != null)
				testLabel.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void readDigits() {
		File file = new File("digitData/trainingimages");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;

			while ((st = br.readLine()) != null)
				digitTrainingImage.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File file2 = new File("digitData/traininglabels");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file2));

			String st;

			while ((st = br.readLine()) != null)
				digitTrainingLabel.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		File file3 = new File("digitData/validationimages");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file3));

			String st;

			while ((st = br.readLine()) != null)
				digitValidationImage.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file4 = new File("digitData/validationlabels");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file4));

			String st;

			while ((st = br.readLine()) != null)
				digitValidationLabel.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		File file5 = new File("digitData/testimages");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file5));

			String st;

			while ((st = br.readLine()) != null)
				digitImage.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file6 = new File("digitData/testlabels");
		//ArrayList<String> digit =  new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file6));

			String st;

			while ((st = br.readLine()) != null)
				digitLabel.add(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public static ArrayList<int[][]> findFeatures(boolean isDigit, int caseNo) {
		ArrayList<int[][]> map = new ArrayList<int[][]>();
		
		int i = 0;
		int count = 1;
		//boolean needsSpace = false;
		
		if(isDigit == true) {
			switch(caseNo) {
				
				case 1:
					
					
					while(count < 5000) {
						//System.out.println(count);
						String str = digitTrainingImage.get(i);
						str = str.trim();
						
						if(str.length() > 0) {
							int [][]mp = new int[DIGIT_DATUM_HEIGHT][DIGIT_DATUM_WIDTH];
	//						System.out.println(digitTrainingImage.get(i));
	//						needsSpace = true;
							int x = 0;
							for(int k = i; k< i + DIGIT_DATUM_HEIGHT; k++) {
								String temp = digitTrainingImage.get(k);
								for(int y = 0; y< DIGIT_DATUM_WIDTH; y++) {
									if(temp.charAt(y) != ' ') {
										mp[x][y] = 1;
									}
									else {
										mp[x][y] = 0;
									}
								}
								x++;
							}
							try {
							map.add(mp);
							count++;
							}
							catch(Exception e) {
								System.out.println(e.getMessage());
							}
	
							i+=DIGIT_DATUM_HEIGHT;
						}
						else {
							i++;
						}
						
					}
					break;
					
			case 2:
				
				long size = digitValidationLabel.size();
				while(count < size) {
					String str = digitValidationImage.get(i);
					str = str.trim();
					
					if(str.length() > 0) {
						int [][]mp = new int [DIGIT_DATUM_HEIGHT][DIGIT_DATUM_WIDTH];
	
						int x = 0;
						for(int k = i; k< i + DIGIT_DATUM_HEIGHT; k++) {
							String temp = digitValidationImage.get(k);
							for(int y = 0; y< DIGIT_DATUM_WIDTH; y++) {
								if(temp.charAt(y) != ' ') {
									mp[x][y] = 1;
								}
								else {
									mp[x][y] = 0;
								}
							}
							x++;
						}
						try {
						map.add(mp);
						count++;
						}
						catch(Exception e) {
							System.out.println(e.getMessage());
						}
	
						i+=DIGIT_DATUM_HEIGHT;
					}
					else {
						i++;
					}
					
				}
				break;
				case 3:
				
				long ssize = digitLabel.size();
				while(count < ssize) {
					
					String str = digitImage.get(i);
					str = str.trim();
					
					if(str.length() > 0) {
						int [][]mp = new int[DIGIT_DATUM_HEIGHT][DIGIT_DATUM_WIDTH];
	
						int x = 0;
						for(int k = i; k< i + DIGIT_DATUM_HEIGHT; k++) {
							String temp = digitImage.get(k);
							for(int y = 0; y< DIGIT_DATUM_WIDTH; y++) {
								if(temp.charAt(y) != ' ') {
									mp[x][y] = 1;
								}
								else {
									mp[x][y] = 0;
								}
							}
							x++;
						}
						try {
						map.add(mp);
						count++;
						}
						catch(Exception e) {
							System.out.println(e.getMessage());
						}
	
						i+=DIGIT_DATUM_HEIGHT;
					}
					else {
						i++;
					}
					
				}
				break;
			}
		
		}else {
			//FACE
			switch(caseNo) {
				
				case 1:
					
					long fsize = trainingLabel.size();
					while(count < fsize) {
						System.out.println(count);
						String str = trainingImage.get(i);
						str = str.trim();
						
						if(str.length() > 0) {
							int [][]mp = new int [FACE_DATUM_HEIGHT][FACE_DATUM_WIDTH];
	//						System.out.println(digitTrainingImage.get(i));
	//						needsSpace = true;
							int x = 0;
							for(int k = i; k< i + FACE_DATUM_HEIGHT; k++) {
								String temp = trainingImage.get(k);
								for(int y = 0; y< FACE_DATUM_WIDTH; y++) {
									if(temp.charAt(y) != ' ') {
										mp[x][y] = 1;
									}
									else {
										mp[x][y] = 0;
									}
								}
								x++;
							}
							try {
							map.add(mp);
							count++;
							}
							catch(Exception e) {
								System.out.println(e.getMessage());
							}
	
							i+=FACE_DATUM_HEIGHT;
						}
						else {
							i++;
						}
						
					}
					break;
					
			case 2:
				
				long size = validationLabel.size();
				while(count < size) {
					String str = validationImage.get(i);
					str = str.trim();
					
					if(str.length() > 0) {
						int [][]mp = new int [FACE_DATUM_HEIGHT][FACE_DATUM_WIDTH];
	
						int x = 0;
						for(int k = i; k< i + FACE_DATUM_HEIGHT; k++) {
							String temp = validationImage.get(k);
							for(int y = 0; y< FACE_DATUM_WIDTH; y++) {
								if(temp.charAt(y) != ' ') {
									mp[x][y] = 1;
								}
								else {
									mp[x][y] = 0;
								}
							}
							x++;
						}
						try {
						map.add(mp);
						count++;
						}
						catch(Exception e) {
							System.out.println(e.getMessage());
						}
	
						i+=FACE_DATUM_HEIGHT;
					}
					else {
						i++;
					}
					
				}
				break;
				case 3:
				
				long ssize = testLabel.size();
				while(count < ssize) {
					
					String str = testImage.get(i);
					str = str.trim();
					
					if(str.length() > 0) {
						int [][]mp = new int [FACE_DATUM_HEIGHT][FACE_DATUM_WIDTH];
	
						int x = 0;
						for(int k = i; k< i + FACE_DATUM_HEIGHT; k++) {
							String temp = testImage.get(k);
							for(int y = 0; y< FACE_DATUM_WIDTH; y++) {
								if(temp.charAt(y) != ' ') {
									mp[x][y] = 1;
								}
								else {
									mp[x][y] = 0;
								}
							}
							x++;
						}
						try {
						map.add(mp);
						count++;
						}
						catch(Exception e) {
							System.out.println(e.getMessage());
						}
	
						i+=FACE_DATUM_HEIGHT;
					}
					else {
						i++;
					}
					
				}
				break;
			}
		}
		
		return map;
	}
}
