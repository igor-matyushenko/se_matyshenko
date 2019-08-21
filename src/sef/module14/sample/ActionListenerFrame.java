package sef.module14.sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = -3598311698673233314L;
	private JButton addButton;
	private JButton subtractButton;
	private JLabel countLabel;
	private int count=0;
	
	public ActionListenerFrame(){
		addButton = new JButton("Increment");
		subtractButton = new JButton("Decrement");
		countLabel = new JLabel("0");
		Container pane = getContentPane();
		FlowLayout flowLayout = new FlowLayout();
		
		pane.setLayout(flowLayout);
		pane.add(addButton); 
		pane.add(subtractButton); 
		pane.add(countLabel); 
		addButton.addActionListener(this);
		subtractButton.addActionListener(this);
		
		setLocation(300,300);
		setSize(400,100);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Инкремент и декримент");
	}
	
	public static void main(String arg[]){
		new ActionListenerFrame();
	}

	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == addButton){
			count++;
		}else if (event.getSource() == subtractButton){
			count--;
		}
		countLabel.setText(""+count);
		
	}
	
}
