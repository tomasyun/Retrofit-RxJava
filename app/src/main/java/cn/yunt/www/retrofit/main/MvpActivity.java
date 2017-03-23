package cn.yunt.www.retrofit.main;


import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.yunt.www.retrofit.ui.BaseActivity;

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }
}
