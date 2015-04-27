package com.example.hello_world;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Intro extends Activity {
	//Variables for widgets
	View bttrans;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);//Call the layout (screen)
		// Don`t allow the keybord pop up until the EditText be touched
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		//Define the variable associated for each widget at the layout file (xml)
		bttrans = findViewById(R.id.bttrans);

		//Call the method 
		eventoBtstart(); //Call the method to begin the game - Changing the intent

	}
	//Method to change the Intent to begin the game selection
	public void eventoBtstart() {
		// Event OnClick to change the Intent
		bttrans.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { //
				//Show the Toast for short time - My namelogo and email
				Toast.makeText(Intro.this,
						"@diegohenrique - diegohdb@gmail.com",
						Toast.LENGTH_SHORT).show();
				//Function to change the Intent(class) - Intro.java for MainActivity.java
				Intent intent = new Intent(Intro.this, MainActivity.class);
				startActivity(intent);

			}
		});
	}

}