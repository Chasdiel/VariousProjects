import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;


public class SortAlgorithmsCompare {
    public static int[] table;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Comparing working length for \"Bubble sort\" and \"Quicksort\" using a random table. v.1");

//        int[] tabela = {18,28,14,2,16,3,12, 15, 2, 18};
//        System.out.println("wykonuje quicksort");
//        quicksort1(tabela, 0, tabela.length-1);
//        printTable(tabela);

        Instant timeStart, timeStop;
        Duration duration, duration2, duration3;

        //create random table
        genSortedTable(sc);
        sc.close();

        // print unsorted random table
        printTable(table);
        System.out.println("Bubble sort processing. Please wait.");

        // check actual time, run Bubble Sort algorithm, check time again and calculate the difference
        timeStart = Instant.now();
        printTable(bubbleSort(table.clone()));
        timeStop = Instant.now();
        duration = Duration.between(timeStart, timeStop);


        System.out.println("Optimized bubble sort processing. Please wait.");

        // check actual time, run Optimized Bubble Sort algorithm, check time again and calculate the difference
        timeStart = Instant.now();
        printTable(bubbleSortOptimized(table.clone()));
        timeStop = Instant.now();
        duration2 = Duration.between(timeStart, timeStop);

        System.out.println("Quicksort processing. Please wait. ");
        // check actual time, run Bubble Sort algorithm, check time again and calculate the difference
        timeStart = Instant.now();
        printTable(quicksort(table.clone(), 0, table.clone().length - 1));
        timeStop = Instant.now();
        duration3 = Duration.between(timeStart, timeStop);


        // print results
        System.out.println("Bubble sort duration: ");
        System.out.printf("%.3f", (double) duration.toMillis() / 1000);
        System.out.println("s");
        System.out.println("Optimized bubble sort duration: ");
        System.out.printf("%.3f", (double) duration2.toMillis() / 1000);
        System.out.println("s");
        System.out.println("Quicksort duration: ");
        System.out.printf("%.3f", (double) duration3.toMillis() / 1000);
        System.out.println("s");
    }


    public static int[] bubbleSort(int[] tab) {
        for (int i = 1; i < tab.length; i++) {
            for (int j = 0; j < tab.length - 1; j++) {
                if (tab[j] > tab[j + 1]) {
                    // proces zamiany
                    int temp;
                    temp = tab[j + 1];
                    tab[j + 1] = tab[j];
                    tab[j] = temp;
                }
            }
        }
        return tab;
    }


    public static int[] bubbleSortOptimized(int[] tab) {
        boolean p;
        for (int i = 1; i < tab.length; i++) {
            p = true;       //
            for (int j = 0; j < tab.length - i; j++) {  // zamiast -1 daliśmy -i, dzięki czemu zaoszczędzimy na iteracjach
                if (tab[j] > tab[j + 1]) {
                    // the process of swapping numbers
                    int temp;
                    temp = tab[j + 1];
                    tab[j + 1] = tab[j];
                    tab[j] = temp;
                    p = false;
                }
            }
            if (p) break;
        }
        return tab;
    }


    //sort increasingly
    public static int[] quicksort(int[] tab, int left, int right) {
        {
            int v = tab[(left + right) / 2]; // VALUE of the MIDDLE INDEX of the table
            int p, q, x;
            p = left;
            q = right;
            do {
                while (tab[p] < v) p++;   // while value of first index is less than value of middle index -> index++
                while (tab[q] > v) q--;   // while value of first index is greater than value of middle index -> index--
                //swap values if there is a case where numbers greater than pivot are on the left, an less than are on the right
                if (p <= q) {
                    x = tab[p];
                    tab[p] = tab[q];
                    tab[q] = x;
                    p++;
                    q--;
                }
            } while (p <= q);      // do until you find a pivot
            if (q > left) quicksort(tab, left, q);    //
            if (p < right) quicksort(tab, p, right);
        }
        return tab;
    }


    //additional method only for a deeper understanding
    public static int[] quicksort1(int[] tab, int left, int right) {
        {
            int v = tab[(left + right) / 2]; //average value from the table
            int p, q, x;
            p = left;
            q = right;
            for (int i : tab) {
                System.out.print(i + " ");
            }

            do {
                System.out.println("wykonuje iteracje ");
                System.out.println("-----------");
                System.out.println("v = " + v);
                while (tab[p] > v) {
                    p++;
                    System.out.println("Zwiekszam p:" + p);
                    System.out.println();
                    System.out.println("value of p now is " + tab[p]);}
                    while (tab[q] < v) {
                        q--;
                    System.out.println("Zmniejszam q:" + q);
                    System.out.println("value of q now" + tab[q]);
                    }
                    if (p >= q) {
                    System.out.println("Jesli p<=q to zamieniam wartosci dla p = " + p + "(" + tab[p]+") oraz q =" + q+ "(" + tab[q]+")");
                        x = tab[p];
                        tab[p] = tab[q];
                        tab[q] = x;
                        p++;
                        q--;
                    }
                }
                while (p >= q) ;
                {
                    for (int i : tab) {
                        System.out.print(i + " ");
                    }
                    System.out.println();

                    if (q > left) {
                    System.out.println("q: " + q + ", p: " + p + ", left: " + left);
                        quicksort(tab, left, q);

                    System.out.println("Quicksort left");
                    }
                    if (p < right) {
                    System.out.println("q: " + q + ", p: " + p + ", left: " + left);
                        quicksort(tab, p, right);
                    System.out.println("Quicksort left");
                    }
                }

            }
            return tab;
        }


        // creating random numbers to the table
        public static int[] randTable (Scanner sc){

            System.out.println("How many numbers in an array?");

            int howMany = sc.nextInt();

            table = new int[howMany];
            for (int i = 0; i < howMany; i++) {
                table[i] = (int) (Math.random() * 10000 + 1);
//            System.out.println(table[i] + " ");
            }
            return table;
        }


        public static int[] genSortedTable (Scanner sc){
            System.out.println("How many numbers in an array?");
            int howMany = sc.nextInt();
            table = new int[howMany];
            for (int i = howMany - 1; i >= 0; i--) {
                table[i] = i;
//            System.out.println(table[i] + " ");
            }
            return table;
        }


        public static void printTable ( int[] table){
            for (int i : table) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
}
