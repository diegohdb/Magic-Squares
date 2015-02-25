package com.example.hello_world;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Intro extends Activity {
	View bttrans;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);

		bttrans = findViewById(R.id.bttrans);

		eventoBtstart();

	}

	public void eventoBtstart() {
		bttrans.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { //

				Toast.makeText(Intro.this,
						"@diegohenrique - diegohdb@gmail.com",
						Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(Intro.this, MainActivity.class);
				startActivity(intent);

			}
		});
	}

}