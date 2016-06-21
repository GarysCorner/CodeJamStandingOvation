//File:		TestCase.java
//Author:	Gary Bezet
//Date:		2016-06-21
//Desc:		Designed to solve past google codejam problem "Standing Ovation" qualification round 2015
//Problem:	https://code.google.com/codejam/contest/6224486/dashboard
//Results:	



public class TestCase {
    
    //variables
    public int Smax;  //maximum shyness values
    public int[] Sval;  //array of shyness values
    public int solution = 0;  //the solution initialize it since we will just be adding to it
    public int casenum; //case number
    
    //functions
    public int solve(){
        
        long solvetime =  System.currentTimeMillis(); //get start of solve time
        
        int currentstanding = 0;  //current number of people standing at level
        
        //go through all people
        for(int i = 0; i <= this.Smax; i ++) {
                   
            //if not enough people standing add some friends
            if( currentstanding < i ) {
                int needed = i - currentstanding; //number of people needed to get this level
                this.solution += needed;
                currentstanding += needed;
            }
            currentstanding += Sval[i];
            
            
        }        
        
        
        System.err.printf("Case %1$d solved in %2$dms\tAns= %3$d\n", casenum, System.currentTimeMillis() - solvetime, this.solution);
        
        return this.solution;
    }
    
}
