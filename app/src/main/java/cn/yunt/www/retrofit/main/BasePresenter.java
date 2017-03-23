package cn.yunt.www.retrofit.main;

import cn.yunt.www.retrofit.http.ApiStores;
import cn.yunt.www.retrofit.http.AppClient;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<V> {
    public V view;
    protected ApiStores apiStores;
    private CompositeSubscription compositeSubscription;

    public void attachView(V view) {
        this.view = view;
        apiStores = AppClient.retrofit().create(ApiStores.class);
    }

    public void detachView() {
        this.view = null;
        onUnsubscribe();

    }


    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
