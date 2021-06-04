package com.example.dblokal_manga.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dblokal_manga.EditManga;
import com.example.dblokal_manga.R;
import com.example.dblokal_manga.entity.DataManga;
import com.example.dblokal_manga.main.DaftarManga;
import com.example.dblokal_manga.main.MainContact;

import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.ViewHolder> {
    Context context;
    List<DataManga> list;
    MainContact.hapus view;

    public MangaAdapter(Context context, List<DataManga> list, MainContact.hapus view) {
        this.context = context;
        this.list = list;
        this.view = view;
    }

    @NonNull
    @Override
    public MangaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_daftar_manga, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DataManga data = list.get(i);
        viewHolder.tvNama.setText(data.getNama_manga());
        viewHolder.tvDeskripsi.setText(data.getDeskripsi());
        viewHolder.tvGenre.setText(data.getGenre());
        viewHolder.id.setText(String.valueOf(data.getId()));
        viewHolder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.deleteData(data);
            }
        });
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditManga.class);
                intent.putExtra("nama", data.getNama_manga());
                intent.putExtra("deskripsi", data.getDeskripsi());
                intent.putExtra("genre", data.getGenre());
                intent.putExtra("id", data.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvDeskripsi,tvGenre, id;
        Button btnHapus, btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_item_nama_manga);
            tvDeskripsi = itemView.findViewById(R.id.tv_item_deskripsi);
            tvGenre = itemView.findViewById(R.id.tv_item_genre);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            id = itemView.findViewById(R.id.tv_item_id);
        }
    }
}
