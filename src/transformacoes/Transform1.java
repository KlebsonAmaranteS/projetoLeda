package transformacoes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import metodos.methods;

public class Transform1 {

	Scanner entrada = null;
	int id[] = new int[350];
	String name[] = new String[350];
	int i = 0;
	String recebe;
	String column;
	String[] file = new String[1250835];
	public Transform1() throws IOException {
		File compartilhaBike = new File("LA_Metro_BikeSharing_CLEANED_2016quater3-2021q3.csv");
		try (Scanner read = new Scanner(compartilhaBike)) {
			this.column = read.nextLine().toString();
			int n = 0;
			String line;

			while (read.hasNextLine()) {
				line = read.nextLine().toString();
				this.file[n] = line;
				++n;
			}
		}
	}

	public void readArquivStations() throws IOException {
		
		File stations = new File("stations.csv");
		this.entrada = new Scanner(stations);
		methods.readLinha(this.entrada.nextLine(), 0, 2);
		while (this.entrada.hasNextLine()) {
			;
			this.recebe = methods.readLinha(this.entrada.nextLine(), 0, 2);
			this.id[i] = Integer.parseInt(this.recebe.substring(0, 4));
			this.name[i] = this.recebe.substring(4);
			++i;
		}
	}

	public void extractsMetroTrips() throws IOException {
		String recebe;
		String line;
		int idinicio, idfim;
		int j = 0;
		alteraArquiv(column, "start_station", "end_station");

		while (j < 1250835) {
			line = file[j];
			recebe = methods.readLinha(line, 9, 11);
			idinicio = Integer.parseInt(recebe.substring(0, 4));
			idfim = Integer.parseInt(recebe.substring(4));
			++j;
			String start = "", end = "";
			for (i = 0; i < 350; i++) {
				if ((this.id[i] == idinicio) || (this.id[i] == idfim)) {
					if ((this.id[i] == idinicio) && (this.id[i] == idfim)) {
						start = this.name[i];
						end = this.name[i];
						i = 351;
					} else if (this.id[i] == idinicio) {
						start = this.name[i];
					} else if (this.id[i] == idfim) {
						end = this.name[i];
					}
				}
				else if (i == 349) {
					if (start.equals("") || end.equals("")) {
						if (start.equals("") && end.equals("")) {
							start = "" + idinicio;
							end = "" + idfim;
						} else if (start.equals(""))
							start = "" + idinicio;
						else if (end.equals(""))
							end = "" + idfim;
					}
				}
			} 
			alteraArquiv(line, start, end);
		} 
	}

	public void alteraArquiv(String line, String start, String end) { // id, (id, id)

		int n = 0;
		try (FileWriter writer = new FileWriter("LAMetroTrips.csv", true)) {
			StringBuilder adiciona = new StringBuilder();

			Scanner readLinha = new Scanner(line);
			readLinha.useDelimiter(",");
			while (readLinha.hasNext() && (n < 16)) {
				if ((n == 9) || (n == 10)) {
					if (n == 9) {
						adiciona.append(start);
						readLinha.next();
						adiciona.append(",");
					} else {
						adiciona.append(end);
						readLinha.next();
						adiciona.append(",");
					}
				} else {
					adiciona.append(readLinha.next());
					if (n != 15) {
						adiciona.append(",");
					}
				}
				++n;
			}
			adiciona.append("\n");
			writer.write(adiciona.toString());
			writer.close();
			readLinha.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	} 

}