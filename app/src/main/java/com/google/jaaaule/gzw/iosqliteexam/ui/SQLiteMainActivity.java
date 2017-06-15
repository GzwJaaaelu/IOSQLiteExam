package com.google.jaaaule.gzw.iosqliteexam.ui;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.jaaaule.gzw.iosqliteexam.R;
import com.google.jaaaule.gzw.iosqliteexam.adapter.GoodsAdapter;
import com.google.jaaaule.gzw.iosqliteexam.db.GoodsOpenHelper;
import com.google.jaaaule.gzw.iosqliteexam.model.Goods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.jaaaule.gzw.iosqliteexam.R.id.fab;

public class SQLiteMainActivity extends AppCompatActivity {
    private RecyclerView mShowGoodsView;
    private FloatingActionButton mFab;
    private GoodsAdapter mAdapter;
    private List<Goods> mGoodsList;
    private GoodsOpenHelper mOpenHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        insertGoodsDataToDB();
        initView();
        initListener();
        queryGoods();
    }

    private void initData() {
        mGoodsList = new ArrayList<>();
        mOpenHelper = new GoodsOpenHelper(this);
        mDatabase = mOpenHelper.getWritableDatabase();
    }

    /**
     * 插入假数据
     */
    private void insertGoodsDataToDB() {
        Goods clock = new Goods(R.drawable.ic_clock + "",
                "意大利纯手工闹钟，今天不要 2333，只要 998...",
                998f, R.drawable.ic_clock + "", 3, "2017-05-19 11:11:11");
        mGoodsList.add(clock);
        Goods computer = new Goods(R.drawable.ic_computer + "",
                "2017 苹果新品，你难道不爱乔爷了么...",
                15550f, R.drawable.ic_computer + "", 18, "2017-05-19 12:12:12");
        mGoodsList.add(computer);
        Goods electronicOrgan = new Goods(R.drawable.ic_electronic_organ + "",
                "欧洲进口电子琴，殿堂级音乐体验，你还在犹豫什么...",
                8888f, R.drawable.ic_electronic_organ + "", 13, "2017-05-19 09:09:09");
        mGoodsList.add(electronicOrgan);
        Goods guitar = new Goods(R.drawable.ic_guitar + "",
                "知名大师打造的限量版吉他，本店特许销售，来体验吧...",
                2333f, R.drawable.ic_guitar + "", 5, "2017-05-19 18:18:18");
        mGoodsList.add(guitar);
        Goods typeWriter = new Goods(R.drawable.ic_typewriter + "",
                "老子他妈的编不下去了，破打字机随便买...",
                233f, R.drawable.ic_typewriter + "", 66, "2017-05-19 07:07:07");
        mGoodsList.add(typeWriter);

        mOpenHelper.insertMoreGoods(mGoodsList);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mShowGoodsView = (RecyclerView) findViewById(R.id.rl_show_goods_list);
        mShowGoodsView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GoodsAdapter(mGoodsList);
        mShowGoodsView.setAdapter(mAdapter);

        mFab = (FloatingActionButton) findViewById(fab);
    }

    private void initListener() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "冰淇淋强势插入...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                insertIceCream();
            }
        });
    }

    private void insertIceCream() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Goods iceCream = new Goods(R.drawable.ic_ice_cream + "",
                "滨江特色冰淇淋，来自滨江的味道，你值得拥有...",
                4.5f, R.drawable.ic_ice_cream + "", 99, format.format(new Date()));
        mOpenHelper.insertGoods(iceCream);
        queryGoods();
    }

    /**
     * 查询数据库中的货物
     */
    private void queryGoods() {
        mGoodsList = mOpenHelper.queryAndOrderByDECSTime();
        if (mGoodsList != null) {
            mAdapter.setGoodsList(mGoodsList);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "并没有什么可以设置的选项...", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
