package com.example.androidui



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_main_constraint.*

class MainActivity : AppCompatActivity(),  View.OnClickListener {

    val isRegistered = false
    //   lateinit var registerButton: Button
//    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_constraint)
        // view objects are availabe
//        val titleTextview: TextView = findViewById(R.id.subTitle)
//        registerButton = findViewById(R.id.button2)
//        loginButton = findViewById(R.id.button3)
        registerForContextMenu(demoB)

        // setupButtons()

        if (isRegistered) {
            subTitle.text = "Please Login"
        } else
            subTitle.text = "Please Register"

        button3.setOnClickListener(this)
        button2.setOnClickListener(this)
    }
    fun setupButtons() {

        if (isRegistered)
            button2.visibility = View.INVISIBLE
        else
            button3.visibility = View.INVISIBLE
    }

    override fun onClick(v: View?) {
        val id = v?.id
        when (id){
            R.id.button3 ->{
                Log.d("MainActivity", "Login Clicked")
                // Launch Activity
                val i = Intent(this, AuthActivity::class.java)
                startActivity(i)
            }
            R.id.button2 -> {
                Log.d("MainActivity", "Register Clicked")
                showPopUpMenu()
                val i = Intent(this, RegisterActivity::class.java)
               startActivity(i)

            }
        }

    }

    private fun showPopUpMenu() {
        val pMenu = PopupMenu(this, button2)
        pMenu.menu.add("Driver")
        pMenu.menu.add("Rider")

        pMenu.setOnMenuItemClickListener {
            when(it.title){
                "Driver" -> {
                    Toast.makeText(this, "Welcome Driver", Toast.LENGTH_LONG).show()
                }
                "Rider" -> {
                    Toast.makeText(this, "Welcome Rider", Toast.LENGTH_LONG).show()
                }

            }
            true
        }

        pMenu.show()
    }

    val MENU_LOGIN = 1
    val MENU_EXIT = 2
    val MENU_REGISTER = 3
    val MENU_FILE_OPEN = 4
    val MENU_FILE_SAVE = 5
    val MENU_FILE_CLOSE = 6


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val item1 = menu?.add(0, MENU_LOGIN, 0, "Sign in" )
        item1?.setIcon(R.drawable.ic_launcher_foreground)
        item1?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu?.add(Menu.NONE, MENU_REGISTER, 0 , "Register")

       val exitItem = menu?.add(0, MENU_EXIT, 0 , "Exit")
        exitItem?.setIcon(android.R.drawable.sym_action_email)
        //exitItem?.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        exitItem?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        val fileMenu = menu?.addSubMenu("File")
        fileMenu?.add(0, MENU_FILE_OPEN, 0, "Open")
        fileMenu?.add(0, MENU_FILE_SAVE, 0, "Save")
        fileMenu?.add(0, MENU_FILE_CLOSE, 0, "Close")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("MainActivity", "${item.title} option selected")
        val id = item.itemId
        when(id){
            MENU_LOGIN -> {
                val i = Intent(this, AuthActivity::class.java)
                startActivity(i)
                return true
            }
            MENU_REGISTER -> {
                val i = Intent(this, RegisterActivity::class.java)
                startActivity(i)
                return true
            }
            MENU_EXIT -> {
                finish()
                return true
            }
            MENU_FILE_OPEN -> {}
            MENU_FILE_SAVE -> {}
            MENU_FILE_CLOSE -> {}

        }
        return super.onOptionsItemSelected(item)
    }
    val MENU_ITEM_1 = 1
    val MENU_ITEM_2 = 2

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        when(v?.id){
            R.id.demoB -> {
                //UI DEMO button
                menu?.add(Menu.NONE, MENU_ITEM_1, 2, "Continue")
                menu?.add(0, MENU_ITEM_2, 1 ,"Cancel")

            }
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            MENU_ITEM_1 -> {
                Toast.makeText(this, "Continuing with demo", Toast.LENGTH_LONG).show()
            }
            MENU_ITEM_2 -> {
                Toast.makeText(this, "Cancelling with demo", Toast.LENGTH_LONG).show()
            }
        }
        return super.onContextItemSelected(item)
    }

}

//        fun setupButtons() {
//            if (isRegistered)
//                registerB.visibility = View.INVISIBLE
//            else
//                submitB.visibility = View.INVISIBLE



//}