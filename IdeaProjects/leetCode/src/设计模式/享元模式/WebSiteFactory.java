package 设计模式.享元模式;

import java.util.HashMap;

public class WebSiteFactory {
    private HashMap<String, WebSite> flyweights = new HashMap<>();

    public WebSite getWebSiteCategory(String key){
        if (!flyweights.containsKey(key)){
            flyweights.put(key, new ConcreteWebSite(key));
        }
        return flyweights.get(key);
    }

    public int getWebSiteCount(){
        return flyweights.size();
    }
}
