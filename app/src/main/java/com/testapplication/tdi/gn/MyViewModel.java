package com.testapplication.tdi.gn;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    private ArrayList<GitHubItem> mDataList = new ArrayList<>();
    private HttpRequesterModel mHttpRequesterModel;
    private ViewModelListener mViewModelListener = null;

    public interface ViewModelListener {
        void OnRequestViewModelFinished(ArrayList<GitHubItem> result);
    }

    public void SetViewModelListener(ViewModelListener listener) {
        mViewModelListener = listener;
        if(mDataList.size() > 0) {
            mViewModelListener.OnRequestViewModelFinished(mDataList);
        }
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
            public void OnHttpRequestFinished(ArrayList<GitHubItem> result) {
                mDataList = result;
                mViewModelListener.OnRequestViewModelFinished(result);
            }
        });
    }
}
