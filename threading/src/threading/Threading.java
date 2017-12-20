package threading;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Threading implements Runnable {

    private int start1;
    private int stop1;
    private int id1;
    private int value;
    private int sayac;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        int numL = numLines();
        System.out.println(numL);

        String kelime;

        Scanner sc = new Scanner(System.in);
        int numTh;
        System.out.println("String Giriniz  : ");
        kelime = sc.next();
        System.out.println("Thread Sayisi : ");
        numTh = sc.nextInt();

        int step = (int) Math.round((double) numL / (double) numTh);
        Threading arrTh[] = new Threading[numTh];
        int sum = 0;

        for (int i = 0; i < numTh;
                i++) {
            if (i == numTh - 1) {
                arrTh[i] = new Threading(i * step, numL - 1, i);
            } else {
                arrTh[i] = new Threading(i * step, (i + 1) * step - 1, i);
            }
            arrTh[i].run(kelime);
            sum = arrTh[i].getValue() + sum;
            System.out.println("Kelimenin Sayisi " + sum);
        }
    }

    Threading(int a, int b, int c) {
        start1 = a;
        stop1 = b;
        id1 = c;
    }

    public void run(String kelime) {
        try {
            myThreading(kelime);


        } catch (FileNotFoundException ex) {
            Logger.getLogger(Threading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int numLines() throws FileNotFoundException, IOException {
        LineNumberReader reader = new LineNumberReader(new FileReader("a.txt"));
        int cnt = 0;
        String lineRead = "";
        while ((lineRead = reader.readLine()) != null) {
        }

        cnt = reader.getLineNumber();
        reader.close();

        return cnt;
    }

    void myThreading(String kelime) throws FileNotFoundException {
        sayac = 0;
        FileInputStream fs = new FileInputStream("a.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));

        for (int i = 0; i < start1; i++) {
            try {

                br.readLine();
            } catch (IOException ex) {
            }
        }
        for (int i = start1; i <= stop1; i++) {
            try {
                String lineIWant = br.readLine();
                System.out.println(lineIWant);
                if (kelime.equals(lineIWant)) {
                    sayac++;
                }
            } catch (IOException ex) {
            }
        }
        System.out.println(" sayac " + sayac + " thread " + id1 + " baslangic " + start1 + " bitisik " + stop1);

    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getValue() {

        value = sayac;
        return value;
    }
}

