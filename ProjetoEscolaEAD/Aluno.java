public class Aluno
{
    private int codigo;
    private String nome;
    private String dataNascimento;
    private String email;
    private String senha;

    private Curso cursoMatriculado;


    private double[] notas = new double[3];
    private boolean[] lancada = new boolean[3];


    private Mensalidade[] mensalidades;
    private int numParcelas;

  
    public Aluno(int codigo, String nome, String dataNascimento,
                 String email, String senha)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;

        cursoMatriculado = null;

        for(int i = 0; i < 3; i++)
        {
            notas[i] = 0;
            lancada[i] = false;
        }

        mensalidades = null;
        numParcelas = 0;
    }



    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public Curso getCursoMatriculado()
    {
        return cursoMatriculado;
    }

    public void setCursoMatriculado(Curso cursoMatriculado)
    {
        this.cursoMatriculado = cursoMatriculado;
    }



    public void lancarNotas()
    {
        for(int i = 0; i < 3; i++)
        {
            double nota;

            do
            {
                nota = Teclado.leDouble(
                    "Digite a nota " + (i + 1) + " (0 a 10): "
                );

                if(nota < 0 || nota > 10)
                {
                    System.out.println(
                        "A nota deve estar entre 0 e 10."
                    );
                }

            } while(nota < 0 || nota > 10);

            notas[i] = nota;
            lancada[i] = true;
        }

        System.out.println("Notas lançadas com sucesso!");
    }

    public double calcularMedia()
    {
        double soma = 0;

        for(int i = 0; i < 3; i++)
        {
            soma += notas[i];
        }

        return soma / 3;
    }

    public void exibirNotas()
    {
        System.out.println("\n=== NOTAS DO ALUNO ===");
        System.out.println("Aluno: " + nome);
        System.out.println("Código: " + codigo);

        for(int i = 0; i < 3; i++)
        {
            if(lancada[i])
            {
                System.out.println(
                    "Nota " + (i + 1) + ": " + notas[i]
                );
            }
            else
            {
                System.out.println(
                    "Nota " + (i + 1) + ": Não lançada"
                );
            }
        }

        System.out.printf("Média: %.2f%n", calcularMedia());
    }



    public void adicionarMensalidades(double[] valores)
    {
        numParcelas = valores.length;

        mensalidades = new Mensalidade[numParcelas];

        for(int i = 0; i < numParcelas; i++)
        {
            mensalidades[i] = new Mensalidade(valores[i]);
        }

        System.out.println(
            "Mensalidades cadastradas com sucesso!"
        );
    }

    public void exibirMensalidades()
    {
        if(mensalidades == null || numParcelas == 0)
        {
            System.out.println(
                "Nenhuma mensalidade cadastrada."
            );
            return;
        }

        System.out.println("\n=== FINANCEIRO DO ALUNO ===");
        System.out.println("Aluno: " + nome);
        System.out.println("Código: " + codigo);

        for(int i = 0; i < numParcelas; i++)
        {
            String status;

            if(mensalidades[i].isPago())
            {
                status = "PAGO";
            }
            else
            {
                status = "PENDENTE";
            }

            System.out.printf(
                "Parcela %d | Valor: R$ %.2f | Status: %s%n",
                i + 1,
                mensalidades[i].getValor(),
                status
            );
        }
    }

    public void pagarMensalidade(int indice)
    {
        if(mensalidades == null)
        {
            System.out.println(
                "Nenhuma mensalidade cadastrada."
            );
            return;
        }

        if(indice < 0 || indice >= numParcelas)
        {
            System.out.println(
                "Número de parcela inválido."
            );
            return;
        }

        if(mensalidades[indice].isPago())
        {
            System.out.println(
                "Essa parcela já está paga."
            );
            return;
        }

        mensalidades[indice].darBaixa();

        System.out.println(
            "Parcela " + (indice + 1) +
            " paga com sucesso!"
        );
    }



    public void exibeDados()
    {
        System.out.println("=== DADOS DO ALUNO ===");
        System.out.println("Código: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println(
            "Data de nascimento: " + dataNascimento
        );
        System.out.println("E-mail: " + email);
        System.out.println("Senha: " + senha);

        if(cursoMatriculado != null)
        {
            System.out.println(
                "Curso: " + cursoMatriculado.getNome()
            );
        }
        else
        {
            System.out.println("Curso: Nenhum");
        }
    }
}