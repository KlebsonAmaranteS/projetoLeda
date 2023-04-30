package ordenacoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import metodos.methods;

public class Ordenacao3 {

	String[] array = new String[1250835];
	String column;
	
	public Ordenacao3() throws FileNotFoundException {
		File metroTrips = new File("LAMetroTrips.csv");
		Scanner read = new Scanner(metroTrips);
		String line;
		int n = 0;
		column = read.nextLine();
		
		while(read.hasNextLine()) {
			line = read.nextLine().toString();
			array[n] = line;	
			++n;
		} 
		read.close();
	}
	
	public void casosMedios() {
		
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_insertionSort_medioCaso.csv");
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_selectionSort_medioCaso");
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_quickSort_medioCaso");
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_mergeSort_medioCaso");
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_heapSort_medioCaso");
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_quickSortMedianaDe3_medioCaso");
	}
	
	public static long alteraFormatData(String line) {
		
		try {
			
			String dataNF = methods.readLinha(line, 2, 3).substring(0,2);
			String dataNF2 = methods.readLinha(line, 2, 3).substring(3,5);
			dataNF = dataNF.replace("/","");
			dataNF2 = dataNF2.replace("/","");
			int n1 = Integer.parseInt(dataNF);
			int n2 = Integer.parseInt(dataNF2);
			
			if(n1 < n2) {
				SimpleDateFormat formatOrigin = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		       	String data1 = methods.readLinha(line, 2, 3);
		    	Date data = formatOrigin.parse(data1); 

		    	SimpleDateFormat formatFinl = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    	String formatAMD = formatFinl.format(data);
		    	
		    	formatAMD = formatAMD.replace("/","");
		    	formatAMD = formatAMD.replace(":","");
		    	formatAMD = formatAMD.replace(" ","");
		    	return Long.parseLong(formatAMD);
			}
			else {
				String formatAMD = methods.readLinha(line, 2, 3);
				formatAMD = formatAMD.replace("/","");
		    	formatAMD = formatAMD.replace(":","");
		    	formatAMD = formatAMD.replace(" ","");
		    	return Long.parseLong(formatAMD);
			}
		
		}
	    catch(ParseException e){
	    	System.out.println("Error: " + e.getMessage());
	    	return -1;
	    }
    }

	private void insertionSort(int n){
	
		long chv1, chv2;
		int j;
		String aux1, aux2;
		for(int i = 1; i < 2000; i++) {
			chv1 = alteraFormatData(array[i]);
			aux1 = array[i];
			j = i - 1;
			chv2 = alteraFormatData(array[j]);
			aux2 = array[j];
		
			while((j >= 0) && (chv2 > chv1)) {
				array[j + 1] = array[j];
				j = j - 1;
				if(j >= 0) {
					chv2 = alteraFormatData(array[j]);
					aux2 = array[j];
				}
			}
			array[j + 1] = aux1;
		}
		
	} 
	
	public void arquivsInsertion(int n) {
		
		//Melhor caso
		insertionSort(n);
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_insertionSort_melhorCaso.csv");
		
		//Pior caso
		methods.inverteArr(array, array.length);
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_insertionSort_piorCaso.csv");
		} 
	
	private void selectionSort(int n) {	
		
		int idx, j;
		double aux1 = 0D, aux2 = 0D;
		String line;
		for (int i = 0; i < n; i++) {
			idx = i;
			for (j = i + 1; j < n; j++) {
				aux1 = alteraFormatData(array[idx]);
				aux2 = alteraFormatData(array[j]);;
				if (aux2 < aux1)
					idx= j;
			
			line = array[idx];
			array[idx] = array[i];
			array[i] = line;
			}
		}		
	}
	
	public void arquivsSelection(int n) {
		//Melhor caso
		selectionSort(n); 
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_selectionSort_melhorCaso");
				
		//Pior caso
		methods.inverteArr(array, array.length);
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_selectionSort_piorCaso");
	} 
	
	private void heapSort(int tamArr)
    {
        int num = tamArr;//array.length;
  
        
        for (int i = num / 2 - 1; i >= 0; i--)
            heapify(array, num, i);
  
        
        for (int i = num - 1; i > 0; i--) {
            
            String temp = array[0];
            array[0] = array[i];
            array[i] = temp;
  
            
            heapify(array, i, 0);
        }
    } 
  
