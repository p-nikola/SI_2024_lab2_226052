import java.util.List;

class Item {
    String name;
    String barcode; //numerical
    int price;
    float discount;

    public Item(String name, String code, int price, float discount) {
        this.name = name;
        this.barcode = code;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBarcode(String code) {
        this.barcode = code;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}


public class SILab2 {
    public static boolean checkCart(List<Item> allItems, int payment){//1
        if (allItems == null){//2
            throw new RuntimeException("allItems list can't be null!");//3
        }

        float sum = 0;//4

        for (int i = 0; i < allItems.size(); i++){//5.1 5,2 5.3
            Item item = allItems.get(i);//6
            if (item.getName() == null || item.getName().length() == 0){ //7
                item.setName("unknown"); // 8
            }
            if (item.getBarcode() != null){//9
                String allowed = "0123456789";//10
                char chars[] = item.getBarcode().toCharArray();//10
                for (int j = 0; j < item.getBarcode().length(); j++){//11.1 11.2 11.3
                    char c = item.getBarcode().charAt(j);//12
                    if (allowed.indexOf(c) == -1){//13
                        throw new RuntimeException("Invalid character in item barcode!");//14
                    }
                }//15
                if (item.getDiscount() > 0){//16
                    sum += item.getPrice()*item.getDiscount();//17
                }
                else {//18
                    sum += item.getPrice();//19
                }
            }
            else {//20
                throw new RuntimeException("No barcode!");//21
            }
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'){//22
                sum -= 30;//23
            }
        }//24
        if (sum <= payment){//25
            return true;//26
        }
        else {//27
            return false;//28
        }
    }//29
}
