package com.example.appbrinquedoopeniot_Free;



import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity principal que cuida referencia botoes da tela principal, e manipula
 * os dados que são salvos.
 * 
 * @author Administrador
 */
@SuppressLint({ "HandlerLeak", "ClickableViewAccessibility" })
public class MainActivity extends FragmentActivity {

	String frente, direita, esquerda, tras, x, y, z, a, b, c, conteudoAVoltar;
	Button btnConectar, btnFrente, btnDireita, btnEsquerda, btnTras, btn1, btn2, btn3, btn4, btn5, btn6;
	TextView txtArduino01, txtArduino02, txtArduino03, txtArduino04, txtArduino05, txtArduino06, txtArduino07;
	View dados;

	FragmentManager fm = getSupportFragmentManager();

	public static final String PREFS_NAME = "Preferences";

	Intent Value_Bottons;
	Handler h;

	BluetoothThread btt;
	Handler writeHandler;

	// Requisição para Activity de ativação do Bluetooth
	// Se numero for maior > 0,este codigo sera devolvido em onActivityResult()
	private static final int REQUEST_ENABLE_BT = 1;
	// Requisição para Activity para inciar tela do aplicativos pareados,
	// que se houver ou nao aplicativo pareado retornara para onActivityResult()
	// e realizara as devidas ações conforme a resposta
	public static final int SELECT_PAIRED_DEVICE = 2;
	public static final int VALORES = 4;

	// BluetoothAdapter é comando de entrada padrão paras todads interações com
	private BluetoothAdapter bluetoothPadrao = null;


