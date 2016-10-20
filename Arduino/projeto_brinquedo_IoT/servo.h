Servo servo;

void contoleServo(int angulo) {
  servo.attach(5);
  servo.write(map(angulo, 0, 50, 75, 125));
}

