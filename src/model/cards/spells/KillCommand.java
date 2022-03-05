package model.cards.spells;

import javax.swing.ImageIcon;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class KillCommand extends Spell implements MinionTargetSpell, HeroTargetSpell {
	ImageIcon image;
	public KillCommand() {
		super("Kill Command", 3, Rarity.COMMON);
		image=new ImageIcon("Spells/kill command.png");
	}

	@Override
	public void performAction(Hero h) {
		h.setCurrentHP(h.getCurrentHP() - 3);

	}

	@Override
	public void performAction(Minion m) {
		if (m.isDivine())
			m.setDivine(false);
		else
			m.setCurrentHP(m.getCurrentHP() - 5);

	}

	public ImageIcon getImage() {
		return image;
	}

}
