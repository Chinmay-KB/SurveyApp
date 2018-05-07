package surveyapp.thesmader.com.surveyapp;

/**
 * Created by Chinmay on 05-05-2018.
 */

import com.google.firebase.firestore.Exclude;

public class Note {
    private String documentId;
    private int marks;
    private int mainSheet;
    private int suppSheet;
    private int index;

    public Note() {
        //public no-arg constructor needed
    }

    public Note(int marks, int mainSheet, int suppSheet,int index) {
        this.marks = marks;
        this.mainSheet = mainSheet;
        this.suppSheet =suppSheet;
        this.index=index;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public int getMarks() {
        return marks;
    }

    public int getMainSheet() {
        return mainSheet;
    }

    public int getSuppSheet() {
        return suppSheet;
    }
    public int getIndex(){
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
