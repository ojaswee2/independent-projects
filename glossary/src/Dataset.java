import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Dataset {
    private String filename = "";
    private File f;
    //private Map<String, String> alphabetLines;
    Map<Character, Map<String,String>> temp_gloss;
    Map<Character, Boolean> is_full;

    public Dataset(String name) {
        //alphabetLines = new HashMap<String,String>();
        temp_gloss = new HashMap<Character, Map<String,String>>();
        is_full = new HashMap<Character, Boolean>();
        for (int i = 97; i < 124; i++) {
            is_full.put((char)i,false);
            temp_gloss.put((char)i,new HashMap<String, String>());
        }
        //ln("here");
        //printMap();
        filename = name + ".txt";
        try {
            f = new File(filename);
            if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        }
        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }

    public String getName() {
        return filename;
    }

    public void add(String key, String value) {
        // need to implement miscellaneous
        char first_letter = key.charAt(0);
        if (temp_gloss.get(first_letter) != null) {
            if (temp_gloss.get(first_letter).size() == 10) {
                try {
                    FileWriter myWriter = new FileWriter(filename, true);
                    myWriter.append(hashToFile(hash(key)) + " : " + hashToFile(hash(value)) + "\r\n");
                    myWriter.close();
                    //System.out.println("Successfully wrote to the file.");
                  } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                  }
            } else {
                temp_gloss.get(first_letter).put(key, value);
                if (temp_gloss.get(first_letter).size() == 10) {
                    is_full.replace(first_letter, true);
                }
            }
        }
    }

    public void printMap() {
        for (Character c : temp_gloss.keySet()) {
            System.out.print(c + " ");
            for (String s : temp_gloss.get(c).keySet()) {
                System.out.print(s + ":" + temp_gloss.get(c).get(s) + " ");
            }
            System.out.print("\n");
        }
    }

    public void clear() {
        // make this delete everything
        temp_gloss.clear();
        f.delete();
    }

    public int[] hash(String s) {
        int[] hashArray = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            hashArray[i] = (int)s.charAt(i);
        }
        return hashArray;
    }

    public String hashToFile(int[] arr) {
        String s = "";
        for (int i = 0; i < arr.length;i++) {
            if (arr[i] == 0) {
                break;
            }
            s += arr[i] + " ";
        }
        return s;
    }

    public String hashToString(int[] arr) {
        String s = "";
        for (int i = 0; i < arr.length;i++) {
            if (arr[i] == 0) {
                break;
            }
            s += (char)arr[i];
        }
        return s;
    }

    public String get(String key) {
        boolean b = true;
        int[] k = new int[32];
        int[] v = new int[100];
        
        int[] keyToHash = hash(key);
        /*System.out.println("keyToHash length: " + keyToHash.length);
        for (int i = 0; i < keyToHash.length; i++) {
            System.out.print(keyToHash[i]);
        }*/
        char first = key.charAt(0);
        if (is_full.get(first)) {
            try {
                Scanner lineReader = new Scanner(f);
                while (lineReader.hasNextLine()) {
                    Scanner intReader = new Scanner(lineReader.nextLine());
                    int i = 0;
                    int j = 0;
                    while (intReader.hasNext()) { //return hashToString(v);
                        if (intReader.hasNextInt()) {
                            if (b) {
                                k[i] = intReader.nextInt();
                                //System.out.println("k" + k[i]);
                                i++;
                            } else {
                                v[j] = intReader.nextInt();
                                //System.out.println("v" + v[j]);
                                j++;
                            }
                        } else {
                            intReader.next();
                            b = false;
                            //System.out.println("bool: " + b);
                        }
                    }
                    for (int c = 0; c < keyToHash.length; c++) {
                        if (keyToHash[c] != k[c]) {
                            //System.out.println("here2");
                            break;
                        }
                        if (c == (keyToHash.length - 1)) {
                            //System.out.println("here3");
                            return hashToString(v);
                        }
                    }
                    //System.out.println("here3");
                    k = new int[32];
                    v = new int[100];
                    b = true;
                }
                b = true;
              } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
        }
        return temp_gloss.get(first).get(key);
    }

    public void delete(String key) {
        char first = key.charAt(0);
        if (temp_gloss.get(first).containsKey(key)) {
            System.out.println("found in map");
            temp_gloss.get(first).remove(key);
            is_full.replace(first,false);
            return;
        }
        deleteFromFile(key);
    }

    public void deleteFromFile(String key) {
        boolean thisLine = false;
        boolean b = true;
        int[] k = new int[32];
        int[] v = new int[100];
        int[] keyToHash = hash(key);
        String line = "";

        
        File temp = new File("temp.txt");

        try {
            if (temp.createNewFile()) {
            System.out.println("File created: " + temp.getName());
        }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            Scanner lineReader = new Scanner(f);
            while (lineReader.hasNextLine()) {
                line = lineReader.nextLine();
                Scanner intReader = new Scanner(line);
                int i = 0;
                int j = 0;
                while (intReader.hasNext()) { //return hashToString(v);
                    if (intReader.hasNextInt()) {
                        if (b) {
                            k[i] = intReader.nextInt();
                            //System.out.println("k" + k[i]);
                            i++;
                        } else {
                            v[j] = intReader.nextInt();
                            //System.out.println("v" + v[j]);
                            j++;
                        }
                    } else {
                        intReader.next();
                        b = false;
                        //System.out.println("bool: " + b);
                    }
                }
                for (int c = 0; c < keyToHash.length; c++) {
                    if (keyToHash[c] != k[c]) {
                        //System.out.println("here2");
                        break;
                    }
                    if (c == (keyToHash.length - 1)) {
                        //System.out.println("here3");
                        thisLine = true;
                    }
                }
                //System.out.println("here3");
                if (!thisLine) {
                    try {
                        FileWriter myWriter = new FileWriter("temp.txt", true);
                        myWriter.append(line + "\r\n");
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                      } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                      }
                } else {
                    thisLine = false;
                }
                k = new int[32];
                v = new int[100];
                b = true;
            }
            b = true;
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        if(temp.renameTo(f)) {
            System.out.println("renamed");
         } else {
            System.out.println("Error");
         }
         temp.delete();
    }
}
