package com.example.lenovo.doubanapplication.listactivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.doubanapplication.adapter.BooksAdapter;
import com.example.lenovo.doubanapplication.databinding.ItemSearchBinding;
import com.example.lenovo.doubanapplication.tool.BaseActivity;
import com.example.lenovo.doubanapplication.detailactivity.BookDetailActivity;
import com.example.lenovo.doubanapplication.tool.HttpUtil;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.search.SearchBook;
import com.example.lenovo.doubanapplication.databinding.ActivityListBinding;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class BooklistActivity extends BaseActivity {
    private List<SearchBook.Books> bookList = new ArrayList<>();
    private ActivityListBinding mbinding;
    private BooksAdapter adapter = new BooksAdapter(bookList);
    private boolean isLoadable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        mbinding.listLayout.setBackgroundColor(getResources().getColor(R.color.doubanbookbackground));
        LinearLayoutManager layoutManager = new LinearLayoutManager(BooklistActivity.this);
        mbinding.recyclerView.setLayoutManager(layoutManager);
        mbinding.recyclerView.setAdapter(adapter);

        //接下来设置XRecyclerview特有的相关属性
        mbinding.recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mbinding.recyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mbinding.recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        View header = LayoutInflater.from(this).inflate(R.layout.item_header, (ViewGroup)findViewById(android.R.id.content),false);
        mbinding.recyclerView.addHeaderView(header);
        mbinding.recyclerView.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);
        mbinding.recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bookList.clear();
                        init();
                        adapter.notifyDataSetChanged();
                        mbinding.recyclerView.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        init();
                        adapter.notifyDataSetChanged();
                        if (!isLoadable) {
                            Toast.makeText(BooklistActivity.this, "无法加载更多", Toast.LENGTH_SHORT).show();
                        }
                        mbinding.recyclerView.loadMoreComplete();
                    }
                }, 1000);
            }
        });
        init();
    }

    private void init() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        HttpUtil.sendOKHttpRequest("https://api.douban.com/v2/book/search?count=20&start=" + String.valueOf(bookList.size()) + "&fields=id,title,images&q=" + data, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                SearchBook searchBook = gson.fromJson(responseData, SearchBook.class);
                if (searchBook.getBooks().size() == 0) {
                    isLoadable = false;
                }
                bookList.addAll(searchBook.getBooks());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        if (adapter.getItemCount() == 0) {
                            mbinding.textNullview.setVisibility(View.VISIBLE);
                            //mbinding.recyclerView.setVisibility(View.GONE);
                        } else {
                            adapter.setOnItemClickListener(new BooksAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if (position < bookList.size()) {
                                        Toast.makeText(BooklistActivity.this, "你点击了《" + bookList.get(position).getTitle() + "》", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(BooklistActivity.this, BookDetailActivity.class);
                                        intent.putExtra("bookid_data", bookList.get(position).getId());
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
                Toast.makeText(BooklistActivity.this, "书籍搜索出现错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
