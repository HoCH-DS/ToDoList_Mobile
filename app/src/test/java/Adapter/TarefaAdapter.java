package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.br.senai.todolist.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import model.Tarefa;


public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    //lista de tarefas
    private List<Tarefa> tarefa;

    //variavel para o Context
    private Context context;

    //Criar Um Construdor para Receber as Lista e seus derivados
    public TarefaAdapter(List<Tarefa> lista, Context context){
        this.tarefa = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //infla o leyout do adapter
        View view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_tarefa, parent, false);
        //retorna um novo viewHolder com a View
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        // obtém a tarefa para Position
        Tarefa t = tarefa.get(position);
        holder.tvTitulo.setText(t.getTitulo());
        //se tiver concluida
        if(t.isConcluida()){
            holder.tvStatus.setText("CONCLUIDA");
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.green));
        }else {
            holder.tvStatus.setText("ABERTA");
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        }
        //formata a data de long pra String
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvStatus.setText(formatador.format(t.getDataPrevista()));
        Calendar.getInstance().getTimeInMillis();
    }

    @Override
    //retorna a quantidades de itens na Lista e seus derivados
    public int getItemCount() {
        if(tarefa != null) {
            return tarefa.size();
        }
        return 0;
    }

    //Classe ViewHolder prara mapear os componentes do XML
    class TarefaViewHolder extends RecyclerView.ViewHolder {
        //Variaveis para acessar os componentes do XML
        TextView tvTitulo, tvdata, tvStatus;

        public TarefaViewHolder(View view){
            //Chama o Construtor da super class
            super(view);
            //passar para as variaveis, os campos do XML
            tvTitulo = view.findViewById(R.id.NomeTarefa);
            tvdata = view.findViewById(R.id.dataTarefa);
            tvStatus = view.findViewById(R.id.EstadoTarefa);


        }
    }

}
