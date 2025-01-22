package src.lld.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Integer,Item> aisleToProductMap = new HashMap();
    private Map<Item,Integer> productCountMap = new HashMap();
    private int aisleCount;

    public Inventory(int aisleCount){
        this.aisleCount = aisleCount;
    }

    Item getProductAt(int aisle){
        return aisleToProductMap.getOrDefault(aisle,null);
    }

    public void load(Item item, int count){
        int currentCount = productCountMap.getOrDefault(item,0);
        if(currentCount>0){
            productCountMap.put(item,productCountMap.getOrDefault(item,0)+count);
        }
        else{
            if(productCountMap.size()==aisleCount){
                System.out.println("Vending Machine is full");
            }
            else{
                for(int i=1;i<=aisleCount;i++){
                    if(!aisleToProductMap.containsKey(i)){
                        aisleToProductMap.put(i,item);
                        productCountMap.put(item,count);
                        break;
                    }
                }
                this.aisleCount++;
            }

        }
    }

    public void dispense(int aisleNum){
        if(!aisleToProductMap.containsKey(aisleNum)){
            System.out.println("Aisle empty");
            return;
        }
        Item item = aisleToProductMap.get(aisleNum);
        int currentCount = productCountMap.getOrDefault(item,0);
        if(currentCount<1){
            System.out.println("Not enough items to dispense");
            return;
        }
        productCountMap.put(item,productCountMap.getOrDefault(item,0)-1);
        System.out.println("Please collect your product : "+item.name);
        if(productCountMap.getOrDefault(item,0)==0){
            productCountMap.remove(item);
            for(int i=1;i<=aisleCount;i++){
                if(aisleToProductMap.containsKey(i)&&aisleToProductMap.get(i).equals(item)){
                    aisleToProductMap.remove(i);
                    return;
                }
            }
        }
    }

}
