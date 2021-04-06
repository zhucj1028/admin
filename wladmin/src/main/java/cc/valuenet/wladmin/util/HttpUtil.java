package cc.valuenet.wladmin.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

    public static boolean isMobileDevice(HttpServletRequest request) {
        String requestHeader = request.getHeader("user-agent").toLowerCase();
        String[] deviceArray = new String[]{"android", "iphone", "ios", "windows phone"};
        if (requestHeader == null) {
            return false;
        }
        requestHeader = requestHeader.toLowerCase();
        for (int i = 0; i < deviceArray.length; i++) {
            if (requestHeader.indexOf(deviceArray[i]) > 0) {
                return true;
            }
        }
        return false;
    }
}
