package br.ufsc.ine5605;


public class Hora {
    private int hora;
    private int minuto;
    
    public Hora(int hora, int minuto){
        if(hora < 24 && minuto < 60){
            this.hora = hora;
            this.minuto = minuto;
        }
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
    
    public boolean vemAntes(Hora horario){
        return hora < horario.getHora() || (hora == horario.getHora() && minuto < horario.getMinuto());

    }
     public boolean vemDepois(Hora horario){
        return hora > horario.getHora() || (hora == horario.getHora() && minuto > horario.getMinuto());
    }
     
    @Override
    public String toString(){
        return "" + hora + ":" + minuto;
    }
    
}
