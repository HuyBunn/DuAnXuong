package com.example.duanxuong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    BottomNavigationView bottom_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav = findViewById(R.id.nav);
        bottom_menu=findViewById(R.id.menu_bot);
        View view = nav.getHeaderView(0);
        TextView tvUser = view.findViewById(R.id.tvUser);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        if(user.equalsIgnoreCase("admin")){
            nav.getMenu().findItem(R.id.nav_taotaikhoan).setVisible(true);
        }else {
//            dao = new thuthuDao(this);
//            thuthu tt = dao.getID(user);
//            String username = tt.getHoTenTT();
//            tvUser.setText("Welcome "+username);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setItemIconTintList(null);
        frg_sp pm = new frg_sp();
        setTitle("Quản lý sản phẩm");
        Toast.makeText(this, "Chào mừng đến với trang chủ", Toast.LENGTH_SHORT).show();
        replaceFrg(pm);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.nav_thongke) {
                    frg_thongke pm = new frg_thongke();
                    setTitle("Thống kê");
                    replaceFrg(pm);
                }else if (item.getItemId()==R.id.nav_doimatkhau){
                    frg_dmk s = new frg_dmk();
                    setTitle("ĐỔi mật khẩu");
                    replaceFrg(s);
                }else if (item.getItemId()==R.id.nav_taotaikhoan){
                    frg_ttk ls = new frg_ttk();
                    setTitle("Tạo tài khoản");
                    replaceFrg(ls);
                } else if (item.getItemId()==R.id.nav_dangxuat) {
                    startActivity(new Intent(getApplicationContext(), dang_nhap.class));
                    finish();
                }

                drawerLayout.close();
                return true;
            }
        });
        bottom_menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.nav_sp) {
                    frg_sp sp = new frg_sp();
                    setTitle("Sản phẩm");
                    replaceFrg(sp);
                }else if (item.getItemId()==R.id.nav_hoadon){
                    frg_hoadon hoadon = new frg_hoadon();
                    setTitle("Hóa đơn");
                    replaceFrg(hoadon);
                }else if (item.getItemId()==R.id.nav_CThoadon){
                    frg_cthoadon ct = new frg_cthoadon();
                    setTitle("Chi tiết hóa đơn");
                    replaceFrg(ct);
                }else if (item.getItemId()==R.id.nav_loai){
                    frg_loai loai = new frg_loai();
                    setTitle("Loại sản phẩm");
                    replaceFrg(loai);
                }
                return true;
            }
        });
    }
    public void replaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav, frg).commit();
    }
    }