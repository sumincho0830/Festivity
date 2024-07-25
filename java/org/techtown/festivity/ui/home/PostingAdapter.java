package org.techtown.festivity.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.festivity.R;
import org.techtown.festivity.ui.Posting;

import java.util.ArrayList;

public class PostingAdapter extends RecyclerView.Adapter<PostingAdapter.ViewHolder>{
    ArrayList<Posting> postings = new ArrayList<>();

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.post_item, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Posting posting = postings.get(position);
            holder.setPosting(posting);
        }

        @Override
        public int getItemCount() {
            return postings.size();
        }

        public void addPosting(Posting posting){
            postings.add(posting);
            notifyDataSetChanged();
        }
        public Posting getPosting(int position){
            return postings.get(position);
        }

        static class ViewHolder extends RecyclerView.ViewHolder{
            TextView title;
            TextView content;
            TextView location;
            TextView date;
            ImageView postingImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.postingTitle_text);
                content = itemView.findViewById(R.id.postingContent_text);
                location = itemView.findViewById(R.id.postingLocation_text);
                date = itemView.findViewById(R.id.postingDate_text);
                postingImage = itemView.findViewById(R.id.postingImage);
            }

            public void setPosting(Posting posting){
                title.setText(posting.getTitle());
                content.setText(posting.getContent());
                location.setText(posting.getLocation());
                date.setText(posting.getDate());
                postingImage.setImageResource(posting.getImageRes());

            }
        }
}