	/**
	 * Criação da tela
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		setContentView(R.layout.activity_main);
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		Value_Bottons = new Intent(this, Values_bottons.class);

		// Obtem o bluetooth padrao do aparelho celular
		bluetoothPadrao = BluetoothAdapter.getDefaultAdapter();

		// Vereficamos se o aparelho possui adaptador Bluetooth

		
		interromperBluetooth();
		resgatarValoresBotoes();
		referenciarElementosTela();

	}

	public void referenciarElementosTela() {
		dados = (View) findViewById(R.id.FundoDados);
		txtArduino01 = (TextView) findViewById(R.id.txtArduino01);
		txtArduino02 = (TextView) findViewById(R.id.txtArduino02);
		txtArduino03 = (TextView) findViewById(R.id.txtArduino03);
		txtArduino04 = (TextView) findViewById(R.id.txtArduino04);
		txtArduino05 = (TextView) findViewById(R.id.txtArduino05);
		txtArduino06 = (TextView) findViewById(R.id.txtArduino06);
		txtArduino07 = (TextView) findViewById(R.id.txtArduino07);
		btnConectar = (Button) findViewById(R.id.btnConectar);
		btnFrente = (Button) findViewById(R.id.bt_frente);
		btnDireita = (Button) findViewById(R.id.bt_direita);
		btnEsquerda = (Button) findViewById(R.id.bt_esquerda);
		btnTras = (Button) findViewById(R.id.bt_tras);
		btn1 = (Button) findViewById(R.id.bt_x);
		btn2 = (Button) findViewById(R.id.bt_y);
		btn3 = (Button) findViewById(R.id.bt_z);
		btn4 = (Button) findViewById(R.id.bt_a);
		btn5 = (Button) findViewById(R.id.bt_b);
		btn6 = (Button) findViewById(R.id.bt_c);
	}

	//SharedPreferences grava dados nos arquivos locais da aplicação,
	//aonde que pode ser acessivel em qualquer tela.
	public void resgatarValoresBotoes() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		frente = settings.getString("frente", "");
		direita = settings.getString("direita", "");
		esquerda = settings.getString("esquerda", "");
		tras = settings.getString("tras", "");
		x = settings.getString("x", "");
		y = settings.getString("y", "");
		z = settings.getString("z", "");
		a = settings.getString("a", "");
		b = settings.getString("b", "");
		c = settings.getString("c", "");
		conteudoAVoltar = settings.getString("conteudoAVoltar", "");
	}

	public void listaDeDispositivos() {
		if (bluetoothPadrao.isEnabled()) {
			if (btt == null) {
				Intent searchPairedDevicesIntent = new Intent(this, PairedDevices.class);
				startActivityForResult(searchPairedDevicesIntent, SELECT_PAIRED_DEVICE);
			} else {
				interromperBluetooth();
			}

		}
	}
	
	public void interromperBluetooth(){
		if(btt != null){
			btnConectar.setText("Conectar");

			btt.interrupt();
			btt = null;
		}
		
	}

	public void connectButtonPressed(View v) {

		if (bluetoothPadrao == null) {
			Toast.makeText(getApplicationContext(), "Dispostivo não possui Bluetooth", Toast.LENGTH_LONG).show();

		} else {
			if (!bluetoothPadrao.isEnabled()) {

				Intent novoIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(novoIntent, REQUEST_ENABLE_BT);
			} else {

				listaDeDispositivos();
			}
		}

	}
	


	public void acaoDosBotoes() {
		btnFrente.setOnTouchListener(new BotaoListener(frente));
		btnEsquerda.setOnTouchListener(new BotaoListener(esquerda));
		btnDireita.setOnTouchListener(new BotaoListener(direita));
		btnTras.setOnTouchListener(new BotaoListener(tras));
		btn1.setOnTouchListener(new BotaoListener(x));
		btn2.setOnTouchListener(new BotaoListener(y));
		btn3.setOnTouchListener(new BotaoListener(z));
		btn4.setOnTouchListener(new BotaoListener(a));
		btn5.setOnTouchListener(new BotaoListener(b));
		btn6.setOnTouchListener(new BotaoListener(c));
	}

	public class BotaoListener implements OnTouchListener {

		private String mensagem;

		public BotaoListener(String mensagem) {
			super();
			this.mensagem = mensagem;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (btt != null) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					Message msg = Message.obtain();
					msg.obj = mensagem;
					writeHandler.sendMessage(msg);
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					Message msg = Message.obtain();
					msg.obj = conteudoAVoltar;
					writeHandler.sendMessage(msg);
				}
			} else {
				// Bluetooth nao conectado
			}

			// Para mandar em tempo real
			// if (btt != null) {
			// Message msg = Message.obtain();
			// msg.obj = mensagem;
			// writeHandler.sendMessage(msg);
			// } else {
			// Toast.makeText(getApplicationContext(), "Bluetooth nao
			// conectado", Toast.LENGTH_LONG).show();
			// }
			return false;
		}

	}

	boolean imprimir = true;

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {

		// Retrono do pedido de ativação do Bluetooth
		case REQUEST_ENABLE_BT:

			if (resultCode == Activity.RESULT_OK) {
				Toast.makeText(getApplicationContext(), "Bluetooth Ativado XD", Toast.LENGTH_LONG).show();
				listaDeDispositivos();
			} else {
				Toast.makeText(getApplicationContext(), "Você precisa ativar o bluetooth ", Toast.LENGTH_LONG).show();

			}
			break;
		case SELECT_PAIRED_DEVICE:
			if (resultCode == RESULT_OK) {
				btnConectar = (Button) findViewById(R.id.btnConectar);
				if (btt == null) {

					btt = new BluetoothThread(data.getStringExtra("btDevAddress"), new Handler() {

						@Override
						public void handleMessage(Message message) {

							String s = (String) message.obj;

							String textoB[] = s.split(";");

							// Do something with the message
							if (s.equals("CONNECTED")) {
								btnConectar.setText("Desconectar");
								btnConectar.setEnabled(true);

								// ativado = true;
							} else if (s.equals("DISCONNECTED")) {

								Toast.makeText(getApplicationContext(), "Desconectado", Toast.LENGTH_LONG).show();
								btnConectar.setText("Conectar");

								btt = null;
								btnConectar.setEnabled(true);
							} else if (s.equals("CONNECTION FAILED")) {
								Toast.makeText(getApplicationContext(), "Falha na conexao", Toast.LENGTH_LONG).show();
								btnConectar.setText("Conectar");

								btt = null;
								btnConectar.setEnabled(true);
							} else {

								loop: for (int i = 0; i < textoB.length; i++) {
									switch (i) {
									case 0:
										txtArduino01.setText(textoB[0]);
										break;
									case 1:
										txtArduino02.setText(textoB[1]);
										break;
									case 2:
										txtArduino03.setText(textoB[2]);
										break;
									case 3:
										txtArduino04.setText(textoB[3]);
										break;
									case 4:
										txtArduino05.setText(textoB[4]);
										break;
									case 5:
										txtArduino05.setText(textoB[5]);
										break;
									case 6:
										txtArduino05.setText(textoB[6]);
										break;
									default:
										if (imprimir) {
											Toast.makeText(getApplicationContext(),
													"Numeros maximos de linhas ultrapassado!!!!!!!", Toast.LENGTH_LONG)
													.show();
											imprimir = false;
										}

										break loop;
									}
								}
							}
						}
					});
				}

				if (btt != null) {
					// Get the handler that is used to send messages
					writeHandler = btt.getWriteHandler();

					// Run the thread
					btt.start();

					btnConectar.setText("Conectando...");
					btnConectar.setEnabled(false);
				}
				// break;

			} else {
				Toast.makeText(getApplicationContext(), "Nenhum dispositivo Selecionado", Toast.LENGTH_LONG).show();
			}
			break;
		case VALORES:
			if (resultCode == RESULT_OK) {
				Toast.makeText(getApplicationContext(), "Mudanças salvas", Toast.LENGTH_LONG).show();
				frente = data.getStringExtra("frente");
				direita = data.getStringExtra("direita");
				esquerda = data.getStringExtra("esquerda");
				tras = data.getStringExtra("tras");
				x = data.getStringExtra("x");
				y = data.getStringExtra("y");
				z = data.getStringExtra("z");
				a = data.getStringExtra("a");
				b = data.getStringExtra("b");
				c = data.getStringExtra("c");
				conteudoAVoltar = data.getStringExtra("conteudoAVoltar");
			} else {
				Toast.makeText(getApplicationContext(), "Mudanças não salvas", Toast.LENGTH_LONG).show();
			}

			break;
		}
	};

	private MenuItem btiMostrarDados;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		btiMostrarDados = menu.findItem(R.id.item6);
		btiMostrarDados.setTitle("Mostrar");
		return true;
	}

	private boolean mostrarDados = true;

	@Override
	public boolean onMenuItemSelected(int panel, MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show();

			interromperBluetooth();
			finish();

			break;
		/*
		 * case R.id.item1:
		 * 
		 * break; case R.id.item2: Toast.makeText(this, "So mostro meu ID XD: "
		 * + (item.getItemId() + 1), Toast.LENGTH_SHORT).show(); break;
		 */
		case R.id.item3:
			Value_Bottons.putExtra("frente", frente);
			Value_Bottons.putExtra("direita", direita);
			Value_Bottons.putExtra("esquerda", esquerda);
			Value_Bottons.putExtra("tras", tras);
			Value_Bottons.putExtra("x", x);
			Value_Bottons.putExtra("y", y);
			Value_Bottons.putExtra("z", z);
			Value_Bottons.putExtra("a", a);
			Value_Bottons.putExtra("b", b);
			Value_Bottons.putExtra("c", c);
			Value_Bottons.putExtra("conteudoAVoltar", conteudoAVoltar);
			startActivityForResult(Value_Bottons, VALORES);

