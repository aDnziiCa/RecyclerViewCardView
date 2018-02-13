package adnziica.adrian.com.recyclerviewcardview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import adnziica.adrian.com.recyclerviewcardview.models.Movie;
import adnziica.adrian.com.recyclerviewcardview.adapters.MyAdapter;
import adnziica.adrian.com.recyclerviewcardview.R;


public class MainActivity extends AppCompatActivity
    {
        private List<Movie> movies;
        private RecyclerView mRecyclerView;
        private RecyclerView.LayoutManager mLayoutManager;
        private RecyclerView.Adapter mAdapter;
        private int count=0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            movies = this.getAllMovies();
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mLayoutManager = new LinearLayoutManager(this);
            //mLayoutManager = new GridLayoutManager(this,2);
            //mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

            mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener()
                {
                    @Override
                    public void OnItemClick(Movie movies, int position) {
                        //Toast.makeText(MainActivity.this, name+" - "+position, Toast.LENGTH_SHORT).show();
                        removeMovie(position);
                    }
                });
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu,menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.add_name:
                    this.addMovie(0);
                    return true;
                    default:
                        return super.onOptionsItemSelected(item);
            }


        }

        private void addMovie(int position) {
            movies.add(position, new Movie("New image "+(++count),R.drawable.images1));
            mAdapter.notifyItemInserted(position);
            mLayoutManager.scrollToPosition(position);
        }

        private void removeMovie(int position) {
            if(position!=-1) {

                movies.remove(position);
                mAdapter.notifyItemRemoved(position);
            }else{return;}

        }

        private List<Movie> getAllMovies(){
            return new ArrayList<Movie>()
                {{
                    add(new Movie("Movie 1",R.drawable.elizabeth1));
                    add(new Movie("Movie 2",R.drawable.elizabeth1));
                    add(new Movie("Movie 3",R.drawable.elizabeth1));
                    add(new Movie("Movie 4",R.drawable.elizabeth1));
                }};
        }

    }
