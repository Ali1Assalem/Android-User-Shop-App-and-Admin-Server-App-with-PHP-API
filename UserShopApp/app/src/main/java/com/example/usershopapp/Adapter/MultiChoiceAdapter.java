package com.example.usershopapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.usershopapp.Model.Product;
import com.example.usershopapp.R;
import com.example.usershopapp.Utils.Common;

import java.util.List;

public class MultiChoiceAdapter extends RecyclerView.Adapter<MultiChoiceAdapter.MultiChoiceViewHolder>{
    Context context;
    List<Product> optionList;


    public MultiChoiceAdapter(Context context, List<Product> optionList) {
        this.context = context;
        this.optionList = optionList;
    }

    @NonNull
    @Override
    public MultiChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.multi_check_layout,null);
        return new MultiChoiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiChoiceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.checkBox.setText(optionList.get(position).Name);

        Common.toppingAdded.clear();
        Common.toppingPrice=0.0;
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Common.toppingAdded.add(buttonView.getText().toString());
                    Common.toppingPrice+=Double.parseDouble(optionList.get(position).Price);
                }
                else {
                    Common.toppingAdded.remove(buttonView.getText().toString());
                    Common.toppingPrice-=Double.parseDouble(optionList.get(position).Price);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    public class MultiChoiceViewHolder extends RecyclerView.ViewHolder {
        Context context;
        CheckBox checkBox;
        public MultiChoiceViewHolder(View itemView){
            super(itemView);
            checkBox=itemView.findViewById(R.id.ckb_topping);
        }
    }
}