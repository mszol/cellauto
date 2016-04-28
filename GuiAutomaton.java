
/******************************************
* Marina Zolkin
* group 89-512-02
* ex1
******************************************/
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GuiAutomaton extends JPanel
{

	private static final long serialVersionUID = 1L;
	private int size = 100;
	private int steps = 250;
	
	private int step_counter =1;
	private final static Color black = Color.black;
	private final static Color white = Color.white;
	private JPanel board_p = new JPanel();
	private JPanel help_p = new JPanel();
	private JLabel steps_label= new JLabel();
	private JButton[][] squares;
	private JButton[][] help_squares;
	
	private int counter=0;
	private javax.swing.Timer timer;
	private int time_to_wait = 1000;
	


	
	private Automaton auto = new Automaton();
	
	public GuiAutomaton(){
		this.setLayout(new CardLayout());
		board_p.setLayout(new GridLayout(size, size));
		help_p.setLayout(new GridLayout(size, size));
		squares = new JButton[size][size];
		help_squares = new JButton[size][size];
		
		auto.initialize();
		createSquares(size);
		add(board_p, BorderLayout.CENTER);
		add(help_p, BorderLayout.CENTER);
		board_p.setVisible(true);
		help_p.setVisible(false);
		
		
	}
	
	public GuiAutomaton(int set_size, int set_steps, JLabel label){
		this.size=set_size;
		this.steps = set_steps;
		this.steps_label=label;
		auto = new Automaton(set_size, set_steps);
		this.setLayout(new CardLayout());
		board_p.setLayout(new GridLayout(size, size));
		help_p.setLayout(new GridLayout(size, size));
		squares = new JButton[size][size];
		help_squares = new JButton[size][size];
		
		createSquares(size);
		auto.initialize();	
		add(board_p, BorderLayout.CENTER);
		add(help_p, BorderLayout.CENTER);
		board_p.setVisible(true);
		help_p.setVisible(false);
		
	}
	
		
	public int getStepCounter(){
		return this.step_counter;
	}
	
	public void runSteps(){
		timer = new javax.swing.Timer(time_to_wait, new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        counter++;
		        if(counter==steps+1){
		            timer.stop();
		            counter=0;
		        }else{
		        	if(counter==0){
		        		  setGuiBoard();
		        	}
		        	else{
		        		if(counter%2 == 1){
		        			auto.oddStep();	
		        			steps_label.setText("Generation: "+counter);  
		        			setGuiBoard();
		        		}
		        		else{
		        			auto.evenStep();
		        			steps_label.setText("Generation: "+counter);
		        			setGuiBoard();
		        		}
		        	}
		        }
		    }
		});
		timer.setRepeats(true);
		timer.start();
	}
	
	public void runStepsWrapAround(){
		timer = new javax.swing.Timer(time_to_wait, new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        counter++;
		        if(counter==steps+1){
		            timer.stop();
		            counter=0;
		        }else{
		        	if(counter==0){
		        		  setGuiBoard();
		        	}
		        	else{
		        		if(counter%2 == 1){
		        			auto.oddStep();	
		        			steps_label.setText("Generation: "+counter);  
		        			setGuiBoard();
		        		}
		        		else{
		        			auto.evenStepWrapAround();
		        			steps_label.setText("Generation: "+counter);
		        			setGuiBoard();
		        		}
		        	}
		        }
		    }
		});
		timer.setRepeats(true);
		timer.start();
	}
	

	
	public Automaton getAuto(){
		return this.auto;
	}
	


	
	public void createSquares(int size){
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				JButton button = new JButton();
				button.setPreferredSize(new Dimension(10, 10));
				button.setEnabled(false);
				board_p.add(button);
				squares[i][j]=button;
			}
		}
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				JButton button = new JButton();
				button.setPreferredSize(new Dimension(10, 10));
				button.setEnabled(false);
				help_p.add(button);
				help_squares[i][j]=button;
			}
		}
	}
	
	public void incrementStep(){
		this.step_counter++;
	}
	public void setGuiBoard(){
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(auto.isAlive(i,j))
					squares[i][j].setBackground(black);
				else
					squares[i][j].setBackground(white);
			}
		}

	}
	
	
}


