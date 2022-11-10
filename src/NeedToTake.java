package prereqchecker;

import java.util.*;


public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

	    StdIn.setFile(args[0]);
        int n = Integer.parseInt(StdIn.readLine());
        ArrayList<ArrayList<String>> adjlist = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < n ; i++){
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(StdIn.readLine());
            adjlist.add(arrayList);
        }
        n = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < n; i++){
            adjlist.get(find(StdIn.readString(),adjlist)).add(StdIn.readString());
        }
        Set<String> set = new HashSet<>();
        StdIn.setFile(args[1]);
        String target  = StdIn.readLine();
        Set<String> targetPreReqs = new HashSet<>();
        addpre(target, targetPreReqs, adjlist);
        targetPreReqs.remove(target);
        n = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < n; i++){
            addpre(StdIn.readLine(), set, adjlist);
        }
        StdOut.setFile(args[2]);
        for (String i : targetPreReqs) {
            if(!set.contains(i)){
                StdOut.println(i);
            }
        }
    }
    private static void addpre(String course, Set<String> classes, ArrayList<ArrayList<String>> adjlist){
        if(adjlist.get(find(course,adjlist)).size() == 1){
            if(!classes.contains(course)){
              classes.add(course);
            }
        }
        else{
          if(!classes.contains(course)){
              classes.add(course);
            }
            for(int i = 1;i < adjlist.get(find(course,adjlist)).size(); i++){
              addpre(adjlist.get(find(course,adjlist)).get(i), classes, adjlist);
            }
        }
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