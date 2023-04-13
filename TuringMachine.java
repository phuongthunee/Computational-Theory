class TuringMachine {

    int n;
    int m;
    int[][] nextState;
    int[][] newSymbol;
    char[][] lr;
    int[] tape = new int[25];
    int tapeLength;

    int currState;
    int head;

    void print(){
        System.out.println("\tCurrent state: "+currState);
        System.out.print("\t|");
        for (int s: tape) {
            if (s == 0) System.out.print("_");
            else System.out.print(s);
            System.out.print("|");
        }

        System.out.println();
        System.out.print("\t");
        if (head < 0 || head >= tape.length){
            System.out.println("Head is out of bounds!");
            return;
        }

        for(int i = 0; i < head; i++) {
            if (tape[i] > 99) System.out.print("    ");
            else if (tape[i] > 9) System.out.print("   ");
            else System.out.print("  ");
        }
        System.out.println(" ^");
    }

    void emulate(){
        while (true) {
            print();
            int oldState = currState;
            int oldHead = head;

            if (lr[oldState][tape[oldHead]] == '@') head++;
            else if (lr[oldState][tape[oldHead]] == '|') head--;

            tape[oldHead] = newSymbol[oldState][tape[oldHead]];

            currState = nextState[oldState][tape[oldHead]];

            if (currState == n-2){
                System.out.println("ACCEPTED");
                return;
            }
            if (currState == n-1){
                System.out.println("REJECTED");
                return;
            }
        }
    }
}