package src.lld.designpatterns.fitnesstracking;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name,email;
    private int age,userId;

    private List<Activity> activities;

    User(){
        activities = new ArrayList();
    }

    public void addActivity(Activity activity){
        this.activities.add(activity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
