public class State {
    static final char[] PUNC = {',','\'','"','{','}'};
    static final char[] NAME = "abcdefghijklmnopqrstuvwxyz_".toCharArray();
    static final char[] NUM = {'1','2','3','4','5','6','7','8','9','0'};
    static final char[] OP = {'-','+','*','/','%'};

    LexicalAnalyzer la;

    public State(LexicalAnalyzer lex) {
        la = lex;
    }
    
    public int token(char c) {
        if (contains(PUNC, c)) {
            return 1;
        }
        if (contains(NAME, c)) {
            return 2;
        }
        if (contains(NUM, c)) {
            return 3;
        }
        if (contains(OP, c)) {
            return 4;
        }
        return 0;
    }

    public boolean contains(char[] array, char toFind) {
        for (char c : array) {
             if (c == toFind) {
                  return true;
             }
        }
        return false;
   }
}
