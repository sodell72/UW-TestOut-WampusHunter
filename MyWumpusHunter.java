import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class MyWumpusHunter extends WumpusHunter {

   public String getName() {
      return "Hunter mcHunt Face";
   }

   public void startAt(MountainCave root) {
   
      // prints null message to console and to action log
      if (root == null) {
         String nullMessage = "Mountain Top provided was null";
         System.out.println(nullMessage);
         actionLog += nullMessage;
         return;
      }

      Stack<MountainCave> mountainTraverseStack = new Stack<>();
      Stack<MountainCave> pathStack = new Stack<>();
      
      mountainTraverseStack.add(root);
      boolean scalesFound = false;
      while (!scalesFound && !mountainTraverseStack.isEmpty()) {
         MountainCave currentCave = mountainTraverseStack.pop();
         pathStack.push(currentCave);
         actionLog += "Entering the " + currentCave.getCaveName();
         if (currentCave.isAdjacentToScales()) {
            actionLog += " We've neared the scales!\n";
         } else {
            actionLog += "\n";
         }
         if (currentCave.hasScales()) {
            scalesFound = true;
            actionLog += "We've found the scales! ... The path is... ";
            printPath(pathStack);
            return;
         }
         
         // Adds children to stack to allow for further iteration down the tree
         // added <MountainCave> to getChildren method in MountainCave.java to remove unchecked/unsafe operation in MyWampusHunter
         List<MountainCave> children = currentCave.getChildren();
         for (MountainCave cave : children) {
            if (cave != null) {
               mountainTraverseStack.push(cave);
            }
         }

         // checks if top of path has children in the top of the traverse stack
         while (!pathStack.isEmpty() && !mountainTraverseStack.isEmpty() && !pathStack.peek().getChildren().contains(mountainTraverseStack.peek())) { 
            pathStack.pop();
         }
      }
      
   }

   // prints the path from the mountain top to the cave with the scales
   private void printPath(Stack<MountainCave> pathStack) {
      Stack<MountainCave> flipStack = new Stack<>();
      while (!pathStack.isEmpty()) {
         MountainCave currentCave = pathStack.pop();
         flipStack.push(currentCave);
      }
      actionLog += "Start at the " + flipStack.pop().getCaveName();
      while(!flipStack.isEmpty()) {
         String location = flipStack.pop().getCaveName();
         actionLog += " and\n" + "then visit the " + location;
      }
      return;
   }

}