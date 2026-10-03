public class AlunoBolsista extends Aluno
{
    private String tipoBolsa;

    public AlunoBolsista(int codigo, String nome,
                         String dataNascimento,
                         String email,
                         String senha,
                         String tipoBolsa)
    {
        super(codigo, nome, dataNascimento, email, senha);

        this.tipoBolsa = tipoBolsa;
    }

    public String getTipoBolsa()
    {
        return tipoBolsa;
    }

    public void setTipoBolsa(String tipoBolsa)
    {
        this.tipoBolsa = tipoBolsa;
    }

    @Override
    public void exibeDados()
    {
        System.out.println("=== DADOS DO ALUNO BOLSISTA ===");
        System.out.println("Código: " + getCodigo());
        System.out.println("Nome: " + getNome());
        System.out.println(
            "Data de nascimento: " + getDataNascimento()
        );
        System.out.println("E-mail: " + getEmail());
        System.out.println("Senha: " + getSenha());
        System.out.println("Tipo de bolsa: " + tipoBolsa);

        if(getCursoMatriculado() != null)
        {
            System.out.println(
                "Curso: " + getCursoMatriculado().getNome()
            );
        }
        else
        {
            System.out.println("Curso: Nenhum");
        }
    }
}