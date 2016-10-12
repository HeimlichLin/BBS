package com.example.user_pc.bbs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by User-PC on 2016/9/12.
 */
public class MainAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;

    private static class ViewHolder {
        public ImageView thumbImageView;
        public TextView titleTextView;
        public TextView descTextView;
    }

    public MainAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mJsonArray = new JSONArray();
    }

    // 輸入JSON資料
    public void updateData(JSONArray jsonArray) {
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {return mJsonArray.length(); }

    @Override
    public Object getItem(int position) {
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_main, null);
            holder = new ViewHolder();
            holder.thumbImageView = (ImageView) convertView.findViewById(R.id.img_thumb);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.text_title);
            holder.descTextView = (TextView) convertView.findViewById(R.id.text_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 取得目前這個Row的JSON資料
        JSONObject jsonObject = (JSONObject) getItem(position);

        String imageUrl = "";
        if (jsonObject.has("img_list")) {
            JSONArray img_list = jsonObject.optJSONArray("img_list");
            if (img_list.length() != 0) {
                imageUrl = img_list.optString(0);
            }
        }
        if (!imageUrl.equals("")) {
            // 使用 Picasso 來載入網路上的圖片
            // 圖片載入前先用placeholder顯示預設圖片
            Picasso.with(mContext).load(imageUrl).placeholder(R.drawable.wait).into(holder.thumbImageView);
        } else { // 沒有縮圖的話放 disp logo
            holder.thumbImageView.setImageResource(R.drawable.wait);
        }

        // 從JSON資料取得標題和摘要
        String title = "";
        String desc = "";
        if (jsonObject.has("title")) {
            title = jsonObject.optString("title");
        }
        if (jsonObject.has("desc")) {
            desc = jsonObject.optString("desc");
        }

        holder.titleTextView.setText(title);
        holder.descTextView.setText(desc);

        return convertView;

    }

}
