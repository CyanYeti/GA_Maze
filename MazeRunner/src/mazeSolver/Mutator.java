package mazeSolver;

import java.util.Random;

public class Mutator {
	public static String[] newPop(String[] pop, int[] fit)
	  {
	    final double mutRate = .03;
	    Random rand = new Random();
	    //String[] pop = {"11011000", "10011100", "11110001", "01001101", "0001101", "01010100", "01011000", "11001111", "00010111", "01000101"};
	    //int[] fit = {72, 34, 72, 73, 49, 25, 13, 54, 76, 87};
	    
	    //picker is the fitness score itself and pickerPOS is the position it was in, so we can keep track of which individual it is
	    int[] picker = new int[fit.length/2];
	    int[] pickerPOS = new int[fit.length/2];
	    
	    //filling picker with 0s to start & pickerPOS with -1 in order to prevent it from thinking picker[0] is duplicate
	    for(int p = 0; p < picker.length; p++){
	      picker[p] = 0;
	      pickerPOS[p] = -1;
	    }
	    
	    //looping through to find the top numbers
	    for(int k = 0; k < picker.length; k++){
	      for(int i = 0; i < fit.length; i++){
	        //if the number is greater than the last and we dont already have that specific case of that number we add it to the list
	        if(picker[k] < fit[i] && dupCheck(pickerPOS, i) == false) {
	          picker[k] = fit[i];
	          pickerPOS[k] = i;
	        }
	      }
	    }
	    
	    //starting to create new population
	    //adds fitess from last gen to new gen
	    String[] newPop = new String[pop.length];
	    for(int i=0; i < newPop.length/2; i++){
	      newPop[i] = pop[pickerPOS[i]];
	      //System.out.println(newPop[i]);
	    }
	    
	    //Crossing over to fill the rest of the population slots
	    for(int p = 0; p<newPop.length-pickerPOS.length; p++){
	      String parent1 = pop[pickerPOS[rand.nextInt(pickerPOS.length) + 0]];
	      String parent2 = pop[pickerPOS[rand.nextInt(pickerPOS.length) + 0]];
	      int crosspoint = rand.nextInt(parent1.length() + 0);
	      String kid = (parent1.substring(0, crosspoint) + parent2.substring(crosspoint, parent2.length()));
	      
	      //going through new individual and mutating according to rate
	      for(int i = 0; i<kid.length(); i++){
	        if(rand.nextDouble() < mutRate){
	          String mutGene = kid.substring(i, i+1);
	          switch(mutGene){
	            case "0":
	            kid = (kid.substring(0, i) + "1" + kid.substring(i+1, kid.length()));
	            break;
	            case "1":
	          	kid = (kid.substring(0, i) + "0" + kid.substring(i+1, kid.length()));
	          	break;
	          }
	        }
	      }
	      newPop[pickerPOS.length + p] = kid;
	    }
	    return newPop;
	  }
	  //This makes sure we dont have the same individual more than once
	  static boolean dupCheck(int[] array, int dup)
	  {
	    for(int i = 0; i < array.length; i++){
	      if(array[i] == dup){
	        return true;
	      }
	    }
	    return false;
	  }
}
