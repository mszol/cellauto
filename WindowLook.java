/******************************************
* Marina Zolkin
* group 89-512-02
* ex1
******************************************/

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class WindowLook extends JFrame {

	private static final long serialVersionUID = 1L;
	private CardLayout layout;
	private Menu menu;
	
	private int size;
	private int steps;
	
	public WindowLook() {
		this.menu = new Menu(this);
		this.layout = new CardLayout();
		setLayout(layout);
		add(menu, "menu");
		layout.show(getContentPane(), "menu");
	}


	public void setSize(int set_size) {
		this.size = set_size;
	}
	public void setSteps(int set_steps) {
		this.steps = set_steps;
	}

	public void startAutomatonRegular(){
		JFrame frame = new JFrame();	
		GuiAutomaton gui = setAutomatonOnFrame(frame);
		packFrame(frame,gui);
		gui.runSteps();
		
	}
	
	public void startAutomatonWrapAround(){
		JFrame frame = new JFrame();	
		GuiAutomaton gui = setAutomatonOnFrame(frame);
		packFrame(frame,gui);
		gui.runStepsWrapAround();
	}
	
	//All the cells of the board are set to black (wrap-around behavior)
	public void startBehaviorOne(){
		JFrame frame = new JFrame();	
		GuiAutomaton gui = setAutomatonOnFrame(frame);
		gui.getAuto().caseOne();
		packFrame(frame,gui);
		gui.runStepsWrapAround();
	}
	
	//All the cells of the board are set to black (Regular behavior)
	public void startBehaviorTwo(){
		JFrame frame = new JFrame();	
		GuiAutomaton gui = setAutomatonOnFrame(frame);
		gui.getAuto().caseOne();
		packFrame(frame,gui);
		gui.runSteps();
	}
	
	
	//initialize a diagonal to black (wrap-around behavior)
	
	public void startBehaviorThree(){
		JFrame frame = new JFrame();	
		GuiAutomaton gui = setAutomatonOnFrame(frame);
		gui.getAuto().caseThree();
		packFrame(frame,gui);
		gui.runStepsWrapAround();
	}
	
	//a middle square sized 1\3 of the field size is set to random values, the rest of the board is set to white 
	public void startGliderOne(){
		JFrame frame = new JFrame();	
		GuiAutomaton gui = setAutomatonOnFrame(frame);
		gui.getAuto().gliderOne();
		packFrame(frame,gui);
		gui.runStepsWrapAround();
	}
	
	//white board in black frame (gliders appear in the diagonals)
	public void startGliderTwo(){
		JFrame frame = new JFrame();	
		GuiAutomaton gui = setAutomatonOnFrame(frame);
		gui.getAuto().gliderTwo();
		packFrame(frame,gui);
		gui.runStepsWrapAround();
	}
	
	public GuiAutomaton setAutomatonOnFrame(JFrame frame){
		frame.setSize(100,100);
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.PAGE_AXIS));
		JLabel steps_label = new JLabel();
		steps_label.setHorizontalAlignment(JLabel.CENTER);
		steps_label.setText("Generation: 0");
		steps_label.setVisible(true);
		
		GuiAutomaton gui = new GuiAutomaton(size, steps, steps_label);
		frame.add(steps_label);
		frame.add(gui);
		return gui;
		
	}
	
	public void packFrame (JFrame frame,GuiAutomaton gui){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		gui.setGuiBoard();
	}
	
	public static void main(String[] arr){
		
		WindowLook game_window = new WindowLook();
		game_window.setPreferredSize(new Dimension(600, 500));
		game_window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		game_window.pack();
		game_window.setResizable(true);
		game_window.setLocationRelativeTo(null);
		game_window.setVisible(true);
		}
}
