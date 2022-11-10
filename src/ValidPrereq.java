package prereqchecker;
import java.util.*;


public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
	    StdIn.setFile(args[0]);
        int n = Integer.parseInt(StdIn.readLine());
        ArrayList<ArrayList<String>> adjlist = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < n ; i++){
            ArrayList<String> arraylist = new ArrayList<String>();
            arraylist.add(StdIn.readLine());
            adjlist.add(arraylist);
        }
        n = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < n; i++){
            adjlist.get(find(StdIn.readString(),adjlist)).add(StdIn.readString());
        }

        StdIn.setFile(args[1]);
        String ogclass =  StdIn.readLine();
        String addclass =  StdIn.readLine();
        adjlist.get(find(ogclass,adjlist)).add(addclass);

        StdOut.setFile(args[2]);
        if(check(adjlist) == true){
            StdOut.print("YES");
        }
        else{
            StdOut.print("NO");
        }
    }
    
    private static boolean check(ArrayList<ArrayList<String>> adjlist){
        Set<String> newset = new HashSet<>();
        boolean valid = true;
        for(int i = 0; i < adjlist.size(); i++){
            newset.add(adjlist.get(i).get(0));
            int f = 1;
            while(f < adjlist.get(i).size()){
                valid = Helper(adjlist, newset, adjlist.get(i).get(f));
                if(valid == false){
                    return false;
                }
                f++; 
            }
            newset.remove(adjlist.get(i).get(0));
        }
        return true;
    }
    
    private static boolean Helper(ArrayList<ArrayList<String>> adj, Set<String> set, String curr){
       if(set.contains(curr)){
           return false;
       }
       set.add(curr);
       boolean valid = true;
       int i = 1;
       while(i < adj.get(find(curr, adj)).size()){
         valid = Helper(adj,set,adj.get(find(curr, adj)).get(i));
         if(valid == false){
            return false;
         }
         i++;
       }
       set.remove(curr);
       return true;
    }

    private static int find(String course, ArrayList<ArrayList<String>> adj){
        for(int i = 0; i < adj.size(); i++){
            if(adj.get(i).get(0).equals(course)){
                return i;
            }
        }
        return -1;
    }
}