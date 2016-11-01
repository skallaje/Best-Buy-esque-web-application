import java.util.concurrent.CopyOnWriteArrayList;
import java.util.*;

public class ItemsList{
   public static List<ItemOrder> it = new CopyOnWriteArrayList<ItemOrder>();
   public static int finalCost = 0;
   
   public static void clear(){
	   it.clear();
   }
   
   public static void updateList(Item i){
	   it.add(new ItemOrder(i));
   }
   
   public static void deleteItem(String s){
	   for(ItemOrder io: ItemsList.it){
			if(io.item.getItemID().toUpperCase().equals(s))
				it.remove(io);
		}
   }
   
   public static int getOrderCost(){
	   for(ItemOrder io: ItemsList.it){
			finalCost+=io.item.getCost();
		}
		return finalCost;
   }
}