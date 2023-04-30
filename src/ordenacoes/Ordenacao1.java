package ordenacoes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import metodos.methods;
public class Ordenacao1 {
	String column;
	String arr[] = new String[1250835];
	public Ordenacao1() throws FileNotFoundException {
		//For ordinate start in bikes
		File compartilhaBike = new File("LAMetroTrips.csv");
		Scanner entradaBike = null;
		entradaBike = new Scanner(compartilhaBike); 
		this.column = entradaBike.nextLine();
		String line;
		int j = 0;
		while(entradaBike.hasNextLine()) {
			line = entradaBike.nextLine().toString();
		    arr[j] = line;
		    j++;
		}
		
	} 
	public void casosMedios() {
		
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_insertionSort_medioCaso.csv");
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_selectionSort_medioCaso.csv");
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_mergeSort_medioCaso.csv");
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_quickSort_medioCaso.csv");
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_heapSort_medioCaso.csv");
	}
	
	private void insertionSort(int n) {
		int i,j;  
        String var;
        for (j = 1; j < n; j++) {       
            var = this.arr[j]; 
            i = j - 1;

            while (i >= 0) {
                if (methods.readLinha(var, 9, 10).compareTo(methods.readLinha(arr[i], 9, 10)) > 0) {
                    break;
                }
                this.arr[i + 1] = this.arr[i];
                i--;
            }
            this.arr[i + 1] = var;
        }
	}
	
	public void arquivsInsertion(int n) {
		
		//Melhor caso
		insertionSort(n); 
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_insertionSort_melhorCaso.csv");
		
		//Pior caso
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_insertionSort_piorCaso.csv");
	} 
	
	private void selectionSort(int n){
		
		int idx;
		String minimo;
		for(int i = 0; i < n - 1; i++){
	        idx = i;
	        minimo = this.arr[i];
	        for(int j = i + 1; j < n; j++){
	            if(methods.readLinha(this.arr[j], 9, 10).compareTo(methods.readLinha(minimo, 9, 10)) < 0){
	                minimo = this.arr[j];
	                idx = j;
	            }
	        }
	        if(idx != i){
	            String temp = this.arr[idx];
	            this.arr[idx] = this.arr[i];
	            this.arr[i] = temp;
	        }
	    }
    } 
	
	public void arquivsSelection(int n) {
		
		//Melhor caso
		selectionSort(n); 
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_selectionSort_melhorCaso.csv");
		
		//Pior caso
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_selectionSort_piorCaso.csv");
		
	} 
	
	private void mergeSort(String[] nomes) {
        if (nomes.length >= 2) {
            String[] esquerd = new String[nomes.length / 2];
            String[] direit = new String[nomes.length - nomes.length / 2];

            for (int i = 0; i < esquerd.length; i++) {
                esquerd[i] = nomes[i];
            }

            for (int i = 0; i < direit.length; i++) {
                direit[i] = nomes[i + nomes.length / 2];
            }

            mergeSort(esquerd);
            mergeSort(direit);
            merge(nomes, esquerd, direit);
        }
    }

    private void merge(String[] nomes, String[] esquerd, String[] direit) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < nomes.length; i++) {
            if (y >= direit.length || (x < esquerd.length && methods.readLinha(esquerd[x], 9, 10).compareToIgnoreCase(methods.readLinha(direit[y], 9, 10)) < 0)) {
                nomes[i] = esquerd[x];
                x++;
            } else {
                nomes[i] = direit[y];
                y++;
            }
        }
    } 
    
    public void arquivsMerge() {
		
    	//Melhor caso
		mergeSort(this.arr); 
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_mergeSort_melhorCaso.csv");
		
		//Pior caso
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_mergeSort_piorCaso.csv");
		
	} 
    
    private void quickSort(String array[], int init, int fim) {
	    int i = init;
	    int k = fim;
	    if (fim - init >= 1) {
	        String pivot = array[init];
	        while (k > i) {
	            while (methods.readLinha(array[i], 9, 10).compareTo(methods.readLinha(pivot, 9, 10)) <= 0 && i <= fim && k > i)
	                i++;
	            while (methods.readLinha(array[k], 9, 10).compareTo(methods.readLinha(pivot, 9, 10)) > 0 && k >= init && k >= i)
	                k--;
	            if (k > i)
	                troca(array, i, k);
	        }
	        troca(array, init, k);
	        quickSort(array, init, k - 1);
	        quickSort(array, k + 1, fim);
	    }
	} 
    
	private void troca(String array[], int idx1, int idx2) {
	    String temp = array[idx1];
	    array[idx1] = array[idx2];
	    array[idx2] = temp;
	}
	
	public void arquivsQuick() {
		
		//Melhor caso
		quickSort(this.arr, 0, this.arr.length - 1); 
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_quickSort_melhorCaso.csv");
		
		//Pior caso
		
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_quickSort_piorCaso.csv");
		
	}
	
	private void heapSort( int i) {
        for (int j = i / 2; j >= 0; j--)
            busca(this.arr, i, j);
        for (int k = i - 1; k > 0; k--) {
            String s = this.arr[0];
            this.arr[0] = this.arr[k];
            this.arr[k] = s;
            busca(this.arr, k, 0);
        }
    }
	
    private static void busca(String as[], int i, int j) {
        int k = 2 * j + 1;
        if (k < i) {
            if (k + 1 < i && methods.readLinha(as[k], 9, 10).compareTo(methods.readLinha(as[k+1], 9, 10)) < 0)
                k = 2 * j + 2;
            if (methods.readLinha(as[j], 9, 10).compareTo(methods.readLinha(as[k], 9, 10)) < 0) {
                String s = as[j];
                as[j] = as[k];
                as[k] = s;
                busca(as, i, k);
            }
        }
    } 
    
    public void arquivsHeapSort(int n) {
		
    	//Melhor caso
		heapSort(n); //Ordenando
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_heapSort_melhorCaso.csv");
		
		//Pior caso
		
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("station_heapSort_piorCaso.csv");
		
	}
}