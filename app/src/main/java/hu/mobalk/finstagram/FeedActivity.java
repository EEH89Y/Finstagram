package hu.mobalk.finstagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {
    private static final String LOG_TAG = FeedActivity.class.getName();
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;
    private ArrayList<PostItem> mPostData;
    private PostAdapter mAdapter;

//    private FirebaseFirestore mFirestore;
//    private CollectionReference mItems;

    private Integer itemLimit = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

       if (user != null){
           Log.d(LOG_TAG,"Logged in!");
       }else{
           Log.d(LOG_TAG,"Unsuccessful log in!");
           finish();
       }

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
        mPostData = new ArrayList<>();

        mAdapter = new PostAdapter(this, mPostData);
        mRecyclerView.setAdapter(mAdapter);

//        mFirestore = FirebaseFirestore.getInstance();
//        mItems = mFirestore.collection("Items");

//        queryData();
        initializeData();
    }

    private void initializeData() {

        String[] postUserName = getResources().getStringArray(R.array.post_user_names);
        String[] postDesc  = getResources().getStringArray(R.array.post_desc);
        TypedArray ImageRes  = getResources().obtainTypedArray(R.array.post_images);

        mPostData.clear();

        for (int i = 0; i < postUserName.length; i++) {
            mPostData.add(new PostItem (
                    postUserName[i],
                    postDesc[i],
                    ImageRes.getResourceId(i, 0)));
//            mItems.add(new PostItem (
//                    postUserName[i],
//                    postDesc[i],
//                    ImageRes.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        ImageRes.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }




//    private void queryData() {
//        mPostData.clear();
//        mItems.orderBy("name").limit(itemLimit).get().addOnSuccessListener(queryDocumentSnapshots -> {
//            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
//                PostItem item = document.toObject(PostItem.class);
//                mPostData.add(item);
//            }
//
//            if (mPostData.size() == 0) {
//                initializeData();
//                queryData();
//            }
//
//            // Notify the adapter of the change.
//            mAdapter.notifyDataSetChanged();
//        });
//    }
}