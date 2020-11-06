package ideanity.oceans.methodistndwom.adapters;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.HashMap;
import java.util.List;

import ideanity.oceans.methodistndwom.R;
import ideanity.oceans.methodistndwom.activities.DetailNoteActivity;
import ideanity.oceans.methodistndwom.database.DatabaseAccess;

public class NoteAdapter extends Adapter<NoteAdapter.MyViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public List<HashMap<String, String>> noteData;

    public class MyViewHolder extends ViewHolder implements OnClickListener {
        TextView topic;
        TextView date;
        TextView message;
        View view1;
        ImageView imageView;
        ImageView delete;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.topic = (TextView) itemView.findViewById(R.id.topic);
            this.date = (TextView) itemView.findViewById(R.id.date_time);
            this.message = (TextView) itemView.findViewById(R.id.body);
            this.view1 = (View) itemView.findViewById(R.id.view_line);
            this.imageView = (ImageView) itemView.findViewById(R.id.image1);
            this.delete = (ImageView) itemView.findViewById(R.id.image_delete);
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_view);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            /*Intent i = new Intent(ExpenseAdapter.this.context, EditExpenseActivity.class);
            String str = "expense_id";
            i.putExtra(str, (String) ((HashMap) ExpenseAdapter.this.expenseData.get(getAdapterPosition())).get(str));
            String str2 = "expense_name";
            i.putExtra(str2, (String) ((HashMap) ExpenseAdapter.this.expenseData.get(getAdapterPosition())).get(str2));
            String str3 = "expense_note";
            i.putExtra(str3, (String) ((HashMap) ExpenseAdapter.this.expenseData.get(getAdapterPosition())).get(str3));
            String str4 = "expense_amount";
            i.putExtra(str4, (String) ((HashMap) ExpenseAdapter.this.expenseData.get(getAdapterPosition())).get(str4));
            String str5 = "expense_date";
            i.putExtra(str5, (String) ((HashMap) ExpenseAdapter.this.expenseData.get(getAdapterPosition())).get(str5));
            String str6 = "expense_time";
            i.putExtra(str6, (String) ((HashMap) ExpenseAdapter.this.expenseData.get(getAdapterPosition())).get(str6));
            ExpenseAdapter.this.context.startActivity(i);*/
        }
    }

    public NoteAdapter(Context context2, List<HashMap<String, String>> noteData2) {
        this.context = context2;
        this.noteData = noteData2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this.context);
        final String note_id = (String) ((HashMap) this.noteData.get(position)).get("note_id");
        String theme_name = (String) ((HashMap) this.noteData.get(position)).get("theme_name");
        String message_body = (String) ((HashMap) this.noteData.get(position)).get("message_body");
        String date = (String) ((HashMap) this.noteData.get(position)).get("note_date");
        //String time = (String) ((HashMap) this.noteData.get(position)).get("note_time");
        databaseAccess.open();

        holder.topic.setText(theme_name);
        holder.date.setText(date);
        holder.message.setText(message_body);

        holder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new Builder(NoteAdapter.this.context).setMessage(R.string.want_to_delete).setCancelable(false).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        databaseAccess.open();
                        if (databaseAccess.deleteNote(note_id)) {
                            Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                            NoteAdapter.this.noteData.remove(holder.getAdapterPosition());
                            NoteAdapter.this.notifyItemRemoved(holder.getAdapterPosition());
                        } else {
                            Toast.makeText(NoteAdapter.this.context, R.string.failed, Toast.LENGTH_LONG).show();
                        }
                        dialog.cancel();
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
            }

        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NoteAdapter.this.context, DetailNoteActivity.class);
                String str = "note_id";
                i.putExtra(str, (String) ((HashMap) NoteAdapter.this.noteData.get(position)).get(str));
                String str2 = "theme_name";
                i.putExtra(str2, (String) ((HashMap) NoteAdapter.this.noteData.get(position)).get(str2));
                String str3 = "message_body";
                i.putExtra(str3, (String) ((HashMap) NoteAdapter.this.noteData.get(position)).get(str3));
                String str4 = "note_date";
                i.putExtra(str4, (String) ((HashMap) NoteAdapter.this.noteData.get(position)).get(str4));
//                String str5 = "note_time";
//                i.putExtra(str5, (String) ((HashMap) NoteAdapter.this.noteData.get(position)).get(str5));
                NoteAdapter.this.context.startActivity(i);

            }
        });


    }

    public int getItemCount() {
        return this.noteData.size();
    }
}
