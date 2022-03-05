package model.cards.spells;

import javax.swing.ImageIcon;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class ShadowWordDeath extends Spell implements MinionTargetSpell {
	ImageIcon image;
	public ShadowWordDeath() {
		super("Shadow Word: Death", 3, Rarity.BASIC);
		image=new ImageIcon("Spells/shadow.png");
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		if (m.getAttack() < 5)
			throw new InvalidTargetException("Choose a minion with 5 or more attack");
		m.setCurrentHP(0);

	}

	public ImageIcon getImage() {
		return image;
	}
	

}
