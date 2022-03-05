package Buttons;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import model.cards.*;
public class playCard extends JButton{
	public Card card;
	public playCard(){
		setText("Play Card");
		setForeground(Color.WHITE);
		setFont(new Font("TimesRoman", Font.BOLD, 15));
		setContentAreaFilled(false);
		setBorderPainted(false);
		//this.card=card;

	}
}
