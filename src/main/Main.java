package main;

import java.io.IOException;
import java.util.Scanner;
import ordenacoes.Ordenacao1;
import ordenacoes.Ordenacao2;
import ordenacoes.Ordenacao3;
import transformacoes.Transform1;
import transformacoes.Transform2;
import transformacoes.Transform3;

public class Main {
	public static void main(String args[]) {
		try {
			Scanner entrada = new Scanner(System.in);

			System.out.print("Informe o numero 1 para começar as transformações ou 0 para sair:\n ");
			int numero = entrada.nextInt();
			if (numero == 1) {
			try (Scanner usuario = new Scanner(System.in)) {
				System.out.println(
						"------------------Menu-------------\nAs transformações estão sendo executadas!\nAguarde alguns minutos\nGerando ....");
				Transform1 transformacao1 = new Transform1();
				transformacao1.readArquivStations();
				transformacao1.extractsMetroTrips();
				Transform2 transformacao2 = new Transform2();
				transformacao2.newFile();
				Transform3 transformacao3 = new Transform3();
				transformacao3.newFile2();

				System.out.println("Finalizado com sucesso, pode conferir na pasta do projeto\nO pr...\n");

				int init = 0;
				int tamh = 0;
				do {
					System.out.println("Para ordenar tem três formas: 1°, 2°, 3° ,digite o número correspondente:(1 ou 2 ou 3) e (4) para sair");
					System.out.println("\n1 - Ordenar o arquivo completo pelo nomes das estações\n2 - Ordenar o arquivo pelo campo de duração da viagem \n3 - Ordenar o arquivo pela data de início da viagem \n4 - exit");
					init = usuario.nextInt();
					System.out.println("Deseja ordenar em quantas linhas? (valor maximo = 1250836): ");
					tamh = usuario.nextInt();

				
						if(init==1){
						
							Ordenacao1 ord1 = new Ordenacao1();
							ord1.casosMedios();
							ord1.arquivsInsertion(tamh);
							ord1.arquivsSelection(tamh);
							ord1.arquivsMerge();
							ord1.arquivsQuick();
							ord1.arquivsHeapSort(tamh);
						}
						else if (init==2){
						
							Ordenacao2 ord2 = new Ordenacao2();
							ord2.casosMedios();
							ord2.arquivsCounting();
							ord2.arquivsMerge();
							ord2.arquivsInsertion(tamh);
							ord2.arquivsSelection(tamh);
							ord2.arquivsHeapSort();
							ord2.arquivsQuickSort3(tamh);
							ord2.arquivsQuickSrt(tamh);
						}
						
						else if (init== 3){
							Ordenacao3 ord3 = new Ordenacao3();
							ord3.casosMedios();
							ord3.arquivsHeapSort(tamh);
							ord3.arquivsInsertion(tamh);
							ord3.arquivsSelection(tamh);
							ord3.arquivsQuickSrt(tamh);
							ord3.arquivsQuickSort3(tamh);
							ord3.arquivsMergeSort(tamh - 1);
						}
						
						else if (init==4){
							System.out.println("Sua ordenação foi finalizada !");
						}
						else{
							System.out.println("Opção Inválida");
						}
					
				} while (init < 4 && init > 0);
			}}
		} catch (IOException e) {
			System.out.println("Fatal ERROR, reinicie o processo!\n");
			System.out.println(e);
		}
	}
}