package com.amxc.project.home.model.http;

import com.amxc.project.app.Apis;
import com.amxc.project.home.view.HomeFragment;

/**
 * Created by zhudong on 17-11-15.
 */

public class ApiUtils {

    /**
     * 获取请求url
     * @param type
     * @param page
     * @return
     */
    public static String getUrl(int type, int page) {
        StringBuilder sb=new StringBuilder();
        switch (type){
            case HomeFragment.ONE:
                sb.append(Apis.TOP_URL).append(Apis.TOP_ID);
                break;
            case HomeFragment.TWO:
                sb.append(Apis.COMMON_URL).append(Apis.NBA_ID);
                break;
            case HomeFragment.THREE:
                sb.append(Apis.COMMON_URL).append(Apis.CAR_ID);
                break;
            case HomeFragment.FOUR:
                sb.append(Apis.COMMON_URL).append(Apis.JOKE_ID);
                break;
            default:
                sb.append(Apis.TOP_URL).append(Apis.TOP_ID);
                break;
        }
        sb.append("/").append(page).append(Apis.END_URL);
        return sb.toString();
    }

    /**
     * 获取ID
     * @param type
     * @return
     */
    public static String getID(int type) {
        String id;
        switch (type) {
            case HomeFragment.ONE:
                id = Apis.TOP_ID;
                break;
            case HomeFragment.TWO:
                id = Apis.NBA_ID;
                break;
            case HomeFragment.THREE:
                id = Apis.CAR_ID;
                break;
            case HomeFragment.FOUR:
                id = Apis.JOKE_ID;
                break;
            default:
                id = Apis.TOP_ID;
                break;
        }
        return id;
    }
}
