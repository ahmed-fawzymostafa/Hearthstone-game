package model.cards.spells;

import javax.swing.ImageIcon;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class SiphonSoul extends Spell implements LeechingSpell {
	ImageIcon image;
	public SiphonSoul() {
		super("Siphon Soul", 6, Rarity.RARE);
		image=new ImageIcon("Spells/siphon.png");
	}

	@Override
	public int performAction(Minion m) {
		m.setCurrentHP(0);
		return 3;
	}

	public ImageIcon getImage() {
		return image;
	}

}
