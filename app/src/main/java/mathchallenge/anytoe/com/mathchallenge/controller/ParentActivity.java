package mathchallenge.anytoe.com.mathchallenge.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mathchallenge.anytoe.com.mathchallenge.R;
import mathchallenge.anytoe.com.mathchallenge.generic.Repository;
import mathchallenge.anytoe.com.mathchallenge.model.highscore.LocalHighscore;
import mathchallenge.anytoe.com.mathchallenge.model.user.PseudoIDGenerator;
import mathchallenge.anytoe.com.mathchallenge.model.user.User;

/**
 * Created by anytoe on 10/10/2015.
 */
public class ParentActivity extends AppCompatActivity {

    private Repository<User> userRepo;
    private Repository<LocalHighscore> localHighscoreRepo;

    private User user;
    private LocalHighscore localHighscore;

    private FragmentTransaction transaction = null;
    private FragmentManager manager = null;

    private boolean hasStopped;

    @Override
    protected void onResume() {
        super.onResume();

        userRepo = new Repository<>(this, "user");
        localHighscoreRepo = new Repository<>(this, "localHighScore");

        user = userRepo.loadObject(new User(PseudoIDGenerator.getUniquePsuedoID()));
        localHighscore = localHighscoreRepo.loadObject(new LocalHighscore());

//        actionBar.setLogo(R.mipmap.ic_launcher);
//        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onPause() {
        userRepo.saveObject(user);
        localHighscoreRepo.saveObject(localHighscore);
        super.onPause();
    }


    protected FragmentTransaction openTransaction() {
        transaction = getFragmentManager().beginTransaction();
        manager = getFragmentManager();
        return transaction;
    }

    protected void commitTransaction() {
        if (!hasStopped)
            transaction.commit();
        transaction = null;
        manager = null;
    }

    protected void removeFragment(int containerId) {
        Fragment fragmentContainerFull = manager.findFragmentById(containerId);
        if (fragmentContainerFull != null) {
            transaction.remove(fragmentContainerFull);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
    }

    protected void addFragment(int containerId, Fragment fragment) {
        transaction.add(containerId, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    public User getUser() {
        return user;
    }

    public LocalHighscore getLocalHighscore() {
        return localHighscore;
    }

    public void save() {
        userRepo.saveObject(user);
        localHighscoreRepo.saveObject(localHighscore);
    }

    @Override
    protected void onStop() {
        super.onStop();
        transaction = null;

        hasStopped = true;
    }
}
