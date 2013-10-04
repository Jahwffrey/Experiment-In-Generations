package MainPackage;

import java.util.*;

public class theClass {
	
	static double seed = Math.random();
	
	public static double rand(double a){
		a = ((seed*23427.3)+425568)%1;
		seed = a;
		return a;
	}
	
	public static void main(String[] args){
		//All dem vars'
		int xx = 0;
		int yy = 0;
		/*int length = 10000;
		int height = 1000;**/
		int length = 200;
		int height = 100;
		String showTime = "";
		
		System.out.println("Creatind World");
		
		//Fill the thing with 0's
		int land[][] = new int[height][length];
		for(int i = 0;i<land.length;i++){
			for(int ii = 0;ii<land[i].length;ii++){
				land[i][ii]=0;
			}
		}
		
		System.out.println("Forming Surface");
		
		//land[y][x]

		//Create the basic LAND
		yy = height/2;
		for(int i = 0;i<length;i++){
			land[yy][i]=1;
			yy+=1-(int)(rand(seed)*3);
			if(yy<2){
				yy=2;
			}
			if(yy>height-2){
				yy=height-2;
			}
		}
		
		System.out.println("Filling Ground");
		
		//run through, placing other things!
		//0 = air
		//1 = grass
		//2 = dirt
		//3 = stone
		//4 = metal
		//5 = wood
		//6 = leaves
		for(int i = 0;i<land.length;i++){
			for(int ii = 0;ii<land[i].length;ii++){
				
				//CHECK DIRECTLY ABOVE!
				if(i>2){
					//fill with dirt
					if(land[i-1][ii]==1||land[i-1][ii]==2||land[i-1][ii]==3){
						if(i<height-(height/8)){
							land[i][ii]=2;
						}
						else{
							land[i][ii]=3;
						}
					}
				}
				
				//CHECK DIRECTLY BELOW
				if(i<height-2){
					//plant a tree
					if(land[i+1][ii]==1){
						if(i>10&&(int)(rand(seed)*15)==12){
							System.out.println("Planting A Tree");
							land[i][ii]=5;
							xx=ii;
							yy=i;
							try{
								for(int j = 0;j<2+(int)(rand(seed)*7);j++){
									yy-=1;
									land[yy][xx]=5;
								}
								land[yy-1][xx]=6;
								land[yy-2][xx]=6;
								land[yy-1][xx-1]=6;
								land[yy-1][xx+1]=6;
								land[yy][xx-1]=6;
								land[yy][xx+1]=6;
								land[yy][xx+2]=6;
								land[yy][xx-2]=6;
								land[yy-1][xx-1]=6;
								land[yy-1][xx+1]=6;
							}
							catch(ArrayIndexOutOfBoundsException u){
								System.out.println("[a tree grew out of bounds]");
							}
						}
					}
				}
				
			}
		}
		
		System.out.println("Seeding Underground Rescources");
		
		try{
			//THE IMMEDIATE NEXT LINE DETERMINES RELATIVE FREQUENCY OF STONES/METAL!
			for(int i = 0;i<40+(int)(rand(seed)*40);i++){
				xx=(int)(rand(seed)*length);
				yy=(int)(rand(seed)*height);
				//put stones on dirt!
				if(land[yy][xx]==2){
					land[yy][xx]=3;
					try{
						//IMMEDIATE NEXT LINE DETERMINES SIZE OF STONE VEINS!
						for(int ii = 0;ii<(int)(rand(seed)*20);ii++){
							land[yy-1][xx]=3;
							land[yy+1][xx]=3;
							land[yy][xx+1]=3;
							land[yy][xx-1]=3;
							yy+=1-(int)(rand(seed)*3);
							xx+=1-(int)(rand(seed)*3);
						}
					}
					catch(ArrayIndexOutOfBoundsException e){
						System.out.println("[a vein of stones went out of bounds]");
					}
				}
				//put metal on stones!
				else if(land[yy][xx]==3){
					land[yy][xx]=4;
					try{
						//IMMEDIATE NEXT LINE DETERMINES SIZE OF METAL VEINS!
						for(int ii = 0;ii<(int)(rand(seed)*20);ii++){
							land[yy-1][xx]=4;
							land[yy+1][xx]=4;
							land[yy][xx+1]=4;
							land[yy][xx-1]=4;
							yy+=1-(int)(rand(seed)*3);
							xx+=1-(int)(rand(seed)*3);
						}
					}
					catch(ArrayIndexOutOfBoundsException e){
						System.out.println("[a vein of metal went out of bounds]");
					}
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Stones Outside!");
		}
		
		System.out.println("Preparing display...");
		
		//DRAW IT, FOOL!
		for(int i = 0;i<land.length;i++){
			for(int ii = 0;ii<land[i].length;ii++){
				if(land[i][ii]!=3){
					showTime+=land[i][ii];
				}
				else{
					showTime+="•";
				}
			}
			showTime+="\n";
		}
		System.out.println(showTime);
		
		System.out.println("Done");
	}
}
