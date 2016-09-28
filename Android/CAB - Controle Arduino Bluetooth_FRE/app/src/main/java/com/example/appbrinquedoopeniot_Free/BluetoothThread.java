package com.example.appbrinquedoopeniot_Free;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.InputStream;
import java.io.OutputStream;

import android.os.Handler;
import android.os.Message;

import android.util.Log;

import java.util.UUID;

/**
 * A thread that connects to a remote device over Bluetooth, and reads/writes
 * data using message Handlers. A delimiter character is used to parse messages
 * from a stream, and must be implemented on the other side of the connection as
 * well. If the connection fails, the thread exits.
 *
 * Usage:
 *
 * BluetoothThread t = BluetoothThread("00:06:66:66:33:89", new Handler() {
 * 
 * @Override public void handleMessage(Message message) { String msg = (String)
 *           message.obj; do_something(msg); } });
 *
 *           Handler writeHandler = t.getWriteHandler(); t.start();
 */
@SuppressLint({ "DefaultLocale", "HandlerLeak" })
public class BluetoothThread extends Thread {

	// Tag for logging
	private static final String TAG = "BluetoothThread";

	// Delimiter used to separate messages
	private static final char DELIMITER = ';';

	// UUID that specifies a protocol for generic bluetooth serial communication
	private static final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	// Ira armazenar o endere�o MAC do disposivo Bluetooth
	private final String address;

	// BluetoothSocket � um ponto de conex�o que permite trocar dados com outro
	// disposivo bleutooth atravs do ImputStream() e OutputStream()
	public BluetoothSocket socket;

	// BluetoothSocket � um ponto de conex�o que permite trocar dados com outro
	// disposivo bleutooth atravs do ImputStream() e OutputStream()
	private OutputStream outStream;
	private InputStream inStream;

	// Handlers usado para passar dados entre threads
	private final Handler readHandler;
	private final Handler writeHandler;

	// Buffer used to parse messages
	private String rx_buffer = "";

	/**
	 * Construtor da classe, recebe o endere�o MAC do dispositivo bluetooth
	 * e um Handler para as mensagens recebidas.
	 *
	 */
	public BluetoothThread(String address, Handler handler) {

		this.address = address.toUpperCase();
		this.readHandler = handler;

		writeHandler = new Handler() {
			@Override
			public void handleMessage(Message message) {
				write((String) message.obj);
			}
		};
	}
	
	

	/**
	 * Devolve o manipulador(Handler) de escrita para a conex�o. As mensagens
	 * recebidas por este manipulador(handler) sera escrito no Bluetooth socket.
	 */
	public Handler getWriteHandler() {
		return writeHandler;
	}

	/**
	 * Conectar o Bluetooth socket, ou lan�ar uma exe��o se ele falhar.
	 */
	private void connect() throws Exception {

		Log.i(TAG, "Attempting connection to " + address + "...");

		// Verifica se a dispositivo bluetooth no aparelho, ou se o bluetooth esta ativado.
		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		if ((adapter == null) || (!adapter.isEnabled())) {
			throw new Exception("Bluetooth adapter not found or not enabled!");
		}

		// Find the remote device
		BluetoothDevice remoteDevice = adapter.getRemoteDevice(address);

		// Create a socket with the remote device using this protocol
		socket = remoteDevice.createRfcommSocketToServiceRecord(uuid);

		// Make sure Bluetooth adapter is not in discovery mode
		adapter.cancelDiscovery();

		// Connect to the socket
		socket.connect();

		// Get input and output streams from the socket
		outStream = socket.getOutputStream();
		inStream = socket.getInputStream();

		Log.i(TAG, "Connected successfully to " + address + ".");
	}

	/**
	 * Disconnect the streams and socket.
	 */
	private void disconnect() {

		if (inStream != null) {
			try {
				inStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (outStream != null) {
			try {
				outStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (socket != null) {
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Return data read from the socket, or a blank string.
	 */
	private String read() {

		String s = "";

		try {
			// Check if there are bytes available
			if (inStream.available() > 0) {

				// Read bytes into a buffer
				byte[] inBuffer = new byte[1024];
				int bytesRead = inStream.read(inBuffer);

				// Convert read bytes into a string
				s = new String(inBuffer, "ASCII");
				s = s.substring(0, bytesRead);
			}

		} catch (Exception e) {
			Log.e(TAG, "Read failed!", e);
		}

		return s;
	}

	/**
	 * Write data to the socket.
	 */
	private void write(String s) {

		try {
			// Add the delimiter
			// s += DELIMITER;
			byte[] msgBuffer = s.getBytes();
			// Convert to bytes and write
			// outStream.write(s.getBytes());
			outStream.write(msgBuffer);
			Log.i(TAG, "[SENT] " + s);

		} catch (Exception e) {
			Log.e(TAG, "Write failed!", e);

		}
	}

	/**
	 * Pass a message to the read handler.
	 */
	private void sendToReadHandler(String s) {

		Message msg = Message.obtain();
		msg.obj = s;
		readHandler.sendMessage(msg);
		Log.i(TAG, "[RECV] " + s);
	}

	/**
	 * Send complete messages from the rx_buffer to the read handler.
	 */
	private void parseMessages() {

		// Find the first delimiter in the buffer
		int inx = rx_buffer.indexOf(DELIMITER);

		// If there is none, exit
		if (inx == -1)
			return;

		// Get the complete message
		String s = rx_buffer.substring(0, inx);

		// Remove the message from the buffer
		rx_buffer = rx_buffer.substring(inx + 1);

		// Send to read handler
		sendToReadHandler(s);

		// Look for more complete messages
		parseMessages();
	}

	/**
	 * Entry point when thread.start() is called.
	 */
	public void run() {
		
		// Attempt to connect and exit the thread if it failed
		try {
			connect();
			sendToReadHandler("CONNECTED");
		} catch (Exception e) {
			Log.e(TAG, "Failed to connect!", e);
			sendToReadHandler("CONNECTION FAILED");
			disconnect();
			return;
		}

		// Loop continuously, reading data, until thread.interrupt() is called
		while (!this.isInterrupted()) {

			// Make sure things haven't gone wrong
			if ((inStream == null) || (outStream == null)) {
				Log.e(TAG, "Lost bluetooth connection!");
				break;
			}

			// Read data and add it to the buffer
			String s = read();
			if (s.length() > 0)
				rx_buffer += s;

			// Look for complete messages
			parseMessages();
		}

		// If thread is interrupted, close connections
		disconnect();
		sendToReadHandler("DISCONNECTED");
	}
}
