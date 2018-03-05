package com.example.lenovo.doubanapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class BooklistActivity extends BaseActivity {
    private SearchBook BookList=new SearchBook();
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        linearLayout=(LinearLayout)findViewById(R.id.list_layout);
        linearLayout.setBackgroundColor(Color.rgb(246,246,241));
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
                        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle_view);
                        LinearLayoutManager layoutManager=new LinearLayoutManager(BooklistActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        BooksAdapter adapter=new BooksAdapter(BooklistActivity.this,BookList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new BooksAdapter.OnItemClickListener(){
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(BooklistActivity.this,"你点击了《"+BookList.getBooks().get(position).getTitle()+"》",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(BooklistActivity.this,BookDetailActivity.class);
                                intent.putExtra("bookid_data",BookList.getBooks().get(position).getId());
                                startActivity(intent);
                            }
                        });
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
