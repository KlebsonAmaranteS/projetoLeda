package metodos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class methods {

	public static String readLinha(String line, int n, int m){ 
    	
    	String linhaStations = "";
    	int count = 0;
        try (Scanner rowScanner = new Scanner(line)) { 
			rowScanner.useDelimiter(",");
			//Generate the loop
            while (rowScanner.hasNext() && (count <= m)) {
            	if((count >= n) && (count < m)) {
            		linhaStations += rowScanner.next().toString();
                    ++n;
                    ++count;
            	}
            	else {
            		rowScanner.next();
            		++count;
            	}
            } 
        }
        return linhaStations;
    }
	
	//rename the csv
	public static void alteraNomeArquiv(String name) {
		//change the name archife for new name
		File oldName = new File("file.csv");
        File newName =  new File("" + name + ".csv");
        oldName.renameTo(newName);
	}
	//Generating the csv file
	public static void geraArquivCsv(String array[], String coluna) {
		String line;
		int i = 0;
		try (FileWriter writer = new FileWriter("file.csv", true)){
			writer.write(coluna + "\n");
			
    		for(i = 1; i < array.length; i++) {
    			line = array[i];
    			writer.write(line + "\n");
    		}
    		
    		writer.close();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	//for invert using the for all the spaces of memory
	//in the array
	public static void inverteArr(String array[], int n) {
		
		int j = n - 1;
		String lineTable;
		
		for(int i = 0; i < 625417 && j >= 0; i++) {
			lineTable = array[i];
			array[i] = array[j];
			array[j] = lineTable;
			--j;
		}
	}
}
