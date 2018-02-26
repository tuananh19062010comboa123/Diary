package myapplication.example.com.entity;


import java.io.Serializable;

public class Diary   implements Serializable {
    private int diaryId;
    private String title;
    private String content;
    private String dateTime;
    private String category;
    private int favorite;

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public Diary(int diaryId, String title, String content, String dateTime, String category, int favorite) {
        this.diaryId = diaryId;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.category = category;
        this.favorite = favorite;
    }

    public Diary() {

    }

    public String toString(){
        return "diary_Id : " + this.diaryId + ", titel : " + this.title + ", content : " + this.content + ", dateTime : "+ this.dateTime
                +"category : " + this.category + ", favorite : " + this.favorite;
    }
}
