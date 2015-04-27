package com.example.hello_world;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

@SuppressLint("NewApi")
public class Help extends Activity {
	//Variables for widgets
	View bttrans;
	Button btBacktogame;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);//Call the layout (screen)
		
		btBacktogame = (Button) findViewById(R.id.btBackgame);
		
		eventBackgame();
	}
	
	public void eventBackgame(){
		// Event OnClick to tutorial
				btBacktogame.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
		
	}
}