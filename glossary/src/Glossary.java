import java.util.Scanner;
// import java.util.Set;
// import java.util.HashSet;

public class Glossary {
    Dataset ds;

    public Glossary() {
        ds = new Dataset("glossary");
    }

    public void create() {
        Scanner reader = new Scanner(System.in);
        System.out.println("TYPE \'indiv\' FOR ONE ENTRY OR \'full\' FOR WHOLE DATASET: ");
        String choice = reader.nextLine();

        if (choice.equals("indiv")) {
            System.out.println("TYPE YOUR KEY: ");
            String key = reader.nextLine();

            System.out.println("VALUE OF " + key + ":");
            String value = reader.nextLine();
            ds.add(key,value);
            System.out.println("ADDED");
         } else {
            System.out.println("SEPARATE KEYS AND VALUES WITH \':\' AND SEPARATE ENTRIES WITH NEW LINE. TYPE \'done\' WHEN FINISHED.");
            String line;
            String[] entry = new String[4];
            int i = 0;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                Scanner lineReader = new Scanner(line);
                if (line.equals("done")) {
                    break;
                }
                lineReader.useDelimiter(":");
                while (lineReader.hasNext()) {
                    entry[i] = lineReader.next();
                    i++;
                }
                i = 0;
                ds.add(entry[0],entry[1]);
            }
            System.out.println("CREATED");
        }

        //reader.close();
    }

    public void get() {
        Scanner reader = new Scanner(System.in);
        System.out.println("KEY TO FIND: ");
        String key = reader.nextLine();
        // find key from temp_gloss first
        String value = ds.get(key);
        if (value == null) {
            System.out.println("KEY DOES NOT EXIST");
        } else {
            System.out.println("THE VALUE IS " + value);
        }
    }

    public void delete() {
        Scanner reader = new Scanner(System.in);
        System.out.println("KEY TO DELETE: ");
        String key = reader.nextLine();
        ds.delete(key);
        System.out.println("DELETED");
    }

    public void printDataset() {
        ds.printMap();
    }

    public void clear() {
        ds.clear();
    }
}
