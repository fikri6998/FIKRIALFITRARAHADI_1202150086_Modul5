package e.fikri6998.fikrialfitrarahadi_1202150086_modul5;

/**
 * Created by fikri6998 on 3/25/2018.
 */

public class Data {

    //memdeklarasi variable yang akan digunakan
    String todo, deskripsi, prioritas;

    public Data(String mTodo, String mDescription, String mPriority) {
        this.todo = mTodo;
        this.deskripsi = mDescription;
        this.prioritas = mPriority;
    }

    //Set setter dan getter untuk mengambil data

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDescription() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPriority() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }
}
