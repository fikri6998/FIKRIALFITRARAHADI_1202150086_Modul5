package e.fikri6998.fikrialfitrarahadi_1202150086_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ToDoActivity extends AppCompatActivity {

    //Deklarasi Variabel
    private EditText ToDo, Description, Priority;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        //set title menjadi add to do
        setTitle("Add ToDo");

        //Referensi
        ToDo = (EditText) findViewById(R.id.ToDo);
        Description = (EditText) findViewById(R.id.Description);
        Priority = (EditText) findViewById(R.id.Priority);
        database = new Database(this);
    }

    public void addTodo(View view) {

        //Jika data inputan terisi
        if (database.inputData(new Data(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))) {
            //maka munculkan informasi data todolist berhasil ditambahkan
            Toast.makeText(this, "To Do baru berhasil ditambahkan", Toast.LENGTH_SHORT).show();

            //pindah ke activity main
            startActivity(new Intent(ToDoActivity.this, MainActivity.class));

            //mengakhiri activity yg berjalan
            this.finish();

        } else {
            //kalau tidak ada inputannya maka tampilkan pesan tidak boleh kosong
            Toast.makeText(this, "ToDo harus diisi!", Toast.LENGTH_SHORT).show();

            //reset inputan jadi null atau dikosongkan kembali
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }

    //Method untuk tombol kembali
    @Override
    public void onBackPressed() {
        //intent menuju MainActivity
        Intent intent = new Intent(ToDoActivity.this, MainActivity.class);
        //pindah ke intent pertama
        startActivity(intent);
        //mengakhiri activity yg berjalan
        this.finish();
    }
}
