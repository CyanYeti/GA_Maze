package mazeSolver;

import java.util.*;

public class PopulationCreate {
	private String Population[] = new String[MainSolver.populationSize];		//2 ->300
	
	PopulationCreate() {
		int i = 0;
		while(i != MainSolver.populationSize){ 									//2 ->300
			Population[i] = "";
			i++;
		}
	}

	public void setPopulation(int size, int indivSize){
		Random random = new Random();
		for(int i = 0; i < size; i++){
			String individual = "";
			while(individual.length() < indivSize){
				int ranNumber = random.nextInt(3);
				if(ranNumber == 1){
					individual += "0";
				} else if (ranNumber == 2){
					individual += "1";
				}
			}
			this.Population[i] = individual;
		}
	}
	public String[] getPopulation(){
		return Population;
	}
}
