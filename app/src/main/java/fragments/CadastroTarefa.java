package fragments;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.br.senai.todolist.R;
import com.example.br.senai.todolist.databinding.FragmentCadastroTarefaBinding;

import java.util.Calendar;

import database.AppDataBase;
import model.Tarefa;

public class CadastroTarefa extends Fragment {


    private FragmentCadastroTarefaBinding binding;
    //variavel para o date picker
    DatePickerDialog datePicker;
    //variaveis para o dia, mês e ano
    int year, month, day;
    //variavel para a data atual
    Calendar dataAtual;
    //variavel para da data formatada
    String dataEscolhida = "";
    //variavel para acessar a database
    AppDataBase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //intancia a database
        database = AppDataBase.getDataBase(getActivity());

        //intancia a binding
        binding = FragmentCadastroTarefaBinding.inflate(inflater, container, false);

        //instancia a data atual
        dataAtual = Calendar.getInstance();

        // descobre a data atual
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);
        datePicker = new DatePickerDialog(getContext(), (view, ano, mes, dia) -> {

            year = ano;
            month = mes;
            day = dia;
            //formata a String da dataEscolhida
            dataEscolhida = String.format("%02d/%02d/%04d/", day, month, year);
            //jogar a String no botao
            binding.dataButton.setText(dataEscolhida);
        }, year, month, day);

        //listener do botao de data
        binding.dataButton.setOnClickListener(view -> {

            //abre o DatePicker
            datePicker.show();
        });

        binding.salvarButton.setOnClickListener(v -> {

            //validar campos
            if (binding.titulo.getText().toString().isEmpty()) {
                binding.titulo.setError(getString(R.string.campo_obrigatorio));

            } else if (dataEscolhida.isEmpty()) {
                Toast.makeText(getContext(), R.string.informe_data, Toast.LENGTH_SHORT).show();

            } else {
                //criar um objeto Tarefa
                Tarefa tarefa = new Tarefa();
                //popular a tarefa
                tarefa.setTitulo(binding.titulo.getText().toString());
                tarefa.setDescricao(binding.descricao.getText().toString());
                //criar um calendar
                Calendar dataRealizada = Calendar.getInstance();
                dataRealizada.set(year, month, day);
                //passar para a tarefa os milessegundos
                tarefa.setDataPrevista(dataRealizada.getTimeInMillis());
                //criar um Calendar para a data atual
                Calendar dataAtual = Calendar.getInstance();
                tarefa.setDataCricao(dataAtual.getTimeInMillis());
                //salvar a tarefa no UI
                new InsertTarefa().execute(tarefa);
            }

        });

        return binding.getRoot();
    }

    //class para a Task Inserir tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {

        @Override
        protected void onPreExecute() {
            Log.w("PASSOU", "onPreExecute");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.w("PASSOU", "onProgressUpdate");
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            Log.w("PASSOU", "doInBackground");
            //extrair a tarefa do vetor
            Tarefa t = tarefas[0];
            try {
                database.getTarefaDao().insert(t);
                //retorna a mgs de sucesso
                return"ok";
            }catch (Exception e){
                e.printStackTrace();
                //retorna a mgs de erro caso tenha um erro
                return e.getLocalizedMessage();
            }

        }

        @Override
        protected void onPostExecute(String resultado) {
            if(resultado.equals("ok")){
                Log.w("RESULTADO", "FOIIII");
                //voltar para a tela anterior
                getActivity().onBackPressed();


            }else {
                Log.w("RESULTADO", resultado);
                Toast.makeText(getContext(), "DEU MERDA"+resultado, Toast.LENGTH_SHORT).show();

            }

        }
    }

    //Listar Atarefa


}

