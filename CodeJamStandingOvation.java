//File:		CodeJamStandingOvation.java
//Author:	Gary Bezet
//Date:		2016-06-21
//Desc:		Designed to solve past google codejam problem "Standing Ovation" qualification round 2015
//Problem:	https://code.google.com/codejam/contest/6224486/dashboard
//Results:	A-small-practice.in: 29ms       A-large-practice.in: 33ms

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;





public class CodeJamStandingOvation {

    //variables
    
    
    private String infileopt, outfileopt;  //infaile and outfile names
    private BufferedReader infile;  //inputfile stream
    private PrintStream outfile;  //outputfile stream
    
    int totalcases;  //total cases
    private TestCase[] testcases;  //data structure containing each case
    
    //functions
    
    //main function calles run function and nothing else
    public void run(String[] args) {
        
        long starttime = System.currentTimeMillis();  //start time
        
        //process arguments
        this.initargs(args);
        
        //open files
        this.openfiles();
        
        //load data
        this.loaddata();
        
        //print program options
        printopts();
        
        
        //solve all the cases
        long solvetime = System.currentTimeMillis();  //time of solution solving start
        for(int i =0; i< totalcases; i++) {            
            testcases[i].solve();
        }
        System.err.printf("\n%1$d cases solved in %2$dms\n", totalcases, System.currentTimeMillis() - solvetime);
        
        //output solution
        long outputtime = System.currentTimeMillis();
        for(int i=0; i<totalcases; i++) {
            outfile.printf("Case #%1$d: %2$d\n", testcases[i].casenum, testcases[i].solution);
        }
        System.err.printf("%1$d cases outputed to \"%3$s\" in %2$dms\n", totalcases, System.currentTimeMillis() - outputtime, outfileopt);
        
        
        System.err.printf("%1$d cases processed in %2$dms\n", totalcases, System.currentTimeMillis() - starttime);
    }
    
    //print program options
    private void printopts() {
        System.err.printf("Infile:\t%1$s\nOutfile:\t%2$s\nTotal Cases:\t%3$d\n\n", infileopt, outfileopt, totalcases);
    }
    
    //loat all the data from infile into testcases
    private void loaddata() {
        
        //load the number of cases
        try{
            totalcases = Integer.valueOf(this.readline());
        } catch(Exception err) {
            System.err.printf("Could not read total cases from %1$s\n%2$s\n", infileopt, err);
            System.exit(5);
        }
        
        //initialise test cases array
        testcases = new TestCase[totalcases];
        
        //read through all test cases
        for( int i=0; i < totalcases; i++ ) {
            
            String[] line = readline().split(" ");  //split Smax from the array of numbers
            
            testcases[i] = new TestCase();  //initialize testcase array
            testcases[i].casenum = i+1;
            
            //get value of Smax
            testcases[i].Smax = Integer.valueOf(line[0]);
            
            testcases[i].Sval = new int[testcases[i].Smax+1];  //initilize the array of shyness values
            
            //read each value in one at a time
            for(int c=0; c <= testcases[i].Smax; c++) {
                char Sval = line[1].charAt(c);  
                
                try {
                    testcases[i].Sval[c] = Integer.valueOf(String.valueOf(Sval));
                } catch( Exception err) {
                    System.err.printf("There was an error inputing Sval(%1$d) of case %2$d", c+1, i+1);
                    System.exit(6);
                }
                
            }
            
        }
        
        
    }
    
    //read a line from the file exit program on error
    private String readline() {
        String line = null;
        try {
            line = infile.readLine();
        } catch (Exception err) {
            System.err.printf("Unable to read line from:  %1$s\n%2$s\n", infileopt, err);
            System.exit(4);
        }
        
        return line;
    }
    
    //open the files
    private void openfiles() {
        try {
            infile = new BufferedReader(new FileReader(infileopt));
        } catch(Exception err) {
            System.err.printf("Error opening infile:  %1$s\n%2$s\n", infileopt, err);
            System.exit(2);
        }
        
        //if outfile not set set to stdio
        if(outfileopt == null) {
            outfile = System.out;
            outfileopt = "Stdout";
        } else {
            try {
                outfile = new PrintStream(new File(outfileopt));
            } catch (Exception err) {
                System.err.printf("Error opening outfile:  %1$s\n%2$s\n", outfileopt, err);
                System.exit(3);
            }
        }
    }
    
    //process arguments
    private void initargs(String[] args) {
        
        //check number of arguments
        if(args.length > 2 || args.length == 0) {
            System.err.println("Error:  This program requires 1 or 2 arguments which must be the input file and the output file.  If not output file is specified then use stdout");
            System.exit(1);
        }
        
        infileopt = args[0];
        if( args.length == 2 ) {
            outfileopt = args[1];
        }
        
    }
    
    public static void main(String[] args) {
        CodeJamStandingOvation prog = new CodeJamStandingOvation();
        prog.run(args);
    }
    
    
}
