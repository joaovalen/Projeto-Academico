
package aula5exemplos;

public class Professor {
    private String nome;
    private String areas[];
    private long siape;

    public boolean novaArea(String area) {
        for (int i = 0; i < areas.length; i++) {
            if (areas[i] == null) {
                areas[i] = area;
                return true;
            }
        }
        return false;
    }
    
    public boolean removerArea(String area) {
        for (int i = 0; i < areas.length; i++) {
            if (areas[i].equals(area)) {
                areas[i] = null;
                return true;
            }
        }
        return false;
    }
   
     public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String[] getAreas() {
        return areas;
    }
    public void setAreas(String[] areas) {
        this.areas = areas;
    }
    public long getSiape() {
        return siape;
    }
    public void setSiape(long siape) {
        this.siape = siape;
    }
    public void alterarNome(String novoNome) {
        this.nome = novoNome;
    }
    
    
     public Professor(String nome, long siape){
        this.nome = nome;
        this.siape = siape;
    }
    
    public Professor(String nome,long siape,String[] areas){
        this.nome = nome;
        this.siape = siape;
        this.areas = areas;
    }
    
}
