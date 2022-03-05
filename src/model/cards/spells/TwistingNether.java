package model.cards.spells;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell {
	ImageIcon image;
	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);
		image=new ImageIcon("Spells/twist.png");
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		while (!oppField.isEmpty()) {

			oppField.get(0).setCurrentHP(0);

		}
		while (!curField.isEmpty()) {

			curField.get(0).setCurrentHP(0);

		}

	}

	public ImageIcon getImage() {
		return image;
	}

}
