package cn.yunt.www.retrofit.http;

import com.wuxiaolong.androidutils.library.LogUtil;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class ApiCallBack<M> extends Subscriber<M> {

    public abstract void onSuccess(M modle);

    public abstract void onFailure(String string);

    public abstract void onFinish();

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            LogUtil.d("code=" + code);
            if (code == 504) {
                msg = "网络异常";
            } else if (code == 502 || code == 404) {
                msg = "服务器异常,请稍后重试";
            }
            onFailure(msg);

        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }
}
