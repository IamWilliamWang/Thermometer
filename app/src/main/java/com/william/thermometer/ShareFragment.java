package com.william.thermometer;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by william on 2017/4/18.
 */
/*数据类*/
class ShareItem {

    int imageId;
    String information;

    private ShareItem() {
        super();
    }

    public ShareItem(String information,int imageId) {
        this.imageId = imageId;
        this.information = information;
    }

    public int getImageId() {
        return imageId;
    }

    public String getInformation() {
        return information;
    }
}


/**
 * Created by william on 2017/4/18.
 * 声明一个view holder来储存view对象
 */
class ShareViewHolder extends RecyclerView.ViewHolder{

    ImageView shareImageView;
    TextView shareTextView;
    //View thisView;

    public ShareViewHolder(View view) {
        super(view);
        shareImageView = (ImageView) view.findViewById(R.id.share_image);
        shareTextView = (TextView) view.findViewById(R.id.share_text);
        //thisView = view;
    }
}

/**
 * Created by william on 2017/4/18.
 */
/*RecyclerView适配器*/
class ShareAdapter extends RecyclerView.Adapter<ShareViewHolder> {

    List<ShareItem> shareItemList = new ArrayList<>();

    /*构造器*/
    public ShareAdapter(List<ShareItem> list) {
        this.shareItemList = list;
    }

    /*创建储存所有view的holder*/
    @Override
    public ShareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_share_item,parent,false);

        final ShareViewHolder holder = new ShareViewHolder(view);
        holder.shareTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ShareItem item = shareItemList.get(position);
                Snackbar.make(v,"已"+item.getInformation()+"!",Snackbar.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    /*将内容绑定、显示到页面相应位置*/
    @Override
    public void onBindViewHolder(ShareViewHolder holder, int position) {
        ShareItem item = shareItemList.get(position);//将相应的数据取出来
        holder.shareImageView.setImageResource(item.getImageId());
        holder.shareTextView.setText(item.getInformation());
    }

    @Override
    public int getItemCount() {
        return shareItemList.size();
    }
}

/**
 * Created by william on 2017/4/18.
 * 分享Fragment
 */
public class ShareFragment extends Fragment {

    private List<ShareItem> shareItemList = new ArrayList<>();

    private void initShareItemList() {
        ShareItem shareItem = new ShareItem("分享到新浪微博",R.drawable.weibo);
        shareItemList.add(shareItem);
        shareItem = new ShareItem("分享到QQ空间",R.drawable.qqzone);
        shareItemList.add(shareItem);
        shareItem = new ShareItem("分享到微信朋友",R.drawable.wechat);
        shareItemList.add(shareItem);
        shareItem = new ShareItem("分享到微信朋友圈",R.drawable.pengyouquan);
        shareItemList.add(shareItem);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_share, container, false); // Inflate the layout for this fragment

        initShareItemList();//初始化数据

        /*将布局管理器给RecyclerView*/
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.settings_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*对RecyclerView进行适配*/
        ShareAdapter shareAdapter = new ShareAdapter(this.shareItemList);
        recyclerView.setAdapter(shareAdapter);

        return root;
    }

}
