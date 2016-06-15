package org.angryautomata.game;

import javafx.scene.paint.Color;
import org.angryautomata.Player;
import org.angryautomata.game.scenery.Scenery;

public class Population
{
	public static final int GRADIENT_MIN = 0, GRADIENT_MAX = 100, GRADIENT_INIT = 50;

	private final Player player;
	private final int team;
	private int state;
	private int gradient = GRADIENT_INIT;
	private Position position, prev;
	private boolean hasPlayed = false;

	public Population(Player player, int state, int team, Position position)
	{
		this.player = player;
		this.state = state;
		this.team = team;
		this.position = position;
		prev = position;

		player.addPopulation(this);
	}

	public Player getPlayer()
	{
		return player;
	}

	public int getState()
	{
		return state;
	}

	public int nextState(int symbol)
	{
		return state = player.getAutomaton().nextState(state, symbol);
	}

	public int getGradient()
	{
		return gradient;
	}

	public int getTeam()
	{
		return team;
	}

	public Position getPosition()
	{
		return position;
	}

	public Position getPreviousPosition()
	{
		return prev;
	}

	public void moveTo(Position position)
	{
		prev = this.position;
		this.position = position;
	}

	public Color getColor()
	{
		return player.getColor();
	}

	public void updateGradient(int grad)
	{
		gradient += grad;
	}

	public boolean isDead()
	{
		return gradient <= GRADIENT_MIN;
	}

	public boolean canClone()
	{
		return gradient >= GRADIENT_MAX;
	}

	public Population createClone()
	{
		Position clonePos = new Position(position.getX() + (int) (Math.random() * 3.0D) - 1, position.getY() + (int) (Math.random() * 3.0D) - 1);

		Population clone = new Population(player, 0, team, clonePos);

		clone.updateGradient(-GRADIENT_INIT);

		int splitGradient = gradient / 2;

		updateGradient(-splitGradient);
		clone.updateGradient(splitGradient);

		return clone;
	}

	public void die()
	{
		player.removePopulation(this);
	}

	public boolean isOnOwnAutomaton()
	{
		Automaton automaton = player.getAutomaton();
		Position origin = automaton.getOrigin();
		int originX = origin.getX(), originY = origin.getY();
		int x = position.getX(), y = position.getY();

		return x >= originX && x < originX + automaton.numberOfStates() && y >= originY && y < originY + Scenery.sceneries();
	}

	public boolean comesFrom(Position position)
	{
		return prev.equals(position);
	}

	public boolean isOnSameTeamAs(Population population)
	{
		return team == population.getTeam();
	}

	public boolean hasPlayed()
	{
		return hasPlayed;
	}

	public void played(boolean hasPlayed)
	{
		this.hasPlayed = hasPlayed;
	}
}