			break;
		// case R.id.item4:
		// break;
		case R.id.item6:
			if (mostrarDados) {
				dados.setVisibility(View.VISIBLE);
				txtArduino01.setVisibility(View.VISIBLE);
				txtArduino02.setVisibility(View.VISIBLE);
				txtArduino03.setVisibility(View.VISIBLE);
				txtArduino04.setVisibility(View.VISIBLE);
				txtArduino05.setVisibility(View.VISIBLE);
				Toast.makeText(getApplicationContext(), "Dados visiveis", Toast.LENGTH_LONG).show();
				btiMostrarDados.setTitle("Ocultar");
				mostrarDados = false;
			} else {

				dados.setVisibility(View.INVISIBLE);
				txtArduino01.setVisibility(View.INVISIBLE);
				txtArduino02.setVisibility(View.INVISIBLE);
				txtArduino03.setVisibility(View.INVISIBLE);
				txtArduino04.setVisibility(View.INVISIBLE);
				txtArduino05.setVisibility(View.INVISIBLE);
				Toast.makeText(getApplicationContext(), "Dados invisiveis", Toast.LENGTH_LONG).show();
				btiMostrarDados.setTitle("Mostrar");
				mostrarDados = true;
			}

			break;

		case R.id.item8:
			InfoFragment dFragment = new InfoFragment();
			// Show DialogFragment
			dFragment.show(fm, "Dialog Fragment");
			break;
		}

		return true;
	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void onPause() {
		super.onPause();

		// if (btt != null) {
		// btt.interrupt();
		// btt = null;
		// btnConectar.setText("Conectar");
		// }

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("frente", frente);
		editor.putString("direita", direita);
		editor.putString("esquerda", esquerda);
		editor.putString("tras", tras);
		editor.putString("x", x);
		editor.putString("y", y);
		editor.putString("z", z);
		editor.putString("a", a);
		editor.putString("b", b);
		editor.putString("c", c);
		editor.putString("conteudoAVoltar", conteudoAVoltar);

		// Confirma a gravação dos dados
		editor.commit();
	}

	@Override
	public void onResume() {
		super.onResume();
		acaoDosBotoes();
	}
	
	@Override
	public void onStop() {
		super.onStop();;
		//interromperBluetooth();
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	

}
