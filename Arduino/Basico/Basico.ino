
#include "Servo.h"
#include <SoftwareSerial.h>
//---------------------------------------------------------
// Se tudo estiver conectado corretamente,
//Pode mudar as portas abaixo de acordo como foi feito sua conexão dos 
//Componentes no arduino
#define PINBTX 12
#define PINBRX 8

int motorEsquerdoSPEED = 3;
int motorDireitoSPEED = 6;

int motorEsquerdoFrente = 2;
int motorDireitoFrente = 7;
int motorDireitoTras = 5;
int motorEsquerdoTras = 4;

//LED RGB refencia as portas
#define LEDR 11
#define LEDG 10
#define LEDB 9
//---------------------------------------------------------

//Refencia a porta que esta conectado o bluetooth
SoftwareSerial serial1(PINBRX, PINBTX);
void setup() {
  Serial.begin(9600);
  serial1.begin(9600);

  pinMode(motorEsquerdoTras, OUTPUT);
  pinMode(motorEsquerdoFrente, OUTPUT);
  pinMode(motorDireitoFrente, OUTPUT);
  pinMode(motorDireitoTras, OUTPUT);
  pinMode(motorEsquerdoSPEED, OUTPUT);
  pinMode(motorDireitoSPEED, OUTPUT);

  digitalWrite(motorDireitoFrente, HIGH);
  digitalWrite(motorDireitoTras, LOW);
  digitalWrite(motorEsquerdoTras, LOW);
  digitalWrite(motorEsquerdoFrente, HIGH);


  delay(1000);
  controleLedRGB(255, 255, 0);
}

void loop() {
  //Se tiver algo na serial(Bluetooth ou outros) manda o valor para controle motores
  if (serial1.available() > 0) {
    //Char e velocidade maxima de 255
    controleMotores(serial1.read(), 255);
  }
}
