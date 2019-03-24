package com.testapplication.tdi.gn;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    ArrayList<GithubItem> mDataList = new ArrayList<>();
    HttpRequesterModel mHttpRequesterModel;
    private ViewModelListener mViewModelListener = null;

    public interface ViewModelListener {
        void OnRequestViewModelFinished(ArrayList<GithubItem> result);
    }

    public void SetViewModelListener(ViewModelListener listener) {
        mViewModelListener = listener;
    }

    public void requestData(String name) {
        String url = String.format("https://api.github.com/search/users?q=%s", name);
        setupRequestModelListener();
        mHttpRequesterModel.requestData(url);
    }

    private void setupRequestModelListener() {
        mHttpRequesterModel = new HttpRequesterModel();
        mHttpRequesterModel.SetModelListener(new HttpRequesterModel.ModelListener() {
            @Override
            public void OnHttpRequestFinished(ArrayList<GithubItem> result) {
                mDataList = result;
                mViewModelListener.OnRequestViewModelFinished(result);
            }
        });
    }
}