    private static void heapify(String array[], int n, int i)
    {
        int largura = i; 
        int esquerd = 2 * i + 1;
        int direit = 2 * i + 2; 
  
        if (esquerd < n && alteraFormatData(array[esquerd]) > alteraFormatData(array[largura]))
            largura = esquerd;
  
        if (direit < n && alteraFormatData(array[direit]) > alteraFormatData(array[largura]))
            largura = direit;
  
        if (largura != i) {
            String troca = array[i];
            array[i] = array[largura];
            array[largura] = troca;
  
            heapify(array, n, largura);
        }
    } 
    
    public void arquivsHeapSort(int n) {
		
    	//Melhor caso
		heapSort(n);
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_heapSort_melhorCaso");
		
		//Pior caso
		methods.inverteArr(array, array.length);
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_heapSort_piorCaso");
		
	}
    
    public static int particiona1(String arr[], int low, int high)
    {
        long pivot = alteraFormatData(arr[high]);
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (alteraFormatData(arr[j]) <= pivot)
            {
                i++;
 
                
                String tempo = arr[i];
                arr[i] = arr[j];
                arr[j] = tempo;
            }
        }
 
        String tempo = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = tempo;
 
        return i + 1;
    }
 
    private static void quicksort(String arr[], int baix, int alt)
    {
        if (baix < alt)
        {

            int pi = particiona1(arr, baix, alt);

            quicksort(arr, baix, pi-1);
            quicksort(arr, pi+1, alt);
        }
    }
    
    public void arquivsQuickSrt(int n) {
		
    	//Melhor caso
		quicksort(this.array, 0, n); 
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_quickSort_melhorCaso");
		
		//Pior caso
		methods.inverteArr(array, array.length);
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_quickSort_piorCaso");
		
	} 
    
    private static int pivoMed(String array[], int baix, int alt) {
    	
		long primeiro = alteraFormatData(array[baix]);
		long ultimo = alteraFormatData(array[array.length - 1]);
		int meio = (alt) / 2;

		String[] ordenaArr = {array[baix], array[meio], array[alt]};

		Arrays.sort(ordenaArr);

		String valorMedio = ordenaArr[1];

		long tempo = alteraFormatData(array[alt]);
		String auxilTempo = array[alt];
		String line = array[alt];
		array[alt] = valorMedio;
		long aux1 = alteraFormatData(array[meio]);
		
		if (alteraFormatData(valorMedio) == alteraFormatData(array[baix])) {
			array[baix] = line;
		} else if (alteraFormatData(valorMedio) == (aux1)) {
			array[meio] = auxilTempo;
		}
		
		return particiona(array, baix, alt);

	}

	private static void medQuickSort(String array[], int baix, int alt) {
		if (baix >= alt)
			return;

		if (baix < alt) {

			int p = pivoMed(array, baix, alt);

			QuickSort(array, baix, alt);

		}
	}

	private static void QuickSort(String array[], int baix, int alt) {

		if (baix < alt) {
			int p = particiona(array, baix, alt);

			QuickSort(array, baix, p - 1);
			QuickSort(array, p + 1, alt);
		}
	}
	
	private static int particiona(String array[], int baix, int alt) {
		long pivot = alteraFormatData(array[alt]);
		int i = (baix - 1);
		long aux1;
		int contador = 0;

		for (int j = baix; j < alt; j++) {
			aux1 = alteraFormatData(array[j]);
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
		medQuickSort(this.array, 0, n);
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_quickSortMedianaDe3_melhorCaso");
		
		//Pior caso
		methods.inverteArr(array, array.length);
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_quickSortMedianaDe3_piorCaso");
		
	}
	
	private static void merge(String array[], int esquerd, int m, int direit)
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
        long aux1, aux2;
        while (i < num1 && j < num2) {
        	aux1 = alteraFormatData(left[i]);
        	aux2 = alteraFormatData(right[j]);
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
    
    private static void mergeSort(String array[], int esquerd, int direit)
    {
        if (esquerd < direit) {
        	
            int m = esquerd+ (direit-esquerd)/2;
 
            mergeSort(array, esquerd, m);
            mergeSort(array, m + 1, direit);
 
            merge(array, esquerd, m, direit);
        }
    }
    
    public void arquivsMergeSort(int n) {
		
    	//Melhor caso
    	mergeSort(this.array, 0, n); 
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_mergeSort_melhorCaso");
		
		//Pior caso
		methods.inverteArr(array, array.length);
		methods.geraArquivCsv(this.array, this.column);
		methods.alteraNomeArquiv("start_time_mergeSort_piorCaso");
		
	} 
}