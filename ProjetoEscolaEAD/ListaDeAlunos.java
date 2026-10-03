public class ListaDeAlunos
{
    private Aluno[] alunos;
    private int totalAlunos;

    public ListaDeAlunos(int capacidade)
    {
        alunos = new Aluno[capacidade];
        totalAlunos = 0;
    }

    public boolean adicionarAluno(Aluno a)
    {
        for(int i = 0; i < totalAlunos; i++)
        {
            if(alunos[i].getCodigo() == a.getCodigo())
            {
                return false;
            }
        }

        if(totalAlunos < alunos.length)
        {
            alunos[totalAlunos] = a;
            totalAlunos++;

            return true;
        }

        return false;
    }

    public void exibirLista()
    {
        if(totalAlunos == 0)
        {
            System.out.println(
                "Nenhum aluno cadastrado."
            );
            return;
        }

        for(int i = 0; i < totalAlunos; i++)
        {
            alunos[i].exibeDados();

            System.out.println(
                "----------------------------"
            );
        }
    }

    public Aluno buscarAluno(int codigo)
    {
        for(int i = 0; i < totalAlunos; i++)
        {
            if(alunos[i].getCodigo() == codigo)
            {
                return alunos[i];
            }
        }

        return null;
    }

    public Aluno[] getAlunos()
    {
        return alunos;
    }

    public int getTotalAlunos()
    {
        return totalAlunos;
    }
}