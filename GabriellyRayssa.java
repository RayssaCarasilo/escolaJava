/*
 * Alunas: Gabrielly de Oliveira
 *         Rayssa Carasilo dos Santos
 */

import java.util.ArrayList;
import java.util.Scanner;

//Classe que representa o aluno
class Aluno {

    private int matricula;
    private String nome;
    private double nota1;
    private double nota2;

    //Construtor
    public Aluno(int Matricula, String Nome) {
        this.matricula = Matricula;
        this.nome = Nome;
        this.nota1 = 0;
        this.nota2 = 0;
    }

    //Getters
    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    //Setters
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    // método para buscar um aluno pela matricula
    public static Aluno buscar(ArrayList<Aluno> alunos, int matricula) {
        for (Aluno a : alunos) {
            if (a.getMatricula() == matricula) {
                return a;
            }
        }
        return null;
    }

    // método para calcular a média de um aluno
    public double calcularMedia() {
        return (nota1 + nota2) / 2;
    }

    // método para encontrar o aluno com a maior média
    public static Aluno maiorMedia(ArrayList<Aluno> alunos) {
        Aluno melhor = null;
        for (Aluno a : alunos) {
            if (melhor == null || a.calcularMedia() > melhor.calcularMedia()) {
                melhor = a;
            }
        }
        return melhor;
    }
}

//Classe principal
public class GabriellyRayssa {

    // método principal do programa
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Aluno> alunos = new ArrayList<>();

        int opcao;

        do {
            System.out.println("\n========= SISTEMA DE ALUNOS ==========");
            System.out.println("1 - Cadastrar um novo aluno: ");
            System.out.println("2 - Cadastrar notas: ");
            System.out.println("3 - Listar todos os alunos: ");
            System.out.println("4 - Buscar aluno pela matricula: ");
            System.out.println("5 - Identificar aluno com maior nota: ");
            System.out.println("6 - Alterar as notas de um aluno: ");
            System.out.println("7 - Remover um aluno: ");
            System.out.println("0 - Encerrar o programa: ");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            // verifica se há alunos cadastrados antes de permitir opções que dependem disso
            if (alunos.isEmpty() && (opcao == 2 || (opcao >= 4 && opcao <= 7))) {
                System.out.println("Nenhum aluno cadastrado ainda.");
                continue;
            }

            // executa a ação correspondente à opção escolhida
            switch (opcao) {
                case 1: {
                    //Cadastrar um novo aluno
                    System.out.print("Digite a matricula: ");
                    int matricula = scanner.nextInt();
                    scanner.nextLine();

                    //Verificar se a matricula ja existe
                    boolean existe = false;

                    for (Aluno aluno : alunos) {
                        if (aluno.getMatricula() == matricula) {
                            existe = true;
                            break;
                        }
                    }

                    if (existe) {
                        System.out.println("Essa matricula ja esta cadastrada!");
                    } else {
                        System.out.print("Digite o nome do aluno: ");
                        String nome = scanner.nextLine();

                        Aluno novoAluno = new Aluno(matricula, nome);
                        alunos.add(novoAluno);

                        System.out.println("Aluno cadastrado com sucesso!");
                    }
                    break;
                }

                case 2: {
                    System.out.print("Digite a matricula do aluno: ");
                    int matricula = scanner.nextInt();
                    scanner.nextLine();
                    Aluno a = Aluno.buscar(alunos, matricula);
                    // verifica se o aluno existe antes de permitir a inserção de notas
                    if (a == null) {
                        System.out.println("Aluno não encontrado.");
                        break;
                    }

                    if (a.getNota1() != 0 && a.getNota2() != 0) {
                        System.out.println("Notas já preenchidas.");
                        break;
                    }

                    System.out.print("Digite a nota N1 do aluno: ");
                    a.setNota1(scanner.nextDouble());
                    System.out.print("Digite a nota N2 do aluno: ");
                    a.setNota2(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.println("Notas salvas!");
                    break;
                }

                case 3:
                    //Listar todos os alunos
                    if (alunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado!");
                    } else {
                        System.out.println("\n========== ALUNOS CADASTRADOS ============");

                        // exibe todas as informações de todos os alunos, com o for acessando cada aluno da lista
                        for (Aluno aluno : alunos) {
                            System.out.println("-------------------------------------------------");
                            System.out.println("Matricula: " + aluno.getMatricula());
                            System.out.println("Nome: " + aluno.getNome());
                            System.out.println("Nota 1: " + aluno.getNota1());
                            System.out.println("Nota 2: " + aluno.getNota2());
                        }
                    }
                    break;

                case 4: {
                    System.out.print("Digite a matricula do aluno: ");
                    int matricula = scanner.nextInt();
                    scanner.nextLine();
                    Aluno a = Aluno.buscar(alunos, matricula);
                    if (a == null) {
                        System.out.println("Aluno não encontrado.");
                    } else {
                        System.out.println("-------------------------------------------------");
                        System.out.println("Matricula: " + a.getMatricula());
                        System.out.println("Nome: " + a.getNome());
                        System.out.println("Nota 1: " + a.getNota1());
                        System.out.println("Nota 2: " + a.getNota2());
                    }
                    break;
                }

                case 5: {
                    // encontra e exibe o aluno com a maior média, através do método maiorMedia da classe Aluno
                    if (alunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado!");
                        break;
                    }

                    Aluno melhor = Aluno.maiorMedia(alunos);
                    System.out.printf("O aluno de maior média é: %s (%.2f)%n",
                            melhor.getNome(), melhor.calcularMedia());
                    break;
                }

                case 6: {
                    System.out.print("Digite a matricula do aluno: ");
                    int matricula = scanner.nextInt();
                    scanner.nextLine();
                    Aluno a = Aluno.buscar(alunos, matricula);
                    if (a == null) {
                        System.out.println("Aluno não encontrado.");
                        break;
                    }
                    System.out.print("Digite a nova nota N1 do aluno: ");
                    a.setNota1(scanner.nextDouble());
                    System.out.print("Digite a nova nota N2 do aluno: ");
                    a.setNota2(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.println("Notas alteradas!");
                    break;
                }

                case 7: {
                    System.out.print("Digite a matricula do aluno: ");
                    int matricula = scanner.nextInt();
                    scanner.nextLine();
                    Aluno a = Aluno.buscar(alunos, matricula);
                    if (a == null) {
                        System.out.println("Aluno não encontrado.");
                    } else {
                        // remove o aluno da lista de alunos caso tenha sido encontrado
                        alunos.remove(a);
                        System.out.println("Aluno removido!");
                    }
                    break;
                }

                case 0:
                    System.out.println("bye bye ");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
