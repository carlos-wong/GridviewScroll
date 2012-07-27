package com.carlos.gridvewscroll;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carlos.gridvewscroll.AutoLoadListener.AutoLoadCallBack;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView gridview = (GridView) findViewById(R.id.test_gridview);

		// // 生成动态数组，并且转入数据
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 30; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.ic_launcher);// 添加图像资源的ID
			map.put("ItemText", "NO." + String.valueOf(i));// 按序号做ItemText
			lstImageItem.add(map);
		}
		// // 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
		SimpleAdapter saImageItems = new SimpleAdapter(this, // 没什么解释
				lstImageItem,// 数据来源
				R.layout.night_item,// night_item的XML实现

				// 动态数组与ImageItem对应的子项
				new String[] { "ItemImage", "ItemText" },

				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.ItemImage, R.id.ItemText });
		//
		// //添加自动读页的事件
		AutoLoadListener autoLoadListener = new AutoLoadListener(callBack);
		gridview.setOnScrollListener(autoLoadListener);
		// // 添加并且显示
		gridview.setAdapter(saImageItems);
	}

	AutoLoadCallBack callBack = new AutoLoadCallBack() {

		public void execute(String url) {
			Toast.makeText(MainActivity.this, url, 500).show();
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
