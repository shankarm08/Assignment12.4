package com.wedddingapp.shankar.andrinteralert;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    //Creating refernce of arrayaList.
    ArrayList<Person> personList;


    final Context context=this;   //Creating Object of contex.


    //Creating references for elements used in layouts and Dialog.
    Button saveBtn,cancelBtn;
    EditText nameET,phoneET,dobET;
    ListView listView;

    @Override
    //onCreate method.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //Setting contentView.

        getSupportActionBar();    //Getting ActionBar for option menu.

        //Setting ListView with its Id.
        listView=(ListView)findViewById(R.id.list);

        //Creating object of ArrayList.
        personList=new ArrayList<Person>();

    }

    @Override
    //Method to set the layout of Option menu.
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflating menu file to its source code.
        getMenuInflater().inflate(R.menu.option_menu,menu);

        return true;  //returnin true.
    }

    @Override
    //Method to handle onClick event on option menu item.
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //displaying Item.
        Toast.makeText(getApplicationContext(),item.getTitle()+" is clicked",Toast.LENGTH_SHORT).show();

        //Creating dialog and setting its content view.
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.custom_adapter);

        //Setting EditTexts and Buttons with their IDs.
        nameET=(EditText)dialog.findViewById(R.id.name_et);
        phoneET=(EditText)dialog.findViewById(R.id.phone_et);
        dobET=(EditText)dialog.findViewById(R.id.dob_et);

        saveBtn=(Button)dialog.findViewById(R.id.save_btn);
        cancelBtn=(Button)dialog.findViewById(R.id.cancel_btn);

        //if SAVE is clicked.
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Save is clicked",Toast.LENGTH_SHORT).show();

                //Checking all details are filled.
                if(!nameET.getText().toString().isEmpty() &&
                        !phoneET.getText().toString().isEmpty() &&
                        !dobET.getText().toString().isEmpty())
                {

                    //creating object of Person and filling information of EditTexts into its fields.
                    Person person=new Person();
                    person.nameOfPerson=nameET.getText().toString();
                    person.phoneNumber=phoneET.getText().toString();
                    person.dateBirth=dobET.getText().toString();

                    //Add object into ArrayList.
                    personList.add(person);

                    //Creating Custom Adapter.
                    CustomAdapter adapter=new CustomAdapter(MainActivity.this,personList);

                    //Setting adapter to listView.
                    listView.setAdapter(adapter);

                    //Setting item Click Listener to listView.
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                        {
                            //Showing Toast.
                            Toast.makeText(getApplicationContext(),personList.get(position).nameOfPerson+" is Clicked.",Toast.LENGTH_SHORT).show();
                        }
                    });

                    dialog.dismiss();   //Dismiss dialog
                }
                else
                {
                    //Display toast.
                    Toast.makeText(getApplicationContext(),"Please, Fill All Detaiils",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Display toast.
                Toast.makeText(getApplicationContext(),"Cancel is clicked",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();    //Showing dialog.
        return true;   //returning true.
    }

}