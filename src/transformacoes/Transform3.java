package transformacoes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import metodos.methods;

public class Transform3 {
	double valorMedio;
	public Transform3() throws FileNotFoundException {
		double soma = 0D;
		int contador = 0;
		File metroTrips = new File("LAMetroTrips.csv");
		Scanner metroTime = null;
		String line;
		metroTime = new Scanner(metroTrips);
		metroTime.nextLine(); 
		while(metroTime.hasNextLine()) {
			line = metroTime.nextLine();
			soma += Integer.parseInt(methods.readLinha(line, 1, 2));//searchTime(lineTable);
			++contador;
		}
		this.valorMedio = soma / contador;
	}
	public void newFile2() {
		int num;
		try (FileWriter writer = new FileWriter("LAMetroTrips_F2.csv", true)){
			File metroTrips = new File("LAMetroTrips.csv");
			Scanner metroTime = null;
			String line;
			metroTime = new Scanner(metroTrips);
			line = metroTime.nextLine(); 
			writer.write(line + "\n");		
    		while(metroTime.hasNextLine()) {
    			line = metroTime.nextLine();
				num = Integer.parseInt(methods.readLinha(line, 1, 2));//searchTime(lineTable);
				if(num > valorMedio) {
					writer.write(line + "\n");
				}
				
			} 
    		writer.close();
		}
		catch(IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}
