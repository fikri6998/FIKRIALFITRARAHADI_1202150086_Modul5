package e.fikri6998.fikrialfitrarahadi_1202150086_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Database mDatabase;
    private RecyclerView mRecyclerView;
    private DataAdapter adapter;
    private ArrayList<Data> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("ToDo List");

        mRecyclerView = findViewById(R.id.recyclerview); //untuk recyclerview
        mArrayList = new ArrayList<>(); //bikin araylist baru
        mDatabase = new Database(this); //bikin database baru
        mDatabase.readData(mArrayList);//panggil method readData()

        //inisiasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //bikin adapter baru
        adapter = new DataAdapter(this, mArrayList, color);

        //settingan untuk perubahan ukuran
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //inisiasi adapter untuk recycler view
        mRecyclerView.setAdapter(adapter);

        //akses method swipeDelete yang akan dipakai ketika kita menggeser todolistnya
        swipeDelete();

        //untuk mengakses button yang akan dipakai untuk ke halaman ToDoActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //melakukan perpindahan ke halaman ToDoActivity
                Intent intent = new Intent(MainActivity.this, ToDoActivity.class);
                //pindah ke activity lain
                startActivity(intent);
            }
        });
    }

    //ketika menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //method untuk item yang akan dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //inisiasi id
        int id = item.getItemId();

        //apabila item yang dipilih dalam setting
        if (id == R.id.action_settings) {

            //membuat intent ke menu Settings
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            //pindah ke activity lain
            startActivity(intent);
            //menghentikan aktivitas yg tadi dijalankan
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //method swipeDelete digunakanakn untuk menghapus todolist yang kita geser
    public void swipeDelete() {

        //bikin touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Method ini digunaakna untuk ketika kita mengeser todolist
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Data current = adapter.getData(position);
                //jika sitodolist kita geser maka akan dihapus
                if (direction == ItemTouchHelper.LEFT) {
                    if (mDatabase.removeData(current.getTodo())) {
                        //menghapus data
                        adapter.deleteData(position);
                        //membuat informasi degan snackbar pemberitahuan bahwa todolist yang digeserberhasil terhapus
                        Snackbar.make(findViewById(R.id.Activity_Main_Layout), "Todo List berhasil dihapus dari List", 500).show();
                    }
                }
            }
        };

        //inisiasi itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(mRecyclerView);
    }
}

