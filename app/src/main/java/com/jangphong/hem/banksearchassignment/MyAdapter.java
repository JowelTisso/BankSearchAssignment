package com.jangphong.hem.banksearchassignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {



    public List<ListItem> listItems;
    public Context context;
    public List<ListItem> AllBanks;



    public MyAdapter(List<ListItem> listItemAdap, Context context) {
        this.listItems = listItemAdap;
        this.context = context;
        this.AllBanks = new ArrayList<>(listItemAdap);



    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items,parent,false);
        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        holder.txtviewBankName.setText(listItem.getBank_name());
        holder.txtviewAddress.setText(listItem.getAddress());
        holder.txtviewIfsc.setText(listItem.getIfsc());
        holder.txtviewBranch.setText(listItem.getBranch());
        holder.txtviewBankId.setText(listItem.getBank_id());
        holder.txtviewCity.setText(listItem.getCity());
        holder.txtviewDistrict.setText(listItem.getDistrict());
        holder.txtviewState.setText(listItem.getState());



    }
    @Override
    public Filter getFilter() {
        return filter;
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }




    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<ListItem> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty())
            {
                filteredList.addAll(AllBanks);
            }
            else
            {
                for (ListItem Banks: AllBanks)
                {
                    if ( Banks.toString().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        filteredList.add(Banks);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            listItems.clear();
            listItems.addAll((Collection<? extends ListItem>) filterResults.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtviewBankName;
        public TextView txtviewAddress;
        public TextView txtviewIfsc;
        public TextView txtviewBranch;
        public TextView txtviewBankId;
        public TextView txtviewCity;
        public TextView txtviewDistrict;
        public TextView txtviewState;

        public ViewHolder(View itemView) {
            super(itemView);

            txtviewBankName = (TextView)itemView.findViewById(R.id.bankName);
            txtviewAddress = (TextView)itemView.findViewById(R.id.address);
            txtviewIfsc = (TextView)itemView.findViewById(R.id.ifsc);
            txtviewBranch = (TextView)itemView.findViewById(R.id.branch);
            txtviewBankId = (TextView)itemView.findViewById(R.id.bankId);
            txtviewCity = (TextView)itemView.findViewById(R.id.city);
            txtviewDistrict = (TextView)itemView.findViewById(R.id.district);
            txtviewState = (TextView)itemView.findViewById(R.id.state);
        }
    }
}
