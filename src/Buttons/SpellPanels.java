package Buttons;
import javax.swing.*;

import java.awt.*;

import model.cards.*;
import model.cards.spells.*;
public class SpellPanels extends JPanel{
	private Card spell;
	private JTextArea txt;
	private ImageIcon image;
	private JButton spellButton;
	
	public SpellPanels(int Mana,Rarity rarity,ImageIcon image,Card spell){
		setLayout(null);
		String info ="Mana:"+Mana+"\nRarity:"+rarity;
		txt=new JTextArea(info);
		txt.setEditable(false);
		txt.setBounds(0,145,140,55);
		this.add(txt);
		
		spellButton=new JButton();
		spellButton.setBounds(0,0,140,145);
		spellButton.setIcon(image);
		this.add(spellButton);
		
		
		this.spell=spell;
		this.image=image;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image cur = toolkit.getImage("Sounds/clicker.png");
		Cursor c = toolkit.createCustomCursor(cur,new Point(spellButton.getX(),spellButton.getY()),"");
		spellButton.setCursor(c);
	}
	
	public Card getSpell() {
		return spell;
	}

	public ImageIcon getImage() {
		return image;
	}

	public JTextArea getTxt() {
		return txt;
	}
	public void setTxt(JTextArea txt) {
		this.txt = txt;
	}
	public JButton getSpellButton() {
		return spellButton;
	}
	public void setSpellButton(JButton spellButton) {
		this.spellButton = spellButton;
	}
	
	
}
