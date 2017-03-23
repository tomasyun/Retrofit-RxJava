package cn.yunt.www.retrofit.http;

import cn.yunt.www.retrofit.main.MainModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ApiStores {
        String API_SERVER_URL="http://www.weather.com.cn/";

    //加载天气
    @GET("adat/sk/{cityId}.html")
    Call<MainModel> loadDataByRetrofit(@Path("cityId") String cityId);

    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadDataByRetrofitRxjava(@Path("cityId") String cityId);

}
