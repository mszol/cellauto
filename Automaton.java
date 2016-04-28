/******************************************
* Marina Zolkin
* group 89-512-02
* ex1
******************************************/
import java.util.Random;

public class Automaton {
	
	public static int size=100;
	public static int steps=250;
	private boolean[][] board = new boolean[size][size];
	
	public Automaton(){
	}
	
	
	public Automaton(int set_size, int set_steps){
		size = set_size;
		steps = set_steps;
		board = new boolean[size][size];
	}
	
	public boolean[][] getBoard(){
		return this.board;
	}
	
	
	public void setBoard(boolean[][] to_set){
		for(int i=0; i<size;i++){
			for(int j=0; j<size; j++){
				this.board[i][j]=to_set[i][j];
			}
		}
	}
	
	public boolean isAlive(int i, int j){
		if(board[i][j])
			return true;
		return false;
	}
	
	//received the top-left coordinates of a square 4x4 cells and perform transformation
	public void changeSquare(int i, int j){
		int rule = checkRule(i,j);
		switch(rule){
			case 1: break;
			case 2: switchValues(i,j);
					break;
			case 3: switchAndRotate(i,j);
		}
	}
	
	//receives the top-left coordinates of a 4x4 square, returns the number of the rule it matches
	public int checkRule(int row, int column){
		int living = 0;
		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				if (board[row+i][column+j])
					living++;
			}
		}
		if(2==living)
			return 1;
		else{
			if(3==living)
				return 3;
			else
				return 2;
		}
	}
	
	public void switchValues(int row, int column){
		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				board[row+i][column+j]=!board[row+i][column+j];
			}
		}
	}
	
	public void switchAndRotate(int row, int column){
		switchValues(row, column);
		if(board[row][column]){
			board[row][column]=false;
			board[row+1][column+1]=true;
		}
		else{
			if(board[row][column+1]){
				board[row][column+1]=false;
				board[row+1][column]=true;
			}
			else{
				if(board[row+1][column]){
					board[row+1][column]=false;
					board[row][column+1]=true;
				}
				else{
					board[row+1][column+1]=false;
					board[row][column]=true;
				}
			}
		}			
	}
	

	
	public void oddStep(){
		for(int i=0; i<size; i+=2){
			for(int j=0; j<size; j+=2){
				changeSquare(i,j);
			}
		}
	}
	
	public void evenStep(){
		for(int i=1; i<size-1; i+=2){
			for(int j=1; j<size-1; j+=2){
				changeSquare(i,j);
			}
		}
	}
	
	
	
	public void evenStepWrapAround(){
		evenStep();
		//Wrap around rows
		for(int i=1; i<size-1; i+=2){
			boolean[][]square = {{board[i][size-1], board[i][0]}, {board[i+1][size-1], board[i+1][0]}};
			int rule = checkRuleWrap(square);
			if(2 == rule){
				board[i][size-1]=!board[i][size-1];
				board[i][0]=!board[i][0];
				board[i+1][size-1]=!board[i+1][size-1];
				board[i+1][0]=!board[i+1][0];
			}
			
			if(3 == rule){
				helpWrap(square);
				//copy back onto the board
				board[i][size-1]= square[0][0];
				board[i][0]=square[0][1];
				board[i+1][size-1]=square[1][0]; 
				board[i+1][0]= square[1][1];
			}
		}
		
		//Wrap around columns
		for(int j=1; j<size-1; j+=2){
			boolean[][]square = {{board[size-1][j], board[size-1][j+1]}, {board[0][j], board[0][j+1]}};
			int rule = checkRuleWrap(square);
	
			if(2 == rule){
				board[size-1][j]=!board[size-1][j];
				board[size-1][j+1]=!board[size-1][j+1];
				board[0][j]=!board[0][j];
				board[0][j+1]=!board[0][j+1];
			}
			
			if(3 == rule){
				helpWrap(square);	
				//copy back onto the board
				board[size-1][j] = square[0][0];
				board[size-1][j+1] =square[0][1];
				board[0][j] =square[1][0]; 
				board[0][j+1] = square[1][1];
			}
		}
		
		//Wrap around the corners 
		boolean[][]square = {{board[0][0], board[0][size-1]}, {board[size-1][0], board[size-1][size-1]}};
		int rule = checkRuleWrap(square);
		if(2 == rule){
			board[0][0]=!board[0][0];
			board[0][size-1]=!board[0][size-1];
			board[size-1][0]=!board[size-1][0];
			board[size-1][size-1]=!board[size-1][size-1];
		}
		
		if(3 == rule){
			helpWrap(square);	
			//copy back onto the board
			board[0][0] = square[0][0];
			board[0][size-1] =square[0][1];
			board[size-1][0] =square[1][0]; 
			board[size-1][size-1] = square[1][1];
		}
		
	}

	
	
public int checkRuleWrap(boolean [][] square){
	int living = 0;
	for(int i=0; i<2; i++){
		for(int j=0; j<2; j++){
			if (square[i][j])
				living++;
		}
	}
	if(2==living)
		return 1;
	else{
		if(3==living)
			return 3;
		else
			return 2;
	}
}

	public void helpWrap(boolean [][] square){

			//switch
			for(int m=0; m<2; m++){
				for(int n=0; n<2; n++){
					square[m][n]=!square[m][n];
				}
			}
			//rotate
			if(square[0][0]){
				square[0][0]=false;
				square[1][1]=true;
			}
			else{
				if(square[0][1]){
					square[0][1]=false;
					square[1][0]=true;
				}
				else{
					if(square[1][0]){
						square[1][0]=false;
						square[0][1]=true;
					}
					else{
						square[1][1]=false;
						square[0][0]=true;
					}
				}
			}
	}
	
	public void initialize(){
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				Random rn = new Random();
				int n = rn.nextInt() % 2;
				if(1==n)
					board[i][j]=true;
				else
					board[i][j]=false;
			}
		}
	}
	
	

	
	public void caseOne(){
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				board[i][j]=true;
			}
		}
	}
	
	public void caseThree(){
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(i==j)
					board[i][j]=true;
				else
					board[i][j]=false;
			}
		}
	}	
	
	public void setWhite(){
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				board[i][j]=false;
			}
		}
	}
			
	public void gliderOne(){
		setWhite();
		for (int i=size/3; i<size*2/3; i++){
			for(int j=size/3; j<size*2/3; j++){
				Random rn = new Random();
				int n = rn.nextInt() % 2;
				if(1==n)
					board[i][j]=true;
				else
					board[i][j]=false;
			}
		}
	}	
	
	public void gliderTwo(){
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if((i==0)||(j==0)||(i==size-1)||(j==size-1))
					board[i][j]=true;
				else
					board[i][j]=false;
			}
		}
	}	



}