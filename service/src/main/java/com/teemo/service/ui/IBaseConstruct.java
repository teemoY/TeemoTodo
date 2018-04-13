package com.teemo.service.ui;


import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackActivity;
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackFragment;

public interface IBaseConstruct {


    interface IBaseView {

        IApp getApp();

    }

    interface IBaseAction<T extends IBaseView> {

        T getView();

        void registerSubscription(int key, Disposable subscription);

        void registerSubscription(Disposable subscription);

        void unRegisterSubscription(int key);

        /**
         * Binds presenter with a view when resumed. The Presenter will perform initialization here.
         *
         * @param view the view associated with this presenter
         */
        void takeView(T view);

        /**
         * Drops the reference to the view when destroyed
         */
        void dropView();
    }

    interface IBaseActivity extends IBaseView, ISwipeBackActivity, ISupportActivity {

    }


    interface IBaseFragment extends IBaseView, ISwipeBackFragment, ISupportFragment {

    }

    interface IApp {

        boolean debugable();

    }
}
