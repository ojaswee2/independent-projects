public interface Token {

     static final char[] PUNC = {',','\'','"','{','}'};
     static final char[] NAME = "abcdefghijklmnopqrstuvwxyz_".toCharArray();
     static final char[] NUM = {'1','2','3','4','5','6','7','8','9','0'};

     void patternMatch();
     String getType();
     
     default public boolean contains(char[] array, char toFind) {
          for (char c : array) {
               if (c == toFind) {
                    return true;
               }
          }
          return false;
     }
}