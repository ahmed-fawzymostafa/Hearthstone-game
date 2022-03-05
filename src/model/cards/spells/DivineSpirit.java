package model.cards.spells;

import javax.swing.ImageIcon;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class DivineSpirit extends Spell implements MinionTargetSpell {
	ImageIcon image;
	public DivineSpirit() {
		super("Divine Spirit", 3, Rarity.BASIC);
		image=new ImageIcon("Spells/divine.png");
	}

	@Override
	public void performAction(Minion m) {
		m.setMaxHP(m.getMaxHP() * 2);
		m.setCurrentHP(m.getCurrentHP() * 2);

	}

	public ImageIcon getImage() {
		return image;
	}

}
