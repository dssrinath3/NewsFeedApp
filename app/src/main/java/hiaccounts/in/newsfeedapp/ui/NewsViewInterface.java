package hiaccounts.in.newsfeedapp.ui;

import hiaccounts.in.newsfeedapp.models.Newsfeed;

public interface NewsViewInterface {
    void showProgressBar();
    void hideProgressBar();
    void displayNews(Newsfeed newsResponse);
    void displayError(String s);
}
