/******************************************
* Marina Zolkin
* group 89-512-02
* ex1
******************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Menu extends JPanel{

	private static final long serialVersionUID = 1L;
	private WindowLook view;
	private JPanel center;
	private JPanel east;
	private JTextField stepsField;
	private JTextField sizeField;
	private int field_dim=20;
	private String default_size = "100";
	private String default_steps = "250";

	public Menu(WindowLook view){
		
		this.view = view;
		
		setLayout(new BorderLayout());
		
		center = new JPanel();
		center.setLayout(new GridLayout(14,1));
		center.setPreferredSize(new Dimension(260,260));
		center.setBackground(new Color(147,183,249));
		center.add(new JLabel());
		
		east = new JPanel();
		east.setBackground(new Color(147,183,249));
		east.setLayout(new GridLayout(12,1));
		
		createMenu();
	}

	private void createMenu() {
		
		createLabelOnPanel(center,"Enter the field size","Arial",Font.BOLD,14);	
		
		sizeField = new JTextField(field_dim);
		sizeField.setText(default_size);
		center.add(sizeField);

		createLabelOnPanel(center,"Enter the number of generations","Arial",Font.BOLD,14);	
		
		stepsField = new JTextField(field_dim);
		stepsField.setText(default_steps);
		center.add(stepsField);
		
		center.add(new JLabel());
		
		JButton startRegular = new JButton("Run regular automaton");
		startRegular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getInputText();
				view.startAutomatonRegular();
			}
		});
		center.add(startRegular);
		
		JButton startWrapAround = new JButton("Run wrap-around automaton");
		startWrapAround.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getInputText();
				view.startAutomatonWrapAround();
			}
		});
		center.add(startWrapAround);
		
		JButton behavior1 = new JButton("Interesting behavior 1");
		behavior1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getInputText();
				view.startBehaviorOne();
			}
		});
		center.add(behavior1);
		
		JButton behavior2 = new JButton("Interesting behavior 2");
		behavior2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getInputText();
				view.startBehaviorTwo();
			}
		});
		center.add(behavior2);
		
		JButton behavior3 = new JButton("Interesting behavior 3");
		behavior3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getInputText();
				view.startBehaviorThree();
			}
		});
		center.add(behavior3);
		
		JButton glider1 = new JButton("Glider 1");
		glider1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getInputText();
				view.startGliderOne();
			}
		});
		center.add(glider1);
		
		JButton glider2 = new JButton("Glider 2");
		glider2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getInputText();
				view.startGliderTwo();
			}
		});
		center.add(glider2);
		add(center,BorderLayout.WEST);
		
		createHeaderOnPanel(east,"Cellular Automaton");
		addBreak(east,2);	
		
		createLabelOnPanel(east,"Computational Biology","Lucida Calligraphy",Font.PLAIN,20);		
		createLabelOnPanel(east,"   assignment #1","Lucida Calligraphy",Font.PLAIN,20);		
		addBreak(east,2);
		
		createLabelOnPanel(east,"by:    Marina Zolkin","Lucida Calligraphy",Font.PLAIN,20);	
		createLabelOnPanel(east,"group 89-512-02","Lucida Calligraphy",Font.PLAIN,20);
		
		add(east, BorderLayout.CENTER);
	}
	
	public void createLabelOnPanel(JPanel panel,String str, String fontname, int font, int fontsize){
		JLabel label = new JLabel(str);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font(fontname, font, fontsize));
		panel.add(label);	
	}
	
	public void createHeaderOnPanel(JPanel panel,String str){
		JLabel label = new JLabel(str);
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
		panel.add(label);	
	}
	
	public void addBreak(JPanel panel, int num){
		for (int i=0; i<num; i++)
			panel.add(new JLabel());
	}
	
	public void getInputText(){
		String size = sizeField.getText();
		if(!size.isEmpty()){
			view.setSize(Integer.parseInt(size));
		}
		else
			view.setSize(100);
		String steps = stepsField.getText();
		if(!steps.isEmpty()){
			view.setSteps(Integer.parseInt(steps));
		}
		else
			view.setSteps(250);		
	}

}
