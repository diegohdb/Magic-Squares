package com.example.hello_world;

import com.example.hello_world.util.Toolkit;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MS5x5 extends Activity {
	// Variable for Database
	public SQLiteDatabase bancoDados = null;
	public Cursor cursor;

	// Variables for widgets
	String numX, numY, numD, num1, num2, num3;
	EditText et11, et12, et13, et14, et15, et21, et22, et23, et24, et25, et31,
			et32, et33, et34, et35, et41, et42, et43, et44, et45, et51, et52,
			et53, et54, et55;
	Button btCheck, btQuit, btSave, btNext, btDelete, btPrevious, btHelp;
	TextView sumX1, sumX2, sumX3, sumX4, sumX5, sumY1, sumY2, sumY3, sumY4,
			sumY5, sumD1, sumD2;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ms5x5);// Call the layout (screen)

		// Don`t allow the keybord pop up until the EditText be touched
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		// Define the variable associated for each widget at the layout file
		// (xml)
		et11 = (EditText) findViewById(R.id.et11);
		et12 = (EditText) findViewById(R.id.et12);
		et13 = (EditText) findViewById(R.id.et13);
		et14 = (EditText) findViewById(R.id.et14);
		et15 = (EditText) findViewById(R.id.et15);
		et21 = (EditText) findViewById(R.id.et21);
		et22 = (EditText) findViewById(R.id.et22);
		et23 = (EditText) findViewById(R.id.et23);
		et24 = (EditText) findViewById(R.id.et24);
		et25 = (EditText) findViewById(R.id.et25);
		et31 = (EditText) findViewById(R.id.et31);
		et32 = (EditText) findViewById(R.id.et32);
		et33 = (EditText) findViewById(R.id.et33);
		et34 = (EditText) findViewById(R.id.et34);
		et35 = (EditText) findViewById(R.id.et35);
		et41 = (EditText) findViewById(R.id.et41);
		et42 = (EditText) findViewById(R.id.et42);
		et43 = (EditText) findViewById(R.id.et43);
		et44 = (EditText) findViewById(R.id.et44);
		et45 = (EditText) findViewById(R.id.et45);
		et51 = (EditText) findViewById(R.id.et51);
		et52 = (EditText) findViewById(R.id.et52);
		et53 = (EditText) findViewById(R.id.et53);
		et54 = (EditText) findViewById(R.id.et54);
		et55 = (EditText) findViewById(R.id.et55);
		sumX1 = (TextView) findViewById(R.id.SumXResult1);
		sumX2 = (TextView) findViewById(R.id.SumXResult2);
		sumX3 = (TextView) findViewById(R.id.SumXResult3);
		sumX4 = (TextView) findViewById(R.id.SumXResult4);
		sumX5 = (TextView) findViewById(R.id.SumXResult5);
		sumY1 = (TextView) findViewById(R.id.SumYResult1);
		sumY2 = (TextView) findViewById(R.id.SumYResult2);
		sumY3 = (TextView) findViewById(R.id.SumYResult3);
		sumY4 = (TextView) findViewById(R.id.SumYResult4);
		sumY5 = (TextView) findViewById(R.id.SumYResult5);
		sumD1 = (TextView) findViewById(R.id.SumDResult1);
		sumD2 = (TextView) findViewById(R.id.SumDResult2);

		btCheck = (Button) findViewById(R.id.btCheck);
		btQuit = (Button) findViewById(R.id.btExit);
		btSave = (Button) findViewById(R.id.btSave);
		btHelp = (Button) findViewById(R.id.btHelp);
		// Call the method
		selectSq55(); // Call the method to choose a new game or a previous
						// saved
		eventBtquit(); // Call the method to quit the game
		eventBtsave(); // Call the method to save the game
		eventBtcheck(); // Call the method to check the game
		eventBthelp(); // call the method to pop up the tutorial
	}

	// Method to choose a new game or a previous saved
	public void selectSq55() {
		// Pop up the dialog box to choose if the game is new or previous saved
		AlertDialog.Builder builder = new AlertDialog.Builder(MS5x5.this);
		builder.setTitle("Attention");
		builder.setMessage("Do you wish to start a new game or continue a previous one?");
		builder.setPositiveButton("New", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// Action for the button New
			}
		});
		builder.setNegativeButton("Continue",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// Action for the button Continue
						openorcreateBank(); // call the method to open the
											// database
						searchData(); // call the method to check if are data
										// in the database
						getData(); // call the method to check the data in
									// the database if there is some
					}
				});
		builder.show();

	}

	public void eventBthelp() {
		// Event OnClick to tutorial
		btHelp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MS5x5.this, Help.class);
				startActivity(intent);
			}
		});
	}

	// Method to quit the game
	public void eventBtquit() {
		// Event OnClick to quit the game
		btQuit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Pop up the dialog box to choose if want quit or not
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MS5x5.this);
				builder.setTitle("Attention");
				builder.setMessage("Are you sure you want quit this game?");
				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// Action for the button Yes
								finish();// Close the intent
							}
						});
				builder.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// Action for the button Continue
							}
						});
				builder.show();
			}
		});
	}

	// Method to save the values from the square in the database
	public void eventBtsave() {
		// Action that happens if the button save is clicked
		btSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Open the database to save
				openorcreateBank();

				// Initialize values with 0
				String i11 = "0";
				String i12 = "0";
				String i13 = "0";
				String i14 = "0";
				String i15 = "0";

				String i21 = "0";
				String i22 = "0";
				String i23 = "0";
				String i24 = "0";
				String i25 = "0";
				String i31 = "0";
				String i32 = "0";
				String i33 = "0";
				String i34 = "0";
				String i35 = "0";
				String i41 = "0";
				String i42 = "0";
				String i43 = "0";
				String i44 = "0";
				String i45 = "0";
				String i51 = "0";
				String i52 = "0";
				String i53 = "0";
				String i54 = "0";
				String i55 = "0";
				// Check if there is some value in the square, if not put 0
				try {

					if (et11.getText().toString().isEmpty()) {
						i11 = "0";
					} else {
						i11 = et11.getText().toString();
					}
				} catch (Exception e) {
					i11 = "0";
				}
				try {
					if (et13.getText().toString().isEmpty()) {
						i12 = "0";
					} else {
						i12 = et12.getText().toString();
					}

				} catch (Exception e) {
					i12 = "0";
				}
				try {
					if (et13.getText().toString().isEmpty()) {
						i13 = "0";
					} else {
						i13 = et13.getText().toString();
					}

				} catch (Exception e) {
					i13 = "0";
				}
				try {
					if (et14.getText().toString().isEmpty()) {
						i14 = "0";
					} else {
						i14 = et14.getText().toString();
					}

				} catch (Exception e) {
					i14 = "0";
				}
				try {
					if (et15.getText().toString().isEmpty()) {
						i15 = "0";
					} else {
						i15 = et15.getText().toString();
					}

				} catch (Exception e) {
					i15 = "0";
				}

				try {
					if (et21.getText().toString().isEmpty()) {
						i21 = "0";
					} else {
						i21 = et21.getText().toString();
					}

				} catch (Exception e) {
					i21 = "0";
				}
				try {
					if (et22.getText().toString().isEmpty()) {
						i22 = "0";
					} else {
						i22 = et22.getText().toString();
					}
				} catch (Exception e) {
					i22 = "0";
				}
				try {
					if (et23.getText().toString().isEmpty()) {
						i23 = "0";
					} else {
						i23 = et23.getText().toString();
					}

				} catch (Exception e) {
					i23 = "0";
				}
				try {
					if (et24.getText().toString().isEmpty()) {
						i24 = "0";
					} else {
						i24 = et24.getText().toString();
					}

				} catch (Exception e) {
					i24 = "0";
				}
				try {
					if (et25.getText().toString().isEmpty()) {
						i25 = "0";
					} else {
						i25 = et25.getText().toString();
					}

				} catch (Exception e) {
					i25 = "0";
				}

				try {
					if (et31.getText().toString().isEmpty()) {
						i31 = "0";
					} else {
						i31 = et31.getText().toString();
					}
				} catch (Exception e) {
					i31 = "0";
				}
				try {
					if (et32.getText().toString().isEmpty()) {
						i32 = "0";
					} else {
						i32 = et32.getText().toString();
					}
				} catch (Exception e) {
					i32 = "0";
				}

				try {
					if (et33.getText().toString().isEmpty()) {
						i33 = "0";
					} else {
						i33 = et33.getText().toString();
					}

				} catch (Exception e) {
					i33 = "0";
				}
				try {
					if (et34.getText().toString().isEmpty()) {
						i34 = "0";
					} else {
						i34 = et34.getText().toString();
					}

				} catch (Exception e) {
					i34 = "0";
				}
				try {
					if (et35.getText().toString().isEmpty()) {
						i35 = "0";
					} else {
						i35 = et35.getText().toString();
					}

				} catch (Exception e) {
					i35 = "0";
				}

				try {
					if (et41.getText().toString().isEmpty()) {
						i41 = "0";
					} else {
						i41 = et41.getText().toString();
					}
				} catch (Exception e) {
					i41 = "0";
				}
				try {
					if (et42.getText().toString().isEmpty()) {
						i42 = "0";
					} else {
						i42 = et42.getText().toString();
					}
				} catch (Exception e) {
					i42 = "0";
				}

				try {
					if (et43.getText().toString().isEmpty()) {
						i43 = "0";
					} else {
						i43 = et43.getText().toString();
					}

				} catch (Exception e) {
					i43 = "0";
				}
				try {
					if (et44.getText().toString().isEmpty()) {
						i44 = "0";
					} else {
						i44 = et44.getText().toString();
					}

				} catch (Exception e) {
					i44 = "0";
				}
				try {
					if (et45.getText().toString().isEmpty()) {
						i45 = "0";
					} else {
						i45 = et45.getText().toString();
					}

				} catch (Exception e) {
					i45 = "0";
				}
				try {
					if (et51.getText().toString().isEmpty()) {
						i51 = "0";
					} else {
						i51 = et51.getText().toString();
					}
				} catch (Exception e) {
					i51 = "0";
				}
				try {
					if (et52.getText().toString().isEmpty()) {
						i52 = "0";
					} else {
						i52 = et52.getText().toString();
					}
				} catch (Exception e) {
					i52 = "0";
				}

				try {
					if (et53.getText().toString().isEmpty()) {
						i53 = "0";
					} else {
						i53 = et53.getText().toString();
					}

				} catch (Exception e) {
					i53 = "0";
				}
				try {
					if (et54.getText().toString().isEmpty()) {
						i54 = "0";
					} else {
						i54 = et54.getText().toString();
					}

				} catch (Exception e) {
					i54 = "0";
				}
				try {
					if (et55.getText().toString().isEmpty()) {
						i55 = "0";
					} else {
						i55 = et55.getText().toString();
					}

				} catch (Exception e) {
					i55 = "0";
				}

				// Call the method to save the data in the database
				saveData(i11, i12, i13, i14, i15, i21, i22, i23, i24, i25, i31,
						i32, i33, i34, i35, i41, i42, i43, i44, i45, i51, i52,
						i53, i54, i55);
			}
		});
	}

	// Method to check the conditions to win the game
	public void eventBtcheck() {
		// Event OnClick to check after click the button
		btCheck.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Initializing all the values with 0
				int i11 = 0;
				int i12 = 0;
				int i13 = 0;
				int i14 = 0;
				int i15 = 0;
				int i21 = 0;
				int i22 = 0;
				int i23 = 0;
				int i24 = 0;
				int i25 = 0;
				int i31 = 0;
				int i32 = 0;
				int i33 = 0;
				int i34 = 0;
				int i35 = 0;
				int i41 = 0;
				int i42 = 0;
				int i43 = 0;
				int i44 = 0;
				int i45 = 0;
				int i51 = 0;
				int i52 = 0;
				int i53 = 0;
				int i54 = 0;
				int i55 = 0;
				
				// Try to pick every value from the square
				// Catch if nothing put 0
				try {
					i11 = Integer.parseInt(et11.getText().toString());
				} catch (Exception e) {
					i11 = 0;
				}
				try {
					i12 = Integer.parseInt(et12.getText().toString());
				} catch (Exception e) {
					i12 = 0;
				}
				try {
					i13 = Integer.parseInt(et13.getText().toString());
				} catch (Exception e) {
					i13 = 0;
				}
				try {
					i14 = Integer.parseInt(et14.getText().toString());
				} catch (Exception e) {
					i14 = 0;
				}
				try {
					i15 = Integer.parseInt(et15.getText().toString());
				} catch (Exception e) {
					i15 = 0;
				}
				try {
					i21 = Integer.parseInt(et21.getText().toString());
				} catch (Exception e) {
					i21 = 0;
				}

				try {
					i22 = Integer.parseInt(et22.getText().toString());
				} catch (Exception e) {
					i22 = 0;
				}

				try {
					i23 = Integer.parseInt(et23.getText().toString());
				} catch (Exception e) {
					i23 = 0;
				}
				try {
					i24 = Integer.parseInt(et24.getText().toString());
				} catch (Exception e) {
					i24 = 0;
				}
				try {
					i25 = Integer.parseInt(et25.getText().toString());
				} catch (Exception e) {
					i25 = 0;
				}
				try {
					i31 = Integer.parseInt(et31.getText().toString());
				} catch (Exception e) {
					i31 = 0;
				}
				try {
					i32 = Integer.parseInt(et32.getText().toString());
				} catch (Exception e) {
					i32 = 0;
				}

				try {
					i33 = Integer.parseInt(et33.getText().toString());
				} catch (Exception e) {
					i33 = 0;
				}
				try {
					i34 = Integer.parseInt(et34.getText().toString());
				} catch (Exception e) {
					i34 = 0;
				}try {
					i35 = Integer.parseInt(et35.getText().toString());
				} catch (Exception e) {
					i35 = 0;
				}
				try {
					i41 = Integer.parseInt(et41.getText().toString());
				} catch (Exception e) {
					i41 = 0;
				}
				try {
					i42 = Integer.parseInt(et42.getText().toString());
				} catch (Exception e) {
					i42 = 0;
				}

				try {
					i43 = Integer.parseInt(et43.getText().toString());
				} catch (Exception e) {
					i43 = 0;
				}
				try {
					i44 = Integer.parseInt(et44.getText().toString());
				} catch (Exception e) {
					i44 = 0;
				}
				try {
					i45 = Integer.parseInt(et45.getText().toString());
				} catch (Exception e) {
					i45 = 0;
				}
				try {
					i51 = Integer.parseInt(et51.getText().toString());
				} catch (Exception e) {
					i51 = 0;
				}
				try {
					i52 = Integer.parseInt(et52.getText().toString());
				} catch (Exception e) {
					i52 = 0;
				}

				try {
					i53 = Integer.parseInt(et53.getText().toString());
				} catch (Exception e) {
					i53 = 0;
				}
				try {
					i54 = Integer.parseInt(et54.getText().toString());
				} catch (Exception e) {
					i54 = 0;
				}
				try {
					i55 = Integer.parseInt(et55.getText().toString());
				} catch (Exception e) {
					i55 = 0;
				}

				// Sum the horizontal values
				int SumH1 = i11 + i12 + i13 + i14 + i15;
				int SumH2 = i21 + i22 + i23 + i24 + i25;
				int SumH3 = i31 + i32 + i33 + i34 + i35;
				int SumH4 = i41 + i42 + i43 + i44 + i45;
				int SumH5 = i51 + i52 + i53 + i54 + i55;

				// Sum the vertical values
				int SumV1 = i11 + i21 + i31 + i41 + i51;
				int SumV2 = i12 + i22 + i32 + i42 + i52;
				int SumV3 = i13 + i23 + i33 + i43 + i53;
				int SumV4 = i14 + i24 + i34 + i44 + i54;
				int SumV5 = i15 + i25 + i35 + i45 + i55;

				// Sum the diagonal values
				int SumD1 = i11 + i22 + i33 + i44 + i55;
				int SumD2 = i15 + i24 + i33 + i42 + i51;

				// Set the values in the Horizontal Sum
				sumX1.setText(String.valueOf(SumH1));
				sumX2.setText(String.valueOf(SumH2));
				sumX3.setText(String.valueOf(SumH3));
				sumX4.setText(String.valueOf(SumH4));
				sumX5.setText(String.valueOf(SumH5));
				// Set the values in the Vertical Sum
				sumY1.setText(String.valueOf(SumV1));
				sumY2.setText(String.valueOf(SumV2));
				sumY3.setText(String.valueOf(SumV3));
				sumY4.setText(String.valueOf(SumV4));
				sumY5.setText(String.valueOf(SumV5));
				// Set the values in the Diagonal Sum
				sumD1.setText(String.valueOf(SumD1));
				sumD2.setText(String.valueOf(SumD2));

				// Call the method testSolution with Sum and squares values
				testSolution(SumH1, SumH2, SumH3, SumH4, SumH5, SumV1, SumV2, SumV3,
						SumV4, SumV5, SumD1, SumD2, i11, i12, i13, i14, i15, i21, i22, i23,
						i24, i25, i31, i32, i33, i34, i35, i41, i42, i43, i44, i45, i51, i52, i53, i54, i55);
			}
		});
	}

	// Method to check if the sum values are equals
	public void testSolution(int SumH1, int SumH2, int SumH3, int SumH4,
			int SumH5, int SumV1, int SumV2, int SumV3, int SumV4, int SumV5,
			int SumD1, int SumD2, int i11, int i12, int i13, int i14, int i15,
			int i21, int i22, int i23, int i24, int i25, int i31, int i32,
			int i33, int i34, int i35, int i41, int i42, int i43, int i44,
			int i45, int i51, int i52, int i53, int i54, int i55) {
		// Test if are the sum value is equal in each line
		if (SumH1 == SumH2 && SumH2 == SumH3 && SumH3 == SumH4
				&& SumH4 == SumH5 && SumH5 == SumV1 && SumV1 == SumV2
				&& SumV2 == SumV3 && SumV3 == SumV4 && SumV4 == SumV5
				&& SumV5 == SumD1 && SumD1 == SumD2) {
			// If they are test if are duplicated values in the squares
			testDuplicates(i11, i12, i13, i14, i15, i21, i22, i23, i24, i25,
					i31, i32, i33, i34, i35, i41, i42, i43, i44, i45, i51, i52,
					i53, i54, i55);// Call
			// the
			// method
			// testDuplicates
			// with
			// the
			// square
			// values
		} else {
			Toolkit.showMessage("Warning",
					"The sum values are different, keep trying!", MS5x5.this);
		}
	}

	// Method to test if there is duplicated square values
	public void testDuplicates(int i11, int i12, int i13, int i14, int i15,
			int i21, int i22, int i23, int i24, int i25, int i31, int i32,
			int i33, int i34, int i35, int i41, int i42, int i43, int i44,
			int i45, int i51, int i52, int i53, int i54, int i55) {
		// Test if are square values duplicated if it is show the warning
		// message to the user
		// If have none duplicated values show the congratulation message - End
		// of Game - User Win!
		if (i11 == i12 || i11 == i13 || i11 == i14 || i11 == i15 || i12 == i13
				|| i12 == i14 || i12 == i15 || i13 == i14 || i13 == i15
				|| i14 == i15) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at horizontal 01", MS5x5.this);
		} else if (i21 == i22 || i21 == i23 || i21 == i24 || i21 == i25
				|| i22 == i23 || i22 == i24 || i22 == i25 || i23 == i24
				|| i23 == i25 || i24 == i25) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at horizontal 02", MS5x5.this);
		} else if (i31 == i32 || i31 == i33 || i31 == i34 || i31 == i35
				|| i32 == i33 || i32 == i34 || i32 == i35 || i33 == i34
				|| i33 == i35 || i34 == i35) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at horizontal 03", MS5x5.this);
		} else if (i41 == i42 || i41 == i43 || i41 == i44 || i41 == i45
				|| i42 == i43 || i42 == i44 || i42 == i45 || i43 == i44
				|| i43 == i45 || i44 == i45) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at horizontal 04", MS5x5.this);
		} else if (i51 == i52 || i51 == i53 || i51 == i54 || i51 == i55
				|| i52 == i53 || i52 == i54 || i52 == i55 || i53 == i54
				|| i53 == i55 || i54 == i55) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at horizontal 05", MS5x5.this);
		} else if (i11 == i21 || i11 == i31 || i11 == i41 || i11 == i51
				|| i21 == i31 || i21 == i41 || i21 == i51 || i31 == i41
				|| i31 == i51 || i41 == i51) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at vertical 01", MS5x5.this);
		} else if (i12 == i22 || i12 == i32 || i12 == i42 || i12 == i52
				|| i22 == i32 || i22 == i42 || i22 == i52 || i32 == i42
				|| i32 == i52 || i42 == i52) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at vertical 02", MS5x5.this);
		} else if (i13 == i23 || i13 == i33 || i13 == i43 || i13 == i53
				|| i23 == i33 || i23 == i43 || i23 == i53 || i33 == i43
				|| i33 == i53 || i43 == i53) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at vertical 03", MS5x5.this);
		} else if (i14 == i24 || i14 == i34 || i14 == i44 || i14 == i54
				|| i24 == i34 || i24 == i44 || i24 == i54 || i34 == i44
				|| i34 == i54 || i44 == i54) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at vertical 04", MS5x5.this);
		} else if (i15 == i25 || i15 == i35 || i15 == i45 || i15 == i55
				|| i25 == i35 || i25 == i45 || i25 == i55 || i35 == i45
				|| i35 == i55 || i45 == i55) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at vertical 05", MS5x5.this);
		} else if (i11 == i22 || i11 == i33 || i11 == i44 || i11 == i55
				|| i22 == i33 || i22 == i44 || i22 == i55 || i33 == i44
				|| i33 == i55 || i44 == i55) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at diagonal 01", MS5x5.this);
		} else if (i15 == i24 || i15 == i33 || i15 == i42 || i15 == i51
				|| i33 == i42 || i33 == i42 || i33 == i51 || i42 == i51) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at diagonal 02", MS5x5.this);
		} else {
			Toolkit.showMessage("Attention", "Congratulations! You won!",
					MS5x5.this);
		}

	}

	// ***********************METHODS TO THE
	// DATABASE*********************************

	// METHOD TO CREATE/OPEN THE DATABASE
	public void openorcreateBank() {
		// Defining the SQLite database name
		String nomeBanco = "bancoSQLite";

		try {
			// Defining the database mode
			bancoDados = openOrCreateDatabase(nomeBanco,
					MODE_ENABLE_WRITE_AHEAD_LOGGING, null);

			// Creating the table name and columns
			String sql = "CREATE TABLE IF NOT EXISTS test5 (id INTEGER PRIMARY KEY AUTOINCREMENT, c1l1 TEXT, c1l2 TEXT,c1l3 TEXT, c1l4 TEXT,"
					+ " c1l5 TEXT, c2l1 TEXT,c2l2 TEXT,c2l3 TEXT, c2l4 TEXT, c2l5 TEXT,c3l1 TEXT,c3l2 TEXT,c3l3 TEXT, c3l4 TEXT, c3l5 TEXT, "
					+ "c4l1 TEXT,c4l2 TEXT, c4l3 TEXT, c4l4 TEXT, c4l5 TEXT, c5l1 TEXT,c5l2 TEXT, c5l3 TEXT, c5l4 TEXT, c5l5 TEXT)";
			// Executing the the database
			bancoDados.execSQL(sql);
			// Message box to show that the bank was created
			// Toolkit.showMessage("AVISO",
			// "Banco criado com sucesso!",MS4x4.this);
		} catch (Exception erro) {
			// Message box to show that the bank was not created and why
			// Toolkit.showMessage("ERRO","Erro ao criar/abrir o banco: " +
			// erro.getMessage(),MS3x3.this);
		}

	}

	// METHOD TO CLOSE THE DATABASE
	public void closeDatabase() {
		try {
			// Function to close the database
			bancoDados.close();
			// Message box to show that the database was closed
			// com.example.hello_world.util.Toolkit.showMessage("ERRO","Banco fechado",
			// MS3x3.this);

		} catch (Exception erro) {
			// Message box to show that the database was not closed and why
			// com.example.hello_world.util.Toolkit.showMessage("ERRO","Erro ao fechar o banco: "
			// + erro.getMessage(), MS3x3.this);
		}
	}

	// METHOD TO SAVE IN THE DATABASE
	public void saveData(String c1l1, String c1l2, String c1l3, String c1l4,
			String c1l5, String c2l1, String c2l2, String c2l3, String c2l4,
			String c2l5, String c3l1, String c3l2, String c3l3, String c3l4,
			String c3l5, String c4l1, String c4l2, String c4l3, String c4l4,
			String c4l5, String c5l1, String c5l2, String c5l3, String c5l4,
			String c5l5) {

		try {
			// Commands to save, table and columns are defined
			String sql = "insert into test5 (c1l1,c1l2,c1l3,c1l4,c1l5,c2l1,c2l2,c2l3,c2l4,c2l5,"
					+ "c3l1 ,c3l2 ,c3l3,c3l4,c3l5,c4l1,c4l2,c4l3,c4l4,c4l5,c5l1,c5l2,c5l3,c5l4,c5l5) values ('"
					+ c1l1 + "','" + c1l2 + "','" + c1l3 + "','" + c1l4 + "','" + c1l5
					+ "','"
					+ c2l1 + "','" + c2l2 + "','" + c2l3 + "','" + c2l4 + "','" + c2l5
					+ "','"
					+ c3l1 + "','" + c3l2 + "','" + c3l3 + "','" + c3l4 + "','" + c3l5
					+ "','"
					+ c4l1 + "','" + c4l2 + "','" + c4l3 + "','" + c4l4 + "','" + c4l5
					+ "','"
					+ c5l1 + "','" + c5l2 + "','" + c5l3 + "','" + c5l4 + "','" + c5l5
					+ "')";
			// Executing the database command
			bancoDados.execSQL(sql);

			// Message box to show that the data was saved
			Toolkit.showMessage("OK", "Game saved successfully!", MS5x5.this);

			// Call the method to close the database after saving
			closeDatabase();
		} catch (Exception erro) {
			// Message box to show that the data was not saved and why
			Toolkit.showMessage("ERRO",
					"Erro ao gravar dados: " + erro.getMessage(), MS5x5.this);
		}
	}

	// METHOD TO CHECK IF THERE IS ANY DATA IN THE DATABASE
	private boolean searchData() {
		try {
			// Cursor receive data from the table and column specified through
			// the query command
			cursor = bancoDados.query("test5", new String[] { "c1l1", "c1l2",
					"c1l3", "c1l4", "c1l5", "c2l1", "c2l2", "c2l3", "c2l4", "c2l5", "c3l1",
					"c3l2", "c3l3", "c3l4", "c3l5", "c4l1", "c4l2", "c4l3", "c4l4", 
					"c4l5", "c5l1", "c5l2", "c5l3", "c5l4", "c5l5"},
					null, null, null, null, null, null);

			// Create variable and check if the cursor got any data returning
			// true or false
			int numeroRegistros = cursor.getCount();
			if (numeroRegistros != 0) {
				// Move the cursor for the first row at the table
				cursor.moveToFirst();
				return true;
			} else {
				return false;
			}

		} catch (Exception erro) {
			// Message box to show some error
			com.example.hello_world.util.Toolkit
					.showMessage("ERRO",
							"Erro ao buscar registro: " + erro.getMessage(),
							MS5x5.this);
			return false;
		}

	}

	// METHOD TO GET THE LAST SAVED VALUES IN THE DATABASE
	public void getData() {
		// Check if the method searchData return true, i.e, there are data!
		if (searchData() == true) {
			// Move the cursor for the last data saved (it is this way because I
			// just want the last values)
			cursor.moveToLast();
			// Put the values got by the cursor at each column in the String
			// created
			String value01 = cursor.getString(cursor.getColumnIndex("c1l1"));
			String value02 = cursor.getString(cursor.getColumnIndex("c1l2"));
			String value03 = cursor.getString(cursor.getColumnIndex("c1l3"));
			String value04 = cursor.getString(cursor.getColumnIndex("c1l4"));
			String value05 = cursor.getString(cursor.getColumnIndex("c1l5"));
			String value06 = cursor.getString(cursor.getColumnIndex("c2l1"));
			String value07 = cursor.getString(cursor.getColumnIndex("c2l2"));
			String value08 = cursor.getString(cursor.getColumnIndex("c2l3"));
			String value09 = cursor.getString(cursor.getColumnIndex("c2l4"));
			String value10 = cursor.getString(cursor.getColumnIndex("c2l5"));
			String value11 = cursor.getString(cursor.getColumnIndex("c3l1"));
			String value12 = cursor.getString(cursor.getColumnIndex("c3l2"));
			String value13 = cursor.getString(cursor.getColumnIndex("c3l3"));
			String value14 = cursor.getString(cursor.getColumnIndex("c3l4"));
			String value15 = cursor.getString(cursor.getColumnIndex("c3l5"));
			String value16 = cursor.getString(cursor.getColumnIndex("c4l1"));
			String value17 = cursor.getString(cursor.getColumnIndex("c4l2"));
			String value18 = cursor.getString(cursor.getColumnIndex("c4l3"));
			String value19 = cursor.getString(cursor.getColumnIndex("c4l4"));
			String value20 = cursor.getString(cursor.getColumnIndex("c4l5"));
			String value21 = cursor.getString(cursor.getColumnIndex("c5l1"));
			String value22 = cursor.getString(cursor.getColumnIndex("c5l2"));
			String value23 = cursor.getString(cursor.getColumnIndex("c5l3"));
			String value24 = cursor.getString(cursor.getColumnIndex("c5l4"));
			String value25 = cursor.getString(cursor.getColumnIndex("c5l5"));
			

			// Message box to show the values acquired
			// Toolkit.showMessage("FOIiii", "Valor: " + value01 + value02+
			// value03
			// + value04 + value05 + value06 + value07 + value08+ value09,
			// MS3x3.this);

			// Call the method to fill up the squares with the values got by
			// cursor, i.e, the last saved values
			continueGame(value01, value02, value03, value04, value05, value06,
					value07, value08, value09, value10, value11, value12,
					value13, value14, value15, value16, value17, value18, 
					value19, value20, value21, value22, value23, value24, value25);
		} else {
			com.example.hello_world.util.Toolkit.showMessage("Attention",
					"There are no game saved!", MS5x5.this);
		}
	}

	// METHOD TO PUT THE VALUES ACQUIRED FOR THE SQUARED VALUES AND THEN
	// CONTINUE THE GAME
	public void continueGame(String value01, String value02, String value03,
			String value04, String value05, String value06, String value07,
			String value08, String value09, String value10, String value11,
			String value12, String value13, String value14, String value15,
			String value16, String value17, String value18, String value19, 
			String value20, String value21, String value22, String value23, 
			String value24, String value25) {
		// Set the value acquired at the edit text (square value)
		et11.setText(value01);
		et12.setText(value02);
		et13.setText(value03);
		et14.setText(value04);
		et15.setText(value05);
		et21.setText(value06);
		et22.setText(value07);
		et23.setText(value08);
		et24.setText(value09);
		et25.setText(value10);
		et31.setText(value11);
		et32.setText(value12);
		et33.setText(value13);
		et34.setText(value14);
		et35.setText(value15);
		et41.setText(value16);
		et42.setText(value17);
		et43.setText(value18);
		et44.setText(value19);
		et45.setText(value20);
		et51.setText(value21);
		et52.setText(value22);
		et53.setText(value23);
		et54.setText(value24);
		et55.setText(value25);
	}

	// METHODS TO DEBUGGING THE DATABASE
	public void mostrarProximoRegistro() {

		try {
			cursor.moveToNext();
			// mostrarDados();
			String value01 = cursor.getString(cursor.getColumnIndex("c1l1"));
			Toolkit.showMessage("FOIiii", "Valor: " + value01, MS5x5.this);

		} catch (Exception erro) {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Não há mais registros!", MS5x5.this);
		}
	}

	public void mostrarPreviousRegistro() {

		try {
			cursor.moveToPrevious();
			// mostrarDados();
			String value01 = cursor.getString(cursor.getColumnIndex("c1l1"));
			Toolkit.showMessage("FOIiii", "Valor: " + value01, MS5x5.this);

		} catch (Exception erro) {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Não há mais registros!", MS5x5.this);
		}
	}

	public void deleteRegistro() {

		try {
			String value01 = cursor.getString(cursor.getColumnIndex("c1l1"));

			bancoDados.delete("test2", "c1l1 like '" + value01 + "'", null);

			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Registro excluido com sucesso" + value01, MS5x5.this);

		} catch (Exception e) {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Erro ao excluir registro!" + e.getMessage(), MS5x5.this);
		}

	}
}