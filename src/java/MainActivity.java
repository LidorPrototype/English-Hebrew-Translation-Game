package com.example.translationgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Hebrew Mode Is To Translate From Hebrew To English
    // English Mode Is To Translate From English To Hebrew

    protected TextView textViewQuestion, textViewLevel, textViewScore, textViewVictory, textViewVictory2, textViewTitle;
    protected EditText editTextTranslation;
    protected Button btnDone, btnReset;
    protected RadioGroup radioGroupLanguagesOptions;
    protected RadioButton radioButtonEnglish ,radioButtonHebrew;
    protected TextView textViewTotalWordsTranslated, textViewCorrectWords, textViewWordsLeft, textViewHighScore;
    protected String[] englishWords = {  // Level - Easy  - index 0 till 35
                                        "Grass", "Knee", "Elephant", "Mirror", "House", "Speaker", "Country", "Continent", "Wine", "Rug", "World", "Deposit",
                                        "Desktop", "Martial Arts", "Capsule", "Bone Marrow", "Phenomenon", "Protein", "Keyboard", "Code", "Spaceship", "Sin",
                                        "Tidy", "Camping","Chair", "Bombastic", "Queue", "Computer", "Cheque", "Petition", "Sitcom", "University",
                                        "Predict", "Garden", "Dawn", "Grant",
                                        // Level - Intermediate  - index 36 till 64
                                        "Tremendous", "Solar System", "Status Quo", "Duplicity", "Quirky", "Asteroid", "Democracy", "Software Engineering",
                                        "Courageous", "Eclipse", "Communism", "Confidant", "The Milky Way", "Dictatorship", "Negligent", "Particle",
                                        "Lunar Eclipse", "Sanitation", "Corporation", "Abnegation", "Apathetic", "Vitriolic", "Cylinder", "A Little Towel",
                                        "Sophisticated", "Ambiguous", "Nonplussed", "Abundance", "Lieutenant",
                                        // Level - Hard  - index 65 till 102
                                        "Nausea", "Erudite", "Pager", "Cone", "Chairman", "Amenable", "Pancreas", "Internet", "Hexagon", "Convivial",
                                        "Denominator", "Moxie", "Appease", "Octagon", "Trenchant", "Referendum", "Esophagus", "Brazen", "Waffle",
                                        "Decry", "Infamy", "Complacency", "Prospectus", "Eloquent", "Flabbergasted", "Jet Leg", "Beguile", "Liminality",
                                        "Yoke", "Furtive", "Irregardless", "Anachronistic", "Circumlocution", "Icosahedron", "Iconoclast", "Utilitarian",
                                        "Clairvoyant", "Modus Vivendi",
                                        // Level - Last Word  - index 103
                                        "Supercalifragilisticexpialidocious"};//word number 104
    protected String[] hebrewWords = {  // Level - Easy  - index 0 till 35
                                        "דשא", "ברך", "פיל", "מראה", "בית", "רמקול","מדינה", "יבשת", "יין", "שטיח", "עולם", "הפקדה", "שולחן עבודה",
                                        "אומנויות לחימה", "קפסולה", "מח עצם", "תופעה", "חלבון", "מקלדת", "צופן", "חללית", "חטא", "מסודר", "מחנאות",
                                        "כיסא","בומבסטי", "תור", "מחשב", "הַמְחָאָה", "עֲצוּמָה", "קומדיית מצבים", "אוניברסיטה", "לחזות", "גינה", "שחר",
                                        "מענק",
                                        // Level - Intermediate  - index 36 till 64
                                        "עצום", "מערכת השמש", "מצב קיים", "כפילות", "משונה", "אסטרואיד", "דמוקרטיה", "הנדסת תוכנה", "אמיץ לב", "ליקוי חמה",
                                        "קומוניזם", "סודי", "שביל החלב", "דיקטטורה", "רשלני", "חלקיק", "ליקוי ירח", "תברואה", "תאגיד", "התכנסות", "אדיש",
                                        "מזויף", "גָּלִיל", "אלונטית", "מתוחכם", "דו משמעי", "לא מבולבל", "שפע", "סגן",
                                        // Level - Hard  - index 65 till 102
                                        "בחילה", "מלומד", "זימונית", "חרוט", "יושב ראש", "מקובל", "לבלב", "מרשתת", "משושה", "משכנע", "מכנה", "תעוזה",
                                        "לפייס", "מתומן", "נוקשה", "משאל עם", "וושט", "חצוף", "אפיפית", "לזלזל", "קלון", "שלמות", "תשקיף", "רהוט",
                                        "נדהם", "יעפת", "תועבה", "רגיל - ללא מעמד", "עול", "חמקמק", "ללא הפסקה", "אנכרוניסטי", "תקיפה", "עֶשְׂרִימוֹן", "שובר אלילים",
                                        "תועלתי", "רומנטי", "הֶסְדֵּר בְּפֹעַל",
                                        // Level - Last Word  - index 103
                                        "כול כך טוב שאין מילה לתאר זאת"};//word number 104
    protected int counterIndex = 0, counterTotal = 0, counterCorrect = 0, counterLeftToTranslate = hebrewWords.length, counterTries = 3;
    protected int levelEasy = 0, levelIntermediate = 36, levelHard = 65, levelLastWord = 103;
    protected String level = " Easy", mode = "";
    protected int score = 0, pointsEasy = 5, pointsIntermediate = 10, pointsHard = 15, pointsLastWord = 20, pointsAttempt = 3, pointsMistake = 6;
    protected  int maxScore = 1030, highScore = 0;
    protected  Button btnPassword, btnNameEnter;
    protected EditText editTextPassword, editTextPlayerName;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    private ImageView imageViewBestScore;
    private View view;
    private String playerName = "Creator";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.colorWhite);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        editTextTranslation = findViewById(R.id.editTextAnswer);
        btnDone = findViewById(R.id.btnSubmitAnswer);
        btnReset = findViewById(R.id.btnReset);
        textViewLevel = findViewById(R.id.textViewLevel);
        textViewTotalWordsTranslated = findViewById(R.id.textViewNumOfWords);
        textViewCorrectWords = findViewById(R.id.textViewCorrectWords);
        textViewWordsLeft = findViewById(R.id.textViewWordsLeft);
        textViewScore = findViewById(R.id.textViewScore);
        textViewVictory = findViewById(R.id.textViewVictory);
        textViewVictory.setAlpha(0.0f);
        textViewVictory2 = findViewById(R.id.textViewVictory2);
        textViewVictory2.setAlpha(0.0f);
        textViewHighScore = findViewById(R.id.textViewHighScore);
        radioGroupLanguagesOptions = findViewById(R.id.radioGroup);
        radioButtonEnglish = findViewById(R.id.radioButtonEnglishMode);
        radioButtonHebrew = findViewById(R.id.radioButtonHebrewMode);
        btnPassword = findViewById(R.id.btnPassword);
        editTextPassword = findViewById(R.id.editTextPassword);
        imageViewBestScore = findViewById(R.id.imageViewBestScore);
        editTextPlayerName = findViewById(R.id.editTextName);
        btnNameEnter = findViewById(R.id.btnNameEnter);

        btnNameEnter.setText("");
        btnNameEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerName = editTextPlayerName.getText().toString();
                editTextPlayerName.setText("");
            }
        });

        imageViewBestScore.setAlpha(0.0f);

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Welcome - ברוכים הבאים");
        alertDialog.setMessage("In Order To Use Lidor's Awesome Game\nPlease Select A Mode!\nעל מנת להשתמש במשחק האדיר של לידור\nאנא ביחרו מצב!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "I Understand - אני מבין", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();

        textViewTitle.setText("Welcome  -  ברוכים הבאים");
        textViewQuestion.setText("Choose Mode Please  -  אנא בחרו מצב");

        //check radioButtons state
        //Toast.makeText(this, "1) " + radioButtonEnglish.isChecked() + ", 2) " + radioButtonHebrew.isChecked(), Toast.LENGTH_SHORT).show();

        level = "Easy";

        radioGroupLanguagesOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                resetGame();
            }
        });

        btnDone.setOnClickListener(this);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

        //If user didn't choose mode, preventing every other option to be accessible but choosing a mode
        if ((!(radioButtonHebrew.isChecked())) && (!(radioButtonEnglish.isChecked()))){
            editTextTranslation.setEnabled(false);
            editTextPassword.setEnabled(false);
            btnPassword.setEnabled(false);
            btnDone.setEnabled(false);
            btnReset.setEnabled(false);
        }

        items = FileHelper.readData(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        for (int i = 0; i < englishWords.length; i++) {
            adapter.add(i + ") " + englishWords[i] + Character.toString("\u2192".toCharArray()[0]) + hebrewWords[i]);
            FileHelper.writeData(items, this);
            adapter.notifyDataSetChanged();
        }

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = editTextPassword.getText().toString();
                if (password.equals("Dawn")){
                    goToAnswerStorage();
                }
            }
        });

