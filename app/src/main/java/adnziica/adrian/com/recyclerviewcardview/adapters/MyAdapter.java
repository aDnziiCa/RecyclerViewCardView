package adnziica.adrian.com.recyclerviewcardview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import adnziica.adrian.com.recyclerviewcardview.R;
import adnziica.adrian.com.recyclerviewcardview.models.Movie;

/**
 * Created by aDnziiCa
 * on 04/02/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
    {

        private List<Movie> movies;
        private int layout;
        private OnItemClickListener onItemClickListener;

        private Context context;

        public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener){
            this.movies = movies;
            this.layout = layout;
            this.onItemClickListener = listener;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            context = parent.getContext();
            View v = LayoutInflater.from(context).inflate(layout,parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(movies.get(position),onItemClickListener);

        }

        @Override
        public int getItemCount() {
            return movies.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView textViewName;
            public ImageView imageViewPoster;

            public ViewHolder(View v){
                super(v);
                this.textViewName = (TextView)v.findViewById(R.id.textViewTitle);
                imageViewPoster = (ImageView) v.findViewById(R.id.imageViewPoster);
            }

            public void bind(final Movie movie, final OnItemClickListener listener){
                //this.TextViewName.setText(name);
                textViewName.setText(movie.getName());
                Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);
                //imageViewPoster.setImageResource(movie.getPoster());

                itemView.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view) {
                            listener.OnItemClick(movie, getAdapterPosition());
                        }
                    });
            }

        }

        public interface OnItemClickListener {
                void OnItemClick(Movie movie, int position);
            }


    }
