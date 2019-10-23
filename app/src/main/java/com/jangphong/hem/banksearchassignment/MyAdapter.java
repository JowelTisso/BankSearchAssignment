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
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {



    public List<ListItem> listItems;
    public Context context;
    public List<ListItem> AllBanks ;



    public MyAdapter(List<ListItem> listItemAdap, Context context) {
        this.listItems = listItemAdap;
        this.context = context;
        this.AllBanks = listItemAdap;



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

            String charString = charSequence.toString();

            List<ListItem> filteredList = new ArrayList<>();

            if (charString.isEmpty())
            {
                listItems = AllBanks;
            }
            else
            {
                for (ListItem Banks: AllBanks)
                {
                    if ( Banks.getBank_name().toLowerCase().contains(charString.toLowerCase())||Banks.getIfsc().toLowerCase().contains(charString.toLowerCase())||Banks.getAddress().toLowerCase().contains(charString.toLowerCase())||Banks.getBank_id().toLowerCase().contains(charString.toLowerCase())||Banks.getBranch().toLowerCase().contains(charString.toLowerCase())||Banks.getCity().toLowerCase().contains(charString.toLowerCase())||Banks.getDistrict().toLowerCase().contains(charString.toLowerCase())||Banks.getState().toLowerCase().contains(charString.toLowerCase()))
                    {
                        filteredList.add(Banks);
                    }
                }

                listItems = filteredList;
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listItems;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filResults) {


            listItems = (List<ListItem>)filResults.values;
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtviewBankName;
        private TextView txtviewAddress;
        private TextView txtviewIfsc;
        private TextView txtviewBranch;
        private TextView txtviewBankId;
        private TextView txtviewCity;
        private TextView txtviewDistrict;
        private TextView txtviewState;

        public ViewHolder(View itemView) {
            super(itemView);

            txtviewBankName = itemView.findViewById(R.id.bankName);
            txtviewAddress = itemView.findViewById(R.id.address);
            txtviewIfsc = itemView.findViewById(R.id.ifsc);
            txtviewBranch =itemView.findViewById(R.id.branch);
            txtviewBankId = itemView.findViewById(R.id.bankId);
            txtviewCity = itemView.findViewById(R.id.city);
            txtviewDistrict = itemView.findViewById(R.id.district);
            txtviewState = itemView.findViewById(R.id.state);
        }
    }
}
