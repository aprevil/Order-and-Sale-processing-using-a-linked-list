/*Andrew Previl             CISC 3130           Homework 3
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
/*
150 Widgets received at $1.0

130 Widgets received at $2.0

145 Widgets sold
145  at $1.30  each  Sales: $188.50
total sales: $188.5

50 Widgets received at $2.5

75 Widgets sold
5    at $1.30  each  Sales: $6.50  
70   at $2.60  each  Sales: $182.00
total sales: $188.5

110 Widgets sold
60   at $2.60  each  Sales: $156.00
50   at $3.25  each  Sales: $162.50
Remainder of 70 Widgets not available.
total sales: $318.5

50 Widgets received at $4.0

30 Widgets received at $5.0

40 Widgets received at $5.5

The next two customers will receive a 30.0% discount.

50 Widgets sold
50   at $5.20  each  Sales: $260.00
total sales: 260.0
total sales after discount: $182.00

30 Widgets sold
30   at $6.50  each  Sales: $195.00
total sales: 195.0
total sales after discount: $136.50

50 Widgets received at $6.0

265 Widgets received at $10.0

60 Widgets sold
40   at $7.15  each  Sales: $286.00
20   at $7.80  each  Sales: $156.00
total sales: $442.0

The next two customers will receive a 50.0% discount.

100 Widgets sold
30   at $7.80  each  Sales: $234.00
70   at $13.00 each  Sales: $910.00
total sales: 1144.0
total sales after discount: $572.00

70 Widgets sold
70   at $13.00 each  Sales: $910.00
total sales: 910.0
total sales after discount: $455.00

125 Widgets sold
125  at $13.00 each  Sales: $1625.00
Remainder of 50 Widgets not available.
total sales: $1625.0

40 Widgets received at $14.0

75 Widgets received at $15.0

110 Widgets sold
40   at $18.20 each  Sales: $728.00
70   at $19.50 each  Sales: $1365.00
total sales: $2093.0

30 Widgets received at $16.0

40 Widgets received at $18.0

------Widgets in Stock-------
5 at $15.0
30 at $16.0
40 at $18.0
*/
