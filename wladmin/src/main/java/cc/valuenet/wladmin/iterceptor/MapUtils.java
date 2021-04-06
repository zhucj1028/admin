package cc.valuenet.wladmin.iterceptor;

import cc.valuenet.wladmin.util.R;
import sun.applet.Main;
import sun.rmi.transport.ObjectTable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zcj
 */
public class MapUtils extends  HashMap<String, Object> {

    // public static Map put(String key, String value){
    //     HashMap hashMap = new HashMap(0);
    //     hashMap.put(key,value);
    //     return hashMap;
    // }
    @Override
    public  Object put(String key, Object value) {
        return  super.put(key,value) ;
    }

}
