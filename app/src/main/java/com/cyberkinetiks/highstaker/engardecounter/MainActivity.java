package com.cyberkinetiks.highstaker.engardecounter;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button advanceButton, retreatButton, attackButton, advancelungeButton, okButton, cancelButton;
    private Button numberButtons[];
    EnGardeGameRoutine game_routine;
    NumberKeyboardHandlers numberKeyboardHandlers;

    /*modes
    -1 - nothing
    0 - advance
    1 - retreat
    2 - attack
    3 - advance-lunge
     */
//    int mode = -1;

    private void initializeButtons() {
        
        advanceButton = (Button)findViewById(R.id.buttonAdvance);
        advanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CKdebug", "advanceButton pressed");
                numberKeyboardHandlers.setMode(1);
                switchToNumberKeyboard();

                }
        }); //объект, выполняющий действие при нажатии кнопки


        retreatButton = (Button)findViewById(R.id.buttonRetreat);
        retreatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CKdebug", "retreatButton pressed");
                numberKeyboardHandlers.setMode(2);
                switchToNumberKeyboard();

                }
        }); //объект, выполняющий действие при нажатии кнопки

        attackButton = (Button)findViewById(R.id.buttonAttack);
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CKdebug", "attackButton pressed");
                numberKeyboardHandlers.setMode(3);
                switchToNumberKeyboard();

                }
        }); //объект, выполняющий действие при нажатии кнопки

        advancelungeButton = (Button)findViewById(R.id.buttonAdvanceLunge);
        advancelungeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CKdebug", "advancelungeButton pressed");
                numberKeyboardHandlers.setMode(4);
                switchToNumberKeyboard();

                }
        }); //объект, выполняющий действие при нажатии кнопки

        okButton = (Button)findViewById(R.id.buttonOK);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CKdebug", "OKButton pressed");
                numberKeyboardHandlers.OKHandler();
                switchToActionKeyboard();
                updateNumerals();
            }
        }); //объект, выполняющий действие при нажатии кнопки

        cancelButton = (Button)findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CKdebug", "cancelButton pressed");
                numberKeyboardHandlers.cancelHandler();
                switchToActionKeyboard();

            }
        }); //объект, выполняющий действие при нажатии кнопки

        //initialize number buttons
        numberButtons = new Button[5];
        for(int i=0; i<5; i++)
        {
            final int INDEXE = i+1;
            int buttonID = getResources().getIdentifier(String.format("button%d", (i+1)),
                    "id", getPackageName());
            numberButtons[i] = (Button)findViewById(buttonID);

            numberButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CKdebug", String.format("number %d pressed",INDEXE));
                    numberKeyboardHandlers.numberHandler(INDEXE);
                }
            }); //объект, выполняющий действие при нажатии кнопки

        }

    }

    private void switchToNumberKeyboard()
    {
        LinearLayout numberKeyboard = (LinearLayout)findViewById(R.id.numberButtonsLayout);
        LinearLayout actionKeyboard = (LinearLayout)findViewById(R.id.actionButtonsLayout);
        actionKeyboard.setVisibility(View.GONE);
        numberKeyboard.setVisibility(View.VISIBLE);
    }

    private void switchToActionKeyboard()
    {
        // make unavailable action buttons invisible
        if(game_routine.canAttack())
        {
            attackButton.setVisibility(View.VISIBLE);
        }
        else
        {
            attackButton.setVisibility(View.GONE);
        }

        if(game_routine.canRetreat())
        {
            retreatButton.setVisibility(View.VISIBLE);
        }
        else
        {
            retreatButton.setVisibility(View.GONE);
        }

        if(game_routine.canAdvanceLunge())
        {
            advancelungeButton.setVisibility(View.VISIBLE);
        }
        else
        {
            advancelungeButton.setVisibility(View.GONE);
        }

        if(game_routine.canAdvance())
        {
            advanceButton.setVisibility(View.VISIBLE);
        }
        else
        {
            advanceButton.setVisibility(View.GONE);
        }

        //hide numbers, show actions
        LinearLayout numberKeyboard = (LinearLayout)findViewById(R.id.numberButtonsLayout);
        LinearLayout actionKeyboard = (LinearLayout)findViewById(R.id.actionButtonsLayout);
        numberKeyboard.setVisibility(View.GONE);
        actionKeyboard.setVisibility(View.VISIBLE);
    }

    private void updateNumerals()
    {
        TextView rangeField = (TextView)findViewById(R.id.range_number);
        rangeField.setText(String.valueOf(game_routine.getRange()));

        TextView remainingField = (TextView)findViewById(R.id.remaining_number);
        remainingField.setText(String.valueOf(game_routine.getRemaining()));

        TextView player1scoreField = (TextView)findViewById(R.id.player1score);
        player1scoreField.setText(String.valueOf(game_routine.getPlayer1score()));

        TextView player2scoreField = (TextView)findViewById(R.id.player2score);
        player2scoreField.setText(String.valueOf(game_routine.getPlayer2score()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("CKdebug", "onCreate() invoked");

        game_routine = new EnGardeGameRoutine();
        numberKeyboardHandlers = new NumberKeyboardHandlers(game_routine);
        initializeButtons();

        updateNumerals();

        //debug crap
//        game_routine = new EnGardeGameRoutine();
//        EnGardeGameRoutine game_routine2 = game_routine;
//        Log.d("CKdebug", String.format("game_routine range %d", game_routine.range));
//        Log.d("CKdebug", String.format("game_routine2 range %d", game_routine2.range));
//        game_routine2.range -= 5;
//        Log.d("CKdebug", String.format("game_routine range %d", game_routine.range));
//        Log.d("CKdebug", String.format("game_routine2 range %d", game_routine2.range));
//        System.out.print(game_routine.range);
//        System.out.print(game_routine2.range);
        //end debug crap

        //show action keyboard
        switchToActionKeyboard();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
