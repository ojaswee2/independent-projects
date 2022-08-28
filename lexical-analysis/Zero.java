public class Zero implements Token {
    
    LexicalAnalyzer la;
    private String type = "Zero";

    public Zero(LexicalAnalyzer la) {
        this.la = la;
    }

    @Override
    public void patternMatch() {
        if (la.getPlace() == la.getSourceCodeLength()) {
            la.print();
            } else if (contains(PUNC,la.getCharAtPlace())) {
                Punctuation punc = new Punctuation(la);
                la.setToken(punc);
                punc.patternMatch();
            } else if (contains(NAME,la.getCharAtPlace())) {
                Name name = new Name(la);
                la.setToken(name);
                name.patternMatch();
            }
    }

    @Override
    public String getType() {
        return type;
    }
}