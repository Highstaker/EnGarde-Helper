package com.cyberkinetiks.highstaker.engardecounter;


import android.util.Log;

public class EnGardeGameRoutine {

    public int getRange(){
        return range;
    }

    public int getRemaining() {
        return remaining;
    }

    public int getPlayer1score() {
        return player1score;
    }

    public int getPlayer2score() {
        return player2score;
    }

    public int getCurrentPlayer() {
        return cur_player;
    }

    public int getPlayer1position() {
        return player1position;
    }

    public int getPlayer2position() {
        return player2position;
    }

    private int range;
    private int remaining = 15;
	private int player1score = 0;
	private int player2score = 0;
    private int cur_player;
    private int player1position = 0;
    private int player2position = 22;

	public EnGardeGameRoutine()
	{
        resetGame(1);
	}

    private void resetGame(int player)
    {
        Log.d("CKdebug", "resetGame called");

        range = 22;
        remaining = 15;
        player1score = 0;
        player2score = 0;
        player1position = 0;
        player2position = 22;
        cur_player = player;
    }

    private void switchPlayer()
    {
        if (cur_player == 1)
            cur_player = 2;
        else if (cur_player == 2)
            cur_player = 1;
    }

    public boolean canAttack()
    {
        return (range >= 1 && range <=5);
    }

    public boolean canAdvance()
    {
        return (range > 1);
    }

    public boolean canRetreat()
    {
        if (cur_player == 1)
        {
            return (player1position > 0);
        }
        else
        {
            return (player2position < 22);
        }
    }

    public boolean canAdvanceLunge()
    {
        return (range > 2 && range < 10);
    }

    public void advance(int steps)
    {
        if((range - steps) >= 1) {
            movePlayerForward(steps);
            remaining -= 1;
        }
    }

    public void retreat(int steps)
    {
        if((range + steps) <= 1) {
            movePlayerBackward(steps);
            remaining -= 1;
        }
    }

    public void attack(int steps, int power)
    {
        if(range == steps)
        {
            remaining -= power;
        }
    }

    public void parry(int power)
    {
        remaining -= power;
    }

    public void advancelunge(int move_steps, int attack_steps, int power)
    {
        if(range == (move_steps + attack_steps))
        {
            movePlayerForward(move_steps);
            remaining -= (power + 1);
        }
    }

    /*
    Called when a player wins.
    They receive a point, the game is reset and the loser starts the round.
     */
    public void playerWins(int player)
    {
        if (player == 1)
            player1score += 1;
            resetGame(2);
        if (player == 2)
            player1score += 1;
            resetGame(1);
    }

    private void movePlayerForward(int steps)
    {
        range -= steps;
        if (cur_player == 1)
        {
            player1position += steps;
        }
        if (cur_player == 2)
        {
            player2position -= steps;
        }
    }

    private void movePlayerBackward(int steps)
    {
        range -= steps;
        if (cur_player == 1)
        {
            player1position -= steps;
        }
        if (cur_player == 2)
        {
            player2position += steps;
        }
    }

}
