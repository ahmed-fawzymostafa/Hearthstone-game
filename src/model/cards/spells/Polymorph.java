package model.cards.spells;

import javax.swing.ImageIcon;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class Polymorph extends Spell implements MinionTargetSpell {
	ImageIcon image;
	public Polymorph() {
		super("Polymorph", 4, Rarity.BASIC);
		image=new ImageIcon("Spells/polymorph.png");
	}

	@Override
	public void performAction(Minion m) {
		m.setCurrentHP(1);
		m.setMaxHP(1);
		m.setAttack(1);
		m.setName("Sheep");
		m.setSleeping(true);
		m.setTaunt(false);
		m.setDivine(false);
		m.setManaCost(1);
		m.setImage(new ImageIcon("Minions/sheep.jpg"));

	}

	public ImageIcon getImage() {
		return image;
	}
	
}
