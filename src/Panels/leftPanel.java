package Panels;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class leftPanel extends JLabel {
	JLabel selectedCard;
	JButton lowerPower , lowerEnd,upperPower,upperEnd;
	
	public leftPanel(){
		setLayout(null);
		this.setIcon(new ImageIcon("BackGrounds/leftrightpanel.jpg"));
		lowerPower=new JButton("HeroPower");
		lowerPower.setActionCommand("lower player");
		lowerPower.setBounds(60,800,150,50);
		lowerPower.setContentAreaFilled(false);
		lowerPower.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		lowerPower.setBorderPainted(false);
		lowerPower.setForeground(Color.WHITE);
		
		
		lowerEnd=new JButton("End Turn");
		lowerEnd.setActionCommand("lower player end");
		lowerEnd.setBounds(60,700,150,50);
		lowerEnd.setContentAreaFilled(false);
		lowerEnd.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		lowerEnd.setBorderPainted(false);
		lowerEnd.setForeground(Color.WHITE);
		
		upperPower=new JButton("Hero Power");
		upperPower.setActionCommand("upper player");
		upperPower.setBounds(60,100,150,50);
		upperPower.setContentAreaFilled(false);
		upperPower.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		upperPower.setBorderPainted(false);
		upperPower.setForeground(Color.WHITE);
		
		upperEnd=new JButton("End Turn");
		upperEnd.setActionCommand("upper player end");
		upperEnd.setBounds(60,200,150,50);
		upperEnd.setContentAreaFilled(false);
		upperEnd.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		upperEnd.setBorderPainted(false);
		upperEnd.setForeground(Color.WHITE);
		
		add(lowerPower);
		add(lowerEnd);
		add(upperPower);
		add(upperEnd);
		
		
	}

	public JLabel getSelectedCard() {
		return selectedCard;
	}

	public JButton getLowerPower() {
		return lowerPower;
	}

	public JButton getLowerEnd() {
		return lowerEnd;
	}

	public JButton getUpperPower() {
		return upperPower;
	}

	public JButton getUpperEnd() {
		return upperEnd;
	}

	
	
}
