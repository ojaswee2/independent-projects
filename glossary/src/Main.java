import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Glossary g = new Glossary();

        while(true)
        {
            System.out.println("ENTER YOUR CHOICE: create, get, delete, OR done");
            String choice = scanner.nextLine();
            if (choice.equals("done")) {
                break;
            }
            if (choice.equals("create")){
                    g.create();
                } else if (choice.equals("get")){
                    g.get();
                } else if (choice.equals("delete")){
                    g.delete();
                } else {
                    System.out.println("INVALID COMMAND, TRY THESE: \n    \'create\' \n    \'get\' \n    \'delete\' \n    \'done\'");
                }
        }
        scanner.close();
        g.printDataset();
        g.clear();
        System.out.println("THANK YOU");
        System.exit(1);
    }
}
