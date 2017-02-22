package com.fz.mybaisi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fz.mybaisi.R;
import com.fz.mybaisi.essence.adapter.PersonAdapter;
import com.fz.mybaisi.essence.adapter.viewholder.BaseViewHolder;
import com.fz.mybaisi.essence.bean.EssenceBean;
import com.fz.mybaisi.essence.bean.TempBean;
import com.fz.mybaisi.interfaces.OnGetNetListener;
import com.fz.mybaisi.utils.ExpandLinearLayoutManager;
import com.fz.mybaisi.utils.GetNet;
import com.fz.mybaisi.view.PullZoomView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalDetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;

    private TempBean tempBean;
    private String url;
    private List<EssenceBean.ListBean> datas;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);
        ButterKnife.bind(this);

        getData();
        setData();

    }

    //设置页面数据
    private void setData() {
        //用户名
        tvName.setText(tempBean.getUserName());

        url = tempBean.getPersonalUrl();//个人中心数据
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
                Toast.makeText(PersonalDetailActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
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
        EssenceBean essenceBean = JSON.parseObject(response, EssenceBean.class);
//        String name = titleBean.getMenus().get(0).getSubmenus().get(0).getName();
        datas = essenceBean.getList();
        if (datas != null && datas.size() > 0) {
            //设置适配器
            adapter = new PersonAdapter(this, datas, tempBean);

            recyclerView.setLayoutManager(new ExpandLinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
//            recyclerView.setAdapter(new MyAdapter(datas));

            PullZoomView pzv = (PullZoomView) findViewById(R.id.pzv);

            pzv.setSensitive(2f);
            pzv.setIsZoomEnable(true);
            pzv.setIsParallax(true);
            pzv.setZoomTime(500);
            pzv.setOnScrollListener(new PullZoomView.OnScrollListener() {
                @Override
                public void onScroll(int l, int t, int oldl, int oldt) {
                    System.out.println("onScroll   t:" + t + "  oldt:" + oldt);
                }

                @Override
                public void onHeaderScroll(int currentY, int maxY) {
                    System.out.println("onHeaderScroll   currentY:" + currentY + "  maxY:" + maxY);
                    if (Math.abs(maxY - currentY) > 50) {
                        llTitle.setBackgroundColor(Color.argb(0, 0, 0, 0));
                    } else {
                        llTitle.setBackgroundColor(Color.argb(180, 230, 0, 0));
                    }
                }

                @Override
                public void onContentScroll(int l, int t, int oldl, int oldt) {
                    System.out.println("onContentScroll   t:" + t + "  oldt:" + oldt);
                }
            });
            pzv.setOnPullZoomListener(new PullZoomView.OnPullZoomListener() {
                @Override
                public void onPullZoom(int originHeight, int currentHeight) {
                    System.out.println("onPullZoom  originHeight:" + originHeight + "  currentHeight:" + currentHeight);
                }

                @Override
                public void onZoomFinish() {
                    System.out.println("onZoomFinish");
                }
            });


        } else {
            Toast.makeText(PersonalDetailActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取传递过来的数据
     */
    private void getData() {
        tempBean = (TempBean) getIntent().getSerializableExtra(BaseViewHolder.PERSONAL_PAGER);
    }

    @OnClick({R.id.tv_back, R.id.iv_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_more:
                Toast.makeText(PersonalDetailActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
                break;
        }
    }

//     class MyAdapter extends RecyclerView.Adapter<MyAdapter.SimpleViewHolder> {
//
//        private final List<EssenceBean.ListBean> datas;
//        private final LayoutInflater inflater;
////        private ArrayList<String> strings;
//
//        public MyAdapter(List<EssenceBean.ListBean> datas) {
////            strings = new ArrayList<>();
////            for (int i = 0; i < 30; i++) {
////                strings.add("条目：" + i);
////            }
//            this.datas = datas;
//            inflater = LayoutInflater.from(PersonalDetailActivity.this);
//
//        }
//
//        @Override
//        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new SimpleViewHolder(inflater.inflate(R.layout.item_radio_viewpager, parent, false));
//        }
//
//        @Override
//        public void onBindViewHolder(SimpleViewHolder holder, int position) {
//            holder.setData();
//        }
//
//        @Override
//        public int getItemCount() {
//            return datas.size();
//        }
//
//        class SimpleViewHolder extends RecyclerView.ViewHolder {
//            @BindView(R.id.tab_layout)
//            TabLayout tabLayout;
//            @BindView(R.id.viewPager)
//            ViewPager viewPager;
//            private MyViewPagerAdaper adapter;
//
//
//            public SimpleViewHolder(View inflate) {
//                super(inflate);
//                ButterKnife.bind(this, itemView);
//
//                initPager();
//            }
//
//
//            public void setData() {
//                adapter = new MyViewPagerAdaper(pagers);
//                viewPager.setAdapter(adapter);
//                //关联ViewPager
//                tabLayout.setupWithViewPager(viewPager);
//            }
//
//            private List<DetailBasePager> pagers;
//
//            private void initPager() {
//                pagers = new ArrayList<>();
//                pagers.add(new PostPager(PersonalDetailActivity.this, datas));//帖子
//                pagers.add(new SharePager(PersonalDetailActivity.this));//分享
//                pagers.add(new CommentPager(PersonalDetailActivity.this));//评论
//            }
//        }
//    }
//
//    class MyViewPagerAdaper extends PagerAdapter {
//
//        private final List<DetailBasePager> pagers;
//        private String[] titles = {"帖子", "分享", "评论"};
//
//        public MyViewPagerAdaper(List<DetailBasePager> pagers) {
//            this.pagers = pagers;
//        }
//
//        /**
//         * 显示标题
//         *
//         * @param position
//         * @return
//         */
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return titles[position];
//        }
//
//        @Override
//        public int getCount() {
//            return pagers.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//
//            DetailBasePager Pager = pagers.get(position);
//            View rootView = Pager.rootView;
//            container.addView(rootView);
//            //调用initData加载数据
//            Pager.initData();
//
//            return rootView;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//    }
}
