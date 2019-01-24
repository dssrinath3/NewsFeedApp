package hiaccounts.in.newsfeedapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Newsfeed {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("rows")
    @Expose
    public List<Row> rows = null;


    public Newsfeed(String title, List<Row> rows) {
        this.title = title;
        this.rows = rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
