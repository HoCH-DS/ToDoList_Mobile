package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.br.senai.todolist.R;
import com.example.br.senai.todolist.databinding.FragmentCadastroTarefaBinding;
import com.example.br.senai.todolist.databinding.FragmentPrincipalBinding;

import java.util.List;

import database.AppDataBase;
import model.Tarefa;


public class PrincipalFragment extends Fragment {

    //variavel para binding
    private FragmentPrincipalBinding binding;

    //variavel para Data
    private AppDataBase dataBase;

    //variavel para Lista
    private List<Tarefa> tarefas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //intancia o binding
        binding = FragmentPrincipalBinding.inflate(inflater, container, false);
        //clica no botao de adicionar tarefa
        binding.btnAddTarefa.setOnClickListener(v ->{
            NavHostFragment.
                    findNavController( PrincipalFragment.this).
                    navigate(R.id.action_principalFragment_to_cadastroTarefa);

        });
        //Return
        return binding.getRoot();
    }
}
