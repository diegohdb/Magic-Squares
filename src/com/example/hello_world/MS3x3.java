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
public class MS3x3 extends Activity {
	// Variable for Database
	public SQLiteDatabase bancoDados = null;
	public Cursor cursor;

	// Variables for widgets
	String numX, numY, numD, num1, num2, num3;
	EditText et11, et12, et13, et21, et22, et23, et31, et32, et33;
	Button btCheck, btQuit, btSave, btNext, btDelete, btPrevious, btHelp;
	TextView sumX1, sumX2, sumX3, sumY1, sumY2, sumY3, sumD1, sumD2;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ms3x3);// Call the layout (screen)

		// Don`t allow the keybord pop up until the EditText be touched
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		// Define the variable associated for each widget at the layout file
		// (xml)
		et11 = (EditText) findViewById(R.id.et11);
		et12 = (EditText) findViewById(R.id.et12);
		et13 = (EditText) findViewById(R.id.et13);
		et21 = (EditText) findViewById(R.id.et21);
		et22 = (EditText) findViewById(R.id.et22);
		et23 = (EditText) findViewById(R.id.et23);
		et31 = (EditText) findViewById(R.id.et31);
		et32 = (EditText) findViewById(R.id.et32);
		et33 = (EditText) findViewById(R.id.et33);
		sumX1 = (TextView) findViewById(R.id.SumXResult1);
		sumX2 = (TextView) findViewById(R.id.SumXResult2);
		sumX3 = (TextView) findViewById(R.id.SumXResult3);
		sumY1 = (TextView) findViewById(R.id.SumYResult1);
		sumY2 = (TextView) findViewById(R.id.SumYResult2);
		sumY3 = (TextView) findViewById(R.id.SumYResult3);
		sumD1 = (TextView) findViewById(R.id.SumDResult1);
		sumD2 = (TextView) findViewById(R.id.SumDResult2);

		btCheck = (Button) findViewById(R.id.btCheck);
		btQuit = (Button) findViewById(R.id.btExit);
		btSave = (Button) findViewById(R.id.btSave);
		btHelp = (Button) findViewById(R.id.btHelp);
		// Call the method
		selectSq33(); // Call the method to choose a new game or a previous
						// saved
		eventBtquit(); // Call the method to quit the game
		eventBtsave(); // Call the method to save the game
		eventBtcheck(); // Call the method to check the game
		eventBthelp(); // call the method to pop up the tutorial
	}

	// Method to choose a new game or a previous saved
	public void selectSq33() {
		// Pop up the dialog box to choose if the game is new or previous saved
		AlertDialog.Builder builder = new AlertDialog.Builder(MS3x3.this);
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
				Intent intent = new Intent(MS3x3.this, Help.class);
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
						MS3x3.this);
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
				String i21 = "0";
				String i22 = "0";
				String i23 = "0";
				String i31 = "0";
				String i32 = "0";
				String i33 = "0";

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
				// Call the method to save the data in the database
				saveData(i11, i12, i13, i21, i22, i23, i31, i32, i33);
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
				int i21 = 0;
				int i22 = 0;
				int i23 = 0;
				int i31 = 0;
				int i32 = 0;
				int i33 = 0;

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

				// Sum the horizontal values
				int SumH1 = i11 + i12 + i13;
				int SumH2 = i21 + i22 + i23;
				int SumH3 = i31 + i32 + i33;
				// Sum the vertical values
				int SumV1 = i11 + i21 + i31;
				int SumV2 = i12 + i22 + i32;
				int SumV3 = i13 + i23 + i33;
				// Sum the diagonal values
				int SumD1 = i11 + i22 + i33;
				int SumD2 = i13 + i22 + i31;

				// Set the values in the Horizontal Sum
				sumX1.setText(String.valueOf(SumH1));
				sumX2.setText(String.valueOf(SumH2));
				sumX3.setText(String.valueOf(SumH3));
				// Set the values in the Vertical Sum
				sumY1.setText(String.valueOf(SumV1));
				sumY2.setText(String.valueOf(SumV2));
				sumY3.setText(String.valueOf(SumV3));
				// Set the values in the Diagonal Sum
				sumD1.setText(String.valueOf(SumD1));
				sumD2.setText(String.valueOf(SumD2));

				// Call the method testSolution with Sum and squares values
				testSolution(SumH1, SumH2, SumH3, SumV1, SumV2, SumV3, SumD1,
						SumD2, i11, i12, i13, i21, i22, i23, i31, i32, i33);
			}
		});
	}

	// Method to check if the sum values are equals
	public void testSolution(int SumH1, int SumH2, int SumH3, int SumV1,
			int SumV2, int SumV3, int SumD1, int SumD2, int i11, int i12,
			int i13, int i21, int i22, int i23, int i31, int i32, int i33) {
		// Test if are the sum value is equal in each line
		if (SumH1 == SumH2 && SumH2 == SumH3 && SumH3 == SumV1
				&& SumV1 == SumV2 && SumV2 == SumV3 && SumV3 == SumD1
				&& SumD1 == SumD2) {
			// If they are test if are duplicated values in the squares
			testDuplicates(i11, i12, i13, i21, i22, i23, i31, i32, i33);// Call
																		// the
																		// method
																		// testDuplicates
																		// with
																		// the
																		// square
																		// values
		} else {
			Toolkit.showMessage("Warning",
					"The sum values are different, keep trying!", MS3x3.this);
		}
	}

	// Method to test if there is duplicated square values
	public void testDuplicates(int i11, int i12, int i13, int i21, int i22,
			int i23, int i31, int i32, int i33) {
		// Test if are square values duplicated if it is show the warning
		// message to the user
		// If have none duplicated values show the congratulation message - End
		// of Game - User Win!
		if (i11 == i12 || i11 == i13 || i12 == i13) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at horizontal 01", MS3x3.this);
		} else if (i21 == i22 || i21 == i23 || i22 == i23) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at horizontal 02", MS3x3.this);
		} else if (i31 == i32 || i31 == i33 || i32 == i33) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at horizontal 03", MS3x3.this);
		} else if (i11 == i21 || i11 == i31 || i21 == i31) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at vertical 01", MS3x3.this);
		} else if (i12 == i22 || i12 == i32 || i22 == i32) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at vertical 02", MS3x3.this);
		} else if (i13 == i23 || i13 == i33 || i23 == i33) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at vertical 03", MS3x3.this);
		} else if (i11 == i22 || i11 == i33 || i22 == i33) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at diagonal 01", MS3x3.this);
		} else if (i13 == i22 || i13 == i31 || i22 == i31) {
			Toolkit.showMessage("Warning",
					"There are duplicate number at diagonal 02", MS3x3.this);
		} else {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								MS3x3.this);
						builder.setTitle("Attention");
						builder.setMessage( "Congratulations! You won! \n Do you want to play a next level?");
						builder.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										// Action for the button Yes
										Intent intent = new Intent(MS3x3.this, MS4x4.class);
										startActivity(intent);
									}
								});
						builder.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										// Action for the button Continue
										finish();
									}
								});
						builder.show();
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
			String sql = "CREATE TABLE IF NOT EXISTS test2 (id INTEGER PRIMARY KEY AUTOINCREMENT, c1l1 TEXT, "
					+ "c1l2 TEXT,c1l3 TEXT, c2l1 TEXT,c2l2 TEXT,c2l3 TEXT,c3l1 TEXT,c3l2 TEXT,c3l3 TEXT)";
			// Executing the the database
			bancoDados.execSQL(sql);
			// Message box to show that the bank was created
			// Toolkit.showMessage("AVISO",
			// "Banco criado com sucesso!",MS3x3.this);
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
	public void saveData(String lt, String numeroOrdem, String equipe,

	String loc, String loc1, String loc2, String loc3, String loc4, String loc5) {

		try {
			// Commands to save, table and columns are defined
			String sql = "insert into test2 (c1l1,c1l2,c1l3,c2l1,c2l2,c2l3 ,c3l1 ,c3l2 ,c3l3) values ('"
					+ lt
					+ "','"
					+ numeroOrdem
					+ "','"
					+ equipe
					+ "','"
					+ loc
					+ "','"
					+ loc1
					+ "','"
					+ loc2
					+ "','"
					+ loc3
					+ "',"
					+ "'"
					+ loc4 + "','" + loc5 + "')";
			// Executing the database command
			bancoDados.execSQL(sql);

			// Message box to show that the data was saved
			Toolkit.showMessage("OK", "Game saved successfully!", MS3x3.this);

			// Call the method to close the database after saving
			closeDatabase();
		} catch (Exception erro) {
			// Message box to show that the data was not saved and why
			// Toolkit.showMessage("ERRO","Erro ao gravar dados: " +
			// erro.getMessage(), MS3x3.this);
		}
	}

	// METHOD TO CHECK IF THERE IS ANY DATA IN THE DATABASE
	private boolean searchData() {
		try {
			// Cursor receive data from the table and column specified through
			// the query command
			cursor = bancoDados.query("test2", new String[] { "c1l1", "c1l2",
					"c1l3", "c2l1", "c2l2", "c2l3", "c3l1", "c3l2", "c3l3" },
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
							MS3x3.this);
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
			String value04 = cursor.getString(cursor.getColumnIndex("c2l1"));
			String value05 = cursor.getString(cursor.getColumnIndex("c2l2"));
			String value06 = cursor.getString(cursor.getColumnIndex("c2l3"));
			String value07 = cursor.getString(cursor.getColumnIndex("c3l1"));
			String value08 = cursor.getString(cursor.getColumnIndex("c3l2"));
			String value09 = cursor.getString(cursor.getColumnIndex("c3l3"));

			// Message box to show the values acquired
			// Toolkit.showMessage("FOIiii", "Valor: " + value01 + value02+
			// value03
			// + value04 + value05 + value06 + value07 + value08+ value09,
			// MS3x3.this);

			// Call the method to fill up the squares with the values got by
			// cursor, i.e, the last saved values
			continueGame(value01, value02, value03, value04, value05, value06,
					value07, value08, value09);
		} else {
			com.example.hello_world.util.Toolkit.showMessage("Attention",
					"There are no game saved!", MS3x3.this);
		}
	}

	// METHOD TO PUT THE VALUES ACQUIRED FOR THE SQUARED VALUES AND THEN
	// CONTINUE THE GAME
	public void continueGame(String value01, String value02, String value03,
			String value04, String value05, String value06, String value07,
			String value08, String value09) {
		// Set the value acquired at the edit text (square value)
		et11.setText(value01);
		et12.setText(value02);
		et13.setText(value03);
		et21.setText(value04);
		et22.setText(value05);
		et23.setText(value06);
		et31.setText(value07);
		et32.setText(value08);
		et33.setText(value09);
	}

	// METHODS TO DEBUGGING THE DATABASE
	public void mostrarProximoRegistro() {

		try {
			cursor.moveToNext();
			// mostrarDados();
			String value01 = cursor.getString(cursor.getColumnIndex("c1l1"));
			Toolkit.showMessage("FOIiii", "Valor: " + value01, MS3x3.this);

		} catch (Exception erro) {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Não há mais registros!", MS3x3.this);
		}
	}

	public void mostrarPreviousRegistro() {

		try {
			cursor.moveToPrevious();
			// mostrarDados();
			String value01 = cursor.getString(cursor.getColumnIndex("c1l1"));
			Toolkit.showMessage("FOIiii", "Valor: " + value01, MS3x3.this);

		} catch (Exception erro) {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Não há mais registros!", MS3x3.this);
		}
	}

	public void deleteRegistro() {

		try {
			String value01 = cursor.getString(cursor.getColumnIndex("c1l1"));

			bancoDados.delete("test2", "c1l1 like '" + value01 + "'", null);

			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Registro excluido com sucesso" + value01, MS3x3.this);

		} catch (Exception e) {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Erro ao excluir registro!" + e.getMessage(), MS3x3.this);
		}

	}
}