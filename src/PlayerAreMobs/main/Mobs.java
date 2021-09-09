package PlayerAreMobs.main;

import java.util.ArrayList;

public class Mobs {

    public ArrayList<String> mobs = new ArrayList<String>();


    public void main(){
        this.mobs.add("skeleton");
        this.mobs.add("zombie");
    }
    public boolean exists(String mob){
        return mobs.contains(mob);
    }
    public String getClassesString(){
        return mobs.toString();
    }

}
