
public class LinkedList {
   Widgets head;//head of the list
   
   //this function creates the linked list as well as adds a new node onto the list when a purchase is made
   public void create(int items, double price) {
      Widgets node = new Widgets(items, price);
      node.next = null;
      //sets the first node to be the head of the list if there isn't one already
      if(head == null) {
         head = node;
         System.out.println(node.getStock()+" Widgets received at $"+node.getCost()+"\n");
      }
      //traverses the list until it gets to the end to add the new node
      else {
         Widgets current = head;
         while(current.next!=null) {
            current = current.next;
         }
         current.next = node;
         //outputs that the node has been added to the queue
         System.out.println(current.next.getStock()+" Widgets received at $"+current.next.getCost()+"\n");
      } 
   }
   /*this function clears out the nodes with empty stocks*/
   public void clean() {
      Widgets current = head;
      Widgets trail = head;
      while(current!=null) {
         //if the node to the deleted is the first in the list, which it will be in a queue
         if(current.getStock()==0) {
            if(current == head) {
               head = current.next;
               trail = head;
            }
          //empty node isn't in head, so a trailing pointer attaches the previous node to the one the deleted node was pointing to
          //common possible if two nodes back to back were depleted in one order
            else{
               trail.next = current.next;
               trail = current.next;
            }
         }
         current = current.next;//goes to next node
       
      }
   }
   public void print() {//test print function
      Widgets current = head;
      while(current!=null) {
         System.out.println(current.getStock()+" at $"+current.getCost());
         current = current.next;
      }
   }
   public double tot = 0;//used as argument in main program for these two getHead functions
   
   /*these two functions pull the purchase, sale, and discount information from the main program
    * and pass them into the node functions that process the information
    */
   public void getHead(int SA,double total,int times) {
      
      int t = 0;
      head.InventoryCheck(SA,t);//gets the header of how many widgets were bought
      head.widgetsSold(SA,total,times);//does the calculation on how many widgets were bought in each price range 
   }
   //overloaded function of the one above with the discount rate
   public void getHead(int SA,double total,int times,double DR) {
      
      int t = 0;
      head.InventoryCheck(SA,t);
      head.widgetsSold(SA,total,times,DR);    
   }

}
