package Controller;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import engine.*;
import exceptions.*;
import Buttons.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.cards.*;
import model.heroes.*;
import model.cards.minions.*;
import model.cards.spells.*;
import gameView.*;

public class Controller implements ActionListener ,  GameListener{
	//private gameView view;
	private Game model;
	//private welcomeFrame wel;
	private gameView view;
	private boolean fullHand1,fullHand2;
	private Card burned;
	private ArrayList<JPanel> handOne;
	private ArrayList<JPanel> handTwo;
	private ArrayList<MinionPanels> fieldOne,fieldTwo;
	private ArrayList<JButton> handOneButtons,handTwoButtons,fieldOneButtons,fieldTwoButtons;
	private Hero firstHero,secondHero;
	private int handX1,handX2;
	private Minion attacker,target;
	private JLabel attackerIcon,targetIcon;
	private JButton attack ,attackHero;
	private JButton selectedSummon;
	private playCard playCard;
	private JLabel selectedIcon;
	private int fieldX1,fieldX2;
	private Minion spellMinionTarget;
	private JButton hp1,hp2,mana1,mana2,deck1,deck2,name1,name2,powerOfFirst,powerOfSecond;
	private Card selectedSpell;
	private ArrayList<JButton> spellTargetCurrent,spellTargetOpponent;
	private JPopupMenu minions;
	private int index ;
	private String usingOfpower;
	private boolean clone;
	private int attackerIndex,targetIndex,spellTargetIndex,spellHand;
	private ArrayList<Integer> spelltargetss;
	private JLabel f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,k1,k2,k3,k4,k5,k6,k7,k8,k9,k10;
	
