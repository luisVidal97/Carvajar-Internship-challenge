package carvajalProblem;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise {
	
	private static ArrayList<String> arrayLines;

	public static void main(String[] args) {
		
		//--------------------------------------------------------------------------------------------------------------------------------
		// Only reading of lines
		//--------------------------------------------------------------------------------------------------------------------------------
		
		System.out.println("Por favor, ingrese el número de lineas a introducir:");
		Scanner sc = new Scanner(System.in);
		int numberLines = Integer.parseInt(sc.nextLine());
		
		arrayLines = new ArrayList<String>();
		
		for (int i = 0; i < numberLines; i++) {
			System.out.println("Escriba rango de la linea número "+ (i+1) + ". Ejemplo: 6-7");
			String line = sc.nextLine();
			arrayLines.add(line);
			
			String[] arrayLine = line.split("-");
			int start = Integer.parseInt(arrayLine[0]);
			int finish = Integer.parseInt(arrayLine[1]) ;
			int bitLines = finish - start;
			System.out.print(start);
			for (int j = 0; j < bitLines; j++) {
				System.out.print("-");
			}
			System.out.println(finish);
			System.out.println();
		}
		
		//--------------------------------------------------------------------------------------------------------------------------------
		// Only processing  lines
		//--------------------------------------------------------------------------------------------------------------------------------
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("A continuación se van a procesar las lineas... Espere un momento, por favor.");
		System.out.println("------------------------------------------------------------------------------------------");
		algorithmProcessLines();
		System.out.println();
		
		//--------------------------------------------------------------------------------------------------------------------------------
		// Show results
		//--------------------------------------------------------------------------------------------------------------------------------
		System.out.println("El resultado es el siguiente:");
		for (int i = 0; i < arrayLines.size(); i++) {
			System.out.println(arrayLines.get(i));
			
			String[] arrayLine = arrayLines.get(i).split("-");
			int start = Integer.parseInt(arrayLine[0]);
			int finish = Integer.parseInt(arrayLine[1]) ;
			int bitLines = finish - start;
			System.out.print(start);
			for (int j = 0; j < bitLines; j++) {
				System.out.print("-");
			}
			System.out.println(finish);
			
		}

	}
	
	
	/**
	 * Algorithm that take care of analyse all lines that the user put for console
	 * 
	 */
	private static void algorithmProcessLines() {
		
		
		
		for (int i = 0; i < arrayLines.size(); i++) {
			
			String currentRange =  arrayLines.get(i);
			String[] array =  currentRange.split("-");
			int startRangeCurrent = Integer.parseInt(array[0]);
			int finishRangeCurrent = Integer.parseInt(array[1]);
			
			
			//------------------------------------------------------------------------------------------------------------
			// This part check  if a specific range has others ranges, I mean, that other range is content in specific 
			// range. If that occurs, inside of hasTottallyOtherRange()'s method I remove the content range and diminish 1 in
			// my item(index) of loop for.
			//------------------------------------------------------------------------------------------------------------
			if( hasTottallyOtherRange(i, startRangeCurrent ,finishRangeCurrent ) ) {
				i = arrayLines.indexOf(currentRange) - 1;
			
			} 
			
			//------------------------------------------------------------------------------------------------------------
			// This part of my code is the responsible that check if a part of a specific range is content in a part of
			// a other specific range. If that happens, it means that the lines should overcome.
			//------------------------------------------------------------------------------------------------------------
			else if( hasPartOtherRange(i+1,startRangeCurrent ,finishRangeCurrent ) ) {
				if(i!=0) {
					i-=1;
				}else{
					i=0;
				}
			} 
			
		}
		
	
		
	}
	
	
	/**
	 * 
	 * @param startRangeCurrent: current number(start) of range that I am analyzing 
	 * @param finishRangeCurrent: current number(end) of range that I am analyzing 
	 * @return true if a range of line  contents another part of range, false  otherwise
	 */
	private static boolean hasTottallyOtherRange(int indexCurrent, int startRangeCurrent, int finishRangeCurrent) {
		
		boolean flag = false;
		for (int j = 0; j < arrayLines.size() && !flag; j++) {
			
			String[] array =  arrayLines.get(j).split("-");
			int startRange = Integer.parseInt(array[0]);
			int finishRange = Integer.parseInt(array[1]);
			
			if ( j!= indexCurrent && startRangeCurrent <= startRange && finishRange <= finishRangeCurrent ) {
				arrayLines.remove(j);
				flag = true;
			}
			
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @param currentIndex
	 * @param startRangeCurrent: current number(start) of range that I am analyzing 
	 * @param finishRangeCurrent: current number(end) of range that I am analyzing 
	 * @return
	 */
	private static boolean hasPartOtherRange(int currentIndex, int startRangeCurrent, int finishRangeCurrent) {
		
		boolean flag = false;
		
		for (int j = currentIndex; j <  arrayLines.size() && !flag; j++) {
			String[] array =  arrayLines.get(j).split("-");
			int startRange = Integer.parseInt(array[0]);
			int finishRange = Integer.parseInt(array[1]);
			
			if( startRangeCurrent < finishRange && startRangeCurrent > startRange && finishRangeCurrent > finishRange) {
				String newLine = startRange+ "-" + finishRangeCurrent;
				arrayLines.set(currentIndex-1, newLine);
				arrayLines.remove(j);
				flag=true;
			}else if(startRange < finishRangeCurrent && finishRangeCurrent < finishRange && startRangeCurrent < startRange) {
				String newLine = startRangeCurrent+ "-" + finishRange;
				arrayLines.set(currentIndex-1, newLine);
				arrayLines.remove(j);
				flag=true;
			}
		}
		
		return flag;
		
	}


}
