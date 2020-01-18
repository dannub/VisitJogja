package com.erporate.visitjogja.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.erporate.visitjogja.Activity.MainActivity;
import com.erporate.visitjogja.Activity.MapsActivity;
import com.erporate.visitjogja.Model.Wisata;
import com.erporate.visitjogja.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.Viewholder> {


    private List<Wisata> wisataList;
    private  int lastPosition = -1;
    private static final int ERROR_DIALOG_REQUEST =9001;


    public WisataAdapter(List<Wisata> wisataList) {
        this.wisataList = wisataList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wisata_layout,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        String nama = wisataList.get(position).getNamaPariwisata();
        String alamat = wisataList.get(position).getAlamatPariwisata();
        String detail = wisataList.get(position).getDetailPariwisata();
        String gambar = wisataList.get(position).getGambarPariwisata();

        viewholder.setData(nama,alamat,detail,gambar,position);

        if (lastPosition <position) {
            Animation animation = AnimationUtils.loadAnimation(viewholder.itemView.getContext(), R.anim.fade_in);
            viewholder.itemView.setAnimation(animation);
            lastPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return wisataList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView nama;
        private TextView alamat;
        private TextView detail;
        private ImageView gambar;
        private ConstraintLayout expandableView;
        private ImageButton arrowBtn;
        private Button location;
        private CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            alamat = itemView.findViewById(R.id.alamat);
            detail = itemView.findViewById(R.id.detail);
            gambar = itemView.findViewById(R.id.gambar);
            expandableView = itemView.findViewById(R.id.expandable);
            arrowBtn = itemView.findViewById(R.id.arrow);
            location = itemView.findViewById(R.id.location);
            cardView = itemView.findViewById(R.id.cardview);
        }


        public void setData(String namaText, String alamatText, String detailText, String gambarText, int position) {
            nama.setText(namaText);
            alamat.setText(alamatText);

            detail.setText(detailText);

           Glide.with(itemView.getContext()).load(gambarText).apply(new RequestOptions().placeholder(R.drawable.logo)).into(gambar);


           location.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   if (isServiceOK()) {
                       Intent mapsActivityIntent = new Intent(itemView.getContext(), MapsActivity.class);
                       mapsActivityIntent.putExtra("nama",namaText);
                       mapsActivityIntent.putExtra("alamat",alamatText);
                       itemView.getContext().startActivity(mapsActivityIntent);
                   }
               }
           });
            arrowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MainActivity.currentUser!=null) {
                        if (expandableView.getVisibility() == View.GONE) {
                            TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                            expandableView.setVisibility(View.VISIBLE);
                            arrowBtn.setBackground(itemView.getResources().getDrawable(R.drawable.arrow_up));
                        } else {
                            TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                            expandableView.setVisibility(View.GONE);
                            arrowBtn.setBackground(itemView.getResources().getDrawable(R.drawable.arrow_down));
                        }
                    }else {
                            Toast.makeText(itemView.getContext(),"Silahkan login dulu untuk menikmati informasi lebih!",Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }

        public  boolean isServiceOK(){
            Log.i("hasil","isServiceOK: checking google service version");
            int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable((Activity) itemView.getContext());
            if (available == ConnectionResult.SUCCESS){
                Log.d("hasil","isServiceOK: Google Play Service is working");
                return true;
            }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
                Log.d("hasil","isServiceOK: an error occured but we can fix");
                Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog((Activity) itemView.getContext(),available,ERROR_DIALOG_REQUEST);
                dialog.show();
            }else {
                Toast.makeText(itemView.getContext(), "You cant make map request", Toast.LENGTH_SHORT).show();
            }
            return false;
        }


    }
}
