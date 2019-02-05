import java.util.*;

public class MyMountainFactory extends MountainFactory {


   @Override
	public MountainCave getMountainTop() {
      // maxNodes and maxChildren could be made as inputs to the getMountainTop function to allow for the user to modify these maximum parameters
      int maxNodes = 30;
      int maxChildren = 5;
   
      MountainCave root = new MountainCave("Mountain Top","The air density here seems to indicate you're not far from the base of the mountain");
      
      // Max size is 30, node numbers go from 1 to 30.  (The root node is node 1), min size is 15 because the trinaryMountainFactory has 14 caves
      // Per the instructions, MyMountainFactory must generate a larger tree than the provided factories, trinaryMountainFactory had the largest provided tree
      int numCaves = (int)(Math.random() * (maxNodes - 15)) + 15;
      
      // assignes the index in which the cave with the scales will be created, can't be created on the mountain top for this MountainFactory
      int scalesIndex = (int)(Math.random() * (numCaves - 2)) + 2;
      
      // the root node is considered the 1st node
      Queue<MountainCave> nextRootQueue = new ArrayDeque<>();
      nextRootQueue.add(root);
      
      int currentNode = 2;
      while (currentNode <= numCaves && !nextRootQueue.isEmpty()) {
         MountainCave currentRoot = nextRootQueue.remove();
         
         // the number of children is determined randomly for each parent node up to a maximum number of children
         int numChildren = (int)(Math.random() * maxChildren);
         
         // Ensures tree does not die out before the scales are placed
         if (numChildren == 0 && nextRootQueue.isEmpty() && currentNode < scalesIndex) {
            numChildren += 1;
         }
         
         int childrenCount = 0;
         while (numChildren - childrenCount > 0) {
            if (currentNode == scalesIndex) {
               MountainCave treasureRoom = new MountainCave(currentRoot,"Treasure Room", "The golden scales are here!");
               treasureRoom.setHasScales(true);
               currentRoot.setAdjacentToScales(true);
               nextRootQueue.add(treasureRoom);
            } else {
               String room = "Cave node #" + currentNode;
               String message = "This cave has a huge #" + currentNode + " statue in it";
               MountainCave newRoom = new MountainCave(currentRoot, room, message);
               nextRootQueue.add(newRoom);
            }
            childrenCount++;
            currentNode++;
         }
      }
   
      MountainCave root2 = new MountainCave("Mountain Top","The air density here seems to indicate you're not far from the base of the mountain");
      MountainCave r3 = new MountainCave(root2, "Fox Den", "This cave has huge rocks covered in multi-leaf clovers");	
      System.out.println(root2.getChildren().toString());
      
      return root;
   }

}