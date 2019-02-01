package com.example.imageloader.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.eebbk.bfc.imageload.ImageLoader;
import com.eebbk.bfc.imageload.config.ImageLoadConfig;
import com.eebbk.bfc.imageload.toolbox.ImageLoadCallBack;
import com.eebbk.bfc.imageload.tools.L;
import com.example.imageloader.R;
import com.example.imageloader.support.Data;
import com.example.imageloader.support.TestConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class SampleGridViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<String> urls = new ArrayList<String>();

    public SampleGridViewAdapter(Context context) {
        this.context = context;

        // Ensure we get a different ordering of images on each run.
        Collections.addAll(urls, TestConfig.getNetResource());
        Collections.shuffle(urls);

        // Triple up the list.
//        ArrayList<String> copy = new ArrayList<String>(urls);
//        urls.addAll(copy);
//        urls.addAll(copy);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_image_grid_view, null);
        }
        ImageView view = (ImageView) convertView.findViewById(R.id.grid_item_iv);

        // Get the image URL for the current position.
        String url = getItem(position);
        L.d("getView: " + url);
        // Trigger the download of the URL asynchronously into the image view.
//        Picasso.with(context).setIndicatorsEnabled(true); //
//        Picasso.with(context).load(url) //
//                .placeholder(R.drawable.placeholder) //
//                .error(R.drawable.error) //
//                .fit() //
//                .tag(context) //
//                .into(view);

        ImageLoadConfig conf = new ImageLoadConfig.Builder()
                .setPlaceHolderResId(R.drawable.placeholder)
                .setErrorResId(R.drawable.error).enableFade(true).build();
        ImageLoader.getInstance().

                load(context, url, conf, new ImageLoadCallBack() {
                            @Override
                            public void onSuccess(Object loadSource, boolean isFromMemoryCache, boolean isFirstResource) {
                                L.d("onSuccess: " + loadSource);
                            }

                            @Override
                            public void onError(Exception e, Object loadSource) {
                                L.e("onError: " + e);
                            }
                        }

                ).

                into(view);


//        view.setImageURI(url);
//        Glide.with(context).load(url).error(R.drawable.error).placeholder(R.drawable.placeholder).into(view);
        return convertView;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public String getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
