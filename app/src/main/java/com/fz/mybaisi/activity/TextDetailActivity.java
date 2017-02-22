package com.fz.mybaisi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fz.mybaisi.R;
import com.fz.mybaisi.essence.adapter.CommentAdapter;
import com.fz.mybaisi.essence.adapter.viewholder.BaseViewHolder;
import com.fz.mybaisi.essence.bean.CommentsBean;
import com.fz.mybaisi.essence.bean.TempBean;
import com.fz.mybaisi.interfaces.OnGetNetListener;
import com.fz.mybaisi.utils.GetNet;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextDetailActivity extends AppCompatActivity {


    @BindView(R.id.tv_detail_back)
    TextView tvDetailBack;
    @BindView(R.id.ib_more_right)
    ImageButton ibMoreRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommentAdapter adapter;
    //传递的数据
    private TempBean tempBean;
    /**
     * 评论的数据
     */
    private String url;
    private List<CommentsBean.NormalBean.ListBeanX> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        //获取评论页面的数据
        getUrlData();
        //设置页面数据
        setData();
    }

    //设置页面数据
    private void setData() {

        url = tempBean.getCommentsUrl();//评论地址
        //联网请求评论的数据
        getDataFromNet();
    }

    //联网请求评论的数据
    private void getDataFromNet() {
        GetNet.getNetData(url, null, new OnGetNetListener() {
            @Override
            public void onSuccess(String response) {
//                Toast.makeText(TextDetailActivity.this, "联网成功", Toast.LENGTH_SHORT).show();
                analysisData(response);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(TextDetailActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 解析数据，设置适配器
     *
     * @param response
     */
    private void analysisData(String response) {
        //解析数据
        CommentsBean commentsBean = new Gson().fromJson(response, CommentsBean.class);
//        String name = titleBean.getMenus().get(0).getSubmenus().get(0).getName();
        datas = commentsBean.getNormal().getList();
        if (datas != null && datas.size() > 0) {
            //设置适配器
            adapter = new CommentAdapter(this, datas,tempBean);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        } else {
            Toast.makeText(TextDetailActivity.this, "没有热评数据", Toast.LENGTH_SHORT).show();
        }
    }

    //获取新闻页面的地址
    private void getUrlData() {
        tempBean = (TempBean) getIntent().getSerializableExtra(BaseViewHolder.COMMENTS_PAGER);
    }

    @OnClick({R.id.tv_detail_back, R.id.ib_more_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detail_back:
                Toast.makeText(TextDetailActivity.this, "返回", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.ib_more_right:
                Toast.makeText(TextDetailActivity.this, "更多分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
