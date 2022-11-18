package com.sopt.hajeong.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //앱 실행하면 바로 homefragment 뜸
        supportFragmentManager.beginTransaction()
            .add(R.id.cv_home, HomeFragment())
            .commit()
        //각 메뉴를 눌렀을때 homecontainer안의 내용을 바꾸는(대체하는) 작업
        binding.bnvHome.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.menu_home -> transaction.replace(R.id.cv_home, HomeFragment())
                R.id.menu_gallery -> transaction.replace(R.id.cv_home, GalleryFragment())
                R.id.menu_search -> transaction.replace(R.id.cv_home,SearchFragment())
                else -> error("item id :${it.itemId}) is cannot be selected")
            }
            transaction.commit()
            true
        }

        /*
        //홈 아이템을 다시 선택했을 때 다른 액션(최상단으로 이동) 추가 기능
        binding.bnvHome.setOnItemReselectedListener { item->
            when (item.itemId) {
                R.id.menu_home -> {
                    homeFragment.viewTop()
                    true
                }
                else -> false
            }
        }*/
    }
}