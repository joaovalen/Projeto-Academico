
package projeto_academico;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class SetorEnsino implements Serializable {
    
    public static final int POSICAO_INVALIDA = -1;
    public static final int MAX_PROFESSORES = 60;
    public static final int MAX_CURSOS = 12;
    public static final int MAX_ALUNOS = 500;

    private Aluno[] alunos;
    private Curso cursos[];
    private Professor professores[];
    private String diretor;
    private String coordenador;
    
    
    //////////////////////////// PROFESSOR /////////////////////////////////////
    
    public boolean novaArea(int pos_professor, String area){
        if (professores != null) {
            System.out.println("SIM ELE ENTROU ");
            String areas[] = professores[pos_professor].getAreas();

            for (int i = 0; areas != null && i < areas.length; i++) {
                if (areas[i] == null) {
                    areas[i] = area;
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean novoProfessor(String nome, int siape, String[] areas) {
        for (int i = 0; i < professores.length; i++) {
            if (professores[i] == null) {
                professores[i] = new Professor(nome, siape, areas);
                return true;
            }
        }
        return false;
    }
    
    public boolean novoProfessor(Professor p) {
        for (int i = 0; i < professores.length; i++) {
            if (professores[i] == null) {
                professores[i] = p;
                return true;
            }
        }
        return false;
    }

    public boolean demitirProfessor(int siape) {
        for (int i = 0; i < professores.length; i++) {
            if (professores[i] != null && professores[i].getSiape() == siape) {
                professores[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public Professor encontraProfessor(int siape) {
        if (professores != null){
            for (Professor prof : professores) {
                if (prof != null && prof.getSiape() == siape) {
                    return prof;
                }
            }
        }
        return null;
    }
    
    public int login_professor(int siape) {
        for (int i = 0; professores != null
                && i < professores.length; i++) {
            if (professores[i] != null
                    && professores[i].getSiape() == siape) {
                return i;
            }
        }
        return POSICAO_INVALIDA;
    }
    
    public boolean alterarNota(String nome_disciplina, String nome_curso, String nome_aluno, float nova_nota) {
        System.out.println("VARIÁVEL NOME_CURSO: " + nome_curso);
        System.out.println(cursos[0].getNome());
        for (Curso curso : cursos) {
            System.out.println("PERCORRENDO CURSOS");
            if (curso != null){
                System.out.println("TESTANDO SE É NULO");
                if (curso.getNome().equals(nome_curso)) {
                    System.out.println("TESTANDO O NOME");
                    for (Disciplina disciplina : curso.getDisciplinas()) {
                        if (disciplina.getNome().equals(nome_disciplina)) {
                            Aluno aluno = encontraAluno(nome_aluno);
                            System.out.println("ENTROU NO REGISTRO DE NOTA");
                            if(disciplina.registrarNota(nova_nota, aluno)){
                                System.out.println("RETORNOU VERDADEIRO");
                                return true;
                            }   
                            
                        }
                    }
            }   }
        }
        return false;
    }

    /////////////////////////// CURSO //////////////////////////////////////////
    
    public boolean novoCurso(String nome, String ppc) {
        for (int i = 0; i < cursos.length; i++) {
            if (cursos[i] == null) {
                cursos[i] = new Curso(nome, ppc);
                return true;
            }
        }
        return false;
    }
    
    public boolean novoCurso(Curso c) {
        for (int i = 0; i < getCursos().length; i++) {
            if (getCursos()[i] == null) {
                getCursos()[i] = c;
                return true;
            }
        }
        return false;
    }

    public boolean removerCurso(String nome) {
        for (int i = 0; i < cursos.length; i++) {
            if (cursos[i] != null && cursos[i].getNome().equals(nome)) {
                cursos[i] = null;
                return true;

            }
        }
        return false;
    }
    
    public Curso encontraCurso(String nome){
        if (cursos != null) {
            for (Curso curso : cursos) {
                if (curso != null && curso.getNome().equals(nome)) {
                    return curso;
                }
            }
        }
        return null;
    }
    
    public void ver_cursos() {
        if (cursos != null) {
            for (Curso curso : cursos) {
                if (curso != null) {
                    System.out.println("Curso " + curso.getNome());
                    System.out.println("PPC: " + curso.getPpc());
                    System.out.println("Disciplinas: ");
                    if (curso.getDisciplinas() != null) {
                        for (Disciplina d : curso.getDisciplinas()) {
                            if (d != null) {
                                System.out.println(d.getNome());
                            }
                        }
                    }
                }
            }
        } else {
            System.err.println("Não existem cursos cadastrados.");
        }
    }
    
    
    /////////////////////////// ALUNO //////////////////////////////////////////
    
    public boolean novoAluno(Aluno aluno){
         for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] == null) {
                alunos[i] = aluno;
                return true;
            }
        }
        return false;
    }

    public boolean novoAluno(String nome, long matricula, long anoIngresso) {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null) {
                alunos[i] = new Aluno(nome, matricula, anoIngresso);
                return true;
            }
        }
        return false;
    }
    
    public boolean matricularAluno(Aluno a) {
        boolean ok = false;
        
        for (Curso curso : cursos) {
            if (curso != null && curso.getNome().equals(a.getCurso().getNome())) {
                ok = true;
                for (Disciplina disciplina : curso.getDisciplinas()) {
                    if (disciplina != null){
                        disciplina.registrarAluno(a);
                    }
                }
            }
        }
        return ok;
    }

    public boolean desligaAluno(long matricula) {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getMatricula() == matricula) {
                alunos[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public Aluno encontraAluno(long matricula){
        for (Aluno aluno : alunos) {
            if (aluno != null && aluno.getMatricula() == matricula){
            return aluno;
            }
        }return null;
    }
    
     public Aluno encontraAluno(String nome){
        for (Aluno aluno : alunos) {
            if (aluno != null && aluno.getNome().equals(nome)){
            return aluno;
            }
        }return null;
    }
    
    public void ver_notas(long matricula) {
        boolean aluno_nao_encontrado = true;  
        Aluno aluno = encontraAluno(matricula);
        
        if (aluno != null){
            aluno_nao_encontrado = false;
            Curso curso = aluno.getCurso();
            if (cursos != null) {
                ArrayList<Disciplina> disciplinas = curso.getDisciplinas();
                if (disciplinas != null) {
                    for (Disciplina disciplina : disciplinas) {
                        if (disciplina != null) {
                            for (Map.Entry<Aluno, Float> w: disciplina.getAlunos().entrySet()) {
                                if (w.getKey().equals(aluno)){
                                     System.out.println("A nota do aluno "
                                        + aluno.getNome()
                                        + " é de "
                                        + w.getValue()
                                        + " na disciplina "
                                        + disciplina.getNome());    
                                }
                              
                    
                           

                                       
                            }
                        }
                    }
                }
            }
        }
        if (aluno_nao_encontrado) {
            System.err.println("Aluno não matriculado no sistema.");
        }
    }

    ////////////////////////// DISCIPLINA //////////////////////////////////////
 
    public Disciplina encontraDisciplina(String disciplina, Curso curso){
        if (curso != null && curso.getDisciplinas() != null){
            for (Disciplina disc : curso.getDisciplinas()){
                if (disc != null && disc.getNome().equals(disciplina)){
                    return disc;
                }
            }
        }
        return null;
    }
    
    public boolean novaDisciplina(Disciplina disciplina, Curso curso) {
        for (Curso c : cursos) {
            if (c.equals(curso)) {
                c.novaDisciplina(disciplina);
                return true;
              }
            }
        return false;
    }
    
    public void darNotas(String disc , String c) throws IOException{
        Curso curso = encontraCurso(c);
        
        if (curso == null){
           System.err.println("Curso " + c + " não encontrado.");
        }else {
            Disciplina d = encontraDisciplina(disc,curso);

            if (d == null) {
                System.err.println("Disciplina " + disc + " não encontrada.");
            } else {
                if (d.getAlunos() != null) {
                    System.out.println("Informe as notas dos alunos: ");
                    for (Map.Entry<Aluno, Float> w: d.getAlunos().entrySet()) {
                        System.out.println("Informe a nota do aluno " + w.getKey());
                        float nota = inputFloat();
                        d.getAlunos().replace(w.getKey(), nota);
                    }
                }
            }  
        }       
    }
    
  
    ////////////////////////// GET/SET /////////////////////////////////////////
    
  
    public Aluno[] getAlunos() {
        return alunos;
    }
    public void setAlunos(Aluno[] alunos) {
        this.alunos = alunos;
    }
    public Curso[] getCursos() {
        return cursos;
    }
    public void setCursos(Curso[] cursos) {
        this.cursos = cursos;
    }
    public Professor[] getProfessores() {
        return professores;
    }
    public void setProfessores(Professor[] professores) {
        this.professores = professores;
    }
    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    public String getCoordenador() {
        return coordenador;
    }
    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }
    
    ////////////////////////// CONSTRUTORES ////////////////////////////////////
    
     public SetorEnsino(String diretor, String coordenador) {
        this.coordenador = coordenador;
        this.diretor = diretor;
        this.alunos = new Aluno[MAX_ALUNOS];
        this.professores = new Professor[MAX_PROFESSORES];
        this.cursos = new Curso[MAX_CURSOS];
    }

    public SetorEnsino(Curso[] cursos,
            Professor[] professores,
            String diretor,
            String coordenador) {
        this.cursos = new Curso[MAX_CURSOS];
        this.professores = new Professor[MAX_PROFESSORES];
        this.diretor = diretor;
        this.coordenador = coordenador;
    }
    
    ////////////////////////// LEITURA /////////////////////////////////////////
    
     private static String inputString(){
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        return x;
    }
     
     private static Float inputFloat(){
         Scanner sc = new Scanner(System.in);
         float x = sc.nextFloat();
         return x;
     }
     
      private static int inputInt(){
         Scanner sc = new Scanner(System.in);
         int x = sc.nextInt();
         return x;
     }
      
    //////////////////////////// equals e toString /////////////////////////////
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Arrays.deepHashCode(this.cursos);
        hash = 61 * hash + Arrays.deepHashCode(this.professores);
        hash = 61 * hash + Objects.hashCode(this.diretor);
        hash = 61 * hash + Objects.hashCode(this.coordenador);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SetorEnsino other = (SetorEnsino) obj;
        if (!Objects.equals(this.diretor, other.diretor)) {
            return false;
        }
        return (Objects.equals(this.coordenador, other.coordenador));
    }

    @Override
    public String toString() {
        String nomes_cursos = "";

        for (Curso d : cursos) {
            if (d != null) {
                nomes_cursos += d.toString() + "\n";
            }
        }
        String nomes_professores = "";

        for (Professor e : professores) {
            if (e != null) {
                nomes_professores += e.toString() + "\n";
            }
        }
        return "\n cursos: "
                + nomes_cursos
                + "\n professores: "
                + nomes_professores
                + "\n diretor: "
                + diretor
                + "\n coordenador: " + coordenador;
    }
}
