package transformacoes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Transform2 {
	
	String station[] = new String[31];
	public Transform2() throws IOException{
	//Cria o arquivo com apenas os nomes das estações 
		File pasadena = new File("pivot_stations.csv");
		Scanner registInicialPasadena = null;
		registInicialPasadena = new Scanner(pasadena);
		registInicialPasadena.nextLine(); 
		int i = 0;			
		while(registInicialPasadena.hasNext()) {
			this.station[i] = registInicialPasadena.nextLine().toString(); 
			++i;
		}				
	}
	public void newFile() {
    	File trips = new File("LAMetroTrips.csv");
		Scanner metroTrips = null;
		String line, recebe;
		try (FileWriter writer = new FileWriter("LAMetroTrips_F1.csv", true)){
			metroTrips = new Scanner(trips); 
    		line = metroTrips.nextLine();
    		writer.write(line + "\n");
    		String[] arquivStations;
    		while(metroTrips.hasNextLine()) {
				line = metroTrips.nextLine();
				recebe = obterEstacoes(line, 9, 11);
				arquivStations = recebe.split(","); 
				
				for(int i = 0; i < this.station.length; i++) {
					if((arquivStations[0].equals(this.station[i])) || (arquivStations[1].equals(this.station[i]))) {
						writer.write(line + "\n");
						break;
					}
				} 
			}
    		writer.close();
		}
		catch(IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
    } 
    private static String obterEstacoes(String line, int n, int m) {	
    	String linhaEstacoes = "";
    	int count = 0;
        try (Scanner readLinha = new Scanner(line)) {
            readLinha.useDelimiter(",");
            while (readLinha.hasNext() && (count <= m)) {
            	if((count >= n) && (count < m)) {
            		linhaEstacoes += readLinha.next().toString();
            		linhaEstacoes += ",";
                    ++n;
                    ++count;
            	}
            	else {
            		readLinha.next();
            		++count;
            	}
            } 
        }        
        return linhaEstacoes;
    }
}