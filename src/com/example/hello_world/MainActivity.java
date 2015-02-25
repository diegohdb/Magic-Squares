package com.example.hello_world;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	// Variable for Database
	public SQLiteDatabase bancoDados = null;
	public Cursor cursor;
	double num1, num2, result;
	EditText etnum1, etnum2;
	Button btsq33,btExit;
	TextView tvresult;
	ImageButton imsq33;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btsq33 = (Button) findViewById(R.id.btsq33);
		
		selectSq33();

		abreouCriabanco();

	}

	public void selectSq33() {
		// Evento para o botão "Voltar"
		btsq33.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("Attention");
				builder.setMessage("Do you wish to start a new game or continue a previous one?");
				builder.setPositiveButton("New",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// ação do botão "sim"

								fechaBanco();
								goSq33();
							}
						});
				builder.setNegativeButton("Continue",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// ação do botão "Continue"
								

							}
						});
				builder.show();

			}
		});
	}

	// Method to choose the Magic Square 3x3
	public void goSq33() {

		Intent intent = new Intent(MainActivity.this, MS3x3.class);
		startActivity(intent);
	}

	// MÉTODO PARA CRIAÇÃO/ABERTURA DO BANCO DE DADOS
	public void abreouCriabanco() {
		String nomeBanco = "bancoSQLite";

		try {
			bancoDados = openOrCreateDatabase(nomeBanco,
					MODE_ENABLE_WRITE_AHEAD_LOGGING, null);

			String sql = "CREATE TABLE IF NOT EXISTS cabecalho (id INTEGER PRIMARY KEY AUTOINCREMENT, lt TEXT, "
					+ "ordem TEXT, equipe TEXT)";
			bancoDados.execSQL(sql);
			// Toolkit.showMessage("AVISO",
			// "Banco criado com sucesso!",MainActivity.this);
		} catch (Exception erro) {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Erro ao criar/abrir o banco: " + erro.getMessage(),
					MainActivity.this);
		}

	}

	// MÉTODO PARA FECHAMENTO DO BANCO DE DADOS
	public void fechaBanco() {
		try {
			bancoDados.close();
			// exibirMensagem("AVISO", "Banco fechado com sucesso!");
		} catch (Exception erro) {

			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Erro ao fechar o banco: " + erro.getMessage(),
					MainActivity.this);
		}
	}

	// MÉTODO PARA GRAVAR NO BANCO DE DADOS
	public void gravarRegistro(String lt, String numeroOrdem, String equipe) {

		try {
			String sql = "insert into cabecalho (lt,ordem,equipe) values ('"
					+ lt + "','" + numeroOrdem + "','" + equipe + "')";
			bancoDados.execSQL(sql);

		} catch (Exception erro) {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Erro ao gravar dados: " + erro.getMessage(),
					MainActivity.this);
		}

	}

	private boolean buscarDados() {
		try {
			cursor = bancoDados.query("cabecalho", new String[] { "lt",
					"ordem", "equipe" }, null, null, null, null, null, null);

			int numeroRegistros = cursor.getCount();
			if (numeroRegistros != 0) {
				cursor.moveToFirst();
				return true;
			} else {
				return false;
			}

		} catch (Exception erro) {

			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Erro ao buscar registro: " + erro.getMessage(),
					MainActivity.this);
			return false;
		}

	}

	public void chamaConsulta() {
		if (buscarDados() == true) {
			setContentView(R.layout.consulta);
			mostrarDados();
		} else {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Não há dados gravados!", MainActivity.this);
		}
	}

	public void mostrarDados() {
		TextView tvLT = (TextView) findViewById(R.id.tvLT);
		TextView tvORDEM = (TextView) findViewById(R.id.tvORDEM);
		TextView tvEQUIPE = (TextView) findViewById(R.id.tvEQUIPE);
		Button btNext = (Button) findViewById(R.id.btNEXT);
		Button btPrevious = (Button) findViewById(R.id.btPREVIOUS);
		Button btExcluir = (Button) findViewById(R.id.btEXCLUIR);

		btNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mostrarProximoRegistro();
			}
		});

		btPrevious.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mostrarRegistroAnterior();
			}
		});

		btExcluir.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				deleteRegistro();
				buscarDados();
				mostrarDados();
			}
		});

		tvLT.setText(cursor.getString(cursor.getColumnIndex("lt")));
		tvORDEM.setText(cursor.getString(cursor.getColumnIndex("ordem")));
		tvEQUIPE.setText(cursor.getString(cursor.getColumnIndex("equipe")));
	}

	public void mostrarRegistroAnterior() {
		try {
			cursor.moveToPrevious();
			mostrarDados();

		} catch (Exception erro) {

			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Não há registro anterior!", MainActivity.this);

		}

	}

	public void mostrarProximoRegistro() {

		try {
			cursor.moveToNext();
			mostrarDados();

		} catch (Exception erro) {

			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Não há mais registros!", MainActivity.this);
		}
	}

	public void deleteRegistro() {

		try {

			TextView tvLT = (TextView) findViewById(R.id.tvLT);
			TextView tvORDEM = (TextView) findViewById(R.id.tvORDEM);
			TextView tvEQUIPE = (TextView) findViewById(R.id.tvEQUIPE);

			String dadoLT = tvLT.getText().toString();
			String dadoORDEM = tvORDEM.getText().toString();
			String dadoEQUIPE = tvEQUIPE.getText().toString();

			bancoDados.delete("cabecalho", "lt like '" + dadoLT
					+ "'and ordem like '" + dadoORDEM + "'and equipe like '"
					+ dadoEQUIPE + "' ", null);

			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Registro excluido com sucesso", MainActivity.this);

		} catch (Exception e) {
			com.example.hello_world.util.Toolkit.showMessage("ERRO",
					"Erro ao excluir registro!", MainActivity.this);
		}

	}
}
