package model.cards.spells;

import javax.swing.ImageIcon;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Pyroblast extends Spell implements HeroTargetSpell, MinionTargetSpell {
	ImageIcon image;
	public Pyroblast() {
		super("Pyroblast", 10, Rarity.EPIC);
		image=new ImageIcon("Spells/pyro.png");
	}

	@Override
	public void performAction(Minion m) {
		if (m.isDivine())
			m.setDivine(false);
		else
			m.setCurrentHP(m.getCurrentHP() - 10);

	}

	@Override
	public void performAction(Hero h) {
		h.setCurrentHP(h.getCurrentHP() - 10);

	}

	public ImageIcon getImage() {
		return image;
	}

}
