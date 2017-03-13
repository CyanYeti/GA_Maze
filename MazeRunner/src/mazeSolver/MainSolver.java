package mazeSolver;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("unused")
public class MainSolver {
	public static int populationSize = 3;
	public static void main(String args[]){
		//Programming maze in to a array
		
		
		//Creating a population of random bytes aka chromosome
		PopulationCreate population = new PopulationCreate();
		population.setPopulation(populationSize, 408);						//2 -> 300
		String[] pop = population.getPopulation();
		
		int g = 0;
		boolean mazeComplete = false;
		while(mazeComplete == false){
			//Decoding the population into U, R, D, L
			String[] Dpop = new String[populationSize];							//2 -> 300
			Dpop = Decoder.decode(pop);
			
			int[] fitness = new int[populationSize];								//2 -> 300
			Runner runner = new Runner();
			for(int k = 0; k<Dpop.length; k++){
				for(int i =0; i < Dpop[k].length(); i++){
					if(Dpop[k].substring(i, i+1).equals("U")){
						runner.runUp();
						if(runner.winChecker() == true){
							break;
						}
					} else if(Dpop[k].substring(i, i+1).equals("D")){
						runner.runDown();
						if(runner.winChecker() == true){
							break;
						}
					} else if(Dpop[k].substring(i, i+1).equals("L")){
						runner.runLeft();
						if(runner.winChecker() == true){
							break;
						}
					} else if(Dpop[k].substring(i, i+1).equals("R")){
						runner.runRight();
						if(runner.winChecker() == true){
							break;
						}
					}
				}
				fitness[k] = runner.getFit();
				PictureWorker.pathFinished();
				runner.mazeReset();
				
			}
			
			for(int i : fitness){
				if(i == 1){
					mazeComplete = true;
					break;
				} else {
					mazeComplete = false;
				}
			}
			if(mazeComplete == false && g < 10){
				//mutate and do again
				pop = Mutator.newPop(pop, fitness);
				System.out.println("Finished gen "+g);
				g++;
			} else if(mazeComplete == true){
				System.out.println("The maze is complete");
			}
		}
		
	}
	
	
	
	public static void printArray(String[] toPrintArray){
		//printing every individual/item in the array
				int p = 0;
				while(p<toPrintArray.length){
					System.out.println(toPrintArray[p]);
					p++;
				}
	}
}

