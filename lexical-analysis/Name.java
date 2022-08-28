public class Name implements Token {
    // while its any valid AWK punctuation, stay in this state
    LexicalAnalyzer la;
    private String chunk;
    private String type = "Name";

    public Name(LexicalAnalyzer la) {
        this.la = la;
        chunk = "";
    }

    @Override
    public void patternMatch() {
        while (la.getPlace() != la.getSourceCode().length()) {
            if (!contains(NAME,la.getCharAtPlace())) {
                la.addToken(chunk);
                la.setToken(new Zero(la));
                la.getToken().patternMatch();
            } else {
                chunk += la.getCharAtPlace();  
                la.incPlace();   
            }
        }
        if (la.getPlace() == la.getSourceCode().length() && la.getToken() instanceof Name) {
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