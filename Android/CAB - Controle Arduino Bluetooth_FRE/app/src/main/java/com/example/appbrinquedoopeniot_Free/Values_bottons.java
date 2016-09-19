package com.example.appbrinquedoopeniot_Free;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

public class Values_bottons extends Activity {
	public static EditText EditFrente, EditDireita, EditEsquerda, EditTras, EditX, EditY, EditZ, EditA, EditB, EditC,EditConteudoAVoltar;
	public static final String PREFS_NAME = "Preferences";
	public static final int VALORES = 4;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_values_bottons);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		Bundle extras = getIntent().getExtras();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		EditFrente = (EditText) findViewById(R.id.EditTextFrente);
		EditDireita = (EditText) findViewById(R.id.EditTextDireita);
		EditEsquerda = (EditText) findViewById(R.id.EditTextEsquerda);
		EditTras = (EditText) findViewById(R.id.EditTextTras);
		EditX = (EditText) findViewById(R.id.EditTextX);
		EditY = (EditText) findViewById(R.id.EditTextY);
		EditZ = (EditText) findViewById(R.id.EditTextZ);
		EditA = (EditText) findViewById(R.id.EditTextA);
		EditB = (EditText) findViewById(R.id.EditTextB);
		EditC = (EditText) findViewById(R.id.EditTextC);
		EditConteudoAVoltar =(EditText) findViewById(R.id.EditTextConteudoAVoltar);

		EditFrente.setText(extras.getString("frente"));
		EditDireita.setText(extras.getString("direita"));
		EditEsquerda.setText(extras.getString("esquerda"));
		EditTras.setText(extras.getString("tras"));
		EditX.setText(extras.getString("x"));
		EditY.setText(extras.getString("y"));
		EditZ.setText(extras.getString("z"));
		EditA.setText(extras.getString("a"));
		EditB.setText(extras.getString("b"));
		EditC.setText(extras.getString("c"));
		EditConteudoAVoltar.setText(extras.getString("conteudoAVoltar"));

	}
	
	public Intent pegarValores(){
		Intent valores = new Intent();
		valores.putExtra("frente", EditFrente.getText().toString());
		valores.putExtra("direita", EditDireita.getText().toString());
		valores.putExtra("esquerda", EditEsquerda.getText().toString());
		valores.putExtra("tras", EditTras.getText().toString());
		valores.putExtra("x", EditX.getText().toString());
		valores.putExtra("y", EditY.getText().toString());
		valores.putExtra("z", EditZ.getText().toString());
		valores.putExtra("a", EditA.getText().toString());
		valores.putExtra("b", EditB.getText().toString());
		valores.putExtra("c", EditC.getText().toString());
		valores.putExtra("conteudoAVoltar", EditConteudoAVoltar.getText().toString());
		
		
		return valores;
	}
	
public void valoresPadrao(){
	EditFrente.setText("a");
	EditDireita.setText("b");
	EditEsquerda.setText("c");
	EditTras.setText("d");
	EditX.setText("e");
	EditY.setText("f");
	EditZ.setText("g");
	EditA.setText("h");
	EditB.setText("i");
	EditC.setText("j");
	EditConteudoAVoltar.setText("0");
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.values_bottons, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		//Ja que usamos o setDisplayHomeAsUpEnabled para true, para poder usar o icone do home, o onOptionsItemSelected
		//pasa a ser onMenuItemSelected
//		int id = item.getItemId();
//		switch (id) {
//		case R.id.action_settings:
//			setResult(RESULT_OK, pegarValores());
//			
//			finish();
//			break;
//			
//		case android.R.id.home:
//			finish();
//			break;
//		case R.id.valores_padrao:
//			valoresPadrao();
//			break;
//
//		default:
//			break;
//		}
//		
		return super.onOptionsItemSelected(item);
	}
	public boolean onMenuItemSelected(int panel, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			setResult(RESULT_OK, pegarValores());
			
			finish();
			break;
			
		case android.R.id.home:
			finish();
			break;
		case R.id.valores_padrao:
			valoresPadrao();
			break;

		default:
			break;
		}

		return true;
	};

	

	@Override
	protected void onStop() {
		super.onStop();
		
	}
	
	

}
