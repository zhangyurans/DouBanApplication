package com.example.lenovo.doubanapplication.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.lenovo.doubanapplication.listactivity.MovielistActivity;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.databinding.FragmentMovieBinding;


/**
 * Created by lenovo on 2017/8/18.
 */

public class MovieFragment extends Fragment {
    private FragmentMovieBinding mbinding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mbinding= DataBindingUtil.inflate(inflater,R.layout.fragment_movie,container,false);
        mbinding.SearchMoviebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String inputText=mbinding.InputMovietext.getText().toString();
                if(inputText.equals("")){
                    Toast.makeText(getActivity(),"输入不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    TSnackbar tSnackbar=TSnackbar.make(mbinding.moviecontainer,"你确定要查找"+inputText+"?", TSnackbar.LENGTH_INDEFINITE).setAction("确定",new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),"正在搜索",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getActivity(),MovielistActivity.class);
                            intent.putExtra("extra_data",inputText);
                            startActivity(intent);
                        }
                    });
                    View tSnackbarView=tSnackbar.getView();
                    TextView tSnackbarText=tSnackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                    tSnackbarText.setTextColor(Color.WHITE);
                    tSnackbar.setActionTextColor(getResources().getColor(R.color.doubanmoviebackground));
                    tSnackbar.show();
                }
            }
        });
        return mbinding.getRoot();
    }
}
