package khanhnqph30151.fptpoly.duan1.user.home.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duan1.R;
import khanhnqph30151.fptpoly.duan1.user.cart.CartDAO;
import khanhnqph30151.fptpoly.duan1.user.home.Home;
import khanhnqph30151.fptpoly.duan1.user.home.HomeAdapter;
import khanhnqph30151.fptpoly.duan1.user.home.HomeDAO;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    Context context;
    ArrayList<Comment> list;

    public CommentAdapter(Context context, ArrayList<Comment> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(ArrayList<Comment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment=list.get(position);
        holder.tv_user.setText(comment.getUser_name());
        holder.tv_cmt.setText(comment.getComment_content());
        holder.ratingStarView.setRating(comment.getRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_user, tv_cmt;
        RatingStarView ratingStarView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_user=itemView.findViewById(R.id.tv_user_cmt);
            tv_cmt=itemView.findViewById(R.id.tv_content_cmt);
            ratingStarView=itemView.findViewById(R.id.ratingstarview);

        }
    }
}
