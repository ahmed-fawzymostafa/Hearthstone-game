package Buttons;
import javax.swing.*;

import java.awt.*;

import model.cards.minions.*;
import model.cards.*;
public class MinionPanels extends JPanel{
	private Card minion;
	private JTextArea txt;
	private JButton minionButton;
	private ImageIcon image;
	
	public MinionPanels(int HP,int attack, int Mana,Rarity rarity,boolean taunt,boolean charge,boolean divine,ImageIcon image,Minion minion){
		//setSize(140,200);
		setLayout(null);
		String info="HP:"+HP+"  Attack:"+attack+" Mana:"+Mana+"\nRarity:"+rarity+"\n";
		if(taunt)
			info+="Taunt   ";
		if(divine)
			info+="Divine  ";
		if(charge)
			info+="Charge";
		else
			info+="Sleeping";
		
		txt=new JTextArea(info);
		txt.setBounds(0,145,140,55);
		txt.setEditable(false);
		this.add(txt);
		minionButton=new JButton();
		minionButton.setBounds(0,0,140,145);
		minionButton.setIcon(image);
		this.add(minionButton);
		this.minion=minion;
		this.image=image;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image cur = toolkit.getImage("Sounds/clicker.png");
		Cursor c = toolkit.createCustomCursor(cur,new Point(minionButton.getX(),minionButton.getY()),"");
		minionButton.setCursor(c);
	
	}
	public JButton getMinionButton() {
		return minionButton;
	}
	public void setMinionButton(JButton minionButton) {
		this.minionButton = minionButton;
	}
	public void setTxt(JTextArea txt) {
		this.txt = txt;
	}
	public JTextArea getTxt() {
		return txt;
	}
	
	public Minion getMinion() {
		return (Minion) minion;
	}
	public void setMinion(Minion minion) {
		this.minion = minion;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public void setMinion(Card minion) {
		this.minion = minion;
	}
	
	
	
	
	
	





	
	
}
