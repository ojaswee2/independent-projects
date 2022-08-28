public class Punctuation implements Token {
    LexicalAnalyzer la;
    private String chunk;
    private String type = "Punctuation";

    public Punctuation(LexicalAnalyzer la) {
        this.la = la;
        chunk = "";
    }

    @Override
    public void patternMatch() {
        while (la.getPlace() != la.getSourceCode().length()) {
            if (!contains(PUNC,la.getCharAtPlace())) {
                la.addToken(chunk);
                la.setToken(new Zero(la));
                la.getToken().patternMatch();
            } else {
                chunk += la.getCharAtPlace();  
                la.incPlace();   
            }
        }
        if (la.getPlace() == la.getSourceCode().length() && la.getToken() instanceof Punctuation) {
            la.addToken(chunk);
            la.setToken(new Zero(la));
            la.getToken().patternMatch();
        }
    }

    @Override
    public String getType() {
        return type;
    }
}