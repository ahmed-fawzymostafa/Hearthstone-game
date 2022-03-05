package gameView;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import model.heroes.*;
import Controller.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class welcomeFrame extends JFrame implements ActionListener{
	
	private ButtonGroup firstHero;
	private ButtonGroup secondHero;
	public JRadioButton hunt,hunt2,war,war2,mage,mage2,pal,pal2,pri,pri2;
	public Hero one,two;
	private Clip clip;
	
	private String name1,name2;
	private gameView view;
	public boolean bol;
	public void setFirstHero(ButtonGroup firstHero) {
		this.firstHero = firstHero;
	}
	public void setSecondHero(ButtonGroup secondHero) {
		this.secondHero = secondHero;
	}
	public void setHunt(JRadioButton hunt) {
		this.hunt = hunt;
	}
	public void setHunt2(JRadioButton hunt2) {
		this.hunt2 = hunt2;
	}
	public void setWar(JRadioButton war) {
		this.war = war;
	}
	public void setWar2(JRadioButton war2) {
		this.war2 = war2;
	}
	public void setMage(JRadioButton mage) {
		this.mage = mage;
	}
	public void setMage2(JRadioButton mage2) {
		this.mage2 = mage2;
	}
	public void setPal(JRadioButton pal) {
		this.pal = pal;
	}
	public void setPal2(JRadioButton pal2) {
		this.pal2 = pal2;
	}
	public void setPri(JRadioButton pri) {
		this.pri = pri;
	}
	public void setPri2(JRadioButton pri2) {
		this.pri2 = pri2;
	}
	
	public String getName1() {
		return name1;
	}
	public String getName2() {
		return name2;
	}
	public welcomeFrame(){
		setVisible(true);
		setLocation(600,350);
		setSize(728,410);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon("BackGrounds/background.jpg")));
		setTitle("HEARTHSTONE");
		JOptionPane.showMessageDialog(this,"WELCOME TO HEARTHSTONE\nPLEASE CHOOSE YOUR HEROS");
		name1=JOptionPane.showInputDialog("Type First Player's Name Please");
		name2=JOptionPane.showInputDialog("Type Second Player's Name Please");
		
		
		
		
		//labels for heros
		JButton first=new JButton("First Player");
		JButton second= new JButton("Second Player");
		first.setBounds(280,0,200,50);
		first.setBorderPainted(false);
		first.setContentAreaFilled(false);
		this.add(first);
		first.disable();
		first.setFont(new Font("",Font.ITALIC,30));
		first.setForeground(Color.white);
		
		second.setBounds(250,125,250,50);
		second.setBorderPainted(false);
		second.setContentAreaFilled(false);
		this.add(second);
		second.disable();
		second.setFont(new Font("",Font.ITALIC,30));
		second.setForeground(Color.white);
		//radiobuttons for heroes
		hunt=new JRadioButton("Hunter");
		hunt.setForeground(Color.WHITE);
		hunt.setBounds(50,70,100,10);
		hunt.setContentAreaFilled(false);
		hunt.setActionCommand("hunt");
		this.add(hunt);
		
		
		mage=new JRadioButton("Mage");
		mage.setForeground(Color.WHITE);
		mage.setBounds(200,70,100,10);
		mage.setContentAreaFilled(false);
		mage.setActionCommand("mage");
		this.add(mage);
		
		pal=new JRadioButton("Paladin");
		pal.setForeground(Color.WHITE);
		pal.setBounds(350,70,100,10);
		pal.setContentAreaFilled(false);
		pal.setActionCommand("pal");
		this.add(pal);
		
		pri=new JRadioButton("Priest");
		pri.setForeground(Color.WHITE);
		pri.setBounds(500,70,100,10);
		pri.setContentAreaFilled(false);
		pri.setActionCommand("pri");
		this.add(pri);
		
		war=new JRadioButton("Warlock");
		war.setForeground(Color.WHITE);
		war.setBounds(650,70,100,10);
		war.setContentAreaFilled(false);
		war.setActionCommand("war");
		this.add(war);
		
		firstHero= new ButtonGroup();
		firstHero.add(hunt);firstHero.add(mage);firstHero.add(pal);firstHero.add(pri);firstHero.add(war);
		
		//add again radiobuttons
		hunt2=new JRadioButton("Hunter");
		hunt2.setForeground(Color.WHITE);
		hunt2.setBounds(50,200,100,10);
		hunt2.setContentAreaFilled(false);
		hunt2.setActionCommand("hunt2");
		this.add(hunt2);
		
		mage2=new JRadioButton("Mage");
		mage2.setForeground(Color.WHITE);
		mage2.setBounds(200,200,100,10);
		mage2.setContentAreaFilled(false);
		mage2.setActionCommand("mage2");
		this.add(mage2);
		
		pal2=new JRadioButton("Paladin");
		pal2.setForeground(Color.WHITE);
		pal2.setBounds(350,200,100,10);
		pal2.setContentAreaFilled(false);
		pal2.setActionCommand("pal2");
		this.add(pal2);
		
		pri2=new JRadioButton("Priest");
		pri2.setForeground(Color.WHITE);
		pri2.setBounds(500,200,100,10);
		pri2.setContentAreaFilled(false);
		pri2.setActionCommand("pri2");
		this.add(pri2);
		
		war2=new JRadioButton("Warlock");
		war2.setForeground(Color.WHITE);
		war2.setBounds(650,200,100,10);
		war2.setContentAreaFilled(false);
		war2.setActionCommand("war2");
		this.add(war2);
		
		secondHero= new ButtonGroup();
		secondHero.add(hunt2);secondHero.add(mage2);secondHero.add(pal2);secondHero.add(pri2);secondHero.add(war2);
		
		//add start game button
		JButton startGame=new JButton("Start Game");
		startGame.setFont(new Font("",Font.ITALIC,20));
		startGame.setBounds(280,300,200,60);
		startGame.setContentAreaFilled(false);
		startGame.setForeground(Color.WHITE);
		startGame.addActionListener(this);
		this.add(startGame);
		playSound("Sounds/intro.wav");
		revalidate();
		repaint();
	}
	public ButtonGroup getFirstHero() {
		return firstHero;
	}
	public ButtonGroup getSecondHero() {
		return secondHero;
	}
	
	

	public gameView getView() {
		return view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		
		if(((JButton)e.getSource()).getActionCommand().equals("Start Game")){
			if((hunt.isSelected()|mage.isSelected()|pri.isSelected()|pal.isSelected()|war.isSelected())&&(hunt2.isSelected()|mage2.isSelected()|pal2.isSelected()|pri2.isSelected()|war2.isSelected()))
		{
			
				if(firstHero.getSelection().getActionCommand().equals("hunt"))
					try {
						one=new Hunter();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				if(firstHero.getSelection().getActionCommand().equals("mage"))
					try {
						one=new Mage();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(firstHero.getSelection().getActionCommand().equals("pri"))
					try {
						one=new Priest();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(firstHero.getSelection().getActionCommand().equals("pal"))
					try {
						one=new Paladin();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(firstHero.getSelection().getActionCommand().equals("war"))
					try {
						one=new Warlock();
					} catch (IOException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				//Get second hero
				if(secondHero.getSelection().getActionCommand().equals("hunt2"))
					try {
						two=new Hunter();
					} catch (IOException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(secondHero.getSelection().getActionCommand().equals("mage2"))
					try {
						two=new Mage();
					} catch (IOException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(secondHero.getSelection().getActionCommand().equals("pri2"))
					try {
						two=new Priest();
					} catch (IOException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(secondHero.getSelection().getActionCommand().equals("pal2"))
					try {
						two=new Paladin();
					} catch (IOException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(secondHero.getSelection().getActionCommand().equals("war2"))
					try {
						two=new Warlock();
					} catch (IOException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			
				
				new Controller(one,two,name1,name2);
			//view=new gameView();
				
				//bol=true;
				this.dispose();
				clip.close();
			
		}
		else{
			JOptionPane.showMessageDialog((JButton)e.getSource(), "PLEASE CHOOSE THE HEROS WHO YOU PLAYS WITH");
			//bol=false;
		}
	}
	}
	public void playSound(String filePath) {
		try{
			AudioInputStream ais= AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
			clip = AudioSystem.getClip();
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
		new welcomeFrame();
	}
	
}
