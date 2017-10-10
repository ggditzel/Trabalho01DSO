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
    
    /**
     * Verifica se o horario antecede o horario do parametro
     * @param horario
     * 	horario que sera comparado
     * @return
     * true, se o horario anteceder, ou false, se o horario proceder
     */
    public boolean vemAntes(Hora horario){
        return hora < horario.getHora() || (hora == horario.getHora() && minuto < horario.getMinuto());

    }
    
    /**
     * Verifica se o horario procede o horario do parametro
     * @param horario
     * 	horario que sera comparado
     * @return
     * true, se o horario proceder, ou false, se o horario anteceder
     */
     public boolean vemDepois(Hora horario){
        return hora > horario.getHora() || (hora == horario.getHora() && minuto > horario.getMinuto());
    }
     
    @Override
    public String toString() {
        if(hora >= 10 && minuto >= 10) {
        	return "" + hora + ":" + minuto;
        }
        if(hora >= 10) {
        	return "" + hora + ":0" + minuto;
        }
        if(minuto >= 10) {
        	return "0" + hora + ":" + minuto;
        } else {
        	return "0" + hora + ":0" + minuto;
        }
        
    }
    
}
