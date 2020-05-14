package com.example.carwale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carwale.R;
import com.example.carwale.model.ListFields;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.ViewHolder>
public class ListAdapter extends  RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {
    private final Context context;
    private  final ArrayList<ListFields> listfields;
    private final EventListener listener;
    private   ArrayList<ListFields> listfieldsfull;

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ListFields> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listfieldsfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ListFields item : listfields) {
                    if (item.getCountryname().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listfields.clear();
            listfields.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    public interface EventListener {
        void onSelect(ListFields listfields);
    }

    public ListAdapter(Context context , ArrayList<ListFields> listfields)
    {
        this.context = context;
        this.listfields = listfields;
        listfieldsfull = new ArrayList<>(listfields);
        this.listener= (EventListener) context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_list_filed1, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final ListFields listfield = listfields.get(i);


        viewHolder.CountryName.setText("Country Name       :"+listfield.getCountryname());
        viewHolder.Totalrrecovered.setText("Total recovered    :"+listfield.getTotalrecovered());
        viewHolder.Totaldeaths.setText(("Total deaths         :"+listfield.getTotaldeaths()));
        viewHolder.Totalcase.setText(("Total cases           :"+listfield.getTotalcases()));


    }

    @Override
    public int getItemCount() {
        return listfields.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        TextView CountryName ,Totalcase ,Totaldeaths ,Totalrrecovered ;


        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            RelativeLayout layout=(RelativeLayout) itemView;
            CountryName = layout.findViewById(R.id.countrynamefield);
            Totalcase =layout.findViewById(R.id.totalcasesfield);
            Totaldeaths =layout.findViewById(R.id.totaldeathsfield);
            Totalrrecovered =layout.findViewById(R.id.totalrecoveredfield);
        }







    }
}
