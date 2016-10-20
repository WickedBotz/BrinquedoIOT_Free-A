#include <Servo.h>
#include <SoftwareSerial.h>
#include "bluetooth.h"

void inicialize() {
  // portas digitais para os leds
  pinMode(ledFreDir, OUTPUT);
  pinMode(ledFreEsq, OUTPUT);
  pinMode(ledTraDir, OUTPUT);
  pinMode(ledTraEsq, OUTPUT);
  // estado inicial das portas digitais dos leds
  digitalWrite(ledFreDir, LOW);
  digitalWrite(ledFreEsq, LOW);
  digitalWrite(ledTraDir, LOW);
  digitalWrite(ledTraEsq, LOW);

  // portas digitais para o motor
  pinMode(pinIN1, LOW);
  pinMode(pinIN2, LOW);
  pinMode(pinENA, LOW);
  // estado inicial das portas digitais do motor
  digitalWrite(pinIN1, LOW);
  digitalWrite(pinIN2, LOW);
  digitalWrite(pinENA, LOW);

  // comunicação via serial para bluetooth
  serial1.begin(9600);
  serial1.setTimeout(10);
  Serial.begin(9600);
}

