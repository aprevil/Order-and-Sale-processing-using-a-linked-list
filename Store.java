/*Andrew Previl
 * This program reads in purchase orders, sale orders, and discount offers from a list and stores the
 * purchases in a linked list queue that processes sales orders using a FIFO policy
 */
import java.util.Scanner;
import java.io.*;
public class Store {
   public static void main(String[] args) throws IOException {
      File orders = new File("orders.txt");
      Scanner read = new Scanner(orders);
      LinkedList list = new LinkedList();
     
     
     
      double discountRate = 0;
      int discountsLeft = 0;
      while(read.hasNextLine()) {
         //reads the whole line and takes the value of the first character to determine the transaction type
         String line = read.nextLine();
         char type = line.charAt(0);
         
         if(type =='S') {//sale code
           
           int saleAmount = Integer.parseInt(line.substring(1).trim());
           
           if(discountsLeft>0) {//uses the list function that leads to the function with the discount rate
              list.getHead(saleAmount,list.tot,1,discountRate);
              discountsLeft--;
           }
           else {//uses the list function that leads to the function without the discount rate
              list.getHead(saleAmount,list.tot,1);
           }
           System.out.println();
           list.clean();//clears the list of any nodes with 0 stocks after the sale processing
              
         }
         else if(type =='R') {//purchase code
            int widgetAmount = Integer.parseInt(line.substring(1,line.indexOf('$')).trim());
            double widgetPrice = Double.parseDouble(line.substring(line.indexOf("$")+1));
            list.create(widgetAmount, widgetPrice);//creates a new node or the list if it's the first node
            
         }
         else{//discount code P
            discountRate = Double.parseDouble(line.substring(1,line.indexOf('%')))/100.0;
            discountsLeft+=2;//sets the counter in the sale if statement for the next two customers
            System.out.println("The next two customers will receive a "+discountRate*100+"% discount.\n");
         }
            
      }
   read.close();
   
   System.out.println("------Widgets in Stock-------");
   list.print();
   
   
   }
}

