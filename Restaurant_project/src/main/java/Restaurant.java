import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime now = getCurrentTime();


               if(((now.isAfter(openingTime) && now.isBefore(closingTime)) || now.equals(openingTime))){
               return true;
               }
               return false;

        }




    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return Collections.unmodifiableList(menu);
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    public Item findItemByName(String itemName)throws itemNotFoundException {
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        throw new itemNotFoundException(itemName);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
      System.out.print("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }




    int totalAmount = 0;
    public int orderCost(String itemName)throws itemNotFoundException{
        Item menuItem = findItemByName(itemName);
        totalAmount += menuItem.getPrice();
        return totalAmount;
    }



}
