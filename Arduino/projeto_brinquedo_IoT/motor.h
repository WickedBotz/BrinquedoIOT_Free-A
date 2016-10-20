int pinIN1 = 2, pinIN2 = 4, pinENA = 3;

void controleMotor(int opcao, int potencia) {
  switch (opcao) {
    case 1: // Frente
      digitalWrite(pinIN1, LOW);
      digitalWrite(pinIN2, HIGH);
      analogWrite(pinENA, potencia);
      break;
    case 2: // Trás
      digitalWrite(pinIN1, HIGH);
      digitalWrite(pinIN2, LOW);
      analogWrite(pinENA, potencia);
      break;
    default: // Desligado
      digitalWrite(pinIN1, LOW);
      digitalWrite(pinIN2, LOW);
      analogWrite(pinENA, 0);
  }
}

