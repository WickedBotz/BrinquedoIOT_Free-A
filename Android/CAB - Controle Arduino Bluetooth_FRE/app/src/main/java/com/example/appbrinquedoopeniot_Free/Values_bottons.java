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
        EditFrente.setText("8");
        EditDireita.setText("6");
        EditEsquerda.setText("4");
        EditTras.setText("2");
        EditX.setText("x");
        EditY.setText("y");
        EditZ.setText("z");
        EditA.setText("a");
        EditB.setText("b");
        EditC.setText("c");
        EditConteudoAVoltar.setText("0");
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Preenche o menu; isto acrescenta itens ? barra de a??o se ela estiver presente.
		getMenuInflater().inflate(R.menu.values_bottons, menu);
		return true;
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
}
