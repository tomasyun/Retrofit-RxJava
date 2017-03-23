package cn.yunt.www.retrofit.main;

import cn.yunt.www.retrofit.http.ApiCallBack;

public class MainPresenter extends BasePresenter<MainView> {
    public MainPresenter(MainView view) {
        attachView(view);
    }


    public void loadDataByRetrofitRxjava(String cityId) {
        view.showLoading();
        addSubscription(apiStores.loadDataByRetrofitRxjava(cityId), new ApiCallBack<MainModel>() {
            @Override
            public void onSuccess(MainModel modle) {
                view.getDataSuccess(modle);
            }

            @Override
            public void onFailure(String string) {
                view.getDataFaliure(string);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

}
