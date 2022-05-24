package com.chaddevz.sockshttp.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONObject;
import com.chadx.sockshttp.R;
import android.graphics.Color;
import android.view.animation.*;
import com.chaddevz.sockshttp.SocksHttpApp;
import android.widget.LinearLayout;

public class SpinnerAdapter extends ArrayAdapter<JSONObject> {

    private int spinner_id;

    public SpinnerAdapter(Context context, int spinner_id, ArrayList<JSONObject> list) {
        super(context, R.layout.spinner_item, list);
        this.spinner_id = spinner_id;
    }

    @Override
    public JSONObject getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return view(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return view(position, convertView, parent);
    }

    private View view(int position, View convertView, ViewGroup parent) {
		Animation anim = AnimationUtils.loadAnimation(SocksHttpApp.getApp(), R.drawable.pop_in);
        View v = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        TextView tv = v.findViewById(R.id.itemName);
        TextView info = v.findViewById(R.id.info);
        ImageView im = v.findViewById(R.id.itemImage);
		TextView extra = v.findViewById(R.id.textExtra);
		LinearLayout spinner_item_layout = v.findViewById(R.id.spinner_item_layout);
        try {

            String name = getItem(position).getString("Name");
            tv. setText(name);

            if (spinner_id == R.id.serverSpinner) {
                getServerIcon(position, im, info);
				info.setText(getItem(position).getString("sInfo"));
				String qwerty = getItem(position).getString("sInfo").toLowerCase();
				if (qwerty.contains("")) {
                    extra.setText("FREE");
                } else if(qwerty.contains("ssl")){
					extra.setText("SSL/TLS");
				} else if(qwerty.contains("ssh")){
					extra.setText("SSH");
				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		spinner_item_layout.startAnimation(anim);
        return v;
    }

    private void getServerIcon(int position, ImageView im, TextView info) throws Exception {
        InputStream inputStream = getContext().getAssets().open("flags/" + getItem(position).getString("FLAG"));
        im.setImageDrawable(Drawable.createFromStream(inputStream, getItem(position).getString("FLAG")));
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
