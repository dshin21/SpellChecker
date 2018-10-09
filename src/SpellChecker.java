import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class SpellChecker {
    private static ArrayList<String> spellingMistakes = new ArrayList<>();

    public static void main( String[] args ) throws IOException {
        Scanner testDataFile = new Scanner( new File( "src\\lab4_testdata.txt" ) );
        BufferedReader wordListFile = new BufferedReader( new FileReader( "src\\lab4_wordlist.txt" ) );
        ArrayList<String> list = new ArrayList<>();
        String word;
        int DNECounter = 0;

        while ( ( word = wordListFile.readLine() ) != null )
            list.add( word );

        String[] listArray = list.toArray( new String[ 0 ] );
        //seq
        long start = System.currentTimeMillis();
        while ( testDataFile.hasNext() )
            if ( !seqSearch( testDataFile.next(), listArray ) )
                DNECounter++;
        long end = System.currentTimeMillis();
        float totalTime = (float) ( ( end - start ) * 1000.0 );

        System.out.println( "Sequential Search: " + DNECounter + " words were not found. " + totalTime + " microseconds" );
        System.out.println( "Not Found: " + spellingMistakes + "\n" );
        //seq

        spellingMistakes.clear();

        testDataFile = new Scanner( new File( "src\\lab4_testdata.txt" ) );
        wordListFile = new BufferedReader( new FileReader( "src\\lab4_wordlist.txt" ) );
        list.clear();
        DNECounter = 0;

        while ( ( word = wordListFile.readLine() ) != null )
            list.add( word );

        listArray = list.toArray( new String[ 0 ] );

        //bin
        start = System.currentTimeMillis();
        while ( testDataFile.hasNext() )
            if ( !binSearch( testDataFile.next(), listArray ) )
                DNECounter++;
        end = System.currentTimeMillis();
        totalTime = (float) ( ( end - start ) * 1000.0 );

        System.out.println( "Binary Search: " + DNECounter + " words were not found. " + totalTime + " microseconds" );
        System.out.println( "Not Found: " + spellingMistakes );
        //bin
    }

    private static boolean seqSearch( String word, String[] a ) {
        for ( String cword : a ) if ( word.equalsIgnoreCase( cword ) ) return true;
        spellingMistakes.add( word );
        return false;
    }

    private static boolean binSearch( String word, String[] a ) {
        int head = 0;
        int tail = a.length - 1;

        while ( head <= tail ) {
            int mid = ( head + tail ) / 2;
            if ( a[ mid ].compareToIgnoreCase( word ) == 0 ) return true;
            else if ( a[ mid ].compareToIgnoreCase( word ) < 0 ) head = mid + 1;
            else tail = mid - 1;
        }
        spellingMistakes.add( word );
        return false;
    }
}
