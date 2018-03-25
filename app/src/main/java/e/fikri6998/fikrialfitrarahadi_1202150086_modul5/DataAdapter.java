package e.fikri6998.fikrialfitrarahadi_1202150086_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fikri6998 on 3/25/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.holder> {

    //deklarasi variabel yang akan digunakan
    private Context mContext;
    private List<Data> list;
    int color;

    //konstruktor
    public DataAdapter(Context context, List<Data> list, int color){
        this.mContext=context;
        this.list=list;
        this.color=color;
    }

    //menentukan viewholder untuk recyclerview
    @Override
    public DataAdapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview, parent, false);
        holder hold = new holder(view);
        return hold;
    }

    //menyetting nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(DataAdapter.holder holder, int position) {

        Data data = list.get(position);
        holder.mToDo.setText(data.getTodo());
        holder.mDesc.setText(data.getDescription());
        holder.mPriority.setText(data.getPriority());
        holder.mCardView.setCardBackgroundColor(mContext.getResources().getColor(this.color));

    }

    //mendapatkan jumlah list
    @Override
    public int getItemCount() {
        return list.size();
    }
        //mendapatkan list dari adapter
        public Data getData(int position){
            return list.get(position);
        }

        //untuk menghapus list pada todolist
        public void deleteData(int i){
            //hapus item yang terpilih
            list.remove(i);

            //memberi notifikasi item yang dihapus
            notifyItemRemoved(i);
            notifyItemRangeChanged(i, list.size());

        }

    class holder extends RecyclerView.ViewHolder{
        //deklarasi variable yang akan digunakan

        public TextView mToDo, mDesc, mPriority;
        public CardView mCardView;
        public holder(View itemView){
            super(itemView);

            //panggil si data data untuk ditampilkan ke cardviewnya
            mToDo = itemView.findViewById(R.id.Todo);
            mDesc = itemView.findViewById(R.id.Deskripsi);
            mPriority = itemView.findViewById(R.id.Prioritas);
            mCardView = itemView.findViewById(R.id.cardview);
        }
    }


}
