package com.example.hello_world.util;

import android.app.AlertDialog;
import android.content.Context;

//Classe com m�todos utilit�rios
public class Toolkit {
public static void showMessage(String title, String message, Context context){
	
	AlertDialog.Builder dialog = new AlertDialog.Builder(context);
	dialog.setTitle(title);
	dialog.setMessage(message);
	dialog.setNeutralButton("OK", null);
	dialog.show();
}
}
