package model.cards.spells;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class CurseOfWeakness extends Spell implements AOESpell {
	ImageIcon image;
	public CurseOfWeakness() {
		super("Curse of Weakness", 2, Rarity.RARE);
		image=new ImageIcon("Spells/curse.png");
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		for (int i = 0; i < oppField.size(); i++) {
			oppField.get(i).setAttack(oppField.get(i).getAttack() - 2);
		}

	}

	public ImageIcon getImage() {
		return image;
	}
	

}
