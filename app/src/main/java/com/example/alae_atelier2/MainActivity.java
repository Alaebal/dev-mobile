public class MainActivity extends AppCompatActivity {

    private final Bundle data = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showScreen1();
    }

    private void showScreen1() {
        setContentView(R.layout.example1);
        EditText editNom = findViewById(R.id.editNom);
        findViewById(R.id.btnNext).setOnClickListener(v -> {
            String nom = editNom.getText().toString().trim();
            if (nom.isEmpty()) { editNom.setError(getString(R.string.err_empty)); return; }
            data.putString(Keys.NOM, nom);
            showScreen2();
        });
    }

    private void showScreen2() {
        setContentView(R.layout.example2);
        ((TextView) findViewById(R.id.userName)).setText(data.getString(Keys.NOM, ""));
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editAge = findViewById(R.id.editAge);
        findViewById(R.id.btnNext).setOnClickListener(v -> {
            data.putString(Keys.EMAIL, editEmail.getText().toString().trim());
            data.putInt(Keys.AGE, Integer.parseInt(editAge.getText().toString().trim()));
            showScreen4();   // etc.
        });
    }

    // showScreen3(), showScreen4(), showScreen5() : même schéma
}