	public Controller(Hero first,Hero second,String player1,String player2) {
		
		view=new gameView();
		
		spellTargetIndex=0;
		spelltargetss=new ArrayList<>();
		
		attackerIndex=0;
		targetIndex=0;
		fullHand1=false;
		fullHand2=false;
		firstHero=first;
		secondHero=second;
		handOne=new ArrayList<>();
		handTwo=new ArrayList<>();
		handOneButtons=new ArrayList<>();
		handTwoButtons=new ArrayList<>();
		fieldOneButtons=new ArrayList<>();
		fieldTwoButtons=new ArrayList<>();
		fieldOne=new ArrayList<>();
		fieldTwo=new ArrayList<>();
		spellTargetCurrent=new ArrayList<>();
		spellTargetOpponent=new ArrayList<>();
		minions=new JPopupMenu();
		index=0;
		attackerIcon=new JLabel();
		targetIcon= new JLabel();
		attack=new JButton("Attack Minion");
		attack.setBounds(1650,550,230,50);
		attackHero=new JButton("Attack Opp Hero");
		attackHero.setBounds(1650,600,230,50);
		attack.addActionListener(this);
		attackHero.addActionListener(this);
		attack.setBorderPainted(false);
		attack.setContentAreaFilled(false);
		attack.setFont(new Font("",Font.PLAIN,20));
		attackHero.setFont(new Font("",Font.PLAIN,20));
		attackHero.setBorderPainted(false);
		attackHero.setContentAreaFilled(false);
		attacker=null;
		target=null;
		clone=false;
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	//	Image AM=toolkit.getImage("Sounds/clicker.png");
	//	Image AH=toolkit.getImage("Sounds/clicker.png");
	//	Image power1=toolkit.getImage("Sounds/clicker.png");
	//	Image power2=toolkit.getImage("Sounds/clicker.png");
	//	Image end1=toolkit.getImage("Sounds/clicker.png");
	//	Image end2=toolkit.getImage("Sounds/clicker.png");
	//	Cursor am = toolkit.createCustomCursor(AM,new Point(attack.getX(),attack.getY()),"");
	//	attack.setCursor(am);
	//	Cursor ah = toolkit.createCustomCursor(AH,new Point(attackHero.getX(),attackHero.getY()),"");
	//	attackHero.setCursor(ah);
		
		f1=new JLabel(new ImageIcon("Heros/backHand.png"));
		f2=new JLabel(new ImageIcon("Heros/backHand.png"));
		f3=new JLabel(new ImageIcon("Heros/backHand.png"));
		f4=new JLabel(new ImageIcon("Heros/backHand.png"));
		f5=new JLabel(new ImageIcon("Heros/backHand.png"));
		f6=new JLabel(new ImageIcon("Heros/backHand.png"));
		f7=new JLabel(new ImageIcon("Heros/backHand.png"));
		f8=new JLabel(new ImageIcon("Heros/backHand.png"));
		f9=new JLabel(new ImageIcon("Heros/backHand.png"));
		f10=new JLabel(new ImageIcon("Heros/backHand.png"));
		k1=new JLabel(new ImageIcon("Heros/backHand.png"));
		k2=new JLabel(new ImageIcon("Heros/backHand.png"));
		k3=new JLabel(new ImageIcon("Heros/backHand.png"));
		k4=new JLabel(new ImageIcon("Heros/backHand.png"));
		k5=new JLabel(new ImageIcon("Heros/backHand.png"));
		k6=new JLabel(new ImageIcon("Heros/backHand.png"));
		k7=new JLabel(new ImageIcon("Heros/backHand.png"));
		k8=new JLabel(new ImageIcon("Heros/backHand.png"));
		k9=new JLabel(new ImageIcon("Heros/backHand.png"));
		k10=new JLabel(new ImageIcon("Heros/backHand.png"));
		
		
		
		
		handX1=230;
		handX2=230;
		fieldX1=230;
		fieldX2=230;
		//wel=new welcomeFrame();
		
		
		try {
			model=new Game(firstHero,secondHero);
		} catch (FullHandException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.setListener(this);
		
		
		
		name1=new JButton("NAME:"+player1);
		name2=new JButton("NAME:"+player2);
		JButton heroname1=new JButton(firstHero.getName());
		JButton heroname2=new JButton(secondHero.getName());
		hp1=new JButton("HP:"+firstHero.getCurrentHP());
		hp2=new JButton("HP:"+secondHero.getCurrentHP());
		name1.setBounds(0,800,200,50);
		heroname1.setBounds(0,830,220,50);
		hp1.setBounds(0,860,200,50);
		name2.setBounds(0,0,200,50);
		heroname2.setBounds(0,30,220,50);
		hp2.setBounds(0,60,200,50);
		name1.setContentAreaFilled(false);
		name1.setBorderPainted(false);
		name2.setContentAreaFilled(false);
		name2.setBorderPainted(false);
		heroname1.setContentAreaFilled(false);
		heroname1.setBorderPainted(false);
		heroname2.setContentAreaFilled(false);
		heroname2.setBorderPainted(false);
		hp1.setContentAreaFilled(false);
		hp1.setBorderPainted(false);
		hp2.setContentAreaFilled(false);
		hp2.setBorderPainted(false);
		name1.setFont(new Font("TimesRoman", Font.BOLD, 20));
		name2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		heroname1.setFont(new Font("TimesRoman", Font.BOLD, 20));
		heroname2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		hp1.setFont(new Font("TimesRoman", Font.BOLD, 20));
		hp2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		mana1=new JButton("MANA:"+firstHero.getCurrentManaCrystals());
		mana2=new JButton("MANA:"+secondHero.getCurrentManaCrystals());
		mana1.setBounds(0,890,200,50);
		mana2.setBounds(0,90,200,50);
		mana1.setContentAreaFilled(false);
		mana2.setContentAreaFilled(false);
		mana1.setBorderPainted(false);
		mana2.setBorderPainted(false);
		mana1.setFont(new Font("TimesRoman", Font.BOLD, 20));
		mana2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		
		name1.setForeground(Color.BLUE);
		name2.setForeground(Color.BLUE);
		heroname1.setForeground(Color.BLUE);
		heroname2.setForeground(Color.BLUE);
		mana1.setForeground(Color.BLUE);
		mana2.setForeground(Color.BLUE);
		hp1.setForeground(Color.BLUE);
		hp2.setForeground(Color.BLUE);
	
		
		view.add(name1);
		view.add(name2);
		view.add(heroname1);
		view.add(heroname2);
		view.add(hp1);
		view.add(hp2);
		view.add(mana1);
		view.add(mana2);
		
		//JButton
		

		// play Card Button
		playCard=new playCard();
		//playCard.setBounds(40,600,150,50);
		//view.add(playCard);
		playCard.addActionListener(this);
		playCard.setBounds(40,525,150,50);
		playCard.setFont(new Font("", Font.BOLD, 20));
	//	Cursor pc=toolkit.createCustomCursor(AM,new Point(playCard.getX(),playCard.getY()), "");
	//	playCard.setCursor(pc);
		
		selectedIcon=new JLabel();
		
		
		//The Hands of the Two Players
		startingHand1();
		startingHand2();
		
		if(firstHero.equals(model.getCurrentHero())){
			//removing secondHero hand
			handX2=230;
			for (int i=0;i<handTwo.size();i++){
				view.remove(handTwo.get(i));
			}
			// putting back hands
			for(int i=0;i<secondHero.getHand().size();i++)
			{
				if(i==0){
					k1.setBounds(handX2,0,140,200);
					view.add(k1);
					handX2+=140;
				}
				if(i==1){
					k2.setBounds(handX2,0,140,200);
					view.add(k2);
					handX2+=140;
				}
				if(i==2){
					k3.setBounds(handX2,0,140,200);
					view.add(k3);
					handX2+=140;
				}
				if(i==3){
					k4.setBounds(handX2,0,140,200);
					view.add(k4);
					handX2+=140;
				}
				if(i==4){
					k5.setBounds(handX2,0,140,200);
					view.add(k5);
					handX2+=140;
				}
				if(i==5){
					k6.setBounds(handX2,0,140,200);
					view.add(k6);
					handX2+=140;
				}
				if(i==6){
					k7.setBounds(handX2,0,140,200);
					view.add(k7);
					handX2+=140;
				}
				if(i==7){
					k8.setBounds(handX2,0,140,200);
					view.add(k8);
					handX2+=140;
				}
				if(i==8){
					k9.setBounds(handX2,0,140,200);
					view.add(k9);
					handX2+=140;
				}
				if(i==9){
					k10.setBounds(handX2,0,140,200);
					view.add(k10);
					handX2+=140;
				}
				}
		}
		
		if(secondHero.equals(model.getCurrentHero())){
			//removing secondHero hand
			handX1=230;
			for (int i=0;i<handOne.size();i++){
				view.remove(handOne.get(i));
			}
			// putting back hands
			for(int i=0;i<firstHero.getHand().size();i++)
			{
				if(i==0){
					f1.setBounds(handX1,750,140,200);
					view.add(f1);
					handX1+=140;
				}
				if(i==1){
					f2.setBounds(handX1,750,140,200);
					view.add(f2);
					handX1+=140;
				}
				if(i==2){
					f3.setBounds(handX1,750,140,200);
					view.add(f3);
					handX1+=140;
				}
				if(i==3){
					f4.setBounds(handX1,750,140,200);
					view.add(f4);
					handX1+=140;
				}
				if(i==4){
					f5.setBounds(handX1,750,140,200);
					view.add(f5);
					handX1+=140;
				}
				if(i==5){
					f6.setBounds(handX1,750,140,200);
					view.add(f6);
					handX1+=140;
				}
				if(i==6){
					f7.setBounds(handX1,750,140,200);
					view.add(f7);
					handX1+=140;
				}
				if(i==7){
					f8.setBounds(handX1,750,140,200);
					view.add(f8);
					handX1+=140;
				}
				if(i==8){
					f9.setBounds(handX1,750,140,200);
					view.add(f9);
					handX1+=140;
				}
				if(i==9){
					f10.setBounds(handX1,750,140,200);
					view.add(f10);
					handX1+=140;
				}
				}
		}
		
		
		deck1=new JButton(firstHero.getDeck().size()+"");
		deck2=new JButton(secondHero.getDeck().size()+"");
		deck1.setContentAreaFilled(false);
		deck1.setBorderPainted(false);
		deck2.setContentAreaFilled(false);
		deck2.setBorderPainted(false);
		deck1.setFont(new Font("Helvetica", Font.PLAIN, 20));
		deck2.setFont(new Font("Helvetica", Font.PLAIN, 20));
		deck2.setBounds(1815,150,100,50);
		deck1.setBounds(1815,800,100,50);
		view.add(deck1);
		view.add(deck2);
		
		
		
		
		
		//end turn and useHero power
		JButton endOfFirst=new JButton("End Turn");endOfFirst.setActionCommand("endOfFirst");
		endOfFirst.setBounds(37,650,150,50);
		endOfFirst.setContentAreaFilled(false);
		endOfFirst.setFont(new Font("", Font.BOLD, 23));
		
		endOfFirst.setBorderPainted(false);
		endOfFirst.setForeground(Color.DARK_GRAY);
		endOfFirst.addActionListener(this);
		view.add(endOfFirst);
	
		powerOfFirst=new JButton("Hero Power");powerOfFirst.setActionCommand("powerOfFirst");
		powerOfFirst.setBounds(15,700,200,50);
		powerOfFirst.setContentAreaFilled(false);
		powerOfFirst.setFont(new Font("TimesRoman", Font.BOLD, 23));
		powerOfFirst.setBorderPainted(false);
		powerOfFirst.setForeground(Color.DARK_GRAY);
		powerOfFirst.addActionListener(this);
		view.add(powerOfFirst);
		
		JButton endOfSecond=new JButton("End Turn");endOfSecond.setActionCommand("endOfSecond");
		endOfSecond.setBounds(37,200,150,50);
		endOfSecond.setContentAreaFilled(false);
		endOfSecond.setFont(new Font("TimesRoman", Font.BOLD, 23));
		endOfSecond.setBorderPainted(false);
		endOfSecond.setForeground(Color.DARK_GRAY);
		endOfSecond.addActionListener(this);
		view.add(endOfSecond);
		
		powerOfSecond=new JButton("Hero Power");powerOfSecond.setActionCommand("powerOfSecond");
		powerOfSecond.setBounds(15,150,200,50);
		powerOfSecond.setContentAreaFilled(false);
		powerOfSecond.setFont(new Font("TimesRoman", Font.BOLD, 23));
		powerOfSecond.setBorderPainted(false);
		powerOfSecond.setForeground(Color.DARK_GRAY);
		powerOfSecond.addActionListener(this);
		view.add(powerOfSecond);
	
		/*	Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image cur = toolkit.getImage("Sounds/clicker.png");
		Cursor c = toolkit.createCustomCursor(cur,new Point(minionButton.getX(),minionButton.getY()),"");
		.setCursor(c);*/
		
		
//		Cursor pf = toolkit.createCustomCursor(power1,new Point(powerOfFirst.getX(),powerOfFirst.getY()),"");
//		powerOfFirst.setCursor(pf);
		
//		Cursor ps = toolkit.createCustomCursor(power2,new Point(powerOfSecond.getX(),powerOfSecond.getY()),"");
//		powerOfSecond.setCursor(ps);
		
//		Cursor e1 = toolkit.createCustomCursor(end1,new Point(endOfFirst.getX(),endOfFirst.getY()),"");
//		endOfFirst.setCursor(e1);
		
//		Cursor e2 = toolkit.createCustomCursor(end2,new Point(endOfSecond.getX(),endOfSecond.getY()),"");
//		endOfSecond.setCursor(e2);
		
		
		view.revalidate();
		view.repaint();
		
	
	}
	
	
	
	
	
	//public gameView getView() {
	//	return view;
	//}





	public Game getModel() {
		return model;
	}









	public ArrayList<JPanel> getHandOne() {
		return handOne;
	}





	public ArrayList<JPanel> getHandTwo() {
		return handTwo;
	}





	public int getHandX1() {
		return handX1;
	}





	public int getHandX2() {
		return handX2;
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource(); 
		if(b.getActionCommand().equals("firstHero")|b.getActionCommand().equals("firstHeroSpell")){
			if(firstHero.equals(model.getCurrentHero()))
			{
				selectedSummon=b;
				//if(selectedSummon==null)
					//selectedSummon=b;
				int index=handOneButtons.indexOf(b);
				Card card=firstHero.getHand().get(index);
				
				
				view.add(playCard);
				//adding aphoto
				JPanel photo=handOne.get(index);
				if(photo instanceof MinionPanels)
				{	
					ImageIcon i=((MinionPanels)photo).getImage();
					selectedIcon.setIcon(i);
					selectedIcon.setBounds(50,375,140,145);
					view.add(selectedIcon);
				}
				else//if(photo instanceof SpellPanels)
				{	
					//System.out.println("balabizo1");
					ImageIcon is=((SpellPanels)photo).getImage();
					selectedIcon.setIcon(is);
					selectedIcon.setBounds(50,375,140,145);
					view.add(selectedIcon);
				}
				view.revalidate();
				view.repaint();
				
			}
				
		}
		else if(b.getActionCommand().equals("secondHero")|b.getActionCommand().equals("secondHeroSpell"))
					if(secondHero.equals(model.getCurrentHero()))
					{
						
						selectedSummon=b;
						
						//System.out.println("second");
						int index=handTwoButtons.indexOf(b);
						Card card=secondHero.getHand().get(index);
						
						
						//playCard.setBounds(40,600,150,50);
						view.add(playCard);
						//adding aphoto
						JPanel photo=handTwo.get(index);
						if(photo instanceof MinionPanels)
						{	selectedSummon=((MinionPanels) handTwo.get(index)).getMinionButton();
							ImageIcon i=((MinionPanels)photo).getImage();
							selectedIcon.setIcon(i);
							selectedIcon.setBounds(50,375,140,145);
							view.add(selectedIcon);
						}
						else//if(photo instanceof SpellPanels)
						{	
							selectedSummon=((SpellPanels) handTwo.get(index)).getSpellButton();
							//System.out.println("balabizo");
							ImageIcon is=((SpellPanels)photo).getImage();
							selectedIcon.setIcon(is);
							selectedIcon.setBounds(50,375,140,145);
							view.add(selectedIcon);
						}
						view.revalidate();
						view.repaint();	
					}
		
		
		//Hero Power simulation
		if(b.getActionCommand().equals("powerOfFirst")&&firstHero.equals(model.getCurrentHero())){
			if(firstHero instanceof Mage){
				minions=new JPopupMenu();
				JButton oppMins=new JButton("Opponent Minions");
				if(secondHero.getField().size()!=0)
					minions.add(oppMins);
				oppMins.disable();
				for(int i=0;i<secondHero.getField().size();i++){
					Minion m=secondHero.getField().get(i);
					JButton tmp=new JButton(m.getName());
					tmp.setActionCommand("spellMinionTargetOpponent"+i);
					tmp.addActionListener(this);
					minions.add(tmp);
				}
				JButton oppHero=new JButton("1 Damage To Opp Hero");
				oppHero.setActionCommand("MageHeroPower");
				oppHero.addActionListener(this);
				minions.add(oppHero);
				minions.show(powerOfFirst,0,0);
				usingOfpower="Mage";
				
			}
			if(firstHero instanceof Priest){
				minions=new JPopupMenu();
				JButton myMins=new JButton("My Minions");
				if(firstHero.getField().size()!=0)
					minions.add(myMins);
				myMins.disable();	
			
			for(int i=0;i<firstHero.getField().size();i++){
				Minion m=firstHero.getField().get(i);
				JButton tmp=new JButton(m.getName());
				tmp.setActionCommand("spellMinionTargetCurrent"+i);
				tmp.addActionListener(this);
				minions.add(tmp);
			}
			JButton myHero=new JButton("Restore 2 HP To Hero");
			myHero.setActionCommand("PriestHeroPower");
			myHero.addActionListener(this);
			minions.add(myHero);
			minions.show(powerOfFirst,0,0);
			usingOfpower="Priest";
		}
			if(firstHero instanceof Warlock){
				try {
					((Warlock)firstHero).useHeroPower();
					addingEndTurn2();
					mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
					hp1.setText("HP:"+firstHero.getCurrentHP());
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				}
				
			}
			if(firstHero instanceof Paladin){
				try {
					((Paladin)firstHero).useHeroPower();
					Minion minion=firstHero.getField().get(firstHero.getField().size()-1);
					MinionPanels mp=new MinionPanels(minion.getCurrentHP(),minion.getAttack(),minion.getManaCost()
							,minion.getRarity(),minion.isTaunt(),!minion.isSleeping(), minion.isDivine(),minion.getImage(), minion);
					fieldOne.add(mp);
					fieldOneButtons.add(mp.getMinionButton());
					mp.getMinionButton().addActionListener(this);
					mp.getMinionButton().setActionCommand("firstHeroField");
					mp.setBounds(fieldX1,500,140,200);
					fieldX1+=140;
					mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
					view.add(mp);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
					}
			}
			if(firstHero instanceof Hunter){
				try {
					((Hunter)firstHero).useHeroPower();
					mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
					hp2.setText("HP:"+secondHero.getCurrentHP());
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				}
			}
		
		}
		if(b.getActionCommand().equals("powerOfSecond")&&secondHero.equals(model.getCurrentHero())){
			if(secondHero instanceof Mage){
				minions=new JPopupMenu();
				JButton oppMins=new JButton("Opponent Minions");
				if(firstHero.getField().size()!=0)
					minions.add(oppMins);
				oppMins.disable();
				for(int i=0;i<firstHero.getField().size();i++){
					Minion m=firstHero.getField().get(i);
					JButton tmp=new JButton(m.getName());
					tmp.setActionCommand("spellMinionTargetOpponent"+i);
					tmp.addActionListener(this);
					minions.add(tmp);
				}
				JButton oppHero=new JButton("1 Damage To Opp Hero");
				oppHero.setActionCommand("MageHeroPower");
				oppHero.addActionListener(this);
				minions.add(oppHero);
				minions.show(powerOfSecond,0,0);
				usingOfpower="Mage";
			}
			
			if(secondHero instanceof Priest){
				minions=new JPopupMenu();
				JButton myMins=new JButton("My Minions");
				if(secondHero.getField().size()!=0)
					minions.add(myMins);
				myMins.disable();
				for(int i=0;i<secondHero.getField().size();i++){
					Minion m=secondHero.getField().get(i);
					JButton tmp=new JButton(m.getName());
					tmp.setActionCommand("spellMinionTargetCurrent"+i);
					tmp.addActionListener(this);
					minions.add(tmp);
				}
				JButton myHero=new JButton("Restore 2 HP To Hero");
				myHero.setActionCommand("PriestHeroPower");
				myHero.addActionListener(this);
				minions.add(myHero);
				minions.show(powerOfSecond,0,0);
				usingOfpower="Priest";
			}
			if(firstHero instanceof Warlock){
				try {
					((Warlock)secondHero).useHeroPower();
					addingEndTurn1();
					mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
					hp2.setText("HP:"+secondHero.getCurrentHP());
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				}
				
			}
			if(secondHero instanceof Paladin){
				try {
					((Paladin)secondHero).useHeroPower();
					Minion minion=secondHero.getField().get(secondHero.getField().size()-1);
					MinionPanels mp=new MinionPanels(minion.getCurrentHP(),minion.getAttack(),minion.getManaCost()
							,minion.getRarity(),minion.isTaunt(),!minion.isSleeping(), minion.isDivine(),minion.getImage(), minion);
					fieldTwo.add(mp);
					fieldTwoButtons.add(mp.getMinionButton());
					mp.getMinionButton().addActionListener(this);
					mp.getMinionButton().setActionCommand("secondHeroField");
					mp.setBounds(fieldX2,250,140,200);
					fieldX2+=140;
					mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
					view.add(mp);
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
					}
			}
			if(secondHero instanceof Hunter){
				try {
					((Hunter)secondHero).useHeroPower();
					mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
					hp1.setText("HP:"+firstHero.getCurrentHP());
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				}
			}
		}

		//playing a card for the firstHero
		if(b.getActionCommand().equals("Play Card")){
			if(firstHero.equals(model.getCurrentHero()))
			{
				index=handOneButtons.indexOf(selectedSummon);
				Object c=firstHero.getHand().get(index);
				
				
				if(c instanceof Minion){
					try {
						MinionPanels removed=(MinionPanels)handOne.get(index);
						firstHero.playMinion((Minion)c);
						
						Minion m=(Minion)c;
						MinionPanels newM=new MinionPanels(m.getCurrentHP(),m.getAttack(),m.getManaCost()
								,m.getRarity(),m.isTaunt(),!m.isSleeping(),m.isDivine(),m.getImage(),m);
						newM.setBounds(fieldX1,500,140,200);
						newM.getMinionButton().setActionCommand("firstHeroField");
						newM.getMinionButton().addActionListener(this);
						fieldOne.add(newM);
						fieldOneButtons.add(newM.getMinionButton());
						
						
						handOne.remove(index);
						handOneButtons.remove(index);
						//reputting panels after playing a Minion
						mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
						view.remove(removed);
						view.remove(playCard);
						view.remove(selectedIcon);
						puttingHand1();
						puttingField1();
						playSound("Sounds/playMinion.wav");
						view.add(newM);
					} catch (NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e1.getMessage());
					}catch(NotYourTurnException e1){
						JOptionPane.showMessageDialog(view,e1.getMessage());
					}catch(FullFieldException e1){
						JOptionPane.showMessageDialog(view,e1.getMessage());
					}

				}
				else{
					
					if(c instanceof CurseOfWeakness)
						try {
							
							
							firstHero.castSpell((CurseOfWeakness)c, secondHero.getField());
							SpellPanels removed=(SpellPanels)handOne.get(index);
							handOne.remove(index);
							handOneButtons.remove(index);
							view.remove(playCard);
							view.remove(selectedIcon);
							view.remove(removed);
							puttingField2();
							puttingHand1();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							playSound("Sounds/playMinion.wav");
							
							
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					if(c instanceof DivineSpirit){
						if(firstHero.getCurrentManaCrystals()>=((DivineSpirit)c).getManaCost())
						{minions=new JPopupMenu();
						
						if(firstHero.getField().size()!=0)
					{
						
						JButton current=new JButton("My Minions");
						minions.add(current);
						current.disable();
						
						for(int i=0;i<firstHero.getField().size();i++){
							Minion m=firstHero.getField().get(i);
							JButton tmp=new JButton(m.getName());
							tmp.setActionCommand("spellMinionTargetCurrent"+i);
							tmp.addActionListener(this);
							minions.add(tmp);
						}
						
						
						minions.show(playCard,0,0);
						selectedSpell=(Card)c;
						view.remove(playCard);
						view.remove(selectedIcon);
						spellHand=index;
					}else
						JOptionPane.showMessageDialog(view,"Field Is Empty");
				}else
					JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					}
					if(c instanceof Flamestrike)
						try {
							for(int i=0;i<secondHero.getField().size();i++){
								Minion m=secondHero.getField().get(i);
								if(m.getCurrentHP()<=4&&!m.isDivine())
									spelltargetss.add(i);
							}
							
							firstHero.castSpell((Flamestrike)c, model.getOpponent().getField());
							//removing spell from hand from the view
							for(int i=0;i<spelltargetss.size();i++){
								int z=spelltargetss.get(i);
								fieldTwoButtons.remove(z);
								for(int j=i;j<spelltargetss.size();j++){
									spelltargetss.set(j,spelltargetss.get(j)-1);
								}
							}
							spelltargetss=new ArrayList<>();
							SpellPanels removed=(SpellPanels)handOne.get(index);
							view.remove(removed);
							//removing its button and panel from lists
							handOne.remove(index);
							handOneButtons.remove(index);
							//reputting hands and fields
							puttingHand1();
							puttingField2();
							puttingField1();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							view.remove(playCard);
							view.remove(selectedIcon);
							playSound("Sounds/playMinion.wav");
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					if(c instanceof HolyNova)
						try {
							for(int i=0;i<secondHero.getField().size();i++){
								Minion m=secondHero.getField().get(i);
								if(!m.isDivine()&&m.getCurrentHP()<=2)
									spelltargetss.add(i);
							}
							firstHero.castSpell((HolyNova)c, secondHero.getField());
						
							for(int i=0;i<spelltargetss.size();i++){
								int z=spelltargetss.get(i);
								fieldTwoButtons.remove(z);
								for(int j=i;j<spelltargetss.size();j++){
									spelltargetss.set(j,spelltargetss.get(j)-1);
								}
							}
							spelltargetss=new ArrayList<>();
							SpellPanels removed=(SpellPanels)handOne.get(index);
							view.remove(removed);
							handOne.remove(index);
							handOneButtons.remove(index);
							puttingHand1();
							puttingField1();
							puttingField2();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							view.remove(playCard);
							view.remove(selectedIcon);
							playSound("Sounds/playMinion.wav");
							
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					if(c instanceof KillCommand){
						if(firstHero.getCurrentManaCrystals()>=((KillCommand)c).getManaCost())
						{minions=new JPopupMenu();
						JButton killCommandHero=new JButton("3 Damage To Opp Hero");
						killCommandHero.setActionCommand("killCommandHero");
						killCommandHero.addActionListener(this);
						minions.add(killCommandHero);

							if(secondHero.getField().size()!=0){
								
							 	JButton myMinions=new JButton("Opponent Minions");
							 	myMinions.disable();
							 	minions.add(myMinions);
							 	for(int i=0;i<secondHero.getField().size();i++)
								{	
									Minion m=secondHero.getField().get(i);
									JButton tmp=new JButton(m.getName());
									tmp.setActionCommand("spellMinionTargetOpponent"+i);
									tmp.addActionListener(this);
									minions.add(tmp);
								}
						}
					
							minions.show(playCard,0,0);
							selectedSpell=(Card)c;
							view.remove(playCard);
							view.remove(selectedIcon);
							spellHand=index;
						
					
							
						}
						else
							JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					
					}
					
					if(c instanceof LevelUp ){
						try {
							firstHero.castSpell((LevelUp)c);
							SpellPanels removed=(SpellPanels)handOne.get(index);
							view.remove(removed);
							handOne.remove(index);
							handOneButtons.remove(index);
							puttingHand1();
							puttingField1();
							puttingField2();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							view.remove(playCard);
							view.remove(selectedIcon);
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					}
					if(c instanceof MultiShot){
						try {
							boolean flagg=false;
							if(secondHero.getField().size()==1)
							{	flagg=true;
								spellTargetIndex=0;
							
							}else
								for(int i=0;i<secondHero.getField().size();i++){
									Minion m=secondHero.getField().get(i);
									if(!m.isDivine()&&m.getCurrentHP()<=3){
										spelltargetss.add(i);
								
									}
									}
								
							firstHero.castSpell((MultiShot)c, secondHero.getField());
						if(flagg&&secondHero.getField().size()==0){
							fieldTwoButtons.remove(spellTargetIndex);
							
						}
						else{
							int i1=((MultiShot)c).getShotTarget1();
							int i2=((MultiShot)c).getShotTarget2();
							for(int i=0;i<spelltargetss.size();i++){
								if(i1==spelltargetss.get(i)){
									int z=spelltargetss.get(i);
									fieldTwoButtons.remove(z);
									for(int j=i;j<spelltargetss.size();j++){
									spelltargetss.set(j,spelltargetss.get(j)-1);
									}
									i1=-5;
									i2--;
								}
								if(i2==spelltargetss.get(i)){
									int z=spelltargetss.get(i);
									fieldTwoButtons.remove(z);
									for(int j=i;j<spelltargetss.size();j++){
									spelltargetss.set(j,spelltargetss.get(j)-1);
									}
									i2=-5;
									i1--;
								}
							}
						}
							spelltargetss=new ArrayList<>();
							spellTargetIndex=-5;
							
							SpellPanels removed=(SpellPanels)handOne.get(index);
							view.remove(removed);
							handOne.remove(index);
							handOneButtons.remove(index);
							puttingHand1();
							puttingField2();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							view.remove(playCard);
							view.remove(selectedIcon);
							playSound("Sounds/playMinion.wav");
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					}
					
					if(c instanceof Polymorph){
						if(firstHero.getCurrentManaCrystals()>=((Polymorph)c).getManaCost()){
							minions=new JPopupMenu();
							if(secondHero.getField().size()!=0)
						{
						
						JButton opponent= new JButton("Opponent Minions");
						minions.add(opponent);
						opponent.disable();
						
						for(int i=0;i<(secondHero.getField().size());i++)
						{	
							Minion m=secondHero.getField().get(i);
							JButton tmp=new JButton(m.getName());
							tmp.setActionCommand("spellMinionTargetOpponent"+i);
							tmp.addActionListener(this);
							
							minions.add(tmp);
						}
						
						minions.show(playCard,0,0);
						selectedSpell=(Card)c;
						view.remove(playCard);
						view.remove(selectedIcon);
						spellHand=index;
						}
						else
							JOptionPane.showMessageDialog(view,"Field Is Empty");
						}
					else
						JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					}	
					if(c instanceof Pyroblast){
						if(firstHero.getCurrentManaCrystals()>=((Pyroblast)c).getManaCost())
						{minions=new JPopupMenu();
							if(secondHero.getField().size()!=0)
							{
						JButton oppMinions=new JButton("Opponent Minions");
						minions.add(oppMinions);
						oppMinions.disable();
						for(int i=0;i<secondHero.getField().size();i++)
						{	
							Minion m=secondHero.getField().get(i);
							JButton tmp=new JButton(m.getName());
							tmp.setActionCommand("spellMinionTargetOpponent"+i);
							tmp.addActionListener(this);
							minions.add(tmp);
						}
							}
						JButton herotarget=new JButton("10 Damage To Opp Hero");
						herotarget.addActionListener(this);
						herotarget.setActionCommand("PyroblastHeroTarget");
						minions.add(herotarget);
						minions.show(playCard,0,0);
						selectedSpell=(Card)c;
						view.remove(playCard);
						view.remove(selectedIcon);
						spellHand=index;//hena aho
					
						}
						else
							JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
							
						
					}
					if(c instanceof SealOfChampions){
						if(firstHero.getCurrentManaCrystals()>=((SealOfChampions)c).getManaCost())
						{
							minions=new JPopupMenu();
							if(firstHero.getField().size()!=0)
						{
						
						JButton myMinions=new JButton("My Minions");
						minions.add(myMinions);
						myMinions.disable();
						for(int i=0;i<firstHero.getField().size();i++){
							Minion m=firstHero.getField().get(i);
							JButton tmp=new JButton(m.getName());
							tmp.setActionCommand("spellMinionTargetCurrent"+i);
							tmp.addActionListener(this);
							minions.add(tmp);
						}
						
						minions.show(playCard,0,0);
						selectedSpell=(Card)c;
						view.remove(playCard);
						view.remove(selectedIcon);
						spellHand=index;
						
						}else
							JOptionPane.showMessageDialog(view,"Field Is Empty");
							
						
					}else
						JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
						
					}
					if(c instanceof ShadowWordDeath){
						if(firstHero.getCurrentManaCrystals()>=((ShadowWordDeath)c).getManaCost())
						{
							minions=new JPopupMenu();
							if(secondHero.getField().size()!=0)
							{
						JButton oppMinions=new JButton("Opponent Minions");
						minions.add(oppMinions);
						oppMinions.disable();
						for(int i=0;i<secondHero.getField().size();i++)
						{	
							Minion m=secondHero.getField().get(i);
							JButton tmp=new JButton(m.getName());
							tmp.setActionCommand("spellMinionTargetOpponent"+i);
							tmp.addActionListener(this);
							minions.add(tmp);
						}
						minions.show(playCard,0,0);
						selectedSpell=(Card)c;
						view.remove(playCard);
						view.remove(selectedIcon);
						spellHand=index;
							}
							else
								JOptionPane.showMessageDialog(view,"Field Is Empty");
					}else
						JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
						
					}
					if(c instanceof SiphonSoul){
						if(firstHero.getCurrentManaCrystals()>=((SiphonSoul)c).getManaCost())
						{
							minions=new JPopupMenu();
							if(secondHero.getField().size()!=0)
					{	
							 	JButton myMinions=new JButton("Opponent Minions");
							 	minions.add(myMinions);
							 	for(int i=0;i<secondHero.getField().size();i++)
								{	
									Minion m=secondHero.getField().get(i);
									JButton tmp=new JButton(m.getName());
									tmp.setActionCommand("spellMinionTargetOpponent"+i);
									tmp.addActionListener(this);
									minions.add(tmp);
								}
						
							minions.show(playCard,0,0);
							selectedSpell=(Card)c;		
							view.remove(playCard);
							view.remove(selectedIcon);
							spellHand=index;
					}
							else
								JOptionPane.showMessageDialog(view,"Field Of Enemy Is Empty");
						}
						else
							JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					
					}
						
					if(c instanceof TwistingNether){
						try {
							firstHero.castSpell((TwistingNether)c, secondHero.getField());
							SpellPanels removed=(SpellPanels)handOne.get(index);
							handOne.remove(index);
							handOneButtons.remove(index);
							view.remove(playCard);
							view.remove(selectedIcon);
							view.remove(removed);
							for(int i=0;i<fieldOne.size();i++){
								view.remove(fieldOne.get(i));
							}
							for(int i=0;i<fieldTwo.size();i++){
								view.remove(fieldTwo.get(i));
							}
							
							fieldOne=new ArrayList<>();
							fieldOneButtons=new ArrayList<>();
							fieldTwo=new ArrayList<>();
							fieldTwoButtons=new ArrayList<>();
							puttingField2();
							puttingHand1();
							puttingField1();
							
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					}
				}
			}
			view.revalidate();
			view.repaint();
			
			
			
		}
		
		if(b.getActionCommand().equals("Play Card")){
			if(secondHero.equals(model.getCurrentHero()))
			{
				index=handTwoButtons.indexOf(selectedSummon);
				Object c=secondHero.getHand().get(index);
				
				
				if(c instanceof Minion){
					try {
						MinionPanels removed=(MinionPanels)handTwo.get(index);
						secondHero.playMinion((Minion)c);
						
						Minion m=(Minion)c;
						MinionPanels newM=new MinionPanels(m.getCurrentHP(),m.getAttack(),m.getManaCost()
								,m.getRarity(),m.isTaunt(),!m.isSleeping(),m.isDivine(),m.getImage(),m);
						newM.setBounds(fieldX2,250,140,200);
						//add minionpanel to field arraylist
						newM.getMinionButton().setActionCommand("secondHeroField");
						newM.getMinionButton().addActionListener(this);
						fieldTwo.add(newM);
						fieldTwoButtons.add(newM.getMinionButton()); 
						
						
						handTwo.remove(index);
						handTwoButtons.remove(index);
						
						mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
						
						view.remove(removed);
						view.remove(playCard);
						view.remove(selectedIcon);
						
						puttingHand2();
						puttingField2();
						view.add(newM);
						playSound("Sounds/playMinion2.wav");
						
					
				}	catch (NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e1.getMessage());
					}catch(NotYourTurnException e1){
						JOptionPane.showMessageDialog(view,e1.getMessage());
					}catch(FullFieldException e1){
						JOptionPane.showMessageDialog(view,e1.getMessage());
					}
					
				}
				else{
					
					if(c instanceof CurseOfWeakness)
						try {
							secondHero.castSpell((CurseOfWeakness)c, firstHero.getField());
							SpellPanels removed=(SpellPanels)handTwo.get(index);
							handTwo.remove(index);
							handTwoButtons.remove(index);
							view.remove(removed);
							view.remove(playCard);
							view.remove(selectedIcon);
							puttingHand2();
							puttingField1();
							puttingField2();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							playSound("Sounds/playMinion2.wav");
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					if(c instanceof DivineSpirit)
					{
						if(secondHero.getCurrentManaCrystals()>=((DivineSpirit)c).getManaCost())
						{
							minions=new JPopupMenu();
						if(secondHero.getField().size()!=0)
					{
						
						JButton current=new JButton("My Minions");
						minions=new JPopupMenu();
						minions.add(current);
						current.disable();
						
						for(int i=0;i<(secondHero.getField().size());i++)
						{	
							Minion m=secondHero.getField().get(i);
							JButton tmp=new JButton(m.getName());
							tmp.setActionCommand("spellMinionTargetCurrent"+i);
							tmp.addActionListener(this);
							minions.add(tmp);
						}
						
						minions.show(playCard,120,0);
						selectedSpell=(Card)c;
						view.remove(playCard);
						view.remove(selectedIcon);
						spellHand=index;
					}else
						JOptionPane.showMessageDialog(view,"Field Is Empty");
					}else
						JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					}
					if(c instanceof Flamestrike)
						try {
							
							for(int i=0;i<firstHero.getField().size();i++){
								Minion m=firstHero.getField().get(i);
								if(m.getCurrentHP()<=4&&!m.isDivine())
									spelltargetss.add(i);
							}
							
							secondHero.castSpell((Flamestrike)c, model.getOpponent().getField());
							for(int i=0;i<spelltargetss.size();i++){
								int z=spelltargetss.get(i);
								fieldOneButtons.remove(z);
								for(int j=i;j<spelltargetss.size();j++){
									spelltargetss.set(j,spelltargetss.get(j)-1);
								}
							}
							spelltargetss=new ArrayList<>();
							SpellPanels removed=(SpellPanels)handTwo.get(index);
							view.remove(removed);
							
							handTwo.remove(index);
							handTwoButtons.remove(index);
							puttingField1();
							puttingField2();
							puttingHand2();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							view.remove(playCard);
							view.remove(selectedIcon);
							playSound("Sounds/playMinion2.wav");
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					if(c instanceof HolyNova)
						try {
							for(int i=0;i<firstHero.getField().size();i++){
								Minion m=firstHero.getField().get(i);
								if(!m.isDivine()&&m.getCurrentHP()<=2)
									spelltargetss.add(i);
							}
							
							secondHero.castSpell((HolyNova)c, firstHero.getField());
							for(int i=0;i<spelltargetss.size();i++){
								int z=spelltargetss.get(i);
								fieldOneButtons.remove(z);
								for(int j=i;j<spelltargetss.size();j++){
									spelltargetss.set(j,spelltargetss.get(j)-1);
								}
							}
							spelltargetss=new ArrayList<>();
							SpellPanels removed=(SpellPanels)handTwo.get(index);
							view.remove(removed);
							handTwo.remove(index);
							handTwoButtons.remove(index);
							puttingField1();
							puttingField2();
							puttingHand2();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							view.remove(playCard);
							view.remove(selectedIcon);
							playSound("Sounds/playMinion2.wav");
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					if(c instanceof KillCommand)
					{
						if(secondHero.getCurrentManaCrystals()>=((KillCommand)c).getManaCost())
						{	minions=new JPopupMenu();
							JButton killCommandHero=new JButton("3 Damage To Opp Hero");
							killCommandHero.setActionCommand("killCommandHero");
							killCommandHero.addActionListener(this);
							
							if(firstHero.getField().size()!=0){
								
							 	JButton myMinions=new JButton("Opponent Minions");
							 	minions.add(myMinions);
							 	for(int i=0;i<firstHero.getField().size();i++)
								{	
									Minion m=firstHero.getField().get(i);
									JButton tmp=new JButton(m.getName());
									tmp.setActionCommand("spellMinionTargetOpponent"+i);
									tmp.addActionListener(this);
									minions.add(tmp);
								}
						}
						
							minions.add(killCommandHero);
							minions.show(playCard,0,0);
							selectedSpell=(Card)c;
							view.remove(playCard);
							view.remove(selectedIcon);
							spellHand=index;
					
					
					}else
						JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					}
					if(c instanceof LevelUp ){
						try {
							secondHero.castSpell((LevelUp)c);
							SpellPanels removed=(SpellPanels)handTwo.get(index);
							view.remove(removed);
							handTwo.remove(index);
							handTwoButtons.remove(index);
							puttingField1();
							puttingField2();
							puttingHand2();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							view.remove(playCard);
							view.remove(selectedIcon);
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					}
					if(c instanceof MultiShot){
						try {
							boolean flagg=false;
							if(firstHero.getField().size()==1)
							{	flagg=true;
								spellTargetIndex=0;
							
							}else
								for(int i=0;i<firstHero.getField().size();i++){
									Minion m=firstHero.getField().get(i);
									if(!m.isDivine()&&m.getCurrentHP()<=3){
										spelltargetss.add(i);
								
									}
									}
								
							secondHero.castSpell((MultiShot)c, firstHero.getField());
						if(flagg&&firstHero.getField().size()==0){
							fieldOneButtons.remove(spellTargetIndex);
							
						}
						else{
							int i1=((MultiShot)c).getShotTarget1();
							int i2=((MultiShot)c).getShotTarget2();
							for(int i=0;i<spelltargetss.size();i++){
								if(i1==spelltargetss.get(i)){
									int z=spelltargetss.get(i);
									fieldOneButtons.remove(z);
									for(int j=i;j<spelltargetss.size();j++){
									spelltargetss.set(j,spelltargetss.get(j)-1);
									}
									i1=-5;
									i2--;
								}
								if(i2==spelltargetss.get(i)){
									int z=spelltargetss.get(i);
									fieldOneButtons.remove(z);
									for(int j=i;j<spelltargetss.size();j++){
									spelltargetss.set(j,spelltargetss.get(j)-1);
									}
									i2=-5;
									i1--;
								}
							}
						}
							spelltargetss=new ArrayList<>();
							spellTargetIndex=-5;
							SpellPanels removed=(SpellPanels)handTwo.get(index);
							view.remove(removed);
							handTwo.remove(index);
							handTwoButtons.remove(index);
							puttingField1();
							puttingHand2();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							view.remove(playCard);
							view.remove(selectedIcon);
							playSound("Sounds/playMinion2.wav");
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					}
					
				if(c instanceof Polymorph){
					if(secondHero.getCurrentManaCrystals()>=((Polymorph)c).getManaCost()){
						minions=new JPopupMenu();
						if(firstHero.getField().size()!=0)
						{
							
							
							JButton opponent = new JButton("Opponent Minions");
							minions.add(opponent);
							opponent.disable();
							for(int i=0;i<firstHero.getField().size();i++){
								Minion m=firstHero.getField().get(i);
								JButton tmp=new JButton(m.getName());
								tmp.setActionCommand("spellMinionTargetOpponent"+i);
								tmp.addActionListener(this);
								minions.add(tmp);
							}
							
							minions.show(playCard,0,0);
							selectedSpell=(Card)c;
							view.remove(playCard);
							view.remove(selectedIcon);
							spellHand=index;
							
						}else
							JOptionPane.showMessageDialog(view,"Opponent Field Is Empty");
					
						}
					else
							JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					
				}
					if(c instanceof Pyroblast){
						if(secondHero.getCurrentManaCrystals()>=((Pyroblast)c).getManaCost())
						{minions=new JPopupMenu();
							if(firstHero.getField().size()!=0)
							{
						
						JButton oppMinions=new JButton("Opponent Minions");
						minions.add(oppMinions);
						oppMinions.disable();
						for(int i=0;i<firstHero.getField().size();i++)
						{	
							Minion m=firstHero.getField().get(i);
							JButton tmp=new JButton(m.getName());
							tmp.setActionCommand("spellMinionTargetOpponent"+i);
							tmp.addActionListener(this);
							minions.add(tmp);
						}
							}
						JButton herotarget=new JButton("10 Damage To Opp Hero");
						herotarget.addActionListener(this);
						herotarget.setActionCommand("PyroblastHeroTarget");
						minions.add(herotarget);
						
						minions.show(playCard,0,0);
						selectedSpell=(Card)c;
						view.remove(playCard);
						view.remove(selectedIcon);
						spellHand=index;// hena aho
						mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
						puttingHand2();
						
							
							
							
						}else
							JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					}
					if(c instanceof SealOfChampions){
						if(secondHero.getCurrentManaCrystals()>=((SealOfChampions)c).getManaCost())
						{minions=new JPopupMenu();
							if(secondHero.getField().size()!=0)
							{
						
						JButton myMinions=new JButton("My Minions");
						minions.add(myMinions);
						
						
						for(int i=0;i<secondHero.getField().size();i++){
							Minion m=secondHero.getField().get(i);
							JButton tmp=new JButton(m.getName());
							tmp.setActionCommand("spellMinionTargetCurrent"+i);
							tmp.addActionListener(this);
							minions.add(tmp);
						}
						
						
						
						minions.show(playCard,0,0);
						selectedSpell=(Card)c;
						view.remove(playCard);
						view.remove(selectedIcon);
						spellHand=index;
						}
							else
								JOptionPane.showMessageDialog(view,"Field Is Empty");
						}
						else
							JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					}
					if(c instanceof ShadowWordDeath){
						if(secondHero.getCurrentManaCrystals()>=((ShadowWordDeath)c).getManaCost())
						{minions=new JPopupMenu();
							if(firstHero.getField().size()!=0)
							{	
						JButton oppMinions=new JButton("Opponent Minions");
						minions.add(oppMinions);
						oppMinions.disable();
						for(int i=0;i<firstHero.getField().size();i++)
						{	
							Minion m=firstHero.getField().get(i);
							JButton tmp=new JButton(m.getName());
							tmp.setActionCommand("spellMinionTargetOpponent"+i);
							tmp.addActionListener(this);
							minions.add(tmp);
						}
						
						minions.show(playCard,0,0);
						selectedSpell=(Card)c;
						view.remove(playCard);
						view.remove(selectedIcon);
						spellHand=index;
						}
							else
								JOptionPane.showMessageDialog(view,"Field Is Empty");
						}
						else
							JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					
					
					}
					if(c instanceof SiphonSoul){
						if(secondHero.getCurrentManaCrystals()>=((SiphonSoul)c).getManaCost())
						{minions=new JPopupMenu();
							if(firstHero.getField().size()!=0)
					{
									
							 	JButton myMinions=new JButton("Opponent Minions");
							 	minions.add(myMinions);
							 	for(int i=0;i<firstHero.getField().size();i++)
								{	
									Minion m=firstHero.getField().get(i);
									JButton tmp=new JButton(m.getName());
									tmp.setActionCommand("spellMinionTargetOpponent"+i);
									tmp.addActionListener(this);
									minions.add(tmp);
								}
						
							
							minions.show(playCard,0,0);
							selectedSpell=(Card)c;
							view.remove(playCard);
							view.remove(selectedIcon);
							spellHand=index;
							
						
					}	else
							JOptionPane.showMessageDialog(view,"Field Is Empty");
					
					}else
						JOptionPane.showMessageDialog(view,"You Don't Have Enough Mana");
					}
						
					if(c instanceof TwistingNether){
						try {
							secondHero.castSpell((TwistingNether)c, firstHero.getField());
							SpellPanels removed=(SpellPanels)handTwo.get(index);
							handTwo.remove(index);
							handTwoButtons.remove(index);
							view.remove(removed);
							view.remove(playCard);
							view.remove(selectedIcon);
							for(int i=0;i<fieldOne.size();i++){
								view.remove(fieldOne.get(i));
							}
							for(int i=0;i<fieldTwo.size();i++){
								view.remove(fieldTwo.get(i));
							}
							fieldOne=new ArrayList<>();
							fieldOneButtons=new ArrayList<>();
							fieldTwo=new ArrayList<>();
							fieldTwoButtons=new ArrayList<>();
							puttingHand2();
							puttingField1();
							puttingField2();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							playSound("Sounds/playMinion2.wav");
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e1.getMessage());
						}
					}
				}
			}
			
			
			
			view.revalidate();
			view.repaint();
		}
		
	
			if(b.getActionCommand().equals("firstHeroField")){
				if(firstHero.equals(model.getCurrentHero())){
					index=fieldOneButtons.indexOf(b);
					attacker=firstHero.getField().get(index);
					attackerIndex=index;
					attackerIcon.setIcon(attacker.getImage());
					attackerIcon.setBounds(1625,380,140,200);
					//what shows on trying of attacking
					view.add(attackerIcon);
					view.add(attackHero);
					view.remove(playCard);
					view.remove(selectedIcon);
					
				}
				//if first is a target
				else if(secondHero.equals(model.getCurrentHero())&&attacker!=null){
					index=fieldOneButtons.indexOf(b);
					target=firstHero.getField().get(index);
					targetIndex=index;
					targetIcon.setIcon(target.getImage());
					targetIcon.setBounds(1770,380,140,200);
					view.add(targetIcon);
					view.add(attack);
					
				}
				
			view.revalidate();
			view.repaint();
		}
		
		
			if(b.getActionCommand().equals("secondHeroField")){
				if(secondHero.equals(model.getCurrentHero())){
					index=fieldTwoButtons.indexOf(b);
					attacker=secondHero.getField().get(index);
					attackerIndex=index;
					attackerIcon.setIcon(attacker.getImage());
					attackerIcon.setBounds(1625,380,140,200);
					//what shows on trying of attacking
					view.add(attackerIcon);
					view.add(attackHero);
					view.remove(playCard);
					view.remove(selectedIcon);
				
				}
				//if second is a target
				else if(firstHero.equals(model.getCurrentHero())&&attacker!=null){
					index=fieldTwoButtons.indexOf(b);
					target=secondHero.getField().get(index);
					targetIndex=index;
					targetIcon.setIcon(target.getImage());
					targetIcon.setBounds(1770,380,140,200);
					view.add(targetIcon);
					view.add(attack);
					
				}
		
			view.revalidate();
			view.repaint();
			}
		
		
		
		
		if(b.getActionCommand().equals("Attack Minion")){
			try {
				model.getCurrentHero().attackWithMinion(attacker, target);
				{if(firstHero.equals(model.getCurrentHero()))
					playSound("Sounds/attacking.wav");
				else
					playSound("Sounds/attacking.wav");
				}
				
				
				if(attacker.getCurrentHP()==0){
					if(firstHero.equals(model.getCurrentHero()))
						fieldOneButtons.remove(attackerIndex);
					else
						fieldTwoButtons.remove(attackerIndex);
				}
				if(target.getCurrentHP()==0){
					if(firstHero.equals(model.getOpponent()))
						fieldOneButtons.remove(targetIndex);
					else
						fieldTwoButtons.remove(targetIndex);
				}
				
				puttingField1();
				puttingField2();
				view.remove(attack);
				view.remove(attackHero);
				view.remove(attackerIcon);
				view.remove(targetIcon);
				attacker=null;
				target=null;
				
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | InvalidTargetException
					| NotSummonedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view, e1.getMessage());
				view.remove(attack);
				view.remove(attackHero);
				view.remove(attackerIcon);
				view.remove(targetIcon);
				attacker=null;
				target=null;
			} 
		}
		if(b.getActionCommand().equals("Attack Opp Hero")){
			try {
				model.getCurrentHero().attackWithMinion(attacker, model.getOpponent());
				puttingField1();
				puttingField2();
				view.remove(attack);
				view.remove(attackHero);
				view.remove(attackerIcon);
				view.remove(targetIcon);
				attacker=null;
				target=null;
				hp1.setText("HP:"+firstHero.getCurrentHP());
				hp2.setText("HP:"+secondHero.getCurrentHP());
				playSound("Sounds/attacking.wav");
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | NotSummonedException
					| InvalidTargetException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view, e1.getMessage());
				view.remove(attack);
				view.remove(attackHero);
				view.remove(attackerIcon);
				view.remove(targetIcon);
				attacker=null;
				target=null;
			}
		}
	
		
		
		//End Turn Buttons
		
		if(b.getActionCommand().equals("endOfFirst")){
			if(firstHero.equals(model.getCurrentHero()))
			{
				try {
					if(secondHero.getHand().size()<9)
						clone=true;
					if(secondHero.getDeck().size()!=0)

						burned=secondHero.getDeck().get(0);
					if(secondHero.getHand().size()==10)
						fullHand2=true;
					else
						fullHand2=false;
					model.endTurn();
					addingEndTurn1();
					clone=false;
					//fullHand2=false;
					puttingHand2();
					puttingBacks1();
					mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
					hp2.setText("HP:"+secondHero.getCurrentHP());
					view.remove(playCard);
					view.remove(selectedIcon);
					
					view.remove(attack);
					view.remove(attackHero);
					view.remove(targetIcon);
					view.remove(attackerIcon);
					
					view.revalidate();
					view.repaint();
					
				} catch (FullHandException e1 ) {
					// TODO Auto-generated catch block
					
					deck2.setText(secondHero.getDeck().size()+"");
					if(burned!=null)
					{
					if(burned instanceof Minion)
						JOptionPane.showMessageDialog(view,"The Burned Minion Is : "+((Minion)burned).getName(),e1.getMessage(),JOptionPane.INFORMATION_MESSAGE,burnedImage(burned));
					else
						JOptionPane.showMessageDialog(view,"The Burned Spell Is : "+((Spell)burned).getName(),e1.getMessage(),JOptionPane.INFORMATION_MESSAGE,burnedImage(burned));
					}
					else
						JOptionPane.showMessageDialog(view, e1.getMessage());
						
						
					burned=null;
					puttingHand2();
					puttingBacks1();
				}catch(CloneNotSupportedException e1){
					JOptionPane.showMessageDialog(view,e1.getMessage());
					puttingHand2();
					puttingBacks1();
					deck2.setText(secondHero.getDeck().size()+"");
				}
			}
			else
				JOptionPane.showMessageDialog(view, "Not Your Turn");
			
		}
		if(b.getActionCommand().equals("endOfSecond")){
			if(secondHero.equals(model.getCurrentHero())){
				try {
					if(firstHero.getHand().size()<9)
						clone=true;
					if(firstHero.getDeck().size()!=0)
						burned=firstHero.getDeck().get(0);
					if(firstHero.getHand().size()==10)
						fullHand1=true;
					else
						fullHand1=false;
					
					model.endTurn();
					addingEndTurn2();
					clone=false;
					//fullHand1=false;
					puttingHand1();
					puttingBacks2();
					mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
					hp1.setText("HP:"+firstHero.getCurrentHP());
					view.remove(playCard);
					view.remove(selectedIcon);
					
					view.remove(attack);
					view.remove(attackHero);
					view.remove(targetIcon);
					view.remove(attackerIcon);
					
					view.revalidate();
					view.repaint();
				} catch (FullHandException e1) 
						  {
					// TODO Auto-generated catch block
					if(burned!=null)
					{
					if(burned instanceof Minion)
						JOptionPane.showMessageDialog(view,"The Burned Minion Is : "+((Minion)burned).getName(),e1.getMessage(),JOptionPane.INFORMATION_MESSAGE,burnedImage(burned));
					else
						JOptionPane.showMessageDialog(view,"The Burned Spell Is : "+((Spell)burned).getName(),e1.getMessage(),JOptionPane.INFORMATION_MESSAGE,burnedImage(burned));
						  }
					else
						JOptionPane.showMessageDialog(view, e1.getMessage());
					
					burned=null;
					puttingHand1();
					puttingBacks2();
					deck1.setText(firstHero.getDeck().size()+"");

				}catch(CloneNotSupportedException e1){
					JOptionPane.showMessageDialog(view, e1.getMessage());
					
					
					puttingHand1();
					puttingBacks2();
					deck1.setText(firstHero.getDeck().size()+"");
				}
			}
			else
				JOptionPane.showMessageDialog(view, "Not Your Turn");
			
		}
		
		//first hero for his minion
		if(firstHero.equals(model.getCurrentHero())){
			for(int i=0;i<firstHero.getField().size();i++){
				if(b.getActionCommand().equals("spellMinionTargetCurrent"+i)){
					Minion m=firstHero.getField().get(i);
					if(selectedSpell instanceof DivineSpirit)
						try {
							firstHero.castSpell((DivineSpirit)selectedSpell,m);
							minions=new JPopupMenu();
							selectedSpell=null;
							view.remove(handOne.get(spellHand));
							handOne.remove(spellHand);
							handOneButtons.remove(spellHand);
							puttingHand1();
							
							puttingField1();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							
							
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
					
				
					
					if(selectedSpell instanceof SealOfChampions){
						try {
							firstHero.castSpell((SealOfChampions)selectedSpell,m);
							minions=new JPopupMenu();
							selectedSpell=null;
							puttingField1();
						
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							
							view.remove(handOne.get(spellHand));
							handOne.remove(spellHand);
							handOneButtons.remove(spellHand);
							puttingHand1();
							
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
							
						}
					}
					if(firstHero instanceof Priest){
						try {
							((Priest)firstHero).useHeroPower(m);
							usingOfpower="";
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							hp1.setText("HP:"+firstHero.getCurrentHP());
							puttingField1();
						} catch (NotEnoughManaException
								| HeroPowerAlreadyUsedException
								| NotYourTurnException | FullHandException
								| FullFieldException
								| CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
							
						}
					}
			}
		}
			//first hero for opp minion
			for(int i=0;i<secondHero.getField().size();i++){
				if(b.getActionCommand().equals("spellMinionTargetOpponent"+i)){
					Minion m=secondHero.getField().get(i);
					
					if(selectedSpell instanceof KillCommand){
						try {
							boolean flagg=false;
							if(secondHero.getField().get(i).getCurrentHP()<=5){
								spellTargetIndex=i;
								flagg=true;
							}
							firstHero.castSpell((KillCommand)selectedSpell, m);
							selectedSpell=null;
							if(flagg)
								fieldTwoButtons.remove(spellTargetIndex);
							minions=new JPopupMenu();
							
							view.remove(handOne.get(spellHand));
							handOne.remove(spellHand);
							handOneButtons.remove(spellHand);
							puttingHand1();
							puttingField2();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							break;
							
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
						
					}
					if(selectedSpell instanceof Polymorph){
						try {
							firstHero.castSpell((Polymorph)selectedSpell,m);
							selectedSpell=null;
							minions=new JPopupMenu();
							view.remove(handOne.get(spellHand));
							handOne.remove(spellHand);
							handOneButtons.remove(spellHand);
							puttingHand1();
							
							puttingField2();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
							
						}
					}
					if(selectedSpell instanceof Pyroblast){
						try {
							boolean flagg=false;
							if(secondHero.getField().get(i).getCurrentHP()<=10){
								spellTargetIndex=i;
								flagg=true;
							}
							firstHero.castSpell((Pyroblast)selectedSpell, m);
							minions=new JPopupMenu();
							if(flagg)
								fieldTwoButtons.remove(spellTargetIndex);
							
							view.remove(handOne.get(spellHand));
							handOne.remove(spellHand);
							handOneButtons.remove(spellHand);
							
							
							puttingHand1();
							puttingField2();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
				}
					if(selectedSpell instanceof ShadowWordDeath){
						try {
							boolean flagg=false;
							if(secondHero.getField().get(i).getCurrentHP()>=5){
								spellTargetIndex=i;
								flagg=true;
							}
							firstHero.castSpell((ShadowWordDeath)selectedSpell, m);
							selectedSpell=null;
							minions=new JPopupMenu();
							if(flagg)
								fieldTwoButtons.remove(spellTargetIndex);
							
							view.remove(handOne.get(spellHand));
							handOne.remove(spellHand);
							handOneButtons.remove(spellHand);
							
							
							puttingHand1();
							puttingField2();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
					}
					
					if(selectedSpell instanceof SiphonSoul){
						try {
							
							spellTargetIndex=i;
							firstHero.castSpell((SiphonSoul)selectedSpell,m);
							selectedSpell=null;
							minions=new JPopupMenu();
							fieldTwoButtons.remove(spellTargetIndex);
							
							view.remove(handOne.get(spellHand));
							handOne.remove(spellHand);
							handOneButtons.remove(spellHand);
							puttingHand1();
							
							puttingField2();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							hp1.setText("HP:"+firstHero.getCurrentHP());
							break;
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
							
						}
						
					}
					if(firstHero instanceof Mage){
						try {
							boolean flagg=false;
							if(secondHero.getField().get(i).getCurrentHP()<=1){
								spellTargetIndex=i;
								flagg=true;
							}
							((Mage)firstHero).useHeroPower(m);
							
							usingOfpower="";
							minions=new JPopupMenu();
							if(flagg)
								fieldTwoButtons.remove(spellTargetIndex);
							
							puttingField2();
							mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
							break;
						} catch (NotEnoughManaException
								| HeroPowerAlreadyUsedException
								| NotYourTurnException | FullHandException
								| FullFieldException
								| CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
					}
				}
				
			}
		}
		else{//second hero for his minion
			for(int i=0;i<secondHero.getField().size();i++){
				if(b.getActionCommand().equals("spellMinionTargetCurrent"+i)){
					Minion m=secondHero.getField().get(i);
					if(selectedSpell instanceof DivineSpirit)
						try {
							secondHero.castSpell((DivineSpirit)selectedSpell,m);
							minions=new JPopupMenu();
							selectedSpell=null;
							view.remove(handTwo.get(spellHand));
							handTwo.remove(spellHand);
							handTwoButtons.remove(spellHand);
							puttingHand2();
							
							puttingField2();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
				
					if(selectedSpell instanceof SealOfChampions){
						try {
							secondHero.castSpell((SealOfChampions)selectedSpell, m);
							selectedSpell=null;
							minions=new JPopupMenu();
							view.remove(handTwo.get(spellHand));
							handTwo.remove(spellHand);
							handTwoButtons.remove(spellHand);
							puttingHand2();
							
							puttingField2();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
					}
					if(secondHero instanceof Priest){
						try {
							((Priest)secondHero).useHeroPower(m);
							usingOfpower="";
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							hp2.setText("HP:"+secondHero.getCurrentHP());
							puttingField2();
						} catch (NotEnoughManaException
								| HeroPowerAlreadyUsedException
								| NotYourTurnException | FullHandException
								| FullFieldException
								| CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
							
						}
					}
					
				}
				
			}
			//second hero for opp minion
			for(int i=0;i<firstHero.getField().size();i++){
				if(b.getActionCommand().equals("spellMinionTargetOpponent"+i)){
					Minion m=firstHero.getField().get(i);
					
					if(selectedSpell instanceof KillCommand){
						try {
							boolean flagg=false;
							if(firstHero.getField().get(i).getCurrentHP()<=5){
								spellTargetIndex=i;
								flagg=true;
							}
							secondHero.castSpell((KillCommand)selectedSpell, m);
							selectedSpell=null;
							minions=new JPopupMenu();
							if(flagg)
								fieldOneButtons.remove(spellTargetIndex);
							
							view.remove(handTwo.get(spellHand));
							handTwo.remove(spellHand);
							handTwoButtons.remove(spellHand);
							puttingHand2();
							
							puttingField1();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							break;
							
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
						
					}
					if(selectedSpell instanceof Polymorph){
						try {
							secondHero.castSpell((Polymorph)selectedSpell,m);
							selectedSpell=null;
							minions=new JPopupMenu();
							
							view.remove(handTwo.get(spellHand));
							handTwo.remove(spellHand);
							handTwoButtons.remove(spellHand);
							puttingHand2();
							
							puttingField1();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
					}
					if(selectedSpell instanceof Pyroblast){
						try {
							boolean flagg=false;
							if(firstHero.getField().get(i).getCurrentHP()<=10){
								spellTargetIndex=i;
								flagg=true;
							}
							secondHero.castSpell((Pyroblast)selectedSpell, m);
							selectedSpell=null;
							minions=new JPopupMenu();
							if(flagg)
								fieldOneButtons.remove(spellTargetIndex);
							
							view.remove(handTwo.get(spellHand));
							handTwo.remove(spellHand);
							handTwoButtons.remove(spellHand);
							
							puttingField1();
							puttingHand2();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
					}
					if(selectedSpell instanceof ShadowWordDeath){
						try {
							boolean flagg=false;
							if(firstHero.getField().get(i).getCurrentHP()>=5){
								spellTargetIndex=i;
								flagg=true;
							}
							secondHero.castSpell((ShadowWordDeath)selectedSpell, m);
							selectedSpell=null;
							minions=new JPopupMenu();
							if(flagg)	
								fieldOneButtons.remove(spellTargetIndex);
							
							view.remove(handTwo.get(spellHand));
							handTwo.remove(spellHand);
							handTwoButtons.remove(spellHand);
							
							puttingField1();
							puttingHand2();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								| InvalidTargetException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
					}
					
					if(selectedSpell instanceof SiphonSoul){
						try {
							spellTargetIndex=i;
							secondHero.castSpell((SiphonSoul)selectedSpell, m);
							selectedSpell=null;
							minions=new JPopupMenu();
							fieldOneButtons.remove(spellTargetIndex);
							
							view.remove(handTwo.get(spellHand));
							handTwo.remove(spellHand);
							handTwoButtons.remove(spellHand);
							puttingHand2();
							puttingField1();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							hp2.setText("HP:"+secondHero.getCurrentHP());
							break;
						} catch (NotYourTurnException | NotEnoughManaException
								 e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
					}
					
					if(secondHero instanceof Mage){
						try {
							boolean flagg=false;
							if(firstHero.getField().get(i).getCurrentHP()<=1){
								spellTargetIndex=i;
								flagg=true;
							}
							((Mage)secondHero).useHeroPower(m);
							
							usingOfpower="";
							minions=new JPopupMenu();
							if(flagg)
								fieldOneButtons.remove(spellTargetIndex);
							
							puttingField1();
							mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
							break;
						} catch (NotEnoughManaException
								| HeroPowerAlreadyUsedException
								| NotYourTurnException | FullHandException
								| FullFieldException
								| CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view, e1.getMessage());
						}
					}
				}
				
			}
		}
		if(b.getActionCommand().equals("killCommandHero")){
			try {
				model.getCurrentHero().castSpell((KillCommand)selectedSpell, model.getOpponent());
				selectedSpell=null;
				if(firstHero.equals(model.getCurrentHero())){
					hp2.setText("HP:"+secondHero.getCurrentHP());
					mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
					view.remove(handOne.get(spellHand));
					handOne.remove(spellHand);
					handOneButtons.remove(spellHand);
					puttingHand1();
					
					
				}
				else{
					//int indexSelectedSpell=secondHero.getHand().indexOf(selectedSpell);
					hp1.setText("HP:"+firstHero.getCurrentHP());
					mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
					view.remove(handTwo.get(spellHand));
					handTwo.remove(spellHand);
					handTwoButtons.remove(spellHand);
					puttingHand2();
					
					
				}
				
				
			} catch (NotYourTurnException | NotEnoughManaException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view, e1.getMessage());
			}
			
		}
		
		if(b.getActionCommand().equals("PyroblastHeroTarget")){
			try {
				model.getCurrentHero().castSpell((Pyroblast)selectedSpell, model.getOpponent());
				selectedSpell=null;
				if(firstHero.equals(model.getCurrentHero())){
					hp2.setText("HP:"+secondHero.getCurrentHP());
					mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
					view.remove(handOne.get(spellHand));
					handOne.remove(spellHand);
					handOneButtons.remove(spellHand);
					puttingHand1();
					
				}
				else{
					
					hp1.setText("HP:"+firstHero.getCurrentHP());
					mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
					view.remove(handTwo.get(spellHand));
					handTwo.remove(spellHand);
					handTwoButtons.remove(spellHand);
					puttingHand2();
					
					
				}
				
			} catch (NotYourTurnException | NotEnoughManaException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view, e1.getMessage());
			}
		}
		
		if(b.getActionCommand().equals("MageHeroPower")){
			
			try {
				((Mage)model.getCurrentHero()).useHeroPower(model.getOpponent());
				if(firstHero.equals(model.getCurrentHero())){
					hp2.setText("HP:"+secondHero.getCurrentHP());
					mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
				}
				else
				{
					hp1.setText("HP:"+firstHero.getCurrentHP());
					mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
				}
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
					| NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view, e1.getMessage());
			}
			
		}
		if(b.getActionCommand().equals("PriestHeroPower")){
			try {
				((Priest)model.getCurrentHero()).useHeroPower(model.getCurrentHero());
				if(firstHero.equals(model.getCurrentHero())){
					hp1.setText("HP:"+firstHero.getCurrentHP());
					mana1.setText("MANA:"+firstHero.getCurrentManaCrystals());
				}
				else
				{
					hp2.setText("HP:"+secondHero.getCurrentHP());
					mana2.setText("MANA:"+secondHero.getCurrentManaCrystals());
				}
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
					| NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view, e1.getMessage());
			}
			
		}
		
		

	view.revalidate();
	view.repaint();
	
	}
	
	
	
public void puttingBacks1(){
	handX1=230;
	for (int i=0;i<handOne.size();i++){
		view.remove(handOne.get(i));
	}
	// putting back hands
	for(int i=0;i<firstHero.getHand().size();i++)
	{
		if(i==0){
			f1.setBounds(handX1,750,140,200);
			view.add(f1);
			handX1+=140;
		}
		if(i==1){
			f2.setBounds(handX1,750,140,200);
			view.add(f2);
			handX1+=140;
		}
		if(i==2){
			f3.setBounds(handX1,750,140,200);
			view.add(f3);
			handX1+=140;
		}
		if(i==3){
			f4.setBounds(handX1,750,140,200);
			view.add(f4);
			handX1+=140;
		}
		if(i==4){
			f5.setBounds(handX1,750,140,200);
			view.add(f5);
			handX1+=140;
		}
		if(i==5){
			f6.setBounds(handX1,750,140,200);
			view.add(f6);
			handX1+=140;
		}
		if(i==6){
			f7.setBounds(handX1,750,140,200);
			view.add(f7);
			handX1+=140;
		}
		if(i==7){
			f8.setBounds(handX1,750,140,200);
			view.add(f8);
			handX1+=140;
		}
		if(i==8){
			f9.setBounds(handX1,750,140,200);
			view.add(f9);
			handX1+=140;
		}
		if(i==9){
			f10.setBounds(handX1,750,140,200);
			view.add(f10);
			handX1+=140;
		}
		}
}
	
	public void puttingBacks2(){
		handX2=230;
		for (int i=0;i<handTwo.size();i++){
			view.remove(handTwo.get(i));
		}
		// putting back hands
		for(int i=0;i<secondHero.getHand().size();i++)
		{
			if(i==0){
				k1.setBounds(handX2,0,140,200);
				view.add(k1);
				handX2+=140;
			}
			if(i==1){
				k2.setBounds(handX2,0,140,200);
				view.add(k2);
				handX2+=140;
			}
			if(i==2){
				k3.setBounds(handX2,0,140,200);
				view.add(k3);
				handX2+=140;
			}
			if(i==3){
				k4.setBounds(handX2,0,140,200);
				view.add(k4);
				handX2+=140;
			}
			if(i==4){
				k5.setBounds(handX2,0,140,200);
				view.add(k5);
				handX2+=140;
			}
			if(i==5){
				k6.setBounds(handX2,0,140,200);
				view.add(k6);
				handX2+=140;
			}
			if(i==6){
				k7.setBounds(handX2,0,140,200);
				view.add(k7);
				handX2+=140;
			}
			if(i==7){
				k8.setBounds(handX2,0,140,200);
				view.add(k8);
				handX2+=140;
			}
			if(i==8){
				k9.setBounds(handX2,0,140,200);
				view.add(k9);
				handX2+=140;
			}
			if(i==9){
				k10.setBounds(handX2,0,140,200);
				view.add(k10);
				handX2+=140;
			}
			}
	}
	
	
	
	public void removingBacks1(){
		for(int i=0;i<firstHero.getHand().size();i++){
			if(i==0)
				view.remove(f1);
			if(i==1)
				view .remove(f2);
			if(i==2)
				view .remove(f3);
			if(i==3)
				view .remove(f4);
			if(i==4)
				view .remove(f5);
			if(i==5)
				view .remove(f6);
			if(i==6)
				view .remove(f7);
			if(i==7)
				view .remove(f8);
			if(i==8)
				view .remove(f9);
			if(i==9)
				view .remove(f10);
		}
	}
	public void removingBacks2(){
		for(int i=0;i<secondHero.getHand().size();i++){
			if(i==0)
				view.remove(k1);
			if(i==1)
				view .remove(k2);
			if(i==2)
				view .remove(k3);
			if(i==3)
				view .remove(k4);
			if(i==4)
				view .remove(k5);
			if(i==5)
				view .remove(k6);
			if(i==6)
				view .remove(k7);
			if(i==7)
				view .remove(k8);
			if(i==8)
				view .remove(k9);
			if(i==9)
				view .remove(k10);
		}
	}
	
	
	
	
	
	public void puttingHand1 (){
		
		removingBacks1();
		
		handX1=230;
		for(int i=0;i<handOne.size();i++){
			if(handOne.get(i) instanceof MinionPanels)
				
				{
				MinionPanels reput=(MinionPanels) handOne.get(i);
				String info="HP:"+reput.getMinion().getCurrentHP()+"  Attack:"+reput.getMinion().getAttack()
						+" Mana:"+reput.getMinion().getManaCost()+"\nRarity:"+reput.getMinion().getRarity()+"\n";
				if(reput.getMinion().isTaunt())
					info+="Taunt   ";
				if(reput.getMinion().isDivine())
					info+="Divine  ";
				if(!reput.getMinion().isSleeping())
					info+="Charge";
				else
					info+="Sleeping";
		
				reput.getTxt().setText(info);
				
				reput.setBounds(handX1,750,140,200);
				view.add(reput);
			}
			else
			{	
				SpellPanels reput=(SpellPanels) handOne.get(i);
				String info ="Mana:"+reput.getSpell().getManaCost()+"\nRarity:"+reput.getSpell().getRarity();
				reput.getTxt().setText(info);
				reput.setBounds(handX1,750,140,200);
				view.add(reput);
			}
			handX1+=140;
		}
		
	}

		
	
	public void puttingField1(){
	
		//removing field first
		for(int i=0;i<fieldOne.size();i++){
			view.remove(fieldOne.get(i));
			
		}
		int j=0;
		
		fieldX1=230;
		for(int i=0;i<firstHero.getField().size();i++){
			
			for(j=0;j<fieldOne.size();j++){
				
				MinionPanels m=fieldOne.get(j);
				Minion minion=m.getMinion();
				if(minion.equals(firstHero.getField().get(i))&&firstHero.getField().get(i).getCurrentHP()!=0){
					if(minion.getName().equals("Sheep")){
						String info="HP:"+minion.getCurrentHP()+"  Attack:"+minion.getAttack()+" Mana:"+minion.getManaCost()+"\nRarity:"+minion.getRarity()+"\n";
						if(minion.isTaunt())
							info+="Taunt   ";
						if(minion.isDivine())
							info+="Divine  ";
						if(!minion.isSleeping())
							info+="Charge";
						else
							info+="Sleeping";
						m.getTxt().setText(info);
						m.setBounds(fieldX1,500,140,200);
						fieldX1+=205;
						m.getMinionButton().setActionCommand("firstHeroField");
						m.getMinionButton().addActionListener(this);
						m.getMinionButton().setIcon(new ImageIcon("Minions/sheep.jpg"));
						view.add(m);
						
					}
					else{
					MinionPanels reput=m;
					reput.setBounds(fieldX1,500,140,200);
					fieldX1+=205;
					String info="HP:"+minion.getCurrentHP()+"  Attack:"+minion.getAttack()+" Mana:"+minion.getManaCost()+"\nRarity:"+minion.getRarity()+"\n";
					if(minion.isTaunt())
						info+="Taunt   ";
					if(minion.isDivine())
						info+="Divine  ";
					if(!minion.isSleeping())
						info+="Charge";
					else
						info+="Sleeping";
					reput.getTxt().setText(info);
					view.add(reput);
					
					
					}
				
				}
			
					
			}
			
				
		}
		
		
		
		view.revalidate();
		view.repaint();
		
	}
	
	
	public void puttingField2(){
	
		//removing field first
				for(int i=0;i<fieldTwo.size();i++){
					view.remove(fieldTwo.get(i));
				//	fieldTwo.remove(i);
				}
				
		
		
		
		fieldX2=230;
		for(int i=0;i<secondHero.getField().size();i++){
			
			for(int j=0;j<fieldTwo.size();j++){
				MinionPanels m=fieldTwo.get(j);
				Minion minion=m.getMinion();
				if(minion.equals(secondHero.getField().get(i))&&secondHero.getField().get(i).getCurrentHP()!=0){
					if(minion.getName().equals("Sheep")){
								
						String info="HP:"+minion.getCurrentHP()+"  Attack:"+minion.getAttack()+" Mana:"+minion.getManaCost()+"\nRarity:"+minion.getRarity()+"\n";
						if(minion.isTaunt())
							info+="Taunt   ";
						if(minion.isDivine())
							info+="Divine  ";
						if(!minion.isSleeping())
							info+="Charge";
						else
							info+="Sleeping";
						m.getTxt().setText(info);
						m.setBounds(fieldX2,250,140,200);
						fieldX2+=205;
						m.getMinionButton().setActionCommand("secondHeroField");
						m.getMinionButton().addActionListener(this);
						m.getMinionButton().setIcon(new ImageIcon("Minions/sheep.jpg"));
						view.add(m);
					}
						else{
							
							m.setBounds(fieldX2,250,140,200);
							fieldX2+=205;
					
							String info="HP:"+minion.getCurrentHP()+"  Attack:"+minion.getAttack()+" Mana:"+minion.getManaCost()+"\nRarity:"+minion.getRarity()+"\n";
							if(minion.isTaunt())
								info+="Taunt   ";
							if(minion.isDivine())
								info+="Divine  ";
							if(!minion.isSleeping())
								info+="Charge";
							else
								info+="Sleeping";
					
							m.getTxt().setText(info);
							view.add(m);
					
				}
					}
			
			}
		}
		
		view.revalidate();
		view.repaint();
	}
	
	public void puttingHand2(){
		removingBacks2();
		
		handX2=230;
		for(int i=0;i<handTwo.size();i++){
			if(handTwo.get(i) instanceof MinionPanels){
				MinionPanels reput=(MinionPanels) handTwo.get(i);
				String info="HP:"+reput.getMinion().getCurrentHP()+"  Attack:"+reput.getMinion().getAttack()
						+" Mana:"+reput.getMinion().getManaCost()+"\nRarity:"+reput.getMinion().getRarity()+"\n";
				if(reput.getMinion().isTaunt())
					info+="Taunt   ";
				if(reput.getMinion().isDivine())
					info+="Divine  ";
				if(!reput.getMinion().isSleeping())
					info+="Charge";
				else
					info+="Sleeping";
		
				reput.getTxt().setText(info);
				
				reput.setBounds(handX2,0,140,200);
				view.add(reput);
			}
			else{
				SpellPanels reput=(SpellPanels) handTwo.get(i);
				String info ="Mana:"+reput.getSpell().getManaCost()+"\nRarity:"+reput.getSpell().getRarity();
				reput.getTxt().setText(info);
				reput.setBounds(handX2,0,140,200);
				view.add(reput);
			}
			handX2+=140;
		}
		
	}
	
	public void startingHand1(){
		for(int i=0;i<firstHero.getHand().size();i++)
		{
			view.revalidate();
			view.repaint();
			Card c=firstHero.getHand().get(i);
			
			if(c instanceof Minion)
			{
				System.out.println(firstHero.equals(model.getCurrentHero())+""+firstHero.equals(model.getOpponent()));
				Minion minion=(Minion)c;
				MinionPanels m=new MinionPanels(minion.getCurrentHP(),minion.getAttack(),
						minion.getManaCost(),minion.getRarity(),minion.isTaunt(),!minion.isSleeping(),minion.isDivine(),minion.getImage(),minion);
				
				m.setBounds(handX1,750,140,200);
				m.getMinionButton().setActionCommand("firstHero");
				m.getMinionButton().addActionListener(this);
				view.add(m);
				handOne.add(m);
				handOneButtons.add(m.getMinionButton());
				
				view.revalidate();
				view.repaint();
				handX1+=140;
			}
			
			
		
			else{
				
				if(c instanceof Pyroblast){
					Pyroblast spell=(Pyroblast)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					handX1+=140;
					view.add(s);
					handOne.add(s);handOneButtons.add(s.getSpellButton());s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				
				if(c instanceof CurseOfWeakness){
					CurseOfWeakness spell=(CurseOfWeakness)c;
					SpellPanels s=new SpellPanels(c.getManaCost(), c.getRarity(), ((CurseOfWeakness)c).getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					handX1+=140;
					view.add(s);
					handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				if(c instanceof DivineSpirit){
					DivineSpirit spell=(DivineSpirit)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(), spell.getRarity(), spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				
				}
			}
		
				if(c instanceof Flamestrike){
					Flamestrike spell=(Flamestrike)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(), spell.getRarity(), spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				if(c instanceof HolyNova){
					HolyNova spell=(HolyNova)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
				
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				if(c instanceof KillCommand){
					KillCommand spell=(KillCommand)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(), spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				if(c instanceof LevelUp){
					LevelUp spell=(LevelUp)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(), spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				if(c instanceof MultiShot){
					MultiShot spell=(MultiShot)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				if(c instanceof Polymorph){
					Polymorph spell=(Polymorph)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(), spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
				
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				
				if(c instanceof SealOfChampions){
					SealOfChampions spell=(SealOfChampions)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				if(c instanceof ShadowWordDeath){
					ShadowWordDeath spell=(ShadowWordDeath)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				if(c instanceof SiphonSoul){
					SiphonSoul spell=(SiphonSoul)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				if(c instanceof TwistingNether){
					TwistingNether spell=(TwistingNether)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHero");
					s.getSpellButton().addActionListener(this);
				}
				
			}
		
	}
	
	public void startingHand2(){
		for(int i=0;i<secondHero.getHand().size();i++)
		{
			Card c=secondHero.getHand().get(i);
			
			if(c instanceof Minion)
			{
				Minion minion=(Minion)c;
				MinionPanels m=new MinionPanels(minion.getCurrentHP(),minion.getAttack(),
						minion.getManaCost(),minion.getRarity(),minion.isTaunt(),!minion.isSleeping(),minion.isDivine(),minion.getImage(),minion);
				m.setBounds(handX2,0,140,200);
				handX2+=140;
				m.getMinionButton().setActionCommand("secondHero");
				m.getMinionButton().addActionListener(this);
				
				view.add(m);handTwo.add(m);handTwoButtons.add(m.getMinionButton());
				
			}
			else{
				if(c instanceof CurseOfWeakness){
					CurseOfWeakness spell2=(CurseOfWeakness)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
					
				}
				if(c instanceof DivineSpirit){
					DivineSpirit spell2=(DivineSpirit)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(), spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				
				if(c instanceof Flamestrike){
					Flamestrike spell2=(Flamestrike)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(), spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				if(c instanceof HolyNova){
					HolyNova spell2=(HolyNova)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				if(c instanceof KillCommand){
					KillCommand spell2=(KillCommand)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				if(c instanceof LevelUp){
					LevelUp spell2=(LevelUp)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				if(c instanceof MultiShot){
					MultiShot spell2=(MultiShot)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				if(c instanceof Polymorph){
					Polymorph spell2=(Polymorph)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				if(c instanceof Pyroblast){
					Pyroblast spell2=(Pyroblast)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				if(c instanceof SealOfChampions){
					SealOfChampions spell2=(SealOfChampions)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				if(c instanceof ShadowWordDeath){
					ShadowWordDeath spell2=(ShadowWordDeath)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				if(c instanceof SiphonSoul){
					SiphonSoul spell2=(SiphonSoul)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
				
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				if(c instanceof TwistingNether){
					TwistingNether spell2=(TwistingNether)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHero");
					s2.getSpellButton().addActionListener(this);
				}
				
			}
			
		}
	}
	
	public void addingEndTurn1(){
		
		
		
		//check if chromaggus is on field of first hero
		boolean chrom=false;
		for(int i=0;i<secondHero.getField().size();i++){
			if(secondHero.getField().get(i).getName().equals("Chromaggus"))
				chrom =true;
		}
	if(secondHero.getDeck().size()!=0&&!fullHand2)	
		{
			Card c=secondHero.getHand().get(secondHero.getHand().size()-1);
			
			if(c instanceof Minion)
			{
				Minion minion=(Minion)c;
				MinionPanels m=new MinionPanels(minion.getCurrentHP(),minion.getAttack(),
						minion.getManaCost(),minion.getRarity(),minion.isTaunt(),!minion.isSleeping(),minion.isDivine(),minion.getImage(),minion);
				m.setBounds(handX2,0,140,200);
				handX2+=140;
				m.getMinionButton().setActionCommand("secondHero");
				m.getMinionButton().addActionListener(this);
				
				view.add(m);handTwo.add(m);handTwoButtons.add(m.getMinionButton());
				//adding a clone if chromaggus is on field
				if(chrom&&clone){
					try {
						MinionPanels cln=new MinionPanels(minion.getCurrentHP(),minion.getAttack(),
								minion.getManaCost(),minion.getRarity(),minion.isTaunt()
								,!minion.isSleeping(),minion.isDivine(),minion.getImage(),minion.clone());
						cln.setBounds(handX2,0,140,200);
						cln.getMinionButton().setActionCommand("secondHero");
						cln.getMinionButton().addActionListener(this);
						view.add(cln);
						handTwo.add(cln);
						handTwoButtons.add(cln.getMinionButton());
						System.out.println("chrom");
						handX2+=140;
					
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e.getMessage());
					}
					
				}
				
				view.revalidate();
				view.repaint();
				
			}
			else{
				if(c instanceof CurseOfWeakness){
					CurseOfWeakness spell2=(CurseOfWeakness)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
					
				}
				if(c instanceof DivineSpirit){
					DivineSpirit spell2=(DivineSpirit)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(), spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				
				if(c instanceof Flamestrike){
					Flamestrike spell2=(Flamestrike)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(), spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof HolyNova){
					HolyNova spell2=(HolyNova)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof KillCommand){
					KillCommand spell2=(KillCommand)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&& clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof LevelUp){
					LevelUp spell2=(LevelUp)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof MultiShot){
					MultiShot spell2=(MultiShot)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof Polymorph){
					Polymorph spell2=(Polymorph)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(), spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof Pyroblast){
					Pyroblast spell2=(Pyroblast)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof SealOfChampions){
					SealOfChampions spell2=(SealOfChampions)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof ShadowWordDeath){
					ShadowWordDeath spell2=(ShadowWordDeath)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof SiphonSoul){
					SiphonSoul spell2=(SiphonSoul)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof TwistingNether){
					TwistingNether spell2=(TwistingNether)c;
					SpellPanels s2=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(),spell2);
					s2.setBounds(handX2,0,140,200);
					handX2+=140;
					view.add(s2);handTwo.add(s2);handTwoButtons.add(s2.getSpellButton());
					s2.getSpellButton().setActionCommand("secondHeroSpell");
					s2.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell2.getManaCost(),spell2.getRarity(),spell2.getImage(), spell2.clone());
							cln.setBounds(handX2,0,140,200);
							cln.getSpellButton().setActionCommand("secondHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handTwo.add(cln);
							handTwoButtons.add(cln.getSpellButton());
							
							handX2+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
			}
			
		}
		deck2.setText(secondHero.getDeck().size()+"");
		deck1.setText(firstHero.getDeck().size()+"");
		
		puttingField1();
		puttingField2();
	//	fullHand2=false;
	}
	
	
	public void addingEndTurn2(){
		
		
		
		//check if chromaggus is on field of first hero
				boolean chrom=false;
				for(int i=0;i<firstHero.getField().size();i++){
					if(firstHero.getField().get(i).getName().equals("Chromaggus"))
						chrom =true;
				}
				
		
	if(firstHero.getDeck().size()!=0&&!fullHand1)	
		{
			
			Card c=firstHero.getHand().get(firstHero.getHand().size()-1);
			
			if(c instanceof Minion)
			{
				//System.out.println(firstHero.equals(model.getCurrentHero())+""+firstHero.equals(model.getOpponent()));
				Minion minion=(Minion)c;
				MinionPanels m=new MinionPanels(minion.getCurrentHP(),minion.getAttack(),
						minion.getManaCost(),minion.getRarity(),minion.isTaunt(),!minion.isSleeping(),minion.isDivine(),minion.getImage(),minion);
				
				m.setBounds(handX1,750,140,200);
				m.getMinionButton().setActionCommand("firstHero");
				m.getMinionButton().addActionListener(this);
				view.add(m);
				handOne.add(m);
				handOneButtons.add(m.getMinionButton());
				
				handX1+=140;
				
				//adding a clone if chromaggus is on field
				if(chrom&&clone){
					try {
						
						MinionPanels cln=new MinionPanels(minion.getCurrentHP(),minion.getAttack(),
								minion.getManaCost(),minion.getRarity(),minion.isTaunt()
								,!minion.isSleeping(),minion.isDivine(),minion.getImage(),minion.clone());
						cln.setBounds(handX1,750,140,200);
						cln.getMinionButton().setActionCommand("firstHero");
						cln.getMinionButton().addActionListener(this);
						view.add(cln);
						handOne.add(cln);
						handOneButtons.add(cln.getMinionButton());
						System.out.println("chrom");
						handX1+=140;
					
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e.getMessage());
					}
					
				}
				
				view.revalidate();
				view.repaint();
			}
			
			
		
			else{
				
				if(c instanceof Pyroblast){
					Pyroblast spell=(Pyroblast)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);
					handOne.add(s);handOneButtons.add(s.getSpellButton());s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
					
				}
				
				if(c instanceof CurseOfWeakness){
					CurseOfWeakness spell=(CurseOfWeakness)c;
					SpellPanels s=new SpellPanels(c.getManaCost(), c.getRarity(), ((CurseOfWeakness)c).getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);
					handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof DivineSpirit){
					DivineSpirit spell=(DivineSpirit)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(), spell.getRarity(), spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
			}
		
				if(c instanceof Flamestrike){
					Flamestrike spell=(Flamestrike)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(), spell.getRarity(), spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof HolyNova){
					HolyNova spell=(HolyNova)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof KillCommand){
					KillCommand spell=(KillCommand)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(), spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof LevelUp){
					LevelUp spell=(LevelUp)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(), spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
						
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
					}
				}
				if(c instanceof MultiShot){
					MultiShot spell=(MultiShot)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
					}
				}
				if(c instanceof Polymorph){
					Polymorph spell=(Polymorph)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(), spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							handX1+=140;	
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
					}
				}
				
				if(c instanceof SealOfChampions){
					SealOfChampions spell=(SealOfChampions)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
							
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof ShadowWordDeath){
					ShadowWordDeath spell=(ShadowWordDeath)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;	
						}catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof SiphonSoul){
					SiphonSoul spell=(SiphonSoul)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
							
							} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				if(c instanceof TwistingNether){
					TwistingNether spell=(TwistingNether)c;
					SpellPanels s=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(),spell);
					s.setBounds(handX1,750,140,200);
					handX1+=140;
					view.add(s);handOne.add(s);handOneButtons.add(s.getSpellButton());
					s.getSpellButton().setActionCommand("firstHeroSpell");
					s.getSpellButton().addActionListener(this);
					//if chromaggus is on field
					if(chrom&&clone){
						try {
							
							SpellPanels cln=new SpellPanels(spell.getManaCost(),spell.getRarity(),spell.getImage(), spell.clone());
							cln.setBounds(handX1,750,140,200);
							cln.getSpellButton().setActionCommand("firstHeroSpell");
							cln.getSpellButton().addActionListener(this);
							view.add(cln);
							handOne.add(cln);
							handOneButtons.add(cln.getSpellButton());
							
							handX1+=140;
							
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
						
					}
				}
				
			}
		deck1.setText(firstHero.getDeck().size()+"");
		deck2.setText(secondHero.getDeck().size()+"");
		puttingField2();
		puttingField1();
	//	fullHand1=false;
		
	}
	
	public static void main(String[] args) {
		
	
		
		new welcomeFrame();
	}


//drawing card for warlock
public ImageIcon burnedImage(Card c){
	
	if(c instanceof Minion)
		return ((Minion)c).getImage();
	
	if(c instanceof CurseOfWeakness)
		return ((CurseOfWeakness)c).getImage();
	if(c instanceof DivineSpirit)
		return ((DivineSpirit)c).getImage();
	if(c instanceof Flamestrike)
		return ((Flamestrike)c).getImage();
	if(c instanceof HolyNova)
		return ((HolyNova)c).getImage();
	if(c instanceof KillCommand)
		return ((KillCommand)c).getImage();
	if(c instanceof LevelUp)
		return ((LevelUp)c).getImage();
	if(c instanceof MultiShot)
		return ((MultiShot)c).getImage();
	if(c instanceof Polymorph)
		return ((Polymorph)c).getImage();
	if(c instanceof Pyroblast)
		return ((Pyroblast)c).getImage();
	if(c instanceof SealOfChampions)
		return ((SealOfChampions)c).getImage();
	if(c instanceof ShadowWordDeath)
		return ((ShadowWordDeath)c).getImage();
	if(c instanceof SiphonSoul)
		return ((SiphonSoul)c).getImage();
	if(c instanceof TwistingNether)
		return ((TwistingNether)c).getImage();
	
	return null;
}


public void playSound(String filePath) {
	try{
		AudioInputStream ais= AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(ais);
		clip.start();
	}
	catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
	{
		e.printStackTrace();
	}
}

	@Override
	public void onGameOver() {
		if(firstHero.getCurrentHP()==0){
			
			JOptionPane.showMessageDialog(view,name2.getText()+" is the winner" );
			
		}
		else{
			JOptionPane.showMessageDialog(view,name1.getText()+" is the winner" );
		}
		view.dispose();
	}
}
