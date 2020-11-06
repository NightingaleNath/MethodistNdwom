package ideanity.oceans.methodistndwom.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.HashMap;
import java.util.List;

import ideanity.oceans.methodistndwom.R;
import ideanity.oceans.methodistndwom.activities.DetailCanticleActivity;
import ideanity.oceans.methodistndwom.activities.HymnDetailActivity;

public class CanticleAdapter extends Adapter<CanticleAdapter.MyViewHolder>  {

    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public List<HashMap<String, String>> canticleData;

    public class MyViewHolder extends ViewHolder implements View.OnClickListener {

        TextView hymnNo;
        TextView title;
        TextView lyrics;
        RelativeLayout cardv;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.hymnNo = (TextView) itemView.findViewById(R.id.tvId);
            this.title = (TextView) itemView.findViewById(R.id.tvHymn);
            this.lyrics = (TextView) itemView.findViewById(R.id.lyrics);
            this.cardv = (RelativeLayout) itemView.findViewById(R.id.clickLay);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            Intent i = new Intent(CanticleAdapter.this.context, DetailCanticleActivity.class);
            String str = "id";
            i.putExtra(str, (String) ((HashMap) CanticleAdapter.this.canticleData.get(getAdapterPosition())).get(str));
            String str2 = "hymn_lyrics";
            i.putExtra(str2, (String) ((HashMap) CanticleAdapter.this.canticleData.get(getAdapterPosition())).get(str2));
            String str3 = "hymn_title";
            i.putExtra(str3, (String) ((HashMap) CanticleAdapter.this.canticleData.get(getAdapterPosition())).get(str3));
            CanticleAdapter.this.context.startActivity(i);
        }

    }

    public CanticleAdapter(Context context2, List<HashMap<String, String>> canticleData2) {
        this.context = context2;
        this.canticleData = canticleData2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_canticle, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final String id = (String) ((HashMap) this.canticleData.get(position)).get("id");
        final String title = (String) ((HashMap) this.canticleData.get(position)).get("hymn_title");
        String lyrics = (String) ((HashMap) this.canticleData.get(position)).get("hymn_lyrics");

        holder.hymnNo.setText(id);
        holder.title.setText(title);
        holder.lyrics.setText(lyrics);

        holder.cardv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CanticleAdapter.this.context, DetailCanticleActivity.class);
                String str = "id";
                i.putExtra(str, (String) ((HashMap) CanticleAdapter.this.canticleData.get(position)).get(str));
                String str2 = "hymn_lyrics";
                i.putExtra(str2, (String) ((HashMap) CanticleAdapter.this.canticleData.get(position)).get(str2));
                String str3 = "hymn_title";
                i.putExtra(str3, (String) ((HashMap) CanticleAdapter.this.canticleData.get(position)).get(str3));
                CanticleAdapter.this.context.startActivity(i);
            }
        });
    }

    public int getItemCount() {
        return this.canticleData.size();
    }

}
