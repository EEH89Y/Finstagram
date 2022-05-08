package hu.mobalk.finstagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<PostItem> mPostItemData = new ArrayList<>();
    private ArrayList<PostItem> mPostItemDataAll = new ArrayList<>();
    private Context mContext;
    private int lastPosition = -1;

    PostAdapter(Context context, ArrayList<PostItem> itemsData){
        this.mPostItemData = itemsData;
        this.mPostItemDataAll = itemsData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.posts,parent,false));
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder holder, int position) {
        // Get current sport.
        PostItem currentItem = mPostItemData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentItem);


//        if(holder.getAdapterPosition() > lastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
//            holder.itemView.startAnimation(animation);
//            lastPosition = holder.getAdapterPosition();
//        }
    }

    @Override
    public int getItemCount() {
        return mPostItemData.size();
    }


    /**
     * RecycleView filter
     * **/
//    @Override
//    public Filter getFilter() {
//        super();
//        return userFilter;
//    }


//
//    private Filter userFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            ArrayList<PostItem> filteredList = new ArrayList<>();
//            FilterResults results = new FilterResults();
//
//            if(charSequence == null || charSequence.length() == 0) {
//                results.count =mPostItemDataAll.size();
//                results.values = mPostItemDataAll;
//            } else {
//                String filterPattern = charSequence.toString().toLowerCase().trim();
//                for(PostItem item :mPostItemDataAll) {
//                    if(item.getUserName().toLowerCase().contains(filterPattern)){
//                        filteredList.add(item);
//                    }
//                }
//
//                results.count = filteredList.size();
//                results.values = filteredList;
//            }
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            mPostItemData = (ArrayList)filterResults.values;
//            notifyDataSetChanged();
//        }
//    };

    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mPostUserTitle;
        private ImageView mPostImage;
        private TextView mPostDescprition;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mPostUserTitle = itemView.findViewById(R.id.postUserTitle);
            mPostDescprition = itemView.findViewById(R.id.postDescprition);
            mPostImage = itemView.findViewById(R.id.postImage);


        }

        void bindTo(PostItem currentItem){
            mPostUserTitle.setText(currentItem.getUserName());
            mPostDescprition.setText(currentItem.getDescription());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(currentItem.getImageResource()).into(mPostImage);
        }
    }
}

