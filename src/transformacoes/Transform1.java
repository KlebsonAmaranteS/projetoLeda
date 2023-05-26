package transformacoes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import metodos.methods;

public class Transform1 {

    Scanner entrada = null;
    int[] id = new int[350];
    String[] name = new String[350];
    int i = 0;
    String recebe;
    String column;
    String[] file = new String[1250835];

    public Transform1() throws IOException {
        File compartilhaBike = new File("LA_Metro_BikeSharing_CLEANED_2016quater3-2021q3.csv");
        try (Scanner read = new Scanner(compartilhaBike)) {
            this.column = read.nextLine();
            int n = 0;
            String line;

            while (read.hasNextLine()) {
                line = read.nextLine();
                this.file[n] = line;
                ++n;
            }
        }
    }

    public void readArquivoStations() throws IOException {
        File stations = new File("stations.csv");
        this.entrada = new Scanner(stations);
        methods.readLinha(this.entrada.nextLine(), 0, 2);
        while (this.entrada.hasNextLine()) {
            this.recebe = methods.readLinha(this.entrada.nextLine(), 0, 2);
            this.id[i] = Integer.parseInt(this.recebe.substring(0, 4));
            this.name[i] = this.recebe.substring(4);
            ++i;
        }
    }

    public void extractsMetroTrips() throws IOException {
        alteraArquivo(column, "start_station", "end_station");

        for (int j = 0; j < 1250835; j++) {
            String line = file[j];
            String recebe = methods.readLinha(line, 9, 11);
            int idInicio = Integer.parseInt(recebe.substring(0, 4));
            int idFim = Integer.parseInt(recebe.substring(4));

            String start = "";
            String end = "";

            for (i = 0; i < 350; i++) {
                if (id[i] == idInicio || id[i] == idFim) {
                    if (id[i] == idInicio && id[i] == idFim) {
                        start = name[i];
                        end = name[i];
                        break;
                    } else if (id[i] == idInicio) {
                        start = name[i];
                    } else if (id[i] == idFim) {
                        end = name[i];
                    }
                }
            }

            if (start.isEmpty() || end.isEmpty()) {
                if (start.isEmpty() && end.isEmpty()) {
                    start = String.valueOf(idInicio);
                    end = String.valueOf(idFim);
                } else if (start.isEmpty()) {
                    start = String.valueOf(idInicio);
                } else if (end.isEmpty()) {
                    end = String.valueOf(idFim);
                }
            }

            alteraArquivo(line, start, end);
        }
    }

    public void alteraArquivo(String line, String start, String end) {
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
