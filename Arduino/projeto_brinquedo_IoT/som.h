int pinSom = 6;

boolean flag_on_off = true;

void led_on_off(int note, int duration) {
  int ledFreDir = 8, ledFreEsq = 7, ledTraDir = 9, ledTraEsq = 10;
  pinMode(ledFreDir, OUTPUT);
  pinMode(ledFreEsq, OUTPUT);
  pinMode(ledTraDir, OUTPUT);
  pinMode(ledTraEsq, OUTPUT);

  tone(pinSom, note, duration);
  if (flag_on_off) {
    flag_on_off = false;
    delay(duration);
    digitalWrite(ledFreDir, LOW);
    digitalWrite(ledFreEsq, LOW);
    digitalWrite(ledTraDir, LOW);
    digitalWrite(ledTraEsq, LOW);
  } else {
    flag_on_off = true;
    digitalWrite(ledFreDir, HIGH);
    digitalWrite(ledFreEsq, HIGH);
    digitalWrite(ledTraDir, HIGH);
    digitalWrite(ledTraEsq, HIGH);
    delay(duration);
    digitalWrite(ledFreDir, LOW);
    digitalWrite(ledFreEsq, LOW);
    digitalWrite(ledTraDir, LOW);
    digitalWrite(ledTraEsq, LOW);
  }
  noTone(pinSom);
  delay(50);
}

void star_wars() {

  // Melodia do Star Wars
  int play_fist_1 [9][2] = {{440, 500}, {440, 500}, {440, 500}, {349, 350}, {523, 150}, {440, 500}, {349, 350}, {523, 150}, {440, 650}};
  for (int i = 0 ; i < 9; i++) {
    led_on_off(play_fist_1[i][0], play_fist_1[i][1]);
  }
  delay(500);
  int play_fist_2 [9][2] = {{659, 500}, {659, 500}, {659, 500}, {698, 350}, {523, 150}, {415, 500}, {349, 350}, {523, 150}, {440, 650}};
  for (int i = 0 ; i < 9; i++) {
    led_on_off(play_fist_2[i][0], play_fist_2[i][1]);
  }
  delay(500);

  int play_second_1 [9][2] = {{880, 500}, {440, 300}, {440, 150}, {880, 500}, {830, 325}, {784, 175}, {740, 125}, {698, 125}, {740, 250}};
  for (int i = 0 ; i < 9; i++) {
    led_on_off(play_second_1[i][0], play_second_1[i][1]);
  }
  delay(325);
  int play_second_2 [7][2] = {{455, 250}, {622, 500}, {587, 325}, {554, 175}, {523, 125}, {466, 125}, {523, 250}};
  for (int i = 0 ; i < 7; i++) {
    led_on_off(play_second_2[i][0], play_second_2[i][1]);
  }
  delay(350);

  int play_variant_1 [8][2] = {{349, 250}, {415, 500}, {349, 350}, {440, 125}, {523, 500}, {440, 375}, {523, 125}, {659, 650}};
  for (int i = 0 ; i < 8; i++) {
    led_on_off(play_variant_1[i][0], play_variant_1[i][1]);
  }
  delay(500);

  for (int i = 0 ; i < 9; i++) {
    led_on_off(play_second_1[i][0], play_second_1[i][1]);
  }
  delay(325);
  for (int i = 0 ; i < 7; i++) {
    led_on_off(play_second_2[i][0], play_second_2[i][1]);
  }
  delay(350);

  int play_variant_2 [8][2] = {{349, 250}, {415, 500}, {349, 375}, {523, 125}, {440, 500}, {349, 375}, {523, 125}, {440, 650}};
  for (int i = 0 ; i < 8; i++) {
    led_on_off(play_variant_2[i][0], play_variant_2[i][1]);
  }
  delay(650);
}
