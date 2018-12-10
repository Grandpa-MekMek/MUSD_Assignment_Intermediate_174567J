package com.example.jason.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_edit_movie_details.*

class EditMovieDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie_details)

        val actionBar = supportActionBar

        actionBar!!.setDisplayHomeAsUpEnabled(true)


        var titleFromReview = intent.getStringExtra("title")
        var descriptionFromReview = intent.getStringExtra("description")
        var languageFromReview = intent.getStringExtra("language")
        var releaseDateFromReview = intent.getStringExtra("releaseDate")
        var suitableFromReview = intent.getBooleanExtra("suitable", false)
        var violenceFromReview = intent.getBooleanExtra("violence", false)
        var languageUsedFromReview = intent.getBooleanExtra("languageUsed", false)

        nameET.setText(titleFromReview)
        descriptionET.setText(descriptionFromReview)

        if(languageFromReview == "English") {
            EnglishLanguage.isChecked = true
        }
        else if (languageFromReview == "Chinese") {
            ChineseLanguage.isChecked = true
        }
        else if (languageFromReview == "Malay") {
            MalayLanguage.isChecked = true
        }
        else if (languageFromReview == "Tamil") {
            TamilLanguage.isChecked = true
        }

        releaseDateET.setText(releaseDateFromReview)

        if(suitableFromReview)
        {
            checkboxCB.isChecked == true
            violenceID.visibility = View.VISIBLE
            languageUsedID.visibility = View.VISIBLE

            if(violenceFromReview)
                violenceID.isChecked = true

            if(languageUsedFromReview)
                languageUsedID.isChecked = true
        }

        checkboxCB.setOnClickListener( {
            if (checkboxCB.isChecked == true) {
                violenceID.visibility = View.VISIBLE
                languageUsedID.visibility = View.VISIBLE
            } else {
                violenceID.visibility = View.INVISIBLE
                languageUsedID.visibility = View.INVISIBLE
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var myIntent = Intent(this, MainActivity_View::class.java)
        if (item?.itemId == R.id.menuSave) {
            var validate = true
            if (nameET.text.isEmpty()) {
                validate = false
                nameET.setError("Field empty")
            }
            if (descriptionET.text.isEmpty()) {
                validate = false
                descriptionET.setError("Field empty")
            }
            if (releaseDateET.text.isEmpty()) {
                validate = false
                releaseDateET.setError("Field empty")
            }
            if (validate) {
                myIntent.putExtra("title", nameET.text.toString())
                myIntent.putExtra("description", descriptionET.text.toString())
                var language = ""
                if (EnglishLanguage.isChecked) {
                    language = "English"
                } else if (ChineseLanguage.isChecked) {
                    language = "Chinese"
                } else if (MalayLanguage.isChecked) {
                    language = "Malay"
                } else if (TamilLanguage.isChecked) {
                    language = "Tamil"
                }
                myIntent.putExtra("language", language)
                myIntent.putExtra("releaseDate", releaseDateET.text.toString())
                myIntent.putExtra("suitable", checkboxCB.isChecked)
                myIntent.putExtra("violence", violenceID.isChecked)
                myIntent.putExtra("languageUsed", languageUsedID.isChecked)
                startActivity(myIntent)
                finish()
            }
        }
        else if (item?.itemId == R.id.menuCancel) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()

        return super.onSupportNavigateUp()
    }
}
