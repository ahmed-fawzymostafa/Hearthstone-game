 package model.cards.spells;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell {
	ImageIcon image;
	private int shotTarget1,shotTarget2;
	public MultiShot() {
		super("Multi-Shot", 4, Rarity.BASIC);
		image=new ImageIcon("Spells/multi.png");
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {

		if (oppField.size() == 1)
			affectMinion(oppField.get(0));
		else if (oppField.size() > 1) {
			int r1 = 0;
			int r2 = 0;
			ArrayList<Minion> pool = new ArrayList<Minion>();
			pool.addAll(oppField);
			while (r1 == r2) {
				r1 = (int) (Math.random() * pool.size());
				r2 = (int) (Math.random() * pool.size());
			}
			shotTarget1=r1;
			shotTarget2=r2;
			affectMinion(pool.get(r1));
			affectMinion(pool.get(r2));
		}

	}

	private static void affectMinion(Minion m) {
		if (m.isDivine())
			m.setDivine(false);
		else
			m.setCurrentHP(m.getCurrentHP() - 3);
	}

	public ImageIcon getImage() {
		return image;
	}

	public int getShotTarget1() {
		return shotTarget1;
	}

	public int getShotTarget2() {
		return shotTarget2;
	}

}
