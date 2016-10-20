#include "inicialize.h"
String leituraBluetooth;

void setup() {
  // put your setup code here, to run once:

  inicialize();
}

void loop() {
  // put your main code here, to run repeatedly:

  
    
    if (serial1.available()) {
       leituraBluetooth = serial1.readStringUntil(';');
       serial1.print(leituraBluetooth);
       controleBluetooth(leituraBluetooth[0]);
    }
 
  
}

