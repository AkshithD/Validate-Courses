package prereqchecker;
import java.util.*;


public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }
        
	    StdIn.setFile(args[0]);
        int classes = Integer.parseInt(StdIn.readLine());
        ArrayList<ArrayList<String>> adjlist = new ArrayList<ArrayList<String>>();

        for(int i = 0; i < classes ; i++){
            ArrayList<String> arraylist = new ArrayList<String>();
            arraylist.add(StdIn.readLine());
            adjlist.add(arraylist);
        }

        int changes = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < changes; i++){
            adjlist.get(find(StdIn.readString(),adjlist)).add(StdIn.readString());
        }

        StdOut.setFile(args[1]);
        for(int i = 0; i < adjlist.size(); i++){
            ArrayList<String> temp = adjlist.get(i);
            for(int j = 0; j < temp.size(); j++){
                StdOut.print(temp.get(j) + " ");
            }
            StdOut.println();
        }
        
    }

    private static int find(String course, ArrayList<ArrayList<String>> adjlist){
        for(int i = 0; i < adjlist.size(); i++){
            if(adjlist.get(i).get(0).equals(course)){
                return i;
            }
        }
        return -1;
    }
}