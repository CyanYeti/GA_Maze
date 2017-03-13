package mazeSolver;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class PictureWorker {
	//path to the maze
	private final static String path = "M:/Digital Media/Winikka/Programming 2/_Student Folders/Period1/fernt301/Maze.png";
	public static int fileName = 0;
	
	//only have to set img once, instead of 3+ times
	static BufferedImage baseimg;
	public static void LoadImageAppSmall() {
	       try {
	           baseimg = ImageIO.read(new File(path));
	       } catch (IOException e) {
	       } 
	}
	
	//two methods to send the img height and width
	public static int getW(){
		LoadImageAppSmall();
		return baseimg.getWidth();
	}
	public static int getH(){
		LoadImageAppSmall();
		return baseimg.getHeight();
	}
	
	//loading the maze into an array
	public static String[][] getMaze(){
		//load in pic
		LoadImageAppSmall();
		//loops through every pixel, gets its rgb, assigns its type into a 2d array
		String[][] maze = new String[baseimg.getWidth()][baseimg.getHeight()];
		for(int x=0; x<baseimg.getWidth(); x++){
			for(int y=0; y<baseimg.getHeight(); y++){
				int RGB = baseimg.getRGB(x,y);
				int a = (RGB>>24) & 0xff; //This is setting the rgb in a read able format and 
				int r = (RGB>>16) & 0xff; //declaring the individual ARGB
				int g = (RGB>>8)  & 0xff; //See https://www.dyclassroom.com/image-processing-project/how-to-get-and-set-pixel-value-in-java
				int b = (RGB)     & 0xff; //for a better explanation
				String stringPix = "" +a+r+g+b;
				switch(stringPix) {
					case "255000":
						maze[x][y] = "w"; //wall
						break;
					case "25525500":
						maze[x][y] = "f"; //finish
						break;
					case "255255255255":
						maze[x][y] = "e"; //empty space
						break;
					case "25502550":
						maze[x][y] = "s"; //start
						break;
					case "25500255":
						maze[x][y] = "p"; //path
					default:
						System.out.println("The was an error at pixel: " +x +", " +y);
						System.out.println("stringPix = "+stringPix);
				}
			}
		}
		//System.out.println("mazeSet");
		mazeSet();
		return maze;
	}
	
	//Changes maze to show path which is blue
	public static void pathMaker(int x, int y){
		BufferedImage img1 = null;
		try{
			img1 = ImageIO.read(new File("M:/Digital Media/Winikka/Programming 2/_Student Folders/Period1/fernt301/mazeRunning/MazeS" +fileName+ ".png"));
		} catch (IOException e){		
		}
		int argb = (255 << 24) | (0 << 16) | (0 << 8) | (255);
		img1.setRGB(x, y, argb);
		pathSaver(img1);
	}
	
	//saves the new maze with the new path
	public static void pathSaver(BufferedImage img2){
		fileName += 1;
		File f = new File("M:/Digital Media/Winikka/Programming 2/_Student Folders/Period1/fernt301/mazeRunning/MazeS" +fileName+ ".png");
		try {
			ImageIO.write(img2, "PNG", f);
		} catch (IOException e) {
			System.out.println("Something dun goofed while saving img");
		}
	}
	//saves the individuals last image + a marker to see it in the file + sets us up for recursion
	public static void pathFinished(){
		//first one saves the final position
		BufferedImage img3 = null;
		try{
			img3 = ImageIO.read(new File("M:/Digital Media/Winikka/Programming 2/_Student Folders/Period1/fernt301/mazeRunning/MazeS" +fileName+ ".png"));
		} catch (IOException e){		
		}
		File f = new File("M:/Digital Media/Winikka/Programming 2/_Student Folders/Period1/fernt301/mazeRunning/MazeS" +fileName+ "F.png");
		try {
			ImageIO.write(img3, "PNG", f);
		} catch (IOException e) {
			System.out.println("Something dun goofed while saving img");
		}
		//adds one to filename for when getmaze is called again from runner, the new maze is its own.
		fileName+=1;
	}
	//second resets the img so that path saver starts clean
	private static void mazeSet(){
		BufferedImage img3 = null;
		try{
			img3 = ImageIO.read(new File("M:/Digital Media/Winikka/Programming 2/_Student Folders/Period1/fernt301/Maze.png"));
		} catch (IOException e){		
		}
		File g = new File("M:/Digital Media/Winikka/Programming 2/_Student Folders/Period1/fernt301/mazeRunning/MazeS" +fileName+ ".png");
		try {
			ImageIO.write(img3, "PNG", g);
		} catch (IOException e) {
			System.out.println("Something dun goofed while saving img");
		}
	}
	//opens visual maze
	public static void printPic(){
		JFrame frame = new JFrame("Maze Sample");
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.add(new LoadImageApp());
		frame.pack();
		frame.setVisible(true);
	}
}

//This works to print the maze, it uses a blown up version
@SuppressWarnings("serial")
class LoadImageApp extends Component {
    
    BufferedImage img;
 
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
    
    //loads the image in from the path
    public LoadImageApp() {
       try {
           img = ImageIO.read(new File("M:/Digital Media/Winikka/Programming 2/_Student Folders/Period1/fernt301/Maze.png"));
       } catch (IOException e) {
       } 
    }
    //get the dimensions of the picture
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
}