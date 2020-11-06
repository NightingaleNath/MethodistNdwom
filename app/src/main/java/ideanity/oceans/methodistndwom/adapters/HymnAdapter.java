package ideanity.oceans.methodistndwom.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.HashMap;
import java.util.List;

import ideanity.oceans.methodistndwom.R;
import ideanity.oceans.methodistndwom.activities.HymnDetailActivity;

public class HymnAdapter extends Adapter<HymnAdapter.MyViewHolder>  {

    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public List<HashMap<String, String>> hymnData;

    public class MyViewHolder extends ViewHolder implements View.OnClickListener {

        TextView hymnNo;
        TextView title;
        TextView stansaz;
        TextView content;
        TextView author;
        RelativeLayout cardv;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.hymnNo = (TextView) itemView.findViewById(R.id.hymnNo);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.stansaz = (TextView) itemView.findViewById(R.id.stansaz);
            this.content = (TextView) itemView.findViewById(R.id.content);
            this.author = (TextView) itemView.findViewById(R.id.author);
            this.cardv = (RelativeLayout) itemView.findViewById(R.id.clickLay);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            Intent i = new Intent(HymnAdapter.this.context, HymnDetailActivity.class);
            String str = "id";
            i.putExtra(str, (String) ((HashMap) HymnAdapter.this.hymnData.get(getAdapterPosition())).get(str));
            String str2 = "title";
            i.putExtra(str2, (String) ((HashMap) HymnAdapter.this.hymnData.get(getAdapterPosition())).get(str2));
            String str3 = "stanzas";
            i.putExtra(str3, (String) ((HashMap) HymnAdapter.this.hymnData.get(getAdapterPosition())).get(str3));
            String str4 = "content";
            i.putExtra(str4, (String) ((HashMap) HymnAdapter.this.hymnData.get(getAdapterPosition())).get(str4));
            String str5 = "info";
            i.putExtra(str5, (String) ((HashMap) HymnAdapter.this.hymnData.get(getAdapterPosition())).get(str5));
            HymnAdapter.this.context.startActivity(i);
        }

    }

    public HymnAdapter(Context context2, List<HashMap<String, String>> hymnData2) {
        this.context = context2;
        this.hymnData = hymnData2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hymn, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final String id = (String) ((HashMap) this.hymnData.get(position)).get("id");
        final String title = (String) ((HashMap) this.hymnData.get(position)).get("title");
        String stansa = (String) ((HashMap) this.hymnData.get(position)).get("stanzas");
        String contents = (String) ((HashMap) this.hymnData.get(position)).get("hymn");
        String authors = (String) ((HashMap) this.hymnData.get(position)).get("author");

        holder.hymnNo.setText("MHB\n" + id);
        holder.title.setText(title);
        holder.stansaz.setText(stansa);
        holder.content.setText(contents);
        holder.author.setText(authors);

        holder.cardv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HymnAdapter.this.context, HymnDetailActivity.class);
                String str = "id";
                i.putExtra(str, (String) ((HashMap) HymnAdapter.this.hymnData.get(position)).get(str));
                String str2 = "title";
                i.putExtra(str2, (String) ((HashMap) HymnAdapter.this.hymnData.get(position)).get(str2));
                String str3 = "stanzas";
                i.putExtra(str3, (String) ((HashMap) HymnAdapter.this.hymnData.get(position)).get(str3));
                String str4 = "content";
                i.putExtra(str4, (String) ((HashMap) HymnAdapter.this.hymnData.get(position)).get(str4));
                String str5 = "info";
                i.putExtra(str5, (String) ((HashMap) HymnAdapter.this.hymnData.get(position)).get(str5));
                HymnAdapter.this.context.startActivity(i);
            }
        });
    }

    public int getItemCount() {
        return this.hymnData.size();
    }

}
