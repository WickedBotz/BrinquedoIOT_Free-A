int ledFreDir = 7, ledFreEsq = 8, ledTraDir = 10, ledTraEsq = 9;

void controleLeds(int opcao) {
  switch (opcao) {
    case 1: // Ligado todos os leds
      digitalWrite(ledFreDir, HIGH);
      digitalWrite(ledFreEsq, HIGH);
      digitalWrite(ledTraDir, HIGH);
      digitalWrite(ledTraEsq, HIGH);
      break;
    case 2: // Ligado leds da frente
      digitalWrite(ledFreDir, HIGH);
      digitalWrite(ledFreEsq, HIGH);
      digitalWrite(ledTraDir, LOW);
      digitalWrite(ledTraEsq, LOW);
      break;
    case 3: // Ligado leds de traz
      digitalWrite(ledFreDir, LOW);
      digitalWrite(ledFreEsq, LOW);
      digitalWrite(ledTraDir, HIGH);
      digitalWrite(ledTraEsq, HIGH);
      break;
    case 4: // Ligado leds da direita
      digitalWrite(ledFreDir, HIGH);
      digitalWrite(ledFreEsq, LOW);
      digitalWrite(ledTraDir, HIGH);
      digitalWrite(ledTraEsq, LOW);
      break;
    case 5: // Ligado leds da esquerda
      digitalWrite(ledFreDir, LOW);
      digitalWrite(ledFreEsq, HIGH);
      digitalWrite(ledTraDir, LOW);
      digitalWrite(ledTraEsq, HIGH);
      break;
    default: // Desligado todos leds
      digitalWrite(ledFreDir, LOW);
      digitalWrite(ledFreEsq, LOW);
      digitalWrite(ledTraDir, LOW);
      digitalWrite(ledTraEsq, LOW);
      break;
  }
}

