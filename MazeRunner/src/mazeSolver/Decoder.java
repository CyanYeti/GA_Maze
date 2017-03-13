package mazeSolver;

public class Decoder {
	public static String[] decode(String[] Population){
		String decodedPop[] = new String[Population.length];
		//removing null/emptying array
		int i = 0;
		while(i != decodedPop.length){
			decodedPop[i] = "";
			i++;
		}
		
		//decoding 
		int k =0;
	    while(k != Population.length){
	        i = 0;
	        while(i != Population[k].length()){
	            if((Population[k].substring(i, i+2)).equals("00")){
	                //System.out.println((Population[k].substring(i, i+2)));
	                decodedPop[k] += "D";
	                i+=2;
	            } else if ((Population[k].substring(i, i+2)).equals("01")){
	                //System.out.println((Population[k].substring(i, i+2)));
	                decodedPop[k] += "R";
	                i+=2;
	            } else if ((Population[k].substring(i, i+2)).equals("10")){
	                //System.out.println((Population[k].substring(i, i+2)));
	                decodedPop[k] += "L";
	                i+=2; 
	            } else if ((Population[k].substring(i, i+2)).equals("11")){
	                //System.out.println((Population[k].substring(i, i+2)));
	                decodedPop[k] += "U";
	                i+=2; 
	            } else {
	                System.out.println("Im broken (decoder IF[])");
	            }
	        }
	        //System.out.println(Population[k]);
	        //System.out.println(decodedPop[k]);
	        k++;
	    }
	    return decodedPop;
	}
	public static String[] encode(String[] Population){
		//String[] Population = {"ULLR", "DULR", "UUDR", "DRRL", "RLDU", "UDRL", "LURR", "DLRL"};
	    String encodedPop[] = new String[Population.length];
	    int i = 0;
		while(i != encodedPop.length){
			encodedPop[i] = "";
			
			i++;
		}
		
	    int k =0;
	    while(k != Population.length){
	        i = 0;
	        while(i != Population[k].length()){
	            if((Population[k].substring(i, i+1)).equals("D")){
	                System.out.println((Population[k].substring(i, i+1)));
	                encodedPop[k] += "00";
	                i+=1;
	            } else if ((Population[k].substring(i, i+1)).equals("R")){
	                System.out.println((Population[k].substring(i, i+1)));
	                encodedPop[k] += "01";
	                i+=1;
	            } else if ((Population[k].substring(i, i+1)).equals("L")){
	                System.out.println((Population[k].substring(i, i+1)));
	                encodedPop[k] += "10";
	                i+=1; 
	            } else if ((Population[k].substring(i, i+1)).equals("U")){
	                System.out.println((Population[k].substring(i, i+1)));
	                encodedPop[k] += "11";
	                i+=1; 
	            } else {
	                //System.out.println("Im broken");
	            }
	        }
	        k++;
	      }
	    return encodedPop;
	}
}
