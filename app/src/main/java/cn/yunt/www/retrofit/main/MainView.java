package cn.yunt.www.retrofit.main;

public interface MainView extends BaseView {
    void getDataSuccess(MainModel model);

    void getDataFaliure(String msg);
}
