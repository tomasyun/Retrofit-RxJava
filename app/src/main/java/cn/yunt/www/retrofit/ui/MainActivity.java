package cn.yunt.www.retrofit.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.yunt.www.retrofit.R;
import cn.yunt.www.retrofit.http.ApiCallBack;
import cn.yunt.www.retrofit.http.RetrofitCallBack;
import cn.yunt.www.retrofit.main.MainModel;
import cn.yunt.www.retrofit.main.MainPresenter;
import cn.yunt.www.retrofit.main.MainView;
import cn.yunt.www.retrofit.main.MvpActivity;
import retrofit2.Call;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {
    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBarAsHome("园林在线");
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @OnClick({R.id.button, R.id.button1,R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                loadDataByRetrofit();
                break;
            case R.id.button1:
                loadDataByRetrofitRxJava();
                break;
            case R.id.button2:
                presenter.loadDataByRetrofitRxjava("101310222");
                break;
        }
    }

    private void loadDataByRetrofit() {
        showProgressDialog();
        Call<MainModel> call = apiStores.loadDataByRetrofit("101190201");
        call.enqueue(new RetrofitCallBack<MainModel>() {
            @Override
            public void onSuccess(MainModel modle) {
                dataSuccess(modle);
            }

            @Override
            public void onFailure(int code, String msg) {
                toastShow(msg);
            }

            @Override
            public void onThrowable(Throwable e) {
                toastShow(e.getMessage());
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
        addCalls(call);
    }

    private void loadDataByRetrofitRxJava() {
        showProgressDialog();
        addSubscription(apiStores.loadDataByRetrofitRxjava("101220602"), new ApiCallBack<MainModel>() {
            @Override
            public void onSuccess(MainModel modle) {
                dataSuccess(modle);
            }

            @Override
            public void onFailure(String string) {
                toastShow(string);
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }


    private void dataSuccess(MainModel model) {
        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = "城市" + weatherinfo.getCity() + "\n"
                + "风向" + weatherinfo.getWD() + "\n"
                + "风级" + weatherinfo.getWS() + "\n"
                + "发布时间" + weatherinfo.getTime();
        text.setText(showData);
    }

    @Override
    public void getDataSuccess(MainModel model) {
        dataSuccess(model);
    }

    @Override
    public void getDataFaliure(String msg) {
        toastShow(msg);
    }
}
