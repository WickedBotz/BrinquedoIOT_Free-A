package com.example.appbrinquedoopeniot_Free;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ActionBar;
import android.app.ListActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class PairedDevices extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		/*
		 * Esse trecho n?o ? essencial, mas da um melhor visual a� lista.
		 * Adiciona um titulo a lista de dispositivos pareados utilizando o
		 * layout text_header.xml.
		 */
		ListView lv = getListView();
		LayoutInflater inflater = getLayoutInflater();
		View header = inflater.inflate(R.layout.text_header, lv, false);
		((TextView) header.findViewById(R.id.textView)).setText("\nDispositivos pareados\n");
		lv.addHeaderView(header, null, false);


		/*
		 * Usa o adaptador Bluetooth para obter uma lista de dispositivos
		 * pareados.
		 */
		BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
		List<BluetoothDevice> pairedDevices = new LinkedList<BluetoothDevice>(btAdapter.getBondedDevices());

        //Comparador usado para ordenar uma lista em ordem alfabetica
		Comparator<BluetoothDevice> ALPHABETIC_ORDER = new Comparator<BluetoothDevice>() {
			@Override
			public int compare(BluetoothDevice device1, BluetoothDevice device2) {
				return device1.getName().compareTo(device2.getName());
			}
		};
        //Ordena a lista em ordem Alfabetica
		Collections.sort(pairedDevices, ALPHABETIC_ORDER);
		/*
		 * Cria um modelo para a lista e o adiciona a� tela. Se houver
		 * dispositivos pareados, adiciona cada um a lista.
		 */
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				adapter.add(device.getName() + "\n" + device.getAddress());
			}
		}
	}

	/*
	 * Este metodo ? executado quando o usuario seleciona um elemento da lista.
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		/*
		 * Extrai nome e endere�o a partir do conteudo do elemento selecionado.
		 * Nota: position-1 � utilizado pois adicionamos um t�tulo � lista e o valor
		 * de position recebido pelo m�todo � deslocado em uma unidade.
		 */
		String item = (String) getListAdapter().getItem(position - 1);
		String devName = item.substring(0, item.indexOf("\n"));
		String devAddress = item.substring(item.indexOf("\n") + 1, item.length());

		/*
		 * Utiliza um Intent para encapsular as informa��es de nome e endere�o.
		 * Informa a Activity principal que tudo foi um sucesso! Finaliza e
		 * retorna a Activity principal.
		 */
		Intent returnIntent = new Intent();
		returnIntent.putExtra("btDevName", devName);
		returnIntent.putExtra("btDevAddress", devAddress);
		setResult(RESULT_OK, returnIntent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        // Preenche o menu; isto acrescenta itens ? barra de a??o se ela estiver presente.
		getMenuInflater().inflate(R.menu.paired_devices, menu);
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		return true;
	}

	public boolean onMenuItemSelected(int panel, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		}

		return true;
	};

}
