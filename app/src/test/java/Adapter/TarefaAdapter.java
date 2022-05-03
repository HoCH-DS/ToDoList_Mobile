package Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TarefaAdapter {

    //Classe ViewHolder prara mapear os componentes do XML
    class TarefaViewHolder extends RecyclerView.ViewHolder {
        //Variaveis para acessar os componentes do XML
        TextView tvTitulo, tvdata, tvStatus;

        public TarefaViewHolder(View view){
            //Chama o Construtor da super class
            super(view);
        }
    }

}
