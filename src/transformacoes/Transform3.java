package transformacoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import metodos.methods;

public class Transform3 {
    private double valorMedio;

    public Transform3() throws FileNotFoundException {
        double soma = 0D;
        int contador = 0;
        File metroTrips = new File("LAMetroTrips.csv");
        try (Scanner metroScanner = new Scanner(metroTrips)) {
            metroScanner.nextLine();
            while (metroScanner.hasNextLine()) {
                String line = metroScanner.nextLine();
                soma += Integer.parseInt(methods.readLinha(line, 1, 2));
                contador++;
            }
        }
        valorMedio = soma / contador;
    }

    public void createFilteredFile2() {
        try (FileWriter writer = new FileWriter("LAMetroTrips_F2.csv", true)) {
            File metroTrips = new File("LAMetroTrips.csv");
            try (Scanner metroScanner = new Scanner(metroTrips)) {
                String line = metroScanner.nextLine();
                writer.write(line + "\n");
                while (metroScanner.hasNextLine()) {
                    line = metroScanner.nextLine();
                    int num = Integer.parseInt(methods.readLinha(line, 1, 2));
                    if (num > valorMedio) {
                        writer.write(line + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
