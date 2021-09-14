package caleb.app.android.formalgrammarcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        terminalJob();
        listJob();
    }

    private void listJob() {
        Button button = findViewById(R.id.action_btn);
        EditText editText = findViewById(R.id.primary_edittext);
        TextView textView = findViewById(R.id.txt_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GrammarConverter grammarConverter = new GrammarConverter();
                String input = editText.getText().toString();
                String result = grammarConverter.notTerminalToTerminal(input);
                if (result.equals(input))
                    Toast.makeText(MainActivity.this, "Not possible", Toast.LENGTH_SHORT).show();
                else
                    textView.setText(grammarConverter.notTerminalToTerminal(input));
            }
        });
    }

    private void terminalJob() {
        Button button = findViewById(R.id.action_list_btn);
        EditText editText = findViewById(R.id.secondary_edittext);
        TextView textView = findViewById(R.id.txt_main_list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GrammarConverter grammarConverter = new GrammarConverter();
                String input = editText.getText().toString();
                try{
                    int amount = Integer.parseInt(input);
                    textView.setText(grammarConverter.generateSortedListOfTerminalSymbols(amount).toString());

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Not possible", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}