package com.example.lenovo.doubanapplication.listactivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.doubanapplication.adapter.BooksAdapter;
import com.example.lenovo.doubanapplication.BaseActivity;
import com.example.lenovo.doubanapplication.detailactivity.BookDetailActivity;
import com.example.lenovo.doubanapplication.HttpUtil;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.search.SearchBook;
import com.example.lenovo.doubanapplication.databinding.ActivityListBinding;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class BooklistActivity extends BaseActivity {
    private SearchBook BookList=new SearchBook();
    private ActivityListBinding mbinding;
    private  BooksAdapter adapter=new BooksAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding= DataBindingUtil.setContentView(this,R.layout.activity_list);
        mbinding.listLayout.setBackgroundColor(getResources().getColor(R.color.doubanbookbackground));
        LinearLayoutManager layoutManager=new LinearLayoutManager(BooklistActivity.this);
        mbinding.recyclerView.setLayoutManager(layoutManager);
        adapter.setBooksList(BookList);
        mbinding.recyclerView.setAdapter(adapter);

        Intent intent=getIntent();
        String data=intent.getStringExtra("extra_data");
        HttpUtil.sendOKHttpRequest("https://api.douban.com/v2/book/search?count=100&feilds=id,title,images&q="+data,new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Gson gson=new Gson();
                BookList=gson.fromJson(responseData,SearchBook.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateBooklist(BookList);
                        if(adapter.getItemCount()==0){
                            mbinding.textNullview.setVisibility(View.VISIBLE);
                            mbinding.recyclerView.setVisibility(View.GONE);
                        }
                        else {
                            adapter.setOnItemClickListener(new BooksAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if(position<BookList.getBooks().size()){
                                        Toast.makeText(BooklistActivity.this, "你点击了《" + BookList.getBooks().get(position).getTitle() + "》", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(BooklistActivity.this, BookDetailActivity.class);
                                        intent.putExtra("bookid_data", BookList.getBooks().get(position).getId());
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });

    }

}
