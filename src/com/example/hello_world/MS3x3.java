package com.example.hello_world;

import com.example.hello_world.util.Toolkit;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
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

	String numX, numY, numD, num1, num2, num3;
	EditText et11, et12, et13, et21, et22, et23, et31, et32, et33;
	Button btCheck, btQuit, btSave;
	TextView sumX1, sumX2, sumX3, sumY1, sumY2, sumY3, sumD1, sumD2;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ms3x3);

		// NÃO SOBE AO TECLADO ANTES DE TOCAR NO EDIT TEXT
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

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
		
		//abreouCriabanco();
		//abreouCriabank();
		eventBtcheck();
		eventBtquit();
		//eventBtsave();
	}

	public void eventBtquit() {
		btQuit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
	}

	public void eventBtsave() {
		
		btSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String i11 = "0";
				String i12 = "0";
				String i13 = "0";
				String i21 = "0";
				String i22 = "0";
				String i23 = "0";
				String i31 = "0";
				String i32 = "0";
				String i33 = "0";
				try {
					i11 = et11.getText().toString();
				} catch (Exception e) {
					i11 = "0";
				}
				try {
					i12 = et12.getText().toString();
				} catch (Exception e) {
					i12 = "0";
				}
				try {
					i13 = et13.getText().toString();
				} catch (Exception e) {
					i13 = "0";
				}
				try {
					i21 = et21.getText().toString();
				} catch (Exception e) {
					i21 = "0";
				}

				try {
					i22 = et22.getText().toString();
				} catch (Exception e) {
					i22 = "0";
				}

				try {
					i23 = et23.getText().toString();
				} catch (Exception e) {
					i23 = "0";
				}
				try {
					i31 = et31.getText().toString();
				} catch (Exception e) {
					i31 = "0";
				}
				try {
					i32 = et32.getText().toString();
				} catch (Exception e) {
					i32 = "0";
				}

				try {
					i33 = et33.getText().toString();
				} catch (Exception e) {
					i33 = "0";
				}
				gravarRegistro(i11, i12, i13, i21, i22, i23, i31, i32, i33);
				//gravarData(i11, i12, i13, i21);

			}
		});
	}

	public void testSolution(int SumH1, int SumH2, int SumH3, int SumV1,
			int SumV2, int SumV3, int SumD1, int SumD2, int i11, int i12,
			int i13, int i21, int i22, int i23, int i31, int i32, int i33) {
		if (SumH1 == SumH2 && SumH2 == SumH3 && SumH3 == SumV1
				&& SumV1 == SumV2 && SumV2 == SumV3 && SumV3 == SumD1
				&& SumD1 == SumD2) {
			testDuplicates(i11, i12, i13, i21, i22, i23, i31, i32, i33);
		} else {

		}
	}

	public void testDuplicates(int i11, int i12, int i13, int i21, int i22,
			int i23, int i31, int i32, int i33) {

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
			Toolkit.showMessage("Attention", "Congratulations! You won!",
					MS3x3.this);
		}

	}

	public void eventBtcheck() {
		btCheck.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { //

				// Toast.makeText(MS3x3.this, numX,Toast.LENGTH_SHORT).show();
				int i11 = 0;
				int i12 = 0;
				int i13 = 0;
				int i21 = 0;
				int i22 = 0;
				int i23 = 0;
				int i31 = 0;
				int i32 = 0;
				int i33 = 0;

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

				int SumH1 = i11 + i12 + i13;
				int SumH2 = i21 + i22 + i23;
				int SumH3 = i31 + i32 + i33;
				int SumV1 = i11 + i21 + i31;
				int SumV2 = i12 + i22 + i32;
				int SumV3 = i13 + i23 + i33;
				int SumD1 = i11 + i22 + i33;
				int SumD2 = i13 + i22 + i31;
				sumX1.setText(String.valueOf(SumH1));
				sumX2.setText(String.valueOf(SumH2));
				sumX3.setText(String.valueOf(SumH3));
				sumY1.setText(String.valueOf(SumV1));
				sumY2.setText(String.valueOf(SumV2));
				sumY3.setText(String.valueOf(SumV3));
				sumD1.setText(String.valueOf(SumD1));
				sumD2.setText(String.valueOf(SumD2));
				testSolution(SumH1, SumH2, SumH3, SumV1, SumV2, SumV3, SumD1,
						SumD2, i11, i12, i13, i21, i22, i23, i31, i32, i33);
			}
		});
	}

	// METHOD TO CREATE/OPEN THE DATABASE
	public void abreouCriabanco() {
		String nomeBanco = "bancoSQLite";

		try {
			bancoDados = openOrCreateDatabase(nomeBanco,
					MODE_ENABLE_WRITE_AHEAD_LOGGING, null);

			String sql = "CREATE TABLE IF NOT EXISTS sqtt (id INTEGER PRIMARY KEY AUTOINCREMENT, ioo TEXT, "
					+ "iot TEXT, ior TEXT, ito TEXT, itt TEXT, itr TEXT, iro TEXT, irt TEXT, irr TEXT)";
			bancoDados.execSQL(sql);
			Toolkit.showMessage("AVISO", "Banco criado com sucesso!",
					MS3x3.this);
		} catch (Exception erro) {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Erro ao criar/abrir o banco: " + erro.getMessage(),
					MS3x3.this);
		}

	}

	// METHOD TO CLOSE THE DATABASE
	public void fechaBanco() {
		try {
			bancoDados.close();
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Banco fechado", MS3x3.this);
		
		} catch (Exception erro) {

			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Erro ao fechar o banco: " + erro.getMessage(), MS3x3.this);
		}
	}

	// METHOD TO SAVE AT THE DATABASE
	public void gravarRegistro(String i11, String i12, String i13, String i21,
			String i22, String i23, String i31, String i32, String i33) {

		try {
			String sql = "insert into sqtt (ioo,iot,ior,ito,itt,itr,iro,irt,irr) values ('"
					+ i11 + "','" + i12 + "','" + i13 + "','" + i21 + "','"+ i22 + "','" + i23 + "','" + i31 + "','" + i32 + "','"+ i33 + "')";
			bancoDados.execSQL(sql);
			Toolkit.showMessage("ERRO",
					"Gravado com sucesso: ", MS3x3.this);

		} catch (Exception erro) {
			Toolkit.showMessage("ERRO",
					"Erro ao gravar dados: " + erro.getMessage(), MS3x3.this);
		}

	}
	// MÉTODO PARA GRAVAR NO BANCO DE DADOS
		public void gravarData(String lt, String numeroOrdem, String equipe,String loc) {

			try {
				String sql = "insert into sqtt (lt,ordem,loco,equipe) values ('"
						+ lt + "','" + numeroOrdem + "','" + equipe + "','" + loc + "')";
				bancoDados.execSQL(sql);

				Toolkit.showMessage("OK",
						"Gravado c sucess " ,
						MS3x3.this);
				fechaBanco();
			} catch (Exception erro) {
				Toolkit.showMessage("ERRO",
						"Erro ao gravar dados: " + erro.getMessage(),
						MS3x3.this);
			}

		}
		
		public void abreouCriabank() {
			String nomeBanco = "bancoSQLite";

			try {
				bancoDados = openOrCreateDatabase(nomeBanco,
						MODE_ENABLE_WRITE_AHEAD_LOGGING, null);

				String sql = "CREATE TABLE IF NOT EXISTS sqtt (id INTEGER PRIMARY KEY AUTOINCREMENT, lt TEXT, "
						+ "ordem TEXT,loco TEXT, equipe TEXT)";
				bancoDados.execSQL(sql);
				Toolkit.showMessage("AVISO", "Banco criado com sucesso!",MS3x3.this);
			} catch (Exception erro) {
				Toolkit.showMessage("ERRO",
						"Erro ao criar/abrir o banco: " + erro.getMessage(),
						MS3x3.this);
			}

		}
	
}
