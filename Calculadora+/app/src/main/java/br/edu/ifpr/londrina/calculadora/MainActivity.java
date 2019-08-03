package br.edu.ifpr.londrina.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText res;
    Button equal;
    double first = 0;//, second = 0;
    int operation= 0;



    private Double getNum () {
        try {
            return Double.parseDouble(res.getText().toString());
        } catch (Exception n) {
            return null;
        }
    }
    private Double store () {
        Double t = getNum();
        if (t!=null){
            first = t;
        }   return  t;
    }
    private boolean storeClean () {
        if(store()==null)
            return false;
        res.setText("0");
        return true;
    }
    private void storeOp (int o) {
        if(operation!=0)
            solve();
        operation = o;
        equal.setText("IGUAL");
        storeClean();
    }
    private void solve () {
        Double r = null;
        try {
            switch (operation) {
                case 1:
                    r = first + getNum();
                    break;
                case 2:
                    r = first - getNum();
                    break;
                case 3:
                    r = first * getNum();
                    break;
                case 4:
                    r = getNum();
                    if (r!=0)
                        r = first / r;
                    break;
            }
        } catch (Exception e) {
            return;
        }
        res.setText(r.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        ButtonListener bnl = new ButtonListener ();
        equal = findViewById(R.id.equal);

     for (int c = R.id.button11; c<R.id.button20; c++)
        findViewById(c).setOnClickListener(bnl);
        findViewById(R.id.plus).setOnClickListener(bnl);    //1
        findViewById(R.id.less).setOnClickListener(bnl);    //2
        findViewById(R.id.times).setOnClickListener(bnl);   //3
        findViewById(R.id.divided).setOnClickListener(bnl); //4
        equal.setOnClickListener(bnl);
        res=findViewById(R.id.editText);

    }

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick (View v) {

            switch (v.getId()) {
                case R.id.plus:
                    storeOp(1);
                    break;
                case R.id.less:
                    storeOp(2);
                    break;
                case R.id.times:
                    storeOp(3);
                    break;
                case R.id.divided:
                    storeOp(4);
                    break;
                case R.id.equal:
                    if(operation==0) {
                        res.setText("00");
                        return;
                    }
                    solve();
                    equal.setText("");
                    operation = 0;
                    break;

                    default:
                        for (int c = 0; c<10; c++)
                            if (R.id.button11 + c == v.getId())
                                res.setText(res.getText() + Integer.toString(c));
            }
            
        }
    }
}
