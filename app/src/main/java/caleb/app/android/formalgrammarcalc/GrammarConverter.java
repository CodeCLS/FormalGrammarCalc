package caleb.app.android.formalgrammarcalc;

import java.util.ArrayList;
import java.util.HashMap;

public class GrammarConverter {
    private static final String TAG = "GrammarCOnverter";
    private HashMap<String,String> rules = new HashMap<>();

    public GrammarConverter() {
        createRulesList();
    }

    private void createRulesList() {
        rules.put("S","ABS");
        //rules.put("S",""); Won't be needed(Not enough time)
        rules.put("BA","AB");
        rules.put("BS","b");
        rules.put("Bb","bb");
        rules.put("Ab","ab");
        rules.put("Aa","aa");
    }
    private boolean regularGrammaticInspectorOutcome(String outcome){
        int amountOfUpper = 0;
        int amountOfLower = 0;
        for (int i = 0; i< outcome.length();i++){
            char character = outcome.charAt(i);
            if (character >= 'A' && character <= 'Z'){
                amountOfUpper+=1;
            }
            else if (character >= 'a' && character <= 'z'){
                amountOfLower+=1;
            }
        }
        if (amountOfUpper >= 1){
            return false;
        }
        else{
            return true;
        }

    }

    /**
     * A recursive function that transforms non terminal symbols to terminal symbols if possible
     * @param string the base string (example: ABS)
     * @return the base string transformed to terminal symbols if possible
     */

    public String notTerminalToTerminal(String string){
        for(int i = 0; i< string.length(); i++){
            Character one = string.charAt(i);
            if (i+1 != string.length()) {
                char two = string.charAt(i + 1);
                String connected = one+ Character.toString(two);
                if (rules.containsKey(connected) && rules.get(connected) != null){
                    connected = rules.get(connected);
                    string = string.substring(0,i) +
                            connected + notTerminalToTerminal(string.substring(i+2));
                    string = notTerminalToTerminal(string);
                }
            }
            else
                return string;
        }
        return string;
    }

    /**
     * Most simple way to have a growing list of terminal elements
     * @param amount the amount of terminal elements
     * @return a list with a growing length of terminal elements
     */
    public ArrayList<String> generateSortedListOfTerminalSymbols(int amount){
        String start = "ABS";
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0;i<amount;i++) {
            String result = notTerminalToTerminal(start);
            if (!array.contains(result))
                array.add(result);
            else
                array.add(notTerminalToTerminal(new String(new char[i+1]).replace("\0", start)));
        }
        return array;
    }
}
