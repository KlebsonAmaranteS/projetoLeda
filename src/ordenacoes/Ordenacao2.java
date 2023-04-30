package ordenacoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import metodos.methods;

public class Ordenacao2 {
	
	String[] arr = new String[1250835];
	String column;
	
	public Ordenacao2() throws FileNotFoundException {
		File metroTrips = new File("LAMetroTrips.csv");
		Scanner read = new Scanner(metroTrips);
		String line;
		this.column = read.nextLine();
		int n = 0;
		
		while(read.hasNextLine()) {
			line = read.nextLine().toString();
			this.arr[n] = line;	
			++n;
		} 
		read.close();
	}
	
	public void casosMedios() {
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_insertionSort_medioCaso");
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_selectionSort_medioCaso");
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_quickSort_medioCaso");
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_countingSort_medioCaso");
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_mergeSort_medioCaso");
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_heapSort_medioCaso");
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_quickSortMedianaDe3_medioCaso");
	} 
	
	private void insertionSort(int n){
		
		int chv1, chv2, j;
		String aux1, aux2;
		for(int i = 1; i < n; i++) {
			chv1 = Integer.parseInt(methods.readLinha(this.arr[i], 1, 2));
			aux1 = this.arr[i];
			j = i - 1;
			chv2 = Integer.parseInt(methods.readLinha(this.arr[j], 1, 2));
			aux2 = this.arr[j];
			
			while((j >= 0) && (chv2 > chv1)) {
				this.arr[j + 1] = this.arr[j];
				j = j - 1;
				if(j >= 0) {
					chv2 = Integer.parseInt(methods.readLinha(this.arr[j], 1, 2));
					aux2 = this.arr[j];
				}
			}
			this.arr[j + 1] = aux1;
		}
    }
	
	public void arquivsInsertion(int n) {
		
		//Melhor caso
		insertionSort(n); 
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_insertionSort_melhorCaso.csv");
		
		//Pior caso
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_insertionSort_piorCaso.csv");
	} 
	
	private void selectionSort(int n) throws NumberFormatException {	
		
		int idx, j;
		int aux1 = 0, aux2 = 0;
		String line;
		for (int i = 0; i < n; i++) {
			idx = i;
			for (j = i + 1; j < n; j++) {
				aux1 = Integer.parseInt(methods.readLinha(arr[idx], 1, 2));
				aux2 = Integer.parseInt(methods.readLinha(arr[j], 1, 2));
				if (aux2 < aux1)
					idx= j;
			
			line = arr[idx];
			arr[idx] = arr[i];
			arr[i] = line;
			}
		}		
	}
	
	public void arquivsSelection(int n) {
		//Melhor caso
		selectionSort(n); 
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_selectionSort_melhorCaso");
				
		//Pior caso
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_selectionSort_piorCaso");
	}
	
	private void countingSort()
    {
        int num = arr.length;
 
        String saida[] = new String[num];
        int contador[] = new int[num];
        
        for (int i = 0; i < num; ++i)
            ++contador[Integer.parseInt(methods.readLinha(arr[i], 1, 2))]; 
 
        for (int i = 1; i <= num - 1; ++i){
            contador[i] += contador[i - 1];
        }
 
        int aux;
        for (int i = num - 1; i >= 0; i--) {
        	aux = Integer.parseInt(methods.readLinha(arr[i], 1, 2));
            saida[contador[aux] - 1] = arr[i];
            --contador[aux];
        }

        for (int i = 0; i < num; ++i){
            arr[i] = saida[i];
        }
    }
	
	public void arquivsCounting() {
		
		//Melhor caso
		countingSort(); 
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_countingSort_melhorCaso");
				
		//Pior caso
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_countingSort_piorCaso");
	}
	
	public static void merge(String array[], int esquerd, int m, int direit)
    {
        int num1 = m - esquerd + 1;
        int num2 = direit - m;
 
        String left[] = new String[num1];
        String right[] = new String[num2];
 
        for (int i = 0; i < num1; ++i)
            left[i] = array[esquerd + i];
        for (int j = 0; j < num2; ++j)
            right[j] = array[m + 1 + j];
        
        int i = 0, j = 0;
 
        int k = esquerd;
        int aux1, aux2;
        while (i < num1 && j < num2) {
        	aux1 = Integer.parseInt(methods.readLinha(left[i], 1, 2));
        	aux2 = Integer.parseInt(methods.readLinha(right[j], 1, 2));
            if (aux1 <= aux2) {
                array[k] = left[i];
                i++;
            }
            else {
                array[k] = right[j];
                j++;
            }
            k++;
        }
 
        while (i < num1) {
            array[k] = left[i];
            i++;
            k++;
        }
 
        while (j < num2) {
            array[k] = right[j];
            j++;
            k++;
        }
    }
    
    public static void mergeSort(String array[], int esquerd, int direit)
    {
        if (esquerd < direit) {
        	
            int m = esquerd+ (direit-esquerd)/2;
 
            mergeSort(array, esquerd, m);
            mergeSort(array, m + 1, direit);
 
            merge(array, esquerd, m, direit);
        }
    }
    
