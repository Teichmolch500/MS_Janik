package com.example.taschenrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.*;
import android.widget.TextClock;
import android.view.Menu;
import org.mariuszgromada.math.mxparser.Expression;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText Textbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Textbox = findViewById(R.id.Eingabetext);
        Textbox.setShowSoftInputOnFocus(false);

        Textbox.setOnClickListener(new View.OnClickListener() {
            @Override
            // sobald mann ins eingabefeld klickt wird der alte Text gelöscht!
            public void onClick(View view) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    private void Update(String neuerText){
        String alterText = Textbox.getText().toString();
        // Funktion um Werte an die Ausgewählte Stelle zu setzten
        int auswahlPos = Textbox.getSelectionStart();
        String linkerText = alterText.substring(0,auswahlPos);
        String rechterText = alterText.substring(auswahlPos);
        //Textbox leeren bei eingabe
        if (getString(R.string.Textbox).equals(Textbox.getText().toString())){
            Textbox.setText(neuerText);
            Textbox.setSelection(auswahlPos + 1);
        }
        else {
            Textbox.setText(String.format("%s%s%s", linkerText, neuerText, rechterText));
            // damit die Zahl danach und nicht davor eingefügt wird.
            Textbox.setSelection(auswahlPos + 1);
        }

    }

    public void btn_Eins(View view) {
        Update("1");
    }

    public void btn_Zwei(View view) {
        Update("2");
    }

    public void btn_Drei(View view) {

        Update("3");
    }

    public void btn_Vier(View view) {
        Update("4");
    }

    public void btn_Fünf(View view) {
        Update("5");
    }

    public void btn_Sechs(View view) {
        Update("6");
    }

    public void btn_Sieben(View view) {
        Update("7");
    }

    public void btn_Acht(View view) {
        Update("8");
    }

    public void btn_Neun(View view) {
        Update("9");
    }

    public void btn_Null(View view) {
        Update("0");
    }

    public void btn_Addition(View view) {
        Update("+");
    }

    public void btn_Subtraktion(View view) {
        Update("-");
    }

    public void btn_Multiplikation(View view) {
        Update("×");
    }

    public void btn_Division(View view) {
        Update("÷");
    }

    public void btn_Löschen(View view) {
        Textbox.setText("");
    }

    public void btn_Gleich(View view) {
        String eingabe = Textbox.getText().toString();
        // Mathematische Zeichen ersetzen für die Berechnung
        eingabe = eingabe.replaceAll("÷", "/");
        eingabe = eingabe.replaceAll("×", "*");

        Expression exp = new Expression(eingabe);
        String lösung = String.valueOf(exp.calculate());
        // division durch 0 abfangen der leichte weg
        if ( lösung == "NaN") {
        lösung = "ERROR";
        }
        Textbox.setText(lösung);
        // lösung anzeigen
        Textbox.setSelection(lösung.length());
    }

    public void btn_Rückgängig(View view) {
        int Pos = Textbox.getSelectionStart();
        int textLänge = Textbox.getText().length();
        if ( Pos != 0 && textLänge != 0){
            // kann verscheidende Zeichen ersetzen
            SpannableStringBuilder auswahl = (SpannableStringBuilder) Textbox.getText();
            auswahl.replace(Pos -1, Pos, "");
            Textbox.setText(auswahl);
            Textbox.setSelection(Pos -1);
        }
    }

    public void btn_Exponent(View view) {
        Update("^");
    }

    public void btn_KlammerAuf(View view) {
        Update("(");
    }

    public void btn_KlammerZu(View view) {
        Update(")");
    }

    public void btn_Punkt(View view) {
        Update(".");
    }

    public void menu_klicked(View view) {

        // btn enable
    }

    public void menu_klicked(MenuItem item) {

    }
}
