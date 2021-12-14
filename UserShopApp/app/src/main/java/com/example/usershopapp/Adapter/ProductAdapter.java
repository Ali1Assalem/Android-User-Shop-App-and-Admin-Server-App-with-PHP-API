package com.example.usershopapp.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import com.example.usershopapp.Database.ModelDB.Cart;
import com.example.usershopapp.Database.ModelDB.Favorite;
import com.example.usershopapp.HomeActivity;
import com.example.usershopapp.Interface.IItemClickListener;
import com.example.usershopapp.Model.Product;
import com.example.usershopapp.R;
import com.example.usershopapp.Utils.Common;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    Context context;
    List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.product_item_layout,null);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_price.setText(new StringBuilder("$").append(products.get(position).Price).toString());
        holder.txt_drink_name.setText(products.get(position).Name);

        Picasso.get()
                .load(products.get(position).Link)
                .into(holder.imgProduct);


        holder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showAddToCartDialog(position);
            }
        });


        holder.imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.txt_drink_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //favorite system
        if (Common.favoriteRepository.isFavorite(Integer.parseInt(products.get(position).ID))==1)
            holder.btn_favorite.setImageResource(R.drawable.favorite_red);
        else
            holder.btn_favorite.setImageResource(R.drawable.favorite);

        holder.btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.favoriteRepository.isFavorite(Integer.parseInt(products.get(position).ID))!=1)
                {
                    addOrRemoveToFavorite(products.get(position),true);
                    holder.btn_favorite.setImageResource(R.drawable.favorite_red);
                }
                else
                {
                    addOrRemoveToFavorite(products.get(position),false);
                    holder.btn_favorite.setImageResource(R.drawable.favorite);
                }

            }
        });


    }

    private void addOrRemoveToFavorite(Product product, boolean isAdd) {
        Favorite favorite=new Favorite();
        favorite.id=product.ID;
        favorite.link=product.Link;
        favorite.name=product.Name;
        favorite.price=product.Price;
        favorite.menuId=product.MenuID;

        if (isAdd)
            Common.favoriteRepository.insertFav(favorite);
        else
            Common.favoriteRepository.delete(favorite);
    }


    private void showAddToCartDialog(int position) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context).inflate(R.layout.add_to_cart_layout,null);

        ImageView img_product_dialog=itemView.findViewById(R.id.img_cart_product);
        ElegantNumberButton txt_count=itemView.findViewById(R.id.txt_count);
        TextView txt_product_dialog=itemView.findViewById(R.id.txt_cart_product_name);

        EditText edt_comment=itemView.findViewById(R.id.edt_comment);

        RadioButton rdi_sizeM=itemView.findViewById(R.id.rdi_sizeM);
        RadioButton rdi_sizeL=itemView.findViewById(R.id.rdi_sizeL);

        rdi_sizeM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                    Common.sizeOfCup=0;
            }
        });
        rdi_sizeL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Common.sizeOfCup=1;
            }
        });


        RecyclerView recycler_topping=itemView.findViewById(R.id.recycler_topping);
        recycler_topping.setLayoutManager(new LinearLayoutManager(context));
        recycler_topping.setHasFixedSize(true);

        MultiChoiceAdapter adapter=new MultiChoiceAdapter(context, Common.toppingList);
        recycler_topping.setAdapter(adapter);

        Picasso.get()
                .load(products.get(position).Link)
                .into(img_product_dialog);

        txt_product_dialog.setText(products.get(position).Name);

        builder.setView(itemView);
        builder.setNegativeButton("ADD TO CART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               if(Common.sizeOfCup==-1)
                {
                    Toast.makeText(context, "Please choose size of cup", Toast.LENGTH_SHORT).show();
                    return;
                }

                showConfirmDialog(position,txt_count.getNumber());
                dialog.dismiss();

            }
        });
        builder.show();
        Common.sizeOfCup=-1;

    }

    private void showConfirmDialog(int position, String number) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context).inflate(R.layout.confirm_add_to_cart_layout,null);


        ImageView img_product_dialog=itemView.findViewById(R.id.img_product);
        TextView txt_product_dialog=itemView.findViewById(R.id.txt_cart_product_name);
        TextView txt_product_price=itemView.findViewById(R.id.txt_cart_product_price);
        TextView txt_topping_extra=itemView.findViewById(R.id.txt_topping_extra);


        Picasso.get()
                .load(products.get(position).Link)
                .into(img_product_dialog);

        txt_product_dialog.setText(new StringBuilder(products.get(position).Name).append(" x")
                .append(Common.sizeOfCup ==0 ? "Size M":"Size L")
                .append(number).toString());


        double price=(Double.parseDouble(products.get(position).Price)*Double.parseDouble(number))+Common.toppingPrice;

        if(Common.sizeOfCup==1){  //size L
            price+=(3.0*Double.parseDouble(number));
        }

        StringBuilder topping_final_comment=new StringBuilder("");
        for (String line:Common.toppingAdded)
            topping_final_comment.append(line).append("\n  ");

        txt_topping_extra.setText(topping_final_comment);

        //final double finalPrice = Math.round(price);
        final double finalPrice = price;

        txt_product_price.setText(new StringBuilder("$").append(finalPrice));
        builder.setNegativeButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

                try {
                    //Add to sqlite
                    //create new cartItem
                    Cart cartItem = new Cart();
                    cartItem.name = products.get(position).Name;
                    cartItem.amount = Integer.parseInt(number);
                    cartItem.price = finalPrice;
                    cartItem.size=Common.sizeOfCup;
                    cartItem.with = txt_topping_extra.getText().toString();
                    cartItem.link=products.get(position).Link;

                    Common.cartRepository.insertToCart(cartItem);

                    //  Log.d("EDMT", new Gson().toJson(cartItem));

                    Toast.makeText(context, new Gson().toJson(cartItem), Toast.LENGTH_SHORT).show();


                }
                catch (Exception ex){
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(itemView);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

}