    public void arquivsMerge() {
		
    	//Melhor caso
    	mergeSort(this.arr, 0, 1250834);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_mergeSort_melhorCaso");
		
		//Pior caso
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_mergeSort_piorCaso");
		
	}
    
    private void heapsort() {
		int val = arr.length;
		  
        for (int i = val / 2 - 1; i >= 0; i--)
            heapify(arr, val, i);
  
        for (int i = val - 1; i > 0; i--) {
            String temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
  
            heapify(arr, i, 0);
        }
		
	}

	private static void heapify(String[] arr, int val, int i) {

		int largura = i;
        int esquerd = 2 * i + 1; 
        int direit = 2 * i + 2; 
        
  
        if (esquerd < val && Integer.parseInt(methods.readLinha(arr[esquerd], 1, 2)) > Integer.parseInt(methods.readLinha(arr[largura], 1, 2)))
            largura = esquerd;
  
        if (direit < val && Integer.parseInt(methods.readLinha(arr[direit], 1, 2)) > Integer.parseInt(methods.readLinha(arr[largura], 1, 2)))
            largura = direit;
  
        if (largura != i) {
            String troca = arr[i];
            arr[i] = arr[largura];
            arr[largura] = troca;
  
            heapify(arr, val, largura);
	    }
		
	} 
	
	public void arquivsHeapSort() {
		
		//Melhor caso
		heapsort(); 
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_heapSort_melhorCaso");
		
		//Pior caso
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_heapSort_piorCaso");
		
	}
	
    public static int pivoMed(String array[], int baix, int alt) {
    	
		int primeiro = Integer.parseInt(methods.readLinha(array[baix], 1, 2));
		int ultimo = Integer.parseInt(methods.readLinha(array[array.length - 1], 1, 2));
		int meio = (alt) / 2;

		String[] ordenaArr = { array[baix], array[meio], array[alt] };

		Arrays.sort(ordenaArr);

		String valorMedio = ordenaArr[1];

		int tempo = Integer.parseInt(methods.readLinha(array[alt], 1, 2));
		String auxliTempo = array[alt];
		String line = array[alt];
		array[alt] = valorMedio;
		String aux1 = methods.readLinha(array[meio], 1, 2);
		
		if (valorMedio.equals(array[baix])) {
			array[baix] = line;
		} else if (valorMedio.equals(aux1)) {
			array[meio] = auxliTempo;
		}
		
		return particiona(array, baix, alt);

	}

	public static void medQuickSort(String array[], int baix, int alt) {
		if (baix >= alt)
			return;

		if (baix < alt) {

			int p = pivoMed(array, baix, alt);

			QuickSort(array, baix, alt);

		}
	} 
	
	public static void QuickSort(String array[], int baix, int alt) {

		if (baix < alt) {
			int p = particiona(array, baix, alt);

			
			QuickSort(array, baix, p - 1);
			QuickSort(array, p + 1, alt);
		}
	} 
	
	public static int particiona(String array[], int baix, int alt) {
		int pivot = Integer.parseInt(methods.readLinha(array[alt], 1, 2));
		int i = (baix - 1);
		int aux1;
		int contador = 0;

		for (int j = baix; j < alt; j++) {
			aux1 = Integer.parseInt(methods.readLinha(array[j], 1, 2));
			if (aux1 <= pivot) {
				i++;

				String line = array[i];
				array[i] = array[j];
				array[j] = line;
				contador++;
			}
			contador++;
		}
		
		String line = array[i + 1];
		array[i + 1] = array[alt];
		array[alt] = line;
		contador++;
		
		return i + 1;
	} 
	
	public void arquivsQuickSort3(int n) {
		
		//Melhor caso
		medQuickSort(this.arr, 0, n);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_quickSortMedianaDe3_melhorCaso");
		
		//Pior caso
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_quickSortMedianaDe3_piorCaso");
		
	}
	
	public static int particiona1(String arr[], int baix, int alt)
    {
        int pivot = Integer.parseInt(methods.readLinha(arr[alt], 1, 2));
        int i = (baix-1);
        for (int j=baix; j<alt; j++)
        {
            if (Integer.parseInt(methods.readLinha(arr[alt], 1, 2)) <= pivot)
            {
                i++;
 
               
                String tempo = arr[i];
                arr[i] = arr[j];
                arr[j] = tempo;
            }
        }
 
        String tempo = arr[i+1];
        arr[i+1] = arr[alt];
        arr[alt] = tempo;
 
        return i + 1;
    }
 
    private static void quicksort(String arr[], int baix, int alt)
    {
        if (baix < alt)
        {

            int p = particiona1(arr, baix, alt);

            quicksort(arr, baix, p-1);
            quicksort(arr, p+1, alt);
        }
    }
	
	public void arquivsQuickSrt(int n) {
		
		//Melhor caso
		quicksort(this.arr, 0, n); 
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_quickSort_melhorCaso");
		
		//Pior caso
		methods.inverteArr(arr, arr.length);
		methods.geraArquivCsv(this.arr, this.column);
		methods.alteraNomeArquiv("duration_quickSort_piorCaso");
		
	} 
}