//        ******************************
//        in order to clear all words:
//        items.clear();
//        adapter.clear();
//        adapter.notifyDataSetChanged();
//        FileHelper.writeData(items, this);
//        ******************************

    }

    // Hebrew Mode Is To Translate From Hebrew To English
    // English Mode Is To Translate From English To Hebrew
    public void radioButtonsOnClick(View view) {
        editTextTranslation.setEnabled(true);
        editTextPassword.setEnabled(true);
        btnPassword.setEnabled(true);
        btnReset.setEnabled(true);
        btnDone.setEnabled(true);
        switch (view.getId()){
            case R.id.radioButtonEnglishMode:
                level = " Easy";
                textViewTitle.setText("Good Luck In Translation Game!");
                textViewQuestion.setText("How would you translate the word  '" + englishWords[counterIndex] + "'?");
                textViewTotalWordsTranslated.setText("Total Words Translated: " + counterTotal);
                textViewCorrectWords.setText("Words Translated Correctly: " + counterCorrect);
                textViewWordsLeft.setText("Words Left To Translate: " + counterLeftToTranslate);
                textViewLevel.setText("Level is:   " + level);
                textViewScore.setText("Score: " + score);
                textViewHighScore.setText("High Score: " + highScore + " Of " + playerName);
                radioButtonEnglish.setClickable(false);
                radioButtonHebrew.setClickable(true);
                imageViewBestScore.setAlpha(1.0f);
                btnNameEnter.setText("Enter");
                new CountDownTimer(2000, 1000) { // 2000 = 2 sec
                    public void onTick(long millisUntilFinished) {
                        imageViewBestScore.setImageResource(R.drawable.english_mode);
                    }
                    public void onFinish() {
                        imageViewBestScore.setAlpha(0.0f);
                        imageViewBestScore.setImageResource(R.drawable.white_screen);
                    }
                }.start();
                break;
            case R.id.radioButtonHebrewMode:
                level = " קל";
                textViewTitle.setText("בהצלחה במשחק התירגומים!");
                textViewQuestion.setText("איך תתרגם את המילה  '" + hebrewWords[counterIndex] + "'?");
                textViewTotalWordsTranslated.setText("מילים שתורגמו: " + counterTotal);
                textViewCorrectWords.setText("מילים שתורגמו בצורה נכונה: " + counterCorrect);
                textViewWordsLeft.setText("מילים שנשארו לתירגום: " + counterLeftToTranslate);
                textViewLevel.setText("הרמה היא: " + level);
                textViewScore.setText("ניקוד: " + score);
                textViewHighScore.setText("הניקוד הטוב ביותר: " + highScore + " של " + playerName);
                radioButtonHebrew.setClickable(false);
                radioButtonEnglish.setClickable(true);
                imageViewBestScore.setAlpha(1.0f);
                btnNameEnter.setText("הכנס");
                new CountDownTimer(2000, 1000) { // 2000 = 2 sec
                    public void onTick(long millisUntilFinished) {
                        imageViewBestScore.setImageResource(R.drawable.hebrew_mode);
                    }
                    public void onFinish() {
                        imageViewBestScore.setAlpha(0.0f);
                        imageViewBestScore.setImageResource(R.drawable.white_screen);
                    }
                }.start();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        String userInput = editTextTranslation.getText().toString();
        if (userInput.equals("") || userInput == null) {
            if (radioButtonEnglish.isChecked()){
                Toast.makeText(this, "Please Enter Your Translation!", Toast.LENGTH_SHORT).show();
            }else if (radioButtonHebrew.isChecked()){
                Toast.makeText(this, "אנא הכנס את התירגום שלך!", Toast.LENGTH_SHORT).show();
            }
        }else {
            // English Mode
            if (radioButtonEnglish.isChecked()){
                // Correct
                if (userInput.equalsIgnoreCase(hebrewWords[counterIndex])) {
                    Toast.makeText(this, "Correct Translation! Congretz!", Toast.LENGTH_SHORT).show();
                    if (counterIndex >= levelEasy && counterIndex < levelIntermediate){
                        score += pointsEasy;
                    }else if (counterIndex >= levelIntermediate && counterIndex < levelHard){
                        score += pointsIntermediate;
                    }else if (counterIndex >= levelHard && counterIndex < levelLastWord){
                        score += pointsHard;
                    }else if (counterIndex == levelLastWord){
                        score += pointsLastWord;
                        counterIndex++;
                    }
                    counterCorrect++; counterLeftToTranslate--;  counterTotal++; counterIndex++; counterTries = 3;
                }else { // Wrong
                    counterTries--;
                    if (counterTries > 0) { // Is There Any More Attempts Left?
                        score -= pointsAttempt;
                    }else { // No More Attempts
                        Toast.makeText(this, "No More Attempts, Next Word!", Toast.LENGTH_SHORT).show();
                        counterLeftToTranslate--; counterTotal++; counterIndex++; editTextTranslation.setText(""); counterTries = 3;
                        score -= pointsMistake;
                    }
                    if (counterIndex == levelLastWord){
                        counterIndex++;
                    }
                }
            // Hebrew Mode
            }else if (radioButtonHebrew.isChecked()){
                if (userInput.equalsIgnoreCase(englishWords[counterIndex])) {
                    Toast.makeText(this, "תרגום נכון! כול הכבוד!", Toast.LENGTH_SHORT).show();
                    if (counterIndex >= levelEasy && counterIndex < levelIntermediate){
                        score += pointsEasy;
                    }else if (counterIndex >= levelIntermediate && counterIndex < levelHard){
                        score += pointsIntermediate;
                    }else if (counterIndex >= levelHard && counterIndex < levelLastWord){
                        score += pointsHard;
                    }else if (counterIndex == levelLastWord){
                        score += pointsLastWord;
                        //counterIndex++;
                    }
                    counterCorrect++; counterLeftToTranslate--;  counterTotal++; counterIndex++; counterTries = 3;
                }else { // Wrong
                    counterTries--;
                    if (counterTries > 0) { // Is There Any More Attempts Left?
                        score -= pointsAttempt;
                    }else { // No More Attempts
                        Toast.makeText(this, "אין עוד נסיונות, מילה הבא!", Toast.LENGTH_SHORT).show();
                        counterLeftToTranslate--; counterTotal++; counterIndex++; editTextTranslation.setText(""); counterTries = 3;
                        score -= pointsMistake;
                    }
                    if (counterIndex == levelLastWord){
                        //counterIndex++;
                    }
                }
            }
            editTextTranslation.setText("");
        }
        // Level Changing
        if (counterIndex >= levelEasy && counterIndex < levelIntermediate) {
            if (radioButtonEnglish.isChecked()){
                level = " Easy";
            }else if (radioButtonHebrew.isChecked()){
                level = " קל";
            }
        }else if (counterIndex >= levelIntermediate && counterIndex < levelHard) {
            if (counterIndex == levelIntermediate) {
                if (radioButtonEnglish.isChecked()){
                    level = " Intermediate";
                }else if (radioButtonHebrew.isChecked()){
                    level = " בינוני";
                }
            }
        }else if (counterIndex >= levelHard && counterIndex < levelLastWord) {
            if (counterIndex == levelHard) {
                if (radioButtonEnglish.isChecked()){
                    level = " Hard";
                }else if (radioButtonHebrew.isChecked()){
                    level = " קשה";
                }
            }
        }else if (counterIndex == levelLastWord) {
            if (radioButtonEnglish.isChecked()){
                level = " Last Word!";
            }else if (radioButtonHebrew.isChecked()){
                level = " מילה אחרונה!";
            }
        }//end of level changing
        // End Of Current Game:
        if (counterIndex > levelLastWord){
            textViewVictory.setAlpha(1.0f);
            if (radioButtonEnglish.isChecked()){
                textViewVictory.setText("Congratulation You've Finished The Game!");
                textViewQuestion.setText("No More Words Left To Translate!");
            }else if (radioButtonHebrew.isChecked()){
                textViewVictory.setText("כול הכבוד סיימת את המשחק!");
                textViewQuestion.setText("לא נשארו מילים לתרגום!");
            }
            if (score == maxScore){
                textViewVictory2.setAlpha(1.0f);
                imageViewBestScore.setAlpha(1.0f);
                if (radioButtonEnglish.isChecked()){
                    textViewVictory2.setText("Congratulation!!!\n" +
                            "You've Reached The Maximum Score You Can Possibly Get!\n" +
                            "You're Truly An Amazing Smart Individual!\n Rock On!");
                }else if (radioButtonHebrew.isChecked()){
                    textViewVictory2.setText("כול הכבוד!!!\n" +
                            "הצלחת/ה להגיע לתוצאה הגבוהה ביותר שניתן להגיע!\n" +
                            "את/ה באמת אינדיבידואל מדהים וחכם ביותר!\n" +
                            " יישר כוח!");
                }
            }else {
                textViewVictory2.setAlpha(1.0f);
                if (radioButtonEnglish.isChecked()) {
                    textViewVictory2.setText("However You've Done It With A Score Of Just " + score +" \n" +
                            "You're Not The Smartest Individual That Played This Game!\n" +
                            "You're Probably Not Even In The Top 5!  Lame!");
                }else if (radioButtonHebrew.isChecked()){
                    textViewVictory2.setText("אבל עשית זאת רק עם תוצאה של " + score +" \n" +
                            "את/ה לא האינדיבידואל החכם ביותר ששיחק את המשחק! \n" +
                            "כנראה גם לא האינדיבידואל ב 5 המובילים! גרוע!");
                }
            }
            editTextTranslation.setEnabled(false);
            btnDone.setEnabled(false);
        }else {
            if (radioButtonEnglish.isChecked()) {
                textViewQuestion.setText("How would you translate the word  '" + englishWords[counterIndex] + "'?");
                textViewLevel.setText("Level is:  " + level);
                textViewTotalWordsTranslated.setText("Total Words Translated: " + counterTotal);
                textViewCorrectWords.setText("Words Translated Correctly: " + counterCorrect);
            }else if (radioButtonHebrew.isChecked()){
                textViewQuestion.setText("איך תתרגם את המילה  '" + hebrewWords[counterIndex] + "'?");
                textViewLevel.setText("הרמה היא: " + level);
                textViewTotalWordsTranslated.setText("מילים שתורגמו: " + counterTotal);
                textViewCorrectWords.setText("מילים שתורגמו בצורה נכונה: " + counterCorrect);
            }
        }
        if (radioButtonEnglish.isChecked()){
            textViewScore.setText("Score: " + score);
            textViewWordsLeft.setText("Words Left To Translate: " + counterLeftToTranslate);
        }else if (radioButtonHebrew.isChecked()){
            textViewScore.setText("ניקוד: " + score);
            textViewWordsLeft.setText("מילים שנשארו לתירגום: " + counterLeftToTranslate);
        }
    }

    private void resetGame() {
        if (score > highScore){
            if (score < maxScore && score > highScore){
                imageViewBestScore.setAlpha(1.0f);
                new CountDownTimer(2000, 1000) { // 2000 = 2 sec
                    public void onTick(long millisUntilFinished) {
                        imageViewBestScore.setImageResource(R.drawable.high_score);
                    }
                    public void onFinish() {
                        imageViewBestScore.setAlpha(0.0f);
                        imageViewBestScore.setImageResource(R.drawable.white_screen);
                    }
                }.start();
            }else if (score == maxScore){
                imageViewBestScore.setAlpha(1.0f);
                new CountDownTimer(2000, 1000) { // 2000 = 2 sec
                    public void onTick(long millisUntilFinished) {
                        imageViewBestScore.setImageResource(R.drawable.best_score);
                    }
                    public void onFinish() {
                        imageViewBestScore.setAlpha(0.0f);
                        imageViewBestScore.setImageResource(R.drawable.white_screen);
                    }
                }.start();
            }
            if (radioButtonEnglish.isChecked()) {
                Toast.makeText(this, "Congratulation! New High Score Was Made!", Toast.LENGTH_SHORT).show();
                textViewHighScore.setText("High Score: " + score + " Of " + playerName);
            }else if (radioButtonHebrew.isChecked()){
                Toast.makeText(this, "כול הכבוד! שיא חדש נרשם!", Toast.LENGTH_SHORT).show();
                textViewHighScore.setText("הניקוד הטוב ביותר " + score + " של " + playerName);
            }
            highScore = score;
        }
        score = 0;
        counterCorrect = 0; counterIndex = 0; counterTotal = 0; counterTries = 3; counterLeftToTranslate = hebrewWords.length;
        if (radioButtonHebrew.isChecked()){
            if (highScore != 0) {
                Toast.makeText(this, "קדימה בוא/י ננסה לשבור את השיא!", Toast.LENGTH_SHORT).show();
            }
            level = " קל";
            textViewQuestion.setText("איך תתרגם את המילה  '" + hebrewWords[counterIndex] + "'?");
            textViewTotalWordsTranslated.setText("מילים שתורגמו: " + counterTotal);
            textViewCorrectWords.setText("מילים שתורגמו בצורה נכונה: " + counterCorrect);
            textViewWordsLeft.setText("מילים שנשארו לתירגום: " + counterLeftToTranslate);
            textViewLevel.setText("הרמה היא: " + level);
            textViewScore.setText("ניקוד: " + score);
        }else if (radioButtonEnglish.isChecked()){
            if (highScore != 0){
                Toast.makeText(this, "Let's Try To Beat The High Score!", Toast.LENGTH_SHORT).show();
            }
            level = " Easy";
            textViewQuestion.setText("How would you translate the word  '" + englishWords[counterIndex] + "'?");
            textViewTotalWordsTranslated.setText("Total Words Translated: " + counterTotal);
            textViewCorrectWords.setText("Words Translated Correctly: " + counterCorrect);
            textViewWordsLeft.setText("Words Left To Translate: " + counterLeftToTranslate);
            textViewLevel.setText("Level is:   " + level);
            textViewScore.setText("Score: " + score);
        }
        textViewVictory2.setAlpha(0.0f);
        textViewVictory.setAlpha(0.0f);
        textViewVictory2.setText("");
        textViewVictory.setText("");
        editTextTranslation.setEnabled(true);
        btnDone.setEnabled(true);
    }

    private void goToAnswerStorage() {
        Intent goToAnswersStorage = new Intent(MainActivity.this, AnswersStorageActivity.class);
        startActivity(goToAnswersStorage);
        finish();
    }

}