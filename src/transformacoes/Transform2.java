package transformacoes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Transform2 {

    String[] station = new String[31];

    public Transform2() throws IOException {
        File pasadena = new File("pivot_stations.csv");
        try (Scanner registInicialPasadena = new Scanner(pasadena)) {
            registInicialPasadena.nextLine();
            int i = 0;
            while (registInicialPasadena.hasNext()) {
                this.station[i] = registInicialPasadena.nextLine();
                ++i;
            }
        }
    }

    public void createFilteredFile() {
        File trips = new File("LAMetroTrips.csv");
        try (Scanner metroTrips = new Scanner(trips); FileWriter writer = new FileWriter("LAMetroTrips_F1.csv", true)) {
            String line;
            line = metroTrips.nextLine();
            writer.write(line + "\n");
            while (metroTrips.hasNextLine()) {
                line = metroTrips.nextLine();
                if (shouldIncludeLine(line)) {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private boolean shouldIncludeLine(String line) {
        String[] arquivStations = obterEstacoes(line, 9, 11).split(",");
        for (int i = 0; i < this.station.length; i++) {
            if (arquivStations[0].equals(this.station[i]) || arquivStations[1].equals(this.station[i])) {
                return true;
            }
        }
        return false;
    }

    private static String obterEstacoes(String line, int n, int m) {
        StringBuilder linhaEstacoes = new StringBuilder();
        int count = 0;
        try (Scanner readLinha = new Scanner(line)) {
            readLinha.useDelimiter(",");
            while (readLinha.hasNext() && (count <= m)) {
                if ((count >= n) && (count < m)) {
                    linhaEstacoes.append(readLinha.next()).append(",");
                    ++n;
                    ++count;
                } else {
                    readLinha.next();
                    ++count;
                }
            }
        }
        return linhaEstacoes.toString();
    }
}
