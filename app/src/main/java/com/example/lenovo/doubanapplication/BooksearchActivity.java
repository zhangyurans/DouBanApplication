package com.example.lenovo.doubanapplication;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BooksearchActivity extends BaseActivity {

    private EditText editText;
    CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button search_button=(Button)findViewById(R.id.Search_button);
        editText=(EditText)findViewById(R.id.Input_text);
        editText.setHint("请输入所需查找的图书名");
        container=(CoordinatorLayout)findViewById(R.id.container);
        search_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String inputText=editText.getText().toString();
                if(inputText.equals("")){
                    Toast.makeText(BooksearchActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(BooksearchActivity.this,"正在查找"+inputText,Toast.LENGTH_SHORT).show();
                   Snackbar.make(container,"你确定要查找"+inputText+"?",Snackbar.LENGTH_INDEFINITE).setAction("确定",new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(BooksearchActivity.this,"正在搜索",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(BooksearchActivity.this,BooklistActivity.class);
                            intent.putExtra("extra_data",inputText.toString());
                            startActivity(intent);
                        }
                   }).show();
                    //Intent intent=new Intent(BooksearchActivity.this,BooklistActivity.class);
                   // intent.putExtra("extra_data",inputText.toString());
                   // startActivity(intent);
                }
            }
        });
    }
}
