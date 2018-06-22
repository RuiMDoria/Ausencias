package com.example.ruido.ausencias.Pedidos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruido.ausencias.R;

import java.util.ArrayList;

//Class responsável da ligação entre a class Pedido com a class PedidosActivity

public class PedidosAdapter extends RecyclerView.Adapter<Pedido> {
    private static final String TAG = "PedidosAdapter";
    Context context;
    ArrayList<String> ID, Name, Reason, Startdate, Finishdate, State, Comments, Hours, IDuser, Acess, First, Last;
    String nivel;

    public PedidosAdapter(Context context, ArrayList<String> ID, ArrayList<String> Name, ArrayList<String> Reason, ArrayList<String> Startdate, ArrayList<String> Finishdate, ArrayList<String> State, ArrayList<String> Comments, ArrayList<String> Hours, ArrayList<String> IDuser, ArrayList<String> Acess, ArrayList<String> First, ArrayList<String> Last) {

        this.context = context;
        this.ID = ID;
        this.Name = Name;
        this.Reason = Reason;
        this.Startdate = Startdate;
        this.Finishdate = Finishdate;
        this.State = State;
        this.Comments = Comments;
        this.Hours = Hours;
        this.IDuser = IDuser;
        this.Acess = Acess;
        this.First = First;
        this.Last = Last;


    }

    //Class que faz com que o layout activity_pedido apareça as vezes necessárias para a informação existente
    @Override
    public Pedido onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_pedido, parent, false);
        return new Pedido(v);

    }

    //Class que faz os dados serem exibidos
    @Override
    public void onBindViewHolder(Pedido holder, final int position) {

        holder.nome.setText(Name.get(position));
        holder.motivo.setText(Reason.get(position));
        holder.datainicio.setText(Startdate.get(position));
        holder.datafim.setText(Finishdate.get(position));
        holder.estado.setText(State.get(position));

        nivel = Acess.get(position);
        if (nivel.contains("1")) {
            //faz com que a class PedidoActivity inicie e que as variavel passem para a class que vai iniciar
            holder.recyclelayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PedidoActivity.class);
                    intent.putExtra("id", ID.get(position));
                    intent.putExtra("nome", Name.get(position));
                    intent.putExtra("datainicio", Startdate.get(position));
                    intent.putExtra("datafim", Finishdate.get(position));
                    intent.putExtra("motivo", Reason.get(position));
                    intent.putExtra("observacoes", Comments.get(position));
                    intent.putExtra("horas", Hours.get(position));
                    intent.putExtra("id_user", IDuser.get(position));
                    intent.putExtra("acesslevel", Acess.get(position));
                    intent.putExtra("firstname", First.get(position));
                    intent.putExtra("lastname", Last.get(position));
                    context.startActivity(intent);
                    ((PedidosActivity) context).finish();
                }
            });
        } else {
            ((PedidosActivity) context).finish();
        }
    }

    //Class que retorna o numero de itens na RecycleView
    @Override
    public int getItemCount() {

        return ID.size();
    }
}