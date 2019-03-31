package br.edu.calculadorDePizzas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder viewHolder = new ViewHolder();

    String mensagem = "";
    String mensagemRefrigerante = "";
    int totalPessoas = 0;
    int pedacosPessoas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recebeCampos();
        clearValues();

    }

    @Override
    public void onClick(View v) {
        limpaTextos();
        if(this.viewHolder.totalPessoas.getText().toString().isEmpty() || this.viewHolder.totalPessoas.getText().toString().equals("")
        || this.viewHolder.pedacosPessoa.getText().toString().isEmpty() || this.viewHolder.pedacosPessoa.getText().toString().equals("") ){
            Toast toast = new Toast(getApplicationContext());
            if(this.viewHolder.totalPessoas.getText().toString().isEmpty() || this.viewHolder.totalPessoas.getText().toString().equals("")){
                 toast = Toast.makeText(getApplicationContext(),"Digite o campo de quantidade de pessoas!!!",Toast.LENGTH_LONG);
            }else if (this.viewHolder.pedacosPessoa.getText().toString().isEmpty() || this.viewHolder.pedacosPessoa.getText().toString().equals("")){
                 toast = Toast.makeText(getApplicationContext(),"Digite o campo de quantidade de pedaços!!!",Toast.LENGTH_LONG);
            }
            toast.show();
        }else {
            verificaValores();
            }
        }

    public void verificaValores(){
        Pizza pizza = new Pizza();
        Pessoa pessoa = new Pessoa();
        pessoa.totalLitros = 0;

        totalPessoas = Integer.valueOf(this.viewHolder.totalPessoas.getText().toString());
        pedacosPessoas = Integer.valueOf(this.viewHolder.pedacosPessoa.getText().toString());

        double totalPedacos = totalPessoas * pedacosPessoas;

        beberRefrigerante(pessoa);
        pizzaSozinha(totalPedacos, pizza);

    }

    private void beberRefrigerante(Pessoa pessoa){
        if (this.viewHolder.beberRefrigerante.isChecked()) {
            pessoa.totalLitros = totalPessoas * pessoa.mlPessoa;
            mensagemRefrigerante = "Você precisa comprar " + pessoa.totalLitros + " litros de refrigerante.";
            this.viewHolder.textRefrigerante.setText(mensagemRefrigerante);
        }
    }

    private void pizzaSozinha(double totalPedacos, Pizza pizza){
        if (totalPedacos <= pizza.getPedacosBroto()) {
            mensagem = "O tamanho Broto é o ideal";
        }

        if (totalPedacos > pizza.getPedacosBroto() && totalPedacos < pizza.getPedacosGrande()) {
            mensagem = "O tamanho Médio é o ideal";
        }

        if (totalPedacos < pizza.pedacosGigante && totalPedacos > pizza.getPedacosMedia()) {
            mensagem = "O tamanho Grande é o ideal";
        }

        if (totalPedacos > pizza.getPedacosGrande() && totalPedacos < pizza.getPedacosBrotoGigante()) {
            mensagem = "O tamanho Gigante é o ideal";
        }

        this.viewHolder.textResultado.setText(mensagem);
    }

    private void clearValues(){

        this.viewHolder.textRefrigerante.setText("");
        this.viewHolder.textResultado.setText("");
        this.viewHolder.totalPessoas.setText("");
        this.viewHolder.pedacosPessoa.setText("");
        this.viewHolder.beberRefrigerante.setChecked(false);
    }

    private void limpaTextos(){
        this.viewHolder.textRefrigerante.setText("");
        this.viewHolder.textResultado.setText("");
    }

    public void onResume(){
        super.onResume();
        clearValues();
    }

    public void onPause(){
        super.onPause();
        clearValues();
    }

    private static class ViewHolder{

        EditText totalPessoas;
        EditText pedacosPessoa;
        CheckBox beberRefrigerante;
        TextView textResultado;
        TextView textRefrigerante;

    }

    public void recebeCampos(){
        this.viewHolder.totalPessoas = findViewById(R.id.edit_value);
        this.viewHolder.beberRefrigerante = findViewById(R.id.refrigerante);
        this.viewHolder.pedacosPessoa = findViewById(R.id.pedacos_pessoa);
        this.viewHolder.textResultado = findViewById(R.id.text_resultado);
        this.viewHolder.textRefrigerante = findViewById(R.id.text_refrigerante);
    }


}
