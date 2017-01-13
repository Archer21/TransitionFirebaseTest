package com.archer.transitionfirebasetest.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.adapter.PostAdapter;
import com.archer.transitionfirebasetest.common.BaseActivity;
import com.archer.transitionfirebasetest.common.BasePresenter;
import com.archer.transitionfirebasetest.domain.Post;
import com.archer.transitionfirebasetest.util.Helpers;
import com.archer.transitionfirebasetest.util.ItemOffsetDecorator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.posts_recycler_view)
    RecyclerView list;

    private PostAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostAdapter(MainActivity.this, R.layout.item_post_card);
    }

    @Override
    public void onStart() {
        super.onStart();
        setupList();
        setDummieContent();
    }

    public void setupList () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.addItemDecoration(new ItemOffsetDecorator(getBaseContext(), R.integer.post_list_offset));
        list.setAdapter(adapter);
    }

    public void setDummieContent () {
        List<Post> posts = new ArrayList<>();
        Post post1 = new Post();
        post1.setAvatar("http://i868.photobucket.com/albums/ab244/rikikai/horo01.png");
        post1.setUsername("Horo");
        post1.setContent("I love Spice and Wolf");
        post1.setUrlImage("http://www.1999.co.jp/itbig07/10070972a.jpg");

        Post post2 = new Post();
        post2.setAvatar("http://is1.mzstatic.com/image/thumb/Purple71/v4/59/27/dc/5927dc30-0950-56f0-1785-c3c3db4e51dc/source/100x100bb.jpg");
        post2.setUsername("Nishikino Maki");
        post2.setContent("Maki best waifu");
        post2.setUrlImage("http://i.ebayimg.com/images/g/FjcAAOSwM0FXHya9/s-l640.jpg");

        Post post3 = new Post();
        post3.setAvatar("http://images6.fanpop.com/image/photos/34900000/kirito-kirito-sword-art-online-34958960-100-100.png");
        post3.setUsername("Kirigaya Kazuto");
        post3.setContent("A post with no image.");

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post3);
        posts.add(post2);
        posts.add(post1);
        posts.add(post2);
        posts.add(post1);
        posts.add(post3);

        adapter.addAll(posts);
    }

    public void goContainerLayout (View v) {
        Helpers.navigate(MainActivity.this, ContainerActivity.class);
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
}

































