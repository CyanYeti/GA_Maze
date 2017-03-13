package mazeSolver;

public class Runner {
	private static String[][] maze = PictureWorker.getMaze();
	private static int[] start = findPoint("s");
	private static int[] finish = findPoint("f");
	private static int[] position = new int[2];
	public static int fitness = 0;
	
	//position is the runner current spot in the maze. 0 is X cord and 1 is Y cord.
	public Runner(){
		position = start;
		PictureWorker.pathMaker(position[0], position[1]);
	}
	public void runUp(){
		if(spaceChecker(position[0], position[1]-1) == true){
			maze[position[0]][position[1]] = "p";
			position[1] -= 1;
			PictureWorker.pathMaker(position[0], position[1]);
		} 
	}
	public void runDown(){
		if(spaceChecker(position[0], position[1]+1) == true){
			maze[position[0]][position[1]] = "p";
			position[1] += 1;
			PictureWorker.pathMaker(position[0], position[1]);
		}
	}
	public void runLeft(){
		if(spaceChecker(position[0]+1, position[1]) == true){
			maze[position[0]][position[1]] = "p";
			position[0] += 1;
			PictureWorker.pathMaker(position[0], position[1]);
		}
	}
	public void runRight(){
		if(spaceChecker(position[0]-1, position[1]) == true){
			maze[position[0]][position[1]] = "p";
			position[0] -= 1;
			PictureWorker.pathMaker(position[0], position[1]);
		}
	}
	
	public void mazeReset(){
		maze = PictureWorker.getMaze();
		position = findPoint("s");
		PictureWorker.pathMaker(position[0], position[1]);
	}
	public boolean winChecker(){
		if(maze[position[0]][position[1]] == "f"){
			fitness -= 10;
			return true;
		}
		return false;
	}
	private static boolean spaceChecker(int x, int y){
		if(maze[x][y] == "w"){ 			//maybe add another if it hits fin to -1 from fitness(or more?)
			fitness+=1;
			return false;
		} else if(maze[x][y] == "p"){
			fitness+=1;
			return false;
		}
		return true;
	}
	public static int[] findPoint(String target){
		for(int x = 0; x < PictureWorker.getW(); x++){
			for(int y = 0; y < PictureWorker.getH(); y++){
				if(maze[x][y] == target){
					int[] pointPos = {x, y};
					return pointPos;
				}
			}
		}
		return null;
	}
	private static int fitnessScore(int[] finish, int[] position, int fitness){
		int posX = position[0]; int posY = position[1];
		int finX = finish[0]; int finY = finish[1];
		int fit;
		fit = 1 + (Math.abs(finX - posX) + Math.abs(finY - posY)) + fitness;
		return fit;
	}
	public int getFit(){
		int fitScore = 0;
		fitScore = fitnessScore(finish, position, fitness);
		return fitScore;
	}
}
