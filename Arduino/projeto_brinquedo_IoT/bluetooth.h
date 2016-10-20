#include "motor.h"
#include "servo.h"
#include "leds.h"
#include "som.h"

boolean flag = true;

SoftwareSerial serial1(11, 12); // RX, TX

void controleBluetooth(char opcao) {

  switch (opcao) {
    case '8': // Frente
      flag = true;
      controleMotor(1, 255);
      contoleServo(25);
      controleLeds(2);
      break;
    case '6': // Direita
      contoleServo(5);
      controleLeds(4);
      if (flag) {
        controleMotor(1, 255);
      } else {
        controleMotor(2, 255);
      }
      break;
    case '4': // Esquerda
      contoleServo(45);
      controleLeds(5);
      if (flag) {
        controleMotor(1, 255);
      } else {
        controleMotor(2, 255);
      }
      break;
    case '2': // Trás
      flag = false;
      controleMotor(2, 255);
      contoleServo(25);
      controleLeds(3);
      break;
    case 'a': // Botão A
      star_wars();
      break;
    case '0': // Parar motor
      controleMotor(0, 0);
      break;
  }
}
