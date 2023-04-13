import java.util.Scanner;

class Main {

    private static TuringMachine tm = new TuringMachine();

    public static void main(String[] args){
        readTuringMachineInfo();
        tm.emulate();
        tm.print();
    }

    private static void readTuringMachineInfo(){
        try (Scanner scnr = new Scanner(System.in)) {
            System.out.print("How many states does this turing machine have? Don't include start, accept, or reject state: ");
            tm.n = scnr.nextInt()+3;
            System.out.println();

            System.out.print("Other than blank, how many symbols does this turing machine accept?: ");
            tm.m = scnr.nextInt()+1;
            System.out.println();

            tm.nextState = new int[tm.n-2][tm.m];
            for (int i = 0; i < tm.n-2; i++){
                for (int j = 0; j < tm.m; j++){
                    System.out.print("While in state "+i+", symbol "+j+" should change the state to: ");
                    tm.nextState[i][j] = scnr.nextInt();
                    System.out.println();
                }
            }

            tm.newSymbol = new int[tm.n-2][tm.m];
            for (int i = 1; i < tm.n-2; i++){
                for (int j = 0; j < tm.m; j++){
                    System.out.print("While in state "+i+", symbol "+j+" should be replaced with symbol: ");
                    tm.newSymbol[i][j] = scnr.nextInt();
                    System.out.println();
                }
            }

            tm.lr = new char[tm.n-2][tm.m];
            for (int i = 0; i < tm.n-2; i++){
                for (int j = 0; j < tm.m; j++){
                    System.out.print("While in state "+i+", symbol "+j+" should move the head ('|', '@', or '_'): ");
                    tm.lr[i][j] = scnr.next().charAt(0);
                    System.out.println();
                }
            }

            System.out.print("Enter the tape's contents (int only): ");
            String contents = scnr.next();
            System.out.println();

            tm.tapeLength = contents.length();
            for (int i = 0; i < tm.tapeLength; i++)
                tm.tape[i+1] = Character.getNumericValue(contents.charAt(i));
        }
    }
}