//Metodo que manda os valores atuais dos sensores de refletancia do seguidor.(opcional)
void valores() {
  serial1.print("SR01=");
  serial1.print(analogRead(A0));
  serial1.print("   ");
  serial1.print("SR02=");
  serial1.print(analogRead(A1));
  serial1.print(";");
  serial1.print("SR03=");
  serial1.print(analogRead(A2));
  serial1.print("   ");
  serial1.print("SR04=");
  serial1.print(analogRead(A3));
  serial1.print(";");
  serial1.print("SR05=");
  serial1.print(analogRead(A4));
  serial1.print("   ");
  serial1.print("SR06=");
  serial1.print(analogRead(A5));
  serial1.print(";");
  serial1.print("SR07=");
  serial1.print(analogRead(A6));
  serial1.print("   ");
  serial1.print("SR08=");
  serial1.print(analogRead(A7));
  serial1.println(";");
}

//Ações feitas com os motoes de acordo com a opcao recebida.(Essencial)
//------------------------------------------------------------------------
void controleMotores(char opcao, int potencia) {
  switch (opcao) {
    case '6': // Direita
      digitalWrite(motorDireitoTras, HIGH);
      digitalWrite(motorDireitoFrente, LOW);
      digitalWrite(motorEsquerdoFrente, HIGH);
      digitalWrite(motorEsquerdoTras, LOW);
      analogWrite(motorDireitoSPEED, potencia);
      analogWrite(motorEsquerdoSPEED, potencia);
      break;
    case '4': // Esquerda
      digitalWrite(motorDireitoTras, LOW);
      digitalWrite(motorDireitoFrente, HIGH);
      digitalWrite(motorEsquerdoFrente, LOW);
      digitalWrite(motorEsquerdoTras, HIGH);
      analogWrite(motorDireitoSPEED, potencia);
      analogWrite(motorEsquerdoSPEED, potencia);
      break;
    case '8': // Frente
      digitalWrite(motorDireitoTras, LOW);//n1  2
      digitalWrite(motorDireitoFrente, HIGH);//n2 4
      digitalWrite(motorEsquerdoFrente, HIGH);//n3 5
      digitalWrite(motorEsquerdoTras, LOW);//n4  7
      analogWrite(motorDireitoSPEED, potencia);
      analogWrite(motorEsquerdoSPEED, potencia);
      break;
    case '2': // Trás
      digitalWrite(motorDireitoTras, HIGH);
      digitalWrite(motorDireitoFrente, LOW);
      digitalWrite(motorEsquerdoFrente, LOW);
      digitalWrite(motorEsquerdoTras, HIGH);
      analogWrite(motorDireitoSPEED, potencia);
      analogWrite(motorEsquerdoSPEED, potencia);
      break;
    case '0':
      digitalWrite(motorDireitoTras, LOW);
      digitalWrite(motorDireitoFrente, LOW);
      digitalWrite(motorEsquerdoFrente, LOW);
      digitalWrite(motorEsquerdoTras, LOW);
      analogWrite(motorDireitoSPEED, 0);
      analogWrite(motorEsquerdoSPEED, 0);
      break;
      //Chama a função de valores
    case 'y':
      valores();//opcional
      delay(100);
      break;
  }
}

//Função para o led RGB
//------------------------------------------------------------------------
void controleLedRGB(int r, int g, int b) {
  analogWrite(LEDR, r);
  analogWrite(LEDG, g);
  analogWrite(LEDB, b);
}

