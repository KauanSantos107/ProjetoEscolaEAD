public class SistemaEscolaEAD
{
    public static void main(String[] args)
{
    SistemaEscolaEAD sistema = new SistemaEscolaEAD();
    sistema.executar();
}
    private ListaDeAlunos listaAlunos;

    private Curso[][] matrizCursos;

    private int totalCursos;

    public SistemaEscolaEAD()
    {
        listaAlunos = new ListaDeAlunos(50);

        matrizCursos = new Curso[2][5];

        totalCursos = 0;
    }



    public void cadastrarCursos()
    {
        int quantidade = Teclado.leInt(
            "Quantos cursos deseja cadastrar? "
        );

        if(quantidade <= 0)
        {
            System.out.println(
                "A quantidade deve ser maior que zero."
            );
            return;
        }

        int espacosDisponiveis = 10 - totalCursos;

        if(quantidade > espacosDisponiveis)
        {
            System.out.println(
                "Você pode cadastrar apenas " +
                espacosDisponiveis + " curso(s)."
            );

            quantidade = espacosDisponiveis;
        }

        for(int i = 0; i < quantidade; i++)
        {
            System.out.println(
                "\n=== CADASTRO DO CURSO ==="
            );

            int codigo = Teclado.leInt(
                "Código: "
            );

            if(buscarCurso(codigo) != null)
            {
                System.out.println(
                    "Já existe um curso com esse código."
                );

                i--;
                continue;
            }

            String nome = Teclado.leString(
                "Nome: "
            );

            int duracao = Teclado.leInt(
                "Duração em horas: "
            );

            if(duracao <= 0)
            {
                System.out.println(
                    "A duração deve ser maior que zero."
                );

                i--;
                continue;
            }

            Curso curso = new Curso(
                codigo,
                nome,
                duracao
            );

            int linha = totalCursos / 5;
            int coluna = totalCursos % 5;

            matrizCursos[linha][coluna] = curso;

            totalCursos++;
        }

        System.out.println(
            "Cadastro de cursos finalizado."
        );
    }

    public Curso buscarCurso(int codigo)
    {
        for(int linha = 0;
            linha < matrizCursos.length;
            linha++)
        {
            for(int coluna = 0;
                coluna < matrizCursos[linha].length;
                coluna++)
            {
                if(matrizCursos[linha][coluna] != null &&
                   matrizCursos[linha][coluna].getCodigo() == codigo)
                {
                    return matrizCursos[linha][coluna];
                }
            }
        }

        return null;
    }

    public void exibirCursos()
    {
        if(totalCursos == 0)
        {
            System.out.println(
                "Nenhum curso cadastrado."
            );
            return;
        }

        System.out.println("\n=== CURSOS CADASTRADOS ===");

        for(int linha = 0;
            linha < matrizCursos.length;
            linha++)
        {
            for(int coluna = 0;
                coluna < matrizCursos[linha].length;
                coluna++)
            {
                if(matrizCursos[linha][coluna] != null)
                {
                    matrizCursos[linha][coluna].exibeDados();

                    System.out.println(
                        "----------------------------"
                    );
                }
            }
        }
    }



    public void adicionarAluno()
    {
        System.out.println(
            "\n=== CADASTRO DE ALUNO ==="
        );

        int codigo = Teclado.leInt(
            "Código: "
        );

        String nome = Teclado.leString(
            "Nome: "
        );

        String dataNascimento = Teclado.leString(
            "Data de nascimento: "
        );

        String email = Teclado.leString(
            "E-mail: "
        );

        String senha = Teclado.leString(
            "Senha: "
        );

        Aluno aluno = new Aluno(
            codigo,
            nome,
            dataNascimento,
            email,
            senha
        );

        if(listaAlunos.adicionarAluno(aluno))
        {
            System.out.println(
                "Aluno cadastrado com sucesso!"
            );
        }
        else
        {
            System.out.println(
                "Erro: código já cadastrado ou lista cheia."
            );
        }
    }

    public void adicionarAlunoBolsista()
    {
        System.out.println(
            "\n=== CADASTRO DE ALUNO BOLSISTA ==="
        );

        int codigo = Teclado.leInt(
            "Código: "
        );

        String nome = Teclado.leString(
            "Nome: "
        );

        String dataNascimento = Teclado.leString(
            "Data de nascimento: "
        );

        String email = Teclado.leString(
            "E-mail: "
        );

        String senha = Teclado.leString(
            "Senha: "
        );

        String tipoBolsa = Teclado.leString(
            "Tipo de bolsa: "
        );

        AlunoBolsista aluno = new AlunoBolsista(
            codigo,
            nome,
            dataNascimento,
            email,
            senha,
            tipoBolsa
        );

        if(listaAlunos.adicionarAluno(aluno))
        {
            System.out.println(
                "Aluno bolsista cadastrado com sucesso!"
            );
        }
        else
        {
            System.out.println(
                "Erro: código já cadastrado ou lista cheia."
            );
        }
    }



    public void matricularAluno()
    {
        if(listaAlunos.getTotalAlunos() == 0)
        {
            System.out.println(
                "Nenhum aluno cadastrado."
            );
            return;
        }

        if(totalCursos == 0)
        {
            System.out.println(
                "Nenhum curso cadastrado."
            );
            return;
        }

        int codigoAluno = Teclado.leInt(
            "Código do aluno: "
        );

        Aluno aluno = listaAlunos.buscarAluno(
            codigoAluno
        );

        if(aluno == null)
        {
            System.out.println(
                "Aluno não encontrado."
            );
            return;
        }

        int codigoCurso = Teclado.leInt(
            "Código do curso: "
        );

        Curso curso = buscarCurso(codigoCurso);

        if(curso == null)
        {
            System.out.println(
                "Curso não encontrado."
            );
            return;
        }

        aluno.setCursoMatriculado(curso);

        System.out.println(
            "Aluno matriculado com sucesso!"
        );

        System.out.println(
            "Aluno: " + aluno.getNome()
        );

        System.out.println(
            "Curso: " + curso.getNome()
        );
    }


    public void lancarNotasAluno()
    {
        int codigo = Teclado.leInt(
            "Digite o código do aluno: "
        );

        Aluno aluno = listaAlunos.buscarAluno(
            codigo
        );

        if(aluno == null)
        {
            System.out.println(
                "Aluno não encontrado."
            );
            return;
        }

        System.out.println(
            "Aluno encontrado: " +
            aluno.getNome()
        );

        aluno.lancarNotas();
    }

    public void verificarNotasAluno()
    {
        int codigo = Teclado.leInt(
            "Digite o código do aluno: "
        );

        Aluno aluno = listaAlunos.buscarAluno(
            codigo
        );

        if(aluno == null)
        {
            System.out.println(
                "Aluno não encontrado."
            );
            return;
        }

        aluno.exibirNotas();
    }


    public void cadastrarMensalidades()
    {
        int codigo = Teclado.leInt(
            "Digite o código do aluno: "
        );

        Aluno aluno = listaAlunos.buscarAluno(
            codigo
        );

        if(aluno == null)
        {
            System.out.println(
                "Aluno não encontrado."
            );
            return;
        }

        int quantidade = Teclado.leInt(
            "Quantidade de parcelas: "
        );

        if(quantidade <= 0)
        {
            System.out.println(
                "A quantidade deve ser maior que zero."
            );
            return;
        }

        double[] valores = new double[quantidade];

        for(int i = 0; i < quantidade; i++)
        {
            double valor;

            do
            {
                valor = Teclado.leDouble(
                    "Valor da parcela " +
                    (i + 1) + ": R$ "
                );

                if(valor < 0)
                {
                    System.out.println(
                        "O valor não pode ser negativo."
                    );
                }

            } while(valor < 0);

            valores[i] = valor;
        }

        aluno.adicionarMensalidades(
            valores
        );
    }

    public void verificarFinanceiroAluno()
    {
        int codigo = Teclado.leInt(
            "Digite o código do aluno: "
        );

        Aluno aluno = listaAlunos.buscarAluno(
            codigo
        );

        if(aluno == null)
        {
            System.out.println(
                "Aluno não encontrado."
            );
            return;
        }

        aluno.exibirMensalidades();

        int parcela = Teclado.leInt(
            "\nDigite o número da parcela para pagar " +
            "ou 0 para voltar: "
        );

        if(parcela != 0)
        {
            aluno.pagarMensalidade(
                parcela - 1
            );
        }
    }



    public void executar()
    {
        int opcao;

        do
        {
            System.out.println();
            System.out.println(
                "======================================"
            );
            System.out.println(
                "          ESCOLA EAD"
            );
            System.out.println(
                "======================================"
            );

            System.out.println(
                "1 - Visualizar Lista de Alunos"
            );

            System.out.println(
                "2 - Adicionar Aluno"
            );

            System.out.println(
                "3 - Adicionar Aluno Bolsista"
            );

            System.out.println(
                "4 - Cadastrar Cursos"
            );

            System.out.println(
                "5 - Visualizar Cursos"
            );

            System.out.println(
                "6 - Matricular Aluno em Curso"
            );

            System.out.println(
                "7 - Lançar Notas do Aluno"
            );

            System.out.println(
                "8 - Verificar Notas do Aluno"
            );

            System.out.println(
                "9 - Cadastrar Mensalidades"
            );

            System.out.println(
                "10 - Verificar Financeiro do Aluno"
            );

            System.out.println(
                "11 - Sair"
            );

            System.out.println(
                "======================================"
            );

            opcao = Teclado.leInt(
                "Escolha uma opção: "
            );

            switch(opcao)
            {
                case 1:
                    listaAlunos.exibirLista();
                    break;

                case 2:
                    adicionarAluno();
                    break;

                case 3:
                    adicionarAlunoBolsista();
                    break;

                case 4:
                    cadastrarCursos();
                    break;

                case 5:
                    exibirCursos();
                    break;

                case 6:
                    matricularAluno();
                    break;

                case 7:
                    lancarNotasAluno();
                    break;

                case 8:
                    verificarNotasAluno();
                    break;

                case 9:
                    cadastrarMensalidades();
                    break;

                case 10:
                    verificarFinanceiroAluno();
                    break;

                case 11:
                    System.out.println(
                        "Sistema encerrado."
                    );
                    break;

                default:
                    System.out.println(
                        "Opção inválida!"
                    );
            }

        } while(opcao != 11);
    }
}