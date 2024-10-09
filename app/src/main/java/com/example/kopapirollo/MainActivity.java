package com.example.kopapirollo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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

    private ImageView ko;
    private ImageView papir;
    private ImageView ollo;

    private ImageView eHeart1;
    private ImageView eHeart2;
    private ImageView eHeart3;

    private ImageView aiHeart1;
    private ImageView aiHeart2;
    private ImageView aiHeart3;

    private int drawCount;
    private TextView drawView;

    private ImageView emberChoice;
    private ImageView computerChoice;
    private TextView emberPointsView;
    private TextView computerPointsView;
    private int emberElet;
    private int computerElet;
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
                eletModositas();
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
                eletModositas();
                checkForEnd();
            }
        });
    }

    public void init(){
        this.ko = findViewById(R.id.chooseKo);
        this.papir = findViewById(R.id.choosePapir);
        this.ollo = findViewById(R.id.chooseOllo);

        this.drawCount = 0;
        this.drawView = findViewById(R.id.drawCount);

        this.eHeart1 = findViewById(R.id.emberHeart1);
        this.eHeart2 = findViewById(R.id.emberHeart2);
        this.eHeart3 = findViewById(R.id.emberHeart3);

        this.aiHeart1 = findViewById(R.id.aiHeart1);
        this.aiHeart2 = findViewById(R.id.aiHeart2);
        this.aiHeart3 = findViewById(R.id.aiHeart3);

        this.emberChoice = findViewById(R.id.userChoice);
        this.computerChoice = findViewById(R.id.aiChoice);
        //this.emberPointsView = findViewById(R.id.emberPoints);
        //this.computerPointsView = findViewById(R.id.computerPoints);
        this.emberElet = 3;
        this.computerElet = 3;
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

    public void draw(){
        drawCount++;
        drawView.setText("Döntetlenek száma: " + drawCount);
    }

    public void calcResults(){
        switch (user){
            case 1:
                switch(computer) {
                    case 2:
                        emberElet--;
                        Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        computerElet--;
                        Toast.makeText(MainActivity.this, "Ezt a kört te nyerted!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        draw();
                        break;
                }
                break;
            case 2:
                switch(computer) {
                    case 1:
                        computerElet--;
                        Toast.makeText(MainActivity.this, "Ezt a kört te nyerted!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        emberElet--;
                        Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        draw();
                        break;
                }
                break;
            case 3:
                switch(computer) {
                    case 2:
                        computerElet--;
                        Toast.makeText(MainActivity.this, "Ezt a kört te nyerted!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        emberElet--;
                        Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        draw();
                        break;
                }
        }

    }

    public void eletModositas(){
        if (emberElet == 2){
            eHeart3.setImageResource(R.drawable.heart1);
        }
        if (emberElet == 1){
            eHeart2.setImageResource(R.drawable.heart1);
        }
        if (emberElet == 0){
            eHeart1.setImageResource(R.drawable.heart1);
        }
        if (computerElet == 2){
            aiHeart3.setImageResource(R.drawable.heart1);
        }
        if (computerElet == 1){
            aiHeart2.setImageResource(R.drawable.heart1);
        }
        if (computerElet == 0){
            aiHeart1.setImageResource(R.drawable.heart1);
        }
    }

    public void checkForEnd(){
        if(emberElet == 0){
            alertDialog.setTitle("Vereség");
            alertDialog.create();
            alertDialog.show();
        } else if (computerElet == 0) {
            alertDialog.show();
        }
    }

    public void ujJatek(){
        emberElet = 3;
        computerElet = 3;
        drawCount = 0;
        eHeart1.setImageResource(R.drawable.heart2);
        eHeart2.setImageResource(R.drawable.heart2);
        eHeart3.setImageResource(R.drawable.heart2);
        aiHeart1.setImageResource(R.drawable.heart2);
        aiHeart2.setImageResource(R.drawable.heart2);
        aiHeart3.setImageResource(R.drawable.heart2);
        drawView.setText("Döntetlenek száma: 0");
    }

}