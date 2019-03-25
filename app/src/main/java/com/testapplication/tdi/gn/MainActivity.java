package com.testapplication.tdi.gn;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener{
    private MyViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViewModel();
        setupButtonListener();
    }

    private void initializeViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        mViewModel.SetViewModelListener(new MyViewModel.ViewModelListener() {
            @Override
            public void OnRequestViewModelFinished(ArrayList<GitHubItem> result) {
                ItemFragment listFragment = (ItemFragment) getSupportFragmentManager().findFragmentById(R.id.github_list);
                listFragment.updateList(result);
            }
        });
    }

    private void setupButtonListener() {
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestGitHubUsers(((EditText) findViewById(R.id.search_field)).getText().toString());
            }
        });
    }
    private void RequestGitHubUsers(String name) {
        mViewModel.requestData(name);
    }

    @Override
    public void onListFragmentInteraction(GitHubItem item) {
    }
}
