package adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;

import java.util.List;

import beans.CommunityBean;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    private List<CommunityBean.ResultBean> list;

    public CommunityAdapter(List<CommunityBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(),R.layout.community_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.community_price.setText(list.get(position).getPrice()+"");
        holder.community_title.setText(list.get(position).getCommodityName());

        //String img=list.get(position).getImageUrl();
        Uri uri=Uri.parse(list.get(position).getImageUrl());
        holder.img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView community_title,community_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.community_img);
            community_title=itemView.findViewById(R.id.community_title);
            community_price=itemView.findViewById(R.id.community_price);
        }
    }
}
