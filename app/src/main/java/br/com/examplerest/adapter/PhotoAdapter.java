package br.com.examplerest.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.examplerest.R;
import br.com.examplerest.entity.Photo;

public class PhotoAdapter extends BaseAdapter {

    private List<Photo> photos;
    private WeakReference<Context> weakReference;
    private LayoutInflater mInflater;

    public PhotoAdapter(List<Photo> photos, WeakReference<Context> weakReference) {
        this.photos = photos;
        this.weakReference = weakReference;

        this.mInflater = LayoutInflater.from(weakReference.get());
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return photos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Photo photo = (Photo) getItem(position);

        if (convertView == null) {

            //Cria a view nova
            convertView = mInflater.inflate(R.layout.item, null);

            //Inicia a viewHolder
            holder = new ViewHolder();

            //Inicializa os campos
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textView1 = (TextView) convertView.findViewById(R.id.textView);
            holder.textView2 = (TextView) convertView.findViewById(R.id.textView2);

            //Seta a tag(para não recriar o layout N vezes..
            convertView.setTag(holder);

        } else {

            //Recupera a tag
            holder = (ViewHolder) convertView.getTag();
        }


        //Preenche a imagem
        Glide.with(weakReference.get())
                .load(photo.getThumbnailUrl())
                .centerCrop()
                .into(holder.imageView);

        //Texts
        holder.textView1.setText(String.valueOf(photo.getId()));
        holder.textView2.setText(photo.getTitle());

        return convertView;
    }


    static class ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
    }
}
