package model.cards.spells;

import javax.swing.ImageIcon;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class SealOfChampions extends Spell implements MinionTargetSpell {
	ImageIcon image;
	public SealOfChampions() {
		super("Seal of Champions", 3, Rarity.COMMON);
		image=new ImageIcon("Spells/seal.png");
	}

	@Override
	public void performAction(Minion m) {
		m.setAttack(m.getAttack() + 3);
		m.setDivine(true);

	}

	public ImageIcon getImage() {
		return image;
	}

}
