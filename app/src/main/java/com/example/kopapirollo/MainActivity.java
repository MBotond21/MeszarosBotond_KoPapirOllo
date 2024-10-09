package com.example.kopapirollo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button ko;
    private Button papir;
    private Button ollo;
    private ImageView emberChoice;
    private ImageView computerChoice;
    private TextView emberPointsView;
    private TextView computerPointsView;
    private int emberPoints;
    private int computerPoints;
    private int user;
    private int computer;
    private Random rnd;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        ko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emberChoice.setImageResource(R.drawable.rock);
                user = 1;
                computerChoose();
                calcResults();
                checkForEnd();
            }
        });
        papir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emberChoice.setImageResource(R.drawable.paper);
                user = 2;
                computerChoose();
                calcResults();
                checkForEnd();
            }
        });
        ollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emberChoice.setImageResource(R.drawable.scissors);
                user = 3;
                computerChoose();
                calcResults();
                checkForEnd();
            }
        });
    }

    public void init(){
        this.ko = findViewById(R.id.choiceKo);
        this.papir = findViewById(R.id.choicePapir);
        this.ollo = findViewById(R.id.choiceOllo);
        this.emberChoice = findViewById(R.id.userChoice);
        this.computerChoice = findViewById(R.id.aiChoice);
        this.emberPointsView = findViewById(R.id.emberPoints);
        this.computerPointsView = findViewById(R.id.computerPoints);
        this.emberPoints = 0;
        this.computerPoints = 0;
        this.user = 1;
        this.computer = 1;
        this.rnd = new Random();
        this.alertDialog = new AlertDialog.Builder(this).setTitle("Győzelem").setMessage("Szeretne új játékot játszani?").setCancelable(false).setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
            }
        }).setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).create();
    }

    public void computerChoose(){
        computer = rnd.nextInt(4);
        changeCompPic(computer);
    }

    public void changeCompPic(int pic){
        switch(pic) {
            case 1:
                computerChoice.setImageResource(R.drawable.rock);
                break;
            case 2:
                computerChoice.setImageResource(R.drawable.paper);
                break;
            case 3:
                computerChoice.setImageResource(R.drawable.scissors);
                break;
        }

    }

    public void calcResults(){
        switch (user){
            case 1:
                switch(computer) {
                    case 2:
                        computerPoints++;
                        computerPointsView.setText("Computer: " + computerPoints);
                        Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        emberPoints++;
                        emberPointsView.setText("Ember: " + emberPoints);
                        Toast.makeText(MainActivity.this, "Ezt a kört te nyerted!", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case 2:
                switch(computer) {
                    case 1:
                        emberPoints++;
                        emberPointsView.setText("Ember: " + emberPoints);
                        Toast.makeText(MainActivity.this, "Ezt a kört te nyerted!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        computerPoints++;
                        computerPointsView.setText("Computer: " + computerPoints);
                        Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case 3:
                switch(computer) {
                    case 2:
                        emberPoints++;
                        emberPointsView.setText("Ember: " + emberPoints);
                        Toast.makeText(MainActivity.this, "Ezt a kört te nyerted!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        computerPoints++;
                        computerPointsView.setText("Computer: " + computerPoints);
                        Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                }
        }

    }

    public void checkForEnd(){
        if(emberPoints == 3){
            alertDialog.show();
        } else if (computerPoints == 3) {
            alertDialog.setTitle("Vereség");
            alertDialog.create();
            alertDialog.show();
        }
    }

    public void ujJatek(){
        emberPoints = 0;
        computerPoints = 0;
        computerPointsView.setText("Computer: " + computerPoints);
        emberPointsView.setText("Ember: " + emberPoints);
    }

}