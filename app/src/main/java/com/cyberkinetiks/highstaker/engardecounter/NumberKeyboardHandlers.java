package com.cyberkinetiks.highstaker.engardecounter;

import android.util.Log;

/**
 * Created by highstaker on 31.01.16.
 */
public class NumberKeyboardHandlers {

    public EnGardeGameRoutine game_routine;

    /* modes:
    -1 - nothing
    0 - advance
    1 - retreat
    2 - attack
    3 - advance-lunge
    */
    private int mode = -1;

    //numbers picked by user
    int picked_nums[];

    NumberKeyboardHandlers(EnGardeGameRoutine gameroutine) {
        picked_nums = new int[]{0,0,0,0};
        game_routine = gameroutine;
    }

    public void setMode(int m)
    {
        mode = m;
    }

    public void cancelHandler() {
        resetNumerals();
    }

    private void resetNumerals()
    {
        picked_nums = new int[4];
        mode = -1;
    }

    public void OKHandler() {
        Log.d("CKdebug", String.format("OKhandler called with mode %d",1));

        switch (mode){
            case 0:advanceOKHandle();
                break;

            case 1:retreatOKHandle();
                break;

            case 2:attackOKHandle();
                break;

            default: break;
        }
    }

    public void advanceOKHandle()
    {
        game_routine.advance(picked_nums[0]);
        resetNumerals();
    }

    public void retreatOKHandle()
    {

    }

    public void attackOKHandle()
    {

    }

    public void numberHandler(int number) {
        Log.d("CKdebug", String.format("number %d handler called", number));

        switch (mode){
            case 0:advanceNumberHandle(number);
                break;

            case 1:retreatNumberHandle(number);
                break;

            case 2:attackNumberHandle(number);
                break;

            default: break;
        }
    }

    public void advanceNumberHandle(int number)
    {
        picked_nums[0] = number;
    }

    public void retreatNumberHandle(int number)
    {

    }

    public void attackNumberHandle(int number)
    {

    }

}
