package com.practice.guestbook;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.practice.guestbook.Network.ClientApi;

public class CommentAdapter extends PagedListAdapter<Data, CommentAdapter.DataViewHolder> {

    Context context;

    public CommentAdapter(Context context) {
        super(DIFF_CALLBACK);

        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataViewHolder holder, int position) {
        final Data data = getItem(position);

        if (data != null) {
            Glide.with(context)
                    .load(ClientApi.BASE_URL + data.user.getAvatar())
                    .into(holder.imageView);

            holder.titleTextView.setText(data.title);
            holder.messageTextView.setText(data.message);
            holder.userNameTextView.setText(data.user.getName());

            if (data.user.getId() != IdentificationActivity.user.getUser().getId()) {
                holder.deleteComment.setVisibility(View.INVISIBLE);
            } else {
                holder.deleteComment.setVisibility(View.VISIBLE);
            }

            holder.deleteComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IdentificationActivity.networkController.deleteComment(context, data.id);
                }
            });

        } else {
            Toast.makeText(context, "item is null", Toast.LENGTH_LONG).show();
        }
    }

    static  DiffUtil.ItemCallback<Data> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Data>() {
                @Override
                public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
                    return oldItem.id == newItem.id;
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class DataViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView titleTextView;
        TextView messageTextView;
        TextView userNameTextView;
        Button deleteComment;

        public DataViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            deleteComment = itemView.findViewById(R.id.deleteCommentButton);
        }
    }
}
