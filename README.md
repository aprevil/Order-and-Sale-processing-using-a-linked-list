# Order and Sale processing using a linked-list
This program reads in purchase orders, sale orders, and discount offers from a list in a text file and stores the purchases in a linked list queue that processes sales orders using a FIFO policy. Orders and sales are read in at once from a list in the format provided in the orders.txt file and they are filtered based on the first character in each line. P for discount on next purchase, R for inventory added, and S for sales. All output is shown in the terminal.

# Files:
  Store.java is the main file where input is taken from a notepad and filtered into one of the three different categories.
  
  widgets.java is where the nodes are created for each new inventory addition. This file also contains the method that calculates a sale.
  
  linkedlist.java takes the widgets and adds them onto a linked list. It also repeatedly checks for empty nodes after a sale and removes the nodes from the list.
