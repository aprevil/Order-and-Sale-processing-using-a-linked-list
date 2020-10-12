
public class Widgets {
   private double cost;
   private int stock;
   
   //pointer to next node in the list
   Widgets next;

   
   Widgets(int stock, double cost){
      this.stock = stock;
      this.cost = cost;
   }
   public int getStock() {
      return stock;
   }
   public void setStock(int stock) {
      this.stock = stock;
   }

   public double getCost() {
      return cost;
   }
   public double getMarkup() {
      return cost*1.30;
   }
 
   
   /*This function calculates how many widgets are sold from each node and sets the stock in the node
    * to 0 if all of the widgets are used up in order to get cleared later by the clean function
    * total in the header parameter carries over the total amount through the recursive function to the next node
    * times ensures that the total sale amount only gets printed once at the end
    */
   public void widgetsSold(int orderAmt, double total,int times) {
      boolean skip = true;//skip variable for total output at the bottom
      if(orderAmt>0) {   
         //if the current stock is less than the order amount so we know if we need to check the next node
         if(stock<orderAmt) {
            orderAmt = orderAmt-stock;
            
            double sale = getMarkup()*stock;
            total+=sale;
            //System.out.println(stock +" at "+getMarkup()+" each    Sales: "+sale);
            System.out.printf("%-4d%-4s%-5.2f%-10s%-6.2f\n",stock," at $",getMarkup()," each  Sales: $",sale);
            stock = 0;
            times++;
            //goes to the next node to fill out the rest of the order
            if(next!=null) {next.widgetsSold(orderAmt,total,times);
               skip = false;
            }
            //outputs that the order can't be completed if there isn't another node after the current
            else System.out.println("Remainder of "+orderAmt +" Widgets not available.");
            
         }
         //have enough in stock in the node
         else {
            double sale =getMarkup()*orderAmt;
            total+=sale;
            stock-=orderAmt;
            //System.out.println(orderAmt +" at "+getMarkup()+" each    Sales: "+sale);
            System.out.printf("%-4d%-4s%-5.2f%-10s%-6.2f\n",orderAmt," at $",getMarkup()," each  Sales: $",sale);
            
         }
      
      }
      //since we have a recursive function, this makes sure that only the final total is printed
      if(skip) {
         System.out.println("total sales: $"+total);
      }
      else if(times==1) {
         System.out.println("total sales: $"+total);
      }
      
   }
   //overloaded function of the previous, only difference is that it accounts for a discount
   public void widgetsSold(int orderAmt, double total,int times,double DR) {
      boolean skip = true;//skip variable for total output at the bottom
      if(orderAmt>0) {   
         if(stock<orderAmt&&(stock>0)) {
            orderAmt = orderAmt-stock;
            
            double sale = getMarkup()*stock;
            total+=sale;
            
            System.out.printf("%-4d%-4s%-5.2f%-10s%-6.2f\n",stock," at $",getMarkup()," each  Sales: $",sale);
            stock = 0;
            times++;
            
            if(next!=null) {next.widgetsSold(orderAmt,total,times,DR);
               skip = false;
            }
            else System.out.println("Remainder of "+orderAmt +" Widgets not available.");
            
         }
         else if(stock == 0) {
            System.out.println("not enough to fill order");
         }
         else {//have enough in one order
            double sale =getMarkup()*orderAmt;
            total+=sale;
            stock-=orderAmt;
            //System.out.println(orderAmt +" at "+getMarkup()+" each    Sales: "+sale);
            System.out.printf("%-4d%-4s%-5.2f%-10s%-6.2f\n",orderAmt," at $",getMarkup()," each  Sales: $",sale);
            
         }
      
      }
      //since we have a recursive function, this makes sure that only the final total is printed
      double discount = total-(total*DR);
      if(skip) {    
         System.out.println("total sales: "+total);
         System.out.printf("%-10s%5.2f\n","total sales after discount: $", discount);
      }
      else if(times==1) {
         System.out.println("total sales: "+total);
         System.out.printf("%-10s%5.2f\n","total sales after discount: $", discount);
      }
      
   }
   /*this function is a copy of the widgets sold function except it tracks
    * just the total item that will be calculated for the header above the price breakdown
    * total carries the total number of widgets through the recursion call
    */
   public void InventoryCheck(int amt,int total) {
 
      if(stock<amt) {//have to subtract from current and go to next node
         amt -= stock;
         total+=stock;
         if(next!=null) next.InventoryCheck(amt, total);
         else System.out.println(total+" Widgets sold");
                     
      }
      else {//current stock has enough items in it
         total+=amt;
         System.out.println(total+" Widgets sold");
      }
   
      
      
   }

}