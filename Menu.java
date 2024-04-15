package package1;

import java.util.ArrayDeque;

public class Menu {
    static ArrayDeque<MenuItem> menuItems=new ArrayDeque<>();
    static int flag=0;
    Menu()
    {
        if(flag==0){
        MenuItem burger = new MenuItem(1, "Burger", "Veggies with creamy cheese", 149);
        MenuItem pizza = new MenuItem(2, "Pizza", "Vegetables with extra cheese", 200);
        MenuItem salad = new MenuItem(3, "Salad", "Fresh tomatoes and cucumbers covered with dressing", 100);
        MenuItem vadapav = new MenuItem(4, "vadapav", "Mumbai Special", 35);
        MenuItem frankie = new MenuItem(5, "Frankie", "Filled With Yummy Vegetable And Cheese", 120);
        menuItems.add(burger);
        menuItems.add(pizza);
        menuItems.add(salad);
        menuItems.add(vadapav);
        menuItems.add(frankie);
        flag++;
    }
    }
    void addItem(MenuItem item)
    {
        menuItems.add(item);
    }
    public ArrayDeque<MenuItem> getMenuItems()
    {
        return menuItems;
    }
    public static void display()
    {
        Menu displayMenuItems=new Menu();
        System.out.println("Available Menu...");
        for(MenuItem item:Menu.menuItems)
        {
            System.out.println(item.getId()+","+item.getName()+"("+item.getDescription()+")-Rupees"+item.getPrice());
        }
    }

}
 class MenuItem {
    int id;
    String name,description;
    double price;
    MenuItem(int id,String name,String description,double price)
    {
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public double getPrice()
    {
        return price;
    }
}

