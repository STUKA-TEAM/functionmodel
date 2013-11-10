package recommend;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tools.Constant;
import tools.HttpUtil;

/**
 * @Title: Recommend
 * @Description: Recommend the nearest store of the given location
 * @Company: ZhongHe
 * @author dai
 * @date 2013年11月7日
 */
public class Recommend {
    private static String mRadius = "2000"; // 查找半径
    private String mUserLocation = null; // 用户坐标
    private String mStoreName = null; // 目标店铺
    private String mReqUrl = "http://api.map.baidu.com/place/v2/search";
    private String mParam = null;

    public Recommend(String userLocaion, String storeName) {
        mUserLocation = userLocaion;
        mStoreName = storeName;
        initReqUrl();
    }

    private void initReqUrl() {
        mParam = "?ak=" + Constant.gLbsAk + "&output=json&query=" + mStoreName
                + "&page_size=10&page_num=0&scope=1&location=" + mUserLocation
                + "&radius=" + mRadius;
    }

    public List<Store> getStoreList() {
        List<Store> storeList = null;
        String lbsJson = HttpUtil.doGet(mReqUrl, mParam, "utf-8", false);
        storeList = ParseJson(lbsJson);
        return storeList;
    }

    private List<Store> ParseJson(String json) {
        List<Store> storeList = new ArrayList<Store>();
        Gson gson = new Gson();
        LbsResponse lbsRes = gson.fromJson(json, new TypeToken<LbsResponse>() {
        }.getType());
        Store store = null;
        if (lbsRes.getStatus() == 0) {
            json = gson.toJson(lbsRes.getResults());
            store = gson.fromJson(json, new TypeToken<Store>() {
            }.getType());
            storeList.add(store);
        }
        return storeList;
    }
}
