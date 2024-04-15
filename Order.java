package package1;
import java.util.*;

    
class Order{
    ArrayDeque<MenuItem> items;
    ArrayDeque<Integer> quantities;
    Order()
    {
        items=new ArrayDeque<>();
        quantities=new ArrayDeque<>();
    }
    void addItem(MenuItem item,int quantity)
    {
        items.add(item);
        quantities.add(quantity);
    }
    ArrayDeque<MenuItem> getItems()
    {
        return items;
    }
    ArrayDeque<Integer> getQuantities()
    {
        return quantities;
    }
    double calculateTotalBill()
    {
        double bill=0;
        Iterator<MenuItem> itemIterator=items.iterator();
        Iterator<Integer> quantityIterator=quantities.iterator();
        while(itemIterator.hasNext() && quantityIterator.hasNext())
        {
            MenuItem item=itemIterator.next();
            int  quantity=quantityIterator.next();
            bill+=item.getPrice()*quantity;
        }
        return bill;

    }
}
