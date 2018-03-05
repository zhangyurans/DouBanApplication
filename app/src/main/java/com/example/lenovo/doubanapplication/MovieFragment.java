package com.example.lenovo.doubanapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;


/**
 * Created by lenovo on 2017/8/18.
 */

public class MovieFragment extends Fragment {
    private EditText editText;
    CoordinatorLayout container;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button search_button=(Button)getActivity().findViewById(R.id.Search_moviebutton);
        editText=(EditText)getActivity().findViewById(R.id.Input_movietext);
        container=(CoordinatorLayout)getActivity().findViewById(R.id.moviecontainer);
        search_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String inputText=editText.getText().toString();
                if(inputText.equals("")){
                    Toast.makeText(getActivity(),"输入不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    TSnackbar tSnackbar=TSnackbar.make(container,"你确定要查找"+inputText+"?", TSnackbar.LENGTH_INDEFINITE).setAction("确定",new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),"正在搜索",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getActivity(),MovielistActivity.class);
                            intent.putExtra("extra_data",inputText.toString());
                            startActivity(intent);
                        }
                    });
                    View tSnackbarView=tSnackbar.getView();
                    TextView tSnackbarText=(TextView)tSnackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                    tSnackbarText.setTextColor(Color.WHITE);
                    tSnackbar.setActionTextColor(getResources().getColor(R.color.doubanmoviebackground));
                    tSnackbar.show();
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_movie,container,false);
        return view;
    }
}
