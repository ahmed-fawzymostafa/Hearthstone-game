package gameView;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import Buttons.*;
import model.cards.minions.*;
import model.cards.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import model.cards.spells.*;
import Panels.*;

public class gameView extends JFrame{
	//private leftPanel left;
	//public JLabel lowerHand,upperHand,field;
	
	public gameView(){
		setBounds(0,10,2100,1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new JLabel(new ImageIcon("BackGrounds/new.jpg")));
		setLayout(null);
		setResizable(false);
		
		JLabel l1=new JLabel();
		l1.setBounds(1635,30,195,285);
		l1.setIcon(new ImageIcon("BackGrounds/cardback.png"));
		this.add(l1);
		JLabel l2=new JLabel();
		l2.setBounds(1635,650,195,285);
		l2.setIcon(new ImageIcon("BackGrounds/cardback.png"));
		this.add(l2);
		playSound("Sounds/gamemusic.wav");
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("Sounds/1214924.png");
		Cursor c = toolkit.createCustomCursor(image,new Point(this.getX(),this.getY()),"");
		this.setCursor(c);
		//Icehowl i=new Icehowl();
		//MinionPanels n=new MinionPanels(i.getCurrentHP(), i.getAttack(),i.getManaCost(),i.getRarity(),i.isTaunt(),!i.isSleeping(),i.isDivine() ,i.getImage(),(Minion)i );  
		//n.setBounds(230,740,140,200);
		//this.add(n);
		
		/*		//JLabel field=new JLabel(new ImageIcon("BackGrounds/field.jpg"));
		field.setBounds(300,250,1400,500);
		add(field);
		field.setLayout(null);
		
		upperHand=new JLabel(new ImageIcon("BackGrounds/hand.jpg"));
		upperHand.setBounds(300,0,1400,250);
		upperHand.setLayout(null);
		add(upperHand);
		
		lowerHand=new JLabel(new ImageIcon("BackGrounds/hand.jpg"));
		lowerHand.setBounds(300,750,1400,250);
		lowerHand.setLayout(null);
		this.add(lowerHand);
		
		left=new leftPanel();
		left.setBounds(0,0,300,1000);
		this.setLayout(null);
		add(left);
		
	/*	
		
		
		
		
		//JLabel l=new JLabel("ads");
		//l.setBounds(0,0,140,200);
		//lowerHand.add(l);
		/*Icehowl i=new Icehowl();
		MinionPanels n=new MinionPanels(i.getCurrentHP(), i.getAttack(),i.getManaCost(),i.getRarity(),i.isTaunt(),!i.isSleeping(),i.isDivine() ,i.getImage() );  
		n.setBounds(0,0,140,200);
		this.add(n);
		Polymorph m=new Polymorph();
		SpellPanels p=new SpellPanels(m.getManaCost(),m.getRarity(),m.getImage());
		p.setBounds(140,0,140,200);
		this.add(p);*/
		
	
		
		revalidate();
		repaint();
	}
	
	
	public void playSound(String filePath) {
		try{
			AudioInputStream ais= AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			clip.loop(1000);
		}
		catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	
	

	public static void main(String[] args) {
		new gameView();
	}
	
}
