import java.util.Map;
import java.util.HashMap;
//import java.util.Set;

public class LexicalAnalyzer {

    private String sourceCode;
    private State state;
    private int place;

    Map<State, String> analyzed = new HashMap<State, String>();
    
    public LexicalAnalyzer(String s) {
        sourceCode = s;
        state = new State(this);
        place = 0;
    }

    

    public void setState(State newState) {
        state = newState;
    }

    public void addToken(String lexeme) {
        analyzed.put(state, lexeme);
    }

    public State getState() {
        return state;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public int getSourceCodeLength() {
        return sourceCode.length();
    }

    public int getPlace() {
        return place;
    }

    public void incPlace() {
        place++;
    }

    public char getCharAtPlace() {
        return sourceCode.charAt(place);
    }

    /*public void print() {
        Set<State> states = analyzed.keySet();
        for (State t : states) {
            System.out.println(s.getType() + ": " + analyzed.get(t));
        }
    }*/
}