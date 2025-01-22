package src.lld.coupon;

import java.util.HashMap;
import java.util.Map;

public abstract class Coupon {
    protected String name;
    protected boolean active;
    protected int expiration,availed;
    protected static int totalAvailable,redeemed,perUser;
    private Map<CustomerUser,Integer> counts;

    public Coupon(String name, int expiration){
        this.name = name;
        this.active = true;
        this.expiration = expiration;
        counts = new HashMap();
    }

    public Coupon(String name, int expiration, int totalAvailable, int perUser) {
        this(name,expiration);
        this.totalAvailable = totalAvailable;
        this.perUser = perUser;
    }

    public boolean isAvailable(){
        return redeemed<totalAvailable;
    }

    public boolean redeem(CustomerUser user)
    {
        if(this.isApplicable(user)&&counts.getOrDefault(user,0)<perUser){
            counts.put(user,counts.getOrDefault(user,0)+1);
            redeemed++;
            return true;
        }
        else{
            System.out.println("Couldn't redeem");
        }
        return false;
    }


    public void activate(){
        this.active = true;
    }

    public boolean isActive(){
        return this.active;
    }

    public void disable(){
        this.active = false;
    }

    protected abstract boolean isApplicable(CustomerUser user);

    protected abstract void display();
}
