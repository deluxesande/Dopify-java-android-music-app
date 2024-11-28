package me.deluxesande.dopify;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import me.deluxesande.dopify.fragments.HomePage;
import me.deluxesande.dopify.fragments.LibraryPage;
import me.deluxesande.dopify.fragments.ProfilePage;
import me.deluxesande.dopify.fragments.SearchPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(navListener);

        Fragment selectedFragment = new HomePage();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

    }

    private NavigationBarView.OnItemSelectedListener navListener = item -> {
        int itemId = item.getItemId();

        Fragment selectedFragment = null;

        if (itemId == R.id.home) {
            selectedFragment = new HomePage();
        } else if (itemId == R.id.search) {
            selectedFragment = new SearchPage();
        } else if (itemId == R.id.profile) {
            selectedFragment = new ProfilePage();
        } else if (itemId == R.id.library) {
            selectedFragment = new LibraryPage();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        return true;
    };
}