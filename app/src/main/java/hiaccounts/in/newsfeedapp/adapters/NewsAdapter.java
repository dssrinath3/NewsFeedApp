package hiaccounts.in.newsfeedapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import hiaccounts.in.newsfeedapp.R;
import hiaccounts.in.newsfeedapp.models.Row;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    List<Row> newsList;
    Context context;

    public NewsAdapter(List<Row> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_news_items,parent,false);
        NewsHolder mh = new NewsHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"font/RobotoCondensed-Regular.ttf");
        holder.tvNewsTitle.setTypeface(tf,Typeface.BOLD);
        if (newsList.get(position)!=null){
            if (newsList.get(position).getTitle()!=null){
                holder.tvNewsTitle.setText(newsList.get(position).getTitle());
            }
            if (newsList.get(position).getDescription()!=null){
                holder.tvNewsDescription.setText(newsList.get(position).getDescription());
            }
            Glide.with(context).load(newsList.get(position).getImageHref()).into(holder.imageNews);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        TextView tvNewsTitle,tvNewsDescription;
        ImageView imageNews;

        public NewsHolder(View v) {
            super(v);
            tvNewsTitle = (TextView) v.findViewById(R.id.tvNewsTitle);
            tvNewsDescription = (TextView) v.findViewById(R.id.tvNewsDescription);
            imageNews = (ImageView) v.findViewById(R.id.imageNews);
        }
    }
}
